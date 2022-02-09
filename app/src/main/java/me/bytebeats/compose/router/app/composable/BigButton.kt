package me.bytebeats.compose.router.app.composable

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Created by bytebeats on 2021/10/13 : 17:07
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

@Composable
fun BitButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.body2.copy(color = Color.Cyan)
        )
    }
}