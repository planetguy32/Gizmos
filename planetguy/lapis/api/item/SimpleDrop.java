package planetguy.lapis.api.item;

import java.util.ArrayList;
import java.util.Random;


public class SimpleDrop implements Drop {
	
	public final String item;
	public final int min, max;
	
	public SimpleDrop(String item, int min, int max){
		this.item=item;
		this.min=min;
		this.max=max;
	}
	
	public String[] getDrops(Random r){
		ArrayList<String> strings=new ArrayList<String>(10);
		for(int i=0; i<r.nextInt(max-min)+min; i++){
			strings.add(item);
		}
		return strings.toArray(new String[0]);
	}
	
}
