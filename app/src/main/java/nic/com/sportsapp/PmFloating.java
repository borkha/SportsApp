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

public class PmFloating extends AppCompatActivity
{
    EditText PmPlayerOne, PmPlayerTwo ,Pmvs, sportPm;
    Button PMmatch, SubmitPayMatch;
    List<String> listPayMatch;
    private String[] players;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_pm);

        listPayMatch = new ArrayList<>();
        players = new String[2];
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Pay Match");
        PmPlayerOne = findViewById(R.id.Player1Pay);
        PmPlayerTwo = findViewById(R.id.Player2PM);
        sportPm= findViewById(R.id.sportPaymatch);
        Pmvs = findViewById(R.id.Pmvss);
        SubmitPayMatch = findViewById(R.id.SubmitpayM);
        PMmatch= findViewById(R.id.Paymatch);

        PMmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String item = dataSnapshot.child("name").getValue(String.class);
                                listPayMatch.add(item);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                automatch(listPayMatch);
            }
        });
        SubmitPayMatch.setOnClickListener(view ->
        {
            String playerOne = PmPlayerOne.getText().toString().trim();
            String vs = Pmvs.getText().toString().trim();
            String playerTwo = PmPlayerTwo.getText().toString().trim();
            String Sport = sportPm.getText().toString().trim();

            if(playerOne.isEmpty()){
                PmPlayerOne.setError("Input Required");
                PmPlayerOne.requestFocus();
                return;
            }
            if(vs.isEmpty()){
                Pmvs.setError("Input Required");
                Pmvs.requestFocus();
                return;
            }

            if(playerTwo.isEmpty()){
                PmPlayerTwo.setError("Input Required");
                PmPlayerTwo.requestFocus();
                return;
            }

            if(Sport.isEmpty()){
                sportPm.setError("Input Required");
                sportPm.requestFocus();
                return;
            }

            Automatch automatch = new Automatch(playerOne,vs,playerTwo ,Sport);
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference reference=firebaseDatabase.getReference();
            reference.child("PayMatch Between Players").push().setValue(automatch).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(PmFloating.this,"AutoMatched Successfully",Toast.LENGTH_SHORT).show();
                }
            });
            PmPlayerOne.setText("");
            Pmvs.setText("");
            PmPlayerTwo.setText("");
            sportPm.setText("");

        });
    }
    private void automatch(List<String> listPayMatch)
    {
        randomise(listPayMatch);
    }

    private void randomise(List<String> listPayMatch) {
        Random random = new Random();
        Log.d("TAG", "players in group: " + listPayMatch);
        for (int i = 0; i < listPayMatch.size(); i++) {
            int indexa = random.nextInt(listPayMatch.size());
            players[0] = listPayMatch.get(indexa);
            listPayMatch.remove(indexa);
            String player = players[0];
            int indexb = random.nextInt(listPayMatch.size());
            players[1] = listPayMatch.get(indexb);
            listPayMatch.remove(indexb);
            String playerOne = players[1];
            Log.d("TAG", "randomise: " + players[0] + " " + players[1]);

            PmPlayerOne.setText(player);
            PmPlayerTwo.setText(playerOne);
        }
    }

}
