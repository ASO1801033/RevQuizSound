package jp.ac.asojuku.s.revquizsound

import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //出題くんボタンを押したらQuestActivityへ遷移する
        questButton.setOnClickListener {
            val intent = Intent(this, QuestActivity::class.java)
            startActivity(intent)
        }

        ansButton.setOnClickListener {
            soundPool.play(
                soundResId, 1.0f, 1.0f, 0, 0, 1.0f
            )
        }
    }

    override fun onResume() {
        super.onResume()

        soundPool = SoundPool(2, AudioManager.STREAM_ALARM, 0)
        soundResId = soundPool.load(this, R.raw.anssound, 1)
    }

    override fun onPause() {
        super.onPause()

        soundPool.release()
    }
}
