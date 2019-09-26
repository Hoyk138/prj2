package user.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import user.controller.UserChatEvt;

@SuppressWarnings("serial")
public class UserChat extends JDialog implements ActionListener, Runnable{
	
	private UserMain um;
	private int pcNum;

	private JTextField jtfTalk;
	private JTextArea jtaChat;
	private JScrollPane jspChat;
	private JButton jbtSend;
	
	private ServerSocket server;
	private Socket client;
	private DataInputStream disChat;
	private DataOutputStream dosChat;
	
	private Thread thread;

	//public UserChat(UserMain um) {
	public UserChat(UserMain um, int pcNum, DataOutputStream dos) {
		super(um,"����� ä��",false);
		this.pcNum = pcNum;
		//����
		jtaChat=new JTextArea();
		jspChat=new JScrollPane(jtaChat);
		jtfTalk=new JTextField(25);
		jbtSend=new JButton("������");
		
		jtaChat.setEditable(false);
		jtaChat.setBackground(Color.white);
		
		//������ġ
		setLayout(null);
		jspChat.setBounds(15, 20, 300, 300);
		jtfTalk.setBounds(15, 330, 220, 40);
		jbtSend.setBounds(240, 330, 80, 40);
		
		//frame �߰�
		add(jspChat);
		add(jtfTalk);
		add(jbtSend);
		
		setResizable(false);
		setBounds(100, 100, 350, 430);
		setVisible(true);
		
		//ä�� ���� ����
		thread = new Thread(this);
		thread.start();//�޼����� ���ÿ� �о� ���δ�.
		
		//�̺�Ʈ ó��
//		UserChatEvt uce=new UserChatEvt(this);
//		jbtSend.addActionListener(uce);
//		jtfTalk.addActionListener(uce);
		jbtSend.addActionListener(this);
		jtfTalk.addActionListener(this);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//default close operation�� ���ϰ� ��Ʈ���� ���� ���Ѵ�.
    	addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
//				try {
//					close();
//				} catch (IOException ioe) {
//					ioe.printStackTrace();
//				}//end catch
//				try {
//					dos.writeUTF("/ä������");
//					dos.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				dispose();
			}//windowClosing
			
			@Override
			public void windowClosed(WindowEvent e) {
				thread=null;
				try {
				if(disChat!=null) {disChat.close();}//if
				if(dosChat!=null) {dosChat.close();}//if
				if(client!=null) {client.close();}//if
				if(server!=null) {server.close();}//if
				}catch(IOException ie) {
					ie.printStackTrace();
				}
			}//windowClosed
			
		});
    	
	}//UserChat

	 /**
     * ��ȭ�� ������ ���� ������ �о�鿩 T.A�� ���
     */
	@Override
    public void run() {
		try {
//		nick = JOptionPane.showInputDialog("��ȭ�� �Է�: ");
		
		//�������� ����
//		@SuppressWarnings("resource")
//    	ServerSocket server = new ServerSocket(9000);
		server = new ServerSocket(9001);
//		JTextArea jtaDisplay = uc.getJtaChat();
		jtaChat.getText();
		jtaChat.setText("���� ���� ���Դϴ�.\n");//bug�� �߻��ϸ� getText �� �� ������
    	//�����ڼ����� �ް�
    	client = server.accept();//���ŷ method

    	//���� ��Ʈ�� ����
    	dosChat = new DataOutputStream(client.getOutputStream());
    	//�б� ��Ʈ�� ����
    	disChat = new DataInputStream(client.getInputStream());

    	//�� ��ȭ���� ���濡�� ����
//    	dosWrite.writeUTF(nick);//�Է� ���� ���� Ŭ���̾�Ʈ�� ����
//    	dosWrite.flush();
    	
    	//������ ��ȭ���� �ޱ�
//        inputNick =disRead.readUTF();
        
//        InetAddress ia = client.getInetAddress();//�������� ���������� ���� �� �ִ�.

        jtaChat.setText("��ȭ ��밡 ��� ���̽��ϴ�. ��ſ� ��ȭ �Ǽ���.\n");
//        jtaChat.append("��ȭ ���� :" + /* inputNick+ */"��("+ia.getHostAddress()+") �Դϴ�.");
		
    	if (disChat != null) {
			try {
				while (true) {
					jtaChat.append(/* inputNick+": " + */ disChat.readUTF() + "\n");
					scrollPosition();
				} // end while
			} catch(IOException ioe) {
				JOptionPane.showMessageDialog(this, "�����ڰ� ä���� ���� �Ͽ����ϴ�.");
				ioe.printStackTrace();
			}//end catch
		}//end if
		} catch(IOException ioe) {
			JOptionPane.showMessageDialog(this, "�����ڰ� ä���� ���� �Ͽ����ϴ�.");
			ioe.printStackTrace();
		}
    }//run
	
    /**
     * T.F���� �̺�Ʈ�� �߻����� �� T.F�� �Է°��� �����ͼ� ���Ͽ� ���
     */
    private void sendMsg() throws IOException{
        if (dosChat != null) {
        	String msg = jtfTalk.getText().trim();
        	//�� ��ȭâ�� �Է� ���� ���� 
        	jtaChat.append(pcNum+"�� : "+msg+"\n");
        	scrollPosition();
        	//��Ʈ���� ���
        	dosChat.writeUTF(msg);
			//��Ʈ���� ������ ������(����, client)���� �Է� ���� ������(���)
        	dosChat.flush();
			//T.F�� ������ �ʱ�ȭ
			jtfTalk.setText("");
		} else {
			JOptionPane.showMessageDialog(this, "��ȭ��밡 �����ϴ�.");
		}//end else
	}//sendMsg
	
    private void scrollPosition() {
    	jspChat.getVerticalScrollBar().setValue(jspChat.getVerticalScrollBar().getMaximum());
    }//scrollPosition
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			sendMsg();
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "��ȭ��밡 �����̽��ϴ�.");
			ioe.printStackTrace();
		}//end catch
	}//actionPerformed
    
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
	
	public JScrollPane getJspChat() {
		return jspChat;
	}

//	/**
//	 * ���� �׽�Ʈ��
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		new UserChat();
//	}//main
	
}//UserChat


