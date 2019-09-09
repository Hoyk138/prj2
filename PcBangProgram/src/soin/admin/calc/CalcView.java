package soin.admin.calc;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class CalcView extends JFrame {

	private JTable jtCalcPC, jtCalcItem ;
	private DefaultTableModel dtmCalcPC, dtmCalcItem;
	private JTabbedPane jtp ;
	private JButton jbtClosePC, jbtCalcPC, jbtCalcItem, jbtCloseItem;
	
	public CalcView() {

		super("주문관리");

		String[] pcColumnNames = { "번호", "PC번호", "구매자(ID)", "이용시간", "이용금액" };
		String[] itemColumnNames = { "번호", "PC번호", "구매자(ID)", "제품명", "개수", "결제금액" };

		dtmCalcPC = new DefaultTableModel(pcColumnNames, 5) ;
		dtmCalcItem = new DefaultTableModel(itemColumnNames, 5) ;
		
		JTable jtCalcPC = new JTable(dtmCalcPC);
		JTable jtCalcItem = new JTable(dtmCalcItem);

		jtCalcPC.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtCalcPC.getColumnModel().getColumn(1).setPreferredWidth(50);
		jtCalcPC.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtCalcPC.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtCalcPC.getColumnModel().getColumn(4).setPreferredWidth(150);

		jtCalcPC.setRowHeight(30);
		
		jbtClosePC = new JButton("종료") ;
		jbtCloseItem = new JButton("종료") ;
		jbtCalcPC = new JButton("정산") ;
		jbtCalcItem = new JButton("정산") ;
		
//		JPanel jpCalc = new JPanel() ;
//		jpCalc.setLayout(new BorderLayout()) ;
//		jpCalc.setBorder(new TitledBorder("PC사용료 정산")) ;
		
		JScrollPane jspCalcPC = new JScrollPane(jtCalcPC);
		JPanel jpCalcPC = new JPanel();
		JPanel jpCalcPCSouth = new JPanel();
		
		jpCalcPC.setLayout(new BorderLayout()) ;
		jpCalcPC.setBorder(new TitledBorder("PC사용료 정산")) ;
		
//		jpCalcPC.add(jspCalcPC);
		jpCalcPCSouth.add(jbtClosePC) ;
		jpCalcPCSouth.add(jbtCalcPC) ;
		jpCalcPC.add("Center", jspCalcPC) ;
		jpCalcPC.add("South", jpCalcPCSouth) ;
		
//		jpCalcPC.setLayout(null);
		jspCalcPC.setBounds(0, 0, 700, 600);
		
		//////////////////// 매점 정산 ////////////////////
		jtCalcItem.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtCalcItem.getColumnModel().getColumn(1).setPreferredWidth(50);
		jtCalcItem.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtCalcItem.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtCalcItem.getColumnModel().getColumn(4).setPreferredWidth(30);
		jtCalcItem.getColumnModel().getColumn(5).setPreferredWidth(150);
		
		jtCalcItem.setRowHeight(30);
		
		JScrollPane jspCalcItem = new JScrollPane(jtCalcItem) ;
		JPanel jpCalcItem = new JPanel() ;
		JPanel jpCalcItemSouth = new JPanel() ;
		
		jpCalcItem.setLayout(new BorderLayout()) ;
		jpCalcItem.setBorder(new TitledBorder("매점 정산"));
		
		jpCalcItemSouth.add(jbtCloseItem) ;
		jpCalcItemSouth.add(jbtCalcItem) ;
		jpCalcItem.add("Center", jspCalcItem) ;
		jpCalcItem.add("South", jpCalcItemSouth) ;
		
		
		jspCalcItem.setBounds(0, 0, 700, 600);

		add(jpCalcPC);
		add(jpCalcItem);

		jtp = new JTabbedPane() ;
		jtp.addTab("PC", jpCalcPC);
		jtp.addTab("매점", jpCalcItem);
		
		add("Center", jtp) ;
		
		setBounds(400, 200, 719, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// 기본생성자

	public JTable getJtCalcPC() {
		return jtCalcPC;
	}

	public JTable getJtCalcItem() {
		return jtCalcItem;
	}

	public DefaultTableModel getDtmCalcPC() {
		return dtmCalcPC;
	}

	public DefaultTableModel getDtmCalcItem() {
		return dtmCalcItem;
	}

	public JTabbedPane getJtp() {
		return jtp;
	}

	public JButton getJbtClosePC() {
		return jbtClosePC;
	}

	public JButton getJbtCalcPC() {
		return jbtCalcPC;
	}

	public JButton getJbtCalcItem() {
		return jbtCalcItem;
	}

	public JButton getJbtCloseItem() {
		return jbtCloseItem;
	}
	
	
	public static void main(String[] args) {
		new CalcView();
	}// main

} // class
