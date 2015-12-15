import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ball {

    public static void main(String args[]) {

	try (Scanner sc = new Scanner(System.in)) {

	    int[] input = { 1, 2, 3, 4, 5, 6, 7, 8 };

	    Map<String, int[]> cache = new HashMap<String, int[]>();

	    int n = sc.nextInt();
	    int k = sc.nextInt();

	    int[][] indexesToSwap = new int[n][2];

	    for (int i = 0; i < n; i++) {
		indexesToSwap[i][0] = sc.nextInt();
		indexesToSwap[i][1] = sc.nextInt();
	    }

	    int a = 0;

	    for (int i = 0; i < k; i++) {

		String key = Arrays.toString(input);

		if (cache.containsKey(key)) {
		    input = cache.get(key);

		    if (a < (k / 2)) {
			int x = (k % a);
			i = k - x;
			a = i;
		    }

		    continue;
		}

		for (int j = 0; j < n; j++) {

		    int temp = input[indexesToSwap[j][0] - 1];

		    input[indexesToSwap[j][0] - 1] = input[indexesToSwap[j][1] - 1];
		    input[indexesToSwap[j][1] - 1] = temp;
		}

		cache.put(key, input.clone());
		a++;
	    }

	    for (int i = 0; i < input.length; i++) {
		System.out.print(input[i] + " ");
	    }
	}
    }
}
