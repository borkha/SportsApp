package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import AdapterClass.ScoreAdapterList2;
import ModelClass.autoModel;

public class ScorePlayerList2 extends AppCompatActivity
{
    RecyclerView brc;
    Toolbar toolbarE;
    List<autoModel> TournamentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_player_list2);

        brc= findViewById(R.id.recyclerScorelist2);
        toolbarE= findViewById(R.id.toolScoreList2);

        this.setSupportActionBar(toolbarE);
        this.getSupportActionBar().setTitle("");

        brc.setLayoutManager(new LinearLayoutManager(this));
        brc.setHasFixedSize(true);

        TournamentList = new ArrayList<>();
        TournamentList.add(new autoModel("First Round"));
        TournamentList.add(new autoModel("Quarter Final"));
        TournamentList.add(new autoModel("Semi Final"));
        TournamentList.add(new autoModel("Pay Match"));
        TournamentList.add(new autoModel("Match For Third Position"));
        TournamentList.add(new autoModel("Final Game"));

        ScoreAdapterList2 scoreAdapterList2 = new ScoreAdapterList2(TournamentList,this);
        brc.setAdapter(scoreAdapterList2);
    }
}
