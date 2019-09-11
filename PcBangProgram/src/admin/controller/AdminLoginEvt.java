package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.dao.AdminDAO;
import admin.view.AdminLogin;
import admin.vo.AdminLoginVO;
import kr.co.sist.util.cipher.DataEncrypt;

public class AdminLoginEvt implements ActionListener{
	
	private AdminLogin al;
	
	public AdminLoginEvt(AdminLogin al) {
		
	this.al=al;	
	}//AdminLoginEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		//맨처음에 창뜰때 커서가 아이디에 있었으면 좋겠어
		
		if(ae.getSource()==al.getJtfId()) { //아이디를 입력받으면
			if(!al.getJtfId().getText().equals("")) { // 아이디가 비어있는지 판단
				al.getJpfPass().requestFocus();//값이 존재한다면 커서를 비번으로 이동 
			}//end if
		}//end if

		if(ae.getSource()==al.getJpfPass() || ae.getSource()==al.getJbtnLogin()) { //비밀번호
			
			//입력한 비밀번호 가져오기 : char[]을 작업의 편의성을 위해 String에 할당
			String inputPass=new String (al.getJpfPass().getPassword()); //비번얻기 PlainText(평문) 
			String inputld=al.getJtfId().getText(); // 아이디얻기
			String shaPass="";
			
			try {
				//PlainText -> CipheText로 변환
				shaPass=DataEncrypt.messageDigest("MD5", inputPass);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}//end catch

			//입력받은 아이디와 비밀번호를 VO에 저장
			AdminLoginVO alVO=new AdminLoginVO(inputld, shaPass);
			//DB를 조회하기 위해 DAO객체를 얻기
			AdminDAO aDAO=AdminDAO.getInstance(); 
			try {
				String name = aDAO.selectLogin(alVO);
					
				if( !name.isEmpty() ) { //입력된 아이디와 비밀번호에 일치하는 정보가 있다면
					System.out.println(name);
					al.dispose();
				}else {
					al.getJtfId().setText(""); //아이디 초기화
					al.getJpfPass().setText(""); //비밀번호 초기화
					JOptionPane.showMessageDialog(al, "아이디 혹은 비밀번호를 확인해주세요");
					al.getJtfId().requestFocus(); //커서를 아이디에 위치
				}//end if
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch			
		}//end if
		
	}//actionPerformed
	

		
}//class
