package admin.view;

import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AdminChat extends JFrame{

	private JTextArea jtaChat;
	private JTextField jtfChatMSG;
	private JButton jbtnSend;
	
	public AdminChat() {
		super("관리자-채팅");
		
		jtaChat = new JTextArea();
		JScrollPane jspChat = new JScrollPane(jtaChat);
		
		jtfChatMSG = new JTextField();
		jbtnSend = new JButton();
		
		setBounds(100, 100, 500, 600);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}//windowClosing
		});
	}
	
}
