package com.kwykfyxapp.kwykfyx.browse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;

public class BrowseCategoriesActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView
            browse_carProblemsTextView,
            browse_airconditioningTextView,
            browse_batteryChargingTextView,
            browse_brakeAntilockTextView,
            browse_engineCoolingTextView,
            browse_emissionControlTextView,
            browse_engineDiagnosisTextView,
            browse_engineSensorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_categories);

        browse_carProblemsTextView = findViewById(R.id.browse_carProblemsTextView);
        browse_carProblemsTextView.setOnClickListener(this);

        browse_airconditioningTextView = findViewById(R.id.browse_airconditioningTextView);
        browse_airconditioningTextView.setOnClickListener(this);

        browse_batteryChargingTextView = findViewById(R.id.browse_batteryChargingTextView);
        browse_batteryChargingTextView.setOnClickListener(this);

        browse_brakeAntilockTextView = findViewById(R.id.browse_brakeAntilockTextView);
        browse_brakeAntilockTextView.setOnClickListener(this);

        browse_engineCoolingTextView = findViewById(R.id.browse_engineCoolingTextView);
        browse_engineCoolingTextView.setOnClickListener(this);

        browse_emissionControlTextView = findViewById(R.id.browse_emissionControlTextView);
        browse_emissionControlTextView.setOnClickListener(this);

        browse_engineDiagnosisTextView = findViewById(R.id.browse_engineDiagnosisTextView);
        browse_engineDiagnosisTextView.setOnClickListener(this);

        browse_engineSensorTextView = findViewById(R.id.browse_engineSensorTextView);
        browse_engineSensorTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.browse_carProblemsTextView:
                startActivity(toProblemsActivity(ProblemsActivity.CAR_PROBLEMS));
                break;
            case R.id.browse_airconditioningTextView:
                startActivity(toProblemsActivity(ProblemsActivity.AIRCONDITIONING_PROBLEMS));
                break;
            case R.id.browse_batteryChargingTextView:
                startActivity(toProblemsActivity(ProblemsActivity.BATTERY_CHARGING_PROBLEMS));
                break;
            case R.id.browse_brakeAntilockTextView:
                startActivity(toProblemsActivity(ProblemsActivity.BRAKE_ANTILOCK_BRAKE_PROBLEMS));
                break;
            case R.id.browse_engineCoolingTextView:
                startActivity(toProblemsActivity(ProblemsActivity.ENGINE_COOLING_PROBLEMS));
                break;
            case R.id.browse_emissionControlTextView:
                startActivity(toProblemsActivity(ProblemsActivity.EMISSION_CONTROL_PROBLEMS));
                break;
            case R.id.browse_engineDiagnosisTextView:
                startActivity(toProblemsActivity(ProblemsActivity.ENGINE_DIAGNOSIS));
                break;
            case R.id.browse_engineSensorTextView:
                startActivity(toProblemsActivity(ProblemsActivity.ENGINE_SENSOR_DIAGNOSIS));
                break;
        }
    }

    private Intent toProblemsActivity(int problemType) {
        Intent toProblemsActivity = new Intent(BrowseCategoriesActivity.this, ProblemsActivity.class);
        String extraTag = "com.kwykfyxapp.kwykfyx.browse.BrowseCategoriesActivity.PROBLEM_TYPE";
        toProblemsActivity.putExtra(extraTag, problemType);

        return toProblemsActivity;
    }
}
