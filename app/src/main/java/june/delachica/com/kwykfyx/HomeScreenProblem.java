package june.delachica.com.kwykfyx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeScreenProblem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_problem);
        RelativeLayout queryOne = findViewById(R.id.query1);

        queryOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(HomeScreenProblem.this, HomeScreenProblemSolution.class);
                startActivity(i);
            }
        });
    }
}
