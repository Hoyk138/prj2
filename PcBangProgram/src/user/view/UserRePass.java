package user.view;

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
		
		jlblId=new JLabel("아이디");
		jlblPass=new JLabel("비밀번호");
		jlblConfirm=new JLabel("비밀번호확인");
		
		jlblId2=new JLabel(fp.getJtfId().getText()); 
		jpfPass=new JPasswordField();
		jpfConfirm=new JPasswordField(); 
		
		jbtnResetPass=new JButton("PW재설정");
		
		setLayout(null);
		
		
		jlblId.setBounds(40, 30, 76, 30);
		jlblPass.setBounds(40, 70, 76, 30);
		jlblConfirm.setBounds(40, 110, 100, 30);
		jlblId2.setBounds(170, 30, 200, 25);
		jpfPass.setBounds(170, 70, 200, 25);
		jpfConfirm.setBounds(170, 110, 200, 25);
		jbtnResetPass.setBounds(270, 150, 100, 30);
		
		add(jlblId);
		add(jlblPass);
		add(jlblConfirm);
		add(jlblId2);
		add(jpfPass);
		add(jpfConfirm);
		add(jbtnResetPass);
		
		UserRePassEvt urpe=new UserRePassEvt(this);
		
//		jtfId.addActionListener(urpe);
		jpfPass.addActionListener(urpe);
		jpfConfirm.addActionListener(urpe);
		jbtnResetPass.addActionListener(urpe);
		
		setResizable(false);
		setBounds(700, 400, 450, 250);
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
