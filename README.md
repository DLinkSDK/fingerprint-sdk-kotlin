# fingerprint-sdk-kotlin

Step 1: Get the SDK

(1) Configure the Maven repository
```kotlin   
repositories {
   maven { url 'https://maven.deeplink.dev/repository/maven-releases/' }
}
```

Note: The Maven repository address needs to be configured in both 'buildscript' and 'allprojects' in the root directory's 'build.gradle'.

(2) If you are using Gradle for integration, add the following code to your project's build.gradle:
```kotlin
implementation 'dev.deeplink:id:2.1.3'
```

Step 2: Generate Device Id
```kotlin
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
```