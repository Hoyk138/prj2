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
	
	private void addMember() {
		
//		System.out.println("����");
		String joinId=uj.getJtfId().getText();
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
		
//		String joinPwConfirm=new String(uj.getJpfPassComfirm().getPassword());
		String joinName=uj.getJtfName().getText();
			StringBuilder sbNum=new StringBuilder();
			sbNum.append(uj.getJcbNum().getSelectedItem()).append(uj.getJtfPhone2().getText()).append(uj.getJtfPhone3().getText());
		String joinPhone=sbNum.toString();
			StringBuilder sbQuestion=new StringBuilder();
			sbQuestion.append(uj.getJcbQuestion().getSelectedItem());
		String joinQuestion=sbQuestion.toString();
		String joinAnswer=uj.getJtfAnswer().getText();
		
		
		System.out.println(joinId+cipherPass+joinName+joinPhone+joinQuestion+joinAnswer);
		
		//�Է¹��� ���̵�� ��й�ȣȮ������, ���� VO�� ����
		UserJoinVO ujVO=new UserJoinVO(joinId, cipherPass, joinName, joinPhone, joinQuestion, joinAnswer);
		//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
		UserDAO uDAO=UserDAO.getInstance();
		
		try {
			uDAO.insertMember(ujVO);
			
				JOptionPane.showMessageDialog(uj, "ȸ�������� �Ϸ�Ǿ����ϴ�");
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		}


	}//addMember
	
//	private void confirmId() {
//	
//		//�Է¹��� ���̵�� ��й�ȣȮ������, ���� VO�� ����
//		UserJoinVO ujVO=new UserJoinVO(joinId);
//		//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
//		UserDAO uDAO=UserDAO.getInstance();
//		
//		try {
//			uDAO.overlapId(ujVO.);
//		
//	}//confirmId

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		String pass=new String(uj.getJpfPass().getPassword());
		String confirm=new String(uj.getJpfPassComfirm().getPassword());
	
		
		if (ae.getSource()==uj.getJtfId()) { //ID �Է¹ޱ�
			if(!uj.getJtfId().getText().equals("")) { //ID�� ����ִ��� �Ǵ�
				
				
				uj.getJbtnOverlap().requestFocus(); //���� �����Ѵٸ� Ŀ���� ���̵� �ߺ�Ȯ������ �̵�
				
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJbtnOverlap()) {
//			confirmId();
			System.out.println("������?");
			
///////////////////////////////////////////////////////////////////////////////////////////////////
//			String joinId=uj.getJtfId().getText();
//			String temp="";
//			
//			UserDAO uDAO=UserDAO.getInstance();
//			
//			try {
//				temp=uDAO.overlapId(joinId);
//				
//				
//				if(!temp.isEmpty()) {
//					uj.getJpfPass().requestFocus(); 
//				}else {
//					JOptionPane.showMessageDialog(uj, "�̹� �����ϴ� ���̵� �Դϴ�");
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}			
/////////////////////////////////////////////////////////////////////////////////////////////////
			
		}
		
		
		
		if(ae.getSource()==uj.getJpfPass()) {
				if(pass.equals("")) {
				
				uj.getJpfPassComfirm().requestFocus();
			}
		}

		if(ae.getSource()==uj.getJpfPassComfirm()) {
			if(confirm.equals("")){   

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
			addMember();
			
		}//end if
		
		
	}//actionPerformed

}//class




























