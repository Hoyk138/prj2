package user.view;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import user.controller.FindIdEvt;

@SuppressWarnings("serial")
public class FindId extends JDialog {
	
	private JComboBox<String> jcbNum;
	private DefaultComboBoxModel<String> dcbmNum;
	private JLabel jlbName, jlbPhone;
	private JTextField jtfName, jtfPhone1, jtfPhone2, jtfPhone3;
	private JButton jbtnFindID;
	
	public FindId(UserLogin ul) {  
		super(ul,"ID 찾기",true);
		
		ImageIcon findIdBackground=new ImageIcon("C:/dev/pcbang/user/img/design/find_id_backimg.png");
		ImageIcon findIdButton=new ImageIcon("C:/dev/pcbang/user/img/design/find_id_button1.png");
		ImageIcon findIdButton2=new ImageIcon("C:/dev/pcbang/user/img/design/find_id_button2.png");
		
		JLabel fiBack=new JLabel(findIdBackground);
		
		dcbmNum=new DefaultComboBoxModel<String>(new String[] {"010","011","016","017","018","019"});
		jcbNum=new JComboBox<String>(dcbmNum);
		
		jlbName=new JLabel("이름");
		jlbPhone=new JLabel("휴대폰번호");
		
		jtfName=new JTextField();
		jtfPhone2=new JTextField();
		jtfPhone3=new JTextField();
		
		
		jbtnFindID=new JButton(findIdButton);
		//마우스가 올라갔을 때 다른 이미지로 변경
		jbtnFindID.setRolloverIcon(findIdButton2);
		
		setLayout(null);
		
		fiBack.setBounds(0, 0, 561, 308);
		
		jlbName.setBounds(40, 60, 76, 34);
		jlbPhone.setBounds(40, 100, 76, 30);
		jtfName.setBounds(174, 93, 210, 30);
		jcbNum.setBounds(170, 137, 85, 36);
		jtfPhone2.setBounds(297, 139, 70, 34);
		jtfPhone3.setBounds(410, 139, 70, 34);
		jbtnFindID.setBounds(201, 218, 145, 35);
		
		
		jtfName.setCaretColor(new Color(0xC2C2C2)); //커서컬러바꾸기
		jtfPhone2.setCaretColor(new Color(0xC2C2C2));
		jtfPhone3.setCaretColor(new Color(0xC2C2C2));

		
		jtfName.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //jtf 테두리 없애기
		jtfPhone2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfPhone3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		jbtnFindID.setBorder(javax.swing.BorderFactory.createEmptyBorder());//버튼테두리 없애기
		
		add(jtfName);
		add(jcbNum);
		add(jtfPhone2);
		add(jtfPhone3);
		add(jbtnFindID);
		add(fiBack);
		add(jlbName);
		add(jlbPhone);
		
		FindIdEvt fie=new FindIdEvt(this);
		
		jtfName.addActionListener(fie);
		jcbNum.addActionListener(fie);
		jtfPhone2.addActionListener(fie);
		jtfPhone3.addActionListener(fie);
		jbtnFindID.addActionListener(fie);
		
		jcbNum.setBorder(new TitledBorder(new  LineBorder(new Color(0xefefef))));
		jcbNum.setBackground(Color.white);
		
		setResizable(false);
		setBounds(700, 400, 561, 308);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public JComboBox<String> getJcbNum() {
		return jcbNum;
	}

	public DefaultComboBoxModel<String> getDcbmNum() {
		return dcbmNum;
	}

	public JLabel getJlbName() {
		return jlbName;
	}

	public JLabel getJlbPhone() {
		return jlbPhone;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhone1() {
		return jtfPhone1;
	}

	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}

	public JTextField getJtfPhone3() {
		return jtfPhone3;
	}

	public JButton getJbtnFindID() {
		return jbtnFindID;
	}
	
	
	
//	public static void main(String[] args) {
//		new FindId(null);
//	}//main


}
