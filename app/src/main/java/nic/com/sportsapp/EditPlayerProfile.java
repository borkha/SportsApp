package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import ModelClass.Player;

public class EditPlayerProfile extends AppCompatActivity
{
    Button Update, back;
    TextInputLayout FullName, email, phnNo, PSport, PGender, PAddress,PDob;
    TextView profile;
    private FirebaseUser player ;
    private String playerID;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_player_profile);

        reference = FirebaseDatabase.getInstance().getReference("Player Details");
        FullName = findViewById(R.id.player_nameEt);
        email = findViewById(R.id.player_emailEt);
        phnNo = findViewById(R.id.player_phoneEt);
        PSport = findViewById(R.id.player_sportEt);
        PGender = findViewById(R.id.player_genderEt);
        PAddress = findViewById(R.id.player_addEt);
        PDob = findViewById(R.id.player_birthEt);
        profile = findViewById(R.id.profile);
        Update = findViewById(R.id.update_player);
        back = findViewById(R.id.back_player);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditPlayerProfile.this,PlayerDashboard.class));
            }
        });
        player = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Player Details");
        playerID = player.getUid();
        final TextInputLayout FullName = findViewById(R.id.player_nameEt);
        final TextInputLayout email =  findViewById(R.id.player_emailEt);
        final TextInputLayout phnNo = findViewById(R.id.player_phoneEt);
        final TextInputLayout PSport = findViewById(R.id.player_sportEt);
        final TextInputLayout PGender = findViewById(R.id.player_genderEt);
        final TextInputLayout PAddress = findViewById(R.id.player_addEt);
        final TextInputLayout PDob = findViewById(R.id.player_birthEt);

        reference.child(playerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player playerProfile = snapshot.getValue(Player.class);
                if (playerProfile !=null)
                {
                    String player_name = playerProfile.name;
                    String player_email = playerProfile.email;
                    String player_sport = playerProfile.sport;
                    String player_Address = playerProfile.address;
                    String player_gender = playerProfile.gender;
                    String player_phoneNo = playerProfile.phoneNo;
                    String player_dob = playerProfile.date;

                    FullName.getEditText().setText(player_name);
                    email.getEditText().setText(player_email);
                    phnNo.getEditText().setText(player_phoneNo);
                    PSport.getEditText().setText(player_sport);
                    PGender.getEditText().setText(player_gender);
                    PAddress.getEditText().setText(player_Address);
                    PDob.getEditText().setText(player_dob);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditPlayerProfile.this,"Something wrong happened", Toast.LENGTH_LONG).show();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void updateUser()
    {
        Map<String, Object> updateUser = new HashMap<>();
        updateUser.put("name", FullName.getEditText().getText().toString());
        updateUser.put("email", email.getEditText().getText().toString());
        updateUser.put("address", PAddress.getEditText().getText().toString());
        updateUser.put("sport", PSport.getEditText().getText().toString());
        updateUser.put("gender", PGender.getEditText().getText().toString());
        updateUser.put("phoneNo", phnNo.getEditText().getText().toString());
        updateUser.put("date", PDob.getEditText().getText().toString());

        FirebaseDatabase.getInstance().getReference("Player Details").child(playerID).updateChildren(updateUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditPlayerProfile.this,"Updated",Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditPlayerProfile.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
