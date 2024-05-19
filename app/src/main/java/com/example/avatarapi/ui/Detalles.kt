package com.example.avatarapi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.avatarapi.R
import com.example.avatarapi.data.remote.AvatarApi
import com.example.avatarapi.data.remote.model.AvatarDetail
import com.example.avatarapi.databinding.ActivityDetallesBinding
import com.example.avatarapi.util.Constants
import com.example.avatarapi.util.getStringFormatted
import com.example.avatarapi.util.loadImage
import com.example.avatarapi.util.mensaje
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detalles : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "")

        if (id.isNullOrEmpty()) {
            Log.e("Detalles", getString(R.string.invalid_id))
        }

        Log.d(Constants.LOGTAG, "Id recibido $id")

        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val avatarApi = retrofit.create(AvatarApi::class.java)

        val call: Call<AvatarDetail> = avatarApi.getAvatarDetail(id!!)

        call.enqueue(object: Callback<AvatarDetail>{
            override fun onResponse(p0: Call<AvatarDetail>, response: Response<AvatarDetail>) {
                binding.pbLoading.visibility = View.INVISIBLE
                val context = binding.root.context
                val b = response.body()

                if (response.isSuccessful){
                    binding.apply {
                        tvAvName.text = b?.name
                        tvAvProffesion.text = context.getStringFormatted(R.string.profession, b?.profession)
                        tvAvGender.text = context.getStringFormatted(R.string.gender, b?.gender)
                        tvAvPosition.text = context.getStringFormatted(R.string.position, b?.position)

                        val allies = b?.allies?.filter { it.isNotBlank() }?.joinToString(", ") ?: "Allies Unknown"
                        tvAvAllies.text = context.getStringFormatted(R.string.allies, allies)

                        val enemies = b?.enemies?.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Enemies Unknown"
                        tvAvEnemies.text = context.getStringFormatted(R.string.enemies, enemies)

                        ivImage.loadImage(b?.image)
                    }
                }else{
                    Log.e("Detalles", getString(R.string.error_server))
                }
            }

            override fun onFailure(p0: Call<AvatarDetail>, p1: Throwable) {
                binding.pbLoading.visibility = View.INVISIBLE
                mensaje("No hay conexión disponible")
            }
        })

    }
}