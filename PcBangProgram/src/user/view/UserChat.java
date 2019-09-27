package user.view;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
		this.um = um;
		this.pcNum = pcNum;
		
		//����
		JLabel jlLogo=new JLabel("�� E_ZO PC �� ī���Ϳ� �����ϱ�");
		jtfTalk=new JTextField(25);
		jtaChat=new JTextArea("������ ���͵帱���?");
		jbtSend=new JButton("������");
		jspChat=new JScrollPane(jtaChat);
		
		jtaChat.setEditable(false);
		jtaChat.setBackground(Color.white);
		
		jtaChat.setBorder(new TitledBorder(new  LineBorder(Color.black,2)));
		jtfTalk.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jbtSend.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		
		//������ġ
		setLayout(null);
		jlLogo.setBounds(20, 10, 500, 50);
		jspChat.setBounds(20, 50, 500, 250);
		jtfTalk.setBounds(20, 310, 400, 40);
		jbtSend.setBounds(425, 310, 95, 40);
		
		//frame �߰�
		add(jlLogo);
		add(jspChat);
		add(jtfTalk);
		add(jbtSend);
		
		//��Ʈ
		jlLogo.setFont(new Font("serif",Font.BOLD, 15));
		jlLogo.setForeground(new Color(0xF1C40F));
		jtaChat.setFont(new Font("serif",Font.PLAIN, 15));
		jtfTalk.setFont(new Font("serif",Font.PLAIN, 15));
		jbtSend.setBackground(new Color(0xF1C40F));
		getContentPane().setBackground(new Color(0x434446));
		
		setResizable(false);
		setBounds(1300, 450, 550, 400);
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
				try {
					dos.writeUTF("/ä������");
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
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

        jtaChat.setText("�����ڰ� ��� �Խ��ϴ�. ��ſ� ��ȭ �Ǽ���.\n");
//        jtaChat.append("��ȭ ���� :" + /* inputNick+ */"��("+ia.getHostAddress()+") �Դϴ�.");
		
    	if (disChat != null) {
			try {
				while (true) {
					jtaChat.append(/* inputNick+": " + */ disChat.readUTF() + "\n");
					scrollPosition();
				} // end while
			} catch(IOException ioe) {
				JOptionPane.showMessageDialog(this, "ä���� ���� ���� �Ǿ����ϴ�.");
				ioe.printStackTrace();
			}//end catch
		}//end if
		} catch(IOException ioe) {
			JOptionPane.showMessageDialog(this, "ä���� ���� ���� �Ǿ����ϴ�.");
			ioe.printStackTrace();
		}
    }//run
	
    /**
     * T.F���� �̺�Ʈ�� �߻����� �� T.F�� �Է°��� �����ͼ� ���Ͽ� ���
     */
    private void sendMsg() throws IOException{
        if (dosChat != null) {
        	StringBuilder msg = new StringBuilder();
        	msg
        	.append(um.getUserId()).append("��("+pcNum+"�� PC) : ")
        	.append(jtfTalk.getText().trim()).append("\n");
        	//�� ��ȭâ�� �Է� ���� ���� 
        	jtaChat.append(msg.toString());
        	scrollPosition();
        	//��Ʈ���� ���
        	dosChat.writeUTF(msg.toString());
			//��Ʈ���� ������ ������(����, client)���� �Է� ���� ������(���)
        	dosChat.flush();
			//T.F�� ������ �ʱ�ȭ
			jtfTalk.setText("");
			jtfTalk.requestFocus();
		} else {
			JOptionPane.showMessageDialog(this, "�����ڰ� ���� ������ �ʾҽ��ϴ�.");
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


