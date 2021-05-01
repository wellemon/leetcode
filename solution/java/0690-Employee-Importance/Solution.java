/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/
class Solution {
    // Solution 1 DFS
     private Map<Integer, Employee> map = new HashMap<>();
     public int getImportanceDFS(List<Employee> employees, int id) {
         // put data of employee into map
         for (Employee emp : employees) {
             map.put(emp.id, emp);
         }
         return dfs(id);
     }

     private int dfs(int id) {
         Employee emp = map.get(id);
         // total is the sum of employee's importance
         int total = emp.importance;
         for (Integer subordinate : emp.subordinates) {
             total += dfs(subordinate);
         }
         return total;
     }

    // Solution 2 BFS
    public int getImportanceBFS(List<Employee> employees, int id) {
        // put data of employee into map
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee emp : employees) {
            map.put(emp.id, emp);
        }

        int total = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(id);
        while (!deque.isEmpty()) {
            int poll = deque.pollFirst();
            Employee emp = map.get(poll);
            total += emp.importance;
            for (int subordinate : emp.subordinates) {
                deque.addLast(subordinate);
            }
        }
        return total;
    }

}