import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Triangle {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {

			int numRows = scanner.nextInt();

			Triangle s = new Triangle();
			List<List<Integer>> triangle = s.generate(numRows);

			for (int i = 0; i < triangle.size(); i++) {
				List<Integer> rowData = triangle.get(i);
				for (int j = 0; j < rowData.size(); j++) {
					System.out.print(rowData.get(j) + " ");
				}
				System.out.println();
			}
		}
	}

	public List<List<Integer>> generate(int numRows) {

		List<List<Integer>> triangle = new ArrayList<List<Integer>>();

		for (int row = 0; row < numRows; row++) {

			List<Integer> rowData = new ArrayList<Integer>(row + 1);

			if (row == 0) {
				rowData.add(1);
			} else {
				rowData.add(0, 1);

				for (int index = 1; index < row; index++) {
					List<Integer> previousRow = triangle.get(row - 1);
					int sum = previousRow.get(index - 1)
							+ previousRow.get(index);
					rowData.add(sum);
				}

				rowData.add(1);
			}

			triangle.add(rowData);
		}

		return triangle;
	}
}
