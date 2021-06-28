import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    //This method should return an ArrayList of type QuakeEntry of all the earthquakes 
    //from quakeData that have a magnitude larger than magMin.
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData) {
            if(qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }
    //return an ArrayList of type QuakeEntry of all the earthquakes from quakeData
    //that are less than distMax from the location from
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData) {
            if(qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    //return an ArrayList of type QuakeEntry of all the earthquakes from quakeData
    //whose depth is between minDepth and maxDepth, exclusive.
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    //use filterByDepth and print all the earthquakes from a data source 
    //whose depth is between a given minimum and maximum value. 
    //You should also print out the number of earthquakes found. 
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listDepth = filterByDepth(list, -10000.0, -5000.0);
        for(QuakeEntry qe : listDepth) {
            System.out.println(qe);
        }
        System.out.println("Found " + listDepth.size() + " quakes that match the critera");
    }
    //method should return an ArrayList of type QuakeEntry of all the earthquakes 
    //from quakeData whose titles have the given phrase found at location where 
    //(“start” means the phrase must start the title, “end” means the phrase must 
    //end the title and “any” means the phrase is a substring anywhere in the title.)
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if (where.equals("start")) {
            for(QuakeEntry qe : quakeData) {
                String title = qe.getInfo();   
                if(title.startsWith(phrase)) {
                    answer.add(qe);
                }
            }
        }
        if (where.equals("end")) {
            for(QuakeEntry qe : quakeData) {
                String title = qe.getInfo();   
                if(title.endsWith(phrase)) {
                    answer.add(qe);
                }
            }
        }
        if (where.equals("any")) {
            for(QuakeEntry qe : quakeData) {
                String title = qe.getInfo();   
                if(title.indexOf(phrase) != -1) {
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
    //use filterByPhrase and print all the earthquakes from a data source
    //that have phrase in their title in a given position in the title. 
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listPhrase = filterByPhrase(list, "any", "Can");
        for(QuakeEntry qe : listPhrase) {
            System.out.println(qe);
        }
        System.out.println("Found " + listPhrase.size() + " quakes that match the critera");
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    //use filterByMagnitude and print earthquakes above a certain magnitude, 
    //and also print the number of such earthquakes
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : listBig) {
            System.out.println(qe);
        }
        System.out.println("Found " + listBig.size() + " quakes that match the critera");
    }
    // has no parameters to call filterByDistance to print out the earthquakes within
    //1000 Kilometers to a specified city (such as Durham, NC). For each earthquake found, 
    //print the distance from the earthquake to the specified city, 
    //followed by the information about the city (use getInfo()).
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);
        
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for(int k=0; k < close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("Found " + close.size() + " quakes that match the critera");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
