package user.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import so.pcUserEvt.UserMainEvt;


public class UserMain extends JFrame{
	private JLabel jlPcNum, jlID, jlStartTime, jlUsePrice, jlUseTime;
	private JButton jbtOrder, jbtCounterChat, jbtAdImage;
	// 
	private String num,id,startTime,usePrice,UseTime;
	
	public UserMain() {
		super("E_ZO PC방");
		
		//생성
		jlPcNum=new JLabel("NO");
		jlID=new JLabel("ID : "+"ssang1004");
		jlStartTime=new JLabel("시작시간 : "+"2019-09-03 오후 06:00");
		jlUsePrice=new JLabel("사용요금 : "+"7,000"+"원");
		jlUseTime=new JLabel("사용시간 : "+"07:00"+"분");
		
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
	
	
	/**
	 * 단위테스트용
	 * @param args
	 */
	public static void main(String[] args) {
		new UserMain();
	}//main
	
}//class
