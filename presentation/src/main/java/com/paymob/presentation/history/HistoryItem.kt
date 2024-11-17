package com.paymob.presentation.history

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HistoryItem(
    history: HistoryRateListItemModel,
    backgroundColor: Color,
    textColor: Color,
    onEditClick: () -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Text(
                    text = String.format("%.3f",history.rate),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${history.base} | ${history.target}",
                    color = textColor
                )
                Text(
                    text = "Date: ${history.date}",
                    color = textColor
                )
            }

        }
    }
}

