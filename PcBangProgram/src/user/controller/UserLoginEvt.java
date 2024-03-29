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
	
	private String adminIP;
	private int pcNum;
	
	public UserLoginEvt(UserLogin ul, String adminIP, int pcNum) {
		this.ul = ul;
		this.adminIP = adminIP;
		this.pcNum = pcNum;

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
	
	private boolean checkLogin() {
		
		boolean flag=false;
		String checkLoginId=ul.getJtfId().getText().trim();
		String temp="";
		
		UserDAO uDAO=UserDAO.getInstance();
		
			try {
				temp=uDAO.checkLogin(checkLoginId);
				
					if(temp==null) {
						JOptionPane.showMessageDialog(ul, "이미 사용중인 아이디 입니다");
						ul.getJtfId().requestFocus(); 
					}else { 
						flag=true;
					}
		
			} catch (SQLException e) {
				e.printStackTrace();
				
			}//end catch
			return flag;
		
	}//checkLogin
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(ae.getSource()==ul.getJtfId()) { //아이디를 입력받으면
			if(!ul.getJtfId().getText().equals("")) { // 아이디가 비어있는지 판단
				ul.getJpfPass().requestFocus();//값이 존재한다면 커서를 비번으로 이동 
			}//end if
		}//end if

		if (ae.getSource() == ul.getJpfPass()  || ae.getSource()==ul.getJbtnLogin()) { //비밀번호
		
			/////////////////////////////////////
			
			if(!checkLogin()) {
				return;
			}//end if
			
			/////////////////////////////////////
			
			if (ul.getJtfId().getText().equals("") && ul.getJpfPass().getPassword().length==0) { 
				
				JOptionPane.showMessageDialog(ul, "아이디와 비밀번호를 입력해주세요");  
				ul.getJtfId().requestFocus(); //커서를 아이디에 위치
				
			}else if(!ul.getJtfId().getText().equals("") && ul.getJpfPass().getPassword().length==0)  {
				
				ul.getJpfPass().setText(""); //비밀번호 초기화
				JOptionPane.showMessageDialog(ul, "비밀번호를 입력해주세요");  
				ul.getJpfPass().requestFocus(); //커서를 비밀번호 위치
				
			}else if(ul.getJtfId().getText().equals("") && ul.getJpfPass().getPassword().length>0)   {
				ul.getJtfId().setText(""); //아이디 초기화
				ul.getJpfPass().setText(""); //비밀번호 초기화
				JOptionPane.showMessageDialog(ul, "아이디를 입력해주세요");  
				ul.getJtfId().requestFocus(); //커서를 아이디에 위치
				
			}else {
				
				//입력한 비밀번호 가져오기
				String inputPass=new String (ul.getJpfPass().getPassword()).trim(); //비번얻기 PlainText(평문) 
				String inputId=ul.getJtfId().getText().trim(); // 아이디얻기
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
							
							//if else 
							
							new UserMain(ul.getJtfId().getText().trim(), adminIP, pcNum);
							ul.dispose();
						}else {
//							ul.getJtfId().setText(""); //아이디 초기화
							ul.getJpfPass().setText(""); //비밀번호 초기화
							JOptionPane.showMessageDialog(ul, "아이디나 비밀번호를 확인해주세요");   
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
