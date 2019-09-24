package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import user.DAO.UserDAO;
import user.VO.FindIdVO;
import user.view.FindId;

public class FindIdEvt implements ActionListener {

	private FindId fi;
	
	public FindIdEvt (FindId fi) {
		this.fi=fi;
	}
	
	private void findId() {
		
		if(!fi.getJtfName().getText().equals("") && !fi.getJtfPhone2().getText().equals("") && !fi.getJtfPhone3().getText().equals("")) {
			
			String idName=fi.getJtfName().getText();
			StringBuilder sb=new StringBuilder();
			sb.append(fi.getJcbNum().getSelectedItem()).append(fi.getJtfPhone2().getText()).append(fi.getJtfPhone3().getText());
			String idPhone=sb.toString();
			
			//입력받은 이름과 전화번호를 VO에 저장
			FindIdVO fiVO=new FindIdVO(idName, idPhone);
			//DB를 조회하기 위해 DAO객체를 얻기
			UserDAO uDAO=UserDAO.getInstance();
			
			try {
				String id=uDAO.selectId(fiVO);
				
				if(!id.isEmpty()) {
					JOptionPane.showMessageDialog(fi, "회원님의 아이디는 "+id+" 입니다"); 
					fi.dispose();
					
				}else {
					fi.getJtfName().setText(""); //이름 초기화
					fi.getJtfPhone2().setText(""); //전화번호 초기화
					fi.getJtfPhone3().setText(""); //전화번호 초기화
					fi.getJcbNum().setSelectedIndex(0);
					JOptionPane.showMessageDialog(fi, "일치하는 회원정보가 없습니다");
					fi.getJtfName().requestFocus(); //커서를 이름칸에 위치
					
				}//end else
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//catch
			}else {
				JOptionPane.showMessageDialog(fi, "정보를 모두 입력해주세요");  
			}//end else
		
	}//findId
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==fi.getJtfName()) { //이름 입력받기
			if(!fi.getJtfName().getText().equals("")) { //이름이 비어있는지 판단
				fi.getJtfPhone2().requestFocus(); //값이 존재한다면 커서를 전화번호중간자리로 이동
			}//end if
		}//end if
		
		if(ae.getSource()==fi.getJcbNum()) {
			fi.getJtfPhone2().requestFocus();
		}//end if
		
		if(ae.getSource()==fi.getJtfPhone2()) {
			if(!fi.getJtfPhone2().getText().equals("")) {

					
				fi.getJtfPhone3().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==fi.getJtfPhone3()) {
			if(!fi.getJtfPhone2().getText().equals("")) {
				fi.getJbtnFindID().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==fi.getJbtnFindID()) {
			findId();
		}//end if
		
	}//actionPerformed
	
}//class
