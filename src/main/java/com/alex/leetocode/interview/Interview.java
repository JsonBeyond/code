package com.alex.leetocode.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName Interview
 * @Description TODO
 * @Author Alex
 * @CreateDate 26/07/2019 17:10
 * @Version 1.0
 */
public class Interview {
    public static void main(String[] args) {
//        new Interview().arrayNesting(new int[]{5, 4, 0, 3, 1, 6, 2});
//        new Interview().combine6(4, 2);

        System.out.println(commonChars(new String[]{"cool", "lock", "cook"}));

    }

    public int arrayNesting(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int length = getLength(i, nums);
            maxLength = length > maxLength ? length : maxLength;
        }
        return maxLength;
    }

    private int getLength(int i, int[] nums) {
        int count = 0;
        Vector v = new Vector();
        do {
            v.add(i);
            v.remove(1);
            i = nums[i];
            count++;
        } while (i < nums.length && (v.indexOf(i) == -1));
        return count;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> reLists = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                List<Integer> rightList = new ArrayList<>();
                rightList.add(i);
                rightList.add(j);
                reLists.add(rightList);
            }
        }
        return reLists;
    }

    public List<List<Integer>> combine3(int n, int k) {
        List<List<Integer>> reLists = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int l = j + 1; l <= n; l++) {
                    List<Integer> rightList = new ArrayList<>();
                    rightList.add(i);
                    rightList.add(j);
                    rightList.add(l);
                    reLists.add(rightList);
                }
            }
        }
        return reLists;
    }

    static ArrayList<Integer> tmpArr = new ArrayList<>();

    public void combine4(int n, int k) {

        int[] com = new int[n];
        for (int i = 0; i < n; i++) {
            com[i] = i;
        }
        combine5(0, k, com);
        System.out.println();
    }

    public static void combine5(int index, int k, int[] arr) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmpArr.add(arr[i]);
                System.out.println(tmpArr.toString());
                tmpArr.remove((Object) arr[i]);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.length - k; i++) {
                tmpArr.add(arr[i]);
                combine5(i + 1, k - 1, arr);
                tmpArr.remove((Object) arr[i]);
            }
        } else {
            return;
        }
    }

    public void combine6(int n, int k) {
        combine7(1, k, n);
    }

    private static void combine7(int index, int k, int n) {
        if (k == 1) {
            for (int i = index; i <= n; i++) {
                tmpArr.add(i);
                System.out.println(tmpArr.toString());
                tmpArr.remove((Object) i);
            }
        } else if (k > 1) {
            for (int i = index; i <= n - k + 1; i++) {
                tmpArr.add(i);
                combine7(i + 1, k - 1, n);
                tmpArr.remove((Object) i);
            }
        }
    }

    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * <p>
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * <p>
     * 示例 2：
     * <p>
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     *
     * @param A 仅有小写字母组成的字符串数组 1 <= A.length <= 100 ; 1 <= A[i].length <= 100
     * @return java.util.List<java.lang.String>
     * @author Alex
     * @date 31/07/2019 09:42
     */
    public static List<String> commonChars(String[] A) {
        List<String> ans = new ArrayList<>();
        String s1 = A[0];
        int n1 = s1.length();
        char[] s1Chars = s1.toCharArray();
        for (int i = 0; i < n1; i++) {
            boolean isBreak = false;
            for (String s : A) {
                if (s.indexOf(s1Chars[i]) == -1) {
                    isBreak = true;
                    break;
                }
            }
            if (!isBreak) {
                ans.add(String.valueOf(s1Chars[i]));
            }
        }

        return ans;
    }
}
