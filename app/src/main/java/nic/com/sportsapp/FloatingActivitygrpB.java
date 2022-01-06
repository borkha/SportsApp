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

public class FloatingActivitygrpB extends AppCompatActivity
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
        setContentView(R.layout.activity_float_grp_b);

                listS = new ArrayList<>();
                players = new String[2];

                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Registered Players For Tournaments").child("Badminton").child("badminton").child("group B");
                SPlayerOne = findViewById(R.id.FRPlayer1grpB);
                SPlayerTwo = findViewById(R.id.FRPlayer2grpB);
                sportSemi= findViewById(R.id.sportFRgrpB);

                Svs = findViewById(R.id.vssgrpB);
                SSubmit = findViewById(R.id.FSubmitB);
                SemiMatch= findViewById(R.id.FmatchB);

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

                    Automatch matchModel = new Automatch(playerOne,vs,playerTwo ,Sport);
                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                    DatabaseReference reference=firebaseDatabase.getReference("First Round Matches");
                    reference.child("Group B").push().setValue(matchModel).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(FloatingActivitygrpB.this,"AutoMatched Successfully",Toast.LENGTH_SHORT).show();
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
                Log.d("TAG", "automatch: clicked automatch");
                randomise(listS);
            }

            private void randomise(List<String> listS) {
                Random random = new Random();
                Log.d("TAG", "players in group: " + listS);
                for (int i = 0; i < listS.size(); i++) {
                    //gets a random index of the array
                    int indexa = random.nextInt(listS.size());
                    //adds the value of random index to another array(to stores player1 vs player2)
                    players[0] = listS.get(indexa);
                    //removes that index from the array to avoid redundant players in automatching
                    listS.remove(indexa);
                    String player = players[0];
                    //gets a random index of the array
                    int indexb = random.nextInt(listS.size());
                    //adds the value of random index to another array(to stores player1 vs player2)
                    players[1] = listS.get(indexb);
                    //removes that index from the array to avoid redundant players in automatching
                    listS.remove(indexb);
                    String playerOne = players[1];
                    Log.d("TAG", "randomise: " + players[0] + " " + players[1]);

                    SPlayerOne.setText(player);
                    SPlayerTwo.setText(playerOne);
                }
            }
        }