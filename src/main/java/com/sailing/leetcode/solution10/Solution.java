package com.sailing.leetcode.solution10;

import java.util.HashMap;
import java.util.Map;

/**
 * 2018-03-19 create NFA
 * 2018-03-20 use NFA
 * yangyang
 */
public class Solution {

    public boolean isMatch(String s, String p) {
        Node head = createNFA(p);
        return true;
    }



    private Node createNFA(String p) {
        Node head = new Node(0);
        Node cur = head;
        char pre = 0;
        for(int i = 0; i < p.length(); i ++){
            char c = p.charAt(i);
            int nextStatus = i + 1;
            Node next = null;
            if(c == '.'){
                next = new Node(nextStatus);
                cur.next.put("inf",next);
            }else if(c == '*'){
                next = cur;
                if(pre == '.'){
                    cur.next.put("inf", cur);
                }else {
                    cur.next.put(pre + "", cur);
                }
            }else{
                next = new Node(nextStatus);
                cur.next.put(c + "", next);
            }

            if(i == p.length() - 1){
                next.status = -1;
            }
            cur = next;
            pre = c;
        }
        return head;
    }


    static class Node{
        public Node(int status){
            this.status = status;
        }

        Map<String ,Node> next = new HashMap<>();
        int status;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab","a.*b"));
    }
}
