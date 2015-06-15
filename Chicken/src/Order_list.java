
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Order_list extends JFrame{
   String menu_name[] = {"����ġŲ","�Ķ��̵�","���ġŲ","����ġŲ","�Ĵ�","ġ��"};
   int menu_price[] = {12000,10000,15000,15000,13000,15000};
   HashMap<String, Integer> menu_ = new HashMap<String, Integer>();
   
   ArrayList<Order> orders = new ArrayList<Order>();
   
   ArrayList<Bill> Bills = new ArrayList<Bill>();
   
   int bill_cnt = 0;
   
   int pay_money = 0;
   
   int menu_cnt = 0;
   
   JLabel totalmoney;
   JTextField show_totalmoney;
   
   JButton orderButton;
   JButton payButton;
   
   JPanel mainJPanel = new JPanel(new BorderLayout());
   
   JPanel subPanel1 = new JPanel(new BorderLayout()); //�ֹ� ���
   JPanel subPanel2 = new JPanel(new GridLayout(3,5,5,5)); //���� ���̾�
   JPanel subPanel3 = new JPanel(new GridLayout(8,2,5,5)); //�޴���
   
   JPanel subPanel1_btns = new JPanel();
   JPanel subPanel1_show = new JPanel();
   
   JButton btn1 = new JButton("1");
   JButton btn2 = new JButton("2");
   JButton btn3 = new JButton("3");
   JButton btn4 = new JButton("4");
   JButton btn5 = new JButton("5");
   JButton btn6 = new JButton("6");
   JButton btn7 = new JButton("7");
   JButton btn8 = new JButton("8");
   JButton btn9 = new JButton("9");
   JButton btn0 = new JButton("0");
   JButton del = new JButton("����");
   JButton all = new JButton("���");
   JButton cash = new JButton("����");
   JButton card = new JButton("ī��");
   JButton pay = new JButton("����");
   JTextArea now = new JTextArea();

   JButton menu[] = new JButton[16];
   
   String columnNames[] =
      { "��ǰ��ȣ", "��ǰ�̸�", "��ǰ�ܰ�", "����","����"};

   //DefaultTableModel�� �����ϰ� ������ ���
   DefaultTableModel defaultTableModel;
   //= new DefaultTableModel(rowData, columnNames);
   
   JTable table;
   
   //JScrollPane�� JTable�� ���
   JScrollPane jScollPane;
   
   //ArrayList<Order> orders = new ArrayList<Order>();
   int table_num = 0; // �������ʴ� ��� �� �ִ°ž�
   int total_money = 0;
   String[] MenuName = new String[6];
   
   public Order_list(int table_num, int total_money, String[] MenuName) {
	      
		menu_.put("����ġŲ", 12000);
		menu_.put("�Ķ��̵�", 10000);
		menu_.put("���ġŲ", 15000);
		menu_.put("����ġŲ", 15000);
		menu_.put("�Ĵ�", 13000);
		menu_.put("ġ��", 15000);
		  
		this.table_num = table_num;
		this.total_money = total_money;
		this.MenuName = MenuName;
		
		defaultTableModel = new DefaultTableModel(columnNames,0);
		table = new JTable(defaultTableModel);
		jScollPane = new JScrollPane(table);
		
		/*table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	            int column = table.getSelectedColumn();
	            int row = table.getSelectedRow();
	            orders.get(row).setMenuCNT(Integer.parseInt(table.getValueAt(row, column).toString()));
	            Object aValue = orders.get(row).getMenuCNT() * orders.get(row).getPrice();
	            System.out.println(column+" "+row);
	            table.setValueAt(aValue, row, column+1);
        	}
    	});*/
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        	public void valueChanged(ListSelectionEvent event) {
		        int rows[] = table.getSelectedRows();
		        int temp = 0;
				for(int i = rows.length - 1;i >= 0;i--){
					temp += orders.get(rows[i]).price;
				}
				now.setText(String.valueOf(temp));
			}
		});
		  
		  
		// TODO Auto-generated constructor stub
		setTitle("�ֹ���");
		  
		setContentPane(mainJPanel);
		  
		setSize(700, 650);
		  
		subPanel1.add(jScollPane,BorderLayout.CENTER);
		  
		totalmoney = new JLabel("�� ���� �ݾ�");
		show_totalmoney = new JTextField();
		show_totalmoney.setEditable(false);
		show_totalmoney.setPreferredSize(new Dimension(350, 20));
		subPanel1_show.add(totalmoney, BorderLayout.EAST);
		subPanel1_show.add(show_totalmoney, BorderLayout.WEST);
		subPanel1.add(subPanel1_show,BorderLayout.SOUTH);
		  
		subPanel2.add(btn1);
		btn1.addActionListener(new NumbtnListner());
		subPanel2.add(btn2);
		btn2.addActionListener(new NumbtnListner());
		subPanel2.add(btn3);
		btn3.addActionListener(new NumbtnListner());
		  
		subPanel2.add(del);
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rows[] = table.getSelectedRows();
				for(int i = rows.length - 1;i >= 0;i--){
					defaultTableModel.removeRow(rows[i]-i);
					
					Order_list.this.total_money -= orders.get(rows[i]).price;
					orders.remove(rows[i]);
				}
				show_totalmoney.setText(String.valueOf(Order_list.this.total_money));
			}
		});
		
		subPanel2.add(all);
		all.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.selectAll();
			}
		});
		  
		subPanel2.add(btn4);
		btn4.addActionListener(new NumbtnListner());
		subPanel2.add(btn5);
		btn5.addActionListener(new NumbtnListner());
		subPanel2.add(btn6);
		btn6.addActionListener(new NumbtnListner());
		  
		subPanel2.add(cash);
		cash.addActionListener(new PaybtnListner(true));
		subPanel2.add(card);
		card.addActionListener(new PaybtnListner(false));
		  
		subPanel2.add(btn7);
		btn7.addActionListener(new NumbtnListner());
		subPanel2.add(btn8);
		btn8.addActionListener(new NumbtnListner());
		subPanel2.add(btn9);
		btn9.addActionListener(new NumbtnListner());
		
		subPanel2.add(btn0);
		btn0.addActionListener(new NumbtnListner());
		subPanel2.add(now);
		  
		subPanel3.setLocation(300, 0);
		for(int i = 0;i < 6;i++){
		   menu[i] = new JButton(menu_name[i]);
		   menu[i].addActionListener(new MenubtnListener());
		   subPanel3.add(menu[i]);
		}
		for(int i = 6;i < 15;i++){
		   menu[i] = new JButton();
		   subPanel3.add(menu[i]);
		}
		menu[15] = new JButton("���̺� ����");
		menu[15].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        FileWriter fw = null;
		        FileWriter fw1 = null;
		        try{
		        	long currentTime = System.currentTimeMillis();
					Date date = new Date(currentTime);
					SimpleDateFormat sdfNow = new SimpleDateFormat("[a hh�� mm�� ss��]");
					String strNow = sdfNow.format(date);
					
					fw = new FileWriter(strNow+"order.txt");
					fw.write( "��ǰ��ȣ"+"\t\t"+"��ǰ�̸�"+"\t\t"+"��ǰ�ܰ�"+"\t\t"+"����"+"\t\t"+"����"+"\n");
					for(int i = 0;i < orders.size();i++){
						fw.write(orders.get(i).toString()+"\n");
					}
					
					fw1= new FileWriter(strNow+"bill.txt");
					fw1.write("���̺��ȣ"+"\t\t"+"�����ݾ�"+"\t\t"+"ī��/����"+"\t\t"+"�����ð�"+"\n");
					for(int i = 0;i < Bills.size();i++){
						fw1.write(Bills.get(i).toString()+"\n");
					}
					    
		        }catch(Exception ex){
		        }finally{
		            try{
			            table.selectAll();
			            int rows[] = table.getSelectedRows();
						for(int i = rows.length - 1;i >= 0;i--){
							defaultTableModel.removeRow(rows[i]-i);
							
							orders.remove(rows[i]);
						}
			            menu_cnt = 0;
			            show_totalmoney.setText("0");
			            
			            fw.close();
			            fw1.close();
			            
		            }catch(IOException ioe){}
		        } // finally
			}
		});
		subPanel3.add(menu[15]);
		
		mainJPanel.add(subPanel1,BorderLayout.WEST);
		mainJPanel.add(subPanel2,BorderLayout.SOUTH);
		mainJPanel.add(subPanel3,BorderLayout.EAST);
		
   }
   class MenubtnListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
    	  
    	  int cnt=1;
    	  int temp_cost = menu_.get(e.getActionCommand());
    	  
    	  total_money += temp_cost;
    	 show_totalmoney.setText(String.valueOf(total_money));
    	 
     	 orders.add(new Order(++menu_cnt, cnt, e.getActionCommand(), temp_cost));
    	  
    	  for (int i = 0; i < orders.size(); i++){
    	    if(orders.get(i).num == menu_cnt){
    		   int menu_num = orders.get(i).getNum();
    		   int menu_cnt = orders.get(i).getMenuCNT();
    		   String menu_name = orders.get(i).getName();
    		   int cost = orders.get(i).getPrice();
    		   
    		   Object[] data = {menu_num, menu_name, cost, menu_cnt, cost*menu_cnt};
    		   defaultTableModel.addRow(data);
			 }
    		}
      }
   }
   class NumbtnListner implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		now.append(e.getActionCommand()); 
	}
	   
   }

   
   class PaybtnListner implements ActionListener{
	   boolean CashCard;
	   public PaybtnListner(boolean CashCard) {
		   // TODO Auto-generated constructor stub
		   this.CashCard = CashCard;
	   }
	   @Override
	   public void actionPerformed(ActionEvent e) { // ������ȣ, �����ݾ�, ����||ī��, �����ð� ���������� �Ѱ��ֱ�
			// TODO Auto-generated method stub
			int payment = Integer.parseInt(now.getText());
			Order_list.this.total_money -= payment;
			show_totalmoney.setText(String.valueOf(total_money));
			
			long currentTime = System.currentTimeMillis();
			Date date = new Date(currentTime);
			SimpleDateFormat sdfNow = new SimpleDateFormat("[a hh�� mm�� ss��]");
			String strNow = sdfNow.format(date);
		   
			Bills.add(new Bill(++bill_cnt, payment, CashCard, strNow));
	   }
	   
	   
   }
   public ArrayList<Bill> getBills() {
	return Bills;
   }
}