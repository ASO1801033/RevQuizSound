package jp.ac.asojuku.s.revquizsound

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
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

        soundPool =
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //OSのLOLLIPOPより古い端末のとき
                SoundPool(
                    2, //
                    AudioManager.STREAM_ALARM, //オーディオの種類
                    0) //音源品質、現在未使用。初期値の0を指定
            }else {
                //新しい端末のとき
                val audioAttributes = AudioAttributes
                    .Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                //オーディオ設定を使ってSoundPoolのインスタンスを作る
                SoundPool.Builder()
                    .setMaxStreams(1) //同時音源数(1)
                    .setAudioAttributes(audioAttributes)
                    .build() //ビルド
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
