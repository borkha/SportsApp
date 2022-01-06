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

public class PMScore extends AppCompatActivity
{
    EditText PayplayerName1,Payplayername2,payplayerscore1,payplayerscore2,TournamentPayoff,PayMwinner, payMlose;
    Button PayMdeclareWinner, Paysubmit,losPayMbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm_scores);
        PayplayerName1= findViewById(R.id.PayplayerName1);
        Payplayername2= findViewById(R.id.Payplayername2);
        payplayerscore1= findViewById(R.id.payplayerscore1);
        payplayerscore2= findViewById(R.id.payplayerscore2);
        TournamentPayoff= findViewById(R.id.TournamentPayoff);
        PayMwinner= findViewById(R.id.PayMwinner);
        payMlose= findViewById(R.id.payMlose);
        PayMdeclareWinner= findViewById(R.id.PayMdeclareWinner);
        Paysubmit= findViewById(R.id.Paysubmit);
        losPayMbtn= findViewById(R.id.losPayMbtn);

        losPayMbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = payMlose.getText().toString().trim();

                if(name.isEmpty())
                {
                    payMlose.setError("Input Required");
                    payMlose.requestFocus();
                    return;
                }
                autoModel auto = new autoModel(name);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Players Playing For Third Position").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PMScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                payMlose.setText("");

            }
        });
        PayMdeclareWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = PayMwinner.getText().toString().trim();

                if(name.isEmpty()){
                    PayMwinner.setError("Input Required");
                    PayMwinner.requestFocus();
                    return;
                }

                autoModel auto = new autoModel(name);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference ref=firebaseDatabase.getReference();
                ref.child("Players selected for Final").push().setValue(auto).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PMScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });

                PayMwinner.setText("");
            }
        });

        Paysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstplayer = PayplayerName1.getText().toString().trim();
                String ScoreFirstplayer = payplayerscore1.getText().toString().trim();
                String SecondPlayer = Payplayername2.getText().toString().trim();
                String ScoreSecondplayer = payplayerscore2.getText().toString().trim();
                String TournamentName = TournamentPayoff.getText().toString().trim();
                String Groupname = TournamentPayoff.getText().toString().trim();

                if(Firstplayer.isEmpty()){
                    PayplayerName1.setError("Input Required");
                    PayplayerName1.requestFocus();
                    return;
                }
                if(ScoreFirstplayer.isEmpty()){
                    payplayerscore1.setError("Input Required");
                    payplayerscore1.requestFocus();
                    return;
                }

                if(SecondPlayer.isEmpty()){
                    Payplayername2.setError("Input Required");
                    Payplayername2.requestFocus();
                    return;
                }

                if(ScoreSecondplayer.isEmpty()){
                    payplayerscore2.setError("Input Required");
                    payplayerscore2.requestFocus();
                    return;
                }

                if(TournamentName.isEmpty()){
                    TournamentPayoff.setError("Input Required");
                    TournamentPayoff.requestFocus();
                    return;
                }
                if(Groupname.isEmpty()){
                    TournamentPayoff.setError("Input Required");
                    TournamentPayoff.requestFocus();
                    return;
                }

                Score score = new Score(Firstplayer,ScoreFirstplayer,SecondPlayer,ScoreSecondplayer,TournamentName,Groupname);

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference reference=firebaseDatabase.getReference();
                reference.child("Scores Secured In PayMatch").push().setValue(score).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PMScore.this,"UPDATED",Toast.LENGTH_SHORT).show();
                    }
                });
                PayplayerName1.setText("");
                payplayerscore1.setText("");
                Payplayername2.setText("");
                payplayerscore2.setText("");
                TournamentPayoff.setText("");
            }
        });
    }
}

