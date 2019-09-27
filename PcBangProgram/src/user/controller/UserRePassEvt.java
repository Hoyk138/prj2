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
import user.VO.UserRePassVO;
import user.view.UserRePass;

public class UserRePassEvt implements ActionListener {

	private UserRePass urp;
//	private FindPass fp;
	
	public UserRePassEvt(UserRePass urp) {
		this.urp=urp;
	}//UserRePassEvt
	
	private void resetPass() {
		
		String reId=urp.getJlblId2().getText().trim();
		String rePass=new String(urp.getJpfPass().getPassword()).trim();
		String rePassConfirm=new String(urp.getJpfConfirm().getPassword()).trim();
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
		UserRePassVO urpVO=new UserRePassVO(reId, cipherPass);
		//DB를 조회하기 위해 DAO객체를 얻기
		UserDAO uDAO=UserDAO.getInstance();
		
		try {
			boolean passUpdate=uDAO.updatePass(urpVO);
			
			if(!rePass.equals(rePassConfirm)) {
				urp.getJpfConfirm().setText("");
				JOptionPane.showMessageDialog(urp, "비밀번호가 일치하지 않습니다");
				urp.getJpfConfirm().requestFocus();
			}else {
				
			
				if(passUpdate) {
					JOptionPane.showMessageDialog(urp, "비밀번호가 변경되었습니다");
					urp.dispose();
				}else {
	//				urp.getJlblId2().setText("");
					urp.getJpfPass().setText("");
					urp.getJpfConfirm().setText("");
					JOptionPane.showMessageDialog(urp, "비밀번호 변경에 실패하였습니다.");
					urp.getJpfConfirm().requestFocus();
				}//end else
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//resetPass
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		if(ae.getSource()==urp.getJpfPass()) {
			if(!new String(urp.getJpfPass().getPassword()).isEmpty()) {
				urp.getJpfConfirm().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==urp.getJpfConfirm()) {
			if(!new String(urp.getJpfPass().getPassword()).isEmpty()) {		
				urp.getJbtnResetPass().requestFocus();
				
			}//end if
		}//end if

		if(ae.getSource()==urp.getJbtnResetPass()) {
			resetPass();
		}//end if
		
	}//actionPerformed
		
}//class
