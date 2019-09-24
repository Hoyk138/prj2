package user.helper;

import javax.swing.JLabel;

import user.view.UserMain;

public class TimeThread extends Thread{
	
	private UserMain um;
	private JLabel jlUseTime;
	
	public TimeThread(JLabel jlUseTime) {
		this.jlUseTime=jlUseTime;
		
	}//TimeThread
	
	@Override
	public void run() {
		
//		// 1초*60=1분 -> 1분*60=1시간
		int startTime=(int)System.currentTimeMillis()/1000;
		
		while(true) {
			
			//사용시간 thread
			int nowTime=(int)System.currentTimeMillis()/1000;
			int useTime=nowTime-startTime;
			
//				int sec=useTime%60; //초
			int min=useTime/60%60; //분
			int hour=useTime/3600; //시
			
			//1초간 
			String time=String.format("%2d : %2d 분", hour,min);
			
			jlUseTime.setText(time);
			
			try {
				Thread.sleep(60000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end catch 
		}//end while
		
	}//run

}//class
