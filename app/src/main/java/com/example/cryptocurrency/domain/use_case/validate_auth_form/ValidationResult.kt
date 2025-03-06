package com.example.cryptocurrency.domain.use_case.validate_auth_form

data class ValidationResult (
    val successful: Boolean = false,
    val error: String? = null
)