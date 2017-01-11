
/**
 * Write a description of ClosestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int j = 0; j<howMany; j++){
            int minIndex = 0;
            for(int k = 1; k<copy.size(); k++){
                QuakeEntry quake = copy.get(k);
                Location loc = quake.getLocation(); 
                    if(loc.distanceTo(current) 
                    < copy.get(minIndex).getLocation().distanceTo(current)){
                     minIndex = k;
                    }
                }
        ret.add(copy.get(minIndex));
        copy.remove(minIndex);
    }
        return copy;
    
    }
    public void findCloseQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
        Location jakarta = new Location(-6.211,106.845);
        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 10);
        for(int i = 0; i<close.size(); i++){
            QuakeEntry entry = close.get(i);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, entry);
            
        }
        System.out.println("Number Found: " + close.size());
    }
    
}
