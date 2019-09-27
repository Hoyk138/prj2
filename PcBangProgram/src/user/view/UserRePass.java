package user.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.FindPassEvt;
import user.controller.UserRePassEvt;

@SuppressWarnings("serial")
public class UserRePass extends JDialog {
	
	private JLabel jlblId, jlblPass, jlblConfirm, jlblId2;
//	private JTextField jtfId;
	private JPasswordField jpfPass, jpfConfirm;
	private JButton jbtnResetPass;
	
	private FindPass fp;
	
	public UserRePass(FindPass fp) {
		
		this.fp=fp;
//		super("ID 찾기");
		
		ImageIcon rePassBackground=new ImageIcon("C:/dev/pcbang/user/img/design/reset_pw_backimg.png");
		ImageIcon rePassButton1=new ImageIcon("C:/dev/pcbang/user/img/design/reset_pw_button1.png");
		ImageIcon rePassButton2=new ImageIcon("C:/dev/pcbang/user/img/design/reset_pw_button2.png");
		
		JLabel rpBack=new JLabel(rePassBackground);
		
		jlblId=new JLabel("아이디");
		jlblPass=new JLabel("비밀번호");
		jlblConfirm=new JLabel("비밀번호확인");
		
		jlblId2=new JLabel(fp.getJtfId().getText()); 
		jpfPass=new JPasswordField();
		jpfConfirm=new JPasswordField(); 
		
		jbtnResetPass=new JButton(rePassButton1);
		jbtnResetPass.setRolloverIcon(rePassButton2);
		
		setLayout(null);
		
		
		rpBack.setBounds(0, 0, 517, 396);
		jlblId.setBounds(40, 30, 76, 30);
		jlblPass.setBounds(40, 70, 76, 30);
		jlblConfirm.setBounds(40, 110, 100, 30);
		jlblId2.setBounds(177, 95, 200, 25); 
		
		jpfPass.setBounds(177, 142, 270, 30);
		jpfConfirm.setBounds(177, 190, 270, 30);
		jbtnResetPass.setBounds(153, 266, 193, 35);
		
		
		jpfPass.setCaretColor(new Color(0xC2C2C2)); //커서컬러바꾸기
		jpfConfirm.setCaretColor(new Color(0xC2C2C2));
		
		jpfPass.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //jtf 테두리 없애기
		jpfConfirm.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		jbtnResetPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());//버튼테두리 없애기
		
		
		add(jlblId2); /////아이디고정
		add(jpfPass);
		add(jpfConfirm);
		add(jbtnResetPass);
		add(rpBack);
		add(jlblId);
		add(jlblPass);
		add(jlblConfirm); 
		
		UserRePassEvt urpe=new UserRePassEvt(this);
		
//		jtfId.addActionListener(urpe);
		jpfPass.addActionListener(urpe);
		jpfConfirm.addActionListener(urpe);
		jbtnResetPass.addActionListener(urpe);
		
		setResizable(false);
		setBounds(700, 400, 517, 376);
		setVisible(true);
//		fp.setVisible(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UserRePass

	public JLabel getJlblId() {
		return jlblId;
	}

	public JLabel getJlblPass() {
		return jlblPass;
	}

	public JLabel getJlblConfirm() {
		return jlblConfirm;
	}

	public JLabel getJlblId2() {
		return jlblId2;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JPasswordField getJpfConfirm() {
		return jpfConfirm;
	}

	public JButton getJbtnResetPass() {
		return jbtnResetPass;
	}

	public FindPass getFp() {
		return fp;
	}



}//class
