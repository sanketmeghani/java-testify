package dev.sanket;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Solution {
	public static void main(String[] args) {

		Map<String, Integer> subStringCount = new HashMap<String, Integer>();

		try (Scanner sc = new Scanner(System.in)) {

			int n = sc.nextInt();
			int k = sc.nextInt();
			int l = sc.nextInt();
			int m = sc.nextInt();

			String input = sc.next();

			for (int i = 0; i <= n - k; i++) {
				for (int j = k; j <= l && (i + j) <= n; j++) {

					String substring = input.substring(i, i + j);
					int uniqCharCount = uniqCharCount(substring);

					if (uniqCharCount < m) {
						updateSubstringCount(subStringCount, substring);
					}
				}
			}

			System.out.println(mostOftenSubstringLength(subStringCount));
		}
	}

	private static void updateSubstringCount(
			Map<String, Integer> subStringCount, String substring) {
		Integer count = Integer.valueOf(1);

		if (subStringCount.containsKey(substring)) {
			count = subStringCount.get(substring);
			count = Integer.valueOf(count) + 1;
		}

		subStringCount.put(substring, count);
	}

	private static int mostOftenSubstringLength(
			Map<String, Integer> subStringCount) {
		int maxCount = 0;

		for (Entry<String, Integer> entry : subStringCount.entrySet()) {
			if (entry.getValue().intValue() > maxCount) {
				maxCount = entry.getValue().intValue();
			}
		}
		return maxCount;
	}

	private static int uniqCharCount(String input) {
		boolean uniqChars[] = new boolean[26];

		for (char ch : input.toCharArray()) {
			uniqChars[ch - 97] = true;
		}

		int count = 0;

		for (boolean uniqChar : uniqChars) {
			if (uniqChar) {
				count++;
			}
		}

		return count;
	}
}
