package user.view;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import user.VO.UserItemDetailVO;
import user.controller.UserItemDetailEvt;
import user.controller.UserItemEvt;
import user.controller.UserMainEvt;

public class UserItemDetail extends JDialog{
	
	private JLabel jlImg,jlPrice,jlOne,jlQuantity,jlDescription;
	private JLabel jlDetailName,jlDetailPrice;
	private JTextField jtfDescription;
	private DefaultComboBoxModel<Integer> dcbmQuantity;
	private JComboBox<Integer> jcbQuantity;
	private JButton jbtPut, jbtClose;
	
	private UserMain um;
	private UserItem ui;
	private static UserItemEvt uie; //
	private UserMainEvt ume;
	private String itemName;
	
	
	public UserItemDetail(UserItem ui, UserItemEvt uie, UserItemDetailVO uidVO){
//		this.um=um;
		this.ui=ui;
		this.uie=uie;
		itemName=uidVO.getItemName();
		
		
		//JLabel
		jlPrice=new JLabel("가격");
		jlOne=new JLabel("원");
		jlDescription=new JLabel("◎ 먹거리 POINT ◎");
		jlQuantity=new JLabel("수량");
		//VO
		jlImg=new JLabel(new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/user/image/rs_"+uidVO.getItemImg()));
		
		//JTextField,TextArea  //VO
		jlDetailName=new JLabel("["+uidVO.getCategoryName()+"]"+uidVO.getItemName()); 
		jlDetailPrice=new JLabel(String.valueOf(uidVO.getItemPrice()));
		jtfDescription=new JTextField(uidVO.getItemDescription());
		jtfDescription.setEditable(false);
		
		//JComBox (수량)
		dcbmQuantity=new DefaultComboBoxModel<Integer>();
		for(int i=1; i<=10; i++) {
			dcbmQuantity.addElement(new Integer(i)); //unboxing..?
		}//end for
		jcbQuantity=new JComboBox<Integer>(dcbmQuantity);
		
		jbtPut=new JButton("장바구니 담기");
		jbtClose=new JButton("닫기");

		//Panel
		JPanel jp=new JPanel();
		JPanel jpDetailName=new JPanel();
		JPanel jpCenter=new JPanel();
		JPanel jpBtn=new JPanel();
		
		jp.add(jpDetailName);
		jp.add(jpCenter);
		jp.add(jpBtn);
		//
		jpDetailName.add(jlDetailName);
		//
		jpCenter.add(jlImg);
		jpCenter.add(jlDescription);
		jpCenter.add(jtfDescription);
		jpCenter.add(jlQuantity);
		jpCenter.add(jcbQuantity);
		jpCenter.add(jlPrice);
		jpCenter.add(jlOne);
		jpCenter.add(jlDetailPrice);
		//
		jpBtn.add(jbtPut);
		jpBtn.add(jbtClose);
		
		jlImg.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jtfDescription.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jpDetailName.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jpCenter.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jbtPut.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jbtClose.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		
		jp.setLayout(null);
		jpDetailName.setBounds(0, 0, 600, 50);
		jpCenter.setBounds(0, 50, 600, 270);
		jpBtn.setBounds(0, 320, 600, 60);
		//
		jpDetailName.setLayout(null);
		jlDetailName.setBounds(260, 0, 200, 50);
		//
		jpCenter.setLayout(null);
		jlImg.setBounds(30, 30, 260, 220);
		jlDescription.setBounds(370, 30, 200, 50);
		jtfDescription.setBounds(310, 80, 280, 50);
		jlQuantity.setBounds(330, 150, 100, 50);
		jcbQuantity.setBounds(390, 160, 120, 25);
		jlPrice.setBounds(330, 200, 100, 50);
		jlDetailPrice.setBounds(450, 200, 100, 50);
		jlOne.setBounds(500, 200, 100, 50);
		
		jpBtn.setLayout(null);
		jbtPut.setBounds(200, 10, 200, 50);
		jbtClose.setBounds(520, 10, 80, 40);
		
		//이벤트 처리
		UserItemDetailEvt uode=new UserItemDetailEvt(um,this,ui,uie);
		jcbQuantity.addActionListener(uode);
		jbtPut.addActionListener(uode);
		jbtClose.addActionListener(uode);
		
		//수동배치
		setLayout(null);
		jp.setBounds(22, 22, 600, 380);
		
		//Frame에 추가
		add(jp);
		
		//
		jpDetailName.setBackground(new Color(0x6482B9));
		jpCenter.setBackground(Color.white);
		jtfDescription.setBackground(new Color(0xE0EBFF));
		jcbQuantity.setBackground(Color.white);
		jpBtn.setBackground(new Color(0xCCCCCC));
		jbtPut.setBackground(new Color(0x6482B9));
		jbtClose.setBackground(new Color(0x6482B9));
		//폰트
		jlDetailName.setFont(new Font("serif", Font.BOLD, 15));
		jlDescription.setFont(new Font("MonoSpaced", Font.BOLD, 15));
		jlDetailName.setForeground(Color.white);	
		jbtPut.setForeground(Color.white);	
		jbtClose.setForeground(Color.white);	
		
		getContentPane().setBackground(new Color(0xCCCCCC));
		
		setResizable(false);
		setBounds(ui.getX()+300, ui.getY()+200, 650, 450);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}//UserOrderDetail
	
	
	//getter
	public JTextField getJtfDescription() {
		return jtfDescription;
	}

	public JLabel getJlDetailName() {
		return jlDetailName;
	}

	public JLabel getJlDetailPrice() {
		return jlDetailPrice;
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
//		new UserItemDetail(new UserItem(),uie, new UserItemDetailVO("1", "1", "1","1" , 1));
//	}//main

}//class
