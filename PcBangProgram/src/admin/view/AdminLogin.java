package admin.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.controller.AdminLoginEvt;

@SuppressWarnings("serial")
public class AdminLogin extends JFrame {
	
	private JLabel jlblId, jlblPass;
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JButton jbtnLogin;
	

	public AdminLogin() {
		super("관리자 로그인");
		
		jlblId=new JLabel("아이디");
		jlblPass=new JLabel("비밀번호");
		
		jtfId=new JTextField();
		jpfPass=new JPasswordField();
		
		jbtnLogin=new JButton("로그인");
		
		setLayout(null);
		
		jlblId.setBounds(40, 60, 76, 30);
		jlblPass.setBounds(40, 100, 76, 30);
		jtfId.setBounds(110, 65, 200, 25);
		jpfPass.setBounds(110, 105, 200, 25);
		jbtnLogin.setBounds(330, 50, 90, 90);
		
		add(jlblId);
		add(jlblPass);
		add(jtfId);
		add(jpfPass);
		add(jbtnLogin);

		AdminLoginEvt ale=new AdminLoginEvt(this);
		
		jtfId.addActionListener(ale);
		jpfPass.addActionListener(ale);
		jbtnLogin.addActionListener(ale);
		
		setResizable(false);
		setBounds(100, 200, 450, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//AdminLogin




	public JTextField getJtfId() {
		return jtfId;
	}


	public JPasswordField getJpfPass() {
		return jpfPass;
	}


	public JButton getJbtnLogin() {
		return jbtnLogin;
	}


}//class
