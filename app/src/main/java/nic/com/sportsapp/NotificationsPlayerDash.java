package nic.com.sportsapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import AdapterClass.notifyPlayerAdapter;
import ModelClass.Detail;

public class NotificationsPlayerDash extends AppCompatActivity
{
    RecyclerView recycler_player;
    notifyPlayerAdapter notify;
    Toolbar toolbarNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_player_activity);

        recycler_player =  findViewById(R.id.recycler_player);
        recycler_player.setHasFixedSize(true);
        recycler_player.setLayoutManager(new LinearLayoutManager(this));
        recycler_player.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarNotify = findViewById(R.id.toolbarNotify);

        this.setSupportActionBar(toolbarNotify);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Detail> options =
                new FirebaseRecyclerOptions.Builder<Detail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details"),Detail.class)
                        .build();
        notify = new notifyPlayerAdapter(options,this);
        recycler_player.setAdapter(notify);
    }
    @Override
    protected void onStart() {
        super.onStart();
        notify.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        notify.stopListening();
    }
}