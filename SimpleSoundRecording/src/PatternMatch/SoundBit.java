package PatternMatch;
public class SoundBit {
	public int len = 2;
	public int height = 0;

	// int compare(SoundBit s) {
	// return Math.abs(( lenup - s.lenup) * weightlen )
	// + Math.abs(( height - s.height) * weightheight)
	// + Math.abs((lendown - s.lendown) * weightlen );
	// }
	public SoundBit() {
		// TODO Auto-generated constructor stub
	}
	public SoundBit(int a) {
		height = a;
	}
	public boolean compare(SoundBit s) {
		return s.height == height;
	}

	int compareint(SoundBit s) {
		return
		// Math.abs((lenup - s.lenup)) > 1
		// ||
		Math.abs((height - s.height))
		// || Math.abs((lendown - s.lendown)) > 1
		;
	}

	@Override
	public String toString() {
		return height+""; 
	}
	public int length(){
		return len;
	}
}
