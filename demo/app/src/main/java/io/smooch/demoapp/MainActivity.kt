package io.smooch.demoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.smooch.core.ConversationViewDelegateAdapter
import io.smooch.core.Logger
import io.smooch.core.Smooch
import io.smooch.core.User
import io.smooch.demoapp.databinding.ActivityMainBinding
import io.smooch.demoapp.utils.setupToolbar
import io.smooch.features.conversationlist.ConversationListActivity
import io.smooch.ui.ConversationActivity
import java.util.Date

class MainActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "MainActivity"
        private const val DATE = 1420070400000L // 2015-01-01 00:00:00 UTC
        private const val KARMA = 1337
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding.mainActivityToolbar)

        binding.buttonShow.setOnClickListener {
            ConversationActivity.builder()
                .withStartingText("Hi there! Can you help me please?")
                .show(this)
        }

        binding.buttonManageUser.setOnClickListener {
            startActivity(Intent(this, ManageUserActivity::class.java))
        }

        binding.buttonStartConversation.setOnClickListener {
            Smooch.createConversation(
                "Author Name",
                "Author Description",
                "https://diginomica.com/sites/default/files/images/2020-03/zendesk%20logo%20trans%20740px.png",
                null,
                null,
                null
            ) { response ->
                val message = if (response.error == null) {
                    "Conversation started"
                } else {
                    "Conversation start failed. Error: " + response.error
                }
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonUpdateConversation.setOnClickListener {
            startActivity(Intent(this, UpdateConversationActivity::class.java))
        }

        binding.buttonConversationsList.setOnClickListener {
            ConversationListActivity.builder()
                .showCreateConversationButton(true)
                .show(this)
        }

        binding.buttonSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.buttonCustomConversationFlow.setOnCheckedChangeListener { _, isChecked ->
            val message = "Intercept Conversation $isChecked"
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            Smooch.setConversationViewDelegate(
                object : ConversationViewDelegateAdapter() {
                    override fun onStartActivityCalled(intent: Intent) {
                        Logger.i(LOG_TAG, "onStartActivityCalled - $intent")
                    }

                    override fun onRequestPermissionsCalled(permissions: Array<String>) {
                        Logger.i(LOG_TAG, "onRequestPermissionsCalled")
                    }

                    override fun shouldCreateCustomConversationFlow(): Boolean {
                        return isChecked
                    }

                    override fun onCreateConversationClick() {
                        val intent = Intent(this@MainActivity, InterceptActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            )
        }

        addSomeProperties(User.getCurrentUser())
    }

    private fun addSomeProperties(user: User) {
        val customProperties: MutableMap<String, Any> = HashMap()

        // Identify user with default properties
        user.firstName = "Demo"
        user.lastName = "App"
        user.email = "demo.app@smooch.io"
        user.signedUpAt = Date(DATE)

        // Add your own custom properties
        customProperties["Last Seen"] = Date()
        customProperties["Awesome"] = true
        customProperties["Karma"] = KARMA
        user.addMetadata(customProperties)
    }
}