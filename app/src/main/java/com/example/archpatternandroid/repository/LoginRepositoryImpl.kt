package com.example.archpatternandroid.repository

import com.example.archpatternandroid.networking.ApiService

class LoginRepositoryImpl(val service: ApiService) : LoginRepository {
    override fun login(name: String, password: String) = service.login(name, password)

}