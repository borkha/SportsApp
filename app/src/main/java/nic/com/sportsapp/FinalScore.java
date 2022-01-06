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

public class FinalScore extends AppCompatActivity
{
    EditText Fplayername1,Fplayername2,Fplayerscore1,Fplayerscore2,Finalournament,Fwinnername, Flossname;
    Button Fdeclarewinner, FsubmitWinner,finalplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        Fplayername1= findViewById(R.id.finalplayerName1);
        Fplayername2= findViewById(R.id.finalplayername2);
        Fplayerscore1= findViewById(R.id.finalplayerscore1);
        Fplayerscore2= findViewById(R.id.finalplayerscore2);
        Finalournament= findViewById(R.id.finalTournament);
        Fwinnername= findViewById(R.id.finalrwinner);
        Flossname= findViewById(R.id.Finalloss);
        Fdeclarewinner= findViewById(R.id.finaldeclareWinner);
        FsubmitWinner= findViewById(R.id.FinalsubmitWinners);
        finalplayer= findViewById(R.id.lossplayerfinal);

        finalplayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String name = Flossname.getText().toString().trim();

                if(name.isEmpty())
                {
                    Flossname.setError("Input Required");
                    Flossname.requestFocus();
                    return;
                }

                autoModel auto = new autoModel(name);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Second position Secured").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(FinalScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });

                Flossname.setText("");
            }
        });

        Fdeclarewinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Fwinnername.getText().toString().trim();
                if(name.isEmpty()){
                    Fwinnername.setError("Input Required");
                    Fwinnername.requestFocus();
                    return;
                }

                autoModel auto = new autoModel(name);
                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Winner of the Game").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(FinalScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });

                Fwinnername.setText("");
            }
        });
        FsubmitWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstplayer = Fplayername1.getText().toString().trim();
                String ScoreFirstplayer = Fplayerscore1.getText().toString().trim();
                String SecondPlayer = Fplayername2.getText().toString().trim();
                String ScoreSecondplayer = Fplayerscore2.getText().toString().trim();
                String TournamentName = Finalournament.getText().toString().trim();
                String Groupname = Finalournament.getText().toString().trim();

                if(Firstplayer.isEmpty()){
                    Fplayername1.setError("Input Required");
                    Fplayername1.requestFocus();
                    return;
                }
                if(ScoreFirstplayer.isEmpty()){
                    Fplayerscore1.setError("Input Required");
                    Fplayerscore1.requestFocus();
                    return;
                }
                if(SecondPlayer.isEmpty()){
                    Fplayername2.setError("Input Required");
                    Fplayername2.requestFocus();
                    return;
                }
                if(ScoreSecondplayer.isEmpty()){
                    Fplayerscore2.setError("Input Required");
                    Fplayerscore2.requestFocus();
                    return;
                }
                if(TournamentName.isEmpty()){
                    Finalournament.setError("Input Required");
                    Finalournament.requestFocus();
                    return;
                }
                if(TournamentName.isEmpty()){
                    Finalournament.setError("Input Required");
                    Finalournament.requestFocus();
                    return;
                }

                Score score = new Score(Firstplayer,ScoreFirstplayer,SecondPlayer,ScoreSecondplayer,TournamentName,Groupname);
                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference reference=firebaseDatabase.getReference();
                reference.child("Scores Secured In Final Round").push().setValue(score).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(FinalScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                Fplayername1.setText("");
                Fplayerscore1.setText("");
                Fplayername2.setText("");
                Fplayerscore2.setText("");
                Finalournament.setText("");
            }
        });
    }
}
