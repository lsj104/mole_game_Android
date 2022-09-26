package com.example.week4

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.week4.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {

    var audioPlay = MediaPlayer()

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)



        audioPlay  =MediaPlayer.create(this,R.raw.audio)
        audioPlay.isLooping = true
        audioPlay.start()







        val result = getSharedPreferences("result", Context.MODE_PRIVATE)
        val firstscore = result.getInt("Score_1st", 0)


        binding.second.setText("1st " + firstscore)


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

        if (audioPlay != null) {
            audioPlay.release()
            audioPlay != null
        }

    }










}


