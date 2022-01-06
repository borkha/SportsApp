package nic.com.sportsapp;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import ModelClass.Detail;

public class AddEventDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerSport ,spinnerLoca;
    String item, list;
    EditText tvSport, tVlocation, mDisplayDate, lDisplayDate;
    Button btnSave, btnback;
    Detail detail;
    private static final String TAG = "AddEventDetails";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener lDateSetListener;
    String[] sports = {"Select Sports", "Badminton","Tennis"};
    String[] location = {"Select location", "Guwahati", "Delhi","Jaipur","Moran","Dehradun","Chennai","Bangalore"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_details_admin_activity);

        mDisplayDate = (EditText) findViewById(R.id.tvDate);
        lDisplayDate=(EditText) findViewById(R.id.tvLastDate);
        tvSport = (EditText) findViewById(R.id.tvSport);
        tVlocation = (EditText) findViewById(R.id.tVlocation);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnback = (Button) findViewById(R.id.btnback);
        spinnerSport = (Spinner)findViewById(R.id.spinnersport);
        spinnerLoca = (Spinner)findViewById(R.id.spinnerloca);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddEventDetails.this,AdminDashboard.class));
            }
        });

        spinnerLoca.setOnItemSelectedListener(this);
        spinnerSport.setOnItemSelectedListener(this);

        detail = new Detail();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,sports);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSport.setAdapter(arrayAdapter);

        ArrayAdapter arrayAdapterone = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,location);
        arrayAdapterone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLoca.setAdapter(arrayAdapterone);

        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        AddEventDetails.this
                )
                        .setSmallIcon(R.drawable.ic_player_notifications)
                        .setContentTitle("New Notification")
                        .setContentText("New Event Upcoming");

                Intent notificationIntent = new Intent(AddEventDetails.this,NotificationsPlayerDash.class);
                PendingIntent contentIntent = PendingIntent.getActivity(AddEventDetails.this,0,
                        notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(contentIntent);
                NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());

                insertData();
                clearAll();
            }
        });
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddEventDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d (TAG, "OnDateSet: mm/dd/yyyy:" + month + "/" + day + "/" + year);
                String date = + month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        lDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEventDetails.this, android.R.style.
                        Theme_Holo_Light_Dialog_MinWidth,lDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        lDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d (TAG, "OnDateSet: mm/dd/yyyy:" + month + "/" + day + "/" + year);
                String date = + month + "/" + day + "/" + year;
                lDisplayDate.setText(date);
            }
        };
    }
    private void insertData()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("date",mDisplayDate.getText().toString());
        map.put("gamename",tvSport.getText().toString());
        map.put("lastdate",lDisplayDate.getText().toString());
        map.put("location",tVlocation.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddEventDetails.this,"Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddEventDetails.this,"Error While Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll()
    {
        mDisplayDate.setText("");
        lDisplayDate.setText("");
        tVlocation.setText("");
        tvSport.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        item = spinnerSport.getSelectedItem().toString();
        list = spinnerLoca.getSelectedItem().toString();
        tvSport.setText(item);
        tVlocation.setText(list);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {
        Toast.makeText(this,"item selected",Toast.LENGTH_SHORT).show();
    }
}
