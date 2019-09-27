package admin.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import admin.controller.ManagePCEvt;

@SuppressWarnings("serial")
//public class ManagePC extends JPanel{
public class ManagePC extends JDialog{

//	private DefaultComboBoxModel<String> dcmSeardh;
//	private JComboBox<String> jcbSeardh;
//	private JTextField jtfSearch;
//	private JButton jbtnSearch;
	
	private DefaultTableModel dtmPC;
	private JTable jtPC;
	
	private JButton jbtnResiter;
	private JButton jbtnUpdate;
	private JButton jbtnDelete;
	
//	public ManagePC(MainView mv, ManageShop ms) {
	public ManagePC(MainView mv) {
//		setBorder(new TitledBorder("PC"));
		super(mv, "PC관리", true);
		
		//North에 넣을 검색 패널
//		JPanel jpNorth = new JPanel();
//		
//		dcmSeardh = new DefaultComboBoxModel<String>();
//		dcmSeardh.addElement("PC번호");
//		dcmSeardh.addElement("IP");
//		dcmSeardh.addElement("등록일");
//		jcbSeardh = new JComboBox<String>(dcmSeardh);
//		
//		jtfSearch = new JTextField(10);
//		
//		jbtnSearch = new JButton("검색");
//		
//		jpNorth.add(jcbSeardh);
//		jpNorth.add(jtfSearch);
//		jpNorth.add(jbtnSearch);
		
		//North에 넣을  패널
		JPanel jpNorth = new JPanel();
		jpNorth.add(new JLabel(" "));
		
		//Center에 넣을 테이블
		String[] columnNames = {"PC번호","IP주소","다시 고친 날짜"};
		dtmPC = new DefaultTableModel(columnNames, 6);
		jtPC = new JTable(dtmPC) {
			//테이블 편집 불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		
		//컬럼의 넓이 변경(column Class를 사용한다.): 컬럼의 넓이는 각각 변경 해 주어야 한다.
		jtPC.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtPC.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtPC.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		// 컬럼의 높이 변경
		jtPC.setRowHeight(25);
		//열 이동 불가
		jtPC.getTableHeader().setReorderingAllowed(false);
		
		//가운데 정렬
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer dtcrMember = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		dtcrMember.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmMember = jtPC.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmMember.getColumnCount(); i++) {
			tcmMember.getColumn(i).setCellRenderer(dtcrMember);
		} // end for
		
		JScrollPane jspPC = new JScrollPane(jtPC);
		jspPC.setBorder(new TitledBorder("PC 목록"));
		
		//South에 넣을 버튼 패널
		JPanel jpSouth = new JPanel();
		jbtnResiter = new JButton("등록");
		jbtnUpdate = new JButton("수정");
		jbtnDelete = new JButton("삭제");
		
		jpSouth.add(jbtnResiter);
		jpSouth.add(jbtnUpdate);
		jpSouth.add(jbtnDelete);
		
		//색깔//////////////////////////
		jbtnResiter.setBackground(new Color(0xF5D08A));
		jbtnUpdate.setBackground(new Color(0xF5D08A));
		jbtnDelete.setBackground(new Color(0xF5D08A));
		jpNorth.setBackground(new Color(0x3C5A91));
		jspPC.setBackground(Color.white);
		jpSouth.setBackground(new Color(0x3C5A91));
		setBackground(Color.white);
		////////////////////////////

		//배치
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspPC);
		add("South",jpSouth);
		
		//이벤트 등록
		ManagePCEvt mpce = new ManagePCEvt(this);
		jbtnResiter.addActionListener(mpce);
		jbtnUpdate.addActionListener(mpce);
		jbtnDelete.addActionListener(mpce);
		
		setBounds(mv.getX()+30, mv.getY()+30, 450, 775);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
	}//ManagePC

	public DefaultTableModel getDtmPC() {
		return dtmPC;
	}

	public JButton getJbtnResiter() {
		return jbtnResiter;
	}

	public JButton getJbtnUpdate() {
		return jbtnUpdate;
	}

	public JButton getJbtnDelete() {
		return jbtnDelete;
	}

	public JTable getJtPC() {
		return jtPC;
	}

}//class
