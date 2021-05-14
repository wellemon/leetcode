/**
 * 贪心思想，总是先拿大的后拿小的
 */
public class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            String roman = romans[i];
            int value = values[i];
            while (num >= value) {
                sb.append(roman);
                num -= value;
            }
            if (num == 0) {
                break;
            }
        }
        return sb.toString();
    }
}
