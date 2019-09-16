package user.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import user.controller.FindPassEvt;

public class FindPass extends JDialog {
	
	private JComboBox<String> jcbNum, jcbQuestion;
	private DefaultComboBoxModel<String> dcbmNum, dcbmQuestion ;
	private JLabel jlbId, jlbName, jlbPhone, jlbQuestion, jlbAnswer;
	private JTextField jtfId, jtfName, jtfPhone2, jtfPhone3, jtfAnswer ;
	private JButton jbtnFindPass;
	
	public FindPass() {
//		super("PW 찾기");
		
		dcbmNum=new DefaultComboBoxModel<String>(new String[] {"010","011","017","019"});
		jcbNum=new JComboBox<String>(dcbmNum);
		
		dcbmQuestion=new DefaultComboBoxModel<String>(new String[] 
				{"어린시절 살던 고향의 이름은?","고양이를 키우고 싶은지?","고양시에 아는 사람이 있는지?"});
		jcbQuestion=new JComboBox<String>(dcbmQuestion);
		
		jlbId=new JLabel("ID");
		jlbName=new JLabel("이름");
		jlbPhone=new JLabel("휴대폰번호");
		jlbQuestion=new JLabel("본인확인질문");
		jlbAnswer=new JLabel("답변");
		
		jtfId=new JTextField();
		jtfName=new JTextField();
		jtfPhone2=new JTextField();
		jtfPhone3=new JTextField();
		jtfAnswer=new JTextField();
		
		jbtnFindPass=new JButton("PW찾기");
		
		setLayout(null);
		
		jlbId.setBounds(50, 60, 76, 30);
		jlbName.setBounds(50, 100, 76, 30);
		jlbPhone.setBounds(50, 140, 100, 30);
		jlbQuestion.setBounds(50, 180, 100, 30);
		jlbAnswer.setBounds(50, 220, 76, 30);
		
		jtfId.setBounds(160, 60, 230, 25);
		jtfName.setBounds(160, 100, 230, 25);
		jcbNum.setBounds(160, 140, 60, 25);
		jtfPhone2.setBounds(227, 140, 76, 25);
		jtfPhone3.setBounds(310, 140, 80, 25);
		jcbQuestion.setBounds(160, 180, 230, 25);
		jtfAnswer.setBounds(160, 220, 230, 25);
		
		jbtnFindPass.setBounds(290, 270, 100, 40);
		
		add(jlbId);
		add(jlbName);
		add(jlbPhone);
		add(jlbQuestion);
		add(jlbAnswer);
		add(jtfId);
		add(jtfName);
		add(jcbNum);
		add(jtfPhone2);
		add(jtfPhone3);
		add(jcbQuestion);
		add(jtfAnswer);
		add(jbtnFindPass);
		
		FindPassEvt fpe=new FindPassEvt(this);
		
		jtfId.addActionListener(fpe);
		jtfName.addActionListener(fpe);
		jtfPhone2.addActionListener(fpe);
		jtfPhone3.addActionListener(fpe);
		jtfAnswer.addActionListener(fpe);
		jbtnFindPass.addActionListener(fpe);
		
		setResizable(false);
		setBounds(100, 200, 450, 400);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

		

	public static void main(String[] args) {
		new FindPass();
	}//main

}//class
