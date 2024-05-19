package com.example.avatarapi.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avatarapi.data.remote.AvatarApi
import com.example.avatarapi.data.remote.model.AvatarDato
import com.example.avatarapi.databinding.ActivityMainBinding
import com.example.avatarapi.util.Constants
import com.example.avatarapi.util.mensaje
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val avatarApi = retrofit.create(AvatarApi::class.java)

        val call:  Call<List<AvatarDato>> = avatarApi.getAvatars("api/v1/characters?perPage=497")
        call.enqueue(object : Callback<List<AvatarDato>> {
            override fun onResponse(p0: Call<List<AvatarDato>>, response: Response<List<AvatarDato>>) {
                binding.pbLoading.visibility = View.INVISIBLE

                Log.d(Constants.LOGTAG, "Respuesta recibida: ${response.body()}")

                response.body()?.let { avatars ->
                    val miAdapter = AvatarAdapter(avatars){ avatar->
                        avatar.id?.let { id ->
                            val bundle = bundleOf(
                                "id" to id
                            )
                            val intent = Intent(this@MainActivity, Detalles::class.java)
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                    }
                    binding.rvavatars.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = miAdapter
                    }
                }
            }

            override fun onFailure(p0: Call<List<AvatarDato>>, p1: Throwable) {
                binding.pbLoading.visibility = View.INVISIBLE
                mensaje("No hay conexión disponible")
            }

        })
    }
}