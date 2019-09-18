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
			System.out.println("눌림");
			
		}
		
		
		if (ae.getSource()==uj.getJtfId()) { //ID 입력받기
			if(!uj.getJtfId().getText().equals("")) { //ID가 비어있는지 판단
				
				
				uj.getJpfPass().requestFocus(); //값이 존재한다면 커서를 비밀번호로 이동
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJpfPass()) {
//			if(!uj.getJpfPass().getSelectedText().equals("")) {   ///////////////getText대신getSelectedText?
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
			System.out.println("눌림");
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
			
			//입력받은 아이디와 비밀번호확인질문, 답을 VO에 저장
			UserJoinVO ujVO=new UserJoinVO(joinId, cipherPass, joinName, joinPhone, joinQuestion, joinAnswer);
			//DB를 조회하기 위해 DAO객체를 얻기
			UserDAO uDAO=UserDAO.getInstance();
			
				try {
					boolean join=uDAO.insertMember(ujVO);
					
					if(join) {
						JOptionPane.showMessageDialog(uj, "회원가입이 완료되었습니다");
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
						JOptionPane.showMessageDialog(uj, "회원가입에 실패하였습니다.");
						uj.getJtfId().requestFocus();
						
					}//end else
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch
				
			
		}//end if
		
		
	}//actionPerformed

}//class

























