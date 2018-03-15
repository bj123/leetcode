package com.sailing.leetcode.solution46;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yangyang on 2018/3/15.
 * beat 16%
 */
public class Solution {
    int LIMIT = 0;
    public List<List<Integer>> permute(int[] nums) {
        boolean[] f = new boolean[nums.length];
        LIMIT = 2 * nums.length;
        List<List<Integer>> rs = getP(nums, f, nums.length);
        return rs;
    }

    private List<List<Integer>> getP(int[] nums, boolean[] f, int remain) {
        List<List<Integer>> ss = new LinkedList<>();
        if(remain == 1){
            List<Integer> s = new ArrayList<>(LIMIT);
            for(int j = 0; j < nums.length; j++){
                if(!f[j]){
                    s.add(nums[j]);
                }
            }
            ss.add(s);
            return ss;
        }

        for(int i = 0; i < nums.length; i++){
            if(f[i]){
                continue;
            }

            f[i] = true;
            List<List<Integer>> curRs  = getP(nums, f, remain - 1);
            f[i] = false;
            for(List<Integer> r : curRs){
                r.add(nums[i]);
                ss.add(r);
            }
        }
        return ss;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
    }
}
