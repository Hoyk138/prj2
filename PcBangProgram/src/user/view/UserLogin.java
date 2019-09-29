package user.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.controller.UserLoginEvt;

@SuppressWarnings("serial")
public class UserLogin extends JFrame {
	
	private JLabel jlbId, jlbPass;
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JButton jbtnLogin, jbtnID, jbtnPass, jbtnJoin;
	
	public UserLogin(String adminIP, int pcNum) {
		super("사용자 로그인 ["+pcNum+"번 PC]");
		
		ImageIcon loginBackground=new ImageIcon("C:/dev/pcbang/user/img/design/login_img4.png");
		ImageIcon loginButton=new ImageIcon("C:/dev/pcbang/user/img/design/start_button.png");
		ImageIcon loginButton2=new ImageIcon("C:/dev/pcbang/user/img/design/start_button2.png");
		ImageIcon joinButton=new ImageIcon("C:/dev/pcbang/user/img/design/join_button.png");
		ImageIcon joinButton2=new ImageIcon("C:/dev/pcbang/user/img/design/join_button2.png");
		ImageIcon idButton=new ImageIcon("C:/dev/pcbang/user/img/design/id_button.png");
		ImageIcon idButton2=new ImageIcon("C:/dev/pcbang/user/img/design/id_button2.png");
		ImageIcon passButton=new ImageIcon("C:/dev/pcbang/user/img/design/pw_button.png");
		ImageIcon passButton2=new ImageIcon("C:/dev/pcbang/user/img/design/pw_button2.png");
		
		
        JLabel jlBack=new JLabel(loginBackground);
		jlbId=new JLabel("아이디");
		jlbPass=new JLabel("비밀번호");
        
		jtfId=new JTextField(20);
		jpfPass=new JPasswordField(20);
		
		jbtnLogin=new JButton(loginButton);
		jbtnJoin=new JButton(joinButton);
		jbtnID=new JButton(idButton);
		jbtnPass=new JButton(passButton);
		
		//Rollover : 마우스가 올라갔을 때 다른 이미지로 변경하는 것
		jbtnLogin.setRolloverIcon(loginButton2);
		jbtnJoin.setRolloverIcon(joinButton2);
		jbtnID.setRolloverIcon(idButton2);
		jbtnPass.setRolloverIcon(passButton2);
		
		setLayout(null);
		
		jlBack.setBounds(0, 0, 416, 298);
		jlbId.setBounds(40, 60, 76, 30);
		jlbPass.setBounds(40, 100, 76, 30);
        jtfId.setBounds(142, 165, 133, 24);
        jpfPass.setBounds(142, 203, 133, 24);
		jbtnLogin.setBounds(296, 146, 96, 93);  
		jbtnJoin.setBounds(14, 260, 119, 30);
		jbtnID.setBounds(140, 260, 118, 30);
		jbtnPass.setBounds(263, 260, 143, 30);
		
		jtfId.setBackground(new Color(0x1C1C1C));
		jpfPass.setBackground(new Color(0x1C1C1C));
		jtfId.setForeground(new Color(0xC2C2C2)); //jtf입력글자컬러바꾸기
		jpfPass.setForeground(new Color(0xC2C2C2));
		jtfId.setCaretColor(new Color(0xC2C2C2)); //커서컬러바꾸기
		jpfPass.setCaretColor(new Color(0xC2C2C2));
		jtfId.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //jtf 테두리 없애기
		jpfPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jbtnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());//버튼테두리 없애기
		jbtnJoin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jbtnID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jbtnPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	
		add(jtfId);
		add(jpfPass);
		add(jbtnLogin);
		add(jbtnID);
		add(jbtnPass);
		add(jbtnJoin);
		add(jlBack);
		add(jlbId);
		add(jlbPass);
		
		UserLoginEvt ule=new UserLoginEvt(this, adminIP, pcNum);
		
		jtfId.addActionListener(ule);
		jpfPass.addActionListener(ule);
		jbtnLogin.addActionListener(ule);
		jbtnID.addActionListener(ule);
		jbtnPass.addActionListener(ule);
		jbtnJoin.addActionListener(ule);
		
		//Jframe 열릴 때 jtfId에 커서 두기
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent we) {
				jtfId.requestFocus();
			}
		});
		
		setResizable(false);
		setBounds(1470, 660, 416, 327);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//LoginUser		

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public JButton getJbtnID() {
		return jbtnID;
	}

	public JButton getJbtnPass() {
		return jbtnPass;
	}

	public JButton getJbtnJoin() {
		return jbtnJoin;
	}
	
	
	
			

}//class
