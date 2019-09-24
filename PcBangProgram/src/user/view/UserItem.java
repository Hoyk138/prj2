package user.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import user.controller.UserItemDetailEvt;
import user.controller.UserItemEvt;


/**
 * �԰Ÿ��ֹ� â view
 * @author sist37
 */
public class UserItem extends JFrame{
	
	private DefaultTableModel dtmFood,dtmSnack,dtmDrink;
	private DefaultListModel<String> dlmOrderChoiceList,dlmSearchList;
	private DefaultComboBoxModel<String> dcbOrderBy;
	private JComboBox<String> jcbOrderBy;
	private JTable jtFood, jtSnack, jtDrink;
	private JList<String> jltOrderChoiceList,jlSearchList;
	private JTextField jtfTotalPrice,jtfSearch;
	private JButton jbtOrder, jbtSearch, jbtSearchDetail;
	private JTabbedPane jtpOrder;
	
	private JPopupMenu jpm;
	private JMenuItem jmCancel;
	
	private UserItemDetailEvt uide;
	
	private UserMain um;
	
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
		//�˻�����Ʈ
		dlmSearchList=new DefaultListModel<String>();
		jlSearchList=new JList<String>(dlmSearchList);
		JScrollPane jspSearchList=new JScrollPane(jlSearchList);
		
		//comboBox
		dcbOrderBy=new DefaultComboBoxModel<String>();
		dcbOrderBy.addElement("�ֽż�");
		dcbOrderBy.addElement("�α��");
		dcbOrderBy.addElement("���ݼ�");
		jcbOrderBy=new JComboBox<String>(dcbOrderBy);
		
		//popmenu
		jpm=new JPopupMenu();
		jmCancel=new JMenuItem("���");
		jpm.add(jmCancel);
		
		//���ø���Ʈ�� �˾��޴� �ֱ�
		jltOrderChoiceList.setComponentPopupMenu(jpm);
		
		jtfTotalPrice=new JTextField("0");
		jtfSearch=new JTextField();
		jbtOrder=new JButton("�ֹ�");
		jbtSearch=new JButton("�˻�");
		jbtSearchDetail=new JButton("�󼼺���");
		
		jtfTotalPrice.setEditable(false);
		
		//�� �߰�
		jtpOrder.add("�Ļ�",jspFood);
		jtpOrder.add("����",jspSnack);
		jtpOrder.add("����",jspDrink);
		
		//Panel
		JPanel jpSearch=new JPanel();
		JPanel jpItem=new JPanel();
		JPanel jpChoiceOrder=new JPanel();
		
		jpSearch.add(jtfSearch);
		jpSearch.add(jbtSearch);
		jpSearch.add(jspSearchList);
		jpSearch.add(jbtSearchDetail);
		
		jpItem.add(jcbOrderBy);
		jpItem.add(jtpOrder);
		
		jpChoiceOrder.add(jspOrderChoiceList);
		jpChoiceOrder.add(jtfTotalPrice);
		jpChoiceOrder.add(jbtOrder);
		
		jpSearch.setBorder(new TitledBorder("��ǰ�˻�"));
		jpItem.setBorder(new TitledBorder(""));
		jpChoiceOrder.setBorder(new TitledBorder(""));
		jspOrderChoiceList.setBorder(new TitledBorder("���ø��"));
		jtfTotalPrice.setBorder(new TitledBorder("�� �ݾ�"));
		jspSearchList.setBorder(new TitledBorder(""));
		
		jpSearch.setLayout(null);
		jtfSearch.setBounds(20, 30, 150, 30);
		jbtSearch.setBounds(185, 30, 65, 30);
		jspSearchList.setBounds(20, 70, 230, 130);
		jbtSearchDetail.setBounds(20, 210, 230, 40);
		
		jpItem.setLayout(null);
		jtpOrder.setBounds(10, 50, 670, 600);
		jcbOrderBy.setBounds(550, 20, 100, 30);
		
		jpChoiceOrder.setLayout(null);
		jspOrderChoiceList.setBounds(10, 20, 250, 150);
		jtfTotalPrice.setBounds(50, 190, 200, 90);
		jbtOrder.setBounds(50, 300, 200, 80);
		
		setLayout(null);
		jpSearch.setBounds(750, 45, 270, 260);
		jpItem.setBounds(50, 50, 690, 680);
		jpChoiceOrder.setBounds(750, 330, 270, 400);
		
		add(jpSearch);
		add(jpItem);
		add(jpChoiceOrder);
		
		//�̺�Ʈ ó��
		UserItemEvt uie=new UserItemEvt(this,uide);
		jtFood.addMouseListener(uie);
		jtSnack.addMouseListener(uie);
		jtDrink.addMouseListener(uie);
		jtpOrder.addMouseListener(uie);
		jmCancel.addActionListener(uie);
		jbtOrder.addActionListener(uie);
		jbtSearch.addActionListener(uie);
		jbtSearchDetail.addActionListener(uie);
		jcbOrderBy.addActionListener(uie);
		jtfSearch.addActionListener(uie);
		
		jtFood.getTableHeader().setReorderingAllowed(false); //���̺� �� �ٲ��� ����
		jtFood.getTableHeader().setResizingAllowed(false); //�÷��� ������ �ٲ��� ����
		jtSnack.getTableHeader().setReorderingAllowed(false);
		jtSnack.getTableHeader().setResizingAllowed(false); 
		jtDrink.getTableHeader().setReorderingAllowed(false); 
		jtDrink.getTableHeader().setResizingAllowed(false); 
		
		setResizable(false);
		setBounds(700,150,1050,830);
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
	
	public JButton getJbtSearch() {
		return jbtSearch;
	}
	
	public JComboBox<String> getJcbOrderBy() {
		return jcbOrderBy;
	}

	public JButton getJbtSearchDetail() {
		return jbtSearchDetail;
	}
	
	public JList<String> getJlSearchList() {
		return jlSearchList;
	}

	public DefaultListModel<String> getDlmSearchList() {
		return dlmSearchList;
	}

	public JTextField getJtfSearch() {
		return jtfSearch;
	}


	/**
	 * ���� �׽�Ʈ��
	 * @param args
	 */
	public static void main(String[] args) {
		new UserItem();
	}//main

}//class
