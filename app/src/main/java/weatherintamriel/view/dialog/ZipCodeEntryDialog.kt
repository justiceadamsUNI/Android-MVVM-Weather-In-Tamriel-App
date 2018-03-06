package weatherintamriel.view.dialog

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialog
import android.widget.EditText
import justiceadams.com.weatherintamriel.R

class ZipCodeEntryDialogBuilder(context: Context) : AlertDialog.Builder(context) {

    fun createAlertDialogWithActionOnZipCodeEntry(onZipCodeEntered: (zipCode: Int) -> Unit)
            : AlertDialog {

        return setView(R.layout.dialog_set_zip_code)
                .setPositiveButton("Done", { dialog, _ ->
                    onZipCodeEntered.invoke(getEnteredZipCode(dialog = (dialog as AlertDialog)))
                })
                .setNegativeButton("Cancel", { dialog, _ ->
                    dialog.dismiss()
                })
                .create()
    }

    private fun getEnteredZipCode(dialog: AlertDialog): Int {
        return (dialog as AppCompatDialog)
                .findViewById<EditText>(R.id.zip_code)
                ?.text
                .toString()
                .toInt()
    }
}