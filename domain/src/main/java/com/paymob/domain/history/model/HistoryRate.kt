package com.paymob.domain.history.model


data class HistoryRate(val base:String,
                       val date:String,
                       val target:String,
                       val rate:Float)