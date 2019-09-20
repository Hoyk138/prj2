package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.DAO.UserDAO;
import user.VO.PcHistoryVO;
import user.helper.PriceThread;
import user.helper.TimeThread;
import user.view.UserChat;
import user.view.UserItem;
import user.view.UserMain;

public class UserMainEvt implements ActionListener{
	
	private UserMain um;
	private UserItem ui;
	private UserChat uc;
	
	private TimeThread tt;
	private PriceThread pt;
	
	private int pcNum;
	
	public UserMainEvt(UserMain um) {
		this.um=um;
		pcNum();
		setPcHistory();
		System.out.println("pc��� insert!");
		
//		if(tt==null) {
			tt=new TimeThread(um.getJlUseTime());
			tt.start();
			
			pt=new PriceThread(um.getJlUsePrice());
			pt.start();
//		}//end if
		
	}//UserMainEvt
	
	public void pcNum() {
		
		InetAddress local; 
		
		UserDAO uDAO=UserDAO.getInstance();
		pcNum=0;
		
		try { 
			local = InetAddress.getLocalHost(); 
			String ip = local.getHostAddress(); 
			
			pcNum=uDAO.selectPcNum(ip);
		} catch (UnknownHostException e1) {
			e1.printStackTrace(); 
		}catch (SQLException e) {
			e.printStackTrace();
		}//end catch
//		
		um.getJlPcNum().setText("NO."+String.valueOf(pcNum));
		
	}//pcNum
	
	public void setPcHistory() {
		
		String userId=um.getJlID().getText();
		
		PcHistoryVO phVO=new PcHistoryVO(pcNum, userId);
		
		UserDAO uDAO=UserDAO.getInstance();
		try {
			uDAO.insertPcHistory(phVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
	}//setPcHistory
	
	@Override
	public void actionPerformed(ActionEvent ae) {
			
		if(ae.getSource()==um.getJbtOrder()) { //�԰Ÿ��ֹ���ư
				new UserItem();				
		}//end if
		
		if(ae.getSource()==um.getJbtCounterChat()) { //ī����ä�ù�ư
			switch(JOptionPane.showConfirmDialog(um, "ī���Ϳ� �����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
					new UserChat(um);
			}//end switch			
		}//end if
		
		if(ae.getSource()==um.getJbtAdImage()) { //����â��ư
			
		}//end if
		
	}//actionPerformed



}//class
