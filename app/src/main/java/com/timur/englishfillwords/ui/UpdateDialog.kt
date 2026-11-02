package com.timur.englishfillwords.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.timur.englishfillwords.BuildConfig
import com.timur.englishfillwords.update.UpdateInfo
import com.timur.englishfillwords.update.UpdateState
import com.timur.englishfillwords.update.UpdateViewModel
import com.timur.englishfillwords.ui.theme.Gold

@Composable
fun UpdateDialog(
    onDismiss: () -> Unit,
    viewModel: UpdateViewModel = viewModel(key = "update")
) {
    val state = viewModel.state
    var permissionHint by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (state is UpdateState.Idle) viewModel.checkForUpdates()
    }

    Dialog(
        onDismissRequest = {
            if (state !is UpdateState.Downloading) onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Обновление",
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = FontFamily.Serif
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Версия ${BuildConfig.VERSION_NAME}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(18.dp))

                when (state) {
                    UpdateState.Idle, UpdateState.Checking -> {
                        CircularProgressIndicator(modifier = Modifier.height(32.dp))
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = "Проверяем обновления…",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    UpdateState.UpToDate -> {
                        Text("✓", fontSize = 36.sp, color = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "У вас последняя версия",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    is UpdateState.Available -> {
                        Text(
                            text = "Доступна версия ${state.info.versionName}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (state.info.notes.isNotBlank()) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = state.info.notes,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    is UpdateState.Downloading -> {
                        val progress = state.progress
                        if (progress == null) {
                            CircularProgressIndicator(modifier = Modifier.height(36.dp))
                        } else {
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(50)),
                                color = MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                drawStopIndicator = {}
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = if (progress == null) "Скачиваем…"
                            else "Скачиваем… ${(progress * 100).toInt()}%",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    is UpdateState.Ready -> {
                        Text(
                            text = "Версия ${state.info.versionName} скачана",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Нажмите «Установить» и подтвердите установку в системе.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (permissionHint) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Разрешите установку из этого источника и нажмите «Установить» снова.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Gold
                            )
                        }
                    }

                    is UpdateState.Failed -> {
                        Text(
                            text = "Не удалось обновить",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = state.message,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                when (state) {
                    is UpdateState.Available -> {
                        Button(
                            onClick = { viewModel.download(state.info) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Скачать обновление", fontWeight = FontWeight.SemiBold)
                        }
                    }

                    is UpdateState.Ready -> {
                        Button(
                            onClick = {
                                val started = viewModel.install()
                                permissionHint = !started
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Установить", fontWeight = FontWeight.SemiBold)
                        }
                    }

                    is UpdateState.Failed -> {
                        Button(
                            onClick = { viewModel.checkForUpdates() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Повторить", fontWeight = FontWeight.SemiBold)
                        }
                    }

                    UpdateState.UpToDate -> {
                        OutlinedButton(
                            onClick = { viewModel.checkForUpdates() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Проверить снова")
                        }
                    }

                    else -> Unit
                }

                TextButton(
                    onClick = onDismiss,
                    enabled = state !is UpdateState.Downloading
                ) {
                    Text(
                        text = "Закрыть",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
