 package com.example.khabertask.data.models

 data class LoginResponse(
    val AccountId: Int?=null,
    val AccountRole: Any?=null,
    val Activation: Boolean?=null,
    val ArabicMessage: String?=null,
    val Code: Int?=null,
    val CurrentPage: Int?=null,
    val EnglishMessage: String?=null,
    val IsArabic: Any?=null,
    val PageCount: Int?=null,
    val Success: Boolean?=null,
    val Token: String?=null,
    val UserRole: Any?=null,
    val UserType: Int?=null,
    val VisitStatus: Any?=null,
    val user: User?=null
)