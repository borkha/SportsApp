package nic.com.sportsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ModelClass.Score;
import ModelClass.autoModel;

public class SFScore extends AppCompatActivity
{
    EditText Splayername1,Splayername2,Splayerscore1,Splayerscore2,SemiTournament,Swinnername, Semilossgrp;
    Button Sdeclarewinner, Semisubmit,losSemibtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sf_score);

        Splayername1= findViewById(R.id.SemiplayerName1);
        Splayername2= findViewById(R.id.semiplayername2);
        Splayerscore1= findViewById(R.id.semiplayerscore1);
        Splayerscore2= findViewById(R.id.semiplayerscore2);
        SemiTournament= findViewById(R.id.SemiTournament);
        Swinnername= findViewById(R.id.Semiwinner);
        Semilossgrp= findViewById(R.id.Semilose);
        Sdeclarewinner= findViewById(R.id.SemideclareWinner);
        Semisubmit= findViewById(R.id.Semisubmit);
        losSemibtn= findViewById(R.id.losSemibtn);

        losSemibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Semilossgrp.getText().toString().trim();

                if(name.isEmpty())
                {
                    Semilossgrp.setError("Input Required");
                    Semilossgrp.requestFocus();
                    return;
                }

                autoModel auto = new autoModel(name);
                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Players Playing For Third Position").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SFScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                Semilossgrp.setText("");
            }
        });
        Sdeclarewinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Swinnername.getText().toString().trim();

                if(name.isEmpty()){
                    Swinnername.setError("Input Required");
                    Swinnername.requestFocus();
                    return;
                }
                autoModel auto = new autoModel(name);
                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Players selected for Final").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SFScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });

                Swinnername.setText("");
            }
        });

        Semisubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstplayer = Splayername1.getText().toString().trim();
                String ScoreFirstplayer = Splayerscore1.getText().toString().trim();
                String SecondPlayer = Splayername2.getText().toString().trim();
                String ScoreSecondplayer = Splayerscore2.getText().toString().trim();
                String TournamentName = SemiTournament.getText().toString().trim();
                String Groupname = SemiTournament.getText().toString().trim();

                if(Firstplayer.isEmpty()){
                    Splayername1.setError("Input Required");
                    Splayername1.requestFocus();
                    return;
                }
                if(ScoreFirstplayer.isEmpty()){
                    Splayerscore1.setError("Input Required");
                    Splayerscore1.requestFocus();
                    return;
                }

                if(SecondPlayer.isEmpty()){
                    Splayername2.setError("Input Required");
                    Splayername2.requestFocus();
                    return;
                }

                if(ScoreSecondplayer.isEmpty()){
                    Splayerscore2.setError("Input Required");
                    Splayerscore2.requestFocus();
                    return;
                }

                if(TournamentName.isEmpty()){
                    SemiTournament.setError("Input Required");
                    SemiTournament.requestFocus();
                    return;
                }
                if(Groupname.isEmpty()){
                    SemiTournament.setError("Input Required");
                    SemiTournament.requestFocus();
                    return;
                }

                Score score = new Score(Firstplayer,ScoreFirstplayer,SecondPlayer,ScoreSecondplayer,TournamentName,Groupname);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference reference=firebaseDatabase.getReference();
                reference.child("Scores Secured In SemiFinal Round").push().setValue(score).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SFScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                Splayername1.setText("");
                Splayerscore1.setText("");
                Splayername2.setText("");
                Splayerscore2.setText("");
                SemiTournament.setText("");
            }
        });
    }
}
