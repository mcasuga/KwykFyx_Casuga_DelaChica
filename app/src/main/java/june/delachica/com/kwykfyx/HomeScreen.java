package june.delachica.com.kwykfyx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        TextView firstRowLeft = findViewById(R.id.firstRowLeft);

        firstRowLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(HomeScreen.this, HomeScreenProblem.class);
                startActivity(i);
            }
        });
    }
}
