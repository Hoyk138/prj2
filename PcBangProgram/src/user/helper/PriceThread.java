package user.helper;

import javax.swing.JLabel;

import user.view.UserMain;

public class PriceThread extends Thread{
	
	private UserMain um;
	private JLabel jlUsePrice;
	
	public PriceThread(JLabel jlUsePrice) {
		this.jlUsePrice=jlUsePrice;
		
	}//PriceThread
	
	@Override
	public void run() {
		
		
		int price=0; ////5�д� 100�� ����
		while(true) {
			price=price+100;
			//���ݾ� thread
			
			jlUsePrice.setText(String.valueOf(price)+"��");
			System.out.println(price);
			try {
				Thread.sleep(300*1000); //5�и��� ���� ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//end catch 
		}//end while
		
	}//run

}//class
