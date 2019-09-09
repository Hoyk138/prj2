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
 * 사용자의 상품 주문 창 View
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
		super("먹거리주문");
		
		String[] columnNames= {"상품명","이미지","가격"};

		dtmFood=new DefaultTableModel(columnNames,4);
		dtmSnack=new DefaultTableModel(columnNames,4);
		dtmDrink=new DefaultTableModel(columnNames,4);
	
		//탭
		jtpOrder=new JTabbedPane();

		//식사류 테이블
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
		
		//스낵 테이블
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
		
		//음료 테이블
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
		
		//식사 
		jtFood.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtFood.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		jtFood.setRowHeight(130);
		
		//스낵
		jtSnack.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtSnack.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		jtSnack.setRowHeight(130);
		
		//음료
		jtDrink.getColumnModel().getColumn(0).setPreferredWidth(80);
		jtDrink.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		jtDrink.setRowHeight(130);
	
		//////////////////////////////////////////////////////////////////////////////////////////
		
		dlmOrderChoiceList=new DefaultListModel<String>();
		dlmOrderChoiceList.addElement("사이다");
	
		jltOrderChoiceList=new JList<String>(dlmOrderChoiceList);
		JScrollPane jspOrderChoiceList=new JScrollPane(jltOrderChoiceList);
		
		jlOrderChoiceList=new JLabel("선택목록");
		jlTotalPrice=new JLabel("총금액");
		jtfTotalPrice=new JTextField("10000원");
		jbtOrder=new JButton("주문");
		
		jtfTotalPrice.setEditable(false);
		
		//탭에 3개 스크롤 추가
		jtpOrder.add("식사",jspFood);
		jtpOrder.add("스낵",jspSnack);
		jtpOrder.add("음료",jspDrink);
		
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
		
		//이벤트
		UserItemEvt uoe=new UserItemEvt(this);
		jtFood.addMouseListener(uoe);
		jtSnack.addMouseListener(uoe);
		jtDrink.addMouseListener(uoe);
		jbtOrder.addActionListener(uoe);
		
		setResizable(false);
		setBounds(700,150,550,700);
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//기본생성자
	
	
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
	 * 단위테스트용
	 * @param args
	 */
	public static void main(String[] args) {
		new UserItem();
	}//main

}//class
