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
	public void actionPerformed(ActionEvent e) {

	}//actionPerformed

}//class
