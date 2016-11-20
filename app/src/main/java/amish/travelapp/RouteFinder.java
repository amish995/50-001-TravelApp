package amish.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class RouteFinder extends MainActivity {


    DecimalFormat f = new DecimalFormat("##.00");

    ArrayList<String> temp = new ArrayList<String>(Arrays.asList("Marina Bay Sands"));

    Spinner dropdown;
    Button addButton;
    Button bruteForceButton;
    Button deleteButton;
    Button approxButton;
    EditText budget;
    TextView locationList;
    String[] items = new String[]{"Singapore Flyer", "Vivo City", "Resorts World Sentosa","Buddha Tooth Relic Temple","Zoo"};

    
    public void onClick(View view){
        String selected = dropdown.getItemAtPosition(dropdown.getSelectedItemPosition()).toString();

        if(temp.contains(selected)!=true){
            temp.add(selected);
            Toast.makeText(this, selected + " added to list", Toast.LENGTH_SHORT).show();
            locationList.setText(temp.toString());
        }
        else{
            Toast.makeText(this, selected + " already selected!", Toast.LENGTH_SHORT).show();
        }
        System.out.println(temp);
    }

    public void delete(View view){
        String selected = dropdown.getItemAtPosition(dropdown.getSelectedItemPosition()).toString();

        if(temp.contains(selected)==true){
            temp.remove(selected);
            Toast.makeText(this, selected + " removed from list", Toast.LENGTH_SHORT).show();
            locationList.setText(temp.toString());
        }
        else{
            Toast.makeText(this, selected + " not added yet!", Toast.LENGTH_SHORT).show();
        }
        System.out.println(temp);
    }

    public void bruteForceClick(View view){
        TSPBruteforce tsp = new TSPBruteforce();
        ArrayList<ArrayList<String>> permutationList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> permutationList1 = new ArrayList<ArrayList<String>>();
        ArrayList<String> soln = new ArrayList<String>();
        permutationList = tsp.createPermutations(temp);
        permutationList1 = tsp.checkPermutations(permutationList);
        soln = tsp.getOptimalPath(permutationList1);
        ArrayList<String> finalPath = new ArrayList<>();
        double realBudget = 0;
        realBudget = (Double.parseDouble(budget.getText().toString())) ;
        ArrayList<ArrayList<int[]>>transportationModesMap = tsp.chooseTransport(soln, realBudget);

        //final mode of transport list
        int[] finalMode = transportationModesMap.get(0).get(0);

        //calcalate the cost
        double costs = (tsp.getTotalCost(finalMode,soln));
        costs = (Double.parseDouble(f.format(costs))) ;

        int count = 0;
        int i = 0;
        finalPath.add(soln.get(i));
//        for (int i = 0; i < soln.size();i++){
        while(count<finalMode.length-1){

//            for (int j = 0; j < finalMode.length;j++){

                if (finalMode[count]==0 && i < soln.size()-1) {
                    finalPath.set(i," " + soln.get(i) + " Public Transport to " + soln.get(i+1));
                    count++;
                    i++;
                    finalPath.add(soln.get(i));
                }
                if (finalMode[count]==1 && i < soln.size()-1){
                    finalPath.set(i," " + soln.get(i) + " Taxi to " + soln.get(i+1)) ;
                    count++;
                    i++;
                    finalPath.add(soln.get(i));
                }
                if (finalMode[count]==2 && i < soln.size()-1){
                    finalPath.set(i," " + soln.get(i)+ " Walk to " + soln.get(i+1)) ;
                    count++;
                    i++;
                    finalPath.add(soln.get(i));

                }
                if (finalMode[count]==0 && i == soln.size()-1) {
                    finalPath.set(i,soln.get(i).toString()+" Public Transport to " + soln.get(0).toString());
                    i++;
                    break;
                }
                if (finalMode[count]==1 && i == soln.size()-1){
                    finalPath.set(i,soln.get(i).toString()+" Taxi to " + soln.get(0).toString()) ;
                    i++;
                    break;
                }
                if (finalMode[count]==2 && i == soln.size()-1){
                    finalPath.set(i,soln.get(i).toString()+" Walk to " + soln.get(0).toString()) ;
                    i++;
                    break;
//                }
                }

            }


        Toast.makeText(this, soln.toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ResultScreen.class);
        intent.putExtra("costs",(Double.toString(costs)));
        intent.putExtra("finalPath", finalPath.toString());
        startActivity(intent);
    }

    public void approxButtonClick(View view){
        TSPBruteforce tsp = new TSPBruteforce();
        ArrayList<String> intial = TSPfastApproximation.NearestNeighbor(temp);
        ArrayList<String> approx = TSPfastApproximation.pleaseImprove(intial);

        ArrayList<String> finalPath = new ArrayList<>();
        double realBudget = 0;
        realBudget = (Double.parseDouble(budget.getText().toString())) ;
        ArrayList<ArrayList<int[]>>transportationModesMap = tsp.chooseTransport(approx, realBudget);

        //final mode of transport list
        int[] finalMode = transportationModesMap.get(0).get(0);

        //calcalate the cost
        double costs = (tsp.getTotalCost(finalMode,approx));
        costs = (Double.parseDouble(f.format(costs))) ;

        int count = 0;
        int i = 0;
        finalPath.add(approx.get(i));
//        for (int i = 0; i < soln.size();i++){
        while(count<finalMode.length-1){

//            for (int j = 0; j < finalMode.length;j++){

            if (finalMode[count]==0 && i < approx.size()-1) {
                finalPath.set(i," " + approx.get(i) + " Public Transport to " + approx.get(i+1));
                count++;
                i++;
                finalPath.add(approx.get(i));
            }
            if (finalMode[count]==1 && i < approx.size()-1){
                finalPath.set(i," " + approx.get(i) + " Taxi to " + approx.get(i+1)) ;
                count++;
                i++;
                finalPath.add(approx.get(i));
            }
            if (finalMode[count]==2 && i < approx.size()-1){
                finalPath.set(i," " + approx.get(i)+ " Walk to " + approx.get(i+1)) ;
                count++;
                i++;
                finalPath.add(approx.get(i));

            }
            if (finalMode[count]==0 && i == approx.size()-1) {
                finalPath.set(i,approx.get(i).toString()+" Public Transport to " + approx.get(0).toString());
                i++;
                break;
            }
            if (finalMode[count]==1 && i == approx.size()-1){
                finalPath.set(i,approx.get(i).toString()+" Taxi to " + approx.get(0).toString()) ;
                i++;
                break;
            }
            if (finalMode[count]==2 && i == approx.size()-1){
                finalPath.set(i,approx.get(i).toString()+" Walk to " + approx.get(0).toString()) ;
                i++;
                break;
//                }
            }

        }


        Toast.makeText(this, approx.toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ResultScreen.class);
        intent.putExtra("costs",(Double.toString(costs)));
        intent.putExtra("finalPath", finalPath.toString());
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_finder);
        initDrawer();
        locationList = (TextView) findViewById(R.id.locationsList);
        addButton = (Button) findViewById(R.id.addButton);
        bruteForceButton = (Button) findViewById(R.id.bruteForceButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        budget = (EditText) findViewById(R.id.budgetText);
        dropdown = (Spinner)findViewById(R.id.spinner1);
        approxButton = (Button) findViewById(R.id.approxButton);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }
}
