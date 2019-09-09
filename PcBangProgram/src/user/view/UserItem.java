package user.view;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import prj2.user.evt.UserItemEvt;


/**
 * ������� ��ǰ �ֹ� â View
 * @author sist37
 */
public class UserItem extends JFrame{
	
	private DefaultTableModel dtmFood,dtmSnack,dtmDrink;
	private DefaultListModel<String> dlmOrderChoiceList;
	private JTable jtFood, jtSnack, jtDrink;
	private JList<String> jltOrderChoiceList;
	private JTextField jtfTotalPrice;
	private JLabel jlTotalPrice, jlOrderChoiceList;
	private JButton jbtOrder;
	private JTabbedPane jtpOrder;
	
	public UserItem() {
		super("�԰Ÿ��ֹ�");
		
		String[] columnNames= {"��ǰ��","�̹���","����"};

		dtmFood=new DefaultTableModel(columnNames,4);
		dtmSnack=new DefaultTableModel(columnNames,4);
		dtmDrink=new DefaultTableModel(columnNames,4);
	
		//��
		jtpOrder=new JTabbedPane();

		//�Ļ�� ���̺�
		jtFood=new JTable(dtmFood) {
			
//			@Override
//			public Class<?> getColumnClass(int column) {
//				return getValueAt(0, column).getClass();
//			}
//			
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
		};
		JScrollPane jspFood=new JScrollPane(jtFood);
		
		//���� ���̺�
		jtSnack=new JTable(dtmSnack) {

//			@Override
//			public Class<?> getColumnClass(int column) {
//				return getValueAt(0, column).getClass();
//			}
//			
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
			
		};
		JScrollPane jspSnack=new JScrollPane(jtSnack);
		
		//���� ���̺�
		jtDrink=new JTable(dtmDrink) {
			
//			@Override
//			public Class<?> getColumnClass(int column) {
//				return getValueAt(0, column).getClass();
//			}
//			
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
		};
		JScrollPane jspDrink=new JScrollPane(jtDrink);
		
		//�Ļ� 
		jtFood.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtFood.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		jtFood.setRowHeight(130);
		
		//����
		jtSnack.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtSnack.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		jtSnack.setRowHeight(130);
		
		//����
		jtDrink.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtDrink.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		jtDrink.setRowHeight(130);
	
		//////////////////////////////////////////////////////////////////////////////////////////
		
		dlmOrderChoiceList=new DefaultListModel<String>();
		dlmOrderChoiceList.addElement("���̴�");
	
		jltOrderChoiceList=new JList<String>(dlmOrderChoiceList);
		JScrollPane jspOrderChoiceList=new JScrollPane(jltOrderChoiceList);
		
		jlOrderChoiceList=new JLabel("���ø��");
		jlTotalPrice=new JLabel("�ѱݾ�");
		jtfTotalPrice=new JTextField("10000��");
		jbtOrder=new JButton("�ֹ�");
		
		jtfTotalPrice.setEditable(false);
		
		//�ǿ� 3�� ��ũ�� �߰�
		jtpOrder.add("�Ļ�",jspFood);
		jtpOrder.add("����",jspSnack);
		jtpOrder.add("����",jspDrink);
		
		setLayout(null);
		jtpOrder.setBounds(0, 0, 550, 520);
		jlOrderChoiceList.setBounds(20, 530, 100, 30);
		jlTotalPrice.setBounds(250, 530, 80, 30);
		jspOrderChoiceList.setBounds(20, 550, 200, 100);
		jtfTotalPrice.setBounds(250, 550, 120, 100);
		jbtOrder.setBounds(400,530,120,120);
		
		add(jtpOrder);
		add(jlOrderChoiceList);
		add(jlTotalPrice);
		add(jspOrderChoiceList);
		add(jtfTotalPrice);
		add(jbtOrder);
		
		//�̺�Ʈ
		UserItemEvt uoe=new UserItemEvt(this);
		jtFood.addMouseListener(uoe);
		jtSnack.addMouseListener(uoe);
		jtDrink.addMouseListener(uoe);
		jbtOrder.addActionListener(uoe);
		
		setResizable(false);
		setBounds(700,150,550,700);
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//�⺻������
	
	
	//getter
	public DefaultTableModel getDtmFood() {
		return dtmFood;
	}

	public DefaultTableModel getDtmSnack() {
		return dtmSnack;
	}

	public DefaultTableModel getDtmDrink() {
		return dtmDrink;
	}

	public DefaultListModel<String> getDlmOrderChoiceList() {
		return dlmOrderChoiceList;
	}

	public JTable getJtFood() {
		return jtFood;
	}

	public JTable getJtSnack() {
		return jtSnack;
	}

	public JTable getJtDrink() {
		return jtDrink;
	}

	public JList<String> getJltOrderChoiceList() {
		return jltOrderChoiceList;
	}

	public JTextField getJtfTotalPrice() {
		return jtfTotalPrice;
	}

	public JButton getJbtOrder() {
		return jbtOrder;
	}


	/**
	 * �����׽�Ʈ��
	 * @param args
	 */
	public static void main(String[] args) {
		new UserItem();
	}//main

}//class
