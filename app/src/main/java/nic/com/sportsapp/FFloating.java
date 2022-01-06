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

public class FFloating extends AppCompatActivity
{
    EditText FinalPlayerOne, FinalPlayerTwo, Fvs, sportFinal;
    Button Finalmatch, FinalSubmit;

    DatabaseReference myRef;
    FirebaseDatabase database;
    private List<String> list;
    private String[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_final);

        list = new ArrayList<>();
        players = new String[2];
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Players selected for Final");
        FinalPlayerOne = findViewById(R.id.FinalPlayer1);
        FinalPlayerTwo = findViewById(R.id.FinalPlayer2);
        sportFinal = findViewById(R.id.sportFinal);
        Fvs = findViewById(R.id.Finalvss);
        FinalSubmit = findViewById(R.id.FinalSubmit);
        Finalmatch = findViewById(R.id.Finalmatch);

        FinalSubmit.setOnClickListener(view ->
        {
            String playerOne = FinalPlayerOne.getText().toString().trim();
            String vs = Fvs.getText().toString().trim();
            String playerTwo = FinalPlayerTwo.getText().toString().trim();
            String Sport = sportFinal.getText().toString().trim();

            if (playerOne.isEmpty()) {
                FinalPlayerOne.setError("Input Required");
                FinalPlayerOne.requestFocus();
                return;
            }
            if (vs.isEmpty()) {
                Fvs.setError("Input Required");
                Fvs.requestFocus();
                return;
            }

            if (playerTwo.isEmpty()) {
                FinalPlayerTwo.setError("Input Required");
                FinalPlayerTwo.requestFocus();
                return;
            }

            if (Sport.isEmpty()) {
                sportFinal.setError("Input Required");
                sportFinal.requestFocus();
                return;
            }

            Automatch automatch = new Automatch(playerOne, vs, playerTwo, Sport);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference reference = firebaseDatabase.getReference();
            reference.child(" Final Round ").push().setValue(automatch).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(FFloating.this, "AutoMatched Successfully", Toast.LENGTH_SHORT).show();
                }
            });
            FinalPlayerOne.setText("");
            Fvs.setText("");
            FinalPlayerTwo.setText("");
            sportFinal.setText("");
        });

        Finalmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String item = dataSnapshot.child("name").getValue(String.class);
                                list.add(item);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                automatch(list);
            }
        });
    }
    private void automatch(List<String> list) {
        randomise(list);

    }
    private void randomise(List<String> list) {
        Random random = new Random();
        Log.d("TAG", "players in group: " + list);
        for (int i = 0; i < list.size(); i++) {
            int indexa = random.nextInt(list.size());
            players[0] = list.get(indexa);
            list.remove(indexa);
            String player = players[0];
            int indexb = random.nextInt(list.size());
            players[1] = list.get(indexb);
            list.remove(indexb);
            String playerOne = players[1];
            Log.d("TAG", "randomise: "+ players[0] + " "+ players[1]);
            FinalPlayerOne.setText(player);
            FinalPlayerTwo.setText(playerOne);

        }
    }
}
