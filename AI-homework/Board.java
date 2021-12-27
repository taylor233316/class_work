import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	/**
	 * 将牌状态
	 */
	public int[][] arr;

	/**
	 * 总耗散值
	 */
	public int fn=-1;

	/**
	 * 总耗散值 fn_new = g(n)+w(n)
	 */
	public int fn_new=-1;


	/**
	 * 耗散值f(n)=0.5*w(n)+0.5*p(n)
	 */
	public double fn_wn_pn = -1;


	/**
	 * 当前已走深度(耗散值)
	 */
	public int dn=0;
	
	/**
	 * p(n)路径之和
	 */
	public int pn=-1;

	/**
	 * 不在位将牌个数
	 */
	public int wn = -1;
	
	/**
	 * 父节点
	 */
	public Board parentBoard;
	
	/**
	 * 子节点列表
	 */
	public ArrayList<Board> childBoards=new ArrayList<Board>();
	
	public Board(int[][] arr) {
		super();
		this.arr = arr;
		this.dn = 0;
		this.pn = Utils.getDistanceCount(this, Init.ENDARR);
		this.wn = Utils.getWn(this,Init.ENDARR);
		this.fn = this.dn + this.pn;
		this.fn_new = this.dn + this.wn;
		this.fn_wn_pn = this.dn + 0.5 * this.wn + 0.5 * this.pn;
	}
	
	public Board() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				sb.append(arr[i][j] + "\t");
			}
			sb.append("\n");
		}
		sb.append("fn gn hn = " + fn + " " + dn + " " + pn+"\n");
		return sb.toString();
	}

	/**
	 * 复制本身arr数值
	 * @return
	 */
	public int[][] copySelfArr() {
		int[][] newArr = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
		for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		return newArr;
	}
	/**
	 * 判断两个board是否arr值相等
	 * @param board
	 * @return
	 */
	public boolean equals(Board board) {
		boolean isEqual = true;
		for (int i = 0; i < Init.SIZE && isEqual == true; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (arr[i][j] != board.arr[i][j]) {
					isEqual = false;
					break;
				}
			}
		}
		return isEqual;
	}
	
	/**
	 * 重载，判断board和arr是否值相等
	 * @param board
	 * @return
	 */
	public boolean equals(int[][] array) {
		boolean isEqual = true;
		for (int i = 0; i < Init.SIZE && isEqual == true; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (arr[i][j] != array[i][j]) {
					isEqual = false;
					break;
				}
			}
		}
		return isEqual;
	}
	
	/**
	 * 是否可以向下移动
	 * @return
	 */
	public boolean canDown() {
		boolean isOK = true;
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
//				判断不能向下扩展：（i,j)这个位置为0,i=3表示在最后一行，不能向下扩展
				if (arr[i][j] == 0 && i == Init.SIZE-1) {
					isOK = false;
					break label;
				}
			}
		}
		System.out.println("canDown:"+isOK);
		return isOK;
	}
	
	/**
	 * 是否可以向右移动
	 * @return
	 */
	public boolean canRight() {
		boolean isOK = true;
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (arr[i][j] == 0 && j == Init.SIZE-1) {
					isOK = false;
					break label;
				}
			}
		}
		System.out.println("canRight:"+isOK);
		return isOK;
	}
	/**
	 * 是否可以向左移动
	 * @return
	 */
	public boolean canLeft() {
		boolean isOK = true;
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (arr[i][j] == 0 && j == 0) {
					isOK = false;
					break label;
				}
			}
		}
		System.out.println("canLeft:"+isOK);
		return isOK;
	}
	
	/**
	 * 是否可以向上移动
	 * @return
	 */
	public boolean canUp() {
		boolean isOK = true;
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (arr[i][j] == 0 && i == 0) {
					isOK = false;
					break label;
				}
			}
		}
		System.out.println("canUp:" + isOK);
		return isOK;
	}
	
	
	/**
	 * 向下移动
	 * @return
	 */
	public Board goDown() {
		System.out.println("Go Down");
//		newBoard为copy的棋局状态
		Board newBoard = new Board(this.copySelfArr());
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
//				将空格与下面的数交换
				if (newBoard.arr[i][j] == 0) {
					int t;
					t = newBoard.arr[i][j];
					newBoard.arr[i][j] = newBoard.arr[i + 1][j];
					newBoard.arr[i + 1][j] = t;
					break label;// 这里label退出循环！！避免重复操作！！
				}
			}
		}
