package com.paradoxo.suzana.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.paradoxo.suzana.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogAPIKey(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit = {},
    onSavedClick: (String) -> Unit = {},
    onOpenApiSite: () -> Unit = {}
) {
    var textValue by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = onDismissClick,
        content = {
            Column(
                modifier
                    .clip(RoundedCornerShape(5))
                    .heightIn(300.dp)
                    .background(Color.White)
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "OpenAI Key",
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.aviso_1_acesso_api_key),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.aviso_2_acesso_api_key),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = stringResource(R.string.aviso_3_acesso_api_key),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier
                        .clickable { onOpenApiSite() }
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(25)
                        )
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_key),
                            "key",
                            Modifier.padding(4.dp),
                            tint = Color.White,
                        )
                    }

                    Text(
                        stringResource(R.string.gerar_chave_no_site_oficial),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = textValue, onValueChange = { textValue = it },
                    Modifier
                        .fillMaxWidth(),
                    label = { Text(stringResource(R.string.digite_api_key), color = Color.Gray) }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(onClick = onDismissClick) {
                        Text(stringResource(R.string.cancelar), color = MaterialTheme.colorScheme.primary)
                    }

                    TextButton(onClick = { onSavedClick(textValue) }) {
                        Text(stringResource(R.string.salvar), color = MaterialTheme.colorScheme.primary)
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        })
}


@Preview
@Composable
fun AlertDialogAPIKeyPreview() {
    AlertDialogAPIKey()
}