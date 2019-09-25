package user.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import user.controller.UserMainEvt;

@SuppressWarnings("serial")
public class UserMain extends JFrame{
	private JLabel pcNum, id, startTime, usePrice, useTime; // �Ӽ� (Ÿ��Ʋ)
	private JLabel jlPcNum, jlID, jlStartTime, jlUsePrice, jlUseTime; //�Ӽ� ����(data)
	private JButton jbtOrder, jbtCounterChat, jbtAdImage,jbtExit; //��ư
	
	private UserLogin ul;
	
	public UserMain(String userId) {
		super(" ��E_ZO PC���  [" +  userId +"]��");
		
		//**5��=100�� -> 60��=1200��
		//���۽ð�
		SimpleDateFormat sdf=new SimpleDateFormat("a h�� m��");
//		Font f=new Font();
		
		//����
		pcNum=new JLabel("NO.");
		id=new JLabel("ID");
		startTime=new JLabel("���۽ð� : ");
		useTime=new JLabel("���ð� : ");
		usePrice=new JLabel("[�ĺ���] ���ݾ� : ");
		
		jlPcNum=new JLabel();
		jlID=new JLabel(userId);
		jlStartTime=new JLabel(sdf.format(new Date()));
		jlUseTime=new JLabel();
		jlUsePrice=new JLabel();
		
		jbtAdImage=new JButton("����â");
		jbtOrder=new JButton("�԰Ÿ��ֹ�");
		jbtCounterChat=new JButton("ī���� ����");
		jbtExit=new JButton("�������");
		
		//Pannel
		JPanel jpPcNum=new JPanel();
		JPanel jpID=new JPanel();
		JPanel jpUserID=new JPanel();
		JPanel jpUse=new JPanel();
		JPanel jpSouthBtn=new JPanel();
		
		jpPcNum.add(pcNum);
		jpPcNum.add(jlPcNum);
		//
		jpID.add(id);
		jpUserID.add(jlID);
		//
		jpUse.add(startTime);
		jpUse.add(jlStartTime);
		jpUse.add(useTime);
		jpUse.add(jlUseTime);
		jpUse.add(usePrice);
		jpUse.add(jlUsePrice);
		//
		jpSouthBtn.add(jbtOrder);
		jpSouthBtn.add(jbtCounterChat);
		jpSouthBtn.add(jbtExit);
		
		jpPcNum.setBorder(new TitledBorder(""));
		jpID.setBorder(new TitledBorder(""));
		jpUserID.setBorder(new TitledBorder(""));
		jpUse.setBorder(new TitledBorder(""));
		jpSouthBtn.setBorder(new TitledBorder(""));
		
		jpPcNum.setLayout(null);
		pcNum.setBounds(20, 0, 50, 50);
		jlPcNum.setBounds(50, 0, 50, 50);
		
		jpID.setLayout(null);
		id.setBounds(20, 0, 80, 50);
		jpUserID.setLayout(null);
		jlID.setBounds(30, 0, 130, 50);
		
		jpUse.setLayout(null);
		startTime.setBounds(20, 0, 100, 40);
		jlStartTime.setBounds(100, 0, 100, 40);
		useTime.setBounds(20, 40, 100, 40);
		jlUseTime.setBounds(100, 40, 100, 40);
		usePrice.setBounds(20, 80, 150, 40);
		jlUsePrice.setBounds(150, 80, 100, 40);
		
		jpSouthBtn.setLayout(null);
		jbtOrder.setBounds(0, 0, 175, 60);
		jbtCounterChat.setBounds(175, 0, 175, 60);
		jbtExit.setBounds(350, 0, 150, 60);
		
		
		//��ġ
		setLayout(null);
		jpPcNum.setBounds(50, 40, 500, 50);
		jpID.setBounds(50, 90, 100, 50);
		jpUserID.setBounds(150, 90, 400, 50);
		jpUse.setBounds(50, 140, 350, 130);
		jpSouthBtn.setBounds(50, 270, 500, 60);
		jbtAdImage.setBounds(400, 140, 150, 130);
		
		//frame �߰�
		add(jpPcNum);
		add(jpID);
		add(jpUserID);
		add(jpUse);
		add(jpSouthBtn);
		add(jbtAdImage);
		
		//�̺�Ʈ ó��
		UserMainEvt ume=new UserMainEvt(this);
		jbtOrder.addActionListener(ume);
		jbtCounterChat.addActionListener(ume);
		jbtExit.addActionListener(ume);
		jbtAdImage.addActionListener(ume);
		ume.pcNum();
		
		setResizable(false);
		setBounds(1300, 10, 600, 400);
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
	
	public JButton getJbtExit() {
		return jbtExit;
	}

	public JLabel getJlPcNum() {
		return jlPcNum;
	}

	public JLabel getJlID() {
		return jlID;
	}
	
	public JLabel getJlUsePrice() {
		return jlUsePrice;
	}

	public JLabel getJlUseTime() {
		return jlUseTime;
	}

	

//	/**
//	 * �����׽�Ʈ��
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		new UserMain("bbb");
//	}//main
	
}//class
