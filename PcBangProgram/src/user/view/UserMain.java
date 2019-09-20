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
		super(" [E_ZO PC��] "+userId+"�� ȯ���մϴ�!");
		
		//**5��=200�� -> 60��=1200��
//		int pcNum=10;
		//���۽ð�
		SimpleDateFormat sdf=new SimpleDateFormat("a h�� m��");
		String startTime=sdf.format(new Date());
		
		//����
		jlPcNum=new JLabel("NO.0");
		jlID=new JLabel(userId);
		jlStartTime=new JLabel("���۽ð� : "+startTime);
		jlUseTime=new JLabel("���ð� : ");
		jlUsePrice=new JLabel("����� : ");
		
		jbtAdImage=new JButton("����â");
		jbtOrder=new JButton("�԰Ÿ��ֹ�");
		jbtCounterChat=new JButton("ī����ä��");
		
		//������ �˴ϴ�!
		jlPcNum.setBorder(new TitledBorder(""));
		jlID.setBorder(new TitledBorder(""));
		jlStartTime.setBorder(new TitledBorder(""));
		jlUsePrice.setBorder(new TitledBorder(""));
		jlUseTime.setBorder(new TitledBorder(""));
		jbtAdImage.setBorder(new TitledBorder(""));
		
		//������ġ
		setLayout(null);
		jlPcNum.setBounds(0, 0, 600, 50);
		jlID.setBounds(0, 50, 400, 40);
		jlStartTime.setBounds(0, 90, 400, 40);
		jlUsePrice.setBounds(0, 130, 200, 40);
		jlUseTime.setBounds(200, 130, 200, 40);
		jbtAdImage.setBounds(400, 50, 185, 180);
		jbtOrder.setBounds(0, 170, 200, 60);
		jbtCounterChat.setBounds(200, 170, 200, 60);
		
		//frame �߰�
		add(jlPcNum);
		add(jlID);
		add(jlStartTime);
		add(jlUsePrice);
		add(jlUseTime);
		add(jbtAdImage);
		add(jbtOrder);
		add(jbtCounterChat);
		
		//�̺�Ʈ ó��
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
//	 * �����׽�Ʈ��
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		new UserMain();
//	}//main
	
}//class
