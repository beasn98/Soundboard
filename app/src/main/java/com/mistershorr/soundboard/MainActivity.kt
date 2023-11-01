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
import com.mistershorr.soundboard.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap


class MainActivity : AppCompatActivity() {

    lateinit var noteList : List<Note>

    lateinit var soundPool : SoundPool
    var aNote = 0
    var bbNote = 0
    var highbbNote = 0
    var bNote = 0
    var highbNote = 0
    var cNote = 0
    var csNote = 0
    var highcsNote = 0
    var dNote = 0
    var dsNote = 0
    var highdsNote = 0
    var eNote = 0
    var higheNote = 0
    var fNote = 0
    var highfNote = 0
    var fsNote = 0
    var highfsNote = 0
    var gNote = 0
    var gsNote = 0
    var highgsNote = 0
    var lowgNote = 0


    var noteMap = HashMap<String, Int>()


    //instance var for viewbinding
    private lateinit var binding: ActivityMainBinding


    companion object {
        val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //define the binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initializeSoundPool()
        loadNotes()
        setListeners()
    }

    private fun setListeners() {
        val soundBoardListener = SoundBoardListener()
        binding.buttonMainA.setOnClickListener(soundBoardListener)
        binding.buttonMainBb.setOnClickListener(soundBoardListener)
        binding.buttonMainB.setOnClickListener(soundBoardListener)
        binding.buttonMainC.setOnClickListener(soundBoardListener)
        binding.buttonMainCs.setOnClickListener(soundBoardListener)
        binding.buttonMainD.setOnClickListener(soundBoardListener)
        binding.buttonMainDs.setOnClickListener(soundBoardListener)
        binding.buttonMainE.setOnClickListener(soundBoardListener)
        binding.buttonMainF.setOnClickListener(soundBoardListener)
        binding.buttonMainFs.setOnClickListener(soundBoardListener)
        binding.buttonMainG.setOnClickListener(soundBoardListener)
        binding.buttonMainGs.setOnClickListener(soundBoardListener)
        binding.buttonMainPlaySong.setOnClickListener {
            GlobalScope.launch {
                playSong(noteList)
            }
        }
    }

    private fun initializeSoundPool() {
        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
        aNote = soundPool.load(this, R.raw.scalea, 1)
        bbNote = soundPool.load(this, R.raw.scalebb, 1)
        highbbNote = soundPool.load(this, R.raw.scalehighbb, 1)
        bNote = soundPool.load(this, R.raw.scaleb, 1)
        highbNote = soundPool.load(this, R.raw.scalehighb, 1)
        cNote =  soundPool.load(this, R.raw.scalec, 1)
        csNote = soundPool.load(this, R.raw.scalecs, 1)
        highcsNote = soundPool.load(this, R.raw.scalehighcs, 1)
        dNote = soundPool.load(this, R.raw.scaled, 1)
        dsNote = soundPool.load(this, R.raw.scaleds, 1)
        highdsNote = soundPool.load(this, R.raw.scalehighds, 1)
        eNote = soundPool.load(this, R.raw.scalee, 1)
        higheNote = soundPool.load(this, R.raw.scalehighe, 1)
        fNote = soundPool.load(this, R.raw.scalef, 1)
        highfNote = soundPool.load(this, R.raw.scalehighf, 1)
        fsNote = soundPool.load(this, R.raw.scalefs, 1)
        highfsNote = soundPool.load(this, R.raw.scalehighfs, 1)
        gNote = soundPool.load(this, R.raw.scaleg, 1)
        gsNote = soundPool.load(this, R.raw.scalegs, 1)
        highgsNote = soundPool.load(this, R.raw.scalehighgs, 1)
        lowgNote = soundPool.load(this, R.raw.scalelowg,1)

        //Map usage
        noteMap.put("A",aNote)
        //Kotlin lets you use array-like assignment
        noteMap["Bb"] = bbNote
        noteMap["hBb"] = highbbNote
        noteMap["B"] = bNote
        noteMap["hB"] = highbNote
        noteMap["C"] = cNote
        noteMap["Cs"] = csNote
        noteMap["hCs"] = highcsNote
        noteMap["D"] = dNote
        noteMap["Ds"] = dsNote
        noteMap["hDs"] = highdsNote
        noteMap["E"] = eNote
        noteMap["hE"] = higheNote
        noteMap["F"] = fNote
        noteMap["hF"] = highfNote
        noteMap["Fs"] = fsNote
        noteMap["hFs"] = highfsNote
        noteMap["G"] = gNote
        noteMap["Gs"] = gsNote
        noteMap["hGs"] = highgsNote
        noteMap["lG"] = lowgNote
    }


    private fun playNote(note : String) {
        //?: is the elvis operator. Lets you use a default value if null

        playNote(noteMap[note] ?: 0)
    }

    private fun playNote(noteId : Int) {
        soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
    }

    private fun playChord() {
        //playNote()
    }

    private suspend fun playSong(song: List<Note>) {
        for (item in song) {

            playNote(item.note)
            delay(item.duration)
        }
    }

//    private fun delay(time: Long) {
//        try {
//            Thread.sleep(time)
//        } catch(e: InterruptedException) {
//            e.printStackTrace()
//        }
//    }

    private fun loadNotes() {
        val inputStream = resources.openRawResource(R.raw.song1)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val qType =
            object : TypeToken<List<Note>>() {}.type // data type of the list, questions.
        noteList = gson.fromJson<List<Note>>(jsonString, qType)


        Log.d(TAG, "loadNotes: noteList: $noteList")
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