package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity
{
    private EditText emailEdittext;
    private Button resetPasswordButton;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth=FirebaseAuth.getInstance();
        emailEdittext=findViewById(R.id.emailPass);
        resetPasswordButton=findViewById(R.id.resetBtn);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }
    private void validateData()
    {
        String email = emailEdittext.getText().toString().trim();

        if(email.isEmpty()){
            emailEdittext.setError("Email is required");
            emailEdittext.requestFocus();
        }else {
            forgotPass();
        }
    }
    private void forgotPass()
    {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Check your Email",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPassword.this,PlayerLogin.class));
                    finish();
                }else {
                    Toast.makeText(ForgotPassword.this,"Error :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
