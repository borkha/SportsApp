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
import ModelClass.Score;

public class SFfloating extends AppCompatActivity
{
    EditText SPlayerOne, SPlayerTwo ,Svs, sportSemi;
    Button SemiMatch, SSubmit;
    List<String> listS ;
    private String[] players;
    DatabaseReference myRef;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_sf);

        listS = new ArrayList<>();
        players = new String[2];
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Players selected for Semi Final");
        SPlayerOne = findViewById(R.id.Player1Semi);
        SPlayerTwo = findViewById(R.id.Player2Semi);
        sportSemi= findViewById(R.id.sportS);
        Svs = findViewById(R.id.Svss);
        SSubmit = findViewById(R.id.SubmitSemi);
        SemiMatch= findViewById(R.id.Smatch);


        SSubmit.setOnClickListener(view ->
        {
            String playerOne = SPlayerOne.getText().toString().trim();
            String vs = Svs.getText().toString().trim();
            String playerTwo = SPlayerTwo.getText().toString().trim();
            String Sport = sportSemi.getText().toString().trim();

            if(playerOne.isEmpty()){
                SPlayerOne.setError("Input Required");
                SPlayerOne.requestFocus();
                return;
            }
            if(vs.isEmpty()){
                Svs.setError("Input Required");
                Svs.requestFocus();
                return;
            }
            if(playerTwo.isEmpty()){
                SPlayerTwo.setError("Input Required");
                SPlayerTwo.requestFocus();
                return;
            }
            if(Sport.isEmpty()){
                sportSemi.setError("Input Required");
                sportSemi.requestFocus();
                return;
            }
            Automatch automatch = new Automatch(playerOne,vs,playerTwo ,Sport);
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference reference=firebaseDatabase.getReference();
            reference.child("Semi Final Round").push().setValue(automatch).addOnSuccessListener(new OnSuccessListener<Void>()
            {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(SFfloating.this,"AutoMatched Successfully",Toast.LENGTH_SHORT).show();
                }

            });
            SPlayerOne.setText("");
            Svs.setText("");
            SPlayerTwo.setText("");
            sportSemi.setText("");

        });
        SemiMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String item = dataSnapshot.child("name").getValue(String.class);
                                listS.add(item);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                automatch(listS);
            }
        });
    }
    private void automatch(List<String> listS)
    {
        randomise(listS);
    }

    private void randomise(List<String> listS) {
        Random random = new Random();
        Log.d("TAG", "players in group: " + listS);
        for (int i = 0; i < listS.size(); i++) {
            int indexa = random.nextInt(listS.size());
            players[0] = listS.get(indexa);
            listS.remove(indexa);
            String player = players[0];
            int indexb = random.nextInt(listS.size());
            players[1] = listS.get(indexb);
            listS.remove(indexb);
            String playerOne = players[1];
            Log.d("TAG", "randomise: " + players[0] + " " + players[1]);

            SPlayerOne.setText(player);
            SPlayerTwo.setText(playerOne);
        }
    }
}
