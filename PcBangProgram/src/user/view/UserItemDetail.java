package user.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import user.VO.UserItemDetailVO;
import user.controller.UserItemDetailEvt;
import user.controller.UserItemEvt;

public class UserItemDetail extends JDialog{
	
	private JLabel jlFoodDetailImg,jlFoodPrice,jlQuantity,jlFoodStrongPoint;
	private JTextField jtfName,jtfFoodPrice;
	private DefaultComboBoxModel<Integer> dcbmQuantity;
	private JComboBox<Integer> jcbQuantity;
	private JTextArea jtaFoodStrongPoint;
	private JButton jbtPut, jbtClose;
	
	private UserItem ui;
	private UserItemEvt uie;
	private String itemName;
	
	
	public UserItemDetail(UserItem ui, UserItemEvt uie, UserItemDetailVO uidVO){
		this.ui=ui;
		this.uie=uie;
		itemName=uidVO.getItemName();
		
		//JLabel
		jlFoodPrice=new JLabel("가격");
		jlFoodStrongPoint=new JLabel("!! 먹거리 POINT !!");
		jlQuantity=new JLabel("수량");
		//VO
		jlFoodDetailImg=new JLabel(new ImageIcon(uidVO.getItemImg()));
		
		//JTextField,TextArea  //VO
		jtfName=new JTextField("["+uidVO.getCategoryName()+"]"+uidVO.getItemName()); 
		jtfFoodPrice=new JTextField(String.valueOf(uidVO.getItemPrice()));
		jtaFoodStrongPoint=new JTextArea(uidVO.getItemDescription());
		
		jtfName.setEditable(false);
		jtfFoodPrice.setEditable(false);
		jtaFoodStrongPoint.setEditable(false);
		
		//JComBox (수량)
		dcbmQuantity=new DefaultComboBoxModel<Integer>();
		for(int i=1; i<=10; i++) {
			dcbmQuantity.addElement(new Integer(i)); //unboxing..?
		}//end for
		jcbQuantity=new JComboBox<Integer>(dcbmQuantity);
		
		jbtPut=new JButton("주문목록에 담기");
		jbtClose=new JButton("닫기");
		
		jtfName.setEditable(false);
		jtfFoodPrice.setEditable(false);
		jtaFoodStrongPoint.setEditable(false);
		
		jtaFoodStrongPoint.setBorder(new TitledBorder(""));
		
		//이벤트 처리
		UserItemDetailEvt uode=new UserItemDetailEvt(this,ui,uie);
		jcbQuantity.addActionListener(uode);
		jbtPut.addActionListener(uode);
		jbtClose.addActionListener(uode);
		
		//수동배치
		setLayout(null);
		jlFoodDetailImg.setBounds(10, 10, 250, 250);
		jtfName.setBounds(280, 20, 300, 30);
		jlFoodStrongPoint.setBounds(280, 50, 100, 50);
		jtaFoodStrongPoint.setBounds(280, 95, 300, 70);
		jlFoodPrice.setBounds(280, 220, 50, 50);
		jtfFoodPrice.setBounds(350, 230, 150, 30);
		jlQuantity.setBounds(280, 170, 50, 50);
		jcbQuantity.setBounds(350, 185, 70, 20);
		jbtPut.setBounds(300, 300, 150, 50);
		jbtClose.setBounds(470, 300, 90, 50);
		
		//Frame에 추가
		add(jlFoodDetailImg);
		add(jtfName);
		add(jlFoodStrongPoint);
		add(jtaFoodStrongPoint);
		add(jlFoodPrice);
		add(jtfFoodPrice);
		add(jlQuantity);
		add(jcbQuantity);
		add(jbtPut);
		add(jbtClose);
		
		setResizable(false);
		setBounds(ui.getX()+300, ui.getY()+200, 600, 400);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//UserOrderDetail
	
	
	//getter
	public JTextField getJtfName() {
		return jtfName;
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
	 * 단위테스트용
	 * @param args
	 */
//	public static void main(String[] args) {
//		new UserItemDetail(new UserItem(),new UserItemDetailVO("1", "1", "1","1" , 1));
//	}//main

}//class
