package user.view;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import user.DAO.UserDAO;
import user.controller.UserMainEvt;


public class UserMain extends JFrame{
	private JLabel jlPcNum, jlID, jlStartTime, jlUsePrice, jlUseTime;
	private JButton jbtOrder, jbtCounterChat, jbtAdImage;
	// 
//	private String usePrice,useTime;
	
	public UserMain(String userId) {
		super(" [E_ZO PC방] "+userId+"님 환영합니다!");
		
		//**5분=200원 -> 60분=1200원
//		int pcNum=10;
		//시작시간
		SimpleDateFormat sdf=new SimpleDateFormat("a h시 m분");
		String startTime=sdf.format(new Date());
		
		//생성
		jlPcNum=new JLabel("NO.0");
		jlID=new JLabel(userId);
		jlStartTime=new JLabel("시작시간 : "+startTime);
		jlUseTime=new JLabel("사용시간 : ");
		jlUsePrice=new JLabel("사용요금 : ");
		
		jbtAdImage=new JButton("광고창");
		jbtOrder=new JButton("먹거리주문");
		jbtCounterChat=new JButton("카운터채팅");
		
		//지워도 됩니당!
		jlPcNum.setBorder(new TitledBorder(""));
		jlID.setBorder(new TitledBorder(""));
		jlStartTime.setBorder(new TitledBorder(""));
		jlUsePrice.setBorder(new TitledBorder(""));
		jlUseTime.setBorder(new TitledBorder(""));
		jbtAdImage.setBorder(new TitledBorder(""));
		
		//수동배치
		setLayout(null);
		jlPcNum.setBounds(0, 0, 600, 50);
		jlID.setBounds(0, 50, 400, 40);
		jlStartTime.setBounds(0, 90, 400, 40);
		jlUsePrice.setBounds(0, 130, 200, 40);
		jlUseTime.setBounds(200, 130, 200, 40);
		jbtAdImage.setBounds(400, 50, 185, 180);
		jbtOrder.setBounds(0, 170, 200, 60);
		jbtCounterChat.setBounds(200, 170, 200, 60);
		
		//frame 추가
		add(jlPcNum);
		add(jlID);
		add(jlStartTime);
		add(jlUsePrice);
		add(jlUseTime);
		add(jbtAdImage);
		add(jbtOrder);
		add(jbtCounterChat);
		
		//이벤트 처리
		UserMainEvt ume=new UserMainEvt(this);
		jbtOrder.addActionListener(ume);
		jbtCounterChat.addActionListener(ume);
		jbtAdImage.addActionListener(ume);
		ume.pcNum();
		
		setResizable(false);
		setBounds(1300, 0, 590, 259);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UserMain

	//getter
	public JButton getJbtOrder() {
		return jbtOrder;
	}

	public JButton getJbtCounterChat() {
		return jbtCounterChat;
	}
	
	public JButton getJbtAdImage() {
		return jbtAdImage;
	}

	public JLabel getJlPcNum() {
		return jlPcNum;
	}

	public JLabel getJlUsePrice() {
		return jlUsePrice;
	}

	public JLabel getJlUseTime() {
		return jlUseTime;
	}

	public JLabel getJlID() {
		return jlID;
	}
	
	

//	public String getUsePrice() {
//		return usePrice;
//	}
//
//	public String getUseTime() {
//		return useTime;
//	}

//	public Date getStartDate() {
//		return startDate;
//	}
	
	
	

//	/**
//	 * 단위테스트용
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		new UserMain();
//	}//main
	
}//class
