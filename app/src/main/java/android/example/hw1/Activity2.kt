package android.example.hw1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_2.*


class MyTimer(val textView: TextView, val button: Button) : CountDownTimer(1000000, 1000) {
    var time: Long = 0
    var started: Boolean = false
    private var s1: String = ""
    private var s2: String = ""
    private var s3: String = ""

    override fun onFinish() {
        time = 0
        button.text = "Start"
        textView.text = "тысяча"
        started = false
        cancel()
    }

    override fun onTick(millisUntilFinished: Long) {
        time += 1
        if (time == 1000L)
            this.onFinish()
        else {
            when (time / 100) {
                0L -> s1 = ""
                1L -> s1 = "сто"
                2L -> s1 = "двести"
                3L -> s1 = "триста"
                4L -> s1 = "четыреста"
                5L -> s1 = "пятьсот"
                6L -> s1 = "шестьсот"
                7L -> s1 = "семьсот"
                8L -> s1 = "восемьсот"
                9L -> s1 = "девятьсот"

            }
            if (!(time % 100 in 11..19)) {
                when (time / 10 % 10) {
                    0L -> s2 = ""
                    1L -> s2 = "десять"
                    2L -> s2 = "двадцать"
                    3L -> s2 = "тридцать"
                    4L -> s2 = "сорок"
                    5L -> s2 = "пятьдесят"
                    6L -> s2 = "шестьдесят"
                    7L -> s2 = "семьдесят"
                    8L -> s2 = "восемьдесят"
                    9L -> s2 = "девяносто"

                }
                when (time % 10) {
                    0L -> s3 = ""
                    1L -> s3 = "один"
                    2L -> s3 = "два"
                    3L -> s3 = "три"
                    4L -> s3 = "четыре"
                    5L -> s3 = "пять"
                    6L -> s3 = "шесть"
                    7L -> s3 = "семь"
                    8L -> s3 = "восемь"
                    9L -> s3 = "девять"
                }
            } else {
                s3 = ""
                when (time % 100) {
                    11L -> s2 = "одиннадцать"
                    12L -> s2 = "двенадцдцать"
                    13L -> s2 = "тринадцать"
                    14L -> s2 = "четырнадцать"
                    15L -> s2 = "пятнадцать"
                    16L -> s2 = "шестнадцать"
                    17L -> s2 = "семнадцать"
                    18L -> s2 = "восемнадцать"
                    19L -> s2 = "девятнадцать"


                }
            }
            textView.text = "$s1 $s2 $s3"
        }
    }
}

class Activity2 : AppCompatActivity() {
    lateinit var timer: MyTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        timer = MyTimer(textView, button)
        if (savedInstanceState != null) {
            timer.time = savedInstanceState.getLong("time")
            textView.text = savedInstanceState.getString("timeText")
            timer.started = savedInstanceState.getBoolean("started")
            button.text = savedInstanceState.getString("buttonText")
        }
        button.setOnClickListener {
            timer.started = !timer.started
            if (timer.started) {
                timer.time -= 1L
                button.text = "Stop"
                timer.start()
            } else {
                button.text = "Start"
                timer.started = false
                timer.cancel()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (timer.started) {
            timer.time -= 1L
            timer.cancel()
            timer.start()
        }
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong("time", timer.time)
        outState.putString("timeText", textView.text.toString())
        outState.putBoolean("started", timer.started)
        outState.putString("buttonText", button.text.toString())
        super.onSaveInstanceState(outState)
    }


}
