package com.paradoxo.suzana

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.paradoxo.suzana.model.Autor
import com.paradoxo.suzana.model.Message
import com.paradoxo.suzana.ui.home.ChatScreen
import com.paradoxo.suzana.ui.home.ChatScreenUiState
import com.paradoxo.suzana.ui.home.ChatViewModel
import com.paradoxo.suzana.ui.theme.SuzanaTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {

    val token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()


//            val message by remember { mutableStateOf("") }
//            val openAiApi = OpenAiApi(token)
//            LaunchedEffect(key1 = Unit) {
//                scope.launch {
//                    delay(3000)
//                    openAiApi.getResponse("O que é Hello World?")
//                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//                }
//            }

            SuzanaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Greeting("Android")

                    val viewModel by viewModels<ChatViewModel>()
                    val state by viewModel.uiState.collectAsState()

                    ChatScreen(state = state) {
                        viewModel.sendMessage()
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


                    LaunchedEffect(key1 = Unit) {
                        scope.launch {
                            //delay(3000)
                            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuzanaTheme {
        Greeting("Android")
    }
}

