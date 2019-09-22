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
		setBorder(new TitledBorder("ȸ��"));
		
		//North�� ���� �˻� �г�
		JPanel jpNorth = new JPanel();
		
		dcmSearch = new DefaultComboBoxModel<String>();
		dcmSearch.addElement("ID");
		dcmSearch.addElement("�̸�");
		dcmSearch.addElement("��ȭ��ȣ");
		dcmSearch.addElement("������");
		jcbSearch = new JComboBox<String>(dcmSearch);
		
		jtfSearch = new JTextField(10);
		
		jbtnSearch = new JButton("�˻�");
		
		jpNorth.add(jcbSearch);
		jpNorth.add(jtfSearch);
		jpNorth.add(jbtnSearch);
		
		//Center�� ���� ���̺�
//		String[] columnNames = {"ȸ����ȣ","ID","�̸�","��ȭ��ȣ","������"};
//		String[] columnNames = {"ID","�̸�","��ȭ��ȣ","������"};
		String[] columnNames = {"ID","�̸�","��ȭ��ȣ","������","PC���ð�(��)","��ǰ���űݾ�(��)"};
		dtmMember = new DefaultTableModel(columnNames, 6) {
			//�÷��� �Էµ� ���������� �״�� �����ֱ�
			//���̺� �÷��� ���������� �Էµ� ��ü �״�� ��ȸ �ǵ��� �θ�Ŭ������ Ư�� method�� Override
			@Override
			public Class<?> getColumnClass(int column) {
				//�Էµ� �� �ϳ��� ��� �÷��� ���� ������ Ŭ������ ��ȯ�ϴ� �� 
				//0���� ���� �Էµ� �� �ϳ��� ������� ó��
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
			//���̺� ���� �Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		// �÷��� ���� ����
		jtMember.setRowHeight(30);
		//�� �̵� �Ұ�
		jtMember.getTableHeader().setReorderingAllowed(false);

		//��� ����
		//DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer dtcrMember = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		dtcrMember.setHorizontalAlignment(SwingConstants.CENTER);
		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcmMember = jtMember.getColumnModel();
		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcmMember.getColumnCount(); i++) {
			tcmMember.getColumn(i).setCellRenderer(dtcrMember);
		} // end for
		
		//���̺��� �ڵ� ���� ����, ���� ��������  �����ϴ� ���
		jtMember.setAutoCreateRowSorter(true); 
		TableRowSorter<TableModel> trsMember = new TableRowSorter<TableModel>(jtMember.getModel()); 
		jtMember.setRowSorter(trsMember); 

		JScrollPane jspMember = new JScrollPane(jtMember);

		//��ġ
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspMember);
		
		//�̺�Ʈ ���
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
