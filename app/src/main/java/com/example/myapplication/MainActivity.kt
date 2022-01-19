package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lotteryButton : LottieAnimationView = findViewById(R.id.lotteryButton)
        val number1 : TextView = findViewById(R.id.number1)
        val number2 : TextView = findViewById(R.id.number2)
        val number3 : TextView = findViewById(R.id.number3)
        val number4 : TextView = findViewById(R.id.number4)
        val number5 : TextView = findViewById(R.id.number5)
        val number6 : TextView = findViewById(R.id.number6)

        val lotteryNumbers = arrayListOf(number1, number2, number3, number4, number5, number6)

        val countDownTimer = object: CountDownTimer(3000, 100){
            override fun onFinish() {
            }

            override fun onTick(millisUntilFinished: Long) {
                lotteryNumbers.forEach{
                    val randomNumber = (Math.random() * 45 + 1).toInt() //1~45
                    it.text = "$randomNumber" // number 각각 개별 객체 it
                }
            }
        }

        lotteryButton.setOnClickListener{
            if(lotteryButton.isAnimating){
                lotteryButton.cancelAnimation()
                countDownTimer.cancel()
            }
            else{
                lotteryButton.playAnimation()
                countDownTimer.start()
            }

        }
    }
}