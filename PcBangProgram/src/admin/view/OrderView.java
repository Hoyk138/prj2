package admin.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderView extends JFrame{
	
	private DefaultTableModel dtmOrder;
	
	public OrderView() {
		
		super("�ֹ�����");
		
		String[] columnNames= {"�ֹ���ȣ","PC��ȣ","�ֹ�����","������(ID)","����","��ǰ�̸�","�� �ݾ�","����"};

		dtmOrder=new DefaultTableModel(columnNames,3);
		
	
		//�Ļ�� ���̺�
		JTable jtOrder=new JTable(dtmOrder);
		
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(210);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(130);
		jtOrder.getColumnModel().getColumn(5).setPreferredWidth(120);
		jtOrder.getColumnModel().getColumn(6).setPreferredWidth(150);
		jtOrder.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		jtOrder.setRowHeight(80);
		
		JScrollPane jspOrder=new JScrollPane(jtOrder);
		

		
		
		JPanel jpOrder=new JPanel();
		jpOrder.setLayout(null);
		jspOrder.setBounds(0,50,1000,609);
		jpOrder.add(jspOrder);

		

		
		
		add(jpOrder);
		
		setBounds(120,120,1000,700);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//�⺻������
	
	
	public DefaultTableModel getDtmOrder() {
		return dtmOrder;
	}


	public static void main(String[] args) {
		new OrderView();
	}//main

}//class