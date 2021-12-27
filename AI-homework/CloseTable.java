import java.util.ArrayList;

public class CloseTable {
	ArrayList<Board> tbArr = new ArrayList<Board>();
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
	 * 获取Board在CloseTable中的索引
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
	        throw new Exception("Closetable:不存在该arr!");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
}
