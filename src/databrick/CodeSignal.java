package databrick;

import java.util.*;

public class CodeSignal {
    public static void main(String[] args) {
        CodeSignal codeSignal = new CodeSignal();
        //codeSignal.findSpecialSum();
        //codeSignal.destroyHouses();
//        codeSignal.parseString("879", "369");
//        codeSignal.findPairs();
//        codeSignal.findDifference();
        codeSignal.recursiveSum();
    }

    public void findSpecialSum() {
        int[] array = {3,3,5,2,3};
        int i = 0;
        while (i < array.length && array[i] == 0) {
            i += 1;
        }
        System.out.println(i);
        int res = 0;
        while (i < array.length) {
            if (array[i] == 0) i += 1;
            else {
                int temp = array[i];
                array[i] = 0;
                int j = i + 1;
                while (j < array.length && array[j] >= temp) {
                    array[j] -= temp;
                    j += 1;
                }
                res += temp;
                i += 1;
            }
        }
        System.out.println(res);
    }

    public void destroyHouses() {
        int[] houses = {1, 2, 3};
        int[] queries = {3, 1, 2};
        List<Integer> res = new LinkedList<>();
        Arrays.sort(houses);
        int[] position = new int[houses[houses.length - 1] + 1];
        for (int house : houses) {
            position[house] = 1;
        }
        System.out.println(Arrays.toString(position));
        for (int i = 0; i < queries.length; i++) {
            position[queries[i]] = 0;
            int ptr = 0;
            int count = 0;
            while (ptr < position.length) {
                if (position[ptr] == 1) {
                    count += 1;
                    while (ptr < position.length && position[ptr] == 1) {
                        ptr += 1;
                    }
                } else {
                    ptr += 1;
                }
            }
            res.add(count);
        }
        System.out.println(res);
    }

    public void parseString(String s1, String s2) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            char a = s1.charAt(i);
            char b = s2.charAt(i);
            int sum = (a - '0') + (b - '0');
            res.append(Integer.toString(sum));
        }
        System.out.println(res);
    }

    public void findPairs() {
        int res = 0;
        int target = 1212;
        int[] nums = {1, 212, 12, 12, 121, 123};
        String targetString = Integer.toString(target);
        Map<String, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(Integer.toString(num), frequency.getOrDefault(Integer.toString(num), 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            String temp = Integer.toString(nums[i]);
            if (temp.length() >= targetString.length()) continue;
            int ptr = 0;
            boolean feasible = true;
            while (ptr < temp.length()) {
                if (temp.charAt(ptr) != targetString.charAt(ptr)) {
                    feasible = false;
                    break;
                } else {
                    ptr += 1;
                }
            }
            if (feasible) {
                String need = targetString.substring(ptr);
                if (frequency.containsKey(need)) {
                    if (need.equals(Integer.toString(nums[i]))) res += (frequency.get(need) - 1);
                    else res += frequency.get(need);
                }
            } else {
                continue;
            }
        }
        System.out.println(res);
    }

    public void findDifference() {
        int num = 1010;
        int sum = 0;
        int product = 1;
        String numString = Integer.toString(num);
        for (int i = 0; i < numString.length(); i++) {
            sum += numString.charAt(i) - '0';
            product *= numString.charAt(i) - '0';
        }
        System.out.println(product - sum);
    }

    public void recursiveSum() {
        String input = "11111122223";
        int k = 3;
        int len = input.length();
        while (input.length() > k) {
            int times = input.length() / k + 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < times; i++) {
                int sum = 0;
                for (int j = 0; j < k; j++) {
                    if (i * k + j < input.length()) sum += input.charAt(i * k + j) - '0';
                    else break;
                }
                sb.append(sum);
            }
            input = sb.toString();
        }
        System.out.println(input);
    }



}
