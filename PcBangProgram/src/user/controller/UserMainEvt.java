package user.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
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
import user.view.UserMain;

public class UserMainEvt implements ActionListener, Runnable{
	
	private UserMain um;
//	private RunPcUser rpu;
	
	private Thread thread; 
	private TimeThread tt;
	private PriceThread pt;
	
	private int pcNum;
	private String pcUseCode;
	
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private String adminIP;
	
	public UserMainEvt(UserMain um, String adminIP) {
		this.um=um;
		this.adminIP = adminIP;
		pcNum();
		setPcHistory();
		
		//사용시간, 사용금액 thread
		if(tt==null) {
			tt=new TimeThread(um.getJlUseTime());
			tt.start();
			
			pt=new PriceThread(um.getJlUsePrice());
			pt.start();
		}//end if
		
		//서버에 연결
	    try {
			connectToServer();
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}//end catch
		
	}//UserMainEvt
	
	/**
	 * ip주소로 저장된 pc번호를 조회
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
	 * main창이 띄어질 때 시작 pc사용기록의 데이터가 추가되는 일
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
	 * 이용종료
	 */
	public void useClose() {
		UserDAO uDAO = UserDAO.getInstance();
		boolean flag = false;
		try {
			flag = uDAO.insertPCPayment(pcNum);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}//try catch
		if (flag) {
			pt =null;
			tt =null;
			thread=null;
			try {
				if(dis!=null) {dis.close();}//if
				if(dos!=null) {dos.close();}//if
				if(socket!=null) {socket.close();}//if
			}catch(IOException ie) {
				ie.printStackTrace();
			}//try catch
			um.dispose();
			new RunPcUser(adminIP);
		} else {
			JOptionPane.showMessageDialog(um, "PC 사용 종료에 실패 하였습니다.");
		}//if else
		
	}//useClose
	
	/**
	 * 광고이미지 클릭시, 해당 사이트 이동
	 * @param url
	 */
	public void openWebpage(String url) {
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (IOException | URISyntaxException e) {
			JOptionPane.showMessageDialog(um, "해당사이트로 연결할 수 없습니다.\n(인터넷 연결 문제 시, 카운터에 문의해주세요.)");
			e.printStackTrace();
		}//end catch
	}//openWebpage
	
    private void connectToServer() throws UnknownHostException, IOException{
//		try {
			// 소켓을 생성
//			socket = new Socket("localhost", 9000);
			socket = new Socket(adminIP, 9000);

			// 읽기 스트림 연결
			dis = new DataInputStream(socket.getInputStream());
			// 쓰기 스트림 연결
			dos = new DataOutputStream(socket.getOutputStream());
			
            //내 별명을 인스턴스 변수에 저장
//			this.nick = nick;//입력 대화명을 어디에서든 사용할 수 있도록 인스턴스변수에
			
			//상대방의 대화명을 받기
//			inputNick = disRead.readUTF();

			//ID와 로그인 시간을 상대방에게 전송.
			String userId=um.getJlID().getText();
			dos.writeUTF(userId);
			dos.writeUTF(um.getLoginTime());
			dos.flush();
			
			//스레드 시작
			thread = new Thread(this);
			thread.start();
//			jtaDisplay.append(inputNick+"님의 대화 서버에 들어 오셨습니다. 즐거운 대화 나누세요.\n");
//		} catch (ConnectException ce) {
//			ce.printStackTrace();
//		} // end catch
    }//connectToServer
	
	@Override
	public void run() {
		try {
			String msg = "";
			
			while (true) {
				System.out.println("종료 메세지 대기");
				msg = dis.readUTF();
				System.out.println("종료 메세지 수신");
				if (msg.equals("/종료")) {
					System.out.println("종료 메세지 확인");
					JOptionPane.showMessageDialog(um, "관리자의 요청으로 PC 사용을 종료합니다.");
					useClose();
					
				}//end if
			}//end while
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}//try catch
	}//run

	private void openChat() throws IOException{
		String[] options = {"예","아니요"};
//		switch (JOptionPane.showConfirmDialog(um, "관리자와 채팅을 시작 하시겠습니까?")) {
		switch (JOptionPane.showOptionDialog(um, "관리자와 채팅을 시작 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
		case JOptionPane.OK_OPTION:
			try {
				dos.writeUTF("/채팅");	
				dos.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}//catch
//			new UserChat(um.getPcNum(),dos);	
			new UserChat(um,pcNum,dos);	
		}//switch case
	}//openChat
    
	@Override
	public void actionPerformed(ActionEvent ae) {
			
		if(ae.getSource()==um.getJbtOrder()) { //먹거리주문버튼
				new UserItem();				
		}//end if
		
		if(ae.getSource()==um.getJbtCounterChat()) { //카운터채팅버튼
			switch(JOptionPane.showConfirmDialog(um, "카운터에 문의하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				JOptionPane.showMessageDialog(um, "※채팅이 원활하지 않을 경우, 카운터에 직접 문의해주세요.※");
//				new UserChat(um);
				try {
					openChat();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}//catch
			}//end switch			
		}//end if
		
		if(ae.getSource()==um.getJbtExit()) { //사용종료버튼
			switch(JOptionPane.showConfirmDialog(um, "PC 이용을 종료하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				useClose();
				JOptionPane.showMessageDialog(um, "PC 이용이 종료되었습니다.");
			}//end switch			
		}//end if
		
		if(ae.getSource()==um.getJbtAdImage()) { //광고창버튼
			openWebpage("https://playoverwatch.com/ko-kr/");
		}//end if
		
//		회원정보조회, 요금안내 
		
	}//actionPerformed

	public String getPcUseCode() {
		return pcUseCode;
	}
	

}//class
