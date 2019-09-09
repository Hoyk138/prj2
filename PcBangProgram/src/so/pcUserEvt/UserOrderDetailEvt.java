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
		if(ae.getSource()==uod.getJcbQuantity()) { //수량에서의 이벤트
			setTotalPrice();
		}//end if
		
		if(ae.getSource()==uod.getJbtPut()) { //주문담기 버튼
			setPut();
		}//end if
		
		if(ae.getSource()==uod.getJbtClose()) { //닫기버튼
			close();
		}//end if
		
	}//actionPerformed
	
	

}//class
