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
		
		if(ae.getSource()==ul.getJtfId()) { //아이디를 입력받으면
			if(!ul.getJtfId().getText().equals("")) { // 아이디가 비어있는지 판단
				ul.getJpfPass().requestFocus();//값이 존재한다면 커서를 비번으로 이동 
			}//end if
		}//end if

		if (ae.getSource() == ul.getJpfPass()  || ae.getSource()==ul.getJbtnLogin()) { //비밀번호
			
			if (ul.getJtfId().getText().equals("") && ul.getJpfPass().getText().equals("")) { ////////////getText()되긴하는데..바꿔야하는지..?/////
				
//				ul.getJtfId().setText(""); //아이디 초기화
//				ul.getJpfPass().setText(""); //비밀번호 초기화
				JOptionPane.showMessageDialog(ul, "아이디 비밀번호를 모두 입력해주세요");   //말바꾸기
				ul.getJtfId().requestFocus(); //커서를 아이디에 위치
				
			}else if(!ul.getJtfId().getText().equals("") && ul.getJpfPass().getText().equals("")) {
				
				ul.getJpfPass().setText(""); //비밀번호 초기화
				JOptionPane.showMessageDialog(ul, "비밀번호를 입력해주세요");   //말바꾸기
				ul.getJpfPass().requestFocus(); //커서를 비밀번호 위치
				

			}else if(ul.getJtfId().getText().equals("") && !ul.getJpfPass().getText().equals("")) {
				ul.getJtfId().setText(""); //아이디 초기화
				ul.getJpfPass().setText(""); //비밀번호 초기화
				JOptionPane.showMessageDialog(ul, "아이디를 입력해주세요");   //말바꾸기
				ul.getJtfId().requestFocus(); //커서를 아이디에 위치
				
			}else {
				
			
				//입력한 비밀번호 가져오기
				String inputPass=new String (ul.getJpfPass().getPassword()); //비번얻기 PlainText(평문) 
				String inputId=ul.getJtfId().getText(); // 아이디얻기
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
		
					//입력받은 아이디와 비밀번호를 VO에 저장
					UserLoginVO ulVO = new UserLoginVO(inputId, cipherPass);
					//DB를 조회하기 위해 DAO객체를 얻기
					UserDAO uDAO=UserDAO.getInstance(); 
					try {
						String name = uDAO.selectLogin(ulVO);
		
							if( !name.isEmpty() ) { //입력된 아이디와 비밀번호에 일치하는 정보가 있다면
								new UserMain(ul.getJtfId().getText());
								ul.dispose();
							}else {
								ul.getJtfId().setText(""); //아이디 초기화
								ul.getJpfPass().setText(""); //비밀번호 초기화
								JOptionPane.showMessageDialog(ul, "일치하는 회원정보가 없습니다");   ///멘트바꾸기
								ul.getJtfId().requestFocus(); //커서를 아이디에 위치
							}//end else
						
					} catch (SQLException e) {
						e.printStackTrace();
					}//end catch		
				
			}//end else 
	
			
		}//end if
		
		if(ae.getSource()==ul.getJbtnJoin()) {
			userJoin();  
			
		}
			
		if(ae.getSource()==ul.getJbtnID()) {
			findId();
		}

		if(ae.getSource()==ul.getJbtnPass()) {
			findPass();
		}
		
	}//actionPerformed

}//class
