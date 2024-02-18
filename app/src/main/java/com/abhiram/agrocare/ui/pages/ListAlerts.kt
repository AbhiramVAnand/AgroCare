package com.abhiram.agrocare.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhiram.agrocare.data.room.AlertState
import com.abhiram.agrocare.data.room.Alerts
import com.abhiram.agrocare.ui.theme.FadedBorder
import com.abhiram.agrocare.ui.theme.Secondary

@Composable
fun ListAlerts(state : AlertState) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1F)
            .verticalScroll(rememberScrollState())
    ) {
        for(i in state.alerts){
            ListItem(alert = i)
        }
    }
}

@Composable
fun ListItem(alert : Alerts) {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth(1F)
            .background(Secondary)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        Text(
            text = alert.message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
        Text(
            text = alert.time,
            style = MaterialTheme.typography.bodySmall,
            color = FadedBorder
        )
    }
}