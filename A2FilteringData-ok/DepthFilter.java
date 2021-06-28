
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class DepthFilter implements Filter {
    private double min; 
    private double max;
    public DepthFilter(double minDepth, double maxDepth) { 
        min = minDepth;
        max = maxDepth;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getDepth() >= min && qe.getDepth() <= max; 
    } 
    
    public String getName() {
        return "Depth";
    }
}
