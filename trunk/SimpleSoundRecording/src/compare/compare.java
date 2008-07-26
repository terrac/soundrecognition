package compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.ITuple;

public interface compare {

	public List compare(String name, List<PatternMatch.SoundBit> clist, List<ITuple<String>> contList);

	}
