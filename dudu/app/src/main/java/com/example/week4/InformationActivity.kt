package com.example.week4

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InformationActivity : AppCompatActivity() {
    var audioPlay = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)


        audioPlay =MediaPlayer.create(this,R.raw.audiostart)
        audioPlay.isLooping=true
        audioPlay.start()
    }

    override fun onResume() {
        super.onResume()
        audioPlay.start()
    }
    override fun onPause() {
        super.onPause()
        audioPlay.pause()
    }
    override fun onDestroy() {
        super.onDestroy()

        if(audioPlay!=null){
            audioPlay.release()
            audioPlay!=null
        }
    }
}