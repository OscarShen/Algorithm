package com.oscarshen09.hash.phoneChat;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneChat {
	public void solve() {
		Scanner s = new Scanner(System.in);
		Map<Long, Integer> map = new HashMap<>();
		int n = s.nextInt() * 2;

		for (int i = 0; i < n; i++) {
			Long num = s.nextLong();
			if (!map.containsKey(num)) {
				map.put(num, 1);
				continue;
			}
			int t = map.get(num);
			map.put(num, t + 1);
		}

		Long maxNum = 0L;
		int max = 0;
		for (Long num : map.keySet()) {
			if (map.get(num) > max) {
				maxNum = num;
				max = map.get(num);
			}
		}
		System.out.println(maxNum + " " + max);
		s.close();
	}
}
