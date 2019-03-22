package android.example.hw1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {
    private val timer = object : CountDownTimer(2000, 2000) {
        override fun onFinish() {
            val intent = Intent(this@MainActivity, Activity2::class.java)
            startActivity(intent)
            finish()
        }

        override fun onTick(millisUntilFinished: Long) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        timer.start()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

}
