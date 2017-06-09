package com.feng.media;

public interface IPlayStateCallback {

	void onMediaError(int what, int extra);

	void onBufferingProgressChanged(int percent);

	void onPlayerStateChanged(int newState);

	void onPrepared(int duration);

	void onPlayPositionChanged(float percent, long position, long duration);

	void onCompletion();
}
