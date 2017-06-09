package com.feng.media;

public class PlayerState {
	public static final int STOPED = 0;
	public static final int PREPARING = 1;
	public static final int Prepared = 2;
	public static final int Playing = 3;
	public static final int Paused = 4;
	public static final int Error = 5;
	/**
	 * playerbag不为空，但没有初始化
	 */
	public static final int VisualOnlyPreparing = 6;
	public static final int Seeking = 7;
	public static final int SeekPrepared = 8;
	public static final int SeekCompleted = 9;
	public static final int Buffering = 10;

	public static boolean isPreparingOrPlayingState(int playerState) {
		return playerState == PREPARING || playerState == Prepared || playerState == Playing || playerState == Seeking || playerState == SeekPrepared;
	}

	public static boolean isPreparingState(int playerState) {
		return playerState == PREPARING;
	}

	public static boolean isLoadingState(int playerState) {
		return playerState == PREPARING || playerState == Seeking || playerState == SeekPrepared || playerState == SeekCompleted;
	}

	public static boolean canReportDuration(int playerState) {
		return playerState == PlayerState.Playing;
	}

	public static boolean canSeek(int playerState) {
		return playerState == PlayerState.Playing || playerState == PlayerState.Paused || playerState == PlayerState.Seeking || playerState == PlayerState.SeekPrepared || playerState == PlayerState.SeekCompleted || playerState == PlayerState.Prepared || playerState == PlayerState.PREPARING;
	}

	public static boolean isDurstionSeeking(int playerState) {
		return playerState == PlayerState.Seeking || playerState == PlayerState.SeekPrepared || playerState == PlayerState.SeekCompleted;
	}
}
