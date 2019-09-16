package admin.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AdminChat extends JFrame{

	private PCState pcs;
	
	private JTextArea jtaChat;
	private JScrollPane jspChat;
	private JTextField jtfTalk;
	private JButton jbtSend;

	public AdminChat(PCState pcs) {
		super(pcs.getPcNum()+"번 PC");
		this.pcs = pcs;
		
		//선언
		jtaChat=new JTextArea();
		jtaChat.setEditable(false);
		jtaChat.setBackground(Color.white);
		jspChat=new JScrollPane(jtaChat);
		jspChat.setBorder(new TitledBorder("채팅"));

		jtfTalk=new JTextField(23);
		jbtSend=new JButton("보내기");
		JPanel jpSouth = new JPanel();
		jpSouth.add(jtfTalk);
		jpSouth.add(jbtSend);
		
		add("Center",jspChat);
		add("South",jpSouth);
		
		setResizable(false);
		setBounds(100, 100, 360, 420);
		setVisible(true);
		
//		//이벤트 처리
//		AdminChatEvt ace = new AdminChatEvt(this);
//		jtfTalk.addActionListener(ace);
//		jbtSend.addActionListener(ace);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//AdimChat
	
	public PCState getPcs() {
		return pcs;
	}

	public JTextArea getJtaChat() {
		return jtaChat;
	}

	public JScrollPane getJspChat() {
		return jspChat;
	}

	public JTextField getJtfTalk() {
		return jtfTalk;
	}

	public JButton getJbtSend() {
		return jbtSend;
	}
	
//	public AdminChat() {
//		super("관리자-채팅");
//		
//		jtaChat = new JTextArea();
//		JScrollPane jspChat = new JScrollPane(jtaChat);
//		
//		JPanel jpSouth = new JPanel();
//		jtfChatMSG = new JTextField(30);
//		jbtnSend = new JButton("전송");
//		jpSouth.add(jtfChatMSG);
//		jpSouth.add(jbtnSend);
//		
//		setLayout(new BorderLayout());
//		add("Center",jspChat);
//		add("South",jpSouth);
//		
//		setBounds(100, 100, 500, 600);
//		setVisible(true);
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent we) {
//				dispose();
//			}//windowClosing
//		});
//	}//AdminChat
	
//	/**
//	 * 단위 테스트용
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		new AdminChat(0);
//	}//main
	
}//class
