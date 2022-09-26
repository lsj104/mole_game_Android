package com.example.week4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import android.os.*
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.week4.databinding.ActivityMainBinding
import java.lang.Math.random
import java.lang.StrictMath.random
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    val handler = Handler(Looper.getMainLooper())
    var imageArray = ArrayList<ImageView>()
    var runnable: Runnable = Runnable { }
    val ImageDatas = listOf(R.drawable.dududown, R.drawable.duduup)
    var timeArray = ArrayList<ImageView>()
    var audioPlay = MediaPlayer()
    var audioend = MediaPlayer()









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        audioPlay  =MediaPlayer.create(this,R.raw.audio)
        audioPlay.isLooping = true
        audioPlay.start()

        audioend =MediaPlayer.create(this,R.raw.audiostart)




        val duduup = this.resources.getDrawable(R.drawable.duduup)
        val dududown = this.resources.getDrawable(R.drawable.dududown)
        val Bitmapduduup= (duduup as BitmapDrawable).bitmap
        val Bitmapdududown = (dududown as BitmapDrawable).bitmap
        var score = 0
        var upscore = 0
        var downscore = 0






        imageArray = arrayListOf(binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5,
                binding.imageView6, binding.imageView7, binding.imageView8, binding.imageView9)
        timeArray = arrayListOf(binding.timeover,binding.restart,binding.exit)





        object : CountDownTimer(30000, 1000) {
            override fun onFinish() {

                binding.time.text = "Time over"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                for (image1 in timeArray){
                    audioPlay.pause()
                    audioend.start()
                    image1.visibility = View.VISIBLE
                }


            }

            override fun onTick(p0: Long) {
                binding.time.text = "Time: " + p0 / 1000
            }
        }.start()


        runnable = object : Runnable {
            override fun run() {

                var random = imageArray.random()
                random.setImageResource(ImageDatas.random())
                random.isClickable = true


                handler.postDelayed(runnable, 80)


            }


        }

        handler.post(runnable)


            for (i in 0..8) {
                imageArray[i].setOnClickListener {
                    var temp1 = imageArray[i].getDrawable()
                    var tmpBitmap1 = (temp1 as BitmapDrawable).bitmap
                    if (tmpBitmap1.equals(Bitmapduduup)) {
                        score += 10
                        upscore += 1
                        imageArray[i].setImageResource(R.drawable.hitduduup)
                        imageArray[i].isClickable = false
                        //imageArray[i].visibility = View.INVISIBLE
                        binding.scoreText.text = "Score: " + score
                        binding.scoreup.text = "Catched: " + upscore

                            val t1 = Toast.makeText(this, "+10점", Toast.LENGTH_SHORT)
                            t1.setGravity(Gravity.BOTTOM, Gravity.CENTER, Gravity.CENTER_VERTICAL)
                            t1.show()
                        



                    } else if (tmpBitmap1.equals(Bitmapdududown)) {
                        score -= 10
                        downscore += 1
                        imageArray[i].setImageResource(R.drawable.hitdududown)
                        imageArray[i].isClickable = false
                       // imageArray[i].visibility = View.INVISIBLE
                        binding.scoreText.text = "Score: " + score
                        binding.scoredown.text = "Catched: " + downscore
                        val t2 =  Toast.makeText(this, "-10점",Toast.LENGTH_SHORT)
                            t2.setGravity(Gravity.BOTTOM, Gravity.CENTER,Gravity.CENTER_VERTICAL)
                            t2.show()
                    }

                    val result = getSharedPreferences("result", Context.MODE_PRIVATE)
                    val editor = result.edit()

                    var firstscore = result.getInt("firstscore", 0)
                    var secondscore = result.getInt("secondscore", 0)
                    var thirdscore = result.getInt("thirdscore", 0)


                    if (thirdscore < score) {
                        if(secondscore <score){
                            if (firstscore < score){
                                editor.putInt("Score_1st", score)
                                editor.apply()

                            }else{
                                editor.putInt("Score_2st", score)
                                editor.apply()

                            }
                        }else{
                            editor.putInt("Score_3st", score)
                            editor.apply()

                        }
                    }


                }



            }


        val result = getSharedPreferences("result", Context.MODE_PRIVATE)
        val firstscore = result.getInt("Score_1st", 0)


        binding.scoremain.setText("1st " + firstscore)


        binding.restart.setOnClickListener {
            finishAffinity()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            System.exit(0)

        }

        binding.exit.setOnClickListener {




            System.exit(0)

        }












    }




    override fun onResume() {
        super.onResume()
        audioPlay.start()


    }
    override fun onPause() {
        super.onPause()
        audioPlay.pause()
        audioend.pause()
    }
    override fun onDestroy() {
        super.onDestroy()

        if (audioPlay != null) {
            audioPlay.release()
            audioPlay != null
        }
        if (audioend != null) {
            audioend.release()
            audioend != null
        }
    }


}





