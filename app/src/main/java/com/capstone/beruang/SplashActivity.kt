package com.capstone.beruang

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.beruang.data.repository.PreferenceManager
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.databinding.ActivitySplashBinding
import com.capstone.beruang.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val userRepository: UserRepository by lazy {
        UserRepository(PreferenceManager.getInstance(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUI()
    }

    private fun setUpUI() {
        val isLoggedIn = userRepository.isUserLoggedIn()
        val intent = if (isLoggedIn) {
            playAnimation()
            Intent(this, MainActivity::class.java)
        } else {
            playAnimation()
            Intent(this, LoginActivity::class.java)
        }

        val SPLASH_DELAY: Long = 3600

        binding.tvLogo.animate()
            .setDuration(SPLASH_DELAY)
            .alpha(0f)
            .withEndAction {
                startActivity(intent)
                finish()
            }
    }

    private fun playAnimation() {
        val durationAppear = 2000L // Durasi muncul
        val durationFadeOut = 1000L // Durasi fade-out

        val splashScreenLogoAppear =
            ObjectAnimator.ofFloat(binding.BerUang, View.ALPHA, 0f, 1f).apply {
                duration = durationAppear
            }
        val splashScreenTextLogoAppear =
            ObjectAnimator.ofFloat(binding.tvSplashScreen, View.ALPHA, 0f, 1f).apply {
                duration = durationAppear
            }

        val splashScreenLogoFadeOut =
            ObjectAnimator.ofFloat(binding.BerUang, View.ALPHA, 1f, 0f).apply {
                startDelay = durationAppear
                duration = durationFadeOut
            }
        val splashScreenTextLogoFadeOut =
            ObjectAnimator.ofFloat(binding.tvSplashScreen, View.ALPHA, 1f, 0f).apply {
                startDelay = durationAppear
                duration = durationFadeOut
            }

        AnimatorSet().apply {
            playTogether(splashScreenLogoAppear, splashScreenTextLogoAppear)
            start()
            splashScreenLogoFadeOut.start()
            splashScreenTextLogoFadeOut.start()
        }
    }
}