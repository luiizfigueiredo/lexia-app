package com.lexia.app.features.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SmartToy
import androidx.lifecycle.ViewModel
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

    fun onCardClick(card: MainWord) {
        _uiState.update { current ->
            current.copy(sentence = current.sentence + card)
        }
    }

    fun onClearSentence() {
        _uiState.update { it.copy(sentence = emptyList()) }
    }

    fun onRemoveLastCard() {
        _uiState.update { current ->
            current.copy(sentence = current.sentence.dropLast(1))
        }
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
                ),
                Folder(
                    id = "brinquedos",
                    label = "Brinquedos",
                    icon = Icons.Default.SmartToy,
                    containerColor = PeachFolder.copy(alpha = 0.25f),
                    borderColor = PeachFolder,
                ),
                Folder(
                    id = "sentimentos",
                    label = "Sentimentos",
                    icon = Icons.Default.Favorite,
                    containerColor = LightPinkFolder.copy(alpha = 0.25f),
                    borderColor = LightPinkFolder,
                ),
                Folder(
                    id = "lugares",
                    label = "Lugares",
                    icon = Icons.Default.LocationOn,
                    containerColor = LavenderFolder.copy(alpha = 0.25f),
                    borderColor = LavenderFolder,
                ),
                Folder(
                    id = "pessoas",
                    label = "Pessoas",
                    icon = Icons.Default.People,
                    containerColor = LightYellowFolder.copy(alpha = 0.25f),
                    borderColor = LightYellowFolder,
                ),
                Folder(
                    id = "acoes",
                    label = "Ações",
                    icon = Icons.Default.PanTool,
                    containerColor = LightGreenFolder.copy(alpha = 0.25f),
                    borderColor = LightGreenFolder,
                ),
            )

        return HomeUiState(
            mainWords = mainWords,
            folders = folders,
            sentence = listOf(mainWords[0], mainWords[1], waterWord),
        )
    }
}
