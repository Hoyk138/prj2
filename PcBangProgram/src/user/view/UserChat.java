package user.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import user.controller.UserChatEvt;


public class UserChat extends JFrame{
	
	private JTextField jtfTalk;
	private JTextArea jtaChat;
	private JScrollPane jspChat;
	private JButton jbtSend;

	public UserChat() {
		super("사용자 채팅");
		
		//선언
		jtfTalk=new JTextField(25);
		jtaChat=new JTextArea("무엇을 도와드릴까요?");
		jbtSend=new JButton("보내기");
		jspChat=new JScrollPane(jtaChat);
		
		jtaChat.setEditable(false);
		jtaChat.setBackground(Color.white);
		
		//수동배치
		setLayout(null);
		jspChat.setBounds(15, 20, 300, 300);
		jtfTalk.setBounds(15, 330, 220, 40);
		jbtSend.setBounds(240, 330, 80, 40);
		
		//frame 추가
		add(jspChat);
		add(jtfTalk);
		add(jbtSend);
		
		setResizable(false);
		setBounds(100, 100, 350, 430);
		setVisible(true);
		
		//이벤트 처리
		UserChatEvt uce=new UserChatEvt(this);
		jbtSend.addActionListener(uce);
		jtfTalk.addActionListener(uce);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UserChat

	
	//getter
	public JTextField getJtfTalk() {
		return jtfTalk;
	}

	public JTextArea getJtaChat() {
		return jtaChat;
	}

	public JButton getJbtSend() {
		return jbtSend;
	}

	
	/**
	 * 단위 테스트용
	 * @param args
	 */
	public static void main(String[] args) {
		new UserChat();
	}//main
	
}//UserChat


