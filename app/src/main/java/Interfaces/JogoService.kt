package Interfaces

import Models.Jogo
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.http.*

interface JogoService {
    @GET("/")
    fun lista(): Call<List<Jogo>>

    @POST("/jogos/novo")
    @FormUrlEncoded
    fun novo(
            @Field("titulo") titulo: String,
            @Field("descricao") descricao: String): Call<JSONStringer>

    @POST("/jogos/remove")
    @FormUrlEncoded
    fun remove(@Field("id") id: String): Call<JSONStringer>

    @POST("/jogos/editar")
    @FormUrlEncoded
    fun editar(
            @Field("id") id: String,
            @Field("titulo") titulo: String,
            @Field("descricao") descricao: String): Call<JSONStringer>
}