package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
	private int row;
	private int col;
	private int[][] matrix;
	public Controller(int row, int col) {
		this.row = row;
		this.col = col;
		System.out.println(row + "," + col);
		createMatrix();
        showMatrix();
	}
    public void showMatrix() {
		for (int i = 0; i < row ; i++) {
			for (int j = 0; j < col ; j++) {
				System.out.printf("%3d", matrix[i][j]);
			}
			System.out.println();
		}
	}
    private void createMatrix() {
		matrix = new int[row][col];
		Random rand = new Random();    //tạo 1 số ngẫu nhiên
		int imgCount = 21;             //khai báo số lượng ảnh pokemon là 21
		int max = 4;                   //khai báo số lần xuất hiện tối đa mỗi pokemon
		int arr[] = new int[imgCount + 1];         //khởi tạo mảng với mỗi phần tử đại diện cho 1 pokemon, giá trị của mỗi phần tử ứng với pokemon trong mảng là số lần xh của chúng , khởi tạo giá trị mặc định cho mỗi phần tử đều là 0
		ArrayList<Point> listPoint = new ArrayList<Point>();  //tạo 1 arrayL mới
		for (int i = 0; i < row; i++) { 
			for (int j = 0; j < col; j++) {
				listPoint.add(new Point(i, j));      //mỗi 1 vị trí trong ma trận được thêm thành 1 điểm mới
			}
		}
		// tạo ra các cặp số ngẫu nhiên và gán chúng vào ma trận
		int i = 0;
               do {
	           int index = rand.nextInt(imgCount) + 1;    //đưa ra 1 số đại diện cho 1 pokemon bất kì
	           if (arr[index] < max) {      //so sánh giá trị trên mảng của pokemon (là số lần xuất hiện của pokemon) với số lần xuất hiện tối đa  
		       arr[index] += 2;             //nếu nhỏ hơn tức là chưa xuất hiện đủ 4 lần thì sẽ tăng thêm 2 vào số lần xuất hiện
		       for (int j = 0; j < 2; j++) //cho chạy vòng lặp 2 lần để khởi tạo ra 2 pokemon giống nhau lưu vào ma trận
		        {
                            int size = listPoint.size();  //tạo 1 biến mới lưu dữ liệu về kích sỡ List
                            int pointIndex = rand.nextInt(size);  //tạo 1 biến mới lấy giá trị ngẫu nhiên trong khoảng 0->size-1	
                            matrix[listPoint.get(pointIndex).x][listPoint.get(pointIndex).y] = index; //lúc này sẽ lưu giá trị của điểm có tọa độ x,y trên ma trận là index (mục đích lưu hình ảnh đã chọn ở bên trên)
                            listPoint.remove(pointIndex); // loại bỏ điểm này khỏi listP để tránh lần lấy pointIndex ngẫu nhiên sau lấy trúng số lần trc đã lưu giá trị
                }
               i++;
                   }
                } while (i < row * col / 2); //vì i đại diện cho số cặp poke trùng nhau nên i chạy tới đó 
	}
    public PointLine checkTwoPoint(Point p1, Point p2) {    //kiểm tra 2 điểm (poke) trên ma trận xem có giống nhau 
        if (!p1.equals(p2) && matrix[p1.x][p1.y] == matrix[p2.x][p2.y]) {
            return new PointLine(p1, p2);
        }
        return null;
    }
     public int getRow() {
        return row;
    }

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
}