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
import user.VO.FindIdVO;
import user.VO.UserRePassVO;
import user.view.UserRePass;

public class UserRePassEvt implements ActionListener {

	private UserRePass urp;
	
	public UserRePassEvt(UserRePass urp) {
		this.urp=urp;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==urp.getJtfId()) { //비밀번호를 재설정할 ID 입력받기
			if(!urp.getJtfId().getText().equals("")) { //ID를 입력 받았는지 판잔
				
				urp.getJpfPass().requestFocus(); //값이 존재한다면 커서를 비밀번호재설정으로 이동
			}//end if
		}//end if
		
		if(ae.getSource()==urp.getJpfPass()) {
			if(!new String(urp.getJpfPass().getPassword()).isEmpty()) {
				urp.getJpfConfirm().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==urp.getJpfConfirm()) {
			if(!new String(urp.getJpfPass().getPassword()).isEmpty()) {			//if(!urp.getJpfConfirm().getText().equals("")) {
				urp.getJbtnResetPass().requestFocus();
				
			}//end if
		}//end if

		if(ae.getSource()==urp.getJbtnResetPass()) {
			String reID=urp.getJtfId().getText();
			String rePass=urp.getJpfPass().getText();
			String cipherPass="";
			
				try {
					DataEncrypt de=new DataEncrypt("1111111111111111");
					cipherPass=de.encryption(rePass);
				} catch (UnsupportedEncodingException uee) {
					uee.printStackTrace();
				} catch (NoSuchAlgorithmException nsae) {
					nsae.printStackTrace();
				} catch (GeneralSecurityException gse) {
					gse.printStackTrace();
				} // end catch
			
			
			//입력받은 아이디와 비밀번호를 VO에 저장
			UserRePassVO urpVO=new UserRePassVO(reID, cipherPass);
			//DB를 조회하기 위해 DAO객체를 얻기
			UserDAO uDAO=UserDAO.getInstance();
			
			try {
				boolean passUpdate=uDAO.updatePass(urpVO);
				
				if(passUpdate) {
					JOptionPane.showMessageDialog(urp, "비밀번호 변경되었습니다");
				}else {
					urp.getJtfId().setText("");
					urp.getJpfPass().setText("");
					urp.getJpfConfirm().setText("");
					JOptionPane.showMessageDialog(urp, "비밀번호 변경에 실패하였습니다.");
					urp.getJtfId().requestFocus();
				}//end else
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
	
			}//end if
		
		
		}//actionPerformed
		
}//class
