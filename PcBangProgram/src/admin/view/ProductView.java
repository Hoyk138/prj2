package admin.view;



import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.controller.ProductEvt;

@SuppressWarnings("serial")
public class ProductView extends JPanel{
	
	private JButton jbtProductAdd;
	private DefaultTableModel dtmFood,dtmSnack,dtmDrink;
	private JTabbedPane jtp;
	private JTable jtFood,jtSnack,jtDrink;
	public ProductView() {		
		
		String[] columnNames= {"상품코드","상품명","이미지","설명","가격","입력일"};

		dtmFood=new DefaultTableModel(columnNames,3);
		dtmSnack=new DefaultTableModel(columnNames,3);
		dtmDrink=new DefaultTableModel(columnNames,3);
	
		//식사류 테이블
		jtFood=new JTable(dtmFood){
			//테이블 컬럼의 데이터형이 입력된 객체 그대로 조회되도록 부모클래스의 특정
			//method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				//입력된 행하나의 모든 컬럼의 값을 원래의 클래스로 반환하는 일
				//0행은 현재 입력된 행 하나만 대상으로 처리
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {   //클릭은 되고 편집은 안됨
				return false;
			}	
			
		};
		
		jtFood.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(2).setPreferredWidth(300);
		jtFood.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtFood.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		jtFood.setRowHeight(80);
		
		JScrollPane jspFood=new JScrollPane(jtFood);
		//스낵류 테이블
		jtSnack=new JTable(dtmSnack){
			//테이블 컬럼의 데이터형이 입력된 객체 그대로 조회되도록 부모클래스의 특정
			//method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				//입력된 행하나의 모든 컬럼의 값을 원래의 클래스로 반환하는 일
				//0행은 현재 입력된 행 하나만 대상으로 처리
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {   //클릭은 되고 편집은 안됨
				return false;
			}	
			
		};
		
		jtSnack.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(2).setPreferredWidth(300);
		jtSnack.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtSnack.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		jtSnack.setRowHeight(80);
		
		JScrollPane jspSnack=new JScrollPane(jtSnack);
		//음료류 테이블
		jtDrink=new JTable(dtmDrink){
			//테이블 컬럼의 데이터형이 입력된 객체 그대로 조회되도록 부모클래스의 특정
			//method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				//입력된 행하나의 모든 컬럼의 값을 원래의 클래스로 반환하는 일
				//0행은 현재 입력된 행 하나만 대상으로 처리
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {   //클릭은 되고 편집은 안됨
				return false;
			}	
			
		};
		
		jtDrink.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(2).setPreferredWidth(300);
		jtDrink.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtDrink.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		jtDrink.setRowHeight(80);
		
		JScrollPane jspDrink=new JScrollPane(jtDrink);
		//테이블3개 끝
		jbtProductAdd=new JButton("추가");	
				
		JPanel jpFood=new JPanel();
		jpFood.setLayout(null);
		jspFood.setBounds(0,50,900,500);
		jpFood.add(jspFood);
		
		JPanel jpSnack=new JPanel();
		jpSnack.setLayout(null);
		jspSnack.setBounds(0,50,900,500);
		jpSnack.add(jspSnack);
		
		JPanel jpDrink=new JPanel();
		jpDrink.setLayout(null);
		jspDrink.setBounds(0,50,900,500);
		jpDrink.add(jspDrink);
		

		
		//탭
		jtp=new JTabbedPane();
		
		jtp.add("식사",jspFood);
		jtp.add("스낵",jspSnack);
		jtp.add("음료",jspDrink);
		
		ProductEvt pe=new ProductEvt(this);
		jtp.addMouseListener(pe);
		jtDrink.addMouseListener(pe);
		jtSnack.addMouseListener(pe);
		jtFood.addMouseListener(pe);
		jbtProductAdd.addActionListener(pe);
		
		setLayout(new BorderLayout());
		
		JPanel jpButton=new JPanel();
		jpButton.add(jbtProductAdd);
		
		add("Center",jtp);
		add("South",jpButton);
		
		
		setVisible(true);
		
	
	}//기본생성자
	
	
	
	public JTable getJtFood() {
		return jtFood;
	}



	public JTable getJtSnack() {
		return jtSnack;
	}



	public JTable getJtDrink() {
		return jtDrink;
	}



	public JTabbedPane getJtp() {
		return jtp;
	}



	public JButton getJbtProductAdd() {
		return jbtProductAdd;
	}

	public DefaultTableModel getDtmFood() {
		return dtmFood;
	}



	public DefaultTableModel getDtmSnack() {
		return dtmSnack;
	}



	public DefaultTableModel getDtmDrink() {
		return dtmDrink;
	}

}//class