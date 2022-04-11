package com.fast.summary.jvm.thread;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class TestAqs {
    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }


    public static int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int right = 1;
        int max=1;
        char[] array = s.toCharArray();
        set.add(array[0]);
        while(right<array.length) {
            if(set.contains(array[right])) {
                set.remove(array[left]);
                left++;
            } else {
                set.add(array[right]);
                max = right - left + 1 > max ? right - left + 1:max;
                right++;
            }
        }
        return max;
    }

}

class Solution {
    public boolean isValid(String s) {
        char[] array = s.toCharArray();
        HashMap<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < array.length ; i++) {
            char c = array[i];
            if(map.containsKey(c)) {
                stack.push(map.get(c));
            } else {
                if (stack.size() > 0 && c == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0 ? true : false;
    }
}
