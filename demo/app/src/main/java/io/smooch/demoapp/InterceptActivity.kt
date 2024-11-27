package io.smooch.demoapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.smooch.core.Smooch
import io.smooch.demoapp.databinding.ActivityInterceptBinding
import io.smooch.demoapp.utils.setupToolbar
import io.smooch.ui.ConversationActivity
import javax.net.ssl.HttpsURLConnection

class InterceptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInterceptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding.interceptActivityToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonReset.setOnClickListener {
            binding.conversationName.text.clear()
            binding.conversationDescription.text.clear()
        }

        binding.buttonStartConversation.setOnClickListener {
            Smooch.createConversation(
                binding.conversationName.text.toString(),
                binding.conversationDescription.text.toString(),
                null,
                null,
                null,
                null
            ) {
                if (it.status == HttpsURLConnection.HTTP_CREATED) {
                    val conversation = Smooch.getConversation()
                    ConversationActivity.builder().withConversationId(conversation?.id).show(this)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Error Creating Conversation $it",
                        Toast.LENGTH_SHORT
                    ).show()
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

}