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
		
		
		int price=0; ////5�д� 100�� ����
		while(!flag) {
			price=price+100;
			//���ݾ� thread
			
			jlUsePrice.setText("��� ���: "+String.valueOf(price)+"��");
			try {
				Thread.sleep(300*1000); //5�и��� ���� ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end catch 
		}//end while
		
	}//run

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}//class
