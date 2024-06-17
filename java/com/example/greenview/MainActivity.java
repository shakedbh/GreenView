package com.example.greenview;  // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signUpTextView = findViewById(R.id.signUpTextView);
        TextView continueWithoutAccountTextView = findViewById(R.id.continue_without);

        String signUpText = getString(R.string.dont_have_account);
        SpannableString signUpSpannableString = new SpannableString(signUpText);
        int signUpStartIndex = signUpText.indexOf("Sign up");
        int signUpEndIndex = signUpStartIndex + "Sign up".length();
        signUpSpannableString.setSpan(new UnderlineSpan(), signUpStartIndex, signUpEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpSpannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        }, signUpStartIndex, signUpEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpTextView.setText(signUpSpannableString);
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance());

        String continueText = "Continue without account";
        SpannableString continueSpannableString = new SpannableString(continueText);
        int continueStartIndex = 0;
        int continueEndIndex = continueText.length();
        continueSpannableString.setSpan(new UnderlineSpan(), continueStartIndex, continueEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        continueSpannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, MapActivityWithoutUser.class);
                startActivity(intent);
            }
        }, continueStartIndex, continueEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        continueWithoutAccountTextView.setText(continueSpannableString);
        continueWithoutAccountTextView.setMovementMethod(LinkMovementMethod.getInstance());

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        dbHelper = new DatabaseHelper(this);

    }

    public void loginUser(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        Toast Toast = null;
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean userExists = dbHelper.checkUser(email, password);
        if (userExists) {
            // User exists, proceed to the next activity
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            editTextEmail.setText("");
            editTextPassword.setText("");
            Intent intent = new Intent(MainActivity.this, MapActivityWithUser.class);
            startActivity(intent);
        } else {
            // User does not exist, show a message
            Toast.makeText(this, "User not found. Please sign up first.", Toast.LENGTH_SHORT).show();
            editTextEmail.setText("");
            editTextPassword.setText("");
        }
    }


}
