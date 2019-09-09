package so.pcUserEvt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import so.pcUserView.UserOrderDetail;

public class UserOrderDetailEvt implements ActionListener {
	
	private UserOrderDetail uod;
	
	public UserOrderDetailEvt(UserOrderDetail uod) {
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
