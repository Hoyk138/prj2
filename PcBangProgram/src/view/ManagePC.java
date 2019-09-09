package admin.view;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ManagePC extends JPanel{

	private DefaultComboBoxModel<String> dcmSeardh;
	private JComboBox<String> jcbSeardh;
	private JTextField jtfSearch;
	private JButton jbtnSearch;
	
	private DefaultTableModel dtmPC;
	private JTable jtPC;
	
//	private JButton jbtnUpdate;
//	private JButton jbtnDelete;
	private JButton jbtnResiter;
	
	public ManagePC(MainView mv) {
		setBorder(new TitledBorder("PC"));
		
		//North에 넣을 검색 패널
		JPanel jpNorth = new JPanel();
		
		dcmSeardh = new DefaultComboBoxModel<String>();
		dcmSeardh.addElement("PC번호");
		dcmSeardh.addElement("IP");
		dcmSeardh.addElement("등록일");
		jcbSeardh = new JComboBox<String>(dcmSeardh);
		
		jtfSearch = new JTextField(10);
		
		jbtnSearch = new JButton("검색");
		
		jpNorth.add(jcbSeardh);
		jpNorth.add(jtfSearch);
		jpNorth.add(jbtnSearch);
		
		//Center에 넣을 테이블
		String[] columnNames = {"PC번호","IP","등록일","비고"};
		dtmPC = new DefaultTableModel(columnNames, 6);
		jtPC = new JTable(dtmPC);
		JScrollPane jspPC = new JScrollPane(jtPC);
		
		//South에 넣을 버튼 패널
		JPanel jpSouth = new JPanel();
//		jbtnUpdate = new JButton("수정");
//		jbtnDelete = new JButton("삭제");
		jbtnResiter = new JButton("등록");
//		
//		jpSouth.add(jbtnUpdate);
//		jpSouth.add(jbtnDelete);
		jpSouth.add(jbtnResiter);
		
		//배치
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspPC);
		add("South",jpSouth);
		
	}//ManagePC
 
}
