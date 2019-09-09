package so.pcUserEvt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import so.pcUserView.UserChat;
import so.pcUserView.UserMain;
import so.pcUserView.UserOrder;


public class UserMainEvt implements ActionListener{
	
	private UserMain um;

	public UserMainEvt(UserMain um) {
		this.um=um;
	}//UserMainEvt
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==um.getJbtOrder()) { //�԰Ÿ��ֹ���ư
			new UserOrder();
		}//end if
		
		if(ae.getSource()==um.getJbtCounterChat()) { //ī����ä�ù�ư
			new UserChat();
		}//end if
		
		if(ae.getSource()==um.getJbtAdImage()) { //����â��ư
			
		}//end if
		
	}//actionPerformed

}//class