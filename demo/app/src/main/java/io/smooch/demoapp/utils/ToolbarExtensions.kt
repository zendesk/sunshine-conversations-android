package io.smooch.demoapp.utils

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.smooch.ui.utils.WindowInsetsHelper

fun AppCompatActivity.setupToolbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)

    val supportActionBar: ActionBar? = supportActionBar
    if (supportActionBar != null) {
        supportActionBar.show()
        WindowInsetsHelper.applyWindowInsets(toolbar, WindowInsetsHelper.InsetType.TOP)
    }
}