package amish.travelapp;

import java.util.HashMap;

/**
 * Created by Jonbeibeibei on 11/14/2016.
 */
public class Destinations {

    Destinations(){
        super();
    }

    static double[][][] getCostsArray(){
        return costsArray;
    }

    static int[][][] getTimeArray(){
        return timeArray;
    }

    static HashMap<String,Integer> getDestinationmap(){
        return destinationmap;
    }


    private static final HashMap<String,Integer> destinationmap = new HashMap<String,Integer>();
    static {
        destinationmap.put("Marina Bay Sands",0);
        destinationmap.put("Singapore Flyer",1);
        destinationmap.put("Vivo City",2);
        destinationmap.put("Resorts World Sentosa",3);
        destinationmap.put("Buddha Tooth Relic Temple",4);
        destinationmap.put("Zoo",5);
    }

    private static final int numOfDestinations = destinationmap.size();

    private static final double[][][] costsArray = new double[3][numOfDestinations][numOfDestinations];
    static {
//          the first index represents the mode of transport.
//          second index represents the destination of travel
//          third index represents the source of travel
        //---------- Public Transportation Cost---------------
        //from any to Marina Bay Sands
        costsArray[0][0][1] = 0.83;
        costsArray[0][0][2] = 1.18;
        costsArray[0][0][3] = 1.18;
        costsArray[0][0][4] = 0.88;
        costsArray[0][0][5] = 1.88;

        //from any to Singapore Flyer
        costsArray[0][1][0] = 0.83;
        costsArray[0][1][2] = 1.26;
        costsArray[0][1][3] = 1.26;
        costsArray[0][1][4] = 0.98;
        costsArray[0][1][5] = 1.96;

        //from any to Vivocity
        costsArray[0][2][0] = 1.18;
        costsArray[0][2][1] = 1.26;
        costsArray[0][2][3] = 0.00;
        costsArray[0][2][4] = 0.98;
        costsArray[0][2][5] = 2.11;

        //from any to Resort World Sentosa
        costsArray[0][3][0] = 4.03;
        costsArray[0][3][1] = 4.03;
        costsArray[0][3][2] = 2.00;
        costsArray[0][3][4] = 3.98;
        costsArray[0][3][5] = 4.99;

        //from any to Buddha Tooth Relic Temple
        costsArray[0][4][0] = 0.88;
        costsArray[0][4][1] = 0.98;
        costsArray[0][4][2] = 0.98;
        costsArray[0][4][3] = 0.98;
        costsArray[0][4][5] = 1.91;

        //from any to Zoo
        costsArray[0][5][0] = 1.96;
        costsArray[0][5][1] = 1.89;
        costsArray[0][5][2] = 1.99;
        costsArray[0][5][3] = 1.99;
        costsArray[0][5][4] = 1.91;

        //------------Taxi Cost------------------
        //from any to Marina Bay Sands
        costsArray[1][0][1] = 4.32;
        costsArray[1][0][2] = 8.30;
        costsArray[1][0][3] = 8.74;
        costsArray[1][0][4] = 5.32;
        costsArray[1][0][5] = 22.48;

        //from any to Singapore Flyer
        costsArray[1][1][0] = 3.22;
        costsArray[1][1][2] = 7.96;
        costsArray[1][1][3] = 8.40;
        costsArray[1][1][4] = 4.76;
        costsArray[1][1][5] = 19.40;

        //from any to Vivocity
        costsArray[1][2][0] = 6.96;
        costsArray[1][2][1] = 7.84;
        costsArray[1][2][3] = 3.22;
        costsArray[1][2][4] = 4.98;
        costsArray[1][2][5] = 21.48;

        //from any to Resort World Sentosa
        costsArray[1][3][0] = 8.50;
        costsArray[1][3][1] = 9.38;
        costsArray[1][3][2] = 4.54;
        costsArray[1][3][4] = 6.52;
        costsArray[1][3][5] = 23.68;

        //from any to Buddha Tooth Relic Temple
        costsArray[1][4][0] = 4.98;
        costsArray[1][4][1] = 4.76;
        costsArray[1][4][2] = 6.42;
        costsArray[1][4][3] = 6.64;
        costsArray[1][4][5] = 21.60;

        //from any to Zoo
        costsArray[1][5][0] = 18.40;
        costsArray[1][5][1] = 18.18;
        costsArray[1][5][2] = 22.58;
        costsArray[1][5][3] = 22.80;
        costsArray[1][5][4] = 18.40;

        //------------Walk Cost------------------
        //from any to Marina Bay Sands
        costsArray[2][0][1] = 0;
        costsArray[2][0][2] = 0;
        costsArray[2][0][3] = 0;
        costsArray[2][0][4] = 0;
        costsArray[2][0][5] = 0;

        //from any to Singapore Flyer
        costsArray[2][1][0] = 0;
        costsArray[2][1][2] = 0;
        costsArray[2][1][3] = 0;
        costsArray[2][1][4] = 0;
        costsArray[2][1][5] = 0;

        //from any to Vivocity
        costsArray[2][2][0] = 0;
        costsArray[2][2][1] = 0;
        costsArray[2][2][3] = 0;
        costsArray[2][2][4] = 0;
        costsArray[2][2][5] = 0;

        //from any to Resort World Sentosa
        costsArray[2][3][0] = 0;
        costsArray[2][3][1] = 0;
        costsArray[2][3][2] = 0;
        costsArray[2][3][4] = 0;
        costsArray[2][3][5] = 0;

        //from any to Buddha Tooth Relic Temple
        costsArray[2][4][0] = 0;
        costsArray[2][4][1] = 0;
        costsArray[2][4][2] = 0;
        costsArray[2][4][3] = 0;
        costsArray[2][4][5] = 0;

        //from any to Zoo
        costsArray[2][5][0] = 0;
        costsArray[2][5][1] = 0;
        costsArray[2][5][2] = 0;
        costsArray[2][5][3] = 0;
        costsArray[2][5][4] = 0;
    }

