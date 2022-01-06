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

public class QFScore extends AppCompatActivity
{
    EditText Qplayername1,Qplayername2,Qplayerscore1,Qplayerscore2,Quartournament,Qwinnername, Qgroupname;
    Button Qdeclarewinner, QsubmitWinner,otherplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qf_scores);

        Qplayername1= findViewById(R.id.QuartplayerName1);
        Qplayername2= findViewById(R.id.Quartplayername2);
        Qplayerscore1= findViewById(R.id.Qplayerscore1);
        Qplayerscore2= findViewById(R.id.Qplayerscore2);
        Quartournament= findViewById(R.id.QuarTournament);
        Qwinnername= findViewById(R.id.Quarwinner);
        Qgroupname= findViewById(R.id.QgroupName);
        Qdeclarewinner= findViewById(R.id.QdeclareWinner);
        QsubmitWinner= findViewById(R.id.QsubmitWinners);
        otherplayer= findViewById(R.id.lossplayer);

        otherplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Qgroupname.getText().toString().trim();
                if(name.isEmpty())
                {
                    Qgroupname.setError("Input Required");
                    Qgroupname.requestFocus();
                    return;
                }
                autoModel auto = new autoModel(name);
                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Pay Match").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(QFScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                Qgroupname.setText("");
            }
        });
        Qdeclarewinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Qwinnername.getText().toString().trim();
                if(name.isEmpty()){
                    Qwinnername.setError("Input Required");
                    Qwinnername.requestFocus();
                    return;
                }
                autoModel auto = new autoModel(name);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Players selected for Semi Final").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(QFScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                Qwinnername.setText("");
            }
        });
        QsubmitWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstplayer = Qplayername1.getText().toString().trim();
                String ScoreFirstplayer = Qplayerscore1.getText().toString().trim();
                String SecondPlayer = Qplayername2.getText().toString().trim();
                String ScoreSecondplayer = Qplayerscore2.getText().toString().trim();
                String TournamentName = Quartournament.getText().toString().trim();
                String Groupname = Quartournament.getText().toString().trim();

                if(Firstplayer.isEmpty()){
                    Qplayername1.setError("Input Required");
                    Qplayername1.requestFocus();
                    return;
                }
                if(ScoreFirstplayer.isEmpty()){
                    Qplayerscore1.setError("Input Required");
                    Qplayerscore1.requestFocus();
                    return;
                }

                if(SecondPlayer.isEmpty()){
                    Qplayername2.setError("Input Required");
                    Qplayername2.requestFocus();
                    return;
                }

                if(ScoreSecondplayer.isEmpty()){
                    Qplayerscore2.setError("Input Required");
                    Qplayerscore2.requestFocus();
                    return;
                }

                if(TournamentName.isEmpty()){
                    Quartournament.setError("Input Required");
                    Quartournament.requestFocus();
                    return;
                }
                if(Groupname.isEmpty()){
                    Quartournament.setError("Input Required");
                    Quartournament.requestFocus();
                    return;
                }
                 Score score = new Score(Firstplayer,ScoreFirstplayer,SecondPlayer,ScoreSecondplayer,TournamentName,Groupname);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference reference=firebaseDatabase.getReference();
                reference.child("Scores Secured In Quarter Final Round").push().setValue(score).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(QFScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                Qplayername1.setText("");
                Qplayerscore1.setText("");
                Qplayername2.setText("");
                Qplayerscore2.setText("");
                Quartournament.setText("");
            }
        });
    }
}
