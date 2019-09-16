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
		super(pcs.getPcNum()+"�� PC");
		this.pcs = pcs;
		
		//����
		jtaChat=new JTextArea();
		jtaChat.setEditable(false);
		jtaChat.setBackground(Color.white);
		jspChat=new JScrollPane(jtaChat);
		jspChat.setBorder(new TitledBorder("ä��"));

		jtfTalk=new JTextField(23);
		jbtSend=new JButton("������");
		JPanel jpSouth = new JPanel();
		jpSouth.add(jtfTalk);
		jpSouth.add(jbtSend);
		
		add("Center",jspChat);
		add("South",jpSouth);
		
		setResizable(false);
		setBounds(100, 100, 360, 420);
		setVisible(true);
		
//		//�̺�Ʈ ó��
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
//		super("������-ä��");
//		
//		jtaChat = new JTextArea();
//		JScrollPane jspChat = new JScrollPane(jtaChat);
//		
//		JPanel jpSouth = new JPanel();
//		jtfChatMSG = new JTextField(30);
//		jbtnSend = new JButton("����");
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
//	 * ���� �׽�Ʈ��
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		new AdminChat(0);
//	}//main
	
}//class
