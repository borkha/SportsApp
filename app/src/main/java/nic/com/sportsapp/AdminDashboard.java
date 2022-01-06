package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminDashboard extends AppCompatActivity
{
    CardView cardManageEvents, cardRegisteredPlayers, cardFREvents, cardScoredFR,cardQFEvents, cardScoredQF,cardSFEvents, cardScoredSF;
    CardView  cardPMEvents, cardScoredPM,cardFEvents, cardScoredF, cardThirdRoundE,cardThirdRScores,cardHome;
    TextView dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);

        dashboard=findViewById(R.id.dashboard);
        cardManageEvents = findViewById(R.id.cardManageEvent);
        cardRegisteredPlayers = findViewById(R.id.cardRegisteredPlayers);
        cardFREvents = findViewById(R.id.cardFRMatches);
        cardScoredFR = findViewById(R.id.cardScoresFR);

        cardManageEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,ManageActivity.class));
            }
        });
        cardRegisteredPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,PlayersView.class));
            }
        });
        cardFREvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,FirstRoundPlayers.class));
            }
        });
        cardScoredFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,FirstRScores.class));
            }
        });

        cardQFEvents = findViewById(R.id.cardQFEvent);
        cardScoredQF = findViewById(R.id.cardQFScores);
        cardSFEvents = findViewById(R.id.cardSFEvent);
        cardScoredSF = findViewById(R.id.cardSFScores);

        cardQFEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,QFRound.class));
            }
        });
        cardScoredQF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,QFScore.class));
            }
        });
        cardSFEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,SFRound.class));
            }
        });
        cardScoredSF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,SFScore.class));
            }
        });

        cardPMEvents = findViewById(R.id.cardPMEvent);
        cardScoredPM = findViewById(R.id.cardPMScores);
        cardFEvents = findViewById(R.id.cardFEvent);
        cardScoredF = findViewById(R.id.cardScoresF);

        cardPMEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,PMRound.class));
            }
        });
        cardScoredPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,PMScore.class));
            }
        });
        cardFEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,FinalRound.class));
            }
        });
        cardScoredF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,FinalScore.class));
            }
        });

        cardThirdRoundE = findViewById(R.id.cardThirdREvent);
        cardThirdRScores = findViewById(R.id.cardScoresThirdRound);
        cardHome = findViewById(R.id.cardLogout);

        cardThirdRoundE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,ThirdPRound.class));
            }
        });
        cardThirdRScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,ThirdPScore.class));
            }
        });
        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboard.this,MainActivity.class));
            }
        });

    }
}
