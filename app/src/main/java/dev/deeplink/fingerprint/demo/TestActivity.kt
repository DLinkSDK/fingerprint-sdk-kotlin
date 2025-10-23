package dev.deeplink.fingerprint.demo

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import dev.deeplink.fingerprint.Fingerprint
import dev.deeplink.fingerprint.OnGenerateListener

class TestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        Fingerprint.generate(this, "ACCOUNT_ID", "DEV_TOKEN", object : OnGenerateListener {

            @SuppressLint("SetTextI18n")
            override fun onResult(fingerprint: String) {
                findViewById<TextView>(R.id.device_id_tv)?.text = "Fingerprint:$fingerprint"
            }
        })
    }
}