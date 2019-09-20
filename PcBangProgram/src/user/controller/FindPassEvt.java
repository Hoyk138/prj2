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
			
			//입력받은 아이디와 비밀번호확인질문, 답을 VO에 저장
			FindPassVO fpVO=new FindPassVO(pwId, pwAnswer, pwQuestion );
			//DB를 조회하기 위해 DAO객체를 얻기
			UserDAO uDAO=UserDAO.getInstance();
			
			try {
				boolean pass=uDAO.selectPass(fpVO);
				
				if(pass) {
					new UserRePass(fp);
					fp.dispose();
				}else {
					fp.getJtfId().setText("");//아이디 초기화
					fp.getJtfAnswer().setText("");//비밀번호 확인 질문 답 초기화
					fp.getJcbQuestion().setSelectedIndex(0);
					JOptionPane.showMessageDialog(fp, "일치하는 회원정보가 없습니다");
					fp.getJtfId().requestFocus();
					
				}//end else
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end if
		
		
		
		
	}//actionPerformed

}//class
