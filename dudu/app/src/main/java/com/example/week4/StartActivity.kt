package com.example.week4

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week4.databinding.ActivityMainBinding
import com.example.week4.databinding.ActivityStartBinding


class StartActivity : AppCompatActivity() {

    var audioPlay = MediaPlayer()

   private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioPlay =MediaPlayer.create(this,R.raw.audiostart)
        audioPlay.isLooping=true
        audioPlay.start()


        binding.btnstart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.btninfor.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)

        }

        binding.rank.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)

        }

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