package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import ModelClass.Admin;

public class RegisterAdmin extends AppCompatActivity implements View.OnClickListener {
    private EditText adminEmail, adminPassword, adminName, adminAge, adminPhn;
    private Button registerAdmin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        registerAdmin = findViewById(R.id.registerAdmin);
        registerAdmin.setOnClickListener(this);
        adminEmail = findViewById(R.id.adminEmail);
        adminPassword = findViewById(R.id.adminPassword);
        adminName = findViewById(R.id.adminName);
        adminAge = findViewById(R.id.adminAge);
        adminPhn = findViewById(R.id.adminPhn);
        progressBar = findViewById(R.id.progressBarRegister);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerAdmin:
                registerAdmin();
                break;
        }
    }

    private void registerAdmin() {
        String email = adminEmail.getText().toString().trim();
        String password = adminPassword.getText().toString().trim();
        String fullname = adminName.getText().toString().trim();
        String age = adminAge.getText().toString().trim();
        String phoneNo = adminPhn.getText().toString().trim();

        if (fullname.isEmpty()) {
            adminName.setError("Full name required");
            adminName.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            adminAge.setError("Age required");
            adminAge.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            adminEmail.setError("Email is required");
            adminEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            adminEmail.setError("Please provide valid email");
            adminEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            adminPassword.setError("Password required");
            adminPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            adminPassword.setError("Min password length should be 6 characters");
            adminPassword.requestFocus();
            return;
        }
        if (phoneNo.isEmpty()) {
            adminPhn.setError("Phone No required");
            adminPhn.requestFocus();
            return;
        }
        if (phoneNo.length() < 10) {
            adminPhn.setError("length required 10 characters");
            adminPhn.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Admin admin = new Admin(fullname, age, email, phoneNo);
                            FirebaseDatabase.getInstance().getReference("Admin").child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid()).setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterAdmin.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterAdmin.this, AdminActivity.class));
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(RegisterAdmin.this, "Failed to Register! Try Again", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterAdmin.this, "Failed to Register! Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
