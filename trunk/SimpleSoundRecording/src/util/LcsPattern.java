package util;
import java.util.List;

import main.Pattern;
import main.dir;
 
public class LcsPattern extends LongestCommonSubsequence<Pattern> {
        private List<Pattern> x;
        private List<Pattern> y;
 
        public LcsPattern(List<Pattern> from, List<Pattern> to) {
                this.x = from;
                this.y = to;
        }
 
        protected int lengthOfY() {
                return y.size();
        }
        protected int lengthOfX() {
                return x.size();
        }
        protected Pattern valueOfX(int index) {
                return x.get(index);
        }
        protected Pattern valueOfY(int index) {
                return y.get(index);
        }
 
        public String getHtmlDiff() {
                DiffType type = null;
                List<DiffEntry<Pattern>> diffs = diff();
                StringBuffer buf = new StringBuffer();
 
                for(DiffEntry<Pattern> entry : diffs) {
                        if(type != entry.getType()) {
                                if(type != null) {
                                        buf.append("</span>\n");
                                }
                                buf.append("<span class=\""+entry.getType().getName()+"\">");
                                type = entry.getType();
                        }
                        buf.append(entry.getValue());
                }   
                buf.append("</span>\n\n");
                return buf.toString();
        }
 
       
 

 
}
