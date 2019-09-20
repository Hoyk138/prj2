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
		
//		System.out.println("눌림");
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
		
		//입력받은 아이디와 비밀번호확인질문, 답을 VO에 저장
		UserJoinVO ujVO=new UserJoinVO(joinId, cipherPass, joinName, joinPhone, joinQuestion, joinAnswer);
		//DB를 조회하기 위해 DAO객체를 얻기
		UserDAO uDAO=UserDAO.getInstance();
		
		try {
			uDAO.insertMember(ujVO);
			
				JOptionPane.showMessageDialog(uj, "회원가입이 완료되었습니다");
			
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
			JOptionPane.showMessageDialog(uj, "회원가입에 실패하였습니다.");
			uj.getJtfId().requestFocus();
		}


	}//addMember
	
//	private void confirmId() {
//	
//		//입력받은 아이디와 비밀번호확인질문, 답을 VO에 저장
//		UserJoinVO ujVO=new UserJoinVO(joinId);
//		//DB를 조회하기 위해 DAO객체를 얻기
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
	
		
		if (ae.getSource()==uj.getJtfId()) { //ID 입력받기
			if(!uj.getJtfId().getText().equals("")) { //ID가 비어있는지 판단
				
				
				uj.getJbtnOverlap().requestFocus(); //값이 존재한다면 커서를 아이디 중복확인으로 이동
				
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJbtnOverlap()) {
//			confirmId();
			System.out.println("눌리냐?");
			
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
//					JOptionPane.showMessageDialog(uj, "이미 존재하는 아이디 입니다");
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




























