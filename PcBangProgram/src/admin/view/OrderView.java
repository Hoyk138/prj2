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
	private JTable jtOrder;
	private JPopupMenu jpm;
	private JMenuItem jmState;
	//private static OrderView s;
	
	public OrderView() {
		
		String[] columnNames= {"�ֹ��ڵ�","PC��ȣ","�ֹ�����","������(ID)","����","��ǰ�̸�","�� �ݾ�","�����ѽð�"};

		dtmOrder=new DefaultTableModel(columnNames,0){

			//�ڵ� ���� �� �� ���� �߻����� �ʵ��� �������̵�
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0: return String.class;
				case 1:	return Integer.class;
				case 2:	return String.class;
				case 3:	return String.class;
				case 4:	return Integer.class;
				case 5:	return String.class;
				case 6:	return Integer.class;
				case 7:	return String.class;
				default: return String.class;
				}//switch case
			}//getColumnClass
			
			@Override
			public boolean isCellEditable(int row, int column) {   //Ŭ���� �ǰ� ������ �ȵ�
				return false;
			}//isCellEditable	
			
		};
		
		jpm=new JPopupMenu();
		jmState=new JMenuItem("�����ϱ�");
		jpm.add(jmState);
		
	
		//�ֹ� ���̺�
		jtOrder=new JTable(dtmOrder);
		
		jtOrder.setComponentPopupMenu(jpm);
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(70);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(200);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(50);
		jtOrder.getColumnModel().getColumn(5).setPreferredWidth(130);
		jtOrder.getColumnModel().getColumn(6).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(7).setPreferredWidth(190);
		
		jtOrder.setRowHeight(60);
		
		////////////////////////////
		DefaultTableCellRenderer dtcrCenter=new DefaultTableCellRenderer(); //�������
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmOrder=jtOrder.getColumnModel();
		tcmOrder.getColumn(0).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(1).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(2).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(3).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(4).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(5).setCellRenderer(dtcrCenter);
		tcmOrder.getColumn(6).setCellRenderer(dtcrCenter);
		
		//�ڵ� ����
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
		
		setBorder(new TitledBorder("�ֹ�"));
		setLayout(new BorderLayout());
		add("Center", jspOrder);
		
		////////////////////////////
		jtOrder.getTableHeader().setReorderingAllowed(false); //���̺� �� �ٲ��� ����
		jtOrder.getTableHeader().setResizingAllowed(false); //�÷��� ������ �ٲ��� ����
		setBackground(Color.white); 
		////////////////////////////
		
		setVisible(true);
		
	
	}//�⺻������
	
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