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
		// �б⽺Ʈ�� ����
		dis = ac.getDis();
		// ���⽺Ʈ�� ����
		dos = ac.getDos();
		System.out.println("������ ä�� �̺�Ʈ: "+dis);
		System.out.println("������ ä�� �̺�Ʈ: "+dos);
		
		try {
			connectToServer();
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}//end catch
		
		//����ڰ� ������ �޼����� �о���̴� run ����
		thConnect = new Thread(this);
		thConnect.start();
		System.out.println("������ ��ŸƮ");
		
	}// UserChatEvt

	/**
	 * ���� ������ ������ �����Ͽ� ��Ʈ�� ���� ä�� ����loop�� �о���̴� ��
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void connectToServer() throws UnknownHostException, IOException {

		try {
			// ������ ����
//			String ip =JOptionPane.showInputDialog("������ ip Address�Է�\n"+"130,132,133,134,135,137,138,140,141,142,143,144,155,147,148,149,150,159,152,153");
			// localhost, 127.0.0.1, �ڽ��� ip�� ������ �� ��ǻ�Ϳ� �����ϴ� ������ �����Ѵ�.
//			client = new Socket("localhost", 55555);
			socket = new Socket("localhost", 9001);//���� �׽�Ʈ �� ���� �ڽ��� IP��, ���� ��Ȳ���� ������ ���α׷��� �����ִ� IP��
//			socket = new Socket("211.63.89.133", 9001);

			// �б� ��Ʈ�� ����
			disRead = new DataInputStream(socket.getInputStream());
			// ���� ��Ʈ�� ����
			dosWrite = new DataOutputStream(socket.getOutputStream());
			
            //�� ������ �ν��Ͻ� ������ ����
//			this.nick = nick;//�Է� ��ȭ���� ��𿡼��� ����� �� �ֵ��� �ν��Ͻ�������
//			
//			//������ ��ȭ���� �ޱ�
//			inputNick = disRead.readUTF();
//
//			//�� ��ȭ���� ���濡�� ����.
//			dosWrite.writeUTF(nick);
//			dosWrite.flush();
			
//			jtaDisplay.append(/* inputNick+ */"���� ��ȭ ������ ��� ���̽��ϴ�. ��ſ� ��ȭ ��������.\n");
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} // end catch
		
//			// pc��ȣ �޾ƿͼ� ������ ����
////			String pcNum = um.getPcNum().trim() + "��";
////			writeStream.writeUTF(pcNum);
////			writeStream.flush();
//
//			//
//			thConnect = new Thread(this);
//			// ȣ��
//			thConnect.start();
//
//			uc.getJtaChat().setText("EZO PC�� �Դϴ�.v^v^v ������ ���͵帱���?\n");
	}// connectToServer

	/**
	 * ������� �޼����� ���ѷ����� �о� T.A�� ����ϴ� ��
	 */
	@Override
	public void run() {
		// ������ �޼����� ���ѷ����� �о���̱�
		if (disRead != null) {
			try {
				System.out.println("������ ��");
				while (true) {
					System.out.println("������ �� while");
					System.out.println(dis);
					System.out.println(ac);
					System.out.println(ac.getJtaChat());
					ac.getJtaChat().append(disRead.readUTF() + "\n");
					scrollPosition();
				} // end while
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(ac, "����ڲ��� ä���� �����̽��ϴ�.");
				ac.getPcs().setBackgrounColor(Color.LIGHT_GRAY);
				ac.getPcs().setBackground(ac.getPcs().getBackgrounColor());
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
		if (dosWrite != null) {
			String msg = "������: " + ac.getJtfTalk().getText().trim();
			ac.getJtaChat().append(msg + "\n");

			// ���� ����
			dosWrite.writeUTF(msg);
			dosWrite.flush();

			// T.F �ʱ�ȭ
			ac.getJtfTalk().setText("");
			scrollPosition();
		} else {
			JOptionPane.showMessageDialog(ac, "��Ʈ���� �����߻�!\nī���Ϳ� �������ּ���.");
		} // end else
	}// sendMsg

	/**
	 * ä�ÿ� ��ũ���� ����� ��ġ�� ������ ��
	 */
	private void scrollPosition() {
		ac.getJspChat().getVerticalScrollBar().setValue(ac.getJspChat().getVerticalScrollBar().getMaximum());
	}// scrollPosition

//	/**
//	 * ����, ��Ʈ�� ����
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
			System.out.println("����");
//			thConnect = null;
			//PCStateEvt�� ac�� null�� �����Ѵ�.
			ac.getPcse().setAc(null);
			ac.dispose();
			System.out.println("������ä���̺�Ʈ ac: " + ac);
//			ac.dispose();
			System.out.println("������ä���̺�Ʈ ac: " + ac);
//			ac = null;
//			System.out.println("������ä���̺�Ʈ ac: " + ac);
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
		if (ae.getSource() == ac.getJbtSend() || ae.getSource()==ac.getJtfTalk()) { //��ȭT.F OR ������ ��ư
			try {
//				System.out.println("����");
				sendMsg();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed

}// class
