package ar.tis.tisar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.util.Log
import android.text.TextUtils

import com.google.firebase.database.FirebaseDatabase

internal class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "FirebaseEmailPassword"

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        btn_email_sign_in.setOnClickListener(this)
        btn_email_create_account.setOnClickListener(this)
        btn_sign_out.setOnClickListener(this)
        btn_verify_email.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        if (mAuth!!.currentUser != null) {
            Log.e("Current User: ", mAuth!!.currentUser.toString())
            navToTripList()
        }
        val currentUser = mAuth!!.currentUser
        edtEmail.setText("test@test.de")
        edtPassword.setText("passwort1")
    }

    override fun onClick(view: View?) {
        val i = view!!.id

        if (i == R.id.btn_email_create_account) {
            createAccount(edtEmail.text.toString(), edtPassword.text.toString())
        } else if (i == R.id.btn_email_sign_in) {
            signIn(edtEmail.text.toString(), edtPassword.text.toString())
        } else if (i == R.id.btn_sign_out) {
            signOut()
        } else if (i == R.id.btn_verify_email) {
            sendEmailVerification()
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.e(TAG, "createAccount:$email")
        if (!validateForm(email, password)) {
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "createAccount: Success!")

                    // update UI with the signed-in user's information
                    val user = mAuth!!.currentUser
                    navToTripList()
                } else {
                    Log.e(TAG, "createAccount: Fail!", task.exception)
                    Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        Log.e(TAG, "signIn:$email")
        if (!validateForm(email, password)) {
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "signIn: Success!")

                    // update UI with the signed-in user's information
                    val user = mAuth!!.currentUser

                    navToTripList()
                } else {
                    Log.e(TAG, "signIn: Fail!", task.exception)
                    Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }

                if (!task.isSuccessful) {
                    tvStatus.text = "Authentication failed!"
                }
            }
    }

    private fun navToTripList() {
        Toast.makeText(this, "ICH HABE MICH EINGELOGGT!", Toast.LENGTH_LONG).show()
        /*val intent = Intent(this@LoginActivity, TripListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)*/
    }

    private fun signOut() {
        mAuth!!.signOut()
    }

    private fun sendEmailVerification() {
        // Disable Verify Email button
        btn_verify_email.isEnabled = false

        val user = mAuth!!.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Re-enable Verify Email button
                btn_verify_email.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Verification email sent to " + user.email!!, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.e(TAG, "sendEmailVerification failed!", task.exception)
                    Toast.makeText(applicationContext, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateForm(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return true
    }
}

