import java.util.Set;


public class Bill { //테이블번호, 결제금액, 현금||카드, 결제시간
	int num;
	int payment;
	boolean CashOrCard;
	String Time;
	public Bill(int num, int payment, boolean CashOrCard, String Time) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.payment = payment;
		this.CashOrCard = CashOrCard;
		this.Time = Time;
	}
	public int getNum() {
		return num;
	}
	public int getPayment() {
		return payment;
	}
	public String getTime() {
		return Time;
	}
	public boolean getCashOrCard(){
		return CashOrCard;
	}
	public void setCashOrCard(boolean cashOrCard) {
		CashOrCard = cashOrCard;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public void setTime(String time) {
		Time = time;
	}
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			String COC;
			if(CashOrCard)
				COC = "현금";
			else {
				COC = "카드";
			}
			return num+"\t\t"+payment+"\t\t"+COC+"\t\t"+Time;
		}
}
