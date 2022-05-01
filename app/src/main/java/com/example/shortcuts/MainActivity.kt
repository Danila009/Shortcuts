package com.example.shortcuts

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shortcuts.ui.theme.ShortcutsTheme
import java.util.*


class MainActivity : ComponentActivity() {
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)

            val webShortcut = ShortcutInfo.Builder(this,"shortcut_web")
                .setShortLabel("google.com")
                .setLongLabel("Open google.com web site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_foreground))
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")))
                .build()

            shortcutManager.dynamicShortcuts = Collections.singletonList(webShortcut);
        }

        setContent {
            ShortcutsTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShortcutsTheme {
        Greeting("Android")
    }
}