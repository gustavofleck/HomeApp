package com.example.homeapp.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ds.components.DSHeader
import com.example.ds.components.DSInputText
import com.example.ds.components.DSPrimaryButton
import com.example.ds.theme.DesignSystemTheme
import com.example.homeapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

private var email: String = ""
private var password: String = ""

internal class FirebaseAuthenticationLogin : AppCompatActivity() {

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesignSystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(modifier = Modifier.padding(innerPadding)) {
                        setupSignIn()
                    }
                }
            }
        }
    }

    private fun setupSignIn() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

}

@Composable
fun Login(modifier: Modifier = Modifier, signIn: () -> Unit) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        DSHeader(title = "Home App") {}
        Row {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Image(painterResource(id = R.drawable.home_app_launcher_icon), contentDescription = null)
                DSInputText(placeholder = "Email", onValueChange = {
                    email = it
                })
                DSInputText(placeholder = "Password", onValueChange = {
                    password = it
                })
            }
        }
        DSPrimaryButton(text = "Sign in", signIn)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DesignSystemTheme {
       Login() {}
    }
}