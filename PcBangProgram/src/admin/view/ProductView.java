package admin.view;



import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import admin.controller.ProductEvt;

@SuppressWarnings("serial")
public class ProductView extends JPanel{
	
	private JButton jbtProductAdd;
	private DefaultTableModel dtmFood,dtmSnack,dtmDrink;
	private JTabbedPane jtp;
	private JTable jtFood,jtSnack,jtDrink;
	public ProductView(MainView mv) {		
		
		String[] columnNames= {"��ǰ�ڵ�","��ǰ��","�̹���","����","����","�Է���"};

		dtmFood=new DefaultTableModel(columnNames,3);
		dtmSnack=new DefaultTableModel(columnNames,3);
		dtmDrink=new DefaultTableModel(columnNames,3);
	
		//�Ļ�� ���̺�
		jtFood=new JTable(dtmFood){
			//���̺� �÷��� ���������� �Էµ� ��ü �״�� ��ȸ�ǵ��� �θ�Ŭ������ Ư��
			//method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				//�Էµ� ���ϳ��� ��� �÷��� ���� ������ Ŭ������ ��ȯ�ϴ� ��
				//0���� ���� �Էµ� �� �ϳ��� ������� ó��
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {   //Ŭ���� �ǰ� ������ �ȵ�
				return false;
			}	
			
		};
		
		jtFood.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(2).setPreferredWidth(300);
		jtFood.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtFood.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		jtFood.setRowHeight(210);
		
		JScrollPane jspFood=new JScrollPane(jtFood);
		//������ ���̺�
		jtSnack=new JTable(dtmSnack){
			//���̺� �÷��� ���������� �Էµ� ��ü �״�� ��ȸ�ǵ��� �θ�Ŭ������ Ư��
			//method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				//�Էµ� ���ϳ��� ��� �÷��� ���� ������ Ŭ������ ��ȯ�ϴ� ��
				//0���� ���� �Էµ� �� �ϳ��� ������� ó��
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {   //Ŭ���� �ǰ� ������ �ȵ�
				return false;
			}	
			
		};
		
		jtSnack.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(2).setPreferredWidth(300);
		jtSnack.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtSnack.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		jtSnack.setRowHeight(210);
		
		JScrollPane jspSnack=new JScrollPane(jtSnack);
		//����� ���̺�
		jtDrink=new JTable(dtmDrink){
			//���̺� �÷��� ���������� �Էµ� ��ü �״�� ��ȸ�ǵ��� �θ�Ŭ������ Ư��
			//method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				//�Էµ� ���ϳ��� ��� �÷��� ���� ������ Ŭ������ ��ȯ�ϴ� ��
				//0���� ���� �Էµ� �� �ϳ��� ������� ó��
				return getValueAt(0, column).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int column) {   //Ŭ���� �ǰ� ������ �ȵ�
				return false;
			}	
			
		};
		
		jtDrink.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(2).setPreferredWidth(300);
		jtDrink.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtDrink.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		jtDrink.setRowHeight(210);
		
		////////////////////////////
		DefaultTableCellRenderer dtcrCenter=new DefaultTableCellRenderer(); //�������
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmFood=jtFood.getColumnModel();
		tcmFood.getColumn(0).setCellRenderer(dtcrCenter);
		tcmFood.getColumn(1).setCellRenderer(dtcrCenter);
		tcmFood.getColumn(3).setCellRenderer(dtcrCenter);
		tcmFood.getColumn(4).setCellRenderer(dtcrCenter);
		tcmFood.getColumn(5).setCellRenderer(dtcrCenter);
		TableColumnModel tcmSnack=jtSnack.getColumnModel();
		tcmSnack.getColumn(0).setCellRenderer(dtcrCenter);
		tcmSnack.getColumn(1).setCellRenderer(dtcrCenter);
		tcmSnack.getColumn(3).setCellRenderer(dtcrCenter);
		tcmSnack.getColumn(4).setCellRenderer(dtcrCenter);
		tcmSnack.getColumn(5).setCellRenderer(dtcrCenter);
		TableColumnModel tcmDrink=jtDrink.getColumnModel();
		tcmDrink.getColumn(0).setCellRenderer(dtcrCenter);
		tcmDrink.getColumn(1).setCellRenderer(dtcrCenter);
		tcmDrink.getColumn(3).setCellRenderer(dtcrCenter);
		tcmDrink.getColumn(4).setCellRenderer(dtcrCenter);
		tcmDrink.getColumn(5).setCellRenderer(dtcrCenter);
		////////////////////////////
		
		JScrollPane jspDrink=new JScrollPane(jtDrink);
		//���̺�3�� ��
		jbtProductAdd=new JButton("�߰�");	
				
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
		

		
		//��
		jtp=new JTabbedPane();
		
		jtp.add("�Ļ�",jspFood);
		jtp.add("����",jspSnack);
		jtp.add("����",jspDrink);
		
		ProductEvt pe=new ProductEvt(this, mv);
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
		
		////////////////////////////
		jtFood.getTableHeader().setReorderingAllowed(false); //���̺� �� �ٲ��� ����
		jtFood.getTableHeader().setResizingAllowed(false); //�÷��� ������ �ٲ��� ����
		jtSnack.getTableHeader().setReorderingAllowed(false);
		jtSnack.getTableHeader().setResizingAllowed(false); 
		jtDrink.getTableHeader().setReorderingAllowed(false); 
		jtDrink.getTableHeader().setResizingAllowed(false); 
		
		jtFood.setAutoCreateRowSorter(true); 
		TableRowSorter<TableModel> trsFood = new TableRowSorter<TableModel>(jtFood.getModel()); 
		jtFood.setRowSorter(trsFood); 
		jtSnack.setAutoCreateRowSorter(true); 
		TableRowSorter<TableModel> trsSnack = new TableRowSorter<TableModel>(jtSnack.getModel()); 
		jtSnack.setRowSorter(trsSnack); 
		jtDrink.setAutoCreateRowSorter(true); 
		TableRowSorter<TableModel> trsDrink = new TableRowSorter<TableModel>(jtDrink.getModel()); 
		jtDrink.setRowSorter(trsDrink); 
		
		
		jtp.setBackground(Color.white);
		jpButton.setBackground(new Color(0x3C5A91));
		jbtProductAdd.setBackground(new Color(0xF5D08A));
		setBackground(Color.white);
		////////////////////////////
		
		
		setVisible(true);
		
	
	}//�⺻������
	
	
	
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