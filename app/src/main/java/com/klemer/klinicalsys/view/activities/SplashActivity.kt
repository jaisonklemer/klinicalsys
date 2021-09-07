package com.klemer.klinicalsys.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klemer.klinicalsys.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), CoroutineScope {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val job = launch { toNextActivity() }

    }

    private suspend fun toNextActivity() {

        delay(3000)
        withContext(Dispatchers.Main) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
}