
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jonbeibeibei on 11/14/2016.
 */
public class TSPBruteforce implements Runnable{

    private double budget;

    private static double[][][] costsArray = Destinations.getCostsArray();
    private static int[][][] timeArray = Destinations.getTimeArray();
    private HashMap<String, Integer> destinationMap = Destinations.getDestinationmap();
    private ArrayList<String> destinations;

    private double minCosts;

    private int[] minRoute;

    private static long count;

    private ArrayList<Integer> destinationnum = new ArrayList<Integer>();

    private HashMap<Integer,Double> publicCostMap = new HashMap<>();

    //creates an arraylist of permutations of all the possible combinations of destinations
    public static ArrayList<ArrayList<String>> createPermutations(ArrayList<String> destinations){
        if (destinations.size()<=1){
            ArrayList<ArrayList<String>> permutations = new ArrayList<ArrayList<String>>();
            permutations.add(destinations);
            return permutations;
        }
        String finalElement = destinations.remove(0);
        ArrayList<ArrayList<String>> oldPermutations = createPermutations(destinations);
        ArrayList<ArrayList<String>> newPermutations = new ArrayList<ArrayList<String>>();
        for (ArrayList<String> sublist :oldPermutations){
            for(int i = 0; i <= sublist.size(); i++){
                ArrayList<String> newsublist = new ArrayList<String>(sublist);
                newsublist.add(i,finalElement);
                newPermutations.add(newsublist);
            }
        }
        return newPermutations;
    }

    /**
     * Given a nested arraylist of permutations, choose the optimal path
     * @param permutations
     * @return
     */
    public static ArrayList<String> getOptimalPath(ArrayList<ArrayList<String>>permutations){
        double optimalTimeCost= -1.0;
        ArrayList<String> optimalpath = new ArrayList<String>();
        for (int i = 0; i < permutations.size(); i++){
            double newTimeCost = TotalTimeCostAverage(permutations.get(i));
            if (optimalTimeCost == -1.0 || newTimeCost < optimalTimeCost){
                optimalTimeCost = newTimeCost;
                optimalpath = permutations.get(i);
            }
        }
        return optimalpath;
    }

    /**
     * Return the time*cost average of traversing 2 nodes
     * @param fromNode
     * @param toNode
     * @return
     */
    public static double TimeCostAverage(String fromNode, String toNode){
        double walkingTimeCost = 1.0*timeArray[2][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
        double ptTimeCost = timeArray[0][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
        double taxiTimeCost = timeArray[1][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
        return (walkingTimeCost+ptTimeCost+taxiTimeCost)/3;
    }

    public static double TotalTimeCostAverage(ArrayList<String> path){
        double total = 0;
        for(int i = 0; i<path.size(); i++){
            if (i==path.size()-1){
                total += TimeCostAverage(path.get(i),path.get(0));
            }
            else{
                total += TimeCostAverage(path.get(i),path.get(i+1));
            }
        }
        return total;
    }




    @Override
    public void run() {


    }
}