    private static final int[][][] timeArray = new int[3][numOfDestinations][numOfDestinations];
    static{
        //---------Public Transport Time----------------
        //from any to Marina Bay Sands
        timeArray[0][0][1] = 17;
        timeArray[0][0][2] = 24;
        timeArray[0][0][3] = 33;
        timeArray[0][0][4] = 18;
        timeArray[0][0][5] = 86;

        //from any to Singapore Flyer
        timeArray[0][1][0] = 17;
        timeArray[0][1][2] = 29;
        timeArray[0][1][3] = 38;
        timeArray[0][1][4] = 23;
        timeArray[0][1][5] = 87;

        //from any to Vivocity
        timeArray[0][2][0] = 26;
        timeArray[0][2][1] = 31;
        timeArray[0][2][3] = 10;
        timeArray[0][2][4] = 19;
        timeArray[0][2][5] = 86;

        //from any to Resort World Sentosa
        timeArray[0][3][0] = 35;
        timeArray[0][3][1] = 38;
        timeArray[0][3][2] = 10;
        timeArray[0][3][4] = 28;
        timeArray[0][3][5] = 96;

        //from any to Buddha Tooth Relic Temple
        timeArray[0][4][0] = 19;
        timeArray[0][4][1] = 24;
        timeArray[0][4][2] = 18;
        timeArray[0][4][3] = 27;
        timeArray[0][4][5] = 84;

        //from any to Zoo
        timeArray[0][5][0] = 84;
        timeArray[0][5][1] = 85;
        timeArray[0][5][2] = 85;
        timeArray[0][5][3] = 92;
        timeArray[0][5][4] = 83;

        //-----------Taxi Time--------------
        //from any to Marina Bay Sands
        timeArray[1][0][1] = 6;
        timeArray[1][0][2] = 12;
        timeArray[1][0][3] = 13;
        timeArray[1][0][4] = 7;
        timeArray[1][0][5] = 32;

        //from any to Singapore Flyer
        timeArray[1][1][0] = 3;
        timeArray[1][1][2] = 14;
        timeArray[1][1][3] = 14;
        timeArray[1][1][4] = 8;
        timeArray[1][1][5] = 29;

        //from any to Vivocity
        timeArray[1][2][0] = 14;
        timeArray[1][2][1] = 13;
        timeArray[1][2][3] = 4;
        timeArray[1][2][4] = 9;
        timeArray[1][2][5] = 32;

        //from any to Resort World Sentosa
        timeArray[1][3][0] = 19;
        timeArray[1][3][1] = 18;
        timeArray[1][3][2] = 9;
        timeArray[1][3][4] = 14;
        timeArray[1][3][5] = 36;

        //from any to Buddha Tooth Relic Temple
        timeArray[1][4][0] = 8;
        timeArray[1][4][1] = 8;
        timeArray[1][4][2] = 11;
        timeArray[1][4][3] = 12;
        timeArray[1][4][5] = 30;

        //from any to Zoo
        timeArray[1][5][0] = 30;
        timeArray[1][5][1] = 29;
        timeArray[1][5][2] = 31;
        timeArray[1][5][3] = 32;
        timeArray[1][5][4] = 30;

        //--------Foot Time------------------
        //from any to Marina Bay Sands
        timeArray[2][0][1] = 14;
        timeArray[2][0][2] = 69;
        timeArray[2][0][3] = 76;
        timeArray[2][0][4] = 28;
        timeArray[2][0][5] = 269;

        //from any to Singapore Flyer
        timeArray[2][1][0] = 14;
        timeArray[2][1][2] = 81;
        timeArray[2][1][3] = 88;
        timeArray[2][1][4] = 39;
        timeArray[2][1][5] = 264;

        //from any to Vivocity
        timeArray[2][2][0] = 69;
        timeArray[2][2][1] = 81;
        timeArray[2][2][3] = 12;
        timeArray[2][2][4] = 47;
        timeArray[2][2][5] = 270;

        //from any to Resort World Sentosa
        timeArray[2][3][0] = 76;
        timeArray[2][3][1] = 88;
        timeArray[2][3][2] = 12;
        timeArray[2][3][4] = 55;
        timeArray[2][3][5] = 285;

        //from any to Buddha Tooth Relic Temple
        timeArray[2][4][0] = 28;
        timeArray[2][4][1] = 39;
        timeArray[2][4][2] = 47;
        timeArray[2][4][3] = 55;
        timeArray[2][4][5] = 264;

        //from any to Zoo
        timeArray[2][5][0] = 269;
        timeArray[2][5][1] = 264;
        timeArray[2][5][2] = 270;
        timeArray[2][5][3] = 285;
        timeArray[2][5][4] = 264;

    }
}
