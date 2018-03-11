package weatherintamriel.model.map

import weatherintamriel.model.LocationInfoModel
import weatherintamriel.model.ZipCodeLocationInformationRequestResult

class ZipCodeLocationRequestResultToLocationInfoModelMapper {
    fun convertToModel(zipCodeLocationInformationRequestResult:
                       ZipCodeLocationInformationRequestResult): LocationInfoModel {

        val city = zipCodeLocationInformationRequestResult.city
        val state = zipCodeLocationInformationRequestResult.state
        return LocationInfoModel("$city , $state")
    }
}