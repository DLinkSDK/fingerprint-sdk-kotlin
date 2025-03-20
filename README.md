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
implementation 'dev.deeplink:fingerprint:3.0.0'
```

Step 2: Generate Device Fingerprint
```kotlin
class TestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        Fingerprint.generate(this, object : OnGenerateListener {

            override fun onResult(fingerprint: String) {
                findViewById<TextView>(R.id.device_id_tv)?.text = "Fingerprint:$fingerprint"
            }
        })
    }
}
```