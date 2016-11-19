package amish.travelapp;

/**
 * Created by Amish on 20-11-2016.
 */
import net.java.frej.Regex;

public class SpellChecker {
    public static String check_spelling(String inp){
        String input = inp;
        String output = inp;

        input = input.toLowerCase();
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[^a-zA-Z ]", "");

        Regex test6a = new Regex("[buddhatoothrelictemple]");
        Regex test6b = new Regex("[toothrelictemple]");
        Regex test6c = new Regex("[buddhatoothtemple]");

        Regex test68a = new Regex("[SingaporeFlyer]");
        Regex test68b = new Regex("[Flyer]");
        Regex test68c = new Regex("[FlyerSingapore]");

        Regex test75a = new Regex("[SingaporeZoo]");
        Regex test75b = new Regex("[Zoo]");
        Regex test75c = new Regex("[ZooofSingapore]");

        Regex test98a = new Regex("[marinabaysands]");
        Regex test98b = new Regex("[mbs]");
        Regex test98c = new Regex("[marinasands]");

        Regex test99a = new Regex("[vivocity]");
        Regex test99b = new Regex("[vivo city]");
        Regex test99c = new Regex("[vivo's city]");

        Regex test100a = new Regex("[Resortsworldsentosa]");
        Regex test100b = new Regex("[sentosa]");
        Regex test100c = new Regex("[sentosabeach]");

        if (test6a.match(input)|test6b.match(input)|test6c.match(input)|input.equals("BTRT")) {
            output = "Buddha Tooth Relic Temple";
        } else if (test68a.match(input)|test68b.match(input)|test68c.match(input)|input.equals("SF")) {
            output = "Singapore Flyer";
        }else if (test75a.match(input)|test75b.match(input)|test75c.match(input)|input.equals("SZ")) {
            output = "Singapore Zoo";
        }else if (test98a.match(input)|test98b.match(input)|test98c.match(input)|input.equals("MBS")) {
            output = "Marina Bay Sands";
        }else if (test99a.match(input)|test99b.match(input)|test99c.match(input)|input.equals("VC")) {
            output = "VivoCity";
        }else if (test100a.match(input)|test100b.match(input)|test100c.match(input)|input.equals("RWS")) {
            output = "Resorts World Sentosa";
        }else {
            output = inp;
        }

        return output;
    }
}
