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
		
//		// 1��*60=1�� -> 1��*60=1�ð�
		int startTime=(int)System.currentTimeMillis()/1000;
		
		while(!flag) {
			
			//���ð� thread
			int nowTime=(int)System.currentTimeMillis()/1000;
			int useTime=nowTime-startTime;
			
//				int sec=useTime%60; //��
			int min=useTime/60%60; //��
			int hour=useTime/3600; //��
			
			//1�ʰ� 
			String time=String.format("��� �ð�: %2d : %2d ��", hour,min);
			
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
