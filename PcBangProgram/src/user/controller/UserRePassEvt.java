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
import user.view.FindPass;
import user.view.UserRePass;

public class UserRePassEvt implements ActionListener {

	private UserRePass urp;
	private FindPass fp;
	
	public UserRePassEvt(UserRePass urp) {
		this.urp=urp;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
//		if (ae.getSource()==urp.getJtfId()) { //��й�ȣ�� �缳���� ID �Է¹ޱ�
//			if(!urp.getJtfId().getText().equals("")) { //ID�� �Է� �޾Ҵ��� ����
//				
//				urp.getJpfPass().requestFocus(); //���� �����Ѵٸ� Ŀ���� ��й�ȣ�缳������ �̵�
//			}//end if
//		}//end if
		
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
//			String reId=urp.getJtfId().getText();
			String reId=urp.getJlblId2().getText();
			String rePass=new String(urp.getJpfPass().getPassword());
			String rePassConfirm=new String(urp.getJpfConfirm().getPassword());
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
			
			if(!rePass.equals(rePassConfirm)) {
				JOptionPane.showMessageDialog(urp, "��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�");
				return;
			}//end if
				
			//�Է¹��� ���̵�� ��й�ȣ�� VO�� ����
			UserRePassVO urpVO=new UserRePassVO(reId, cipherPass);
			//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
			UserDAO uDAO=UserDAO.getInstance();
			
			try {
				boolean passUpdate=uDAO.updatePass(urpVO);
				
				if(passUpdate) {
					JOptionPane.showMessageDialog(urp, "��й�ȣ ����Ǿ����ϴ�");
					urp.dispose();
				}else {
//					urp.getJlblId2().setText("");
					urp.getJpfPass().setText("");
					urp.getJpfConfirm().setText("");
					JOptionPane.showMessageDialog(urp, "��й�ȣ ���濡 �����Ͽ����ϴ�.");
					urp.getJpfPass().requestFocus();
				}//end else
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
	
			}//end if
		

		
		}//actionPerformed
		
}//class
