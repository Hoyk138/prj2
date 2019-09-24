package user.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
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
	
	public UserMainEvt(UserMain um) {
		this.um=um;
		pcNum();
		setPcHistory();
		
		//사용시간, 사용금액 thread
		if(tt==null) {
			tt=new TimeThread(um.getJlUseTime());
			tt.start();
			
			pt=new PriceThread(um.getJlUsePrice());
			pt.start();
		}//end if
		
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
		um.dispose();
		new RunPcUser();
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
	
	@Override
	public void actionPerformed(ActionEvent ae) {
			
		if(ae.getSource()==um.getJbtOrder()) { //먹거리주문버튼
				new UserItem();				
		}//end if
		
		if(ae.getSource()==um.getJbtCounterChat()) { //카운터채팅버튼
			switch(JOptionPane.showConfirmDialog(um, "카운터에 문의하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				JOptionPane.showMessageDialog(um, "※채팅이 원활하지 않을 경우, 카운터에 직접 문의해주세요.※");
				new UserChat(um);
			}//end switch			
		}//end if
		
		if(ae.getSource()==um.getJbtExit()) { //사용종료버튼
			switch(JOptionPane.showConfirmDialog(um, "PC 이용을 종료하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				JOptionPane.showMessageDialog(um, "PC 이용이 종료되었습니다.");
				useClose();
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
