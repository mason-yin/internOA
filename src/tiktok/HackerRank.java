package tiktok;

import java.util.*;

public class HackerRank {
    public static void main(String[] args) {
        int[][] after = new int[2][2];
        after[0] = new int[]{1,2};
        after[1] = new int[]{3,4};
        List<Integer> consecutiveDifference = new ArrayList<>();
        consecutiveDifference.add(-2);
        consecutiveDifference.add(-1);
        consecutiveDifference.add(-2);
        consecutiveDifference.add(5);
        HackerRank hackerRank = new HackerRank();
        System.out.println(hackerRank.addNumbers(1.1f, 3.89f));
        System.out.println(hackerRank.addNumbers(2.3f, 1.9f));
        System.out.println(hackerRank.lastLetters("bat"));
        System.out.println(Arrays.deepToString(hackerRank.findBeforeMatrix(after)));
        System.out.println(hackerRank.countAnalogousArrays(consecutiveDifference, 3, 10));
        System.out.println(hackerRank.alaaddinTravel(new int[]{8,4,1,9,8,4,1,9}, new int[]{10,9,3,5,10,9,3}));
        System.out.println(hackerRank.countAnagramManipulation("ddcf", "cedk"));
        System.out.println(hackerRank.starNumber("|***|**|**"));
    }

    public int addNumbers(float f1, float f2) {
        return (int) Math.floor(f1 + f2);
    }

