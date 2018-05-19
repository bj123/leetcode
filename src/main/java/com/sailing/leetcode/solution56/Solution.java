package com.sailing.leetcode.solution56;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

            return intervals;
        Iterator<Interval> itor = result.iterator();
            if(cur.start > interval.end || cur.end < interval.start){
            }
        }
        for(Interval cur : needMerge){
            if(cur.end > interval.end){
                interval.end = cur.end;
            }

            if(cur.start < interval.start){
                interval.start = cur.start;
            }
        }

        result.add(interval);
    }


    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }
     
}
