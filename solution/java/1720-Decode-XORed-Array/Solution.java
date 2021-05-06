/**
 * 位运算：异或
 * 已知  encode[i] = decode[i] XOR decode[i-1]                                    (1)
 * 已知条件 encode[0:n]、 decode[0]，求 decode[1:n+1]
 *
 * 异或运算的三条重要性质：
 *      性质一：异或运算满足交换率和结合率
 *      性质二：相同数值异或，结果为 0
 *      性质三：任意数值和 0 异或，结果为数值本身
 *
 * 将 (1) 式两边同时 XOR decode[i-1]：
 *      encode[i] XOR decode[i-1] = decode[i] XOR decode[i-1] XOR decode[i-1]   (2)
 * 根据「性质一」和「性质二」可右式化简：
 *      encode[i] XOR decode[i-1] = 0 XOR decode[i]                             (3)
 * 根据「性质三」可得：
 *      encode[i] XOR decode[i-1] = decode[i]                                   (4)
 * 根据上式与已知条件，decode[i-1] 即为 first，即可递推出后续结果 decode
 */
public class Solution {
    public int[] decode(int[] encoded, int first) {
        int size = encoded.length;
        int[] decode = new int[size+1];
        decode[0] = first;
        for (int i=1; i<=size; i++){
            decode[i] = decode[i-1] ^ encoded[i-1];
        }
        return decode;
    }
}