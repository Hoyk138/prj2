package admin.view;

import java.awt.Color;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import admin.controller.AdminChatEvt;
import admin.controller.PCStateEvt;

@SuppressWarnings("serial")
public class AdminChat extends JDialog{

	private PCState pcs;
	private PCStateEvt pcse;
	
	private JTextArea jtaChat;
	private JScrollPane jspChat;
	private JTextField jtfTalk;
	private JButton jbtSend;
	
	private DataInputStream dis;
	private DataOutputStream dos;

	public AdminChat(PCState pcs, PCStateEvt pcse) {
		super(pcs.getMv(), pcs.getPcNum()+"번 PC",false);
		this.pcs = pcs;
		this.pcse = pcse;
		
		this.dis = pcs.getDis();
		this.dos = pcs.getDos();
		
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
		
		jtaChat.setBorder(new TitledBorder(new  LineBorder(Color.black,2)));
		jtfTalk.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jbtSend.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		
		jtaChat.setFont(new Font("serif",Font.PLAIN, 15));
		jtfTalk.setFont(new Font("serif",Font.PLAIN, 15));
		jbtSend.setBackground(new Color(0xF1C40F));
		getContentPane().setBackground(new Color(0x434446));
		
		setResizable(false);
		setBounds(100, 100, 360, 420);
		setVisible(true);

		//이벤트 처리
		System.out.println("this: "+this);
		AdminChatEvt ace = new AdminChatEvt(this);
		jtfTalk.addActionListener(ace);
		jbtSend.addActionListener(ace);
		
		addWindowListener(ace);
		
//		//이벤트 처리
//		AdminChatEvt ace = new AdminChatEvt(this);
//		jtfTalk.addActionListener(ace);
//		jbtSend.addActionListener(ace);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//AdimChat
	
	public PCState getPcs() {
		return pcs;
	}
	
	public PCStateEvt getPcse() {
		return pcse;
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
	
	public DataInputStream getDis() {
		return dis;
	}

	public DataOutputStream getDos() {
		return dos;
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
