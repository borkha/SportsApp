package nic.com.sportsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ModelClass.RPlayer;

public class RegistrationLinkPlayer extends AppCompatActivity {
    EditText nameRegistration, emailRegistration, phoneRegistration, sportRegistration, ageRegistration;
    TextView gameRegistration;
    Button btnRegistration, backRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_playerl_link);

        nameRegistration = findViewById(R.id.nameRegistration);
        emailRegistration = findViewById(R.id.emailRegistration);
        phoneRegistration = findViewById(R.id.phoneRegistration);
        sportRegistration = findViewById(R.id.sportRegistration);
        ageRegistration = findViewById(R.id.ageRegistration);
        gameRegistration = findViewById(R.id.gameRegistration);
        backRegister = findViewById(R.id.backRegister);
        btnRegistration = findViewById(R.id.btnRegistration);
        final String GameName = getIntent().getStringExtra("gameName");
        gameRegistration.setText(GameName);


        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationLinkPlayer.this);
                builder.setTitle(" Registration Successful");
                builder.setMessage(" Do You Want to Really Exit? ");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();

                String name = nameRegistration.getText().toString().trim();
                String age = ageRegistration.getText().toString().trim();
                String sport = sportRegistration.getText().toString().trim();
                String phoneNo = phoneRegistration.getText().toString().trim();
                String emailId = emailRegistration.getText().toString().trim();

                if (name.isEmpty()) {
                    nameRegistration.setError("Input Required");
                    nameRegistration.requestFocus();
                    return;
                }

                if (age.isEmpty()) {
                    ageRegistration.setError("Input Required");
                    ageRegistration.requestFocus();
                    return;
                }

                if (sport.isEmpty()) {
                    sportRegistration.setError("Input Required");
                    sportRegistration.requestFocus();
                    return;
                }

                if (phoneNo.isEmpty()) {
                    phoneRegistration.setError("Input Required");
                    phoneRegistration.requestFocus();
                    return;
                }

                if (emailId.isEmpty()) {
                    emailRegistration.setError("Input Required");
                    emailRegistration.requestFocus();
                    return;
                }

                RPlayer rplayer = new RPlayer(name, emailId, phoneNo, sport, age);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("Registered Players For Tournaments");
                reference.child(sport).push().setValue(rplayer).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RegistrationLinkPlayer.this, "Successfully Registered for the Tournament", Toast.LENGTH_SHORT).show();
                    }
                });
                nameRegistration.setText("");
                ageRegistration.setText("");
                sportRegistration.setText("");
                phoneRegistration.setText("");
                emailRegistration.setText("");
            }
        });

        backRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationLinkPlayer.this,PlayerDashboard.class));
                Toast.makeText(RegistrationLinkPlayer.this,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}


