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
		super(um,"사용자 채팅",false);
		this.um = um;
		this.pcNum = pcNum;
		
		//선언
		JLabel jlLogo=new JLabel("▒ E_ZO PC ▒ 카운터에 문의하기");
		jtfTalk=new JTextField(25);
		jtaChat=new JTextArea("무엇을 도와드릴까요?");
		jbtSend=new JButton("보내기");
		jspChat=new JScrollPane(jtaChat);
		
		jtaChat.setEditable(false);
		jtaChat.setBackground(Color.white);
		
		jtaChat.setBorder(new TitledBorder(new  LineBorder(Color.black,2)));
		jtfTalk.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jbtSend.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		
		//수동배치
		setLayout(null);
		jlLogo.setBounds(20, 10, 500, 50);
		jspChat.setBounds(20, 50, 500, 250);
		jtfTalk.setBounds(20, 310, 400, 40);
		jbtSend.setBounds(425, 310, 95, 40);
		
		//frame 추가
		add(jlLogo);
		add(jspChat);
		add(jtfTalk);
		add(jbtSend);
		
		//폰트
		jlLogo.setFont(new Font("serif",Font.BOLD, 15));
		jlLogo.setForeground(new Color(0xF1C40F));
		jtaChat.setFont(new Font("serif",Font.PLAIN, 15));
		jtfTalk.setFont(new Font("serif",Font.PLAIN, 15));
		jbtSend.setBackground(new Color(0xF1C40F));
		getContentPane().setBackground(new Color(0x434446));
		
		setResizable(false);
		setBounds(1300, 450, 550, 400);
		setVisible(true);
		
		//채팅 서버 오픈
		thread = new Thread(this);
		thread.start();//메세지를 동시에 읽어 들인다.
		
		//이벤트 처리
//		UserChatEvt uce=new UserChatEvt(this);
//		jbtSend.addActionListener(uce);
//		jtfTalk.addActionListener(uce);
		jbtSend.addActionListener(this);
		jtfTalk.addActionListener(this);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//default close operation은 소켓과 스트림을 끊지 못한다.
    	addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
//				try {
//					close();
//				} catch (IOException ioe) {
//					ioe.printStackTrace();
//				}//end catch
				try {
					dos.writeUTF("/채팅종료");
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
     * 대화의 내용을 무한 루프로 읽어들여 T.A에 출력
     */
	@Override
    public void run() {
		try {
//		nick = JOptionPane.showInputDialog("대화명 입력: ");
		
		//서버소켓 생성
//		@SuppressWarnings("resource")
//    	ServerSocket server = new ServerSocket(9000);
		server = new ServerSocket(9001);
//		JTextArea jtaDisplay = uc.getJtaChat();
		jtaChat.getText();
		jtaChat.setText("서버 가동 중입니다.\n");//bug가 발생하면 getText 한 번 해주자
    	//접속자소켓을 받고
    	client = server.accept();//블로킹 method

    	//쓰기 스트림 연결
    	dosChat = new DataOutputStream(client.getOutputStream());
    	//읽기 스트림 연결
    	disChat = new DataInputStream(client.getInputStream());

    	//내 대화명을 상대방에게 전송
//    	dosWrite.writeUTF(nick);//입력 받은 닉을 클라이언트로 전송
//    	dosWrite.flush();
    	
    	//상대방의 대화명을 받기
//        inputNick =disRead.readUTF();
        
//        InetAddress ia = client.getInetAddress();//접속자의 여러정보를 얻을 수 있다.

        jtaChat.setText("관리자가 들어 왔습니다. 즐거운 대화 되세요.\n");
//        jtaChat.append("대화 상대는 :" + /* inputNick+ */"님("+ia.getHostAddress()+") 입니다.");
		
    	if (disChat != null) {
			try {
				while (true) {
					jtaChat.append(/* inputNick+": " + */ disChat.readUTF() + "\n");
					scrollPosition();
				} // end while
			} catch(IOException ioe) {
				JOptionPane.showMessageDialog(this, "채팅이 종료 종료 되었습니다.");
				ioe.printStackTrace();
			}//end catch
		}//end if
		} catch(IOException ioe) {
			JOptionPane.showMessageDialog(this, "채팅이 종료 종료 되었습니다.");
			ioe.printStackTrace();
		}
    }//run
	
    /**
     * T.F에서 이벤트가 발생했을 때 T.F에 입력값을 가져와서 소켓에 출력
     */
    private void sendMsg() throws IOException{
        if (dosChat != null) {
        	StringBuilder msg = new StringBuilder();
        	msg
        	.append(um.getUserId()).append("님("+pcNum+"번 PC) : ")
        	.append(jtfTalk.getText().trim()).append("\n");
        	//내 대화창에 입력 내용 쓰기 
        	jtaChat.append(msg.toString());
        	scrollPosition();
        	//스트림에 출력
        	dosChat.writeUTF(msg.toString());
			//스트림의 내용을 목적지(소켓, client)에게 입력 내용 보내기(출력)
        	dosChat.flush();
			//T.F의 내용을 초기화
			jtfTalk.setText("");
			jtfTalk.requestFocus();
		} else {
			JOptionPane.showMessageDialog(this, "관리자가 아직 들어오지 않았습니다.");
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
			JOptionPane.showMessageDialog(this, "대화상대가 나가셨습니다.");
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
//	 * 단위 테스트용
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		new UserChat();
//	}//main
	
}//UserChat


