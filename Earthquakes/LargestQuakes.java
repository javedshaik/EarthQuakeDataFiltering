
/**
 * Write a description of ClosestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
        Location jakarta = new Location(-6.211,106.845);
        ArrayList<QuakeEntry> close = getLargest(list,50);
        for(int i = 0; i<close.size(); i++){
            QuakeEntry entry = close.get(i);
            System.out.println(entry);
            
        }
        System.out.println("Number Found: " + close.size());
    }
   public int indexOfLargest(ArrayList<QuakeEntry> data){
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
		int largeIndex = 0;
		double maxMagnitude = 0.0;
		for(int k=1; k < copy.size(); k++) {
			QuakeEntry quake = copy.get(k);
			double mag = quake.getMagnitude();
			if(mag > maxMagnitude) {
				largeIndex = k;
				maxMagnitude = mag;
			}
		}
		return largeIndex;
	}

	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
		ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
		if(howMany > copy.size()) 
			howMany = copy.size();
		int largeIndex = 0;
		for(int j=0; j<howMany; j++){
			largeIndex = indexOfLargest(copy);
			result.add(copy.get(largeIndex));
			copy.remove(largeIndex);
		}
		return result;
	}
}

