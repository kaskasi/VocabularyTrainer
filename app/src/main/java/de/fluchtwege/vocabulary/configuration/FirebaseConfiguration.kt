package de.fluchtwege.vocabulary.configuration


import android.os.Handler
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import de.fluchtwege.vocabulary.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import de.fluchtwege.vocabulary.BuildConfig


class FirebaseConfiguration : Configuration {


    companion object {
        val NEXT_BUTTON_BLACK = "next_button_black"
        val SHOWING_IMAGE = "showing_image"
    }

    val firebaseInstance = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()
        firebaseInstance.setConfigSettings(configSettings)
        firebaseInstance.setDefaults(R.xml.configuration_defauts)
        Handler().post({ fetchRemoteValues() })
    }

    private fun fetchRemoteValues() {
        var cacheExpiration: Long = 3600
        if (firebaseInstance.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0
        }
        firebaseInstance.fetch(cacheExpiration)
                .addOnCompleteListener({ firebaseInstance.activateFetched() })
    }

    override fun isShowingImage() = firebaseInstance.getBoolean(SHOWING_IMAGE)

    override fun isNextButtonBlack() = firebaseInstance.getBoolean(NEXT_BUTTON_BLACK)

}