    public String lastLetters(String word) {
        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(word.length() - 1)).append(" ").append(word.charAt(word.length() - 2));
        return sb.toString();
    }

    public int[][] findBeforeMatrix(int[][] after) {
        int row = after.length;
        int col = after[0].length;
        int[][] before = new int[row][col];
        for (int x = 0; x < row ; x++) {
            for (int y = 0; y < col; y++) {
                int s = after[x][y];
                if (x == 0 && y == 0) {
                    before[x][y] = after[x][y];
                    continue;
                }
                for (int i = 0; i <= x; i++) {
                    for (int j = 0; j <= y; j++) {
                        s = s - before[i][j];
                    }
                }
                before[x][y] = s;
            }
        }
        return before;
    }

    public int countBinarySubstrings(String s) {
        if (s.equals("")) return 0;
        int ptr = 0;
        List<Integer> counts = new ArrayList<>();
        int count = 0;
        while (ptr < s.length()) {
            count = ptr;
            if (s.charAt(ptr) == '0') {
                while (ptr < s.length() && s.charAt(ptr) == '0') ptr += 1;
            } else {
                while (ptr < s.length() && s.charAt(ptr) == '1') ptr += 1;
            }
            count = ptr - count;
            counts.add(count);
        }
        //  System.out.println(counts);
        int res = 0;
        for (int i = 0; i < counts.size() - 1; i++) {
            if (counts.get(i) < counts.get(i + 1)) res += counts.get(i);
            else res += counts.get(i + 1);
        }
        return res;
    }

    public int countAnalogousArrays(List<Integer> consecutiveDifference, int lowerBound, int upperBound) {
        int res = 0;
//        for (int start = lowerBound; start <= upperBound; start++) {
//            boolean satisfied = true;
//            for (int i = 0; i < consecutiveDifference.size(); i++) {
//                start -= consecutiveDifference.get(i);
//                if (start > upperBound) {
//                    satisfied = false;
//                    break;
//                }
//            }
//            if (satisfied) res += 1;
//        }
//        return res;

        int count = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int i : consecutiveDifference){
            count += i;
            max = Math.max(max, count);
            min = Math.min(min, count);
        }
        return Math.max(0, (upperBound - lowerBound) - (max - min) + 1);
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        boolean[] isForbidden = new boolean[6001];
        for (int i : forbidden) isForbidden[i] = true;
        Queue<int[]> queue = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);
        queue.offer(new int[]{0, 0});
        boolean[] visited = new boolean[6001];
        while (!queue.isEmpty()) {
            int[] o = queue.poll();
            int curr = o[0], step = o[1];
            if (curr == x) return step;
            if (visited[curr]) continue;
            visited[curr] = true;
            // 前进一步
            int next = curr + a;
            if (next > 6000 || isForbidden[next]) continue;
            queue.offer(new int[]{next, step + 1});
            // 前进一步+后退一步，连走两步
            next -= b;
            if (next < 0 || isForbidden[next]) continue;
            queue.offer(new int[]{next, step + 2});
        }
        return -1;
    }

    public int alaaddinTravel(int[] magic, int[] dist) {
        for (int i = 0; i < magic.length / 2; i++) {
            int hasMagic = magic[i];
            boolean valid = true;
            for (int j = i; j < i + magic.length / 2; j++) {
                if (hasMagic < dist[j]) {
                    valid = false;
                    break;
                }
                hasMagic = hasMagic - dist[j] + magic[j + 1];
            }
            if (valid) return i;
        }
        return -1;
    }

    public int countAnagramManipulation(String s1, String s2) {
        int count = 0;

        // store the count of character
        int char_count[] = new int[26];

        // iterate though the first String and update
        // count
        for (int i = 0; i < s1.length(); i++)
            char_count[s1.charAt(i) - 'a']++;

        // iterate through the second string
        // update char_count.
        // if character is not found in char_count
        // then increase count
        for (int i = 0; i < s2.length(); i++)
        {
            char_count[s2.charAt(i) - 'a']--;
        }

        for(int i = 0; i < 26; ++i)
        {
            if(char_count[i] != 0)
            {
                count+= Math.abs(char_count[i]);
            }
        }

        return count / 2;
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < n; j++) {
                String word2 = words[j];
                if (isPredecessor(word1, word2)) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                    res = Math.max(dp[j], res);
                }
            }
        }
        return res + 1;
    }

    public boolean isPredecessor(String s1, String s2) {
        if (s2.length() - s1.length() != 1) return false;
        int ptr1 = 0;
        int ptr2 = 0;
        boolean extra = false;
        while (ptr1 < s1.length() && ptr2 < s2.length()) {
            if (s1.charAt(ptr1) != s2.charAt(ptr2)) {
                if (extra) return false;
                else {
                    ptr2 += 1;
                    extra = true;
                    if (s1.charAt(ptr1) != s2.charAt(ptr2)) return false;
                }
            }
            ptr1 += 1;
            ptr2 += 1;
        }
        return true;
    }

    public String GetSExpression(String s){
        boolean graph[][] = new boolean[26][26];
        HashSet<Character> nodes = new HashSet<>();
        //construct graph and check error E2: duplicate edges
        boolean E2 = false;
        for(int i=1;i<s.length();i+=6){
            int x = s.charAt(i)-'A', y = s.charAt(i+2)-'A';
            if(graph[x][y]) //duplicate edge
                E2 = true;
            graph[x][y] = true;
            nodes.add(s.charAt(i));
            nodes.add(s.charAt(i+2));
        }
        //check error E1: more than 2 children
        boolean E1 = false;
        for(int i=0;i<26;i++){
            int count = 0; //number of child
            for(int j=0;j<26;j++){
                if(graph[i][j])
                    count++;
            }
            if(count>2)
                return "E1";
        }
        if(E2) return "E2"; //return E2 after checking E1

        //check E3: cycle present and E4: multiple roots
        int numOfRoots = 0;
        char root =' ';
        for(char node : nodes){ //only check char that in the tree
            for(int i=0;i<26;i++){
                if(graph[i][node-'A'])
                    break;
                if(i==25){
                    numOfRoots++;
                    root = node;
                    boolean[] visited = new boolean[26];
                    if(IsCycle(node, graph, visited))
                        return "E3";
                }
            }
        }
        if(numOfRoots==0) return "E3"; //if no root, must be a cycle
        if(numOfRoots>1) return "E4"; //if more than one roots
        if(root==' ') return "E5"; //if no edge in input string, invalid input error
        return GetExpressionHelper(root, graph);

    }

    //true means there is a cycle, false means no cycle
    private boolean IsCycle(char node, boolean[][] graph, boolean[] visited){
        if(visited[node-'A']) //node has already been visited, must has a cycle
            return true;
        visited[node-'A'] = true;
        for(int i=0;i<26;i++){
            if(graph[node-'A'][i]){
                if(IsCycle((char)(i+'A'), graph, visited))
                    return true;
            }
        }
        return false;
    }

    //Recursive DFS to get the expression/construct the tree
    private String GetExpressionHelper(char root, boolean[][] graph){
        String left = "", right = ""; //if no children, left and right should be empty
        for(int i=0;i<26;i++){
            if(graph[root-'A'][i]){
                left = GetExpressionHelper((char)(i+'A'), graph);
                for(int j=i+1;j<26;j++){
                    if(graph[root-'A'][j]){
                        right = GetExpressionHelper((char)(j+'A') ,graph);
                        break;
                    }
                }
                break;
            }
        }
        return "("+root+left+right+")";
    }

    // Value[i][j] = Sum[i][j] - Sum[i-1][j] - Sum[i][j-1] + Sum[i-1][j-1]

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) res.add("FizzBuzz");
            else if (i % 3 == 0 && i % 5 != 0) res.add("Fizz");
            else if (i % 3 != 0 && i % 5 == 0) res.add("Buzz");
            else res.add(Integer.toString(i));
        }
        return res;
    }

    public boolean canJump(int[] nums) {
        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxJump < i) return false;
            if (maxJump >= i && i + nums[i] > maxJump) maxJump = i + nums[i];
        }
        return maxJump >= nums.length - 1;
    }

    public int jump(int[] nums) {
        int res = 0;
        int start = 0;
        int end = 1;
        while (end < nums.length) {
            int maxLength = Integer.MIN_VALUE;
            for (int i = start; i < end; i++) {
                maxLength = Math.max(maxLength, nums[i] + i);
            }
            start = end;
            end = maxLength + 1;
            res += 1;
        }
        return res;
    }

    public int starNumber(String star) {
        Deque<Character> stack = new LinkedList<>();
        int res = 0;
        for (char c : star.toCharArray()) {
            if (c == '|') {
                int count = 0;
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty()) {
                        char temp = stack.pop();
                        if (temp == '|') {
                            res += count;
                        }
                        else count += 1;
                    }
                }
                stack.push(c);
            } else stack.push(c);
        }
        return res;
    }
}
