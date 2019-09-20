package admin.view;

import java.awt.BorderLayout;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import admin.controller.OrderEvt;

@SuppressWarnings("serial")
public class OrderView extends JPanel{
	
	private DefaultTableModel dtmOrder;
	private JPopupMenu jpm;
	private JMenuItem jmState;
	private JTable jtOrder;
	//private static OrderView s;
	
	public OrderView() {
		
		String[] columnNames= {"주문번호","PC번호","주문일자","구매자(ID)","개수","상품이름","총 금액","결제한시간"};

		dtmOrder=new DefaultTableModel(columnNames,3){

			@Override
			public boolean isCellEditable(int row, int column) {   //클릭은 되고 편집은 안됨
				return false;
			}	
			
		};
		
		jpm=new JPopupMenu();
		jmState=new JMenuItem("결제하기");
		jpm.add(jmState);
		
	
		//식사류 테이블
		jtOrder=new JTable(dtmOrder);
		
		jtOrder.setComponentPopupMenu(jpm);
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(230);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(5).setPreferredWidth(110);
		jtOrder.getColumnModel().getColumn(6).setPreferredWidth(60);
		jtOrder.getColumnModel().getColumn(7).setPreferredWidth(230);
		
		jtOrder.setRowHeight(60);
		
		JScrollPane jspOrder=new JScrollPane(jtOrder);
		

		OrderEvt oe=new OrderEvt(this);
		jmState.addActionListener(oe);
		
		
		JPanel jpOrder=new JPanel();
		jpOrder.setLayout(null);
		jspOrder.setBounds(0,50,900,500);
		jpOrder.add(jspOrder);
		
		setBorder(new TitledBorder("주문"));
		setLayout(new BorderLayout());
		add("Center", jspOrder);
		
		
		setVisible(true);
		
	
	}//기본생성자
	
//	public static OrderView getInstance() {
//		if(s == null) {
//			s=new OrderView();
//		}//end if
//		
//		return s;
//	}//getInstance
	
	
	public JMenuItem getJmState() {
		return jmState;
	}

	public JTable getJtOrder() {
		return jtOrder;
	}

	public DefaultTableModel getDtmOrder() {
		return dtmOrder;
	}

}//class