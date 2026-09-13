package com.lexia.app.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lexia.app.features.home.BoardItem
import com.lexia.app.shared.theme.GreenWord
import com.lexia.app.shared.theme.RedWord

@Composable
fun SentenceBar(
    sentence: List<BoardItem>,
    onSpeakClick: () -> Unit,
    onClearClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isDropTargetActive: Boolean = false,
    isSentenceFull: Boolean = false,
) {
    val background =
        when {
            isDropTargetActive && isSentenceFull -> RedWord.copy(alpha = 0.18f)
            isDropTargetActive -> GreenWord.copy(alpha = 0.22f)
            else -> MaterialTheme.colorScheme.surface
        }
    val borderColor =
        when {
            isDropTargetActive && isSentenceFull -> RedWord
            isDropTargetActive -> GreenWord
            else -> Color.Transparent
        }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier =
            modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(background)
                .border(
                    width = 3.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(28.dp),
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        sentence.forEachIndexed { index, word ->
            SentenceCard(
                order = index + 1,
                label = word.label,
                icon = word.icon,
                borderColor = word.borderColor,
                onClick = { onCardClick(index) },
            )
        }

        if (sentence.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "Toque nos cartões para montar uma frase",
                    color = com.lexia.app.shared.theme.SubtitleText,
                    fontSize = 14.sp,
                )
            }
        } else {
            Box(modifier = Modifier.weight(1f))
        }

        ActionButton(
            icon = Icons.AutoMirrored.Filled.VolumeUp,
            contentDescription = "Falar frase",
            containerColor = com.lexia.app.shared.theme.BlueWord,
            onClick = onSpeakClick,
        )

        ActionButton(
            icon = Icons.AutoMirrored.Filled.Backspace,
            contentDescription = "Deletar frases",
            containerColor = com.lexia.app.shared.theme.PinkWord,
            onClick = onClearClick,
        )
    }
}

@Composable
private fun SentenceCard(
    order: Int,
    label: String,
    icon: ImageVector,
    borderColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier =
                Modifier
                    .width(72.dp)
                    .height(86.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(borderColor.copy(alpha = 0.15f))
                    .border(
                        width = 3.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(18.dp),
                    )
                    .clickable(onClick = onClick)
                    .padding(vertical = 8.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = borderColor,
                modifier = Modifier.size(28.dp),
            )
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = com.lexia.app.shared.theme.DarkText,
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .size(22.dp)
                    .offset(x = (-2).dp, y = (-2).dp)
                    .clip(CircleShape)
                    .background(borderColor),
        ) {
            Text(
                text = order.toString(),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun ActionButton(
    icon: ImageVector,
    contentDescription: String,
    containerColor: Color,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier =
            Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(containerColor),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(28.dp),
        )
    }
}
