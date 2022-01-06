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

public class ThFloating extends AppCompatActivity
{
    EditText TPlayerOne, TPlayerTwo ,Tvs, sportthird;
    Button Tmatch, TSubmit;
    List<String> listTh;
    private String[] players;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_third_pos);

        listTh = new ArrayList<>();
        players = new String[2];

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Players Playing For Third Position");

        TPlayerOne = findViewById(R.id.thirdPlayer1);
        TPlayerTwo = findViewById(R.id.thirdPlayer2);
        sportthird= findViewById(R.id.sportThird);
        Tvs = findViewById(R.id.thirdvss);
        TSubmit = findViewById(R.id.thirdSubmit);

        Tmatch= findViewById(R.id.Thirdmatch);

        TSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerOne = TPlayerOne.getText().toString().trim();
                String vs = Tvs.getText().toString().trim();
                String playerTwo = TPlayerTwo.getText().toString().trim();
                String Sport = sportthird.getText().toString().trim();

                if(playerOne.isEmpty()){
                    TPlayerOne.setError("Input Required");
                    TPlayerOne.requestFocus();
                    return;
                }
                if(vs.isEmpty()){
                    Tvs.setError("Input Required");
                    Tvs.requestFocus();
                    return;
                }

                if(playerTwo.isEmpty()){
                    TPlayerTwo.setError("Input Required");
                    TPlayerTwo.requestFocus();
                    return;
                }

                if(Sport.isEmpty()){
                    sportthird.setError("Input Required");
                    sportthird.requestFocus();
                    return;
                }

                Automatch automatch = new Automatch(playerOne,vs,playerTwo ,Sport);
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference reference=firebaseDatabase.getReference();
                reference.child("Third Position Round").push().setValue(automatch).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ThFloating.this,"AutoMatched Successfully",Toast.LENGTH_SHORT).show();
                    }
                });
                TPlayerOne.setText("");
                Tvs.setText("");
                TPlayerTwo.setText("");
                sportthird.setText("");
            }
        });
        Tmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String item = dataSnapshot.child("name").getValue(String.class);
                                listTh.add(item);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                automatch(listTh);
            }
        });
    }
    private void automatch(List<String> listTh)
    {
        randomise(listTh);
    }

    private void randomise(List<String> listTh) {
        Random random = new Random();
        Log.d("TAG", "players in group: " + listTh);
        for (int i = 0; i < listTh.size(); i++) {
            int indexa = random.nextInt(listTh.size());
            players[0] = listTh.get(indexa);
            listTh.remove(indexa);
            String player = players[0];
            int indexb = random.nextInt(listTh.size());
            players[1] = listTh.get(indexb);
            listTh.remove(indexb);
            String playerOne = players[1];
            Log.d("TAG", "randomise: " + players[0] + " " + players[1]);

            TPlayerOne.setText(player);
            TPlayerTwo.setText(playerOne);
        }

    }
}
