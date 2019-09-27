package admin.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
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
		super("������ �α���");
		
		ImageIcon loginBackground=new ImageIcon("C:/dev/pcbang/admin/img/design/adminlogin_img.png");
		ImageIcon loginButton=new ImageIcon("C:/dev/pcbang/admin/img/design/adminlogin_button.png");
		ImageIcon loginButton2=new ImageIcon("C:/dev/pcbang/admin/img/design/adminlogin_button2.png");
		
        JLabel jlBack=new JLabel(loginBackground);
		jlblId=new JLabel("���̵�");
		jlblPass=new JLabel("��й�ȣ");
		
		jtfId=new JTextField();
		jpfPass=new JPasswordField();
		
		jbtnLogin=new JButton(loginButton);
		jbtnLogin.setRolloverIcon(loginButton2);
		
		
		setLayout(null);
		
		jlBack.setBounds(0, 0, 415, 169);
		jlblId.setBounds(40, 60, 76, 30);
		jlblPass.setBounds(40, 100, 76, 30);
		jtfId.setBounds(141, 68, 133, 24);
		jpfPass.setBounds(141, 106, 133, 24);
		jbtnLogin.setBounds(294, 49, 99, 96);
		
		jtfId.setBackground(new Color(0x1C1C1C));
		jpfPass.setBackground(new Color(0x1C1C1C));
		jtfId.setForeground(new Color(0xC2C2C2)); //jtf�Է±����÷��ٲٱ�
		jpfPass.setForeground(new Color(0xC2C2C2));
		jtfId.setCaretColor(new Color(0xC2C2C2)); //Ŀ���÷��ٲٱ�
		jpfPass.setCaretColor(new Color(0xC2C2C2));
		jtfId.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //jtf �׵θ� ���ֱ�
		jpfPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jbtnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());//��ư�׵θ� ���ֱ�
		
		
		add(jtfId);
		add(jpfPass);
		add(jbtnLogin);
		add(jlBack);
		add(jlblId);
		add(jlblPass);

		AdminLoginEvt ale=new AdminLoginEvt(this);
		
		jtfId.addActionListener(ale);
		jpfPass.addActionListener(ale);
		jbtnLogin.addActionListener(ale);
		
		//Jframe ���� �� jtfId�� Ŀ�� �α�
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent we) {
				jtfId.requestFocus();
			}
		});
		
		setResizable(false);
		setBounds(1500, 843, 421, 198);
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
