package com.paradoxo.suzana

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import com.paradoxo.suzana.ui.home.AlertDialogAPIKey
import com.paradoxo.suzana.ui.home.ChatScreen
import com.paradoxo.suzana.ui.home.ChatViewModel
import com.paradoxo.suzana.ui.theme.SuzanaTheme
import kotlinx.coroutines.launch

private const val URL_OPENAI = "https://platform.openai.com/account/api-keys"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SuzanaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel by viewModels<ChatViewModel>()
                    val state by viewModel.uiState.collectAsState()

                    val context = LocalContext.current
                    val scope = rememberCoroutineScope()

                    var showDialog by remember { mutableStateOf(false) }

                    ChatScreen(state = state,
                        onSendMessage = {
                            scope.launch {
                                viewModel.sendMessage()
                            }
                        },
                        onOpenDialogKey = {
                            showDialog = true
                        }
                    )

                    if (showDialog) {
                        val uriHandler = LocalUriHandler.current
                        AlertDialogAPIKey(
                            onOpenApiSite = {
                                uriHandler.openUri(URL_OPENAI)
                            },
                            onDismissClick = { showDialog = false },
                            onSavedClick = {
                                Log.i("TAG", "onCreate: ${it} ")
                                viewModel.setToken(it)
                                showDialog = false
                            }
                        )
                    }

                    LaunchedEffect(state.showWaitAlert) {
                        if (state.showWaitAlert) {
                            Toast.makeText(
                                context,
                                getString(R.string.wait_before_sen_new_message),
                                Toast.LENGTH_LONG
                            ).show()
                            viewModel.updateWaitAlert()
                        }
                    }

                    LaunchedEffect(state.showError) {
                        if (state.showError) {
                            Toast.makeText(
                                context,
                                getString(R.string.error_message) + state.error,
                                Toast.LENGTH_LONG
                            ).show()
                            viewModel.updateshowError()
                        }
                    }
                }
            }
        }
    }
}
