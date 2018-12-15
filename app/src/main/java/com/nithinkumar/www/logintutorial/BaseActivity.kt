package com.nithinkumar.www.logintutorial

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

/**
 * This is base class which takes care of the googleSignInClient and other things required globally
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * This is GoogleSignInClient used which can hold a null value
     */
    var mGoogleSignInClient: GoogleSignInClient? = null

    /**
     * Configure sign-in to request the user's ID, email address, and basic profile. ID and basic profile are included in DEFAULT_SIGN_IN.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val googleSignInOptions: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    /**
     * This method handles the signin result as to what needs to be done when
     */
    protected fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("handleSignInResult", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    internal fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            val intent = Intent(this, DetailsTutorialActivity::class.java)
            intent.putExtra("accountName", account.displayName)
            intent.putExtra("accountEmail", account.email)
            intent.putExtra("accountImage", account.photoUrl.toString())
            startActivity(intent)
        }
    }
}
