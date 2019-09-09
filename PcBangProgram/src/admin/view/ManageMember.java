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
		setBorder(new TitledBorder("ȸ��"));
		
		//North�� ���� �˻� �г�
		JPanel jpNorth = new JPanel();
		
		dcmSeardh = new DefaultComboBoxModel<String>();
		dcmSeardh.addElement("ID");
		dcmSeardh.addElement("�̸�");
		dcmSeardh.addElement("��ȭ��ȣ");
		jcbSeardh = new JComboBox<String>(dcmSeardh);
		
		jtfSearch = new JTextField(10);
		
		jbtnSearch = new JButton("�˻�");
		
		jpNorth.add(jcbSeardh);
		jpNorth.add(jtfSearch);
		jpNorth.add(jbtnSearch);
		
		//Center�� ���� ���̺�
		String[] columnNames = {"ȸ����ȣ","ID","�̸�","��ȭ��ȣ","������"};
		dtmMember = new DefaultTableModel(columnNames, 6);
		jtMember = new JTable(dtmMember);
		JScrollPane jspMember = new JScrollPane(jtMember);
		
		//��ġ
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspMember);

	}//ManageMember
 
}
