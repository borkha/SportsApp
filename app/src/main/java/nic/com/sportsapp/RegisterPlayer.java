package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import ModelClass.Admin;
import ModelClass.Player;

public class RegisterPlayer extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText namePlayer, emailPlayer, passPlayer, phonePlayer, sportPlayer, addPlayer, datePlayer;
    private RadioButton radioMale, radioFemale;
    private Button registerPlayer;
    private ProgressBar progressBar;
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_register);

        registerPlayer = (Button) findViewById(R.id.registerPlayer);
        registerPlayer.setOnClickListener(this);
        namePlayer = (EditText) findViewById(R.id.namePlayer);
        emailPlayer = (EditText) findViewById(R.id.emailPlayer);
        passPlayer = (EditText) findViewById(R.id.passPlayer);
        phonePlayer = (EditText) findViewById(R.id.phonePlayer);
        sportPlayer = (EditText) findViewById(R.id.sportPlayer);
        addPlayer = (EditText) findViewById(R.id.addPlayer);
        datePlayer = (EditText) findViewById(R.id.datePlayer);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBarRegisterP);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerPlayer:
                registerPlayer();
                break;
        }
    }

    private void registerPlayer() {
        String name = namePlayer.getText().toString().trim();
        String email = emailPlayer.getText().toString().trim();
        String password = passPlayer.getText().toString().trim();
        String phoneNo = phonePlayer.getText().toString().trim();
        String sport = sportPlayer.getText().toString().trim();
        String address = addPlayer.getText().toString().trim();
        String date = datePlayer.getText().toString().trim();

        if (name.isEmpty()) {
            namePlayer.setError("Full name required");
            namePlayer.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            emailPlayer.setError("Email is required");
            emailPlayer.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailPlayer.setError("Please provide valid email");
            emailPlayer.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passPlayer.setError("Password required");
            passPlayer.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passPlayer.setError("Min password length should be 6 characters");
            passPlayer.requestFocus();
            return;
        }
        if (phoneNo.isEmpty()) {
            phonePlayer.setError("Phone No required");
            phonePlayer.requestFocus();
            return;
        }
        if (phoneNo.length() < 10) {
            phonePlayer.setError("length required 10 characters");
            phonePlayer.requestFocus();
            return;
        }
        if (sport.isEmpty()) {
            sportPlayer.setError("Sport name required");
            sportPlayer.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            addPlayer.setError("Address is required");
            addPlayer.requestFocus();
            return;
        }
        if (date.isEmpty()) {
            datePlayer.setError("Date is required");
            datePlayer.requestFocus();
            return;
        }
        if (radioMale.isChecked()) {
            gender = "Male";
        }
        if (radioFemale.isChecked()) {
            gender = "Female";
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Player player = new Player(name, email, phoneNo, sport, address, date, gender);
                    FirebaseDatabase.getInstance().getReference("Player Details").child(FirebaseAuth.getInstance().getCurrentUser()
                            .getUid()).setValue(player).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterPlayer.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterPlayer.this, PlayerLogin.class));
                            } else {
                                Toast.makeText(RegisterPlayer.this, "Failed to Register! Try Again", Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    Toast.makeText(RegisterPlayer.this, "Failed to Register! Try Again", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
