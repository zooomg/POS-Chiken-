

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Table_map extends JFrame {
	ArrayList<Table> tables = new ArrayList<Table>();

   String menu_name[] = {"순살치킨","후라이드","양념치킨","간장치킨","파닭","치즈"};
   int[] MenuCnt = new int[6];
   int total_money = 0;

   Order_list[] bcf = new Order_list[10000];	//버튼 누를때마다 새로 생기면 안되니까
   boolean[] check_table = new boolean[10000];
   
   
   boolean flag = false;

   JButton save;
   JButton edit;
   
   JButton la[] = new JButton[10000];

   JPanel contentPane = new JPanel();
   JPanel mainPanel = new JPanel();
   JPanel subPanel = new JPanel();

   JPanel buttons = new JPanel();
   
   JButton pay_list;
   
   int btncnt = 0;

   Table_map() {
	   BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("table.txt"));
			while(true) {
			    String line = br.readLine();
			    if (line==null) break;
			            
			    String[] temp = line.split("\t\t");

			    String Table_name = temp[2];
	            int Table_num = Integer.parseInt(temp[2].substring(2));
	            la[Table_num] = new JButton(Table_name);
	            la[Table_num].setSize(50, 50);
	            la[Table_num].addActionListener(new tableListner());
	            contentPane.add(la[Table_num]);
	            
	            la[Table_num].setLocation(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
	            btncnt = Table_num;
	            tables.add(new Table(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Table_name));
	   }
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			try{
				br.close();	
			}catch(IOException ioe){}
		}

      setTitle("치킨집 관리 프로그램");

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setContentPane(mainPanel);

      setLayout(null);
      contentPane.setLayout(null);

      contentPane.setSize(500, 500);
      contentPane.setLocation(0, 0);
      mainPanel.add(contentPane);
      
      contentPane.addMouseListener(new MyMouseListener());
      contentPane.setBackground(Color.BLACK);
      
      save = new JButton("테이블 위치 저장");
      save.setLocation(0, 400);
      save.setSize(100,50);
      
      edit = new JButton("테이블 위치 수정");
      edit.setLocation(100,400);
      edit.setSize(100, 50);
      
      pay_list = new JButton("결재내역");
      pay_list.setLocation(200, 400);
      pay_list.setSize(100, 50);
      
      subPanel.setLocation(0, 500);
      subPanel.setSize(500, 200);
      save.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub]
               flag = false;
         }
      });
      edit.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            flag = true;
         }
      });
      pay_list.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Bill_form Bf = new Bill_form();
		}
	});
      subPanel.add(save);
      subPanel.add(edit);
      subPanel.add(pay_list);
      mainPanel.add(subPanel);
      

      setSize(500, 570);
      
      addWindowListener(new WindowAdapter()
      {
          @Override
          public void windowClosing(WindowEvent e)
          {

				// TODO Auto-generated method stub
		        FileWriter fw = null;
		        try{
		        	fw = new FileWriter("table.txt");
					for(int i = 0;i < tables.size();i++){
						fw.write(tables.get(i).toString()+"\n");
					}
					    
		        }catch(Exception ex){}
		        finally{
			    	try {
						fw.close();
					} catch (IOException e1) {
					}
		        }
              e.getWindow().dispose();
          }
      });

      setVisible(true);

   }

   class MyMouseListener implements MouseListener {
      public void mousePressed(MouseEvent e) {
         //ActionEvent aEvent;
         if(flag){
        	 if(btncnt == 999){
        		 btncnt = 0;
        	 }
        	 
        	 
        	 
            btncnt++;
            la[btncnt] = new JButton("좌석" + btncnt);
            la[btncnt].setSize(50, 50);
            la[btncnt].addActionListener(new tableListner());
            contentPane.add(la[btncnt]);
            int x = e.getX();
            int y = e.getY();
            la[btncnt].setLocation(x, y);
            
            tables.add(new Table(x, y, "좌석"+btncnt));
         }
      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseClicked(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
   }
   public static void main(String[] args) {

      new Table_map();

   }
   class tableListner implements ActionListener{

	   @Override
       public void actionPerformed(ActionEvent e) {
          
          if(flag){
             la[Integer.parseInt(e.getActionCommand().substring(2))].setVisible(false);
             check_table[Integer.parseInt(e.getActionCommand().substring(2))-1] = false;
             for(int i = 0;i < tables.size();i++){
            	 if(tables.get(i).getTablename().equals(e.getActionCommand())){
            		 tables.remove(i);
            	 }
             }
          }
          else{
              int table_num = Integer.parseInt(e.getActionCommand().substring(2));
             if(check_table[table_num-1] == false){
            	 System.out.println("최초생성");
                 String menu_name[] = {"순살치킨","후라이드","양념치킨","간장치킨","파닭","치즈"};
                 int total_money = 0;
                 bcf[table_num-1] = new Order_list(table_num,total_money,menu_name);
                 check_table[table_num-1] = true;
             }
             bcf[table_num-1].setVisible(true);
          }
       } // actionPerformed
	   
   }

}