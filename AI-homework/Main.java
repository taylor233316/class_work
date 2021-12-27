import java.util.Scanner;


public class Main {


	public static int[][] InputBoard() {
		System.out.println("输入二维数组的列数：");
		Scanner scan=new Scanner(System.in);
		int r=scan.nextInt();
//        int c=scan.nextInt();
		int[][]matrix=new int[r][r];
		scan.nextLine();//用来跳过行列后的回车符
		System.out.println("输入开始的棋局：");
		for(int i=0;i<r;i++){
			for(int j=0;j<r;j++){
				matrix[i][j]=scan.nextInt();
			}
		}
		return matrix;

	}

	public static int[][] attr = InputBoard();
	public static Board beginBoard = new Board(attr);
	public static Board endBoard = new Board(Init.ENDARR);


	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		System.out.println("*********  选择h(n)函数，0：h(n)=p(n), 1:h(n)=w(n),  2:h(n)=0.5*p(n)+0.5*w(n)  *************");
		int hnFlag= scan.nextInt();
		long start= System.nanoTime();

//		1.建立一开始的棋局,在open表中加入开始节点S，此时Open表为{S}
		OpenTable openTable = new OpenTable();
		openTable.tbArr.add(beginBoard);// 这里需要拷贝逻辑 否则复制的只是指针
		CloseTable closeTable = new CloseTable();

		System.out.println("************ 开始棋局 **********"+openTable);
//		2. 定义中间过程的棋局
		Board curBoard = null;

		int count = 0;
		int node_count = 0;


//		3. 当Open表不为空时，开始查找（Open为空，则问题无解，失败退出）
		while (openTable.tbArr.size() != 0) {

//			1 对节点进行扩展时，我们应该对open表中节点进行排序，选择open表中第一个节点进行扩展。
			System.out.println("第" + ++count + "次拓展");
			if(hnFlag == 0) {
				openTable.sortItSelf(); //排序
			} else if(hnFlag == 1){
				openTable.sortWnSelf(); //wn排序
			} else if (hnFlag == 2){
				openTable.sortWnPnSelf();
			} else{
				System.out.println("输入h(n)失败，退出");
				break;
			}

			curBoard = openTable.getMinBoard(); //选择第一个节点

//			2 判断是否为目标状态，是则成功退出
			// 是否为end
			if (curBoard.equals(endBoard)) {
				System.out.println("SUCCESS:" + curBoard);
//				输出十五数码问题的解路径
				while (curBoard.parentBoard != null) {
					System.out.println("上一步：");
					System.out.println(curBoard.parentBoard);
					curBoard = curBoard.parentBoard;
				}

				break;
			}


//			3 将这个节点加入到closed表中，表示这个节点已经扩展完毕，并且将这个节点从open表中移掉
			closeTable.tbArr.add(curBoard);
//			将S节点从open表中移掉
			openTable.tbArr.remove(openTable.getIndex(curBoard));

			// 4 方向拓展  注意：此时扩展完的节点还没有加入到open表
			for (int s = 0; s < 4; s++) {
				Board newBoard = null;
//				4.1 向下扩展，得到新的棋局
				if (s == 0) {
					if (curBoard.canDown()) {
//						新的棋局的f(n)已经计算好，并且
						newBoard = curBoard.goDown();
					} else {
						continue;
					}
				}

//				4.2 向上扩展
				else if (s == 1) {
					if (curBoard.canUp()) {
						newBoard = curBoard.goUp();
					} else {
						continue;
					}
				}

//				4.3 向右扩展
				else if (s == 2) {
					if (curBoard.canRight()) {
						newBoard = curBoard.goRight();
					} else {
						continue;
					}
				}

//				4.4 向左扩展
				else if (s == 3) {
					if (curBoard.canLeft()) {
						newBoard = curBoard.goLeft();
					} else {
						continue;
					}
				}


				/**
				 * 针对n数码问题，我们规定了不能走重复的路，也就是A向下得到B，但B不能向上得到A，
				 * 因此我们应该判断新生成的棋局是否存在于closed表中，如果存在说明又回去了，我们应该舍弃这种情况。
				 * 当不存在重复时，我们才将他加入到open表中。
				 */
//				5. 添加到open表中
				if (!closeTable.hasBoard(newBoard) && !openTable.hasBoard(newBoard)) {
					openTable.tbArr.add(newBoard);
					System.out.println("openTable:add");
				}
			}


		}

//		4. 评价算法
		/**
		 * （1）生成节点数
		 * （2）扩展节点数 count
		 */

		System.out.println("********** 启发算法评价 ***********");
		if(hnFlag == 0) {
			System.out.println("评价函数为：h(n)=p(n)");
		} else if(hnFlag == 1){
			System.out.println("评价函数为：h(n)=w(n)");
		} else {
			System.out.println("评价函数为：h(n)=0.5 * p(n)+ 0.5 * w(n)");
		}
		System.out.println("扩展节点数为："+closeTable.tbArr.size());
		int node_counts = closeTable.tbArr.size()+openTable.tbArr.size();
		System.out.println("生成节点数为："+node_counts);

		long end  = System.nanoTime();
		long time = end - start;
		double processTime = (double) time / 1_000_000_000;
		System.out.println("循环用时:"+processTime);
	}



}
