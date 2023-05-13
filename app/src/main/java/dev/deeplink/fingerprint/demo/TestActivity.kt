package dev.deeplink.fingerprint.demo

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import dev.deeplink.id.DeviceID
import java.security.MessageDigest

class TestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val pseudoID = DeviceID.getPseudoID()
        val widevineID = DeviceID.getWidevineID()

        // Developers can freely combine these two device IDs to generate a new device ID.
        // For example, concatenate pseudoID and widevineID into a string and encrypt it to get a new device ID.
        if (!pseudoID.id.isNullOrEmpty() && !widevineID.id.isNullOrEmpty()) {
            val newDeviceId = md5(pseudoID.id + widevineID.id)
            @SuppressLint("SetTextI18n")
            findViewById<TextView>(R.id.device_id_tv)?.text = "Fingerprint:$newDeviceId"
        } else {
            @SuppressLint("SetTextI18n")
            findViewById<TextView>(R.id.device_id_tv)?.text = "Fingerprint:Unknown"
        }
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(input.toByteArray())
        val digest = md.digest()
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}