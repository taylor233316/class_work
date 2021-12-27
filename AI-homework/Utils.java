public class Utils {

	/**
	 * 计算 p(n)路径之和
	 * @param
	 * @return  p(n)路径之和
	 */
	public static int getDistanceCount(Board curBoard, Board endBoard) {
		// System.out.println(curBoard);
		// System.out.println(endBoard);
		int count = 0;
		for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				label: for (int m = 0; m < Init.SIZE; m++) {
					for (int n = 0; n < Init.SIZE; n++) {
						if (curBoard.arr[i][j] == endBoard.arr[m][n]) {
							count += getDistance(i, j, m, n);
							break label;
						}
					}
				}
			}
		}
		return count;
	}

	/**
	 * 计算两坐标的矩形距离
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static int getDistance(int x1,int y1,int x2,int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
	
	/**
	 * 计算 p(n)路径之和 重载
	 * @param arr 15数码的一个状态
	 * @return  p(n)路径之和
	 */
	public static int getDistanceCount(Board curBoard, int[][] arr) {
		// System.out.println(curBoard);
		// System.out.println(endBoard);
		int count = 0;
		for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				label: for (int m = 0; m < Init.SIZE; m++) {
					for (int n = 0; n < Init.SIZE; n++) {
						if (curBoard.arr[i][j] == arr[m][n]) {
							count += getDistance(i, j, m, n);
							break label;
						}
					}
				}
			}
		}
		return count;
	}



	/**
	 * 计算 w(n)
	 * @param
	 * @return  w(n)
	 */
	public static int getWn(Board curBoard, Board endBoard) {
		int count = 0;
		for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (curBoard.arr[i][j] != endBoard.arr[i][j]) {
					count ++;
				}
			}
		}
		return count-1;
	}


	/**
	 * 计算w(n)的值，也就是不在位的将牌个数
	 * @param curBoard
	 * @param arr
	 * @return
	 */
	public static int getWn(Board curBoard, int[][] arr){
		int count = 0;
		for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
						if (curBoard.arr[i][j] != arr[i][j]) {
							count ++;
						}
					}
		}
		return count-1;
	}

}
