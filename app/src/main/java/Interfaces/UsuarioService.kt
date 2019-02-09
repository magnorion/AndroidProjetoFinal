package Interfaces

import Models.Jogo
import Models.Usuario
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {

    @POST("/usuario/cadastro")
    @FormUrlEncoded
    fun novo(
            @Field("username") username: String,
            @Field("pass") pass: String): Call<JSONStringer>

    @POST("/usuario/login")
    @FormUrlEncoded
    fun login(
            @Field("username") username: String,
            @Field("pass") pass: String): Call<Usuario>
}