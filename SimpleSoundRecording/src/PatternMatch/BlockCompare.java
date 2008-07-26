package PatternMatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlockCompare {

	
	

	public void execute(List<SoundBit> a,List<Block> block) {
		int highest = 0;

		for (SoundBit soundBit : a) {

			if (highest < soundBit.height) {
				highest = soundBit.height;

			}
		}
		height = highest;
		length = a.size();
		for (Block b : block) {		
			if (b.execute(a, this)) {
				blocksFound.add(b);

			}
		}
	}

	public int compare(BlockCompare a) {
		// TODO Auto-generated method stub
		int count = 0;
		for (Block b : a.blocksFound) {
			boolean flag = false;
			for (Block c : blocksFound) {
				if (c.equals(b))
					flag = true;
			}
			if (flag)
				count++;
		}
		return count;

	}

	List<Block> blocksFound = new ArrayList<Block>();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return blocksFound.toString();
	}

	public int height;
	public int length;
}
