package com.nithinkumar.www.logintutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton

/**
 * This is my test for Google Sign In
 */
class GoogleSignInTutorialActivity : BaseActivity(), View.OnClickListener {

    /**
     * I have declared the Google SignInOptions here
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_sign_in_tutorial)

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT)
        signInButton.setOnClickListener(this)
    }

    /**
     * This is the click listeners needed
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> signIn()
        }
    }

    /**
     *  Check for existing Google Sign In account, if the user is already signed in the GoogleSignInAccount will be non-null.
     */
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    /**
     * this is the method called when Start Activity For Result is called
     */
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            this.handleSignInResult(task)
        }
    }

    private fun signIn() {
        val signInIntent = this.mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}
