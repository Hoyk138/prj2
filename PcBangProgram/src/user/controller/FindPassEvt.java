package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.DAO.UserDAO;
import user.VO.FindPassVO;
import user.view.FindPass;
import user.view.UserRePass;

public class FindPassEvt implements ActionListener {

	private FindPass fp;
	
	public FindPassEvt(FindPass fp) {
		this.fp=fp;
	}//FindPassEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==fp.getJtfId()) {
			if(!fp.getJtfId().getText().equals("")) {
				
				fp.getJtfAnswer().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==fp.getJtfAnswer()) {
			if(!fp.getJtfAnswer().getText().equals("")) {
				
				fp.getJbtnFindPass().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==fp.getJbtnFindPass()) {
			String pwId=fp.getJtfId().getText();
			StringBuilder sb=new StringBuilder();
			sb.append(fp.getJcbQuestion().getSelectedItem());
			String pwAnswer=sb.toString();
			String pwQuestion=fp.getJtfAnswer().getText();
			
			//�Է¹��� ���̵�� ��й�ȣȮ������, ���� VO�� ����
			FindPassVO fpVO=new FindPassVO(pwId, pwAnswer, pwQuestion );
			//DB�� ��ȸ�ϱ� ���� DAO��ü�� ���
			UserDAO uDAO=UserDAO.getInstance();
			
			try {
				boolean pass=uDAO.selectPass(fpVO);
				
				if(pass) {
					new UserRePass(fp);
					fp.dispose();
				}else {
					fp.getJtfId().setText("");//���̵� �ʱ�ȭ
					fp.getJtfAnswer().setText("");//��й�ȣ Ȯ�� ���� �� �ʱ�ȭ
					fp.getJcbQuestion().setSelectedIndex(0);
					JOptionPane.showMessageDialog(fp, "��ġ�ϴ� ȸ�������� �����ϴ�");
					fp.getJtfId().requestFocus();
					
				}//end else
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end if
		
		
		
		
	}//actionPerformed

}//class
