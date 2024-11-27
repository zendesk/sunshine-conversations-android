package io.smooch.demoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.smooch.core.Smooch
import io.smooch.demoapp.databinding.ActivityUpdateConversationBinding
import io.smooch.demoapp.utils.setupToolbar
import javax.net.ssl.HttpsURLConnection

class UpdateConversationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityUpdateConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding.updateConversationActivityToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (!updateConversationDetails(binding = binding)) {
            Toast.makeText(this, "Need to show conversation first", Toast.LENGTH_SHORT).show()
            return
        }

        binding.buttonReset.setOnClickListener {
            binding.conversationName.text.clear()
            binding.conversationDescription.text.clear()
        }

        binding.buttonUpdateConversation.setOnClickListener {

            val newName = binding.conversationName.text.toString()
            val newDesc = binding.conversationDescription.text.toString()

            Toast.makeText(this, "Updating Conversation...", Toast.LENGTH_SHORT).show()

            Smooch.getConversation()?.id?.let { currentConversationId ->
                Smooch.updateConversationById(
                    currentConversationId,
                    newName,
                    newDesc,
                    null,
                    null
                ) {
                    if (it.status == HttpsURLConnection.HTTP_CREATED || it.status == HttpsURLConnection.HTTP_OK) {
                        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                        Smooch.loadConversation(currentConversationId) {
                            Toast.makeText(this, "Conversation Reloaded", Toast.LENGTH_SHORT).show()
                            updateConversationDetails(binding = binding)
                        }
                    } else {
                        Toast.makeText(this, "Error Creating Conversation $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
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

    @SuppressLint("SetTextI18n")
    private fun updateConversationDetails(binding: ActivityUpdateConversationBinding) =
        if (Smooch.getConversation() != null) {
            val currentConversation = Smooch.getConversation()
            binding.currentConversationId.text =
                "Current conversation:\nID = [${currentConversation!!.id}]" +
                        "\nName = [${currentConversation.displayName}]" +
                        "\nDesc = [${currentConversation.description}]"
            true
        } else {
            false
        }

}