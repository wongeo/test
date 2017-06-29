package com.feng.util.net.http;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by feng on 2017/6/30.
 */

public class WebClient {
    private OnWebClientListener mOnWebClientListener;

    public void downloadData(String url) throws IOException {
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) new URL(url).openConnection();
        // 设置连接超时时间
        urlConn.setConnectTimeout(5 * 1000);
        // 开始连接
        urlConn.connect();
        // 判断请求是否成功
        if (urlConn.getResponseCode() == 200) {
            // 获取返回的数据
            byte[] data = readStream(urlConn.getInputStream());
            Log.i(TAG_GET, "Get方式请求成功，返回数据如下：");
        } else {
            Log.i(TAG_GET, "Get方式请求失败");
        }
        // 关闭连接
        urlConn.disconnect();
    }

    public void downloadDataAsync(String url) {

    }

    private byte[] readStream(InputStream inputStream) {
        return null;
    }

    public interface OnWebClientListener {

    }
}
