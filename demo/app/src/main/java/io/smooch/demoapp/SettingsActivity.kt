package io.smooch.demoapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.smooch.core.Settings
import io.smooch.core.Smooch
import io.smooch.demoapp.databinding.ActivitySettingsBinding
import io.smooch.demoapp.utils.setupToolbar

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding.settingsActivityToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonInit.setOnClickListener {
            val integrationId = binding.editTextIntegrationId.text.toString()
            if (integrationId.isNotEmpty()) {
                val message = "Smooch re-initialized with id $integrationId"
                Toast.makeText(this@SettingsActivity, message, Toast.LENGTH_LONG).show()

                // This is only for showcase purposes, you should always
                // initialize Smooch from the Application class
                Smooch.init(this@SettingsActivity.application, Settings(integrationId), null)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var handled = false
        if (item.itemId == android.R.id.home) {
            finish()
            handled = true
        }
        return handled || super.onOptionsItemSelected(item)
    }

}