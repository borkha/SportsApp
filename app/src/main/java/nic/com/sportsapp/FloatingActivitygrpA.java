package nic.com.sportsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ModelClass.Automatch;

public class FloatingActivitygrpA extends AppCompatActivity {
    EditText FPlayerOne, FPlayerTwo, VS, sportF;
    Button Fmatch, FSubmit;
    List<String> listA;
    private String[] players;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_grp_a);

        listA = new ArrayList<>();
        players = new String[2];
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Registered Players For Tournaments").child("Badminton").child("badminton").child("group A");
        FPlayerOne = findViewById(R.id.FRPlayer1grpA);
        FPlayerTwo = findViewById(R.id.FRPlayer2grpA);
        sportF = findViewById(R.id.sportFR);
        VS = findViewById(R.id.vssgrpA);
        FSubmit = findViewById(R.id.FSubmit);
        Fmatch = findViewById(R.id.Fmatch);

        FSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerOne = FPlayerOne.getText().toString().trim();
                String vs = VS.getText().toString().trim();
                String playerTwo = FPlayerTwo.getText().toString().trim();
                String Sport = sportF.getText().toString().trim();

                if (playerOne.isEmpty()) {
                    FPlayerOne.setError("Input Required");
                    FPlayerOne.requestFocus();
                    return;
                }
                if (vs.isEmpty()) {
                    VS.setError("Input Required");
                    VS.requestFocus();
                    return;
                }
                if (playerTwo.isEmpty()) {
                    FPlayerTwo.setError("Input Required");
                    FPlayerTwo.requestFocus();
                    return;
                }
                if (Sport.isEmpty()) {
                    sportF.setError("Input Required");
                    sportF.requestFocus();
                    return;
                }

                Automatch automatch = new Automatch(playerOne, vs, playerTwo, Sport);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("First Round Matches");
                reference.child("GroupA").push().setValue(automatch).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(FloatingActivitygrpA.this, "AutoMatched Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                FPlayerOne.setText("");
                VS.setText("");
                FPlayerTwo.setText("");
                sportF.setText("");
            }
        });
        Fmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String item = dataSnapshot.child("name").getValue(String.class);
                                listA.add(item);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                automatch(listA);
            }
        });

    }

    private void automatch(List<String> listA)
    {
        randomise(listA);
    }

    private void randomise(List<String> listA) {
        Random random = new Random();

        for (int i = 0; i < listA.size(); i++) {
            int indexa = random.nextInt(listA.size());
            players[0] = listA.get(indexa);
            listA.remove(indexa);
            String player = players[0];
            int indexb = random.nextInt(listA.size());
            players[1] = listA.get(indexb);
            listA.remove(indexb);
            String playerOne = players[1];
            Log.d("TAG", "randomise: " + players[0] + " " + players[1]);

            FPlayerOne.setText(player);
            FPlayerTwo.setText(playerOne);
        }
    }
}
