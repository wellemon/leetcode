/**
 * 用 stack 存储数据，先进后出处理字符串
 */
public class Solution {

    public String reverseParentheses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(stringBuilder.toString());
                stringBuilder.setLength(0);
            } else if (ch == ')') {
                stringBuilder.reverse();
                stringBuilder.insert(0, stack.pop());
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

}
