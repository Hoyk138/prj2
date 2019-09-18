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
 * 먹거리주문 창 view
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
		super("먹거리 주문");
		
		String[] columnNames= {"상품명","이미지","가격"};

		dtmFood=new DefaultTableModel(columnNames,4);
		dtmSnack=new DefaultTableModel(columnNames,4);
		dtmDrink=new DefaultTableModel(columnNames,4);
	
		//탭
		jtpOrder=new JTabbedPane();

		//식사 테이블
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
		
		//스낵 테이블
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

		
		//음료 테이블
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
		
		//선택리스트
		dlmOrderChoiceList=new DefaultListModel<String>();
		jltOrderChoiceList=new JList<String>(dlmOrderChoiceList);
		JScrollPane jspOrderChoiceList=new JScrollPane(jltOrderChoiceList);
		
		//popmenu
		jpm=new JPopupMenu();
		jmCancel=new JMenuItem("취소");
		jpm.add(jmCancel);
		
		//선택리스트에 팝업메뉴 넣기
		jltOrderChoiceList.setComponentPopupMenu(jpm);
		
		jlOrderChoiceList=new JLabel("선택목록");
		jlTotalPrice=new JLabel("총금액");
		jtfTotalPrice=new JTextField("0");
		jbtOrder=new JButton("주문");
		
		jtfTotalPrice.setEditable(false);
		
		//탭 추가
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
		
		//이벤트 처리
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
	 * 단위 테스트용
	 * @param args
	 */
	public static void main(String[] args) {
		new UserItem();
	}//main

}//class
