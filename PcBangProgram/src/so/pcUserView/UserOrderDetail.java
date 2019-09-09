package so.pcUserView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import so.pcUserEvt.UserOrderDetailEvt;

public class UserOrderDetail extends JFrame{
	
	private JLabel jlFoodDetailImg,jlFoodPrice,jlQuantity,jlFoodStrongPoint;
	private JTextField jtfFoodName,jtfFoodPrice;
	private DefaultComboBoxModel<Integer> dcbmQuantity;
	private JComboBox<Integer> jcbQuantity;
	private JTextArea jtaFoodStrongPoint;
	private JButton jbtPut, jbtClose;
	
	private UserOrder uo;
	
	public UserOrderDetail(UserOrder uo){
//		super("�԰Ÿ��󼼺���");
		this.uo=uo;
		
		//JLabel
		jlFoodPrice=new JLabel("�ݾ�");
		jlFoodStrongPoint=new JLabel("!! �԰Ÿ� POINT !!");
		jlQuantity=new JLabel("����");
		//VO
		jlFoodDetailImg=new JLabel(new ImageIcon("C:\\dev\\workspace\\Team2_prj2\\src\\prj2\\user\\img\\JMTGR_HOT_CREAM_PORK_COTELETTE.jpg"));
		
		//JTextField,TextArea  //VO
		jtfFoodName=new JTextField("���"); 
		jtfFoodPrice=new JTextField("2000"+"��");
		jtaFoodStrongPoint=new JTextArea("��������");
		
		jtfFoodName.setEditable(false);
		jtfFoodPrice.setEditable(false);
		jtaFoodStrongPoint.setEditable(false);
		
		//JComBox (����)
		dcbmQuantity=new DefaultComboBoxModel<Integer>();
		for(int i=1; i<=10; i++) {
			dcbmQuantity.addElement(new Integer(i)); //����ڽ�..?
		}//end for
		jcbQuantity=new JComboBox<Integer>(dcbmQuantity);
		
		jbtPut=new JButton("�ֹ���Ͽ� ���");
		jbtClose=new JButton("�ݱ�");
		
		jtfFoodName.setEditable(false);
		jtfFoodPrice.setEditable(false);
		jtaFoodStrongPoint.setEditable(false);
		
		jtaFoodStrongPoint.setBorder(new TitledBorder(""));
		
		//�̺�Ʈ
		UserOrderDetailEvt uode=new UserOrderDetailEvt(this);
		jcbQuantity.addActionListener(uode);
		jbtPut.addActionListener(uode);
		jbtClose.addActionListener(uode);
		
		//������ġ
		setLayout(null);
		jlFoodDetailImg.setBounds(10, 10, 250, 250);
		jtfFoodName.setBounds(280, 20, 300, 30);
		jlFoodStrongPoint.setBounds(280, 50, 100, 50);
		jtaFoodStrongPoint.setBounds(280, 95, 300, 70);
		jlFoodPrice.setBounds(280, 220, 50, 50);
		jtfFoodPrice.setBounds(350, 230, 150, 30);
		jlQuantity.setBounds(280, 170, 50, 50);
		jcbQuantity.setBounds(350, 185, 70, 20);
		jbtPut.setBounds(300, 300, 150, 50);
		jbtClose.setBounds(470, 300, 90, 50);
		
		//Frame�� �߰�
		add(jlFoodDetailImg);
		add(jtfFoodName);
		add(jlFoodStrongPoint);
		add(jtaFoodStrongPoint);
		add(jlFoodPrice);
		add(jtfFoodPrice);
		add(jlQuantity);
		add(jcbQuantity);
		add(jbtPut);
		add(jbtClose);
		
		setResizable(false);
		setBounds(uo.getX()+300, uo.getY()+200, 600, 400);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//UserOrderDetail
	
	
	//getter
	public JTextField getJtfFoodName() {
		return jtfFoodName;
	}

	public JTextField getJtfFoodPrice() {
		return jtfFoodPrice;
	}

	public JTextArea getJtaFoodStrongPoint() {
		return jtaFoodStrongPoint;
	}

	public JButton getJbtPut() {
		return jbtPut;
	}

	public JButton getJbtClose() {
		return jbtClose;
	}

	public JComboBox<Integer> getJcbQuantity() {
		return jcbQuantity;
	}


	/**
	 * �����׽�Ʈ��
	 * @param args
	 */
	public static void main(String[] args) {
		new UserOrderDetail(new UserOrder());
	}//main

}//class