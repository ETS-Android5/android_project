package com.example.camera

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.camera.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if (result.resultCode == RESULT_OK) {

            //    binding.image.setImageBitmap(BitmapFactory.decodeFile(photoFile.absolutePath))

                Glide.with(this).load(photoFile).into(binding.image) // call .centerCrop() .circleCrop() before .into()
            } else {
                Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_LONG).show()
            }

        }



        binding.camera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager)!=null){
                val dir = externalCacheDir // getExternalFilesDir(Environment.DIRECTORY.PICTURES)
                val file = File.createTempFile("photo_", ".jpg", dir)
                val uri = FileProvider.getUriForFile(this, "$packageName.provider",file)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)


                startForResult.launch(intent)
                photoFile = file

            }
        }
    }

}