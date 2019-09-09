package min.pcUserView;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import min.pcUserEvt.UserRePassEvt;

@SuppressWarnings("serial")
public class UserRePass extends JFrame {
	
	private JLabel jlblPass, jlblConfirm;
	private JTextField jtfPass, jtfConfirm;
	private JButton jbtnResetPass;
	
	public UserRePass() {
		super("ID 찾기");
		
		jlblPass=new JLabel("비밀번호");
		jlblConfirm=new JLabel("확인");
		
		jtfPass=new JTextField();
		jtfConfirm=new JTextField();
		
		jbtnResetPass=new JButton("PW재설정");

		
		setLayout(null);
		
		jlblPass.setBounds(40, 60, 76, 30);
		jlblConfirm.setBounds(40, 100, 76, 30);
		jtfPass.setBounds(150, 60, 200, 25);
		jtfConfirm.setBounds(150, 100, 200, 25);
		jbtnResetPass.setBounds(250, 150, 100, 30);
		
		add(jlblPass);
		add(jlblConfirm);
		add(jtfPass);
		add(jtfConfirm);
		add(jbtnResetPass);
		
		UserRePassEvt urpe=new UserRePassEvt(this);

		jtfPass.addActionListener(urpe);
		jtfConfirm.addActionListener(urpe);
		jbtnResetPass.addActionListener(urpe);
		
		setResizable(false);
		setBounds(100, 200, 450, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UserRePass
	

	public static void main(String[] args) {
		new UserRePass();
	}//main

}//class
