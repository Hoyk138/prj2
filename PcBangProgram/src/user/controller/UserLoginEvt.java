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
		
		if(ae.getSource()==ul.getJtfId()) { //아이디를 입력받으면
			if(!ul.getJtfId().getText().equals("")) { // 아이디가 비어있는지 판단
				ul.getJpfPass().requestFocus();//값이 존재한다면 커서를 비번으로 이동 
			}//end if
		}//end if

		if (ae.getSource() == ul.getJpfPass()  || ae.getSource()==ul.getJbtnLogin()) { //비밀번호
			
			//입력한 비밀번호 가져오기
			String inputPass=new String (ul.getJpfPass().getPassword()); //비번얻기 PlainText(평문) 
			String inputld=ul.getJtfId().getText(); // 아이디얻기

			//이거말고 AES로 다시
//			String shaPass="";
//			
//			try {
//				//PlainText -> CipheText로 변환
//				shaPass=DataEncrypt.messageDigest("MD5", inputPass);
//			} catch (NoSuchAlgorithmException e1) {
//				e1.printStackTrace();
//			}//end catch

			//입력받은 아이디와 비밀번호를 VO에 저장
			UserLoginVO ulVO=new UserLoginVO(inputld, inputPass);
			//DB를 조회하기 위해 DAO객체를 얻기
			UserDAO uDAO=UserDAO.getInstance(); 
			try {
				String name = uDAO.selectLogin(ulVO);
					
				if( !name.isEmpty() ) { //입력된 아이디와 비밀번호에 일치하는 정보가 있다면
					new UserMain();
					ul.dispose();
				}else {
					ul.getJtfId().setText(""); //아이디 초기화
					ul.getJpfPass().setText(""); //비밀번호 초기화
					JOptionPane.showMessageDialog(ul, "아이디 혹은 비밀번호를 확인해주세요");
					ul.getJtfId().requestFocus(); //커서를 아이디에 위치
				}//end if
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch			
			
			
		}//end if
		
		
		if(ae.getSource()==ul.getJbtnJoin()) {
			System.out.println("가입가입");
			userJoin();  
			
		}
			
		if(ae.getSource()==ul.getJbtnID()) {
			System.out.println("ID찾기");
			findId();
		}

		if(ae.getSource()==ul.getJbtnPass()) {
			System.out.println("PW찾기");
			findPass();
		}
		
	}//actionPerformed

}//class
