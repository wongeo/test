package com.feng.video.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.feng.media.IPlayStateCallback;
import com.feng.media.PlayerState;

import java.io.IOException;

/**
 * Created by wj148202 on 2017/11/9.
 */

public class CustomVideoView extends FrameLayout {

    private static final int VIDEO_PROGRESS = 1;

    private NewTextureView mTextureView;

    private MediaPlayer mMediaPlayer;

    private IPlayStateCallback mPlayStateCallback;
    private int mPlayerState;

    public CustomVideoView(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomVideoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(new ColorDrawable(Color.BLACK));
        }
        initMediaPlayer();
        initTextureView();
    }

    private void initTextureView() {
        mTextureView = new NewTextureView(getContext());
        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mTextureView, params);
    }

    public void play() {
        play("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4", 0);
    }

    public void play(String path, int position) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepareAsync();
            mProgressHandler.removeMessages(VIDEO_PROGRESS);
            mProgressHandler.sendEmptyMessageDelayed(VIDEO_PROGRESS, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        notifyPlayerStateChanged(PlayerState.STOPED);
        mMediaPlayer.stop();
    }

    public void pause() {
        if (!isPlaying()) {
            stop();
            return;
        }

        notifyPlayerStateChanged(PlayerState.PAUSED);
        mMediaPlayer.pause();
    }

    public boolean isPlaying() {
        return mMediaPlayer != null && mMediaPlayer.isPlaying();
    }

    public int getPlayerState() {
        return mPlayerState;
    }

    private synchronized void notifyPlayerStateChanged(int playerState) {
        mPlayerState = playerState;
        if (mPlayStateCallback != null) {
            mPlayStateCallback.onPlayerStateChanged(playerState);
        }
    }

    /**
     * 设置播放View 尺寸
     *
     * @param width
     * @param height
     */
    public void rebuildSize(int width, int height) {
        ViewGroup.LayoutParams lp = mTextureView.getLayoutParams();
        lp.width = width;
        lp.height = height;
        mTextureView.setLayoutParams(lp);
    }

    /**
     * @param f width/height
     */
    public void rebuildSize(float f) {

    }

    public void setPlayStateCallback(IPlayStateCallback callback) {
        mPlayStateCallback = callback;
    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerState = PlayerState.STOPED;
                if (mPlayStateCallback != null) {
                    mPlayStateCallback.onCompletion();
                }
            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mPlayerState = PlayerState.STOPED;
                if (mPlayStateCallback != null) {
                    mPlayStateCallback.onMediaError(new Exception("what:" + what + " extra:" + extra));
                }
                return false;
            }
        });
        mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mPlayerState = PlayerState.PREPARED;
                if (mPlayStateCallback != null) {
                    mPlayStateCallback.onPrepared(mp.getDuration());
                }
                start();
            }
        });
        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                mTextureView.setVideoSize(width, height);
            }
        });
    }

    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            if (mMediaPlayer != null) {
                mMediaPlayer.setSurface(new Surface(surface));
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    private void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            notifyPlayerStateChanged(PlayerState.PLAYING);
        }
    }


    private Handler mProgressHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VIDEO_PROGRESS:
                    if (mPlayStateCallback != null && mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        long position = mMediaPlayer.getCurrentPosition();
                        long duration = mMediaPlayer.getDuration();
                        mPlayStateCallback.onPlayPositionChanged(position * 1.0f / duration, position, duration);
                    }
                    mProgressHandler.sendEmptyMessageDelayed(VIDEO_PROGRESS, 1000);
                    break;
            }
        }
    };
}
