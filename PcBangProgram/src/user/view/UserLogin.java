package user.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.UserLoginEvt;

public class UserLogin extends JFrame {
	
	private JLabel jlbId, jlbPass;
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JButton jbtnLogin, jbtnID, jbtnPass, jbtnJoin;
	
	public UserLogin() {
		super("����� �α���");

		jlbId=new JLabel("���̵�");
		jlbPass=new JLabel("��й�ȣ");
		
		jtfId=new JTextField(20);
		jpfPass=new JPasswordField(20);
		
		
		jbtnLogin=new JButton("������");
		jbtnID=new JButton("IDã��");
		jbtnPass=new JButton("PWã��");
		jbtnJoin=new JButton("ȸ������");
		
		setLayout(null);
		
		jlbId.setBounds(40, 60, 76, 30);
		jlbPass.setBounds(40, 100, 76, 30);
        jtfId.setBounds(100, 60, 200, 25);
        jpfPass.setBounds(100, 100, 200, 25);
		jbtnLogin.setBounds(320, 50, 90, 90);
		jbtnID.setBounds(90, 170, 100, 30);
		jbtnPass.setBounds(200, 170, 100, 30);
		jbtnJoin.setBounds(310, 170, 100, 30);
			
		add(jlbId);
		add(jlbPass);
		add(jtfId);
		add(jpfPass);
		add(jbtnLogin);
		add(jbtnID);
		add(jbtnPass);
		add(jbtnJoin);
		
		UserLoginEvt ule=new UserLoginEvt(this);
		
		jtfId.addActionListener(ule);
		jpfPass.addActionListener(ule);
		jbtnLogin.addActionListener(ule);
		jbtnID.addActionListener(ule);
		jbtnPass.addActionListener(ule);
		jbtnJoin.addActionListener(ule);
		
		setResizable(false);
		setBounds(300, 100, 450, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//LoginUser		
			

}//class
