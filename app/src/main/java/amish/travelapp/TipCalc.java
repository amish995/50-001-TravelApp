package amish.travelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TipCalc extends MainActivity {

    private RadioGroup radioGroup;
    private RadioButton high, medium, low;
    private char tip_level = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);

        initDrawer();

        radioGroup = (RadioGroup) findViewById(R.id.tip_lvl);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_high){
                    tip_level = 20;
                }
                else if(checkedId == R.id.radio_medium){
                    tip_level = 15;
                }
                else{
                    tip_level = 10;
                }
            }
        });

        final EditText billAmt = (EditText) findViewById(R.id.bill_amt);

        final CheckBox servChrg = (CheckBox) findViewById(R.id.checkBox);

        final TextView tipAmt = (TextView) findViewById(R.id.tip_amt);

        final Button calculateButton = (Button) findViewById(R.id.calculate_tip);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double billAmount = Double.parseDouble(billAmt.getText().toString());
                if (servChrg.isChecked()){
                    tipAmt.setText("No Tip Necessary");
                }
                else{
                    double tipAmount = billAmount*tip_level/100;
                    String textToDisp = "Tip Amount: " + tipAmount;
                    tipAmt.setText(textToDisp);
                }
            }
        });

    }
}
