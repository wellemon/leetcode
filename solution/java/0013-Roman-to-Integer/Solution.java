/**
 * 判断每个 roman 字母时，检查后一个字母是否大于当前字母
 * 如果大于 ans 需要减去当前 roman 字母的值，反之则加
 */
public class Solution {
    Map<Character, Integer> map = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {

        char[] chars = s.toCharArray();
        int n = chars.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            int value = map.get(chars[i]);
            if (i < n - 1 && value < map.get(chars[i+1])) {
                res -= value;
            } else {
                res += value;
            }
        }
        return res;
    }
}
