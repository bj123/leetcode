package com.sailing.leetcode.solution282;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> addOperators(String num, int target) {
        if(num == null || num.length() == 0){
            return new ArrayList<>();
        }
        int[] nums = new int[num.length()];
        for(int i = 0; i < num.length(); i++){
            nums[i] = num.charAt(i) - '0';
        }

        List<String> rs = collect(nums, nums.length - 1, target);
        return rs;
    }

    private List<String> collect(int[] nums, int end, int target) {
        List<String> rs = new ArrayList<>();
        if(end == 0){
            if(nums[end] == target) {
                rs.add(String.valueOf(target));
            }
            return rs;
        }

        if(end == 1){
            int a = nums[0];
            int b = nums[1];
            if(a - b == target){
                rs.add(a + "-" + b);
            }

            if(a + b == target){
                rs.add(a + "+" + b);
            }

            if(a * b == target){
                rs.add(a + "*" + b);
            }

            if(a * 10 + b == target){
                rs.add(a + "" + b);
            }
            return rs;
        }

        //if last num use +
        addto(rs, collect(nums, end - 1, target - nums[end]), 1, "+" + nums[end]);
        //if last num use -
        addto(rs, collect(nums, end - 1, target + nums[end]), 2, "-" + nums[end]);
        //if last num use *
        int p = nums[end];
        String ppp = String.valueOf(p);
        for(int i = end; i > 0; i--) {
            p = p * nums[i - 1];
            ppp = nums[i - 1] + "*" + ppp;
            if(i - 2 == -1 ){
                if(p == target){
                    rs.add(ppp);
                }
                break;
            }
            addto(rs, collect(nums, i - 2, target - p ), 3, "+" + ppp);
            addto(rs, collect(nums, i - 2, target + p ), 3, "-" + ppp);
        }
        //if use connected
        p = nums[end];
        ppp = String.valueOf(p);
        int base = 10;
        for(int i = end; i > 0; i--) {
            p =  p + nums[i - 1] *  base;

            ppp = nums[i - 1] +  ppp;
            if(i - 2 == -1 ){
                if(p == target){
                    rs.add(ppp);
                }
                break;
            }
            base = base * 10;

            addto(rs, collect(nums, i - 2, target - p), 3, "+" + ppp);
            addto(rs, collect(nums, i - 2, target + p), 3, "-" + ppp);
        }

        return rs;
    }

    private void addto(List<String> rs, List<String> collect, int i, String s) {
        for(String c : collect){
            String k = c;
            rs.add(c + s);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().addOperators("232", 8));
    }
}
