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
		super(mv, "PC����", true);
		
		//North�� ���� �˻� �г�
//		JPanel jpNorth = new JPanel();
//		
//		dcmSeardh = new DefaultComboBoxModel<String>();
//		dcmSeardh.addElement("PC��ȣ");
//		dcmSeardh.addElement("IP");
//		dcmSeardh.addElement("�����");
//		jcbSeardh = new JComboBox<String>(dcmSeardh);
//		
//		jtfSearch = new JTextField(10);
//		
//		jbtnSearch = new JButton("�˻�");
//		
//		jpNorth.add(jcbSeardh);
//		jpNorth.add(jtfSearch);
//		jpNorth.add(jbtnSearch);
		
		//North�� ����  �г�
		JPanel jpNorth = new JPanel();
		jpNorth.add(new JLabel(" "));
		
		//Center�� ���� ���̺�
		String[] columnNames = {"PC��ȣ","IP�ּ�","�ٽ� ��ģ ��¥"};
		dtmPC = new DefaultTableModel(columnNames, 6);
		jtPC = new JTable(dtmPC) {
			//���̺� ���� �Ұ�
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		
		//�÷��� ���� ����(column Class�� ����Ѵ�.): �÷��� ���̴� ���� ���� �� �־�� �Ѵ�.
		jtPC.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtPC.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtPC.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		// �÷��� ���� ����
		jtPC.setRowHeight(25);
		//�� �̵� �Ұ�
		jtPC.getTableHeader().setReorderingAllowed(false);
		
		//��� ����
		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer dtcrMember = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		dtcrMember.setHorizontalAlignment(SwingConstants.CENTER);
		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcmMember = jtPC.getColumnModel();
		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcmMember.getColumnCount(); i++) {
			tcmMember.getColumn(i).setCellRenderer(dtcrMember);
		} // end for
		
		JScrollPane jspPC = new JScrollPane(jtPC);
		jspPC.setBorder(new TitledBorder("PC ���"));
		
		//South�� ���� ��ư �г�
		JPanel jpSouth = new JPanel();
		jbtnResiter = new JButton("���");
		jbtnUpdate = new JButton("����");
		jbtnDelete = new JButton("����");
		
		jpSouth.add(jbtnResiter);
		jpSouth.add(jbtnUpdate);
		jpSouth.add(jbtnDelete);
		
		//����//////////////////////////
		jbtnResiter.setBackground(new Color(0xF5D08A));
		jbtnUpdate.setBackground(new Color(0xF5D08A));
		jbtnDelete.setBackground(new Color(0xF5D08A));
		jpNorth.setBackground(new Color(0x3C5A91));
		jspPC.setBackground(Color.white);
		jpSouth.setBackground(new Color(0x3C5A91));
		setBackground(Color.white);
		////////////////////////////

		//��ġ
		setLayout(new BorderLayout());
		add("North",jpNorth);
		add("Center",jspPC);
		add("South",jpSouth);
		
		//�̺�Ʈ ���
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
