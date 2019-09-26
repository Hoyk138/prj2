package user.view;

import java.awt.Color;
import java.awt.Font;

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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import user.controller.UserItemDetailEvt;
import user.controller.UserItemEvt;


/**
 * 먹거리주문 창 view
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
		
		DefaultTableCellRenderer dtcrCenter=new DefaultTableCellRenderer(); //가운데정렬
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmFood=jtFood.getColumnModel();
		tcmFood.getColumn(0).setCellRenderer(dtcrCenter);
		tcmFood.getColumn(2).setCellRenderer(dtcrCenter);
		TableColumnModel tcmSnack=jtSnack.getColumnModel();
		tcmSnack.getColumn(0).setCellRenderer(dtcrCenter);
		tcmSnack.getColumn(2).setCellRenderer(dtcrCenter);
		TableColumnModel tcmDrink=jtDrink.getColumnModel();
		tcmDrink.getColumn(0).setCellRenderer(dtcrCenter);
		tcmDrink.getColumn(2).setCellRenderer(dtcrCenter);
	
		//////////////////////////////////////////////////////////////////////////////////////////
		
		//선택리스트
		dlmOrderChoiceList=new DefaultListModel<String>();
		jltOrderChoiceList=new JList<String>(dlmOrderChoiceList);
		JScrollPane jspOrderChoiceList=new JScrollPane(jltOrderChoiceList);
		//검색리스트
		dlmSearchList=new DefaultListModel<String>();
		jlSearchList=new JList<String>(dlmSearchList);
		JScrollPane jspSearchList=new JScrollPane(jlSearchList);
		
		//comboBox
		dcbOrderBy=new DefaultComboBoxModel<String>();
		dcbOrderBy.addElement("최신순");
		dcbOrderBy.addElement("인기순");
		dcbOrderBy.addElement("가격순");
		jcbOrderBy=new JComboBox<String>(dcbOrderBy);
		
		//popmenu
		jpm=new JPopupMenu();
		jmCancel=new JMenuItem("취소");
		jpm.add(jmCancel);
		
		//선택리스트에 팝업메뉴 넣기
		jltOrderChoiceList.setComponentPopupMenu(jpm);
		
		JLabel jlLogo=new JLabel("▒ E_ZO PC ▒");
		JLabel search=new JLabel("▣ 상품 검색 ▣");
		JLabel order=new JLabel("▣ 선택목록 ▣");
		JLabel one=new JLabel("원");
		jtfTotalPrice=new JTextField("0");
		jtfSearch=new JTextField();
		jbtOrder=new JButton("주 문");
		jbtSearch=new JButton("검색");
		jbtSearchDetail=new JButton("상세보기");
		
		jtfTotalPrice.setEditable(false);
		
		jtfTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//탭 추가
		jtpOrder.add("식사",jspFood);
		jtpOrder.add("스낵",jspSnack);
		jtpOrder.add("음료",jspDrink);
		
		//Panel
		JPanel jpSearch=new JPanel();
		JPanel jpItem=new JPanel();
		JPanel jpChoiceOrder=new JPanel();
		JPanel total=new JPanel();
		
		jpSearch.add(search);
		jpSearch.add(jtfSearch);
		jpSearch.add(jbtSearch);
		jpSearch.add(jspSearchList);
		jpSearch.add(jbtSearchDetail);
		
		jpItem.add(jcbOrderBy);
		jpItem.add(jtpOrder);
		
		jpChoiceOrder.add(order);
		jpChoiceOrder.add(jspOrderChoiceList);
		jpChoiceOrder.add(total);
		jpChoiceOrder.add(jbtOrder);
		
		total.add(one);
		total.add(jtfTotalPrice);
		
		jpSearch.setBorder(new TitledBorder(new  LineBorder(Color.black,2)));
		jpItem.setBorder(new TitledBorder(new  LineBorder(Color.black,2)));
		jpChoiceOrder.setBorder(new TitledBorder(new  LineBorder(Color.black,2)));
		jspOrderChoiceList.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		total.setBorder(new TitledBorder(new  LineBorder(Color.black,1),"총 금액"));
		jspSearchList.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		
		jpSearch.setLayout(null);
		search.setBounds(20, 10, 150, 30);
		jtfSearch.setBounds(20, 40, 155, 30);
		jbtSearch.setBounds(185, 40, 65, 30);
		jspSearchList.setBounds(20, 80, 230, 130);
		jbtSearchDetail.setBounds(20, 220, 230, 40);
		
		jpItem.setLayout(null);
		jtpOrder.setBounds(25, 50, 670, 650);
		jcbOrderBy.setBounds(590, 20, 100, 30);
		
		jpChoiceOrder.setLayout(null);
		order.setBounds(10, 10, 200, 45);
		jspOrderChoiceList.setBounds(10, 40, 250, 150);
		total.setBounds(50, 200, 210, 90);
		jbtOrder.setBounds(10, 310, 250, 80);
		
		total.setLayout(null);
		jtfTotalPrice.setBounds(10,25,150,40);
		one.setBounds(180,25,50,40);
		
		setLayout(null);
		jlLogo.setBounds(20, 10, 300, 50);
		jpSearch.setBounds(750, 60, 270, 280);
		jpItem.setBounds(20, 60, 720, 720);
		jpChoiceOrder.setBounds(750, 360, 270, 420);
		
		add(jlLogo);
		add(jpSearch);
		add(jpItem);
		add(jpChoiceOrder);
		
		//이벤트 처리
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
		jlSearchList.addMouseListener(uie);
		
		jtFood.getTableHeader().setReorderingAllowed(false); //테이블 열 바뀌지 않음
		jtFood.getTableHeader().setResizingAllowed(false); //컬럼명 사이즈 바뀌지 않음
		jtSnack.getTableHeader().setReorderingAllowed(false);
		jtSnack.getTableHeader().setResizingAllowed(false); 
		jtDrink.getTableHeader().setReorderingAllowed(false); 
		jtDrink.getTableHeader().setResizingAllowed(false); 
		
		jtfTotalPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jpItem.setBackground(Color.white);
		jpSearch.setBackground(Color.white);
		jpChoiceOrder.setBackground(Color.white);
		jltOrderChoiceList.setBackground(Color.white);
		total.setBackground(Color.white);
		jtfTotalPrice.setBackground(Color.white);
		one.setBackground(Color.white);
		jtpOrder.setBackground(Color.white);
		jbtOrder.setBackground(new Color(0x6482B9));
		jbtSearch.setBackground(new Color(0x6482B9));
		jbtSearchDetail.setBackground(new Color(0x6482B9));
		jcbOrderBy.setBackground(new Color(0xE0EBFF));
		jlSearchList.setBackground(new Color(0xCCCCCC));
		jltOrderChoiceList.setBackground(new Color(0xCCCCCC));
		jltOrderChoiceList.setBackground(new Color(0xCCCCCC));
		//폰트
		jlLogo.setFont(new Font("serif",Font.BOLD, 25));
		jlLogo.setForeground(new Color(0xF1C40F));
		search.setFont(new Font("MonoSpaced", Font.BOLD, 15));
		order.setFont(new Font("MonoSpaced", Font.BOLD, 15));
		jtfTotalPrice.setForeground(Color.RED);	
		one.setForeground(Color.RED);	
		jtfTotalPrice.setFont(new Font("serif", Font.BOLD, 20));
		one.setFont(new Font("serif", Font.BOLD, 15));
		jbtOrder.setForeground(Color.white);	
		jbtOrder.setFont(new Font("MonoSpaced", Font.BOLD, 20));
		jbtSearch.setForeground(Color.white);	
		jbtSearchDetail.setForeground(Color.white);	
		jtFood.setFont(new Font("MonoSpaced", Font.PLAIN, 15));
		jtSnack.setFont(new Font("MonoSpaced", Font.PLAIN, 15));
		jtDrink.setFont(new Font("MonoSpaced", Font.PLAIN, 15));
		
		setResizable(false);
		setBounds(700,150,1050,850);
		setVisible(true);
		
		getContentPane().setBackground(new Color(0x434446));
		
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
	 * 단위 테스트용
	 * @param args
	 */
//	public static void main(String[] args) {
//		new UserItem();
//	}//main

}//class
