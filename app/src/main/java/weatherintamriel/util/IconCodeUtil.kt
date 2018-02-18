package weatherintamriel.util

fun iconCodeToImageUrl(iconCode: String): String {
    val firebaseProjectURL = "https://firebasestorage.googleapis.com/v0/b/weather-in-tamriel.appspot.com/o/"

    return when(iconCode){
        //Clear skies
        "01d"-> "${firebaseProjectURL}clear-skies.png?alt=media&token=ad2643db-fd8f-41e2-a1ff-8201294d52c2"
        "01n"-> "${firebaseProjectURL}clear-skies-night.png?alt=media&token=abd77bc3-0c50-4d16-a9dd-7f755a3dd797"

        //Clouds
        "02d"-> "${firebaseProjectURL}cloudy.png?alt=media&token=7ef46fec-186f-475c-89f8-e54b1152b343"
        "02n"-> "${firebaseProjectURL}cloudy.png?alt=media&token=7ef46fec-186f-475c-89f8-e54b1152b343"
        "03d"-> "${firebaseProjectURL}cloudy.png?alt=media&token=7ef46fec-186f-475c-89f8-e54b1152b343"
        "03n"-> "${firebaseProjectURL}cloudy.png?alt=media&token=7ef46fec-186f-475c-89f8-e54b1152b343"
        "04d"-> "${firebaseProjectURL}cloudy.png?alt=media&token=7ef46fec-186f-475c-89f8-e54b1152b343"
        "04n"-> "${firebaseProjectURL}cloudy.png?alt=media&token=7ef46fec-186f-475c-89f8-e54b1152b343"

        //Rain
        "09d"-> "${firebaseProjectURL}rainy.png?alt=media&token=2a540f64-97c5-46d8-aea4-7e0f8cd314b5"
        "09n"-> "${firebaseProjectURL}rainy.png?alt=media&token=2a540f64-97c5-46d8-aea4-7e0f8cd314b5"
        "10d"-> "${firebaseProjectURL}rainy.png?alt=media&token=2a540f64-97c5-46d8-aea4-7e0f8cd314b5"
        "10n"-> "${firebaseProjectURL}rainy.png?alt=media&token=2a540f64-97c5-46d8-aea4-7e0f8cd314b5"

        //Storm
        "11d"-> "${firebaseProjectURL}storm.png?alt=media&token=08a8014c-7c78-4afa-87a0-9f882e49b332"
        "11n"-> "${firebaseProjectURL}storm.png?alt=media&token=08a8014c-7c78-4afa-87a0-9f882e49b332"

        //Snow
        "13d"-> "${firebaseProjectURL}snowy.png?alt=media&token=c1268411-b91b-44e8-94c6-d197957e5a05"
        "13n"-> "${firebaseProjectURL}snowy.png?alt=media&token=c1268411-b91b-44e8-94c6-d197957e5a05"

        //Mist
        "50d"-> "${firebaseProjectURL}mist.png?alt=media&token=ce7ac147-97b6-4d07-8260-48a02822af14"
        "50n"-> "${firebaseProjectURL}mist.png?alt=media&token=ce7ac147-97b6-4d07-8260-48a02822af14"

        else -> "${firebaseProjectURL}default.png?alt=media&token=e4168a40-b4ec-4724-977d-a9b47a86b5f0"
    }
}
