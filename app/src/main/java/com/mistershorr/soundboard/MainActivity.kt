package com.mistershorr.soundboard

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var buttonA : Button
    lateinit var buttonBb : Button
    lateinit var buttonB : Button
    lateinit var buttonC : Button
    lateinit var buttonCs : Button
    lateinit var buttonD : Button
    lateinit var buttonDs : Button
    lateinit var buttonE : Button
    lateinit var buttonF: Button
    lateinit var buttonFs : Button
    lateinit var buttonG : Button
    lateinit var buttonGs : Button
    
    lateinit var noteList : List<Note>

    lateinit var soundPool : SoundPool
    var aNote = 0
    var bbNote = 0
    var bNote = 0
    var cNote = 0
    var csNote = 0
    var dNote = 0
    var dsNote = 0
    var eNote = 0
    var fNote = 0
    var fsNote = 0
    var gNote = 0
    var gsNote = 0
    var gbNote = 0

    companion object {
        val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()
        initializeSoundPool()
        setListeners()
        loadNotes()
    }

    private fun setListeners() {
        val soundBoardListener = SoundBoardListener()
        buttonA.setOnClickListener(soundBoardListener)
        buttonBb.setOnClickListener(soundBoardListener)
        buttonB.setOnClickListener(soundBoardListener)
        buttonC.setOnClickListener(soundBoardListener)
        buttonCs.setOnClickListener(soundBoardListener)
        buttonD.setOnClickListener(soundBoardListener)
        buttonDs.setOnClickListener(soundBoardListener)
        buttonE.setOnClickListener(soundBoardListener)
        buttonF.setOnClickListener(soundBoardListener)
        buttonFs.setOnClickListener(soundBoardListener)
        buttonG.setOnClickListener(soundBoardListener)
        buttonGs.setOnClickListener(soundBoardListener)
    }


    private fun wireWidgets() {
        buttonA = findViewById(R.id.button_main_a)
        buttonBb = findViewById(R.id.button_main_bb)
        buttonB = findViewById(R.id.button_main_b)
        buttonC = findViewById(R.id.button_main_c)
        buttonCs = findViewById(R.id.button_main_cs)
        buttonD = findViewById(R.id.button_main_d)
        buttonDs =  findViewById(R.id.button_main_ds)
        buttonE = findViewById(R.id.button_main_e)
        buttonF = findViewById(R.id.button_main_f)
        buttonFs = findViewById(R.id.button_main_fs)
        buttonG = findViewById(R.id.button_main_g)
        buttonGs = findViewById(R.id.button_main_gs)
    }

    private fun initializeSoundPool() {

        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
        aNote = soundPool.load(this, R.raw.scalea, 1)
        bbNote = soundPool.load(this, R.raw.scalebb, 1)
        bNote = soundPool.load(this, R.raw.scaleb, 1)
        cNote =  soundPool.load(this, R.raw.scalec, 1)
        csNote = soundPool.load(this, R.raw.scalecs, 1)
        dNote = soundPool.load(this, R.raw.scaled, 1)
        dsNote = soundPool.load(this, R.raw.scaleds, 1)
        eNote = soundPool.load(this, R.raw.scalee, 1)
        fNote = soundPool.load(this, R.raw.scalef, 1)
        fsNote = soundPool.load(this, R.raw.scalefs, 1)
        gNote = soundPool.load(this, R.raw.scaleg, 1)
        gsNote = soundPool.load(this, R.raw.scalegs, 1)
        gbNote = soundPool.load(this, R.raw.scalelowg,1)
    }

    private fun playNote(noteId : Int) {
        soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
    }

    private fun playSong(song: List<Note>) {
        for (item in noteList) {
            when(item.note) {

            }

        }
    }

    private fun loadNotes() {
        val inputStream = resources.openRawResource(R.raw.song)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val qType =
            object : TypeToken<List<Note>>() {}.type // data type of the list, questions.
        var noteList = gson.fromJson<List<Note>>(jsonString, qType)


        var list = ""
        for (item in noteList) {
            list += item.duration.toString() + " " + item.note + " "
        }

        var noteString: List<String> = list.split(" ")

        Log.d(TAG, "loadNotes: $noteString")

        for (i in noteString.indices) {
            noteList += (Note(noteString[i].toInt(),noteString[i+1]))
        }


    }

    private inner class SoundBoardListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button_main_a -> playNote(aNote)
                R.id.button_main_bb -> playNote(bbNote)
                R.id.button_main_b -> playNote(bNote)
                R.id.button_main_c -> playNote(cNote)
                R.id.button_main_cs -> playNote(csNote)
                R.id.button_main_d -> playNote(dNote)
                R.id.button_main_ds -> playNote(dsNote)
                R.id.button_main_e-> playNote(eNote)
                R.id.button_main_f -> playNote(fNote)
                R.id.button_main_fs -> playNote(fsNote)
                R.id.button_main_g -> playNote(gNote)
                R.id.button_main_gs -> playNote(gsNote)
            }
        }
    }


}