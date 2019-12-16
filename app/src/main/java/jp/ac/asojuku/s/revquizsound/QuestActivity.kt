package jp.ac.asojuku.s.revquizsound

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_quest.*

class QuestActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var misssound = 0
    private var corsound = 0
    private var quizsound = 0
    private var clapsound = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        //解答くんボタンを押したらQuestActivityへ遷移する
        answerButton.setOnClickListener { finish() }

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

        //misssoundをロードしておく
        misssound = soundPool.load(this, R.raw.misssound, 1)

        //間違いボタンが押された時
        missButton.setOnClickListener{
            //play(ロードしたID, 左音量, 右音量, 優先度, ループ, 再生速度)
            soundPool.play(misssound, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        //corsoundをロードしておく
        corsound = soundPool.load(this, R.raw.corsound, 1)

        //正解ボタンが押された時
        corButton.setOnClickListener {
            //play(ロードしたID, 左音量, 右音量, 優先度, ループ, 再生速度)
            soundPool.play(corsound, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        //quizsoundをロードしておく
        quizsound = soundPool.load(this, R.raw.quizsound, 1)

        //出題ボタンが押された時
        quizButton.setOnClickListener {
            //play(ロードしたID, 左音量, 右音量, 優先度, ループ, 再生速度)
            soundPool.play(quizsound, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        //clapsoundをロードしておく
        clapsound = soundPool.load(this, R.raw.clapsound, 1)

        //拍手ボタンが押された時
        clapButton.setOnClickListener {
            //play(ロードしたID, 左音量, 右音量, 優先度, ループ, 再生速度)
            soundPool.play(clapsound, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}
