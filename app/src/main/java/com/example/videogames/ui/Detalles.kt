package com.example.videogames.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.videogames.R
import com.example.videogames.data.remote.AvatarApi
import com.example.videogames.data.remote.model.AvatarDetailDto
import com.example.videogames.databinding.ActivityDetallesBinding
import com.example.videogames.util.Constants
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

        Log.d(Constants.LOGTAG, "Id recibido $id")

        //Generamos una instancia a retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val avatarApi = retrofit.create(AvatarApi::class.java)

        val call: Call<AvatarDetailDto> = avatarApi.getAvatarDetail(id!!)

        call.enqueue(object: Callback<AvatarDetailDto>{
            override fun onResponse(p0: Call<AvatarDetailDto>, response: Response<AvatarDetailDto>) {
                binding.pbLoading.visibility = View.INVISIBLE

                binding.apply {
                    binding.tvName.text = response.body()?.name

                    binding.tvProffesion.text = binding.root.context.getString(R.string.profession, response.body()?.profession?: "Unknown")
                   // binding.tvAffiliation.text = binding.root.context.getString(R.string.affiliation, response.body()?.affiliation?: "Unknown")
                    binding.tvGender.text = binding.root.context.getString(R.string.gender, response.body()?.gender?: "Unknown")
                    binding.tvPosition.text = binding.root.context.getString(R.string.position, response.body()?.position?: "Unknown")
                    val alliesText = response.body()?.allies?.filter { it.isNotBlank() }?.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Allies Unknown"
                    binding.tvAllies.text = binding.root.context.getString(R.string.allies, alliesText)
                    val enemiesText = response.body()?.enemies?.filter { it.isNotBlank() }?.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: "Enemies Unknown"
                    binding.tvEnemies.text = binding.root.context.getString(R.string.enemies, enemiesText)


                    //binding.tvAfiliacion.text = itemView.context.getString(R.string.affiliation,
                    //            avatar.affiliation?: "None")
                    Glide.with(this@Detalles)
                        .load(response.body()?.image)
                        .placeholder(R.drawable.loading_anim)
                        .error(R.drawable.nouser)
                        .into(ivImage)
                }

            }

            override fun onFailure(p0: Call<AvatarDetailDto>, p1: Throwable) {
                //Manejamos error de conexion

                binding.pbLoading.visibility = View.INVISIBLE

                Toast.makeText(this@Detalles,
                    "No hay conexión disponible",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }
}