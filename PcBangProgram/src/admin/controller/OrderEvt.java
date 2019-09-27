package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import admin.DAO.AdminDAO;
import admin.VO.OrderViewVO;
import admin.view.OrderView;

public class OrderEvt implements ActionListener{
	
	private OrderView ov;
	public OrderEvt(OrderView ov) {
		this.ov=ov;
		//paymentRealTime();
	}//������
	
	public void paymentRealTime() {
		
		DefaultTableModel dtmF = ov.getDtmOrder();

		dtmF.setRowCount(0);
		Object[] rowData = null;

//		heeDAO hDAO = heeDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
//			List<OrderViewVO> list = hDAO.OrderView();
			List<OrderViewVO> list = aDAO.OrderView();
			OrderViewVO ovVO = null;

//			int orderNum=1;
			
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
				dtmF.addRow(rowData);
				
			} // end for

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}//paymentRealTime
	
	public void stateChange() {
		int selectRow=ov.getJtOrder().getSelectedRow();
		
		if(selectRow==-1) { //�����Ϸ��� ���� ���õ��� �ʾ��� ��.
			JOptionPane.showMessageDialog(ov,"������ ���� �����Ͽ� �ּ���");
			return;		
		}//end if
		
		String orderCode = ov.getJtOrder().getValueAt(ov.getJtOrder().getSelectedRow(), 0).toString();
//		heeDAO hDAO = heeDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();
		int cnt = 0;
		try {
//		    cnt = hDAO.orderState(orderCode,ov);
			cnt = aDAO.orderState(orderCode, ov);
		} catch (SQLException e) {
			e.printStackTrace();
		} // catch
		if (cnt == 1) {
			JOptionPane.showMessageDialog(ov, "��ǰ�� �����Ǿ����ϴ�.");
			paymentRealTime();
		} else {
			JOptionPane.showMessageDialog(ov, "��ǰ�� �������� �߽��ϴ�.");
		} // end if
		
	}//stateChange
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==ov.getJmState()) {
			stateChange();
		}//end if
	}//actionPerformed
	
}
