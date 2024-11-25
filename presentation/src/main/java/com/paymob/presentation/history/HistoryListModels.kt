package com.paymob.presentation.history


data class HistoryRateListModel(
    val items: List<HistoryRateListItemModel> = listOf(),
)

data class HistoryRateListItemModel(
    val base:String,
    val date:String,
    val target:String,
    val rate:Float
)