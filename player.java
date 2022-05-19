
public class player {
	private int numOfLives;
	private int x_pos;
	private int y_pos;
	public player(int n, int x, int y) {
		numOfLives = n;
		x_pos = x;
		y_pos = y;
	}
	public void setNumLives(int numOfLives) {
		this.numOfLives = numOfLives;
	}
	public void set_x_pos(int x_pos) {
		this.x_pos = x_pos;
	}
	public void set_y_pos (int y_pos) {
		this.y_pos = y_pos;
	}
	public int getNumLives() {
		return numOfLives;
	}
	public int get_x_pos() {
		return x_pos;
	}
	public int get_y_pos() {
		return y_pos;
	}
	public void move(int m) {
		int maxX = Arena.xSize;
		int maxY = Arena.ySize;
		if (m == 0 && y_pos != 0) { //north
			y_pos--;
		}
		if (m == 1 && y_pos < maxY) { //south
			y_pos++;
		}
		if (m == 2 && x_pos < maxX) { //east
			x_pos++;
		}
		if (m == 3 && x_pos != 0) { //west
			x_pos--;
		} //have player interact with the heal
		
	}
	

	public static void main(String[] args) {

	}

}
