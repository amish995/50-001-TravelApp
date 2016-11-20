package amish.travelapp;

import java.util.ArrayList;

/**
 * Created by woshibiantai on 19/11/16.
 */
///////////////////////////////////////////////////////////////////////////
// Gets the approximate shortest path using the Nearest Neighbor algorithm.
///////////////////////////////////////////////////////////////////////////

public class TSPfastApproximation {

    public static ArrayList<String> NearestNeighbor(ArrayList<String> destinations) {
        int size = destinations.size();
        int outputSize;

        /////////////////////////////////////////////////////////////////////////
        // the algorithm already takes into account the ending point: the hotel.
        // It is hence unnecessary to include the last destination if it is the
        // same as the start.
        /////////////////////////////////////////////////////////////////////////
        if (destinations.get(0).equals(destinations.get(size - 1))) {
            outputSize = size - 1;
        } else outputSize = size;

        ArrayList<String> output = new ArrayList<String>();
        // Remove the first point from destinations and add it to our output:
        output.add(destinations.remove(0));
        size = destinations.size(); // Gotta reset this value after removing!


        String nextDestination;
        double time = 0;
        while (output.size() < outputSize) {
            String source = output.get(output.size() - 1);  // the latest addition to output is the current source.
            String destination = destinations.get(0);   // after removing current node, the next destination is in index 0;
            time = TSPBruteforce.TimeCostAverage(source, destination);

            for (int i = 0; i < (size - 1); i++) {
                String compareSource = output.get(output.size() - 1);   // Keep the source the same
                String compareDestination = destinations.get(i);              // But vary the destination with any unvisited nodes (except the ending point)
                double newTime = TSPBruteforce.TimeCostAverage(compareSource, compareDestination);

//              If there is a shorter destination from the current source, pick this as the next destination instead.
                if (newTime < time) {
                    time = newTime;
                    destination = destinations.get(i);
                }

            }

            output.add(destination);
            destinations.remove(destination);
            size = destinations.size(); // Gotta reset this value after removing!

        }

        return output;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Swapping certain points with hopes to improve the time
    ///////////////////////////////////////////////////////////////////////////

    public static ArrayList<String> pleaseImprove(ArrayList<String> itinerary) {
        ArrayList<String> newPlan = new ArrayList<String>();
        double time = TSPBruteforce.TotalTimeCostAverage(itinerary);

        int point1 = 1;
        int point2 = 2;

        while (point1 < itinerary.size()) {
            while (point2 < itinerary.size()) {
                newPlan = swap(itinerary, point1, point2);
                double newTime = TSPBruteforce.TotalTimeCostAverage(newPlan);

                if (newTime < time) {   // Improvement found! Immediately swap,
                    itinerary = newPlan;
                    point1 = 1; // then reset point1 and point2 to continue checking
                    point2 = 2;
                } else {
                    point2++;
                }
            }
            point1++;
            point2 = point1 + 1;
        }
        return itinerary;
    }

    ///////////////////////////////////////////////////////////////////////////
    // The method for swapping points around, as used by pleaseImprove()
    ///////////////////////////////////////////////////////////////////////////
    private static ArrayList<String> swap(ArrayList<String> itinerary, int point1, int point2) {
//        if (point1 == point2) return itinerary;
//
//        else if (point1 > point2) {
//            int c = point1;
//            point1 = point2;
//            point2 = c;
//        }

        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < point1; i++) {
            output.add(itinerary.get(i));
        }
        for (int i = point2; i >= point1; i--) {    // Swapping everything in between point1 and point2
            output.add(itinerary.get(i));
        }
        for (int i = point2 + 1; i < itinerary.size(); i++) {
            output.add(itinerary.get(i));
        }

        return output;

    }

    public static ArrayList<ArrayList<int[]>> transportationRoute(final ArrayList<String> path, double budget) {
        return TSPBruteforce.chooseTransport(path, budget);
    }

}
