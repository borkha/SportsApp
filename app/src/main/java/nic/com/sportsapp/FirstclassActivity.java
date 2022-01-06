package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import AdapterClass.FirstclassRGroupAdapter;
import ModelClass.Groups;

public class FirstclassActivity extends AppCompatActivity
{
    RecyclerView recycler;
    Toolbar Gtoolbar;
    List<Groups> groupList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fr_class_activity);

        recycler= findViewById(R.id.recyclerFR);
        Gtoolbar= findViewById(R.id.toolFR);
        this.setSupportActionBar(Gtoolbar);
        this.getSupportActionBar().setTitle("");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        groupList = new ArrayList<>();
        groupList.add(new Groups("Group A"));
        groupList.add(new Groups("Group B"));
        FirstclassRGroupAdapter firstclassRGroupAdapter = new FirstclassRGroupAdapter(groupList,this);
        recycler.setAdapter(firstclassRGroupAdapter);
    }

}
