import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonbeibeibei on 11/14/2016.
 */
public class TSPSolver {



    public static void main(String[] args) {
        double budget = 40;
        TSPBruteforce tsp = new TSPBruteforce();
        ArrayList<String> temp = new ArrayList<String>(Arrays.asList("Zoo","Buddha Tooth Relic Temple","Vivo City","Resorts World Sentosa","Singapore Flyer"));
        ArrayList<ArrayList<String>> permutationList = new ArrayList<ArrayList<String>>();
        ArrayList<String> soln = new ArrayList<String>();
        permutationList = tsp.createPermutations(temp);
        soln = tsp.getOptimalPath(permutationList);
        System.out.println(soln);



    }
}
