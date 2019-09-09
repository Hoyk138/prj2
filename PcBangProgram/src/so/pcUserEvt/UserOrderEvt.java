package so.pcUserEvt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import so.pcUserView.UserOrder;

public class UserOrderEvt extends MouseAdapter implements ActionListener{
	
	private UserOrder uo;

	public UserOrderEvt(UserOrder uo) {
		this.uo=uo;
	}//UserOrderEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uo.getJbtOrder()) {
			
		}
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount()==2) { //더블클릭
//			detailUserOrder();
		}//end if
		
	}//mouseClicked
	
	

}//class
