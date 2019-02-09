package br.com.fiap.a31scj.crud

import Config.RetrofitInit
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_editar_jogo.*
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarJogo: AppCompatActivity() {

    private lateinit var btn_remover: Button
    private lateinit var btn_editar: Button
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_jogo)

        this.btn_remover = findViewById<Button>(R.id.btn_remover)
        this.btn_editar = findViewById<Button>(R.id.btn_editar)

        this.setDados()

        this.btn_editar.setOnClickListener{
            this@EditarJogo.editar();
        }

        this.btn_remover.setOnClickListener{
            this@EditarJogo.remover();
        }
    }

    private fun setDados () {
        val titulo = getIntent().getStringExtra("nome")
        val descricao = getIntent().getStringExtra("descricao")
        this.id = getIntent().getStringExtra("id")

        this.pass.setText(titulo)
        this.descricao.setText(descricao)
    }

    private fun remover() {
        var call = RetrofitInit().jogoService().remove(this.id)
        call.enqueue(object: Callback<JSONStringer> {
            override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                Log.d("terminal", t.toString())
            }

            override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                this@EditarJogo.voltarLista()
            }

        })
    }

    private fun editar() {
        var call = RetrofitInit().jogoService().editar(this.id, this.pass.text.toString(), this.descricao.text.toString())
        call.enqueue(object: Callback<JSONStringer> {
            override fun onFailure(call: Call<JSONStringer>, t: Throwable) {
                Log.d("terminal", t.toString())
            }

            override fun onResponse(call: Call<JSONStringer>, response: Response<JSONStringer>) {
                this@EditarJogo.voltarLista()
            }

        })
    }

    private fun voltarLista() {
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
    }
}
