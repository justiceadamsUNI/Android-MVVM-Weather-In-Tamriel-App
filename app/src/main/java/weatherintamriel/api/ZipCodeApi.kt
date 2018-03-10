package weatherintamriel.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ZipCodeApi {
    companion object {
        private const val APP_KEY = "JNtdgN21BZP1FH0IjQCzfhbEKL7LciQCMrcLjgLSTULBdep0i5pICFYlNaD4Dy95"
        private const val FORMAT = "json"
        private const val UNITS = "degrees"
    }

    @GET("$APP_KEY/info.$FORMAT/{zip_code}/$UNITS")
    fun getZipCodeLocationInfo(@Path("zip_code") zipCode: Int): Single<String> //toDo change to actually deserialize
}