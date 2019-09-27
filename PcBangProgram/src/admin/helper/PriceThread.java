package admin.helper;

import javax.swing.JLabel;

public class PriceThread extends Thread{
	
	private JLabel jlUsePrice;
	
	private boolean flag;
	
	public PriceThread(JLabel jlUsePrice) {
		this.jlUsePrice=jlUsePrice;
		
	}//PriceThread
	
	@Override
	public void run() {
		
		
		int price=0; ////5분당 100원 가격
		while(!flag) {
			price=price+100;
			//사용금액 thread
			
			jlUsePrice.setText("사용 요금: "+String.valueOf(price)+"원");
			try {
				Thread.sleep(300*1000); //5분마다 가격 증가
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end catch 
		}//end while
		
	}//run

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}//class
