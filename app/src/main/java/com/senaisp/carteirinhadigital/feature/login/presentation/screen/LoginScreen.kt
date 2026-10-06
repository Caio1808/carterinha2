package com.senaisp.carteirinhadigital.feature.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.CircularProgressIndicator
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senaisp.carteirinhadigital.R
import com.senaisp.carteirinhadigital.feature.login.domain.model.UsuarioLogado
import com.senaisp.carteirinhadigital.feature.login.presentation.LoginEvent
import com.senaisp.carteirinhadigital.feature.login.presentation.LoginViewModel
import androidx.compose.foundation.layout.Box

private val Background = Color(0xFF282828)
private val White = Color(0xFFF3F3F3)
private val Border = Color(0xFF969696)
private val TextWhite = Color.White.copy(alpha = 0.85f)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onLoginSucesso: (UsuarioLogado) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.usuarioLogado) {
        uiState.usuarioLogado?.let { usuario ->
            viewModel.onEvent(LoginEvent.OnNavegacaoRealizada)
            onLoginSucesso(usuario)
        }
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Imagem de Fundo
        Image(
            painter = painterResource(id = R.drawable.fundo1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Conteúdo da tela (Campos de E-mail, Senha, Botão, etc.)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.weight(1f)
            )

            // Logo SENAI
            Image(
                painter = painterResource(
                    id = R.drawable.senai_logo
                ),
                contentDescription = "SENAI",
                modifier = Modifier
                    .size(
                        width = 206.dp,
                        height = 55.dp
                    ),
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Portal Educacional",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Acesse sua conta de aluno",
                color = Color.White.copy(alpha = 0.74f),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(70.dp)
            )

            // E-mail
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "E-mail",
                    color = TextWhite,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                OutlinedTextField(
                    value = uiState.usuario,
                    onValueChange = { value ->
                        viewModel.onEvent(LoginEvent.OnUsuarioChange(value))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Border,
                        unfocusedBorderColor = Border,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(23.dp)
            )

            // Senha
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Senha",
                    color = TextWhite,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                OutlinedTextField(
                    value = uiState.senha,
                    onValueChange = { viewModel.onEvent(LoginEvent.OnSenhaChange(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Border,
                        unfocusedBorderColor = Border,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            uiState.erroMessage?.let { error ->
                Text(
                    text = error,
                    color = Color(0xFFFF6B6B),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Botão Entrar
            Button(
                onClick = { viewModel.onEvent(LoginEvent.OnEntrarClick) },
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White,
                    contentColor = Color.Black
                )
            ) {

                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.Black)
                } else {
                    Text(text = "Entrar", style = MaterialTheme.typography.titleMedium, color = Color.Black)
                    Text(text = "›", modifier = Modifier.padding(start = 5.dp), fontSize = 25.sp, color = Color.Black)
                }
            }



            Spacer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}