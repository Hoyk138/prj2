package admin.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import admin.view.AdminChat;

public class AdminChatEvt extends WindowAdapter implements ActionListener, Runnable {

	private AdminChat ac;
//	private UserMain um;

//	private Socket socketUser;
//	private DataInputStream readStream;
//	private DataOutputStream writeStream;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Thread thConnect;

	private Socket socket;
	private DataInputStream disRead;
	private DataOutputStream dosWrite;
	
	public AdminChatEvt(AdminChat ac) {
		this.ac = ac;
		// 읽기스트림 연결
		dis = ac.getDis();
		// 쓰기스트림 연결
		dos = ac.getDos();
		System.out.println("관리자 채팅 이벤트: "+dis);
		System.out.println("관리자 채팅 이벤트: "+dos);
		
		try {
			connectToServer();
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}//end catch
		
		//사용자가 보내는 메세지를 읽어들이는 run 실행
		thConnect = new Thread(this);
		thConnect.start();
		System.out.println("스레드 스타트");
		
	}// UserChatEvt

	/**
	 * 소켓 생성과 서버와 연결하여 스트림 연결 채팅 무한loop로 읽어들이는 일
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void connectToServer() throws UnknownHostException, IOException {

		try {
			// 소켓을 생성
//			String ip =JOptionPane.showInputDialog("서버의 ip Address입력\n"+"130,132,133,134,135,137,138,140,141,142,143,144,155,147,148,149,150,159,152,153");
			// localhost, 127.0.0.1, 자신의 ip를 넣으면 내 컴퓨터에 존재하는 서버에 접속한다.
//			client = new Socket("localhost", 55555);
			socket = new Socket("localhost", 9001);//단위 테스트 할 때는 자신의 IP로, 실제 상황에는 관리자 프로그램이 켜져있는 IP로
//			socket = new Socket("211.63.89.133", 9001);

			// 읽기 스트림 연결
			disRead = new DataInputStream(socket.getInputStream());
			// 쓰기 스트림 연결
			dosWrite = new DataOutputStream(socket.getOutputStream());
			
            //내 별명을 인스턴스 변수에 저장
//			this.nick = nick;//입력 대화명을 어디에서든 사용할 수 있도록 인스턴스변수에
//			
//			//상대방의 대화명을 받기
//			inputNick = disRead.readUTF();
//
//			//내 대화명을 상대방에게 전송.
//			dosWrite.writeUTF(nick);
//			dosWrite.flush();
			
//			jtaDisplay.append(/* inputNick+ */"님의 대화 서버에 들어 오셨습니다. 즐거운 대화 나누세요.\n");
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} // end catch
		
//			// pc번호 받아와서 서버로 전달
////			String pcNum = um.getPcNum().trim() + "번";
////			writeStream.writeUTF(pcNum);
////			writeStream.flush();
//
//			//
//			thConnect = new Thread(this);
//			// 호출
//			thConnect.start();
//
//			uc.getJtaChat().setText("EZO PC방 입니다.v^v^v 무엇을 도와드릴까요?\n");
	}// connectToServer

	/**
	 * 사용자의 메세지를 무한루프로 읽어 T.A에 출력하는 일
	 */
	@Override
	public void run() {
		// 보내는 메세지를 무한루프로 읽어들이기
		if (disRead != null) {
			try {
				System.out.println("스레드 런");
				while (true) {
					System.out.println("스레드 런 while");
					System.out.println(dis);
					System.out.println(ac);
					System.out.println(ac.getJtaChat());
					ac.getJtaChat().append(disRead.readUTF() + "\n");
					scrollPosition();
				} // end while
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(ac, "사용자께서 채팅을 나가셨습니다.");
				ac.getPcs().setBackgrounColor(Color.LIGHT_GRAY);
				ac.getPcs().setBackground(ac.getPcs().getBackgrounColor());
				ie.printStackTrace();
			} // end catch
		} // end if
	}// run

	/**
	 * T.F에 입력값을 가져와 소켓으로 분출하는일
	 * 
	 * @throws IOException
	 */
	private void sendMsg() throws IOException {
		// 스트림 연결시
		if (dosWrite != null) {
			String msg = "관리자: " + ac.getJtfTalk().getText().trim();
			ac.getJtaChat().append(msg + "\n");

			// 소켓 분출
			dosWrite.writeUTF(msg);
			dosWrite.flush();

			// T.F 초기화
			ac.getJtfTalk().setText("");
			scrollPosition();
		} else {
			JOptionPane.showMessageDialog(ac, "스트림의 문제발생!\n카운터에 문의해주세요.");
		} // end else
	}// sendMsg

	/**
	 * 채팅에 스크롤을 사용자 위치로 보내는 일
	 */
	private void scrollPosition() {
		ac.getJspChat().getVerticalScrollBar().setValue(ac.getJspChat().getVerticalScrollBar().getMaximum());
	}// scrollPosition

//	/**
//	 * 소켓, 스트림 끊기
//	 * 
//	 * @throws IOException
//	 */
//	private void close() throws IOException {
//		if (dis != null) {
//			dis.close();
//		} // end if
//		if (dos != null) {
//			dos.close();
//		} // end if
//		if (socketUser != null) {
//			socketUser.close();
//		} // end if
//	}// close

	@Override
	public void windowClosing(WindowEvent we) {
//		try {
			System.out.println("종료");
//			thConnect = null;
			//PCStateEvt의 ac를 null로 세팅한다.
			ac.getPcse().setAc(null);
			ac.dispose();
			System.out.println("관리자채팅이벤트 ac: " + ac);
//			ac.dispose();
			System.out.println("관리자채팅이벤트 ac: " + ac);
//			ac = null;
//			System.out.println("관리자채팅이벤트 ac: " + ac);
//			close();
//			ac = null;
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		} // end catch
	}// windowClosing
	
	@Override
	public void windowClosed(WindowEvent e) {
		thConnect=null;
		try {
		if(disRead!=null) {disRead.close();}//if
		if(dosWrite!=null) {dosWrite.close();}//if
		if(socket!=null) {socket.close();}//if
		}catch(IOException ie) {
			ie.printStackTrace();
		}
	}//windowClosed

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ac.getJbtSend() || ae.getSource()==ac.getJtfTalk()) { //대화T.F OR 보내기 버튼
			try {
//				System.out.println("제발");
				sendMsg();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed

}// class
