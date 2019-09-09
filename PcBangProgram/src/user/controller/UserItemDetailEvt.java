package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user.view.UserItemDetail;

public class UserItemDetailEvt implements ActionListener {
	
	private UserItemDetail uod;
	
	public UserItemDetailEvt(UserItemDetail uod) {
		this.uod=uod;
	}//UserOrderDetailEvt
	
	private void setTotalPrice() {
		
	}//setTotalPrice
	
	private void setPut() {
		
	}//setPut
	
	private void close() {
		uod.dispose();
	}//close

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uod.getJcbQuantity()) { //���������� �̺�Ʈ
			setTotalPrice();
		}//end if
		
		if(ae.getSource()==uod.getJbtPut()) { //�ֹ���� ��ư
			setPut();
		}//end if
		
		if(ae.getSource()==uod.getJbtClose()) { //�ݱ��ư
			close();
		}//end if
		
	}//actionPerformed
	
	

}//class
