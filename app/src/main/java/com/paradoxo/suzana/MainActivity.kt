package com.paradoxo.suzana

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.paradoxo.suzana.ui.home.ChatScreen
import com.paradoxo.suzana.ui.home.ChatViewModel
import com.paradoxo.suzana.ui.theme.SuzanaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val token = ""

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

                    ChatScreen(state = state) {

                        scope.launch {
                            viewModel.sendMessage()
                        }
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
                }
            }
        }
    }
}
