import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello");
        ArrayList<Board> tbArr = new ArrayList<Board>();
        System.out.println("tbArr为："+tbArr.size());

        System.out.println("输入二维数组的列数：");
        Scanner scan=new Scanner(System.in);
        int r=scan.nextInt();
//        int c=scan.nextInt();
        int[][]matrix=new int[r][r];
        scan.nextLine();//用来跳过行列后的回车符
        for(int i=0;i<r;i++){
            System.out.println("输入第"+i+"行的数据");
            for(int j=0;j<r;j++){
                matrix[i][j]=scan.nextInt();
            }
        }


        for(int i=0;i<r;i++){
            for(int j=0;j<r;j++)
                System.out.print(matrix[i][j]+",");
            }
            System.out.println("");
        }

    }

