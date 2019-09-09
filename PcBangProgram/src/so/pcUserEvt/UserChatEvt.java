package so.pcUserEvt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import so.pcUserView.UserChat;



public class UserChatEvt implements ActionListener, Runnable{
	
	private UserChat uc;
	
	private Socket socketUser;
	private DataInputStream readStream;
	private DataOutputStream writeStream; 
	private Thread thConnect;

	public UserChatEvt(UserChat uc) {
		this.uc=uc;
	}//UserChatEvt

	@Override
	public void run() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uc.getJbtSend() || ae.getSource()==uc.getJtfTalk()) { //대화T.F OR 보내기 버튼
			
		}
	}//actionPerformed


}//class
