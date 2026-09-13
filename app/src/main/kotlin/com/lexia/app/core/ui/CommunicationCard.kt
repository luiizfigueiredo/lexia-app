package com.lexia.app.core.ui

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommunicationCard(
    label: String,
    icon: ImageVector,
    containerColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Int = 32,
) {
    CommunicationCardLayout(
        label = label,
        icon = icon,
        containerColor = containerColor,
        borderColor = borderColor,
        iconSize = iconSize,
        cardModifier = Modifier.clickable(onClick = onClick),
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableCommunicationCard(
    label: String,
    icon: ImageVector,
    containerColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    dragData: () -> ClipData,
    modifier: Modifier = Modifier,
    iconSize: Int = 32,
) {
    val haptic = LocalHapticFeedback.current

    CommunicationCardLayout(
        label = label,
        icon = icon,
        containerColor = containerColor,
        borderColor = borderColor,
        iconSize = iconSize,
        cardModifier =
            Modifier
                .clickable(onClick = onClick)
                .dragAndDropSource(
                    drawDragDecoration = {
                        drawRoundRect(
                            color = borderColor.copy(alpha = 0.7f),
                            cornerRadius = CornerRadius(24.dp.toPx()),
                        )
                    },
                ) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            startTransfer(
                                DragAndDropTransferData(clipData = dragData()),
                            )
                        },
                        onDrag = { _, _ -> },
                    )
                },
        modifier = modifier,
    )
}

@Composable
private fun CommunicationCardLayout(
    label: String,
    icon: ImageVector,
    containerColor: Color,
    borderColor: Color,
    iconSize: Int,
    cardModifier: Modifier,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(containerColor.copy(alpha = 0.25f))
                    .border(
                        width = 4.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(24.dp),
                    )
                    .then(cardModifier),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = borderColor,
                modifier = Modifier.size(iconSize.dp),
            )
        }

        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun FolderCard(
    label: String,
    icon: ImageVector,
    containerColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(containerColor.copy(alpha = 0.25f))
                    .border(
                        width = 3.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .clickable(onClick = onClick),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = borderColor,
                modifier = Modifier.size(28.dp),
            )
        }

        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 4.dp),
        )
    }
}
