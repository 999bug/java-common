package com.algo.arraystring;

/**
 * @Author: Lisy
 * @Date: 2024/03/11/14:43
 * @Description: https://leetcode.cn/problems/capitalize-the-title/solutions/1202029/jiang-biao-ti-shou-zi-mu-da-xie-by-leetc-lhn7/
 */
public class TitleDaxie {
    public static void main(String[] args) {
        String title = "Ri lOve leetcode";
        String s = capitalizeTitle(title);
        System.out.println("before: " + title);
        System.out.println("after: " + s + "\n");

        title = "First leTTeR of EACH Word";
        String s1 = capitalizeTitle(title);
        System.out.println("before: " + title);
        System.out.println("after: " + s1 + "\n");

        title = "capiTalIze tHe titLe";
        String s2 = capitalizeTitle(title);
        System.out.println("before: " + title);
        System.out.println("after: " + s2 + "\n");
    }

    /**
     * 单词长度小于2的，所有字母小写，超过2的首字母小写
     *
     * @param title
     * @return
     */
    public static String capitalizeTitle1(String title) {
        String[] words = title.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 2) {
                String header = word.substring(0, 1);
                String foot = word.substring(1);
                words[i] = header.toUpperCase() + foot.toLowerCase();
            } else {
                words[i] = word.toLowerCase();
            }
        }

        return String.join(" ", words);
    }

    /**
     * 单词长度小于2的，所有字母小写，超过2的首字母小写
     *
     * @param title
     * @return
     */
    public static String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 2) {
                char[] chars = word.toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                String header = word.substring(0, 1);
                String foot = word.substring(1);
                words[i] = header.toUpperCase() + foot.toLowerCase();
            } else {
                words[i] = word.toLowerCase();
            }
        }

        return String.join(" ", words);
    }
}
