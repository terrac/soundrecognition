package PatternMatch;
import java.io.Serializable;
import java.util.List;




public class Block implements Serializable{
	public int z;
	public int y;
	
	public boolean execute(List<SoundBit> a, BlockCompare z, int highest) {
		throw new RuntimeException("wut");		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "b" + z +" "+y;
	}
}
