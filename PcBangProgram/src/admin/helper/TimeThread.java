package admin.helper;

import javax.swing.JLabel;

public class TimeThread extends Thread{
	
	private JLabel jlUseTime;
	
	private boolean flag;
	
	public TimeThread(JLabel jlUseTime) {
		this.jlUseTime=jlUseTime;
		
	}//TimeThread
	
	@Override
	public void run() {
		
//		// 1초*60=1분 -> 1분*60=1시간
		int startTime=(int)System.currentTimeMillis()/1000;
		
		while(!flag) {
			
			//사용시간 thread
			int nowTime=(int)System.currentTimeMillis()/1000;
			int useTime=nowTime-startTime;
			
//				int sec=useTime%60; //초
			int min=useTime/60%60; //분
			int hour=useTime/3600; //시
			
			//1초간 
			String time=String.format("사용 시간: %2d : %2d 분", hour,min);
			
			jlUseTime.setText(time);
			
			try {
				Thread.sleep(60000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end catch 
		}//end while
		
	}//run

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}//class
