/**
 * 使用 hashmap 保存字符出现的频次
 * 再通过优先队列对频次进行排序，频次相同时，按字母的字典顺序排序
 */
import java.util.*;
public class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        // 优先队列，小根堆
        // 按出现频次升序排序，如果出现频次相同的单词，需要对单词降序排序
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ?
                        b.getKey().compareTo(a.getKey()) :
                        a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }
}
