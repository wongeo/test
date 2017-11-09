package com.feng.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;

/**
 * Created by wj148202 on 2017/11/9.
 */

public class CustomVideoView extends FrameLayout {

    private static final int VIDEO_PROGRESS = 1;

    private IMediaController mMediaController;

    private NewTextureView mTextureView;

    private MediaPlayer mMediaPlayer;

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
        setBackground(new ColorDrawable(Color.BLACK));
        initMediaPlayer();
        initTextureView(mMediaPlayer);
    }

    private void initTextureView(MediaPlayer mediaPlayer) {
        mTextureView = new NewTextureView(getContext());
        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mTextureView, params);
    }

    public void play() {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
            mMediaPlayer.prepareAsync();


            mProgressHandler.removeMessages(VIDEO_PROGRESS);
            mProgressHandler.sendEmptyMessageDelayed(VIDEO_PROGRESS, 1000);
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        toggleMediaControlsVisiblity();
        return false;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        toggleMediaControlsVisiblity();
        return false;
    }


    private void toggleMediaControlsVisiblity() {
        if (mMediaController == null) {
            return;
        }
        if (mMediaController.isShowing()) {
            mMediaController.onHide();
        } else {
            mMediaController.onShow();
        }
    }


    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
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
                mp.start();
            }
        });
        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                mTextureView.setVideoSize(width, height);
            }
        });
    }

    public void setMediaController(IMediaController mediaController) {
        mMediaController = mediaController;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        addView((View) mMediaController, params);
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


    private Handler mProgressHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VIDEO_PROGRESS:
                    if (mMediaController != null && mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        long position = mMediaPlayer.getCurrentPosition();
                        long duration = mMediaPlayer.getDuration();
                        mMediaController.onPositionChange(position, duration);
                    }

                    mProgressHandler.sendEmptyMessageDelayed(VIDEO_PROGRESS, 1000);

                    break;
            }
        }
    };
}
