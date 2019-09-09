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
public class ManageMember extends JPanel{
	
	private DefaultComboBoxModel<String> dcmSeardh;
	private JComboBox<String> jcbSeardh;
	private JTextField jtfSearch;
	private JButton jbtnSearch;
	
	private DefaultTableModel dtmMember;
	private JTable jtMember;
	
	public ManageMember(MainView mv) {
		setBorder(new TitledBorder("회원"));
		
		//North에 넣을 검색 패널
		JPanel jpNorth = new JPanel();
		
		dcmSeardh = new DefaultComboBoxModel<String>();
		dcmSeardh.addElement("ID");
		dcmSeardh.addElement("이름");
		dcmSeardh.addElement("전화번호");
		jcbSeardh = new JComboBox<String>(dcmSeardh);
		
		jtfSearch = new JTextField(10);
		
		jbtnSearch = new JButton("검색");
		
		jpNorth.add(jcbSeardh);
		jpNorth.add(jtfSearch);
		jpNorth.add(jbtnSearch);
		
		//Center에 넣을 테이블
		String[] columnNames = {"회원번호","ID","이름","전화번호","가입일"};
		dtmMember = new DefaultTableModel(columnNames, 6);
		jtMember = new JTable(dtmMember);
		JScrollPane jspMember = new JScrollPane(jtMember);
		
		//배치
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspMember);

	}//ManageMember
 
}
