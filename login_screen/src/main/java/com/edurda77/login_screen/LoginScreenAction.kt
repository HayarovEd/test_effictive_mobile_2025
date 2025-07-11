package com.edurda77.login_screen

sealed interface LoginScreenAction {
    class UpdateEmail(val email: String): LoginScreenAction
    class UpdatePassword(val password: String): LoginScreenAction
}