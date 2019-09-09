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
		super("E_ZO PC��");
		
		//����
		jlPcNum=new JLabel("NO");
		jlID=new JLabel("ID : "+"ssang1004");
		jlStartTime=new JLabel("���۽ð� : "+"2019-09-03 ���� 06:00");
		jlUsePrice=new JLabel("����� : "+"7,000"+"��");
		jlUseTime=new JLabel("���ð� : "+"07:00"+"��");
		
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
	 * �����׽�Ʈ��
	 * @param args
	 */
	public static void main(String[] args) {
		new UserMain();
	}//main
	
}//class
