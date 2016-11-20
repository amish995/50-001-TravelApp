package amish.travelapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jonbeibeibei on 11/14/2016.
 */
public class TSPSolver {




    public static void main(String[] args) {
        double budget = 40;
        TSPBruteforce tsp = new TSPBruteforce();
        ArrayList<String> temp = new ArrayList<String>(Arrays.asList(""));
        temp.add("Marina Bay Sands");
        ArrayList<ArrayList<String>> permutationList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> permutationList1 = new ArrayList<ArrayList<String>>();
        ArrayList<String> soln = new ArrayList<String>();
        ArrayList<String> soln2 = new ArrayList<String>();
        permutationList = tsp.createPermutations(temp);
        permutationList1 = tsp.checkPermutations(permutationList);
        soln = tsp.getOptimalPath(permutationList1);
        System.out.println("solution" + soln);


        ArrayList<ArrayList<int[]>>transportationModesMap = tsp.chooseTransport(soln, 300.0);
        System.out.println(soln);
        System.out.println(Arrays.toString(transportationModesMap.get(0).get(0)));
        int[] finalMode = transportationModesMap.get(0).get(0);

        System.out.println(Arrays.toString(finalMode));
        //calcalate the cost
        System.out.println(tsp.getTotalCost(finalMode,soln));




    }
}
