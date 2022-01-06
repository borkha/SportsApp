package nic.com.sportsapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.PlayerDetailsAdapter;
import ModelClass.RPlayer;


public class WinnerList2 extends AppCompatActivity
{
    Toolbar toolbar;
    RecyclerView recyclerViewWinR;
    PlayerDetailsAdapter playerDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_list_2);

        toolbar=findViewById(R.id.toolbar3);
        recyclerViewWinR=findViewById(R.id.recyclerWinR);
        recyclerViewWinR.setHasFixedSize(true);
        recyclerViewWinR.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWinR.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Winner of the Game"), RPlayer.class)
                        .build();

        playerDetailsAdapter= new PlayerDetailsAdapter(options,this);
        recyclerViewWinR.setAdapter(playerDetailsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        playerDetailsAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        playerDetailsAdapter.stopListening();
    }
}
