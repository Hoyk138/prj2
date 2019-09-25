package admin.view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import admin.controller.CalcItemEvt;
import admin.controller.CalcPCEvt;

@SuppressWarnings("serial")
public class CalcView extends JPanel {

	private JTable jtCalcPC, jtCalcItem ;
	private DefaultTableModel dtmCalcPC, dtmCalcItem;
	private JButton jbtCalcPC, jbtCalcItem, jbtnSearchPC, jbtnSearchItem, jbtnPCSaveFile, jbtnItemSaveFile ;
	private JTabbedPane jtp ;
	private CheckboxGroup cgPC, cgItem ;
	private JTextField jtfStartPC, jtfEndPC, jtfStartItem, jtfEndItem ;
	
	public CalcView() {

		String[] pcColumnNames = { "코드", "PC번호", "사용자(ID)", "이용시간(분)", "이용금액(원)" };
		String[] itemColumnNames = { "코드", "PC번호", "구매자(ID)", "제품명", "개수", "결제금액(원)" };

		dtmCalcPC = new DefaultTableModel(pcColumnNames, 0) ;
		dtmCalcItem = new DefaultTableModel(itemColumnNames, 0) ;
		
		JTable jtCalcPC = new JTable(dtmCalcPC) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		JTable jtCalcItem = new JTable(dtmCalcItem) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false ;
			}
			
		};

		jtCalcPC.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtCalcPC.getColumnModel().getColumn(1).setPreferredWidth(50);
		jtCalcPC.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtCalcPC.getColumnModel().getColumn(3).setPreferredWidth(200);
		jtCalcPC.getColumnModel().getColumn(4).setPreferredWidth(150);

		jtCalcPC.setRowHeight(30);
		
//		jbtClosePC = new JButton("종료") ;
//		jbtCloseItem = new JButton("종료") ;
		jbtCalcPC = new JButton("PC 정산") ;
		jbtCalcItem = new JButton("매점 정산") ;
		jbtnPCSaveFile = new JButton("텍스트 파일로 저장") ;
		jbtnItemSaveFile = new JButton("텍스트 파일로 저장") ;
		
		
		JScrollPane jspCalcPC = new JScrollPane(jtCalcPC);
		JPanel jpCalcPC = new JPanel();
		JPanel jpCalcPC2 = new JPanel();
		JPanel jpCalcPCSouth = new JPanel();
		
		jpCalcPC.setLayout(new BorderLayout()) ;
		jpCalcPC.setBorder(new TitledBorder("PC 사용료 정산")) ;
		
		JPanel jpCalcPCNorth = new JPanel() ;
		jpCalcPCNorth.setLayout(new BorderLayout()) ;
		jpCalcPCNorth.setBorder(new TitledBorder("날짜 옵션")) ;
		
		cgPC = new CheckboxGroup() ;
		Checkbox chbTodayPC = new Checkbox("오늘", cgPC, true);
		Checkbox chbWeekPC = new Checkbox("일주일", cgPC, false);
		Checkbox chbMonthPC = new Checkbox("한 달", cgPC, false);
		Checkbox chbCustomPC = new Checkbox("사용자 지정", cgPC, false);
		
		jtfStartPC = new JTextField(15) {
			@Override
			protected void paintComponent(java.awt.Graphics g) {
			    super.paintComponent(g);

			    if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
			        Graphics2D g2 = (Graphics2D)g.create();
			        g2.setBackground(Color.gray);
			        g2.setFont(getFont().deriveFont(Font.PLAIN));
			        g2.drawString("예)2019-01-01", 5, 15); //figure out x, y from font's FontMetrics and size of component.
			        g2.dispose();
			    }
			  }
		};
		JLabel jlblPC = new JLabel("~") ;
		jtfEndPC = new JTextField(15){
			@Override
			protected void paintComponent(java.awt.Graphics g) {
			    super.paintComponent(g);

			    if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
			        Graphics2D g2 = (Graphics2D)g.create();
			        g2.setBackground(Color.gray);
			        g2.setFont(getFont().deriveFont(Font.PLAIN));
			        g2.drawString("예)2019-12-31", 5, 15); //figure out x, y from font's FontMetrics and size of component.
			        g2.dispose();
			    }
			  }
		} ;
		jbtnSearchPC = new JButton("조회") ;
		
		jpCalcPC2.add(chbTodayPC) ;
		jpCalcPC2.add(chbWeekPC) ;
		jpCalcPC2.add(chbMonthPC) ;
		jpCalcPC2.add(chbCustomPC) ;
		jpCalcPC2.add(jtfStartPC) ;
		jpCalcPC2.add(jlblPC) ;
		jpCalcPC2.add(jtfEndPC) ;
		jpCalcPC2.add(jbtnSearchPC) ;
		
		
		jpCalcPCNorth.add(jpCalcPC2) ;
		jpCalcPCSouth.add(jbtCalcPC) ;
		jpCalcPCSouth.add(jbtnPCSaveFile) ;
		jpCalcPC.add("Center", jspCalcPC) ;
		jpCalcPC.add("South", jpCalcPCSouth) ;
		jpCalcPC.add("North",jpCalcPCNorth) ;
		
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
		JPanel jpCalcItem2 = new JPanel() ;
		JPanel jpCalcItemSouth = new JPanel() ;
		JPanel jpCalcItemNorth = new JPanel() ;
		
		jpCalcItem.setLayout(new BorderLayout()) ;
		jpCalcItem.setBorder(new TitledBorder("매점 정산"));
		
		jpCalcItemNorth.setLayout(new BorderLayout()) ;
		jpCalcItemNorth.setBorder(new TitledBorder("날짜 옵션")) ;
		
		cgItem = new CheckboxGroup() ;
		Checkbox chbTodayItem = new Checkbox("오늘", cgItem, true);
		Checkbox chbWeekItem = new Checkbox("일주일", cgItem, false);
		Checkbox chbMonthItem = new Checkbox("한 달", cgItem, false);
		Checkbox chbCustomItem = new Checkbox("사용자 지정", cgItem, false);
		
		jtfStartItem = new JTextField(15) {
			@Override
			protected void paintComponent(java.awt.Graphics g) {
			    super.paintComponent(g);

			    if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
			        Graphics2D g2 = (Graphics2D)g.create();
			        g2.setBackground(Color.gray);
			        g2.setFont(getFont().deriveFont(Font.PLAIN));
			        g2.drawString("예)2019-01-01", 5, 15); //figure out x, y from font's FontMetrics and size of component.
			        g2.dispose();
			    }
			  }
		} ;
		JLabel jlblItem = new JLabel("~") ;
		jtfEndItem = new JTextField(15) {
			@Override
			protected void paintComponent(java.awt.Graphics g) {
			    super.paintComponent(g);

			    if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
			        Graphics2D g2 = (Graphics2D)g.create();
			        g2.setBackground(Color.gray);
			        g2.setFont(getFont().deriveFont(Font.PLAIN));
			        g2.drawString("예)2019-12-31", 5, 15); //figure out x, y from font's FontMetrics and size of component.
			        g2.dispose();
			    }
			  }
		} ;
		jbtnSearchItem = new JButton("조회") ;
		
		jpCalcItem2.add(chbTodayItem) ;
		jpCalcItem2.add(chbWeekItem) ;
		jpCalcItem2.add(chbMonthItem) ;
		jpCalcItem2.add(chbCustomItem) ;
		jpCalcItem2.add(jtfStartItem) ;
		jpCalcItem2.add(jlblItem) ;
		jpCalcItem2.add(jtfEndItem) ;
		jpCalcItem2.add(jbtnSearchItem) ;
		
		jpCalcItemNorth.add(jpCalcItem2) ;
		
		
