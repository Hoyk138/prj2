package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
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
		new UserJoin(ul);
	}
	
	public void findId() {
		new FindId(ul);
	}
	
	public void findPass() {
		new FindPass(ul);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==ul.getJtfId()) { //���̵� �Է¹�����
			if(!ul.getJtfId().getText().equals("")) { // ���̵� ����ִ��� �Ǵ�
				ul.getJpfPass().requestFocus();//���� �����Ѵٸ� Ŀ���� ������� �̵� 
			}//end if
		}//end if

		if (ae.getSource() == ul.getJpfPass()  || ae.getSource()==ul.getJbtnLogin()) { //��й�ȣ
			
			if (ul.getJtfId().getText().equals("") && ul.getJpfPass().getPassword().equals("")) { 
				
				JOptionPane.showMessageDialog(ul, "���̵�� ��й�ȣ�� ��� �Է����ּ���");  
				ul.getJtfId().requestFocus(); //Ŀ���� ���̵� ��ġ
				
			}else if(!ul.getJtfId().getText().equals("") && ul.getJpfPass().getPassword().equals("")) {
				
				ul.getJpfPass().setText(""); //��й�ȣ �ʱ�ȭ
				JOptionPane.showMessageDialog(ul, "��й�ȣ�� �Է����ּ���");  
				ul.getJpfPass().requestFocus(); //Ŀ���� ��й�ȣ ��ġ
				
			}else if(ul.getJtfId().getText().equals("") && !ul.getJpfPass().getPassword().equals("")) {
				ul.getJtfId().setText(""); //���̵� �ʱ�ȭ
				ul.getJpfPass().setText(""); //��й�ȣ �ʱ�ȭ
				JOptionPane.showMessageDialog(ul, "���̵� �Է����ּ���");  
				ul.getJtfId().requestFocus(); //Ŀ���� ���̵� ��ġ
				
			}else {
				
				//�Է��� ��й�ȣ ��������
				String inputPass=new String (ul.getJpfPass().getPassword()); //������ PlainText(��) 
				String inputId=ul.getJtfId().getText(); // ���̵���
				String cipherPass="";
				
					try {
						DataEncrypt de = new DataEncrypt("1111111111111111");
						cipherPass = de.encryption(inputPass);
					} catch (UnsupportedEncodingException uee) {
						uee.printStackTrace();
					} catch (NoSuchAlgorithmException nsae) {
						nsae.printStackTrace();
					} catch (GeneralSecurityException gse) {
						gse.printStackTrace();
					} // end catch
		
				//�Է¹��� ���̵�� ��й�ȣ�� VO�� ����
				UserLoginVO ulVO = new UserLoginVO(inputId, cipherPass);
				//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
				UserDAO uDAO=UserDAO.getInstance(); 
				try {
					String name = uDAO.selectLogin(ulVO);
		
						if( !name.isEmpty() ) { //�Էµ� ���̵�� ��й�ȣ�� ��ġ�ϴ� ������ �ִٸ�
							
							//if else 
							
							new UserMain(ul.getJtfId().getText());
							ul.dispose();
						}else {
//							ul.getJtfId().setText(""); //���̵� �ʱ�ȭ
							ul.getJpfPass().setText(""); //��й�ȣ �ʱ�ȭ
							JOptionPane.showMessageDialog(ul, "���̵� ��й�ȣ�� Ȯ�����ּ���");   
						}//end else
						
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch		
				
			}//end else 
	
		}//end if
		
		if(ae.getSource()==ul.getJbtnJoin()) {
			userJoin();  
		}//end if
			
		if(ae.getSource()==ul.getJbtnID()) {
			findId();
		}//end if

		if(ae.getSource()==ul.getJbtnPass()) {
			findPass();
		}//end if
		
	}//actionPerformed

}//class