//		此时得到新扩展的棋局状态，d(n)+1,h(n)重新计算，得到f(n)
		newBoard.dn = this.dn + 1;  // 深度
		newBoard.pn = Utils.getDistanceCount(newBoard, Main.endBoard);// 不在位将牌个数
		newBoard.fn = newBoard.dn + newBoard.pn;
		newBoard.wn = Utils.getWn(newBoard,Main.endBoard);
		newBoard.fn_new = newBoard.dn + newBoard.wn;
		newBoard.fn_wn_pn = newBoard.dn + 0.5 * newBoard.wn + 0.5 * newBoard.pn;
		newBoard.parentBoard=this;
		this.childBoards.add(newBoard);
		return newBoard;
	}

	/**
	 * 向上移动
	 * @return
	 */
	public Board goUp() {
		System.out.println("Go Up");
		Board newBoard = new Board(this.copySelfArr());//这里不能这样复制
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (newBoard.arr[i][j] == 0) {
					int t;
					t = newBoard.arr[i][j];
					newBoard.arr[i][j] = newBoard.arr[i - 1][j];
					newBoard.arr[i - 1][j] = t;
					break label;
				}
			}
		}
		newBoard.dn = this.dn + 1;// 深度
		newBoard.pn = Utils.getDistanceCount(newBoard, Main.endBoard);// 不在位将牌个数
		newBoard.fn = newBoard.dn + newBoard.pn;
		newBoard.wn = Utils.getWn(newBoard,Main.endBoard);
		newBoard.fn_new = newBoard.dn + newBoard.wn;
		newBoard.fn_wn_pn = newBoard.dn + 0.5 * newBoard.wn + 0.5 * newBoard.pn;
		newBoard.parentBoard = this;
		this.childBoards.add(newBoard);
		return newBoard;
	}
	
	
	/**
	 * 向右移动
	 * @return
	 */
	public Board goRight() {
		System.out.println("Go Right");
		Board newBoard = new Board(this.copySelfArr());
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (newBoard.arr[i][j] == 0) {
					int t;
					t = newBoard.arr[i][j];
					newBoard.arr[i][j] = newBoard.arr[i][j+1];
					newBoard.arr[i][j+1] = t;
					break label;
				}
			}
		}
		newBoard.dn = this.dn + 1;// 深度
		newBoard.pn = Utils.getDistanceCount(newBoard, Main.endBoard);// 不在位将牌个数
		newBoard.fn = newBoard.dn + newBoard.pn;
		newBoard.wn = Utils.getWn(newBoard,Main.endBoard);
		newBoard.fn_new = newBoard.dn + newBoard.wn;
		newBoard.fn_wn_pn = newBoard.dn + 0.5 * newBoard.wn + 0.5 * newBoard.pn;
		newBoard.parentBoard=this;
		this.childBoards.add(newBoard);
		return newBoard;
	}
	
	/**
	 * 向左移动
	 * @return
	 */
	public Board goLeft() {
		System.out.println("Go Left");
		Board newBoard = new Board(this.copySelfArr());
		label: for (int i = 0; i < Init.SIZE; i++) {
			for (int j = 0; j < Init.SIZE; j++) {
				if (newBoard.arr[i][j] == 0) {
					int t;
					t = newBoard.arr[i][j];
					newBoard.arr[i][j] = newBoard.arr[i][j-1];
					newBoard.arr[i][j-1] = t;
					break label;
				}
			}
		}
		newBoard.dn = this.dn + 1;// 深度
		newBoard.pn = Utils.getDistanceCount(newBoard, Main.endBoard);// 不在位将牌个数
		newBoard.fn = newBoard.dn + newBoard.pn;
		newBoard.wn = Utils.getWn(newBoard,Main.endBoard);
		newBoard.fn_new = newBoard.dn + newBoard.wn;
		newBoard.fn_wn_pn = newBoard.dn + 0.5 * newBoard.wn + 0.5 * newBoard.pn;
		newBoard.parentBoard=this;
		this.childBoards.add(newBoard);
		return newBoard;
	}
}
