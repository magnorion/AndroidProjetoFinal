package br.com.fiap.a31scj.crud

import Config.RetrofitInit
import Models.Usuario
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import br.com.cardote.fichadetreino.helpers.PreferencesHelper
import org.json.JSONObject
import org.json.JSONStringer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var cbStay: CheckBox
    private lateinit var btnLogin: Button
    private lateinit var btnSobre: Button
    private lateinit var btnCadastro: Button
    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var prefHelper : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefHelper = PreferencesHelper(this)

        this.btnLogin = findViewById<Button>(R.id.btnLogin)
        this.btnCadastro = findViewById<Button>(R.id.btnCadastro)
        this.btnSobre = findViewById<Button>(R.id.btnSobre)
        this.user = findViewById<EditText>(R.id.user)
        this.pass = findViewById<EditText>(R.id.pass)
        this.cbStay = findViewById<CheckBox>(R.id.cbStayid)

        this.btnLogin.setOnClickListener {
            this.login()
        }

        this.btnCadastro.setOnClickListener {
            this@MainActivity.openView(CadastroUsuario())
        }

        cbStay.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                prefHelper.stayConnected = true

            }
        }

        btnSobre.setOnClickListener {
            openView(SobreActivity())
        }
    }

    fun login () {
        val user: String = this.user.text.toString()
        val pass: String = this.pass.text.toString()

        if(user.isNotEmpty() && pass.isNotEmpty()) {
            val call = RetrofitInit().usuarioService().login(user, pass)

            call.enqueue(object: Callback<Usuario> {
                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("Terminal", t.toString())
                    Snackbar.make(findViewById(android.R.id.content), call.toString(), Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show()
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                    response?.body()?.let {
                        val usuario: Usuario = it


                        if(usuario.message.equals("usuario não existe")) {
                            Snackbar.make(findViewById(android.R.id.content), usuario.message, Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Action", null).show()
                        } else {
                            this@MainActivity.openView(TelaPrincipal())
                        }


                    }

                }

            })
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Por favor, informe os dados de acesso!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun openView(activity: AppCompatActivity) {
        val intent = Intent(this@MainActivity, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
    }
}
