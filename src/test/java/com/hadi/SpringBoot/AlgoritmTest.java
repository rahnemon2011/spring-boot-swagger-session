package com.hadi.SpringBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class AlgoritmTest {

    @Test
    public void rotateArray() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        int[] results = new int[nums.length];
        int startPoint = results.length - k;
        int[] ints = Arrays.copyOfRange(nums, startPoint, results.length);
        for (int i = 0; i < ints.length; i++) {
            results[i] = ints[i];
        }
        for (int i = 0; i <= k; i++) {
            results[k + i] = nums[i];
        }
        System.out.println("results = " + Arrays.toString(results));
    }

    @Test
    public void LeetCode() {
        String start = "hit";
        String end = "cog";
        String[] dict = {"hot", "dot", "dog", "lot", "log"};

        for (String s : dict) {

        }
    }

    @Test
    public void wildcardMatching() {
        String s = "aab";
        String p = "*ab";
        boolean b = isMatch(s, p);
        System.out.println("b = " + b);

    }

    private boolean isMatch(String s, String p) {
        int i = 0;
        StringBuilder chars = new StringBuilder();
        while (i < p.length()) {
            if (p.charAt(i) != '?' && p.charAt(i) != '*') {
                chars.append(p.charAt(i));
                i++;
            } else {
                if (! s.startsWith(chars.toString())) {
                    return false;
                } else {
                    
                }
            }

        }
        return true;
    }

} 