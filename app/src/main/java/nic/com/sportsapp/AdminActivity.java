package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView registerAdmin;
    private EditText editTextEmail , editTextPassword;
    private Button loginAdmin,homeAdmin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        loginAdmin = (Button) findViewById(R.id.loginAdmin);
        loginAdmin.setOnClickListener(this::onClick);
        homeAdmin = findViewById(R.id.homeAdmin);
        editTextEmail = (EditText) findViewById(R.id.emailAdmin);
        editTextPassword = (EditText) findViewById(R.id.passwordAdmin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        registerAdmin= findViewById(R.id.registerAdmin);

        homeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,MainActivity.class));
            }
        });
    }

    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.loginAdmin:
                loginAdmin();
                break;
        }
    }
    private void loginAdmin()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Please enter email");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //dashboard
                    startActivity(new Intent(AdminActivity.this, AdminDashboard.class));
                    progressBar.setVisibility(View.GONE);

                }else{
                    Toast.makeText(AdminActivity.this,"Failed to login,Please check credentials",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
