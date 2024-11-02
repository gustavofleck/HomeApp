package com.example.homeapp.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ds.components.DSHeader
import com.example.ds.components.DSPrimaryButton
import com.example.ds.theme.DesignSystemTheme
import com.example.homeapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

const val RC_SIGN_IN = 9001

class FirebaseAuthenticationLogin : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupGoogleSignIn()

        enableEdgeToEdge()
        setContent {
            DesignSystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(modifier = Modifier.padding(innerPadding)) {
                        val signInIntent = googleSignInClient.signInIntent
                        startActivityForResult(signInIntent, RC_SIGN_IN)
                    }
                }
            }
        }

    }

    private fun setupGoogleSignIn() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("487875437493-r3qgd9sen877nnq32t40v6pgnvg7j3p4.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }
}

@Composable
fun Login(modifier: Modifier = Modifier, signIn: () -> Unit) {
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        DSHeader(title = "Home App") {}
        Image(painterResource(id = R.drawable.home_app_launcher_icon), contentDescription = "Home App Launcher Icon")
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