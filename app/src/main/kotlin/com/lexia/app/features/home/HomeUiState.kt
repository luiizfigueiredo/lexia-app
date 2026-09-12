package com.lexia.app.features.home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface BoardItem {
    val id: String
    val label: String
    val icon: ImageVector
    val containerColor: Color
    val borderColor: Color
}

data class MainWord(
    override val id: String,
    override val label: String,
    override val icon: ImageVector,
    override val containerColor: Color,
    override val borderColor: Color,
) : BoardItem

data class Folder(
    override val id: String,
    override val label: String,
    override val icon: ImageVector,
    override val containerColor: Color,
    override val borderColor: Color,
) : BoardItem

data class HomeUiState(
    val mainWords: List<MainWord> = emptyList(),
    val folders: List<Folder> = emptyList(),
    val sentence: List<MainWord> = emptyList(),
)
