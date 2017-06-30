package com.feng.util.net.http;

import android.text.TextUtils;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by taohongao on 16/6/29.
 */
public class AacDownloader {
    private HashMap<String, String> mHeader;
    private String mUrl;

    public AacDownloader(Object lock, String url, int offset) {
        mOffset = offset;
        mLock = lock;
        mBuff = new byte[8 * 1024];

        mHeader = new HashMap<>();
        mHeader.put("range", "bytes=" + offset + "-");
        mUrl = url;
    }

    public int getDownloadOffset() {
        return mOffset + mDownloadLength;
    }

    public int getContentLength() {
        return mContentLength;
    }

    public void setDownloadListener(DownloadListener listener) {
        mDownloadListener = listener;
    }

    public void startDownload() {
        if (mThread != null) {
            return;
        }
        mThread = new Thread() {
            public void run() {
                doDownload();
            }
        };
        mThread.setName("AacDownloader");
        mThread.start();
    }

    public void cancelDownload() {
        synchronized (mLock) {
            mCanceled = true;
            if (mHttpClient != null) {
                new Thread() {
                    @Override
                    public void run() {
                        mHttpClient.disconnect();
                    }
                }.start();
            }
            mDownloadListener = null;
            mLock.notifyAll();
        }
    }

    public boolean isCanceled() {
        synchronized (mLock) {
            return mCanceled;
        }
    }

    private void doDownload() {
        try {
            mHttpClient = initHttps(mUrl, "GET", mHeader);
            mHttpClient.connect();
            synchronized (mLock) {
                if (mCanceled) {
                    return;
                }
            }

            mConnectOk = true;
            int code = mHttpClient.getResponseCode();
            if (code / 100 != 2) {
                synchronized (mLock) {
                    if (mCanceled) {
                        return;
                    }
                    mDownloadListener.onDownloadError(this, new AacError(EnumError.NET_INTERRUPTION));
                }
                return;
            }

            String headerRange = mHttpClient.getHeaderField("Content-Range");
            if (!TextUtils.isEmpty(headerRange)) {
                if (!parseContentRange(headerRange)) {
                    synchronized (mLock) {
                        if (mCanceled) {
                            return;
                        }
                        mDownloadListener.onDownloadError(this, new AacError(EnumError.DATA_BAD));
                        return;
                    }
                }
            } else {
                long contentLength = mHttpClient.getContentLength();
                if (mOffset != 0 || contentLength <= 0) {
                    synchronized (mLock) {
                        if (mCanceled) {
                            return;
                        }
                        mDownloadListener.onDownloadError(this, new AacError(EnumError.DATA_BAD));
                        return;
                    }
                }
                mContentLength = (int) contentLength;
            }

            InputStream in = mHttpClient.getInputStream();
            while (true) {
                int readBytes = in.read(mBuff);
                if (readBytes == -1) {
                    synchronized (mLock) {
                        if (mCanceled) {
                            return;
                        }
                        mDownloadListener.onDownloadComplete(this);

                    }
                    return;
                }
                if (readBytes == 0) {
                    continue;
                }
                mDownloadLength += readBytes;
                synchronized (mLock) {
                    if (mCanceled) {
                        return;
                    }

                    mDownloadListener.onReceiveData(this, mBuff, 0, readBytes);
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            synchronized (mLock) {
                if (mCanceled) {
                    return;
                }
                if (mConnectOk) {
                    mDownloadListener.onDownloadError(this, new AacError(EnumError.NET_INTERRUPTION));
                } else {
                    mDownloadListener.onDownloadError(this, new AacError(EnumError.NET_CONNECT_FAIL));
                }
            }
        }
    }

    public boolean parseContentRange(String contentRange) {
        int startOffset;
        int endOffset;
        int contentLength;
        int index0 = contentRange.indexOf("bytes");
        int index1 = contentRange.indexOf('-');
        int index2 = contentRange.indexOf('/');
        if (index0 == -1 || index1 == -1 || index2 == -1) {
            return false;
        }
        if (!(index2 > index1 && index1 > index0)) {
            return false;
        }

        try {
            String str;
            str = contentRange.substring(index0 + 5, index1);
            startOffset = Integer.parseInt(str.trim());
            str = contentRange.substring(index1 + 1, index2);
            endOffset = Integer.parseInt(str.trim());
            str = contentRange.substring(index2 + 1);
            contentLength = Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (mOffset != startOffset || endOffset + 1 != contentLength) {
            return false;
        }

        if (contentLength <= 0) {
            return false;
        }
        mContentLength = contentLength;
        return true;
    }

    @SuppressWarnings("UnusedParameters")
    public interface DownloadListener {
        void onReceiveData(AacDownloader downloader, byte[] data, int offset, int length);

        void onDownloadError(AacDownloader downloader, AacError error);

        void onDownloadComplete(AacDownloader downloader);
    }

    private final Object mLock;
    private byte[] mBuff;
    private HttpURLConnection mHttpClient;
    private DownloadListener mDownloadListener;
    private Thread mThread;
    private boolean mCanceled;
    private boolean mConnectOk;
    private int mOffset;
    private int mDownloadLength;
    private int mContentLength;


    /**
     * 初始化http请求参数
     */
    private HttpURLConnection initHttps(String url, String method, Map<String, String> headers)
            throws Exception {
        if(TextUtils.isEmpty(url)){
            return null;
        }

        URL _url = new URL(url);
        HttpURLConnection http=null;
        if (url.toLowerCase().startsWith("https://")) {
            http = (HttpsURLConnection) _url.openConnection();
        } else {
            http = (HttpURLConnection) _url.openConnection();
        }

        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(25000);
        http.setRequestMethod("GET");
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return http;
    }

}
