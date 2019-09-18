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
		
		if (ae.getSource()==uj.getJtfName()) { //ID �Է¹ޱ�
			if(!uj.getJtfName().getText().equals("")) { //ID�� ����ִ��� �Ǵ�
				
				uj.getJpfPass().requestFocus(); //���� �����Ѵٸ� Ŀ���� ��й�ȣ�� �̵�
			}//end if
		}//end if
		
		if(ae.getSource()==uj.getJpfPass()) {
			if(!uj.getJpfPass().getSelectedText().equals("")) {   ///////////////getText���getSelectedText�³�
				
				
				
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

























