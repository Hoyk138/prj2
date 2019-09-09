package min.pcUserView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import min.pcUserEvt.UserJoinEvt;

public class UserJoin extends JFrame {
	
	private JComboBox<String> jcbNum, jcbQuestion;
	private DefaultComboBoxModel<String> dcbmNum, dcbmQuestion ;
	private JLabel jlblId, jlblPass, jlblPassComfirm, jlblName, jlblPhone, jlblQuestion, jlblAnswer /* jlblYesNo ���պ�����?*/;
	private JTextField jtfId, jtfPass, jtfPassComfirm, jtfName, jtfPhone2, jtfPhone3, jtfAnswer ;
	private JButton jbtnOverlap, jbtnJoin;
	
	public UserJoin() {
		super("ȸ������");
		
		dcbmNum=new DefaultComboBoxModel<String>(new String[] {"010","011","017","019"});
		jcbNum=new JComboBox<String>(dcbmNum);
		
		dcbmQuestion=new DefaultComboBoxModel<String>(new String[] {"����� ��� ������ �̸���?","����̸� Ű��� ������?","���ÿ� �ƴ� ����� �ִ���?"});
		jcbQuestion=new JComboBox<String>(dcbmQuestion);
		
		jlblId=new JLabel("ID");
		jlblPass=new JLabel("��й�ȣ");
		jlblPassComfirm=new JLabel("��й�ȣȮ��");
		jlblName=new JLabel("�̸�");
		jlblPhone=new JLabel("�޴�����ȣ");
		jlblQuestion=new JLabel("����Ȯ������");
		jlblAnswer=new JLabel("�亯");
//		jlblYesNo=new JLabel("����/������");
		
		jtfId=new JTextField();
		jtfPass=new JTextField();
		jtfPassComfirm=new JTextField();
		jtfName=new JTextField();
		jtfPhone2=new JTextField();
		jtfPhone3=new JTextField();
		jtfAnswer=new JTextField();
		
		jbtnOverlap=new JButton("�ߺ�Ȯ��");
		jbtnJoin=new JButton("�����ϱ�");
		
		setLayout(null);
		
		jlblId.setBounds(50, 60, 76, 30);
		jlblPass.setBounds(50, 100, 76, 30);
		jlblPassComfirm.setBounds(50, 140, 100, 30);
		jlblName.setBounds(50, 180, 76, 30);
		jlblPhone.setBounds(50, 220, 76, 30);
		jlblQuestion.setBounds(50, 260, 100, 30);
		jlblAnswer.setBounds(50, 300, 76, 30);
//		jlblYesNo.setBounds(x, y, width, height);
		
		jtfId.setBounds(160, 60, 130, 25);
		jtfPass.setBounds(160, 100, 130, 25);
		jtfPassComfirm.setBounds(160, 140, 130, 25);
		jtfName.setBounds(160, 180, 230, 25);
		jcbNum.setBounds(160, 220, 60, 25);
		jtfPhone2.setBounds(227, 220, 76, 25);
		jtfPhone3.setBounds(310, 220, 80, 25);
		jcbQuestion.setBounds(160, 260, 230, 25);
		jtfAnswer.setBounds(160, 300, 230, 25);
		
		jbtnOverlap.setBounds(300, 60, 90, 25);
		jbtnJoin.setBounds(290, 350, 100, 40);
		
		add(jlblId);
		add(jlblPass);
		add(jlblPassComfirm);
		add(jlblName);
		add(jlblPhone);
		add(jlblQuestion);
		add(jlblAnswer);
		
		add(jtfId);
		add(jtfPass);
		add(jtfPassComfirm);
		add(jtfName);
		add(jcbNum);
		add(jtfPhone2);
		add(jtfPhone3);
		add(jcbQuestion);
		add(jtfAnswer);
		
		add(jbtnOverlap);
		add(jbtnJoin);
		
		UserJoinEvt uje=new UserJoinEvt(this);
		
		jtfId.addActionListener(uje);
		jtfName.addActionListener(uje);
		jtfPhone2.addActionListener(uje);
		jtfPhone3.addActionListener(uje);
		jtfAnswer.addActionListener(uje);
		
		
		setResizable(false);
		setBounds(100, 200, 450, 470);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}



	public static void main(String[] args) {
		new UserJoin();
	}//main

}//class
