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

public class ThirdPScore extends AppCompatActivity
{
    EditText thirdplayerName1,thirdplayername2,thirdplayerscore1,thirdplayerscore2,ThirdTournament,Thirdrwinner, thirdloss;
    Button thirddeclareWinner, ThirdsubmitWinners,lossplayerthhird;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_position_score);

        thirdplayerName1= findViewById(R.id.thirdplayerName1);
        thirdplayername2= findViewById(R.id.thirdplayername2);
        thirdplayerscore1= findViewById(R.id.thirdplayerscore1);
        thirdplayerscore2= findViewById(R.id.thirdplayerscore2);
        ThirdTournament= findViewById(R.id.ThirdTournament);
        Thirdrwinner= findViewById(R.id.Thirdrwinner);
        thirdloss= findViewById(R.id.thirdpos);
        thirddeclareWinner= findViewById(R.id.thirddeclareWinner);
        ThirdsubmitWinners= findViewById(R.id.ThirdsubmitWinners);
        lossplayerthhird= findViewById(R.id.lossplayerthhird);

        lossplayerthhird.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String name = thirdloss.getText().toString().trim();

                if(name.isEmpty())
                {
                    thirdloss.setError("Input Required");
                    thirdloss.requestFocus();
                    return;
                }

                autoModel auto = new autoModel(name);
                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Fourth Position Secured").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ThirdPScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });

                thirdloss.setText("");
            }
        });

        thirddeclareWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Thirdrwinner.getText().toString().trim();

                if(name.isEmpty()){
                    Thirdrwinner.setError("Input Required");
                    Thirdrwinner.requestFocus();
                    return;
                }
                autoModel auto = new autoModel(name);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Third Winner of the Game").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ThirdPScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                Thirdrwinner.setText("");
            }
        });
        ThirdsubmitWinners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstplayer = thirdplayerName1.getText().toString().trim();
                String ScoreFirstplayer = thirdplayerscore1.getText().toString().trim();
                String SecondPlayer = thirdplayername2.getText().toString().trim();
                String ScoreSecondplayer = thirdplayerscore2.getText().toString().trim();
                String TournamentName = ThirdTournament.getText().toString().trim();
                String Groupname = ThirdTournament.getText().toString().trim();

                if(Firstplayer.isEmpty()){
                    thirdplayerName1.setError("Input Required");
                    thirdplayerName1.requestFocus();
                    return;
                }
                if(ScoreFirstplayer.isEmpty()){
                    thirdplayerscore1.setError("Input Required");
                    thirdplayerscore1.requestFocus();
                    return;
                }

                if(SecondPlayer.isEmpty()){
                    thirdplayername2.setError("Input Required");
                    thirdplayername2.requestFocus();
                    return;
                }

                if(ScoreSecondplayer.isEmpty()){
                    thirdplayerscore2.setError("Input Required");
                    thirdplayerscore2.requestFocus();
                    return;
                }

                if(TournamentName.isEmpty()){
                    ThirdTournament.setError("Input Required");
                    ThirdTournament.requestFocus();
                    return;
                }
                if(Groupname.isEmpty()){
                    ThirdTournament.setError("Input Required");
                    ThirdTournament.requestFocus();
                    return;
                }

                Score score = new Score(Firstplayer,ScoreFirstplayer,SecondPlayer,ScoreSecondplayer,TournamentName,Groupname);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference reference=firebaseDatabase.getReference();
                reference.child("Scores Secured For Third Position").push().setValue(score).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ThirdPScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                thirdplayerName1.setText("");
                thirdplayerscore1.setText("");
                thirdplayername2.setText("");
                thirdplayerscore2.setText("");
                ThirdTournament.setText("");
            }
        });

    }
}
