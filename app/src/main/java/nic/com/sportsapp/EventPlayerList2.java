package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import AdapterClass.EAdapter;
import AdapterClass.EventAdapterList2;
import ModelClass.autoModel;

public class EventPlayerList2 extends AppCompatActivity
{
    RecyclerView brc;
    Toolbar toolbarE;
    List<autoModel> TournamentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_player_list_2);

        brc= findViewById(R.id.recyclerElist2);
        toolbarE= findViewById(R.id.toolEList2);

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

        EventAdapterList2 adapter = new EventAdapterList2(TournamentList,this);
        brc.setAdapter(adapter);

    }

}
