package com.example.myapplication.others

import java.io.Serializable


data class PaymentInformation(
    val username : String,
    val amount : Double,
    val transactionType : TransactionType,
    val paymentDetails : String
) : Serializable
