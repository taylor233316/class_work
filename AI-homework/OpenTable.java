import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Open表
 * @author Buuug
 *
 */
public class OpenTable {
	public ArrayList<Board> tbArr = new ArrayList<Board>();
	
	/**
	 * Open表排序
	 */
	public void sortItSelf() {
		Collections.sort(tbArr, new Comparator<Board>() {
			@Override
			public int compare(Board b1, Board b2) {// 重写 Comparator 函数
				if (b1.fn < b2.fn)//小的排前面
					return -1;
				else if (b1.fn > b2.fn)
					return 1;
				else
					return 0;
			}
		});
		System.out.println("Open表Board个数："+this.tbArr.size());
//		System.out.println("排序后：\n"+tbArr);
		System.out.println("最小fn="+tbArr.get(0));
		System.out.println("\n");
	}


	public void sortWnSelf() {
		Collections.sort(tbArr, new Comparator<Board>() {
			@Override
			public int compare(Board b1, Board b2) {// 重写 Comparator 函数
				if (b1.fn_new < b2.fn_new)//小的排前面
					return -1;
				else if (b1.fn_new > b2.fn_new)
					return 1;
				else
					return 0;
			}
		});
		System.out.println("********** h(n)=w(n) ************");
		System.out.println("Open表Board个数："+this.tbArr.size());
//		System.out.println("排序后：\n"+tbArr);
		System.out.println("最小fn_new="+tbArr.get(0));
		System.out.println("\n");
	}


	public void sortWnPnSelf() {
		Collections.sort(tbArr, new Comparator<Board>() {
			@Override
			public int compare(Board b1, Board b2) {// 重写 Comparator 函数
				if (b1.fn_wn_pn < b2.fn_wn_pn)//小的排前面
					return -1;
				else if (b1.fn_wn_pn > b2.fn_wn_pn)
					return 1;
				else
					return 0;
			}
		});
		System.out.println("********** h(n)=0.5 * w(n) + 0.5 * p(n) ************");
		System.out.println("Open表Board个数："+this.tbArr.size());
//		System.out.println("排序后：\n"+tbArr);
		System.out.println("最小fn_new="+tbArr.get(0));
		System.out.println("\n");
	}


	/**
	 * 返回fn最小Board
	 * @return
	 */
	public Board getMinBoard() {
		return tbArr.get(0);
	}
	
	/**
	 * 判断Table中是否包含某个board
	 * @param board
	 * @return
	 */
	public boolean hasBoard(Board board) {
		boolean hasBoard = false;
		for (int i = 0; i < tbArr.size(); i++) {
			if (tbArr.get(i).equals(board)) {
				hasBoard = true;
				break;
			}
		}
		return hasBoard;
	}
	
	/**
	 * 获取Board在OpenTable中的索引
	 * @param board
	 * @return 索引值
	 */
	public int getIndex(Board board) {
		int index=-1;
		for (int i = 0; i < tbArr.size(); i++) {
			if (tbArr.get(i).equals(board)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public Board getBoardByArr(int[][] array){
		for (int i = 0; i < tbArr.size(); i++) {
			if (tbArr.get(i).equals(array)) {
				return tbArr.get(i);
			}
		}
		try {
	        throw new Exception("Opentable:不存在该arr!");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("\n");
		sb.append("OpenTable:\n");
		for(int i=0;i<tbArr.size();i++) {
			sb.append(tbArr.get(i).toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
