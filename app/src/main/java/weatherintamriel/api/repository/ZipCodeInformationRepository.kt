package weatherintamriel.api.repository

import io.reactivex.Single
import weatherintamriel.api.ZipCodeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZipCodeInformationRepository @Inject constructor(private var zipCodeApi: ZipCodeApi) {

    fun getZipCodeLocationInfo(zipCode: Int): Single<String> = zipCodeApi.getZipCodeLocationInfo(zipCode) //toDo: change from single
}