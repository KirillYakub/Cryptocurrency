package com.example.cryptocurrency.presentation.coin_detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.cryptocurrency.data.remote.dto.TeamMember
import androidx.compose.ui.Modifier

@Composable
fun TeamMembers(
    teamMember: TeamMember
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier
            .height(5.dp)
        )
        Text(
            text = teamMember.position,
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic
        )
    }
}