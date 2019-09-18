package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user.view.UserJoin;

public class UserJoinEvt implements ActionListener {
	
	private UserJoin uj;
	
	public UserJoinEvt(UserJoin uj) {
		this.uj=uj;
	}//UserJoinEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource()==uj.getJtfName()) { //ID 입력받기
			if(!uj.getJtfName().getText().equals("")) { //ID가 비어있는지 판단
				
				uj.getJpfPass().requestFocus(); //값이 존재한다면 커서를 비밀번호로 이동
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJpfPass()) {
			if(!uj.getJpfPass().getSelectedText().equals("")) {   ///////////////getText대신getSelectedText맞나
				
				
				
				uj.getJpfPassComfirm().requestFocus();
			}
		}

		if(ae.getSource()==uj.getJpfPassComfirm()) {
			if(!uj.getJpfPassComfirm().getSelectedText().equals("")) {   
				
				
				uj.getJtfName().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJtfName()) {
			if(!uj.getJtfName().getSelectedText().equals("")) {   
				
				
				uj.getJcbNum().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJcbNum()) {
				
				uj.getJtfPhone2().requestFocus();
				
		}//end if
		
		if(ae.getSource()==uj.getJtfPhone2()) {
			if(!uj.getJtfPhone2().getSelectedText().equals("")) {   
				
				
				uj.getJtfPhone3().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJtfPhone3()) {
			if(!uj.getJtfPhone3().getSelectedText().equals("")) {   
				
				
				uj.getJcbQuestion().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJcbQuestion()) {
			
			uj.getJtfAnswer().requestFocus();

		}//end if
		
		if(ae.getSource()==uj.getJtfAnswer()) {
			if(!uj.getJtfAnswer().getSelectedText().equals("")) {   
				
				
				uj.getJbtnJoin().requestFocus();
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJbtnJoin()) {
			String joinId=uj.getJtfName().getText();
			
			
			
		}//end if
		
		
	}//actionPerformed

}//class

























