package weatherintamriel.util

import android.content.Context
import justiceadams.com.weatherintamriel.R
import org.jetbrains.anko.defaultSharedPreferences

fun Context.readZipCodeFromSharedPreferences(): Int? {
    val zip = defaultSharedPreferences.getInt(
            getString(R.string.shared_preference_zip_key),
            -1)

    return if (zip == -1) null else zip
}

fun Context.writeZipCodeIntoSharedPreferences(zipCode: Int?) {
    defaultSharedPreferences
            .edit()
            .putInt( getString(R.string.shared_preference_zip_key), zipCode ?: -1)
            .apply()
}