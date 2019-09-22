package admin.view;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import admin.controller.ManageMemberEvt;

@SuppressWarnings("serial")
public class ManageMember extends JPanel{
	
	private DefaultComboBoxModel<String> dcmSearch;
	private JComboBox<String> jcbSearch;
	private JTextField jtfSearch;
	private JButton jbtnSearch;
	
	private DefaultTableModel dtmMember;
	private JTable jtMember;
	
	public ManageMember(MainView mv) {
		setBorder(new TitledBorder("회원"));
		
		//North에 넣을 검색 패널
		JPanel jpNorth = new JPanel();
		
		dcmSearch = new DefaultComboBoxModel<String>();
		dcmSearch.addElement("ID");
		dcmSearch.addElement("이름");
		dcmSearch.addElement("전화번호");
		dcmSearch.addElement("가입일");
		jcbSearch = new JComboBox<String>(dcmSearch);
		
		jtfSearch = new JTextField(10);
		
		jbtnSearch = new JButton("검색");
		
		jpNorth.add(jcbSearch);
		jpNorth.add(jtfSearch);
		jpNorth.add(jbtnSearch);
		
		//Center에 넣을 테이블
//		String[] columnNames = {"회원번호","ID","이름","전화번호","가입일"};
//		String[] columnNames = {"ID","이름","전화번호","가입일"};
		String[] columnNames = {"ID","이름","전화번호","가입일","PC사용시간(분)","상품구매금액(원)"};
		dtmMember = new DefaultTableModel(columnNames, 6) {
			//컬럼에 입력된 데이터형을 그대로 보여주기
			//테이블 컬럼의 데이터형이 입력된 객체 그대로 조회 되도록 부모클래스의 특정 method를 Override
			@Override
			public Class<?> getColumnClass(int column) {
				//입력된 행 하나의 모든 컬럼의 값을 원래의 클래스로 반환하는 일 
				//0행은 현재 입력된 행 하나만 대상으로 처리
				return getValueAt(0, column).getClass();
			}//getColumnClass

			//			@Override
//			public Class<?> getColumnClass(int column) {
//				switch (column) {
//				case 0:
//					return String.class;
//				case 1:
//					return String.class;
//				case 2:
//					return String.class;
//				case 3:
//					return String.class;
//				case 4:
//					return Integer.class;
//				case 5:
//					return Integer.class;
//				default:
//					return String.class;
//				}
//			}//getColumnClass
		};

		jtMember = new JTable(dtmMember) {
			//테이블 편집 불가
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		// 컬럼의 높이 변경
		jtMember.setRowHeight(30);
		//열 이동 불가
		jtMember.getTableHeader().setReorderingAllowed(false);

		//가운데 정렬
		//DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer dtcrMember = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		dtcrMember.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmMember = jtMember.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmMember.getColumnCount(); i++) {
			tcmMember.getColumn(i).setCellRenderer(dtcrMember);
		} // end for
		
		//테이블을 자동 내림 차순, 오름 차순으로  정렬하는 기능
		jtMember.setAutoCreateRowSorter(true); 
		TableRowSorter<TableModel> trsMember = new TableRowSorter<TableModel>(jtMember.getModel()); 
		jtMember.setRowSorter(trsMember); 

		JScrollPane jspMember = new JScrollPane(jtMember);

		//배치
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspMember);
		
		//이벤트 등록
		ManageMemberEvt mme = new ManageMemberEvt(this);
		jtfSearch.addActionListener(mme);
		jbtnSearch.addActionListener(mme);

	}//ManageMember

	public DefaultTableModel getDtmMember() {
		return dtmMember;
	}

	public JComboBox<String> getJcbSearch() {
		return jcbSearch;
	}

	public JTextField getJtfSearch() {
		return jtfSearch;
	}

	public JButton getJbtnSearch() {
		return jbtnSearch;
	}
 
}//class
