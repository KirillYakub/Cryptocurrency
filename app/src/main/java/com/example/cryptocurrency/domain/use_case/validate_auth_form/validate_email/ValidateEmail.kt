package com.example.cryptocurrency.domain.use_case.validate_auth_form.validate_email

import android.util.Patterns
import com.example.cryptocurrency.domain.use_case.validate_auth_form.ValidationResult

class ValidateEmail {
    operator fun invoke(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(error = "Email can not be blank.")
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(error = "Not a valid email.")
        }
        return ValidationResult(successful = true)
    }
}