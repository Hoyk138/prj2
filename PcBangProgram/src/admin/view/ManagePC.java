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
		
		//North�� ���� �˻� �г�
		JPanel jpNorth = new JPanel();
		
		dcmSeardh = new DefaultComboBoxModel<String>();
		dcmSeardh.addElement("PC��ȣ");
		dcmSeardh.addElement("IP");
		dcmSeardh.addElement("�����");
		jcbSeardh = new JComboBox<String>(dcmSeardh);
		
		jtfSearch = new JTextField(10);
		
		jbtnSearch = new JButton("�˻�");
		
		jpNorth.add(jcbSeardh);
		jpNorth.add(jtfSearch);
		jpNorth.add(jbtnSearch);
		
		//Center�� ���� ���̺�
		String[] columnNames = {"PC��ȣ","IP","�����","���"};
		dtmPC = new DefaultTableModel(columnNames, 6);
		jtPC = new JTable(dtmPC);
		JScrollPane jspPC = new JScrollPane(jtPC);
		
		//South�� ���� ��ư �г�
		JPanel jpSouth = new JPanel();
//		jbtnUpdate = new JButton("����");
//		jbtnDelete = new JButton("����");
		jbtnResiter = new JButton("���");
//		
//		jpSouth.add(jbtnUpdate);
//		jpSouth.add(jbtnDelete);
		jpSouth.add(jbtnResiter);
		
		//��ġ
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspPC);
		add("South",jpSouth);
		
	}//ManagePC
 
}
