package com.lexia.app.features.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SmartToy
import androidx.lifecycle.ViewModel
import com.lexia.app.core.ui.dragdrop.BoardItemRef
import com.lexia.app.shared.theme.BlueWord
import com.lexia.app.shared.theme.GreenWord
import com.lexia.app.shared.theme.LavenderFolder
import com.lexia.app.shared.theme.LightBlueFolder
import com.lexia.app.shared.theme.LightGreenFolder
import com.lexia.app.shared.theme.LightPinkFolder
import com.lexia.app.shared.theme.LightYellowFolder
import com.lexia.app.shared.theme.PeachFolder
import com.lexia.app.shared.theme.PinkWord
import com.lexia.app.shared.theme.RedWord
import com.lexia.app.shared.theme.YellowWord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(createInitialState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onCardClick(card: BoardItem) {
        _uiState.update { current ->
            if (current.sentence.size >= MAX_SENTENCE_SIZE) {
                current
            } else {
                current.copy(sentence = current.sentence + card)
            }
        }
    }

    fun isSentenceFull(): Boolean = _uiState.value.sentence.size >= MAX_SENTENCE_SIZE

    fun onClearSentence() {
        _uiState.update { it.copy(sentence = emptyList()) }
    }

    fun onRemoveLastCard() {
        _uiState.update { current ->
            current.copy(sentence = current.sentence.dropLast(1))
        }
    }

    fun onRemoveCardAt(index: Int) {
        _uiState.update { current ->
            if (index in current.sentence.indices) {
                current.copy(
                    sentence = current.sentence.toMutableList().apply { removeAt(index) },
                )
            } else {
                current
            }
        }
    }

    fun getFolderById(id: String): Folder? = _uiState.value.folders.find { it.id == id }

    fun getMainWordById(id: String): MainWord? = _uiState.value.mainWords.find { it.id == id }

    fun getCategoryItemById(
        folderId: String,
        itemId: String,
    ): CategoryItem? =
        _uiState.value.folders
            .find { it.id == folderId }
            ?.items
            ?.find { it.id == itemId }

    fun resolveBoardItem(ref: BoardItemRef): BoardItem? =
        when (ref) {
            is BoardItemRef.MainWordRef -> getMainWordById(ref.id)
            is BoardItemRef.CategoryItemRef -> getCategoryItemById(ref.folderId, ref.itemId)
        }

    private fun createInitialState(): HomeUiState {
        val mainWords =
            listOf(
                MainWord(
                    id = "eu",
                    label = "Eu",
                    icon = Icons.Default.Person,
                    containerColor = YellowWord.copy(alpha = 0.2f),
                    borderColor = YellowWord,
                ),
                MainWord(
                    id = "quero",
                    label = "Quero",
                    icon = Icons.Default.PanTool,
                    containerColor = GreenWord.copy(alpha = 0.2f),
                    borderColor = GreenWord,
                ),
                MainWord(
                    id = "sim",
                    label = "Sim",
                    icon = Icons.Default.SentimentSatisfied,
                    containerColor = PinkWord.copy(alpha = 0.2f),
                    borderColor = PinkWord,
                ),
                MainWord(
                    id = "nao",
                    label = "Não",
                    icon = Icons.Default.Close,
                    containerColor = RedWord.copy(alpha = 0.2f),
                    borderColor = RedWord,
                ),
            )

        val waterWord =
            MainWord(
                id = "agua",
                label = "Água",
                icon = Icons.Default.LocalDrink,
                containerColor = BlueWord.copy(alpha = 0.2f),
                borderColor = BlueWord,
            )

        val folders =
            listOf(
                Folder(
                    id = "alimentacao",
                    label = "Alimentação",
                    icon = Icons.Default.LocalDining,
                    containerColor = LightBlueFolder.copy(alpha = 0.25f),
                    borderColor = LightBlueFolder,
                    items =
                        listOf(
                            CategoryItem(
                                id = "agua",
                                label = "Água",
                                icon = Icons.Default.LocalDrink,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "leite",
                                label = "Leite",
                                icon = Icons.Default.LocalCafe,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "maca",
                                label = "Maçã",
                                icon = Icons.Default.LocalDining,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "pao",
                                label = "Pão",
                                icon = Icons.Default.BakeryDining,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "banana",
                                label = "Banana",
                                icon = Icons.Default.LocalDining,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "suco",
                                label = "Suco",
                                icon = Icons.Default.LocalDrink,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "biscoito",
                                label = "Biscoito",
                                icon = Icons.Default.BakeryDining,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "doce",
                                label = "Doce",
                                icon = Icons.Default.Cake,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                            CategoryItem(
                                id = "iogurte",
                                label = "Iogurte",
                                icon = Icons.Default.LocalCafe,
                                containerColor = BlueWord.copy(alpha = 0.2f),
                                borderColor = BlueWord,
                            ),
                        ),
                ),
                Folder(
                    id = "brinquedos",
                    label = "Brinquedos",
                    icon = Icons.Default.SmartToy,
                    containerColor = PeachFolder.copy(alpha = 0.25f),
                    borderColor = PeachFolder,
                    items =
                        listOf(
                            CategoryItem(
                                id = "bola",
                                label = "Bola",
                                icon = Icons.AutoMirrored.Filled.HelpOutline,
                                containerColor = PeachFolder.copy(alpha = 0.2f),
                                borderColor = PeachFolder,
                            ),
                        ),
                ),
                Folder(
                    id = "sentimentos",
                    label = "Sentimentos",
                    icon = Icons.Default.Favorite,
                    containerColor = LightPinkFolder.copy(alpha = 0.25f),
                    borderColor = LightPinkFolder,
                    items = emptyList(),
                ),
                Folder(
                    id = "lugares",
                    label = "Lugares",
                    icon = Icons.Default.LocationOn,
                    containerColor = LavenderFolder.copy(alpha = 0.25f),
                    borderColor = LavenderFolder,
                    items = emptyList(),
                ),
                Folder(
                    id = "pessoas",
                    label = "Pessoas",
                    icon = Icons.Default.People,
                    containerColor = LightYellowFolder.copy(alpha = 0.25f),
                    borderColor = LightYellowFolder,
                    items = emptyList(),
                ),
                Folder(
                    id = "acoes",
                    label = "Ações",
                    icon = Icons.Default.PanTool,
                    containerColor = LightGreenFolder.copy(alpha = 0.25f),
                    borderColor = LightGreenFolder,
                    items = emptyList(),
                ),
            )

        return HomeUiState(
            mainWords = mainWords,
            folders = folders,
            sentence = listOf(mainWords[0], mainWords[1], waterWord),
        )
    }

    companion object {
        const val MAX_SENTENCE_SIZE = 8
    }
}
