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
import user.VO.FindPassVO;
import user.VO.UserJoinVO;
import user.view.UserJoin;

public class UserJoinEvt implements ActionListener {
	
	private UserJoin uj;
	
	public UserJoinEvt(UserJoin uj) {
		this.uj=uj;
	}//UserJoinEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==uj.getJbtnJoin()) {
			System.out.println("����");
			
		}
		
		
		if (ae.getSource()==uj.getJtfId()) { //ID �Է¹ޱ�
			if(!uj.getJtfId().getText().equals("")) { //ID�� ����ִ��� �Ǵ�
				
				
				uj.getJpfPass().requestFocus(); //���� �����Ѵٸ� Ŀ���� ��й�ȣ�� �̵�
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJpfPass()) {
//			if(!uj.getJpfPass().getSelectedText().equals("")) {   ///////////////getText���getSelectedText?
				if(!uj.getJpfPass().getText().equals("")) {
				
				uj.getJpfPassComfirm().requestFocus();
			}
		}

		if(ae.getSource()==uj.getJpfPassComfirm()) {
			if(!uj.getJpfPassComfirm().getText().equals("")) {   
				
				
				uj.getJtfName().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJtfName()) {
			if(!uj.getJtfName().getText().equals("")) {   
				
				
				uj.getJcbNum().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJcbNum()) {
				
				uj.getJtfPhone2().requestFocus();
				
		}//end if
		
		if(ae.getSource()==uj.getJtfPhone2()) {
			if(!uj.getJtfPhone2().getText().equals("")) {   
				
				
				uj.getJtfPhone3().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJtfPhone3()) {
			if(!uj.getJtfPhone3().getText().equals("")) {   
				
				
				uj.getJcbQuestion().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJcbQuestion()) {
			
			uj.getJtfAnswer().requestFocus();

		}//end if
		
		if(ae.getSource()==uj.getJtfAnswer()) {
			if(!uj.getJtfAnswer().getText().equals("")) {   
				
				
				uj.getJbtnJoin().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJbtnJoin()) {
			System.out.println("����");
			String joinId=uj.getJtfName().getText();
			String joinPw=new String(uj.getJpfPass().getPassword());
			String cipherPass="";
			
			try {
				DataEncrypt de=new DataEncrypt("1111111111111111");
				cipherPass=de.encryption(joinPw);
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
			} catch (NoSuchAlgorithmException nsae) {
				nsae.printStackTrace();
			} catch (GeneralSecurityException gse) {
				gse.printStackTrace();
			} // end catch
			
		
			
			
			
//			String joinPwConfirm=new String(uj.getJpfPassComfirm().getPassword());
			String joinName=uj.getJtfName().getText();
				StringBuilder sbNum=new StringBuilder();
				sbNum.append(uj.getJcbNum().getSelectedItem()).append(uj.getJtfPhone2().getText()).append(uj.getJtfPhone3().getText());
			String joinPhone=sbNum.toString();
				StringBuilder sbQuestion=new StringBuilder();
				sbQuestion.append(uj.getJcbQuestion().getSelectedItem());
			String joinQuestion=sbQuestion.toString();
			String joinAnswer=uj.getJtfAnswer().getText();
			
			//�Է¹��� ���̵�� ��й�ȣȮ������, ���� VO�� ����
			UserJoinVO ujVO=new UserJoinVO(joinId, cipherPass, joinName, joinPhone, joinQuestion, joinAnswer);
			//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
			UserDAO uDAO=UserDAO.getInstance();
			
				try {
					boolean join=uDAO.insertMember(ujVO);
					
					if(join) {
						JOptionPane.showMessageDialog(uj, "ȸ�������� �Ϸ�Ǿ����ϴ�");
					}else {
						uj.getJtfId().setText("");
						uj.getJpfPass().setText("");
						uj.getJpfPassComfirm().setText("");
						uj.getJtfName().setText("");
						uj.getJcbNum().setSelectedIndex(0);         
						uj.getJtfPhone2().setText("");
						uj.getJtfPhone3().setText("");
						uj.getJcbQuestion().setSelectedIndex(0);
						uj.getJtfAnswer().setText("");
						JOptionPane.showMessageDialog(uj, "ȸ�����Կ� �����Ͽ����ϴ�.");
						uj.getJtfId().requestFocus();
						
					}//end else
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch
				
			
		}//end if
		
		
	}//actionPerformed

}//class

























