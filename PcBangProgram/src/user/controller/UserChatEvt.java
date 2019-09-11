package user.controller;

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
import java.nio.channels.SocketChannel;

import javax.swing.JOptionPane;

import prj2.user.view.UserChat;
import prj2.user.view.UserMain;

public class UserChatEvt extends WindowAdapter implements ActionListener, Runnable {

	private UserChat uc;
	private UserMain um;

	private Socket socketUser;
	private DataInputStream readStream;
	private DataOutputStream writeStream;
	private Thread thConnect;

	public UserChatEvt(UserChat uc) {
		this.uc = uc;

		//
		try {
			connectToServer();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

	}// UserChatEvt

	/**
	 * 소켓 생성과 서버와 연결하여 스트림 연결 채팅 무한loop로 읽어들이는 일
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void connectToServer() throws UnknownHostException, IOException {

		try {
			// 사용자 소켓 생성 및 서버 접속
			socketUser = new Socket("211.63.89.142", 77777);
			// 읽기스트림 연결
			readStream = new DataInputStream(socketUser.getInputStream());
			// 쓰기스트림 연결
			writeStream = new DataOutputStream(socketUser.getOutputStream());
			// pc번호 받아와서 서버로 전달
//			String pcNum = um.getPcNum().trim() + "번";
//			writeStream.writeUTF(pcNum);
//			writeStream.flush();

			//
			thConnect = new Thread(this);
			// 호출
			thConnect.start();

			uc.getJtaChat().setText("EZO PC방 입니다.v^v^v 무엇을 도와드릴까요?\n");
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} // end catch

	}// connectToServer

	/**
	 * 서버의 메세지를 무한루프로 읽어 T.A에 출력하는 일
	 */
	@Override
	public void run() {
		// 보내는 메세지를 무한루프로 읽어들이기
		if (readStream != null) {
			try {
				while (true) {
					uc.getJtaChat().append("카운터 : " + readStream.readUTF() + "\n");
					scrollPosition();
				} // end while
			} catch (IOException ie) {
				//
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
		if (writeStream != null) {
			String msg = uc.getJtfTalk().getText().trim();
			uc.getJtaChat().append(msg + "\n");

			// 소켓 분출
			writeStream.writeUTF(msg);
			writeStream.flush();

			// T.F 초기화
			uc.getJtfTalk().setText("");
			scrollPosition();
		} else {
			JOptionPane.showMessageDialog(uc, "서버의 문제발생!\n카운터에 문의해주세요.");
		} // end else
	}// sendMsg

	/**
	 * 채팅에 스크롤을 사용자 위치로 보내는 일
	 */
	private void scrollPosition() {
		uc.getJspChat().getVerticalScrollBar().setValue(uc.getJspChat().getVerticalScrollBar().getMaximum());
	}// scrollPosition

	/**
	 * 소켓, 스트림 끊기
	 * 
	 * @throws IOException
	 */
	private void close() throws IOException {
		if (readStream != null) {
			readStream.close();
		} // end if
		if (writeStream != null) {
			writeStream.close();
		} // end if
		if (socketUser != null) {
			socketUser.close();
		} // end if
	}// close

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end catch
	}// windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == uc.getJbtSend() || ae.getSource()==uc.getJtfTalk()) { //대화T.F OR 보내기 버튼
			try {
//				System.out.println("제발");
				sendMsg();
			} catch (IOException e) {
				//
				e.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed


}// class
