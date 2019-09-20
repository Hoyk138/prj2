package user.view;

import javax.swing.DefaultComboBoxModel;
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
	
	public UserJoin() {
//		super("회원가입");
		
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
		
		jbtnOverlap=new JButton("중복확인");
		jbtnJoin=new JButton("가입하기");
		
		setLayout(null);
		
		jlblId.setBounds(50, 60, 76, 30);
		jlblPass.setBounds(50, 100, 76, 30);
		jlblPassComfirm.setBounds(50, 140, 100, 30);
		jlblName.setBounds(50, 180, 76, 30);
		jlblPhone.setBounds(50, 220, 76, 30);
		jlblQuestion.setBounds(50, 260, 100, 30);
		jlblAnswer.setBounds(50, 300, 76, 30);
		
		jtfId.setBounds(160, 60, 130, 25);
		jpfPass.setBounds(160, 100, 130, 25);
		jpfPassComfirm.setBounds(160, 140, 130, 25);
		jtfName.setBounds(160, 180, 230, 25);
		jcbNum.setBounds(160, 220, 60, 25);
		jtfPhone2.setBounds(227, 220, 76, 25);
		jtfPhone3.setBounds(310, 220, 80, 25);
		jcbQuestion.setBounds(160, 260, 230, 25);
		jtfAnswer.setBounds(160, 300, 230, 25);
		
		jbtnOverlap.setBounds(300, 60, 90, 25);
		jbtnJoin.setBounds(290, 350, 100, 40);
		
		add(jlblId);
		add(jlblPass);
		add(jlblPassComfirm);
		add(jlblName);
		add(jlblPhone);
		add(jlblQuestion);
		add(jlblAnswer);
		
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
		
		
		UserJoinEvt uje=new UserJoinEvt(this);
		jtfId.addActionListener(uje);
		jpfPass.addActionListener(uje);
		jpfPassComfirm.addActionListener(uje);
		jtfName.addActionListener(uje);
		jcbNum.addActionListener(uje);
		jtfPhone2.addActionListener(uje);
		jtfPhone3.addActionListener(uje);
		jcbQuestion.addActionListener(uje);
		jtfAnswer.addActionListener(uje);
		jbtnOverlap.addActionListener(uje);
		jbtnJoin.addActionListener(uje);
		
		
		setResizable(false);
		setBounds(700, 260, 450, 470);
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




}//class
