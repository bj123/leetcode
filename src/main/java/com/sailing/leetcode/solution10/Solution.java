package com.sailing.leetcode.solution10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 2018-03-19 create NFA
 * 2018-03-20 use NFA
 * yangyang
 */
public class Solution {



    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0){
            return true;
        }
        Node head = createNFA(p);
        return filter(s, head);
    }

    private boolean filter(String s, Node p) {
        Node head = p;
        LinkedList<Node> backtrackList = new LinkedList<>();
        int comsume = 0;
        boolean neddBack = false;
        while(true){
            System.out.println(comsume);
            display(head);
            if(!neddBack) {
                if(p == null && comsume == s.length()){
                    return true;
                }
                if(p == null){
                    neddBack = true;
                    continue;
                }

                if(p.loop != null){
                    backtrackList.add(p);
                    p = p.next;
                    continue;
                }

                if(p.status == -1 && comsume == s.length()){
                    return true;
                }


                if(comsume >= s.length()){
                    neddBack = true;
                    continue;
                }

                Node next = null;
                if(p.path.equals(s.charAt(comsume) + "") || p.path.equals("inf")){
                    next = p.next;
                }

                if(next != null){
                    comsume = comsume + 1;
                    p = next;
                }else {
                    neddBack = true;
                }
            }else {
                //do backtrack
                boolean canMatch = false;
                while (!canMatch) {
                    if(backtrackList.size() == 0){
                        System.out.println("");
                    }
                    p = null;
                    comsume = 0;
                    for(Node node : backtrackList){
                        comsume = comsume + node.useCount;
                        p = node;
                    }

                    if(p == null){
                        return false;
                    }

                    comsume = p.start + comsume;
                    if(p.status == -1 && comsume == s.length()){
                        return true;
                    }

                    if(comsume >= s.length()){

                        Node t = backtrackList.pollLast();
                        t.useCount = 0;
                        continue;
                    }
                    char tmp = s.charAt(comsume);

                    if ((tmp + "").equals(p.loop) || p.loop.equals("inf")){
                        canMatch = true;
                    }

                    if (!canMatch) {
                        Node t = backtrackList.pollLast();
                        t.useCount = 0;
                    }else {
                        comsume = comsume + 1;
                        p.useCount ++;
                        p = p.next;
                    }
                }
                neddBack = false;
            }
        }
    }

    private void display(Node head) {
        while (head != null){
            if(head.loop == null){
                System.out.print("(" + head.path + ")");
            }else {
                System.out.print("(" + head.useCount + head.path + ")");
            }
            head = head.next;
        }
        System.out.println();
    }


    private Node createNFA(String p) {
        Node head = new Node(0);
        Node cur = head;
        int nextStatus = 0;
        for(int i = 0; i < p.length(); i ++){
            char c = p.charAt(i);
            nextStatus = nextStatus + 1;
            Node next = new Node(nextStatus);
            next.pre = cur;
            int oc = 0;
            if(i + 1 < p.length() && p.charAt(i + 1) == '*'){
                if(c == '.'){
                    cur.loop = "inf";
                }else {
                    cur.loop = c + "";
                }
                cur.start = oc;
                if(i + 1 == p.length() - 1){
                    cur.status = -1;
                    break;
                }else {
                    i = i + 1;
                    cur.next = next;
                    cur.path = "^";
                    cur = next;
                }
            }else {
                if (c == '.') {
                    cur.next = next;
                    cur.path = "inf";
                } else {
                    cur.next = next;
                    cur.path = c + "";
                }
                oc ++;
                if (i == p.length() - 1) {
                    next.status = -1;
                }
                cur = next;
            }
        }
        while (cur.pre != null && cur.pre.loop != null){
            cur.pre.status = -1;
            cur = cur.pre;
        }
        return head;
    }


    static class Node{
        public Node(int status){
            this.status = status;
        }
        Node pre;
        String path;
        Node next;
        int status;

        String loop = null;
        int useCount;
        int start;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab","a.*b"));
    }
}
