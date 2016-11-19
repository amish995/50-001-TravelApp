
import sun.security.krb5.internal.crypto.Des;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Jonbeibeibei on 11/14/2016.
 */
public class TSPBruteforce {

    private static double[][][] costsArray = Destinations.getCostsArray();
    private static int[][][] timeArray = Destinations.getTimeArray();
    private HashMap<String, Integer> destinationMap = Destinations.getDestinationmap();
    private ArrayList<String> destinations;

    private double minCosts;

    private int[] minRoute;

    private static long count;

    private ArrayList<Integer> destinationnum = new ArrayList<Integer>();

    private HashMap<Integer, Double> publicCostMap = new HashMap<>();

    //Returns the time for transport mode between two nodes
    public static int getTime(int mode, String fromNode, String toNode) {
        return timeArray[mode][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
    }

    //Returns the cost for transport mode between two nodes
    public static double getCost(int mode, String fromNode, String toNode) {
        return costsArray[mode][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
    }

    //return the total time, given a path and its transport nodes
    public static int getTotalTime(int[] modes, ArrayList<String> path) {
        int total = 0;
        for (int i = 0; i < modes.length; i++) {
            total += getTime(modes[i], path.get(i), path.get(i == modes.length - 1 ? 0 : i + 1));
        }
        return total;
    }

    //return the total cost, given a path and its transport modes
    public static double getTotalCost(int[] modes, ArrayList<String> path) {
        double total = 0;
        for (int i = 0; i < modes.length; i++) {
            total += getCost(modes[i], path.get(i), path.get(i == modes.length - 1 ? 0 : i + 1));
        }
        return total;
    }

    //Method to check if a list of arrays of objects contain a specific array, need to use
    //Arrays.deepEquals
    public static boolean deepContainsArray(List<? extends Object[]> list, Object[] testArray) {
        for (Object[] objArr : list) {
            if (Arrays.deepEquals(objArr, testArray)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Method to check if all values in an array match 1 given value or none.
     */
    static boolean ArrayAllValuesMatch(int mode, int[] integerArray, int value) {
        for (int num : integerArray) {
            if (mode == 0) {
                if (num != value) {
                    return false;
                }
            }
            if (num == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the shortest in a path that doesnt exist in the shortEdge array
     * type chooses the method of evaluating shortest edge, by timecostaverage or walking time
     */
    public static String[] nextShortestEdge(String type, ArrayList<String> path, ArrayList<String[]> shortEdges) {
        double timeCostAverage = -1.0;
        double newTimeCostAverage = 0;
        String[] edge = new String[2];
        String[] tempEdge = new String[2];

        for (int i = 0; i < path.size(); i++) {
            newTimeCostAverage = type.equals("timeCostAverage") ? TimeCostAverage(path.get(i), path.get(i == path.size() - 1 ? 0 : i + 1)) :
                    getTime(2, path.get(i), path.get(i == path.size() - 1 ? 0 : i + 1));
            tempEdge[0] = path.get(i);
            tempEdge[1] = path.get(i == path.size() - 1 ? 0 : i + 1);

            for (String node : tempEdge) {
                if ((timeCostAverage == -1.0 || newTimeCostAverage < timeCostAverage) && !deepContainsArray(shortEdges, tempEdge)) {
                    timeCostAverage = newTimeCostAverage;
                    edge[0] = path.get(i);
                    edge[1] = path.get(i == path.size() - 1 ? 0 : i + 1);
                }
            }
        }
        return edge;
    }


    //creates an arraylist of permutations of all the possible combinations of destinations
    public static ArrayList<ArrayList<String>> createPermutations(ArrayList<String> destinations) {
        if (destinations.size() <= 1) {
            ArrayList<ArrayList<String>> permutations = new ArrayList<ArrayList<String>>();

//            destinations.add("Marina Bay Sands");
            permutations.add(destinations);
            return permutations;
        }
        String finalElement = destinations.remove(0);
        ArrayList<ArrayList<String>> oldPermutations = createPermutations(destinations);
        ArrayList<ArrayList<String>> newPermutations = new ArrayList<ArrayList<String>>();
        for (ArrayList<String> sublist : oldPermutations) {
            for (int i = 0; i <= sublist.size(); i++) {
                ArrayList<String> newsublist = new ArrayList<String>(sublist);
                newsublist.add(i, finalElement);
                newPermutations.add(newsublist);
            }
        }
        return newPermutations;
    }

    //Check the permutationslist, delete unaccaptable ones
    public static ArrayList<ArrayList<String>> checkPermutations(ArrayList<ArrayList<String>> newPermutations){

        int permuteSize = newPermutations.size();
        ArrayList<ArrayList<String>> newerPermutations = new ArrayList<>();

        for(int i = 0; i<permuteSize;i++){
            if(newPermutations.get(i).get(0)=="Marina Bay Sands"){
                newerPermutations.add(newPermutations.get(i));
            }
        }
        return newerPermutations;
    }

    /**
     * Given a nested arraylist of permutations, choose the optimal path
     *
     * @param permutations
     * @return
     */
    public static ArrayList<String> getOptimalPath(ArrayList<ArrayList<String>> permutations) {
        double optimalTimeCost = -1.0;
        ArrayList<String> optimalpath = new ArrayList<String>();
        for (int i = 0; i < permutations.size(); i++) {
            double newTimeCost = TotalTimeCostAverage(permutations.get(i));
            if (optimalTimeCost == -1.0 || newTimeCost < optimalTimeCost) {
                optimalTimeCost = newTimeCost;
                optimalpath = permutations.get(i);
            }
        }
        return optimalpath;
    }

    /**
     * Return the time*cost average of traversing 2 nodes
     *
     * @param fromNode
     * @param toNode
     * @return
     */
    public static double TimeCostAverage(String fromNode, String toNode) {
        double walkingTimeCost = 1.0 * timeArray[2][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
        double ptTimeCost = timeArray[0][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
        double taxiTimeCost = timeArray[1][Destinations.getDestinationmap().get(toNode)]
                [Destinations.getDestinationmap().get(fromNode)];
        return (walkingTimeCost + ptTimeCost + taxiTimeCost) / 3;
    }

    public static double TotalTimeCostAverage(ArrayList<String> path) {
        double total = 0;
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) {
                total += TimeCostAverage(path.get(i), path.get(0));
            } else {
                total += TimeCostAverage(path.get(i), path.get(i + 1));
            }
        }
        return total;
    }

    /**
     * Given the optimal path based on time-cost average, the mode of transport for each of the locations
     * has to be calculated, with the budget constrained.
     * <p>
     * Initially assume taxi for every edge. If the budget is exceeded, find the shortest edge in terms of time
     * cost average, check if walking time is less than 15 minutes. If yes, then change mode to walking, else bus,
     * then check if budget has been exceededm repeat till the cost is within budget.
     */

    public static ArrayList<ArrayList<int[]>> chooseTransport(final ArrayList<String> path, double budget) {
        ArrayList<int[]> possibleModesOverbudget = new ArrayList<int[]>();
        ArrayList<int[]> possibleModesWithinbudget = new ArrayList<int[]>();

        //make all initial modes taxi
        int[] modes = new int[path.size()];
        for (int i = 0; i < modes.length; i++) {
            modes[i] = 1;
        }

        double totalCost = getTotalCost(modes, path);
        String[] shortestEdge = new String[2];
        ArrayList<String[]> shortEdges = new ArrayList<String[]>();

        while (true) {
            shortestEdge = nextShortestEdge("timeCostAverage", path, shortEdges);
            shortEdges.add(shortestEdge);

            //if walking takes < 15 mins, walk
            System.out.println(Arrays.toString(shortestEdge));
            if (timeArray[2][Destinations.getDestinationmap().get(shortestEdge[1])]
                    [Destinations.getDestinationmap().get(shortestEdge[0])] <= 15) {
                modes[path.indexOf(shortestEdge[0])] = 2;
                totalCost = getTotalCost(modes, path);
            }
            //otherwise, take public transport
            else {
                modes[path.indexOf(shortestEdge[0])] = 0;
                totalCost = getTotalCost(modes, path);
            }

            if (totalCost <= budget) {
                possibleModesWithinbudget.add(modes.clone());
            }

            if (totalCost > budget && totalCost <= budget + 10) {
                possibleModesOverbudget.add(modes.clone());
            }

            //Don't evaluate all the underbudget nodes, to not slowdown program
            // taking 5 possibilities are sufficient, choose 1

            if (possibleModesWithinbudget.size() > 5) {
                break;
            }

            // iterated over all edges once but still less than 5 possible modes to evaluate, none of which are taxi
            if (ArrayAllValuesMatch(2, modes, 1)) {
                break;
            }
        }
        shortEdges.clear();

        if(possibleModesWithinbudget.size()<5){
            while(true){
                shortestEdge = nextShortestEdge("walking", path, shortEdges);
                shortEdges.add(shortestEdge);
                System.out.println("No taxi, shortest edge " + shortestEdge[0] + " " + shortestEdge[1]);

                if(!(shortestEdge[0]==null)&&!(shortestEdge[1]==null)){
                    modes[path.indexOf(shortestEdge[0])] = 2;
                    totalCost = getTotalCost(modes,path);
                }


                if(totalCost <= budget){
                    possibleModesWithinbudget.add(modes.clone());
                }

                if(totalCost>budget && totalCost <= budget + 10){
                    possibleModesOverbudget.add(modes.clone());
                }

                if(possibleModesWithinbudget.size()>5){
                    break;
                }

                if(ArrayAllValuesMatch(0,modes,2)){
                    break;
                }


            }
        }
        possibleModesWithinbudget.sort((mode1,mode2)->{
            if(getTotalTime(mode1,path)==getTotalTime(mode2,path)){
                return 0;
            }
            if(getTotalTime(mode1,path)<getTotalTime(mode2,path)){
                return -1;
            }
            else{
                return 1;
            }

        });
        System.out.println("possibleModesWithinBudget before removal");
        for(int[] m:possibleModesWithinbudget){
            for(int i:m){
                System.out.println(i + " ");
            }
            System.out.println("");
        }

        System.out.println("possibleModesOverBudget before removal");
        for(int[] m:possibleModesOverbudget){
            for(int i:m){
                System.out.println(i + " ");
            }
            System.out.println("");
        }
        int[] optimalModes = possibleModesWithinbudget.remove(0);
        double optimalTotalCost = getTotalCost(optimalModes,path);
        int optimalTotalTime = getTotalTime(optimalModes,path);
        possibleModesWithinbudget.removeIf(m->getTotalTime(m,path)-optimalTotalTime>=30);
        possibleModesOverbudget.removeIf(m->(optimalTotalTime-getTotalTime(m,path))/(getTotalCost(m,path)-optimalTotalCost)<=2);

        //return Arraylist containing optimal modes, possible modes within budget, and possible modes overbudget
        ArrayList<int[]> optimalTotalModes = new ArrayList<int[]>();
        optimalTotalModes.add(optimalModes);
        ArrayList<ArrayList<int[]>> allPossibilities = new ArrayList<ArrayList<int[]>>(Arrays.asList(optimalTotalModes,possibleModesWithinbudget,possibleModesOverbudget));
//        for (ArrayList<int[]> m:allPossibilities){
//            for(int[] i:m){
//                System.out.println(Arrays.toString(i));
//            }
//
//        }
//        System.out.println("best path" + Arrays.toString(optimalModes));
        return allPossibilities;

    }

}

