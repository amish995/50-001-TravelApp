package amish.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ResultScreen extends AppCompatActivity {
    TextView resultText;
    TextView costsText;
    TextView transportText;
    TextView optimalRouteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        costsText = (TextView) findViewById(R.id.costsText);
        optimalRouteText = (TextView) findViewById(R.id.optimalRouteText);
        Intent in = getIntent();
        String costs = in.getExtras().getString("costs");
        String pathList = in.getExtras().getString("finalPath");
        costsText.setText("$"+costs);
        optimalRouteText.setText(pathList);

    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, RouteFinder.class));
    }
}
