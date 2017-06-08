package me.frendy.video

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_detail_video.*
import me.frendy.xvideoview.XVideoView

class DetailActivity : AppCompatActivity(), XVideoView.VideoViewCallback {
    private var videoPath: String? = null
    private var videoTitle: String = ""

    private var cachedHeight: Int = 0
    private var isFullscreen: Boolean = false
    private var seekPosition: Int = 0
    private val SEEK_POSITION_KEY = "SEEK_POSITION_KEY"

    companion object {
        val PATH: String = "videoPath"
        val TITLE: String = "videoTitle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_video)

        videoPath = intent.getStringExtra("videoPath")
        videoTitle = intent.getStringExtra("videoTitle")

        xmediaController.attachActivity(this)
        xmediaController.setTitle(videoTitle)
        videoView.setMediaController(xmediaController)
        setVideoAreaSize()
        videoView.setVideoViewCallback(this)

        videoView.start()
    }

    override fun onPause() {
        super.onPause()
        if (videoView != null && videoView.isPlaying()) {
            seekPosition = videoView.getCurrentPosition()
            videoView.pause()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SEEK_POSITION_KEY, seekPosition)
    }

    override fun onRestoreInstanceState(outState: Bundle) {
        super.onRestoreInstanceState(outState)
        seekPosition = outState.getInt(SEEK_POSITION_KEY)
    }

    override fun onBackPressed() {
        if (this.isFullscreen) {
            videoView.setFullscreen(false)
        } else {
            super.onBackPressed()
        }
    }

    private fun setVideoAreaSize() {
        videoLayout.post({
            cachedHeight = (videoLayout.getWidth() * 405f / 720f).toInt()
            val videoLayoutParams = videoLayout.getLayoutParams()
            videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            videoLayoutParams.height = cachedHeight
            videoLayout.setLayoutParams(videoLayoutParams)
            videoView.setVideoPath(videoPath)
            videoView.requestFocus()
        })
    }

    override fun onScaleChange(isFullscreen: Boolean) {
        this.isFullscreen = isFullscreen
        if (isFullscreen) {
            val layoutParams = videoLayout.getLayoutParams()
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            videoLayout.setLayoutParams(layoutParams)
        } else {
            val layoutParams = videoLayout.getLayoutParams()
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = this.cachedHeight
            videoLayout.setLayoutParams(layoutParams)
        }
    }

    override fun onPause(mediaPlayer: MediaPlayer) {  }

    override fun onStart(mediaPlayer: MediaPlayer) {  }

    override fun onBufferingStart(mediaPlayer: MediaPlayer) {  }

    override fun onBufferingEnd(mediaPlayer: MediaPlayer) {  }
}
