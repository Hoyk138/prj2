package admin.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(220);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(5).setPreferredWidth(130);
		jtOrder.getColumnModel().getColumn(6).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(7).setPreferredWidth(190);
		
		jtOrder.setRowHeight(60);
		
		////////////////////////////
		DefaultTableCellRenderer dtcrCenter=new DefaultTableCellRenderer(); //가운데정렬
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmOrder=jtOrder.getColumnModel();
		tcmOrder.getColumn(0).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(1).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(2).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(3).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(4).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(5).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(6).setCellRenderer(dtcrCenter);
		
		jtOrder.setAutoCreateRowSorter(true); 
		TableRowSorter<TableModel> trsOrder = new TableRowSorter<TableModel>(jtOrder.getModel()); 
		jtOrder.setRowSorter(trsOrder); 
		////////////////////////////
		
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
		
		////////////////////////////
		jtOrder.getTableHeader().setReorderingAllowed(false); //테이블 열 바뀌지 않음
		jtOrder.getTableHeader().setResizingAllowed(false); //컬럼명 사이즈 바뀌지 않음
		setBackground(Color.white); 
		////////////////////////////
		
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