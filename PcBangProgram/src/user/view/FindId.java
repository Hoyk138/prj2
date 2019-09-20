package user.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import user.controller.FindIdEvt;

@SuppressWarnings("serial")
public class FindId extends JDialog {
	
	private JComboBox<String> jcbNum;
	private DefaultComboBoxModel<String> dcbmNum;
	private JLabel jlbName, jlbPhone;
	private JTextField jtfName, jtfPhone1, jtfPhone2, jtfPhone3;
	private JButton jbtnFindID;
	
	public FindId(/* UserLogin ul */) {   //////////
//		super("ID 찾기");
		
		dcbmNum=new DefaultComboBoxModel<String>(new String[] {"010","011","016","017","018","019"});
		jcbNum=new JComboBox<String>(dcbmNum);
		
		jlbName=new JLabel("이름");
		jlbPhone=new JLabel("휴대폰번호");
		
		jtfName=new JTextField(4);
		jtfPhone2=new JTextField();
		jtfPhone3=new JTextField();
		
		jbtnFindID=new JButton("ID찾기");
		
		setLayout(null);
		
		jlbName.setBounds(40, 60, 76, 30);
		jlbPhone.setBounds(40, 100, 76, 30);
		jtfName.setBounds(150, 60, 210, 25);
		jcbNum.setBounds(150, 100, 50, 25);
		jtfPhone2.setBounds(210, 100, 70, 25);
		jtfPhone3.setBounds(290, 100, 70, 25);
		jbtnFindID.setBounds(330, 140, 90, 50);
		
		add(jlbName);
		add(jlbPhone);
		add(jtfName);
		add(jcbNum);
		add(jtfPhone2);
		add(jtfPhone3);
		add(jbtnFindID);
		
		
		FindIdEvt fie=new FindIdEvt(this);
		
		jtfName.addActionListener(fie);
		jcbNum.addActionListener(fie);
		jtfPhone2.addActionListener(fie);
		jtfPhone3.addActionListener(fie);
		jbtnFindID.addActionListener(fie);
		
		setResizable(false);
		setBounds(700, 400, 450, 250);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public JComboBox<String> getJcbNum() {
		return jcbNum;
	}

	public DefaultComboBoxModel<String> getDcbmNum() {
		return dcbmNum;
	}

	public JLabel getJlbName() {
		return jlbName;
	}

	public JLabel getJlbPhone() {
		return jlbPhone;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhone1() {
		return jtfPhone1;
	}

	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}

	public JTextField getJtfPhone3() {
		return jtfPhone3;
	}

	public JButton getJbtnFindID() {
		return jbtnFindID;
	}
	
	
	
//	public static void main(String[] args) {
//		new FindId();
//		
//	}


}
