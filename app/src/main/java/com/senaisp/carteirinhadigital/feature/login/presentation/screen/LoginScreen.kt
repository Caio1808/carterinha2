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
import androidx.compose.foundation.layout.width

private val Background = Color(0xFF282828)
private val White = Color(0xFF504C43)
private val Border = Color(0xFFFFFFFF)
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
            painter = painterResource(id = R.drawable.fundo2),
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
            Spacer(
                modifier = Modifier.height(50.dp)
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
                modifier = Modifier.height(110.dp)
            )

            // E-mail
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {



                OutlinedTextField(
                    value = uiState.usuario,
                    onValueChange = { value ->
                        viewModel.onEvent(LoginEvent.OnUsuarioChange(value))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,

                    // Texto indicativo dentro do campo
                    placeholder = {
                        Text(
                            text = "Email",
                            color = Color.Black
                        )
                    },

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Border,
                        unfocusedBorderColor = Border,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(55.dp)
            )

            // Senha
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {


                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                OutlinedTextField(
                    value = uiState.senha,
                    onValueChange = {
                        viewModel.onEvent(LoginEvent.OnSenhaChange(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,

                    // Texto indicativo dentro do campo
                    placeholder = {
                        Text(
                            text = "Senha",
                            color = Color.Black
                        )
                    },

                    visualTransformation = PasswordVisualTransformation(),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Border,
                        unfocusedBorderColor = Border,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(62.dp)
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
                    .width(140.dp)
                    .height(42.dp),
                shape = RoundedCornerShape(1.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White,
                    contentColor = Color.White
                )
            ) {

                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                } else {
                    Text(text = "Entrar", style = MaterialTheme.typography.titleMedium, color = Color.White)
                }
            }



            Spacer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}
//Código feito por Caio Gogojoli