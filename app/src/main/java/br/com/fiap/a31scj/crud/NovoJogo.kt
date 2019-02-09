package br.com.fiap.a31scj.crud

import Config.RetrofitInit
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.widget.Button
import android.widget.EditText
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovoJogo : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var titulo: EditText
    private lateinit var descricao: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_jogo)

        this.button = findViewById<Button>(R.id.add)
        this.titulo = findViewById<EditText>(R.id.pass)
        this.descricao = findViewById<TextInputEditText>(R.id.descricao)

        this.button.setOnClickListener{
            if (this.titulo.text.toString() == "" || this.descricao.text.toString() == "") {
                Snackbar.make(it, "Por favor, informe todos os dados!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            } else {
                this@NovoJogo.novo(this.titulo.text.toString(),this.descricao.text.toString())
            }
        }
    }

    fun novo(titulo: String, descricao: String) {
        val call = RetrofitInit().jogoService().novo(titulo, descricao)

        call.enqueue(object: Callback<JSONStringer> {
            override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                Log.d("Terminal", t.toString())
                Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show()
            }

            override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                this@NovoJogo.voltalista()
            }

        })
    }

    fun voltalista() {
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
    }
}