//		jpCalcItemSouth.add(jbtCloseItem) ;
		jpCalcItemSouth.add(jbtCalcItem) ;
		jpCalcItemSouth.add(jbtnItemSaveFile) ;
		jpCalcItem.add("Center", jspCalcItem) ;
		jpCalcItem.add("South", jpCalcItemSouth) ;
		jpCalcItem.add("North", jpCalcItemNorth) ;
		
		
		jspCalcItem.setBounds(0, 0, 700, 600);
		
		setLayout(new BorderLayout());

//		add(jpCalcPC);
//		add(jpCalcItem);

		jtp = new JTabbedPane() ;
		jtp.addTab("PC", jpCalcPC);
		jtp.addTab("매점", jpCalcItem);
		
		add("Center", jtp) ;
		
		// 이벤트
		CalcPCEvt cpe = new CalcPCEvt(this) ;
		jbtCalcPC.addActionListener(cpe);
		CalcItemEvt cie = new CalcItemEvt(this) ;
		jbtCalcItem.addActionListener(cie);
		jtp.addMouseListener(cpe);
		jtp.addMouseListener(cie);
		jbtnSearchPC.addActionListener(cpe);
		jbtnSearchItem.addActionListener(cie);
		jbtnPCSaveFile.addActionListener(cpe);
		jbtnItemSaveFile.addActionListener(cie);
		
//		setBounds(400, 200, 719, 600);
		setVisible(true);

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

	public JButton getJbtCalcPC() {
		return jbtCalcPC;
	}

	public JButton getJbtCalcItem() {
		return jbtCalcItem;
	}

	public JButton getJbtnSearchPC() {
		return jbtnSearchPC;
	}

	public JButton getJbtnSearchItem() {
		return jbtnSearchItem;
	}

	public JTabbedPane getJtp() {
		return jtp;
	}

	public CheckboxGroup getCgPC() {
		return cgPC;
	}

	public CheckboxGroup getCgItem() {
		return cgItem;
	}

	public JTextField getJtfStartPC() {
		return jtfStartPC;
	}

	public JTextField getJtfEndPC() {
		return jtfEndPC;
	}
	
	public JTextField getJtfStartItem() {
		return jtfStartItem;
	}
	
	public JTextField getJtfEndItem() {
		return jtfEndItem;
	}

	public JButton getJbtnPCSaveFile() {
		return jbtnPCSaveFile;
	}

	public JButton getJbtnItemSaveFile() {
		return jbtnItemSaveFile;
	}

	

	
//	public static void main(String[] args) {
//		new CalcView();
//	}// main

} // class
