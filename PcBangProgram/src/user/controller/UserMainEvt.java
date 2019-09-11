package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user.view.UserChat;
import user.view.UserItem;
import user.view.UserMain;

public class UserMainEvt implements ActionListener{
	
	private UserMain um;
	private UserItem ui;

	public UserMainEvt(UserMain um) {
		this.um=um;
		
		
	}//UserMainEvt
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==um.getJbtOrder()) { //먹거리주문버튼
			//다른 클래스가 띄어지면 다시안띄게 하기
				new UserItem();				
		}//end if
		
		if(ae.getSource()==um.getJbtCounterChat()) { //카운터채팅버튼
			if(um.getJbtCounterChat()==null) {
				new UserChat();				
			}
		}//end if
		
		if(ae.getSource()==um.getJbtAdImage()) { //광고창버튼
			
		}//end if
		
	}//actionPerformed

}//class
