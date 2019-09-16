package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.DAO.UserDAO;
import user.VO.UserLoginVO;
import user.view.FindId;
import user.view.FindPass;
import user.view.UserJoin;
import user.view.UserLogin;
import user.view.UserMain;

public class UserLoginEvt implements ActionListener{

	private UserLogin ul;
	
	public UserLoginEvt(UserLogin ul) {
	
	this.ul=ul;	
	
	}//UserLoginEvt
	
	
	public void userJoin() {
		new UserJoin();
	}
	
	public void findId() {
		new FindId();
	}
	
	public void findPass() {
		new FindPass();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==ul.getJtfId()) { //���̵� �Է¹�����
			if(!ul.getJtfId().getText().equals("")) { // ���̵� ����ִ��� �Ǵ�
				ul.getJpfPass().requestFocus();//���� �����Ѵٸ� Ŀ���� ������� �̵� 
			}//end if
		}//end if

		if (ae.getSource() == ul.getJpfPass()  || ae.getSource()==ul.getJbtnLogin()) { //��й�ȣ
			
			//�Է��� ��й�ȣ ��������
			String inputPass=new String (ul.getJpfPass().getPassword()); //������ PlainText(��) 
			String inputld=ul.getJtfId().getText(); // ���̵���

			//�̰Ÿ��� AES�� �ٽ�
//			String shaPass="";
//			
//			try {
//				//PlainText -> CipheText�� ��ȯ
//				shaPass=DataEncrypt.messageDigest("MD5", inputPass);
//			} catch (NoSuchAlgorithmException e1) {
//				e1.printStackTrace();
//			}//end catch

			//�Է¹��� ���̵�� ��й�ȣ�� VO�� ����
			UserLoginVO ulVO=new UserLoginVO(inputld, inputPass);
			//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
			UserDAO uDAO=UserDAO.getInstance(); 
			try {
				String name = uDAO.selectLogin(ulVO);
					
				if( !name.isEmpty() ) { //�Էµ� ���̵�� ��й�ȣ�� ��ġ�ϴ� ������ �ִٸ�
					new UserMain();
					ul.dispose();
				}else {
					ul.getJtfId().setText(""); //���̵� �ʱ�ȭ
					ul.getJpfPass().setText(""); //��й�ȣ �ʱ�ȭ
					JOptionPane.showMessageDialog(ul, "���̵� Ȥ�� ��й�ȣ�� Ȯ�����ּ���");
					ul.getJtfId().requestFocus(); //Ŀ���� ���̵� ��ġ
				}//end if
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch			
			
			
		}//end if
		
		
		if(ae.getSource()==ul.getJbtnJoin()) {
			System.out.println("���԰���");
			userJoin();  
			
		}
			
		if(ae.getSource()==ul.getJbtnID()) {
			System.out.println("IDã��");
			findId();
		}

		if(ae.getSource()==ul.getJbtnPass()) {
			System.out.println("PWã��");
			findPass();
		}
		
	}//actionPerformed

}//class
