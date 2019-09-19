package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import admin.DAO.CalcItemDAO;
import admin.VO.CalcItemReciptVO;
import admin.VO.CalcItemVO;
import admin.view.CalcView;

public class CalcItemEvt extends MouseAdapter implements ActionListener {

	private CalcView cv;

	public CalcItemEvt(CalcView cv) {
		this.cv = cv;
//		setCalcItemList();
	} // CalcItemEvt

	private void viewCalcItemRecipt() {
		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		
		// <��ǰ��, ����>
		Map<String, Integer> itemQMap = new HashMap<String, Integer>() ;
		
		// <��ǰ��, ����>
		Map<String, Integer> itemPMap = new HashMap<String, Integer>() ;
		
		try {
			List<CalcItemReciptVO> list = ciDAO.selectCalcItemRecipt();

			JTextArea jta = new JTextArea(20, 50);
			JScrollPane jsp = new JScrollPane(jta);
			
			if (list.isEmpty()) {
//				jta.append("������ �� �ִ� �ŷ� ����� �����ϴ�.");
				JOptionPane.showMessageDialog(null, "������ �� �ִ� �ŷ� ����� �����ϴ�.");
				return;
			} // end if

			jta.append(
					"------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("��ȣ\t��ǰ��\t����\t����\n");
			jta.append("=====================================================================\n");

			CalcItemReciptVO cirVO = null;
			int totalCnt = 0 ;
			int totalPrice = 0 ;
			
			for (int i = 0; i < list.size(); i++) {
				cirVO = list.get(i);
				
				// itemQMap
				if (itemQMap.containsKey(cirVO.getItemName())) {
					itemQMap.put(cirVO.getItemName(), itemQMap.get(cirVO.getItemName()) + (cirVO.getQuantity()) ) ;
				} else {
					itemQMap.put(cirVO.getItemName(), cirVO.getQuantity()) ;
				} // end if
				
				itemPMap.put(cirVO.getItemName(), cirVO.getPrice()) ;
				
				totalCnt += cirVO.getQuantity() ;
				totalPrice += cirVO.getorderedPrice() ;
			} // end for
			
			Set<String> keyQ = itemQMap.keySet() ;
			
			Iterator<String> itaQ = keyQ.iterator() ;
			String itemName = "" ;
			int i = 1 ;
			while ( itaQ. hasNext() ) {
				itemName = itaQ.next() ;
				jta.append(i + "\t" + itemName + "\t" + itemQMap.get(itemName) + "\t" + itemQMap.get(itemName)*itemPMap.get(itemName) + "\n");
				i++ ;
			} // end while
			
			jta.append(
					"------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("�� �Ǹ� ���� : " + totalCnt + "��,\t�� ���� : " +  totalPrice + "��");

			JOptionPane.showMessageDialog(null, jsp);

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS�� ������ �߻��߽��ϴ�.");
			Se.printStackTrace();
		} // end catch

	} // CalcItmeEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == cv.getJbtCalcItem()) {
			viewCalcItemRecipt();
		} // end if

	}

	public void setCalcItemList() {
		Object[] rowData = null;

		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		
		try {
			List<CalcItemVO> list = ciDAO.selectCalcItem();

			DefaultTableModel dtm = cv.getDtmCalcItem();
			dtm.setRowCount(0);
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "���� �̿� ������ �����ϴ�.");
				return;
			} // end if


			CalcItemVO ciVO = null;

			for (int i = 0; i < list.size(); i++) {
				ciVO = list.get(i);

				rowData = new Object[6];

				rowData[0] = ciVO.getOrder_code();
				rowData[1] = ciVO.getPCnum();
				rowData[2] = ciVO.getId();
				rowData[3] = ciVO.getItemName();
				rowData[4] = ciVO.getQuantity();
				rowData[5] = ciVO.getPrice();

				dtm.addRow(rowData);

			} // end for

		} catch (SQLException se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			se.printStackTrace();
		} // end catch

	} // setCalcItemList

	public void mouseClicked(MouseEvent me) {

		if (me.getSource() == cv.getJtp()) { // �ֹ� ���� ������ �� �̺�Ʈ ó�� => �ֹ���Ȳ ��ȸ ����
			JTabbedPane jtptemp = (JTabbedPane) me.getSource();
			if (jtptemp.getSelectedIndex() == 1) {
//					if (cv == null) { // ��ȸ Thread�� �����Ǿ� ���� ����(�ֹ���ȸX)
//						ot = new OrderThread(cv.getJtOrderList(), cv.getDtmOrderList()); // ���õ� ���� ��, ���� �ִ� ��
				setCalcItemList();
			} // end if
		} // end if

	} // end mouseClicked

	// �����׽�Ʈ��
//	public static void main(String[] args) {
//		new CalcItemEvt();
//	} // main

} // class
