package user.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import user.controller.UserItemDetailEvt;
import user.controller.UserItemEvt;


/**
 * �԰Ÿ��ֹ� â view
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
	
	private JPopupMenu jpm;
	private JMenuItem jmCancel;
	
	private UserItemDetailEvt uide;
	
	public UserItem() {
		super("�԰Ÿ� �ֹ�");
		
		String[] columnNames= {"��ǰ��","�̹���","����"};

		dtmFood=new DefaultTableModel(columnNames,4);
		dtmSnack=new DefaultTableModel(columnNames,4);
		dtmDrink=new DefaultTableModel(columnNames,4);
	
		//��
		jtpOrder=new JTabbedPane();

		//�Ļ� ���̺�
		jtFood=new JTable(dtmFood) {
			
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jspFood=new JScrollPane(jtFood);
		
		//���� ���̺�
		jtSnack=new JTable(dtmSnack) {

			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		JScrollPane jspSnack=new JScrollPane(jtSnack);

		
		//���� ���̺�
		jtDrink=new JTable(dtmDrink) {
			
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
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
		
		//���ø���Ʈ
		dlmOrderChoiceList=new DefaultListModel<String>();
		jltOrderChoiceList=new JList<String>(dlmOrderChoiceList);
		JScrollPane jspOrderChoiceList=new JScrollPane(jltOrderChoiceList);
		
		//popmenu
		jpm=new JPopupMenu();
		jmCancel=new JMenuItem("���");
		jpm.add(jmCancel);
		
		//���ø���Ʈ�� �˾��޴� �ֱ�
		jltOrderChoiceList.setComponentPopupMenu(jpm);
		
		jlOrderChoiceList=new JLabel("���ø��");
		jlTotalPrice=new JLabel("�ѱݾ�");
		jtfTotalPrice=new JTextField("0");
		jbtOrder=new JButton("�ֹ�");
		
		jtfTotalPrice.setEditable(false);
		
		//�� �߰�
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
		
		//�̺�Ʈ ó��
		UserItemEvt uie=new UserItemEvt(this,uide);
		jtFood.addMouseListener(uie);
		jtSnack.addMouseListener(uie);
		jtDrink.addMouseListener(uie);
		jbtOrder.addActionListener(uie);
		jtpOrder.addMouseListener(uie);
		jmCancel.addActionListener(uie);
		
		setResizable(false);
		setBounds(700,150,550,700);
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//UserItem
	
	
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
	

	public JTabbedPane getJtpOrder() {
		return jtpOrder;
	}
	
	public JPopupMenu getJpm() {
		return jpm;
	}


	public JMenuItem getJmCancel() {
		return jmCancel;
	}


	/**
	 * ���� �׽�Ʈ��
	 * @param args
	 */
	public static void main(String[] args) {
		new UserItem();
	}//main

}//class
