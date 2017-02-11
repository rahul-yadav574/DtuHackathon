package in.evolve.dtuhackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private Button hospitalVisitButton;
    private Button scheduleButton;
    private Button suggestionButton;
    private Button medicineButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        hospitalVisitButton= (Button) findViewById(R.id.hospital_visits_button);
        scheduleButton= (Button) findViewById(R.id.schedule_button);
        suggestionButton= (Button) findViewById(R.id.suggestions_button);
        medicineButton= (Button) findViewById(R.id.medicines_button);

        hospitalVisitButton.setOnClickListener(this);
        scheduleButton.setOnClickListener(this);
        suggestionButton.setOnClickListener(this);
        medicineButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id=view.getId();

        switch (id)
        {
            case R.id.hospital_visits_button:
                Intent intent1=new Intent(UserProfileActivity.this,HospitalVisitActivity.class);
                startActivity(intent1);
                break;
            case R.id.schedule_button:
                Intent intent2=new Intent(UserProfileActivity.this,DailyScheduleActivity.class);
                startActivity(intent2);
                break;
            case R.id.medicines_button:
                Intent intent3=new Intent(UserProfileActivity.this,MedicinesActivity.class);
                startActivity(intent3);
                break;
            case R.id.suggestions_button:
                Intent intent4=new Intent(UserProfileActivity.this,DoctorSuggestionActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
