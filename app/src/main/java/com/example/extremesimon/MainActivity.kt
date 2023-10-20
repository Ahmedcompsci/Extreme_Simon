package com.example.extremesimon

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)


    }

    private var playerArrayList = ArrayList<Int>()
    private var iterNum = 0
    private var checkArrayList = ArrayList<Int>()
    private var score = 0
    private lateinit var gameTimer: CountDownTimer
    private var timerState = false
    private var fourButtonRadio = true

    private fun timerMain(){

        gameTimer = object : CountDownTimer(6000, 500) {

            val timeNum: TextView = findViewById(R.id.timeLeftValueText)
            override fun onTick(timeLeft: Long) {

                timeNum.text = (timeLeft / 1000).toString()

            }

            override fun onFinish() {
                setContentView(R.layout.game_over)

                val finalScore: TextView = findViewById(R.id.finalScoreValueText)

                finalScore.text = score.toString()
                gameTimer.cancel()
            }


        }


    }

    fun startGame(V: View) {

        setContentView(R.layout.game_main)

        val timeNum: TextView = findViewById(R.id.timeLeftValueText)
        val timeText: TextView = findViewById(R.id.timeLeftText)


        playerArrayList.clear()
        checkArrayList.clear()
        score = 0
        iterNum = 0

        timeNum.visibility = View.GONE
        timeText.visibility = View.GONE

        if(timerState){

            timeNum.visibility = View.VISIBLE
            timeText.visibility = View.VISIBLE

        }

        timerMain()
        sequenceCreation()


    }

    private fun sequenceCreation() {
        val simonNum: TextView = findViewById(R.id.currentNumValueText)
        val postScore: TextView = findViewById(R.id.scoreValueText)

        val playButton1: Button = findViewById(R.id.playButton1)
        val playButton2: Button = findViewById(R.id.playButton2)
        val playButton3: Button = findViewById(R.id.playButton3)
        val playButton4: Button = findViewById(R.id.playButton4)
        val playButton5: Button = findViewById(R.id.playButton5)
        val playButton6: Button = findViewById(R.id.playButton6)
        val playButton7: Button = findViewById(R.id.playButton7)
        val playButton8: Button = findViewById(R.id.playButton8)

        postScore.text = score.toString()
        var num = RandNum(4)

        if(!fourButtonRadio){num = RandNum(8)
        }

        val currentNum = num.rand()

        checkArrayList.add(currentNum)

        simonNum.visibility = View.VISIBLE

        simonNum.text = currentNum.toString()


        if(fourButtonRadio){

            playButton5.visibility = View.GONE
            playButton6.visibility = View.GONE
            playButton7.visibility = View.GONE
            playButton8.visibility = View.GONE

        }

        if(!fourButtonRadio){

            playButton5.visibility = View.VISIBLE
            playButton6.visibility = View.VISIBLE
            playButton7.visibility = View.VISIBLE
            playButton8.visibility = View.VISIBLE

        }




        playButton1.isEnabled = false
        playButton2.isEnabled = false
        playButton3.isEnabled = false
        playButton4.isEnabled = false
        playButton5.isEnabled = false
        playButton6.isEnabled = false
        playButton7.isEnabled = false
        playButton8.isEnabled = false

        Handler(Looper.getMainLooper())
            .postDelayed({
                simonNum.visibility = View.INVISIBLE; playButton1.isEnabled = true
                playButton2.isEnabled = true; playButton3.isEnabled = true
                playButton4.isEnabled = true; playButton5.isEnabled = true
                playButton6.isEnabled = true; playButton7.isEnabled = true
                playButton8.isEnabled = true; if(timerState){gameTimer.start()}
            }, 2000)


        iterNum = 0
    }

    class RandNum(private val numButtons: Int) {

        fun rand(): Int {

            return (1..numButtons).random()

        }

    }

    fun clicked(V: View){

        when(V.id){

            R.id.playButton1 -> playerArrayList.add(1)
            R.id.playButton2 -> playerArrayList.add(2)
            R.id.playButton3 -> playerArrayList.add(3)
            R.id.playButton4 -> playerArrayList.add(4)
            R.id.playButton5 -> playerArrayList.add(5)
            R.id.playButton6 -> playerArrayList.add(6)
            R.id.playButton7 -> playerArrayList.add(7)
            R.id.playButton8 -> playerArrayList.add(8)



        }

        if (playerArrayList[iterNum] == checkArrayList[iterNum]) {

            if (playerArrayList.size == checkArrayList.size) {

                if (playerArrayList == checkArrayList) {

                    score += 1
                    playerArrayList.clear()
                    gameTimer.cancel()
                    sequenceCreation()


                } else {

                    gameTimer.cancel()
                    setContentView(R.layout.game_over)

                    val finalScore: TextView = findViewById(R.id.finalScoreValueText)

                    finalScore.text = score.toString()

                }
            } else {

                iterNum += 1

            }

        } else {

            gameTimer.cancel()
            setContentView(R.layout.game_over)

            val finalScore: TextView = findViewById(R.id.finalScoreValueText)

            finalScore.text = score.toString()

        }

    }

    fun backToMain(V: View){

        setContentView(R.layout.main_menu)

    }

    fun toPreferences(V: View){

        setContentView(R.layout.preferences)

        val timerSwitch: Switch = findViewById(R.id.timerSwitch)

        val radioFour: RadioButton = findViewById(R.id.fourButtonRadioButton)
        val radioEight: RadioButton = findViewById(R.id.eightButtonRadioButton)

        if(timerState)
        {

            timerSwitch.isChecked = true

        }

        if(!fourButtonRadio)
        {

            radioEight.isChecked = true

        }
        if(fourButtonRadio)
        {

            radioFour.isChecked = true

        }

    }

    fun switchClick(V: View){

        val timerSwitch: Switch = findViewById(R.id.timerSwitch)

        timerState = timerSwitch.isChecked

    }


    fun radioFourButtonClick(V: View){

        fourButtonRadio = true


    }

    fun radioEightButtonClick(V: View){

        fourButtonRadio = false

    }
}

