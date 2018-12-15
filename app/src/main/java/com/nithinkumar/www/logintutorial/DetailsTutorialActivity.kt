package com.nithinkumar.www.logintutorial

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * this activity shows the details of the logged in account
 */
class DetailsTutorialActivity : BaseActivity(), View.OnClickListener {

    /**
     * this is where the layout is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_tutorial)

        findViewById<View>(R.id.sign_out_button).setOnClickListener(this)
        findViewById<View>(R.id.disconnect_button).setOnClickListener(this)

        val accountName: TextView? = findViewById(R.id.accountName)
        val accountEmailId = findViewById<TextView?>(R.id.accountEmailId)
        var profilePicURL: String? = null
        val profilePic = findViewById<ImageView>(R.id.profilePic)

        if (intent != null) {
            accountName!!.text = intent.getStringExtra("accountName")
            accountEmailId!!.text = intent.getStringExtra("accountEmail")
            profilePicURL = intent.getStringExtra("accountImage")
        }

        if (profilePicURL != null) {
            GlideApp.with(this)
                .load(profilePicURL)
                .into(profilePic)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.sign_out_button -> signOut()
            R.id.disconnect_button -> revokeAccess()
        }
    }

    private fun signOut() {
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) {
                // [START_EXCLUDE]
                //updateUI(null)
                finish()
                // [END_EXCLUDE]
            }
    }

    private fun revokeAccess() {
        mGoogleSignInClient!!.revokeAccess()
            .addOnCompleteListener(this) {
                // [START_EXCLUDE]
                //updateUI(null)
                finish()
                // [END_EXCLUDE]
            }
    }
}
