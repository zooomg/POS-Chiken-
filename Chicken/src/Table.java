
public class Table {
	int x;
	int y;
	String tablename;
	public Table(int x, int y, String tablename) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.tablename = tablename;
	}
	public String getTablename() {
		return tablename;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return x+"\t\t"+y+"\t\t"+tablename;
	}
}
