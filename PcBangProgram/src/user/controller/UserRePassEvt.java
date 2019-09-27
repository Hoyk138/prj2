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
		
			
		//�Է¹��� ���̵�� ��й�ȣ�� VO�� ����
		UserRePassVO urpVO=new UserRePassVO(reId, cipherPass);
		//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
		UserDAO uDAO=UserDAO.getInstance();
		
		try {
			boolean passUpdate=uDAO.updatePass(urpVO);
			
			if(!rePass.equals(rePassConfirm)) {
				urp.getJpfConfirm().setText("");
				JOptionPane.showMessageDialog(urp, "��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				urp.getJpfConfirm().requestFocus();
			}else {
				
			
				if(passUpdate) {
					JOptionPane.showMessageDialog(urp, "��й�ȣ�� ����Ǿ����ϴ�");
					urp.dispose();
				}else {
	//				urp.getJlblId2().setText("");
					urp.getJpfPass().setText("");
					urp.getJpfConfirm().setText("");
					JOptionPane.showMessageDialog(urp, "��й�ȣ ���濡 �����Ͽ����ϴ�.");
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
