package june.delachica.com.kwykfyx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.car_brands, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button mainHome = findViewById(R.id.mainHome);
        Button mainFind = findViewById(R.id.mainFind);
        Button mainSearch = findViewById(R.id.mainSearch);
        mainHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(MainActivity.this, HomeScreen.class);
                startActivity(i);
            }
        });
        mainFind.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(MainActivity.this, FindMechanic.class);
                startActivity(i);
            }
        });
        mainSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(MainActivity.this, ProblemScreen.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
