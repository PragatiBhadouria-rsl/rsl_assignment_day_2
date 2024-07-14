package com.example.myapplication.others

import java.io.Serializable

enum class TransactionType : Serializable {

    CASH,
    CARD,
    NETBANKING,
    UPI
}