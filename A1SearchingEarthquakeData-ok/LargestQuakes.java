
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.lang.*;

public class LargestQuakes {
    // print answers
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        /*
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
        System.out.println("read data for "+list.size()+" quakes");
        /*int largestIndex = indexOfLargest(list);
        System.out.println("location at " + largestIndex + ":" + 
                           list.get(largestIndex).getMagnitude());
        */
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 5);
        for (QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }
        
    }
    //This method returns an integer representing the index location 
    //in data of the earthquake with the largest magnitude.
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry>(data);
        int largestIndex = 0;
        double largestMag = list.get(largestIndex).getMagnitude();
        for(int k=1; k<list.size(); k++) {
            QuakeEntry quake = list.get(k);
            double mag = quake.getMagnitude();
            if(mag > largestMag) {
                   largestIndex = k;
                   largestMag = mag;
            }
        }
        return largestIndex;
    }
    //his method returns an ArrayList of type QuakeEntry of 
    //the top howMany largest magnitude earthquakes from quakeData. 
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        int largestIndex = 0;
        //If quakeData has fewer than howMany earthquakes, 
        //then the number of earthquakes returned in the ArrayList is equal to 
        //the number of earthquakes in quakeData
        int length = Math.min(howMany, quakeData.size());
        for(int i = 0; i < length; i++) {
            largestIndex = indexOfLargest(copy);
            ret.add(copy.get(largestIndex));
            copy.remove(largestIndex);
        }
        return ret;
    }
}
