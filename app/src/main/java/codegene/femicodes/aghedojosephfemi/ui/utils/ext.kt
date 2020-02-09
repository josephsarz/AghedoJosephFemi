package codegene.femicodes.aghedojosephfemi.ui.utils

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun EditText.isValid(
    layout: TextInputLayout,
    errorText: String = "Field cannot be empty"
): Boolean {
    if (text.isBlank()) {
        layout.isErrorEnabled = true
        layout.error = errorText
        return false
    }
    layout.isErrorEnabled = false
    return true
}


fun EditText.isValidInteger(layout: TextInputLayout): Boolean {
    isValid(layout)
    val pattern = "-?\\d+"
    return if (text.matches(pattern.toRegex())) {
        layout.isErrorEnabled = false
        true
    } else {
        layout.isErrorEnabled = true
        layout.error = "Field should be a valid year"
        false
    }
}
