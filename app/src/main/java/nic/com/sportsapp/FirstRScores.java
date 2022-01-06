package nic.com.sportsapp;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ModelClass.Groups;
import ModelClass.Score;
import ModelClass.autoModel;

public class FirstRScores  extends AppCompatActivity

{
    EditText playername1,playername2,playerscore1,playerscore2,tournamentname,winnername, groupname;
    Button declarewinner, submitWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_scores);

        playername1= findViewById(R.id.playerName1);
        playername2= findViewById(R.id.playername2);
        playerscore1= findViewById(R.id.playerscore1);
        playerscore2= findViewById(R.id.playerscore2);
        tournamentname= findViewById(R.id.tournamentname);
        winnername= findViewById(R.id.winnername);
        declarewinner= findViewById(R.id.declareWinner);
        submitWinner= findViewById(R.id.submitWinners);
        groupname=findViewById(R.id.groupName);


        declarewinner.setOnClickListener(view ->
        {
            String name = winnername.getText().toString().trim();

            if(name.isEmpty()){
                winnername.setError("Input Required");
                winnername.requestFocus();
                return;
            }

            autoModel auto = new autoModel(name);

            FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
            DatabaseReference ref=firebaseDatabase.getReference();
            ref.child("Players selected for Quarter Final").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>()
            {
                @Override
                public void onSuccess(Void unused)
                {
                    Toast.makeText(FirstRScores.this,"UPDATED",Toast.LENGTH_SHORT).show();
                }
            });

            winnername.setText("");

        });

        submitWinner.setOnClickListener(view ->
        {
            String Firstplayer = playername1.getText().toString().trim();
            String ScoreFirstplayer = playerscore1.getText().toString().trim();
            String SecondPlayer = playername2.getText().toString().trim();
            String ScoreSecondplayer = playerscore2.getText().toString().trim();
            String TournamentName = tournamentname.getText().toString().trim();
            String Groupname = groupname.getText().toString().trim();

            if(Firstplayer.isEmpty()){
                playername1.setError("Input Required");
                playername1.requestFocus();
                return;
            }
            if(ScoreFirstplayer.isEmpty()){
                playerscore1.setError("Input Required");
                playerscore1.requestFocus();
                return;
            }

            if(SecondPlayer.isEmpty()){
                playername2.setError("Input Required");
                playername2.requestFocus();
                return;
            }

            if(ScoreSecondplayer.isEmpty()){
                playerscore2.setError("Input Required");
                playerscore2.requestFocus();
                return;
            }

            if(TournamentName.isEmpty()){
                tournamentname.setError("Input Required");
                tournamentname.requestFocus();
                return;
            }

            if(Groupname.isEmpty()){
                groupname.setError("Input Required");
                groupname.requestFocus();
                return;
            }

            Score score = new Score(Firstplayer,ScoreFirstplayer,SecondPlayer,ScoreSecondplayer,TournamentName,Groupname);
            FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
            DatabaseReference reference=firebaseDatabase.getReference("Scores Secured In First Round");
            reference.child(Groupname).push().setValue(score).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(FirstRScores.this,"UPDATED",Toast.LENGTH_SHORT).show();
                }
            });
            playername1.setText("");
            playerscore1.setText("");
            playername2.setText("");
            playerscore2.setText("");
            tournamentname.setText("");
            groupname.setText("");

        });
    }
}
