package com.jhjeong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ1159 {

  public static void main(String[] args) throws IOException {
    Map<Character, Integer> map = new HashMap<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int g = nums[0];
    int s = nums[1];

    String W = br.readLine();
    String S = br.readLine();

    for(int i=0;i<W.length();i++) {
      char c = W.charAt(i);
      map.merge(c, 1, Integer::sum);
    }

    int left = 0;
    int right = 0;
    int rs = 0;

    while (true) {
      if (left == s || right == s) break;

      char cur = S.charAt(right);
      if (!map.containsKey(cur)) {
        while(left < right) {
          map.computeIfPresent(S.charAt(left), (character, integer) -> integer + 1);
          left++;
        }
        left++;
        right++;
      }
      else {
        if (map.get(cur) == 0) {
          while(S.charAt(left) != cur) {
            map.computeIfPresent(S.charAt(left), (character, integer) -> integer + 1);
            left++;
          }
          map.computeIfPresent(S.charAt(left), (character, integer) -> integer + 1);
          left++;
        }

        map.computeIfPresent(cur, (character, integer) -> integer - 1);
        if (right - left == g - 1) rs++;
        right++;
      }
    }

    System.out.println(rs);
  }
}
