package user.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import user.controller.UserMainEvt;


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
		
		//������
//		ImageIcon iiLogo=new ImageIcon("");
		ImageIcon iiBack=new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/user/design/bgcolor.jpg");
		ImageIcon iiAd=new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/user/design/AdImg.gif");
		ImageIcon iiAd2=new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/user/design/AdImg2.gif");
					
		//����
		JLabel jlLogo=new JLabel("�� E_ZO PC ��");
		pcNum=new JLabel("NO.");
		id=new JLabel("���̵�.");
		startTime=new JLabel("���۽ð� : ");
		useTime=new JLabel("���ð� : ");
		usePrice=new JLabel("[�ĺ���] ���ݾ� : ");
		
		jlPcNum=new JLabel();
		jlID=new JLabel(userId);
		jlStartTime=new JLabel(sdf.format(new Date()));
		jlUseTime=new JLabel();
		jlUsePrice=new JLabel();
		
		jbtAdImage=new JButton(iiAd);
		jbtAdImage.setRolloverIcon(iiAd2);
		jbtOrder=new JButton("�԰Ÿ��ֹ�");
		jbtCounterChat=new JButton("ī���� ����");
		jbtExit=new JButton("�������");
		
		//Pannel
		JPanel jpPcNum=new JPanel() {
			//�гλ� �ٲٴ� method
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(iiBack.getImage(),0,0,null);
				setOpaque(false);
			}
			
		};
		JPanel jpID=new JPanel();
		JPanel jpUserID=new JPanel();
		JPanel jpUse=new JPanel();
		JPanel jpSouthBtn=new JPanel();
		
		jpPcNum.add(jlLogo);
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
		
		jpPcNum.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jpID.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jpUserID.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jpUse.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jpSouthBtn.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jbtAdImage.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jbtOrder.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jbtCounterChat.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		jbtExit.setBorder(new TitledBorder(new  LineBorder(Color.black,1)));
		
		jpPcNum.setLayout(null);
		jlLogo.setBounds(20, 0, 250, 60);
		pcNum.setBounds(500, 10, 50, 50);
		jlPcNum.setBounds(530, 10, 50, 50);
		
		jpID.setLayout(null);
		id.setBounds(20, 0, 50, 50);
		jpUserID.setLayout(null);
		jlID.setBounds(20, 0, 130, 50);
		
		jpUse.setLayout(null);
		startTime.setBounds(20, 20, 100, 40);
		jlStartTime.setBounds(100, 20, 100, 40);
		useTime.setBounds(20, 70, 100, 40);
		jlUseTime.setBounds(100, 70, 100, 40);
		usePrice.setBounds(20, 120, 150, 40);
		jlUsePrice.setBounds(150, 120, 100, 40);
		
		jpSouthBtn.setLayout(null);
		jbtOrder.setBounds(0, 0, 225, 60);
		jbtCounterChat.setBounds(225, 0, 220, 60);
		jbtExit.setBounds(445, 0, 149, 60);
		
		//��ġ
		setLayout(null);
		jpPcNum.setBounds(0, 0, 600, 60);
		jpID.setBounds(0, 60, 130, 45);
		jpUserID.setBounds(130, 60, 480, 45);
		jpUse.setBounds(0, 105, 320, 185);
		jpSouthBtn.setBounds(0, 290, 600, 70);
		jbtAdImage.setBounds(280, 105, 320,185);
		
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
		
		jbtOrder.setBackground(new Color(0x434446));
		jbtCounterChat.setBackground(new Color(0x434446));
		jbtExit.setBackground(new Color(0x434446));
		jpID.setBackground(new Color(0x696969));
		jpUserID.setBackground(new Color(0x696969));
		jpUse.setBackground(new Color(0xCCCCCC));
		//��Ʈ
		jlLogo.setFont(new Font("serif",Font.BOLD, 25));
		jlLogo.setForeground(new Color(0xF1C40F));
		pcNum.setFont(new Font("MonoSpaced", Font.BOLD, 20));
		pcNum.setForeground(Color.white);
		jlPcNum.setFont(new Font("MonoSpaced", Font.BOLD, 25));
		jlPcNum.setForeground(new Color(0xF1C40F));	
		id.setFont(new Font("serif", Font.BOLD, 15));
		id.setForeground(Color.white);	
		jlID.setFont(new Font("serif", Font.BOLD, 17));
		jlID.setForeground(Color.white);	
		jbtOrder.setFont(new Font("serif", Font.BOLD, 15));
		jbtOrder.setForeground(Color.white);	
		jbtCounterChat.setFont(new Font("serif", Font.BOLD, 15));
		jbtCounterChat.setForeground(Color.white);	
		jbtExit.setFont(new Font("serif", Font.BOLD, 15));
		jbtExit.setForeground(Color.white);	
		startTime.setFont(new Font("serif", Font.BOLD, 15));
		jlStartTime.setFont(new Font("serif", Font.BOLD, 15));
		useTime.setFont(new Font("serif", Font.BOLD, 15));
		jlUseTime.setFont(new Font("serif", Font.BOLD, 15));
		usePrice.setFont(new Font("serif", Font.BOLD, 15));
		jlUsePrice.setFont(new Font("serif", Font.BOLD, 15));
		
		setResizable(false);
		setBounds(1320, 0, 600, 380);
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
