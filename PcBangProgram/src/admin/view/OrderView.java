package admin.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class OrderView extends JPanel{
	
	private DefaultTableModel dtmOrder;
	
	public OrderView() {
		
		String[] columnNames= {"�ֹ���ȣ","PC��ȣ","�ֹ�����","������(ID)","����","��ǰ�̸�","�� �ݾ�","����"};

		dtmOrder=new DefaultTableModel(columnNames,3);
		
	
		//�Ļ�� ���̺�
		JTable jtOrder=new JTable(dtmOrder);
		
		jtOrder.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtOrder.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtOrder.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtOrder.getColumnModel().getColumn(4).setPreferredWidth(130);
		jtOrder.getColumnModel().getColumn(5).setPreferredWidth(130);
		jtOrder.getColumnModel().getColumn(6).setPreferredWidth(150);
		jtOrder.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		jtOrder.setRowHeight(80);
		
		JScrollPane jspOrder=new JScrollPane(jtOrder);
		

		
		
		JPanel jpOrder=new JPanel();
		jpOrder.setLayout(null);
		jspOrder.setBounds(0,50,900,500);
		jpOrder.add(jspOrder);
		
		setBorder(new TitledBorder("�ֹ�"));
		setLayout(new BorderLayout());
		add("Center", jspOrder);
		
		
		setVisible(true);
		
	
	}//�⺻������
	
	
	public DefaultTableModel getDtmOrder() {
		return dtmOrder;
	}

}//class