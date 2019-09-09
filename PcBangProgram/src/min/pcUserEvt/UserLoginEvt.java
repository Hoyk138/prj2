package min.pcUserEvt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import min.pcUserView.UserLogin;


public class UserLoginEvt implements ActionListener{

	private UserLogin ul;
	
	public UserLoginEvt(UserLogin ul) {
	
	this.ul=ul;	
	}//UserLoginEvt
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}//actionPerformed

}//class
