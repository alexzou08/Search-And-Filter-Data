
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;


public class DistanceFilter implements Filter {
    private Location myLocation;
    private double max;
    
    public DistanceFilter(double lat,double lon, double maxDistance) {
        myLocation = new Location(lat, lon);
        max = maxDistance;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getLocation().distanceTo(myLocation) <= max; 
    }
    
    public String getName() {
        return "Distance";
    }
}
