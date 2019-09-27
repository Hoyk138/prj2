package user.view;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.UserJoinEvt;

@SuppressWarnings("serial")
public class UserJoin extends JDialog {
	
	private JComboBox<String> jcbNum, jcbQuestion;
	private DefaultComboBoxModel<String> dcbmNum, dcbmQuestion ;
	private JLabel jlblId, jlblPass, jlblPassComfirm, jlblName, jlblPhone, jlblQuestion, jlblAnswer; 
	private JTextField jtfId, jtfName, jtfPhone2, jtfPhone3, jtfAnswer ;
	private JPasswordField jpfPass, jpfPassComfirm;
	private JButton jbtnOverlap, jbtnJoin;
	
	public UserJoin(UserLogin ul) {
		super(ul,"회원가입",true);
		
		ImageIcon joinBackground=new ImageIcon("C:/dev/pcbang/user/img/design/member_join_backimg2.png");
		ImageIcon idConfirmButton=new ImageIcon("C:/dev/pcbang/user/img/design/id_confirm1.png");
		ImageIcon idConfirmButton2=new ImageIcon("C:/dev/pcbang/user/img/design/id_confirm2.png");
		ImageIcon joinButton=new ImageIcon("C:/dev/pcbang/user/img/design/member_join_button1.png");
		ImageIcon joinButton2=new ImageIcon("C:/dev/pcbang/user/img/design/member_join_button2.png");
		
        JLabel jiBack=new JLabel(joinBackground);
		
		dcbmNum=new DefaultComboBoxModel<String>(new String[] {"010","011","016","017","018","019"});
		jcbNum=new JComboBox<String>(dcbmNum);
		
		//DB에 넣고 list로 불러와서 combobox에 넣기
		dcbmQuestion=new DefaultComboBoxModel<String>(new String[] 
				{"자신의 보물 제1호는?","자신의 인생 좌우명은?","유년시절 가장 생각나는 친구 이름은?",
				"다시 태어나면 되고 싶은 것은?","내가 좋아하는 캐릭터는?","인상 깊게 읽은 책 이름은?"});
		jcbQuestion=new JComboBox<String>(dcbmQuestion);
		
		jlblId=new JLabel("ID");
		jlblPass=new JLabel("비밀번호");
		jlblPassComfirm=new JLabel("비밀번호확인");
		jlblName=new JLabel("이름");
		jlblPhone=new JLabel("휴대폰번호");
		jlblQuestion=new JLabel("본인확인질문");
		jlblAnswer=new JLabel("답변");
		
		jtfId=new JTextField();
		jpfPass=new JPasswordField();
		jpfPassComfirm=new JPasswordField();
		jtfName=new JTextField();
		jtfPhone2=new JTextField();
		jtfPhone3=new JTextField();
		jtfAnswer=new JTextField();
		
		jbtnOverlap=new JButton(idConfirmButton);
		jbtnJoin=new JButton(joinButton);
		
		//Rollover : 마우스가 올라갔을 때 다른 이미지로 변경하는 것
		jbtnOverlap.setRolloverIcon(idConfirmButton2);
		jbtnJoin.setRolloverIcon(joinButton2);

		setLayout(null);
		
		jiBack.setBounds(0, 0, 566, 540);
		jlblId.setBounds(50, 60, 76, 30);
		jlblPass.setBounds(50, 100, 76, 30);
		jlblPassComfirm.setBounds(50, 140, 100, 30);
		jlblName.setBounds(50, 180, 76, 30);
		jlblPhone.setBounds(50, 220, 76, 30);
		jlblQuestion.setBounds(50, 260, 100, 30);
		jlblAnswer.setBounds(50, 300, 76, 30);
		
		jtfId.setBounds(177, 84, 180, 30);
		jpfPass.setBounds(177, 133, 180, 30);
		jpfPassComfirm.setBounds(177, 180, 180, 30);
		jtfName.setBounds(177, 228, 300, 30);
		jcbNum.setBounds(174, 274, 80, 34);
		jtfPhone2.setBounds(293, 277, 76, 30);
		jtfPhone3.setBounds(410, 277, 80, 30);
		jcbQuestion.setBounds(173, 322, 325, 36);
		jtfAnswer.setBounds(177, 374, 300, 30);
		
		jbtnOverlap.setBounds(375, 79, 125, 39);
		jbtnJoin.setBounds(204, 453, 145, 35);
		
//		jtfId.setForeground(new Color(0xC2C2C2)); //jtf입력글자컬러바꾸기
//		jpfPass.setForeground(new Color(0xC2C2C2));
//		jpfPassComfirm.setForeground(new Color(0xC2C2C2));
//		jtfName.setForeground(new Color(0xC2C2C2));
////		jcbNum.setForeground(new Color(0xC2C2C2));
//		jtfPhone2.setForeground(new Color(0xC2C2C2));
//		jtfPhone3.setForeground(new Color(0xC2C2C2));
////		jcbQuestion.setForeground(new Color(0xC2C2C2));
//		jtfAnswer.setForeground(new Color(0xC2C2C2));
		
		jtfId.setCaretColor(new Color(0xC2C2C2)); //커서컬러바꾸기
		jpfPass.setCaretColor(new Color(0xC2C2C2));
		jpfPassComfirm.setCaretColor(new Color(0xC2C2C2));
		jtfName.setCaretColor(new Color(0xC2C2C2));
		jtfPhone2.setCaretColor(new Color(0xC2C2C2));
		jtfPhone3.setCaretColor(new Color(0xC2C2C2));
		jtfAnswer.setCaretColor(new Color(0xC2C2C2));
		
		jtfId.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //jtf 테두리 없애기
		jpfPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jpfPassComfirm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfPhone2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfPhone3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jtfAnswer.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		
		jbtnOverlap.setBorder(javax.swing.BorderFactory.createEmptyBorder());//버튼테두리 없애기
		jbtnJoin.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		add(jtfId);
		add(jpfPass);
		add(jpfPassComfirm);
		add(jtfName);
		add(jcbNum);
		add(jtfPhone2);
		add(jtfPhone3);
		add(jcbQuestion);
		add(jtfAnswer);
		
		add(jbtnOverlap);
		add(jbtnJoin);
		
		add(jiBack);
		
		add(jlblId);
		add(jlblPass);
		add(jlblPassComfirm);
		add(jlblName);
		add(jlblPhone);
		add(jlblQuestion);
		add(jlblAnswer);
		
		UserJoinEvt uje=new UserJoinEvt(this);
		jtfId.addActionListener(uje);
		jpfPass.addActionListener(uje);
		jpfPassComfirm.addActionListener(uje);
		jtfName.addActionListener(uje);
		jcbNum.addActionListener(uje);
		jtfPhone2.addActionListener(uje);
		jtfPhone3.addActionListener(uje);
//		jtfPhone2.addFocusListener(uje);
//		jtfPhone3.addFocusListener(uje);
		jcbQuestion.addActionListener(uje);
		jtfAnswer.addActionListener(uje);
		jbtnOverlap.addActionListener(uje);
		jbtnJoin.addActionListener(uje);
		
		
		setResizable(false);
		setBounds(700, 260, 570, 580);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public JComboBox<String> getJcbNum() {
		return jcbNum;
	}

	public JComboBox<String> getJcbQuestion() {
		return jcbQuestion;
	}

	public DefaultComboBoxModel<String> getDcbmNum() {
		return dcbmNum;
	}

	public DefaultComboBoxModel<String> getDcbmQuestion() {
		return dcbmQuestion;
	}

	public JLabel getJlblId() {
		return jlblId;
	}

	public JLabel getJlblPass() {
		return jlblPass;
	}

	public JLabel getJlblPassComfirm() {
		return jlblPassComfirm;
	}

	public JLabel getJlblName() {
		return jlblName;
	}

	public JLabel getJlblPhone() {
		return jlblPhone;
	}

	public JLabel getJlblQuestion() {
		return jlblQuestion;
	}

	public JLabel getJlblAnswer() {
		return jlblAnswer;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}

	public JTextField getJtfPhone3() {
		return jtfPhone3;
	}

	public JTextField getJtfAnswer() {
		return jtfAnswer;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JPasswordField getJpfPassComfirm() {
		return jpfPassComfirm;
	}

	public JButton getJbtnOverlap() {
		return jbtnOverlap;
	}

	public JButton getJbtnJoin() {
		return jbtnJoin;
	}

	
	
//	public static void main(String[] args) {
//		new UserJoin(null);
//	}//main



}//class
