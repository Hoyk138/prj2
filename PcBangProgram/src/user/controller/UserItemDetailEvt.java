package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import prj2.user.view.UserItemDetail;

public class UserItemDetailEvt implements ActionListener {
	
	private UserItemDetail uid;
	
	public UserItemDetailEvt(UserItemDetail uid) {
		this.uid=uid;
	}//UserOrderDetailEvt
	
	private void setTotalPrice() {
		
	}//setTotalPrice
	
	private void setPut() {
		
	}//setPut
	
	private void close() {
		uid.dispose();
	}//close

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uid.getJcbQuantity()) { //���������� �̺�Ʈ
			setTotalPrice();
		}//end if
		
		if(ae.getSource()==uid.getJbtPut()) { //�ֹ���� ��ư
			setPut();
		}//end if
		
		if(ae.getSource()==uid.getJbtClose()) { //�ݱ��ư
			close();
		}//end if
		
	}//actionPerformed
	
	

}//class
