package Config

import Interfaces.JogoService
import Interfaces.UsuarioService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInit {
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://android-server-app.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun jogoService () : JogoService {
        return retrofit.create(JogoService::class.java)
    }

    fun usuarioService() : UsuarioService {
        return retrofit.create((UsuarioService::class.java))
    }
}