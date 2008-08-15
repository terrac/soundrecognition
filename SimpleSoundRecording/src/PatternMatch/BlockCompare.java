package PatternMatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import catalogues.Catalogue;

public class BlockCompare implements Serializable{

	
	

	public void execute(List<SoundBit> a,List<Block> block) {
		int highest = 0;

		for (SoundBit soundBit : a) {

			if (highest < soundBit.height) {
				highest = soundBit.height;

			}
		}
		for (Block b : block) {		
			if (b.execute(a, this, highest)) {
				blocksFound.add(b);

			}
		}
		//System.out.println(blocksFound);
		Catalogue.printout += blocksFound;
	}

	
	public List<Block> blocksFound = new ArrayList<Block>();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return blocksFound.toString();
	}

}
