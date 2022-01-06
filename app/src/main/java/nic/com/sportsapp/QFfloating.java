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

public class QFfloating extends AppCompatActivity
{
    EditText QPlayerOne, QPlayerTwo ,Qvs, sportQ;
    Button Qmatch, QSubmit;
    private List<String> listQ;
    private String[] players;
    DatabaseReference myRef;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_qf);

        listQ = new ArrayList<>();
        players = new String[2];
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Players selected for Quarter Final");

        QPlayerOne = findViewById(R.id.QPlayer1);
        QPlayerTwo = findViewById(R.id.QPlayer2);
        sportQ= findViewById(R.id.sportQ);
        Qvs = findViewById(R.id.Qvss);
        QSubmit = findViewById(R.id.QSubmit);
        Qmatch= findViewById(R.id.Qmatch);

        QSubmit.setOnClickListener(view ->
        {
            String playerOne = QPlayerOne.getText().toString().trim();
            String vs = Qvs.getText().toString().trim();
            String playerTwo = QPlayerTwo.getText().toString().trim();
            String Sport = sportQ.getText().toString().trim();

            if(playerOne.isEmpty()){
                QPlayerOne.setError("Input Required");
                QPlayerOne.requestFocus();
                return;
            }
            if(vs.isEmpty()){
                Qvs.setError("Input Required");
                Qvs.requestFocus();
                return;
            }

            if(playerTwo.isEmpty()){
                QPlayerTwo.setError("Input Required");
                QPlayerTwo.requestFocus();
                return;
            }

            if(Sport.isEmpty()){
                sportQ.setError("Input Required");
                sportQ.requestFocus();
                return;
            }
            Automatch automatch = new Automatch(playerOne,vs,playerTwo ,Sport);
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference reference=firebaseDatabase.getReference();
            reference.child("Quarter Final Round").push().setValue(automatch).addOnSuccessListener(new OnSuccessListener<Void>()
            {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(QFfloating.this,"AutoMatched Successfully",Toast.LENGTH_SHORT).show();
                }
            });

            QPlayerOne.setText("");
            Qvs.setText("");
            QPlayerTwo.setText("");
            sportQ.setText("");

        });
        Qmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String item = dataSnapshot.child("name").getValue(String.class);
                                listQ.add(item);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                automatch(listQ);
            }
        });
    }
    private void automatch(List<String> listQ)
    {
        randomise(listQ);
    }
    private void randomise(List<String> listQ) {
        Random random = new Random();
        Log.d("TAG", "players in group: " + listQ);
        for (int i = 0; i < listQ.size(); i++) {
            //gets a random index of the array
            int indexa = random.nextInt(listQ.size());
            //adds the value of random index to another array(to stores player1 vs player2)
            players[0] = listQ.get(indexa);
            //removes that index from the array to avoid redundant players in automatching
            listQ.remove(indexa);
            String player = players[0];
            //gets a random index of the array
            int indexb = random.nextInt(listQ.size());
            //adds the value of random index to another array(to stores player1 vs player2)
            players[1] = listQ.get(indexb);
            //removes that index from the array to avoid redundant players in automatching
            listQ.remove(indexb);
            String playerOne = players[1];
            Log.d("TAG", "randomise: " + players[0] + " " + players[1]);

            QPlayerOne.setText(player);
            QPlayerTwo.setText(playerOne);
        }
    }
}
