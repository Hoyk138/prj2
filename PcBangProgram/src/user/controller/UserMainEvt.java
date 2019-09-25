package user.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.DAO.UserDAO;
import user.VO.PcHistoryVO;
import user.helper.PriceThread;
import user.helper.TimeThread;
import user.run.RunPcUser;
import user.view.UserChat;
import user.view.UserItem;
import user.view.UserLogin;
import user.view.UserMain;

public class UserMainEvt implements ActionListener{
	
	private UserMain um;
	private RunPcUser rpu;
	
	private TimeThread tt;
	private PriceThread pt;
	
	private int pcNum;
	private String pcUseCode;
	
	private Socket socket;
//	private DataInputStream dis;
	private DataOutputStream dos;
	
	public UserMainEvt(UserMain um) {
		this.um=um;
		pcNum();
		setPcHistory();
		
		//���ð�, ���ݾ� thread
		if(tt==null) {
			tt=new TimeThread(um.getJlUseTime());
			tt.start();
			
			pt=new PriceThread(um.getJlUsePrice());
			pt.start();
		}//end if
		
		//������ ����
	    try {
			connectToServer();
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}//end catch
		
	}//UserMainEvt
	
	/**
	 * ip�ּҷ� ����� pc��ȣ�� ��ȸ
	 */
	public void pcNum() {
		InetAddress local; 
		pcNum=0;
		
		UserDAO uDAO=UserDAO.getInstance();
		
		try { 
			local = InetAddress.getLocalHost(); 
			String ip = local.getHostAddress(); 
			pcNum=uDAO.selectPcNum(ip);
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace(); 
		}catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		um.getJlPcNum().setText(String.valueOf(pcNum));
	}//pcNum
	
	/**
	 * mainâ�� ����� �� ���� pc������� �����Ͱ� �߰��Ǵ� ��
	 */
	public void setPcHistory() {
		String userId=um.getJlID().getText();
		
		PcHistoryVO phVO=new PcHistoryVO(pcNum, userId);
		
		UserDAO uDAO=UserDAO.getInstance();
		try {
			pcUseCode=uDAO.insertPcHistory(phVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//setPcHistory
	
	/**
	 * �̿�����
	 */
	public void useClose() {
		um.dispose();
		new RunPcUser();
	}//useClose
	
	/**
	 * �����̹��� Ŭ����, �ش� ����Ʈ �̵�
	 * @param url
	 */
	public void openWebpage(String url) {
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (IOException | URISyntaxException e) {
			JOptionPane.showMessageDialog(um, "�ش����Ʈ�� ������ �� �����ϴ�.\n(���ͳ� ���� ���� ��, ī���Ϳ� �������ּ���.)");
			e.printStackTrace();
		}//end catch
	}//openWebpage
	
    private void connectToServer() throws UnknownHostException, IOException{
//		try {
			// ������ ����
			socket = new Socket("localhost", 9000);

			// �б� ��Ʈ�� ����
//			dis = new DataInputStream(socket.getInputStream());
			// ���� ��Ʈ�� ����
			dos = new DataOutputStream(socket.getOutputStream());
			
            //�� ������ �ν��Ͻ� ������ ����
//			this.nick = nick;//�Է� ��ȭ���� ��𿡼��� ����� �� �ֵ��� �ν��Ͻ�������
			
			//������ ��ȭ���� �ޱ�
//			inputNick = disRead.readUTF();

			//ID�� �α��� �ð��� ���濡�� ����.
			dos.writeUTF("���̵�");
			dos.writeUTF("����ð�");
			dos.flush();
			
//			jtaDisplay.append(inputNick+"���� ��ȭ ������ ��� ���̽��ϴ�. ��ſ� ��ȭ ��������.\n");
//		} catch (ConnectException ce) {
//			ce.printStackTrace();
//		} // end catch
    }//connectToServer
    
	private void openChat() throws IOException{
		String[] options = {"��","�ƴϿ�"};
//		switch (JOptionPane.showConfirmDialog(um, "�����ڿ� ä���� ���� �Ͻðڽ��ϱ�?")) {
		switch (JOptionPane.showOptionDialog(um, "�����ڿ� ä���� ���� �Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "��")) {
		case JOptionPane.OK_OPTION:
			try {
				dos.writeUTF("/ä��");	
				dos.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}//catch
			new UserChat(um,Integer.parseInt(um.getJlPcNum().getText()),dos);	
		}//switch case
	}//openChat
    
	@Override
	public void actionPerformed(ActionEvent ae) {
			
		if(ae.getSource()==um.getJbtOrder()) { //�԰Ÿ��ֹ���ư
				new UserItem();				
		}//end if
		
		if(ae.getSource()==um.getJbtCounterChat()) { //ī����ä�ù�ư
//			switch(JOptionPane.showConfirmDialog(um, "ī���Ϳ� �����Ͻðڽ��ϱ�?")) {
//			case JOptionPane.OK_OPTION:
//				JOptionPane.showMessageDialog(um, "��ä���� ��Ȱ���� ���� ���, ī���Ϳ� ���� �������ּ���.��");
//				new UserChat(um);
//			}//end switch
			try {
				openChat();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}//catch
		}//end if
		
		if(ae.getSource()==um.getJbtExit()) { //��������ư
			switch(JOptionPane.showConfirmDialog(um, "PC �̿��� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				JOptionPane.showMessageDialog(um, "PC �̿��� ����Ǿ����ϴ�.");
				useClose();
			}//end switch			
		}//end if
		
		if(ae.getSource()==um.getJbtAdImage()) { //����â��ư
			openWebpage("https://playoverwatch.com/ko-kr/");
		}//end if
		
//		ȸ��������ȸ, ��ݾȳ� 
		
	}//actionPerformed

	public String getPcUseCode() {
		return pcUseCode;
	}
	

}//class
