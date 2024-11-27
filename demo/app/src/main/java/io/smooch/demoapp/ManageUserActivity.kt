package io.smooch.demoapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.smooch.core.LoginResult
import io.smooch.core.LogoutResult
import io.smooch.core.Smooch
import io.smooch.core.SmoochCallback
import io.smooch.core.User
import io.smooch.demoapp.databinding.ActivityManageUserBinding
import io.smooch.demoapp.utils.setupToolbar

class ManageUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityManageUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar(binding.manageUserActivityToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonLogout.setOnClickListener {
            Smooch.logout { response -> onLogoutComplete(response) }
        }

        binding.buttonLogin.setOnClickListener {
            val userId = binding.editTextUserId.text.toString()
            val jwt = binding.editTextJwt.text.toString()
            val firstName = binding.editTextFirstName.text.toString()
            val lastName = binding.editTextLastName.text.toString()
            val user = User.getCurrentUser()

            if (firstName.isNotEmpty()) {
                user.firstName = firstName
            }
            if (lastName.isNotEmpty()) {
                user.lastName = lastName
            }

            if (userId.isNotEmpty() && jwt.isNotEmpty()) {
                Smooch.login(userId, jwt) { response -> onLoginComplete(response) }
            } else {
                val message = "userId and JWT can't be empty"
                Toast.makeText(this@ManageUserActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.editTextUserId.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // no implementation is needed
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val userId = binding.editTextUserId.text.toString()
                    binding.buttonLogin.isEnabled = userId.isNotEmpty()
                }

                override fun afterTextChanged(s: Editable?) {
                    // no implementation is needed
                }
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var handled = false
        if (item.itemId == android.R.id.home) {
            finish()
            handled = true
        }
        return handled || super.onOptionsItemSelected(item)
    }

    private fun onLoginComplete(response: SmoochCallback.Response<LoginResult>) {
        runOnUiThread {
            val message = if (response.error == null) {
                "Login successful"
            } else {
                "Login failed. Error: " + response.error
            }
            Toast.makeText(this@ManageUserActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onLogoutComplete(response: SmoochCallback.Response<LogoutResult>) {
        runOnUiThread {
            val message = if (response.error == null) {
                "Logout successful"
            } else {
                "Logout failed. Error: " + response.error
            }
            Toast.makeText(this@ManageUserActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

}