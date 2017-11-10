package com.feng.video.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.feng.media.VideoMediaPlayer;
import com.feng.media.IPlayStateCallback;

/**
 * Created by feng on 2017/11/9.
 */

public class CustomVideoView extends FrameLayout {

    private NewTextureView mTextureView;

    private VideoMediaPlayer mMediaPlayer;

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

    public void setPlayStateCallback(IPlayStateCallback callback) {
        mMediaPlayer.setPlayStateCallback(callback);
    }

    private void initMediaPlayer() {
        mMediaPlayer = new VideoMediaPlayer();
        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                mTextureView.setVideoSize(width, height);
            }
        });
    }

    public VideoMediaPlayer getMediaPlayer() {
        return mMediaPlayer;
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
}
