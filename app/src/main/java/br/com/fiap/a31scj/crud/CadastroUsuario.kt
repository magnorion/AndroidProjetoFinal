package br.com.fiap.a31scj.crud

import Config.RetrofitInit
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Button
import android.widget.EditText
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroUsuario : AppCompatActivity() {

    private lateinit var etUsername : EditText
    private lateinit var etPass : EditText
    private lateinit var btnCadastrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)

        etUsername = findViewById(R.id.etUsername)
        etPass = findViewById(R.id.etPass)
        btnCadastrar = findViewById(R.id.btnNovoUsuario)


        btnCadastrar.setOnClickListener {
            if (etUsername.text.toString().isNotEmpty() && etPass.text.toString().isNotEmpty()){
                this.novo(etUsername.text.toString(), etPass.text.toString())
            }
        }
    }


    fun novo(username: String, pass: String) {
        val call = RetrofitInit().usuarioService().novo(username, pass)

        call.enqueue(object: Callback<JSONStringer> {
            override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                Log.d("Terminal", t.toString())
                Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show()
            }

            override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                Snackbar.make(findViewById(android.R.id.content), "Usuario cadastrado com sucesso!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show()
                this@CadastroUsuario.voltar()
            }

        })
    }

    fun voltar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
