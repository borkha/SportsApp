package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import AdapterClass.FirstclassRGroupAdapter;
import AdapterClass.FirstclassScoreGroupAdapter;
import ModelClass.Groups;

public class FirstclassScore extends AppCompatActivity
{
    RecyclerView recycler;
    Toolbar Gtoolbar;
    List<Groups> groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_class_score_activity);

        recycler= findViewById(R.id.FCPlayersRecycler);
        Gtoolbar= findViewById(R.id.toolbarFPlayers);
        this.setSupportActionBar(Gtoolbar);
        this.getSupportActionBar().setTitle("");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        groupList = new ArrayList<>();
        groupList.add(new Groups("Group A"));
        groupList.add(new Groups("Group B"));

        FirstclassScoreGroupAdapter firstclassScoreGroupAdapter = new FirstclassScoreGroupAdapter(groupList,this);
        recycler.setAdapter(firstclassScoreGroupAdapter);
    }
}
