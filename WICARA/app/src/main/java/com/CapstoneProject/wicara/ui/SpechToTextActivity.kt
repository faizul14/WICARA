package com.CapstoneProject.wicara.ui

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.CapstoneProject.wicara.databinding.ActivitySpechToTextBinding
import com.CapstoneProject.wicara.viewmodel.VIewModelSpechToText
import java.lang.Exception

class SpechToTextActivity : AppCompatActivity() {
    private var binding : ActivitySpechToTextBinding? = null
    private lateinit var viewModel : VIewModelSpechToText
    private lateinit var mr : MediaRecorder

    private var stat : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpechToTextBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()
        //for media record
        mr = MediaRecorder()
        var path = Environment.getExternalStorageDirectory().toString() + "/Aku.wav"



        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[VIewModelSpechToText::class.java]
        viewModel.getTranslate()

        binding!!.imgbtnMic.setOnClickListener {
            if (stat == 0){
                stat = 1
                Toast.makeText(this, "ini lagi rekam suara", Toast.LENGTH_SHORT).show()
                try {
                    mr.setAudioSource(MediaRecorder.AudioSource.MIC)
                    mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP) //perlu di ubah
                    mr.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                    mr.setOutputFile(path)
                    mr.prepare()
                }catch (e : Exception){
                    Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
                }
                mr.start()
            }else{
                Toast.makeText(this, "di stop", Toast.LENGTH_SHORT).show()
                stat = 0
                mr.stop()
                viewModel.getTranslate()
            }

        }

        viewModel.getTranslate()
//        viewModel.getTest()

        viewModel.translate.observe(this, {data ->
            if (data != null) binding!!.textTransalte.setText(data.toString()) else "Hasil Translate"
        })
        permision()


    }

    private fun permision(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE), 111)

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}