package com.lexia.app.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexia.app.core.ui.CommunicationCard
import com.lexia.app.core.ui.FolderCard
import com.lexia.app.core.ui.HomeButton
import com.lexia.app.core.ui.SentenceBar
import com.lexia.app.shared.theme.MainPanelBlue
import com.lexia.app.shared.theme.SubtitleText

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        uiState = uiState,
        onCardClick = viewModel::onCardClick,
        onFolderClick = { /* não implementado no MVP */ },
        onSpeakClick = { /* não implementado no MVP */ },
        onClearClick = viewModel::onClearSentence,
        onHomeClick = { /* não implementado no MVP */ },
        modifier = modifier,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onCardClick: (MainWord) -> Unit,
    onFolderClick: (Folder) -> Unit,
    onSpeakClick: () -> Unit,
    onClearClick: () -> Unit,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Header(onHomeClick = onHomeClick)

            Row(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MainWordsPanel(
                    mainWords = uiState.mainWords,
                    onCardClick = onCardClick,
                    modifier = Modifier.weight(0.4f).fillMaxHeight(),
                )

                FoldersPanel(
                    folders = uiState.folders,
                    onFolderClick = onFolderClick,
                    modifier = Modifier.weight(0.6f).fillMaxHeight(),
                )
            }

            SentenceBar(
                sentence = uiState.sentence,
                onSpeakClick = onSpeakClick,
                onClearClick = onClearClick,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun Header(
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Oi! Vamos falar?",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "Monte sua frase escolhendo os cartões",
                fontSize = 16.sp,
                color = SubtitleText,
            )
        }

        HomeButton(onClick = onHomeClick)
    }
}

@Composable
private fun MainWordsPanel(
    mainWords: List<MainWord>,
    onCardClick: (MainWord) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            modifier
                .clip(RoundedCornerShape(28.dp))
                .background(MainPanelBlue)
                .padding(20.dp),
    ) {
        Text(
            text = "PALAVRAS PRINCIPAIS",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SubtitleText,
            modifier = Modifier.align(Alignment.Start),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
        ) {
            mainWords.chunked(2).forEach { rowWords ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    rowWords.forEach { word ->
                        CommunicationCard(
                            label = word.label,
                            icon = word.icon,
                            containerColor = word.containerColor,
                            borderColor = word.borderColor,
                            onClick = { onCardClick(word) },
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FoldersPanel(
    folders: List<Folder>,
    onFolderClick: (Folder) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(20.dp),
    ) {
        Text(
            text = "CATEGORIAS",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SubtitleText,
        )

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            modifier = Modifier.fillMaxWidth().weight(1f),
        ) {
            folders.forEach { folder ->
                FolderCard(
                    label = folder.label,
                    icon = folder.icon,
                    containerColor = folder.containerColor,
                    borderColor = folder.borderColor,
                    onClick = { onFolderClick(folder) },
                )
            }
        }
    }
}

@Preview(device = "spec:width=1280dp,height=800dp,dpi=240", showBackground = true)
@Composable
private fun HomeScreenTabletPreview() {
    com.lexia.app.shared.theme.LexiaTheme {
        HomeContent(
            uiState = HomeViewModel().uiState.value,
            onCardClick = {},
            onFolderClick = {},
            onSpeakClick = {},
            onClearClick = {},
            onHomeClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPhonePreview() {
    com.lexia.app.shared.theme.LexiaTheme {
        HomeContent(
            uiState = HomeViewModel().uiState.value,
            onCardClick = {},
            onFolderClick = {},
            onSpeakClick = {},
            onClearClick = {},
            onHomeClick = {},
        )
    }
}
