package weatherintamriel.util

import java.text.DateFormat
import java.util.*

fun convertDateToTamrielDate(date: Long) : String{
    val dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault())
    val stringDate = dateFormat.format(date * 1000)

    val regex = Regex("[^A-Za-z0-9 ]")
    var dateList = regex.replace(stringDate, "").toLowerCase().split(" ")

    val weekday = when(dateList[0]) {
        "sunday"    -> "Sundas"
        "monday"    -> "Morndas"
        "tuesday"   -> "Tirdas"
        "wednesday" -> "Middas"
        "thursday"  -> "Turdas"
        "friday"    -> "Fredas"
        "saturday"  -> "Loredas"
        else -> {
            throw Exception("Error parsing weekday")
        }
    }

    val month = when(dateList[1]) {
        "january"   -> "Morning Star"
        "february"  -> "Sun's Dawn"
        "march"     -> "First Seed"
        "april"     -> "Rain's Hand"
        "may"       -> "Second Seed"
        "june"      -> "Midyear"
        "july"      -> "Sun's Height"
        "august"    -> "Last Seed"
        "september" -> "Hearthfire"
        "october"   -> "Frostfall"
        "november"  -> "Sun's Dusk"
        "december"  -> "Evening Star"
        else -> {
            throw Exception("Error parsing month")
        }
    }

    val dayOfMonth = dateList[2]

    val suffix =
            if (dayOfMonth.toInt() in 11..13) {
                "th"
            } else {
                when (dayOfMonth.toInt() % 10) {
                    1 -> "st"
                    2 -> "nd"
                    3 -> "rd"
                    else -> "th"
                }
            }

    return "$weekday, $month $dayOfMonth$suffix"
}