import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Bill_form extends JFrame {
	JPanel mainJPanel = new JPanel(new BorderLayout());
	String columnNames[] ={ "테이블번호", "결제금액", "카드/현금", "결제시간"};

	//DefaultTableModel을 선언하고 데이터 담기
	DefaultTableModel defaultTableModel;
	//= new DefaultTableModel(rowData, columnNames);
   
	JTable table;
   
	//JScrollPane에 JTable을 담기
	JScrollPane jScollPane;
	
	public Bill_form() {
		
		
		setTitle("결제서");
		setContentPane(mainJPanel);
		// TODO Auto-generated constructor stub
		defaultTableModel = new DefaultTableModel(columnNames,0){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table = new JTable(defaultTableModel);
		jScollPane = new JScrollPane(table);
		
		setSize(500, 500);
		mainJPanel.add(jScollPane,BorderLayout.CENTER);
		
		all_road();
		
		
		setVisible(true);
	}
	public void all_road() {
		BufferedReader br = null;
		String path = Bill_form.class.getResource("").getPath();
		path = path.substring(0, path.length()-4);
		File dirFile=new File(path);
		File []fileList=dirFile.listFiles();
		for(File tempFile : fileList) {
		  if(tempFile.isFile()) {
		    String tempPath=tempFile.getParent();
		    String tempFileName=tempFile.getName();
		    //System.out.println("Path="+tempPath);
		    //System.out.println("FileName="+tempFileName);
		    if(tempFileName.contains("bill.txt")){
				try{
					br = new BufferedReader(new FileReader(tempFileName));
					while(true) {
			            String line = br.readLine();
			            if (line==null) break;
			            
			            String[] temp = line.split("\t\t");
			            
			            
			            if(!temp[0].equals("테이블번호")){
			            	Object[] data = {Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),temp[2],temp[3]};
			            	
			            	defaultTableModel.addRow(data);
			            }
			            	
			            System.out.println(line);
			        }
				}catch(Exception ex){
					System.out.println(ex);
				}finally{
					try{
						br.close();	
					}catch(IOException ioe){}
				}
		    }
		  }
		}
		
	}
}