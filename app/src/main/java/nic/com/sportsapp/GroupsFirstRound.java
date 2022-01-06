package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import AdapterClass.GroupAdapter;
import ModelClass.Groups;

public class GroupsFirstRound extends AppCompatActivity
{
    RecyclerView recycler;
    Toolbar Gtoolbar;
    List<Groups> groupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_firstround);

        recycler= findViewById(R.id.recyclerG);
        Gtoolbar= findViewById(R.id.toolbarG);
        this.setSupportActionBar(Gtoolbar);
        this.getSupportActionBar().setTitle("");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        groupList = new ArrayList<>();
        groupList.add(new Groups("Group A"));
        groupList.add(new Groups("Group B"));
        GroupAdapter groupAdapter = new GroupAdapter(groupList,this);
        recycler.setAdapter(groupAdapter);
    }
}
