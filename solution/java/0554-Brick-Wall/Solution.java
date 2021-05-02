/**
 * 思路：
 * 先遍历求取固定宽度的切线正好相切时砖块数量 map:width2Count
 * 求出其中相切数量的最大值 maxCount
 * 用砖块行数减去其中最大值即为 leastBricks
 */
public class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        // map:width2Count means draw a vertical line at the width form left to righ, the number of tangent brick
        Map<Integer, Integer> width2Count = new HashMap<>();

        // count map:width2Count
        for (List<Integer> oneRow : wall) {
            int widthSum = 0;
            for (int brickWidth : oneRow) {
                widthSum += brickWidth;
                width2Count.put(widthSum, width2Count.getOrDefault(widthSum, 0) + 1);
            }
            // the vertical line can't be two vetical edges of the wall, so remove the last widthSum
            width2Count.remove(widthSum);
        }

        // calculate the maxCount of the values in map:width2Count
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : width2Count.entrySet()) {
            maxCount = Math.max(entry.getValue(), maxCount);
        }
        // java new feature stream to calculate maxCount
        // Integer maxCount = width2Count.values().stream().reduce(Integer::max).orElse(0);

        // wall.size() means The number of rows of the wall
        // subtract maxCount of tangent bricks, so get minimum number of crossed bricks
        return wall.size() - maxCount;
    }
}
