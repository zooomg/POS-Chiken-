
public class Order {
	int num;		//�ֹ���ȣ
	int menuCNT;	//����
	String name;	//��ǰ�̸�
	int price;		//�ܰ�
	
	Order(int num, int menuCNT, String name, int price){
		this.num = num;
		this.menuCNT = menuCNT;
		this.name = name;
		this.price = price;
	}
	public int getMenuCNT() {
		return menuCNT;
	}
	public String getName() {
		return name;
	}
	public int getNum() {
		return num;
	}
	public int getPrice() {
		return price;
	}
	public void setMenuCNT(int menuCNT) {
		this.menuCNT = menuCNT;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return num+"\t\t"+name+"\t\t"+price+"\t\t"+menuCNT+"\t\t"+menuCNT*price;
	}
}