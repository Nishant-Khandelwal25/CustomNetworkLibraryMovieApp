package com.example.customnetworkapimovie.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.customnetworkapimovie.ui.theme.SMALL_PADDING
import com.example.customnetworkapimovie.ui.theme.topAppBarBackgroundColor
import com.example.customnetworkapimovie.ui.theme.topAppBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector? = null, onBackButtonClicked: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(text = title, color = topAppBarContentColor)
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topAppBarBackgroundColor
        ),
        navigationIcon = {
            if (icon != null) Icon(
                imageVector = icon,
                contentDescription = "Arrow Back",
                tint = topAppBarContentColor,
                modifier = Modifier.padding(horizontal = SMALL_PADDING)
                    .clickable {
                    onBackButtonClicked.invoke()
                }
            )
        }
    )
}