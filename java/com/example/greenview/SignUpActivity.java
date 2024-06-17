package com.example.greenview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.Button;
import android.view.MotionEvent;

public class SignUpActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editTextID, editTextName, editTextEmail, editTextPassword, editTextCity, editTextFarmland;
    private RadioGroup radioGroupFarmland;


    @SuppressLint({"MissingSuperCall", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextID = findViewById(R.id.id_number);
        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextCity = findViewById(R.id.city);
        radioGroupFarmland = findViewById(R.id.radioGroup);
        dbHelper = new DatabaseHelper(this);

        Button buttonSignUp = findViewById(R.id.signup_button);

        // Set OnClickListener for the Sign Up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call registerUser method when Sign Up button is clicked
                registerUser(v);
                Intent intent = new Intent(SignUpActivity.this, MapActivityWithUser.class);
                startActivity(intent);
            }
        });

        // Initialize the back button and set its click listener
        ImageButton buttonBack = findViewById(R.id.backButton);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and go back to the main activity
                finish();
            }
        });
    }
            public void registerUser(View view) {

                String id = editTextID.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String city = editTextCity.getText().toString().trim();
                int farmland = radioGroupFarmland.getCheckedRadioButtonId() == R.id.radioButtonYes ? 1 : 0;

                if (!email.isEmpty() && !password.isEmpty() && !id.isEmpty() && !name.isEmpty() && !city.isEmpty()) {

                    long userId = dbHelper.addUser(id, name, email, password, city, farmland);
                    if (userId != -1) {
                        // User added successfully
                        // You can show a success message or navigate to another screen
                        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // Error adding user
                        // You can show an error message
                        Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show();
                    }
                    editTextID.setText("");
                    editTextName.setText("");
                    editTextEmail.setText("");
                    editTextPassword.setText("");
                    editTextCity.setText("");
                    radioGroupFarmland.clearCheck();
                } else {
                    // something is empty
                    // You can show an error message
                    Toast.makeText(this, "Fill all the parameters", Toast.LENGTH_SHORT).show();
                }
            }
        }
