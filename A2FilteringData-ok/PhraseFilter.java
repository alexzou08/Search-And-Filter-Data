
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class PhraseFilter implements Filter {
    //a String representing the type of request that indicates where to search in the title
    //and has one of three values: (“start”, ”end”, or “any”)
    private String where;
    private String phrase;
    
    public PhraseFilter(String choose, String input) {
        where = choose; //one of three values: (“start”, ”end”, or “any”)
        phrase = input;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        String title = qe.getInfo();
        if(where.equals("start")){
            if(title.startsWith(phrase)) {
                return true;
            }
        }
        if(where.equals("end")){
            if(title.endsWith(phrase)) {
                return true;
            }
        }
        if(where.equals("any")){
            if(title.indexOf(phrase) != -1) {
                return true;
            }
        }
        return false; 
    }
    
    public String getName() {
        return "Phrase";
    }
}
