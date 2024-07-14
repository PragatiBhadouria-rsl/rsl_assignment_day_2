package com.example.myapplication.others

import java.io.Serializable

sealed class TransactionStatus : Serializable {
    object Cancelled : TransactionStatus()
    object Completed : TransactionStatus()
}
