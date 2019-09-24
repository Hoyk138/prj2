package admin.helper;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import admin.DAO.heeDAO;
import admin.VO.OrderViewVO;
import admin.view.OrderView;

public class OrderThread extends Thread{
		
		private OrderView ov;
	
		public OrderThread(OrderView ov){
			this.ov=ov;
		}//�⺻������
		
		public void run() {
		
		
		while(true) {
			ov.getDtmOrder().setRowCount(0);
			
			Object[] rowData = null;
			
			heeDAO hDAO = heeDAO.getInstance();
			
		try {
			List<OrderViewVO> list = hDAO.OrderView();
			OrderViewVO ovVO = null;

			
			for (int i = 0; i < list.size(); i++) {
				ovVO = list.get(i);
				rowData = new Object[8];
				rowData[0] = ovVO.getOrderCode();
				rowData[1] = ovVO.getPcNum();
				rowData[2] = ovVO.getOrderDate();
				rowData[3] = ovVO.getId();
				rowData[4] = ovVO.getQuantity();
				rowData[5] = ovVO.getName();
				rowData[6] = ovVO.getTotalPrice();
				rowData[7] = ovVO.getPayment_time();
				ov.getDtmOrder().addRow(rowData);
				
			} // end for
			
			try {
				Thread.sleep(1000*20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//run
}//end while
}