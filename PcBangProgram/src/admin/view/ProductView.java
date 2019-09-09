package admin.view;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductView extends JFrame{
	
	private JButton jbtFoodAdd,jbtSnackAdd,jbtDrinkAdd;
	private DefaultTableModel dtmFood,dtmSnack,dtmDrink;
	private JTabbedPane jtp;
	
	public ProductView() {
		
		super("��ǰ����");
		
		String[] columnNames= {"��ǰ�ڵ�","��ǰ��","�̹���","����","����","�Է���"};

		dtmFood=new DefaultTableModel(columnNames,3);
		dtmSnack=new DefaultTableModel(columnNames,3);
		dtmDrink=new DefaultTableModel(columnNames,3);
	
		//�Ļ�� ���̺�
		JTable jtFood=new JTable(dtmFood);
		
		jtFood.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtFood.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtFood.getColumnModel().getColumn(2).setPreferredWidth(210);
		jtFood.getColumnModel().getColumn(3).setPreferredWidth(180);
		jtFood.getColumnModel().getColumn(4).setPreferredWidth(130);
		jtFood.getColumnModel().getColumn(5).setPreferredWidth(130);
		
		jtFood.setRowHeight(80);
		
		JScrollPane jspFood=new JScrollPane(jtFood);
		//������ ���̺�
		JTable jtSnack=new JTable(dtmSnack);
		
		jtSnack.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtSnack.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtSnack.getColumnModel().getColumn(2).setPreferredWidth(210);
		jtSnack.getColumnModel().getColumn(3).setPreferredWidth(180);
		jtSnack.getColumnModel().getColumn(4).setPreferredWidth(130);
		jtSnack.getColumnModel().getColumn(5).setPreferredWidth(130);
		
		jtSnack.setRowHeight(80);
		
		JScrollPane jspSnack=new JScrollPane(jtSnack);
		//����� ���̺�
		JTable jtDrink=new JTable(dtmDrink);
		
		jtDrink.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtDrink.getColumnModel().getColumn(1).setPreferredWidth(130);
		jtDrink.getColumnModel().getColumn(2).setPreferredWidth(210);
		jtDrink.getColumnModel().getColumn(3).setPreferredWidth(180);
		jtDrink.getColumnModel().getColumn(4).setPreferredWidth(130);
		jtDrink.getColumnModel().getColumn(5).setPreferredWidth(130);
		
		jtDrink.setRowHeight(80);
		
		JScrollPane jspDrink=new JScrollPane(jtDrink);
		//���̺�3�� ��
		jbtFoodAdd=new JButton("�߰�");	
		jbtSnackAdd=new JButton("�߰�");	
		jbtDrinkAdd=new JButton("�߰�");	
		
		
		JPanel jpFood=new JPanel();
		jpFood.setLayout(null);
		jspFood.setBounds(0,50,1160,500);
		jbtFoodAdd.setBounds(800,565,120,50);
		jpFood.add(jspFood);
		jpFood.add(jbtFoodAdd);
		
		JPanel jpSnack=new JPanel();
		jpSnack.setLayout(null);
		jspSnack.setBounds(0,50,1160,500);
		jbtSnackAdd.setBounds(800,565,120,50);
		jpSnack.add(jspSnack);
		jpSnack.add(jbtSnackAdd);
		
		JPanel jpDrink=new JPanel();
		jpDrink.setLayout(null);
		jspDrink.setBounds(0,50,1160,500);
		jbtDrinkAdd.setBounds(800,565,120,50);
		jpDrink.add(jspDrink);
		jpDrink.add(jbtDrinkAdd);
		
		
		//��
		jtp=new JTabbedPane();
		
		jtp.add("�Ļ�",jpFood);
		jtp.add("����",jpSnack);
		jtp.add("����",jpDrink);
		
		add(jtp);
		
		setBounds(120,120,1160,700);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//�⺻������
	
	
	
	public JTabbedPane getJtp() {
		return jtp;
	}



	public JButton getJbtFoodAdd() {
		return jbtFoodAdd;
	}



	public JButton getJbtSnackAdd() {
		return jbtSnackAdd;
	}



	public JButton getJbtDrinkAdd() {
		return jbtDrinkAdd;
	}



	public DefaultTableModel getDtmFood() {
		return dtmFood;
	}



	public DefaultTableModel getDtmSnack() {
		return dtmSnack;
	}



	public DefaultTableModel getDtmDrink() {
		return dtmDrink;
	}



	public static void main(String[] args) {
		new ProductView();
	}//main

}//class