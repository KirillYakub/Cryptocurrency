package com.example.cryptocurrency.domain.use_case.validate_auth_form.validate_password

import com.example.cryptocurrency.common.Constants.PASSWORD_MAX_LENGTH
import com.example.cryptocurrency.common.Constants.PASSWORD_MIN_LENGTH
import com.example.cryptocurrency.domain.use_case.validate_auth_form.ValidationResult

class ValidatePassword {
    operator fun invoke(password: String): ValidationResult {
        if(password.length < PASSWORD_MIN_LENGTH || password.length > PASSWORD_MAX_LENGTH) {
            return ValidationResult(
                error = "Password must contain from $PASSWORD_MIN_LENGTH to $PASSWORD_MAX_LENGTH characters."
            )
        }
        if(!password.any { it.isDigit() }) {
            return ValidationResult(
                error = "Password must contain at least one digit."
            )
        }
        if(!password.any { it.isUpperCase() }) {
            return ValidationResult(
                error = "Password must contain at least one uppercase letter."
            )
        }
        if(!password.any { it.isLowerCase() }) {
            return ValidationResult(
                error = "Password must contain at least one lowercase letter."
            )
        }
        if(password.any { it.isWhitespace() }) {
            return ValidationResult(
                error = "Password can not contain whitespaces."
            )
        }
        return ValidationResult(successful = true)
    }
}