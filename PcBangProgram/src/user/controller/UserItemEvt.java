package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import prj2.user.view.UserItem;

public class UserItemEvt extends MouseAdapter implements ActionListener{
	
	private UserItem uo;

	public UserItemEvt(UserItem uo) {
		this.uo=uo;
	}//UserOrderEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uo.getJbtOrder()) {
			
		}
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount()==2) { //����Ŭ��
//			detailUserOrder();
		}//end if
		
	}//mouseClicked
	
	

}//class
