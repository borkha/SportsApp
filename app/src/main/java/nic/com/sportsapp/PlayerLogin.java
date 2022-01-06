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

public class PlayerLogin extends AppCompatActivity
{
    private TextView ForgotPassPlayer,playerRegister;
    private EditText PlayerEmail , playerPassword;
    private Button loginPlayer;
    private FirebaseAuth mAuth;
    private ProgressBar progressBarPlayerLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        loginPlayer = (Button) findViewById(R.id.loginPlayer);
        loginPlayer.setOnClickListener(this::onClick);
        PlayerEmail = (EditText) findViewById(R.id.PlayerEmail);
        playerPassword = (EditText) findViewById(R.id.playerPassword);
        progressBarPlayerLogin = (ProgressBar) findViewById(R.id.progressBarPlayerLogin);
        mAuth = FirebaseAuth.getInstance();
        playerRegister= findViewById(R.id.playerRegister);
        playerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayerLogin.this,RegisterPlayer.class));
            }
        });

    }
    private void onClick(View view)
    {
        switch (view.getId()){
            case R.id.loginPlayer:
                loginPlayer();
                break;
        }
    }

    private void loginPlayer()
    {
        String email = PlayerEmail.getText().toString().trim();
        String password = playerPassword.getText().toString().trim();
        if (email.isEmpty()){
            PlayerEmail.setError("Please enter email");
            PlayerEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            PlayerEmail.setError("Please provide valid email");
            PlayerEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            playerPassword.setError("Password is Required");
            playerPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            playerPassword.setError("Min password length is 6 characters");
            playerPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //dashboard
                    startActivity(new Intent(PlayerLogin.this, PlayerDashboard.class));
                    progressBarPlayerLogin.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(PlayerLogin.this,"Failed to login,Please check credentials",Toast.LENGTH_SHORT).show();
                    progressBarPlayerLogin.setVisibility(View.GONE);
                }
            }
        });
    }
}
