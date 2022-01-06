package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import AdapterClass.SelectedAdapterList2;
import ModelClass.autoModel;

public class SelectedListPlayers2 extends AppCompatActivity {
    RecyclerView recyclerW;
    Toolbar toolW;
    List<autoModel> WinGrpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_players_2);

        recyclerW = findViewById(R.id.recyclerW);
        toolW = findViewById(R.id.toolW);

        this.setSupportActionBar(toolW);
        this.getSupportActionBar().setTitle("");

        recyclerW.setLayoutManager(new LinearLayoutManager(this));
        recyclerW.setHasFixedSize(true);

        WinGrpList = new ArrayList<>();
        WinGrpList.add(new autoModel("Players Selected For Quarter-Final"));
        WinGrpList.add(new autoModel("Players Selected For Semi-Final"));
        WinGrpList.add(new autoModel("Players Selected For Pay Match"));
        WinGrpList.add(new autoModel("Players Selected For Third Position Match"));
        WinGrpList.add(new autoModel("Players Selected For Final"));

        SelectedAdapterList2 selectedAdapterList2 = new SelectedAdapterList2(WinGrpList, this);
        recyclerW.setAdapter(selectedAdapterList2);

    }
}
