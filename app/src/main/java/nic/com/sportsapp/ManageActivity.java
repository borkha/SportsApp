package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import AdapterClass.ManageAdapter;
import ModelClass.Detail;

public class ManageActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ManageAdapter manageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_admin_activity);

        setSupportActionBar(findViewById(R.id.manageToolbar));
        recyclerView=findViewById(R.id.manageRecycler);
        floatingActionButton= findViewById(R.id.floatingActionButtonManage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Detail> options =
                new FirebaseRecyclerOptions.Builder<Detail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details"), Detail.class)
                        .build();

        manageAdapter = new ManageAdapter(options ,this);
        recyclerView.setAdapter(manageAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddEventDetails.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        manageAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        manageAdapter.stopListening();
    }
}

