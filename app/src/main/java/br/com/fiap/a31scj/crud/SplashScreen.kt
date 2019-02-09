package br.com.fiap.a31scj.crud

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import android.view.animation.AnimationUtils
import android.widget.ImageView
import br.com.cardote.fichadetreino.helpers.PreferencesHelper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME = 3500L

    private lateinit var ivLogo : ImageView
    private lateinit var prefHelper : PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        prefHelper = PreferencesHelper(this)

        ivLogo = findViewById(R.id.splash)

        load()
    }

    private fun load() {
        val anim = AnimationUtils.loadAnimation(
                this,
                R.anim.anim_splash
        )
        anim.reset()

        val iv = findViewById<ImageView>(R.id.splash)

        iv.clearAnimation()
        iv.startAnimation(anim)

        Handler().postDelayed({
            if(prefHelper.stayConnected){
                mostrarMainActivity(TelaPrincipal())
            } else {
                mostrarMainActivity(MainActivity())
            }

            this@SplashScreen.finish()
        }, SPLASH_TIME)

    }

    private fun mostrarMainActivity(activity: AppCompatActivity) {
        val intent = Intent(this@SplashScreen, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
    }
}
