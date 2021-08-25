package com.xxunghee.androidtvexample

import android.content.Context
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.PlaybackControlsRow.*

/**
 * Custom Glue
 *
 * Created by xxunghee on 2021-08-25
 */
class CustomGlue(private val mContext: Context?, playerAdapter: MediaPlayerAdapter) :
    PlaybackTransportControlGlue<MediaPlayerAdapter>(mContext, playerAdapter) {

    /**
     * Set Glue's Action
     *
     * 원래 함수에서는 재생/멈춤 버튼(>/||)만 추가함
     *
     * -> 재생/멈춤 버튼 외에도 빨리감기, 뒤로감기, 이전 영상, 다음 영상 버튼 추가
     *
     * @param primaryActionsAdapter Adapter에 추가한 순서대로 보인다.
     * 여기서는 `[|<]`  `[<<]`  `[>]`  `[>>]`  `[>|]` 순으로 보일 것
     */
    override fun onCreatePrimaryActions(primaryActionsAdapter: ArrayObjectAdapter?) {
        primaryActionsAdapter?.add(SkipPreviousAction(mContext))    // 이전 영상 버튼 (|<)
        primaryActionsAdapter?.add(RewindAction(mContext))          // 뒤로감기 버튼  (<<)
        primaryActionsAdapter?.add(PlayPauseAction(mContext))       // 재생/멈춤 버튼 (>/||)
        primaryActionsAdapter?.add(FastForwardAction(mContext))     // 빨리감기 버튼  (>>)
        primaryActionsAdapter?.add(SkipNextAction(mContext))        // 다음 영상 버튼 (>|)
    }

}