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
		if(ae.getSource()==um.getJbtOrder()) { //먹거리주문버튼
			new UserOrder();
		}//end if
		
		if(ae.getSource()==um.getJbtCounterChat()) { //카운터채팅버튼
			new UserChat();
		}//end if
		
		if(ae.getSource()==um.getJbtAdImage()) { //광고창버튼
			
		}//end if
		
	}//actionPerformed

}//class
