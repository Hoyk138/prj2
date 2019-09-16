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

import user.controller.UserRePassEvt;

@SuppressWarnings("serial")
public class UserRePass extends JDialog {
	
	private JLabel jlblPass, jlblConfirm;
	private JTextField jtfPass, jtfConfirm;
	private JButton jbtnResetPass;
	
	public UserRePass() {
//		super("ID ã��");
		
		jlblPass=new JLabel("��й�ȣ");
		jlblConfirm=new JLabel("Ȯ��");
		
		jtfPass=new JTextField();
		jtfConfirm=new JTextField();
		
		jbtnResetPass=new JButton("PW�缳��");
		
		setLayout(null);
		
		jlblPass.setBounds(40, 60, 76, 30);
		jlblConfirm.setBounds(40, 100, 76, 30);
		jtfPass.setBounds(150, 60, 200, 25);
		jtfConfirm.setBounds(150, 100, 200, 25);
		jbtnResetPass.setBounds(250, 150, 100, 30);
		
		add(jlblPass);
		add(jlblConfirm);
		add(jtfPass);
		add(jtfConfirm);
		add(jbtnResetPass);
		
		UserRePassEvt urpe=new UserRePassEvt(this);

		jtfPass.addActionListener(urpe);
		jtfConfirm.addActionListener(urpe);
		jbtnResetPass.addActionListener(urpe);
		
		setResizable(false);
		setBounds(100, 200, 450, 250);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UserRePass
	

	public static void main(String[] args) {
		new UserRePass();
	}//main

}//class
