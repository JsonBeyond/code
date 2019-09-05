package com.alex.leetocode.arithmatic;

import com.alex.arithmatic.help.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author Alex
 * @CreateDate 26/07/2019 15:09
 * @Version 1.0
 */
public class Solution {

    public static void main(String[] args) {
//        System.out.println(Solution.twoSum2(new int[]{3, 2, 4}, 6).toString());

//        ListNode l1 = new ListNode(3);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(2);
//        l1.next.next.next = new ListNode(7);
//        ListNode l2 = new ListNode(4);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(5);
//        ListNode l3 = Solution.addTwoNumbers(l1, l2);
//        while (l3 != null) {
//            System.out.println(l3.val);
//            l3 = l3.next;
//        }
//        System.out.println(lengthOfLongestSubstring1("au"));
//        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(titleToNumber("AAA"));
    }

    /**
     * 在有序数组中找出两个数，使它们的和为 target，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums   有序数组
     * @param target 两数之和
     * @return int[]
     * @author Alex
     * @date 26/07/2019 15:25
     */
    private static int[] twoSum1(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{i, j};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    /**
     * 在有序数组中找出两个数，使它们的和为 target，并返回他们的数组下标。（官方题解）
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums   无序数组
     * @param target 两数之和
     * @return int[]
     * @author Alex
     * @date 26/07/2019 15:25
     */
    private static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int subtract = target - nums[i];
            if (map.containsKey(subtract)) {
                return new int[]{map.get(subtract), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("没有这样的组合");
    }


    /**
     * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1 非空链表1
     * @param l2 非空链表2
     * @return ListNode
     * @author Alex
     * @date 26/07/2019 15:35
     */
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。(我的解法)
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s 给定一个字符串
     * @return int
     * @author Alex
     * @date 29/07/2019 16:55
     */
    public static int lengthOfLongestSubstring1(String s) {
        int result = 0;
        if (s == null || "".equals(s)) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        String v = s;
        for (int i = 0; i < v.length(); i++) {
            StringBuilder sb = new StringBuilder(v.substring(i, i + 1));
            for (int j = i + 1; j < v.length(); j++) {
                char iChar = v.charAt(j);
                if (sb.indexOf(String.valueOf(iChar)) == -1) {
                    sb.append(iChar);
                } else {
                    result = (result > sb.length()) ? result : sb.length();
                    break;
                }
                if (j == v.length() - 1) {
                    result = (result > sb.length()) ? result : sb.length();
                }
            }
        }
        return result;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。(官方题解)
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s 给定一个字符串
     * @return int
     * @author Alex
     * @date 29/07/2019 16:55
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        // current index of character
        int[] index = new int[128];
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static int titleToNumber(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int result = 0;
        char[] sChars = s.toCharArray();
        int n = sChars.length;
        for (int i = 0; i < n; i++) {
            result += (sChars[i] - 'A' + 1) * Math.pow(26, n - i - 1);
        }
        return result;
    }


    /**
     * 32.最长有效括号
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * 示例 1:
     *
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     *
     * 示例 2:
     *
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     *
     * @param s 
     * @return int
     * @author Alex
     * @date 27/08/2019 14:59
     */
    public int longestValidParentheses(String s) {
        int maxValidLength = 0;


        return maxValidLength;
    }
}
