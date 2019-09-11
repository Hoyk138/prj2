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
	 * ���� ������ ������ �����Ͽ� ��Ʈ�� ���� ä�� ����loop�� �о���̴� ��
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void connectToServer() throws UnknownHostException, IOException {

		try {
			// ����� ���� ���� �� ���� ����
			socketUser = new Socket("211.63.89.142", 77777);
			// �б⽺Ʈ�� ����
			readStream = new DataInputStream(socketUser.getInputStream());
			// ���⽺Ʈ�� ����
			writeStream = new DataOutputStream(socketUser.getOutputStream());
			// pc��ȣ �޾ƿͼ� ������ ����
//			String pcNum = um.getPcNum().trim() + "��";
//			writeStream.writeUTF(pcNum);
//			writeStream.flush();

			//
			thConnect = new Thread(this);
			// ȣ��
			thConnect.start();

			uc.getJtaChat().setText("EZO PC�� �Դϴ�.v^v^v ������ ���͵帱���?\n");
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} // end catch

	}// connectToServer

	/**
	 * ������ �޼����� ���ѷ����� �о� T.A�� ����ϴ� ��
	 */
	@Override
	public void run() {
		// ������ �޼����� ���ѷ����� �о���̱�
		if (readStream != null) {
			try {
				while (true) {
					uc.getJtaChat().append("ī���� : " + readStream.readUTF() + "\n");
					scrollPosition();
				} // end while
			} catch (IOException ie) {
				//
				ie.printStackTrace();
			} // end catch
		} // end if
	}// run

	/**
	 * T.F�� �Է°��� ������ �������� �����ϴ���
	 * 
	 * @throws IOException
	 */
	private void sendMsg() throws IOException {
		// ��Ʈ�� �����
		if (writeStream != null) {
			String msg = uc.getJtfTalk().getText().trim();
			uc.getJtaChat().append(msg + "\n");

			// ���� ����
			writeStream.writeUTF(msg);
			writeStream.flush();

			// T.F �ʱ�ȭ
			uc.getJtfTalk().setText("");
			scrollPosition();
		} else {
			JOptionPane.showMessageDialog(uc, "������ �����߻�!\nī���Ϳ� �������ּ���.");
		} // end else
	}// sendMsg

	/**
	 * ä�ÿ� ��ũ���� ����� ��ġ�� ������ ��
	 */
	private void scrollPosition() {
		uc.getJspChat().getVerticalScrollBar().setValue(uc.getJspChat().getVerticalScrollBar().getMaximum());
	}// scrollPosition

	/**
	 * ����, ��Ʈ�� ����
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
		if (ae.getSource() == uc.getJbtSend() || ae.getSource()==uc.getJtfTalk()) { //��ȭT.F OR ������ ��ư
			try {
//				System.out.println("����");
				sendMsg();
			} catch (IOException e) {
				//
				e.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed


}// class
