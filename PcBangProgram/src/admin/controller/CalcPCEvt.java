package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import admin.DAO.CalcPCDAO;
import admin.VO.CalcPCVO;
import admin.view.CalcView;

public class CalcPCEvt implements ActionListener {
	
	private CalcView cv ;
	
	public CalcPCEvt(CalcView cv) {
		this.cv = cv ;
		setCalcPCList();
	} // CalcPCEvt
	
	private void CalcPCEvt() {
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			
			JTextArea jta = new JTextArea(20, 50) ;
			JScrollPane jsp = new JScrollPane(jta) ;
			if (list.isEmpty()) {
				jta.append("������ �� �ִ� �ŷ� ����� �����ϴ�.");
			} // end if
			
			jta.append("------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("��ȣ\t�̿� �ð�\t�̿� �ݾ�\n");
			jta.append("=====================================================================\n");
			
			CalcPCVO cv = null ;
//			int totalCnt = 0 ;
//			int totalPrice = 0 ;
			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i) ;
				//useTime, int num, int totalCnt, int price, int totalPrice
				jta.append((i+1) + "\t" + cv.getPcNum() + "\t" + cv.getUseTime() + "\t" + "\n");
//				totalCnt += cv.getTotalCnt() ;
//				totalPrice += cv.getTotalPrice() ;
			} // end for
			jta.append("------------------------------------------------------------------------------------------------------------------------\n");
//			jta.append("�� �� : " + totalCnt + "\t, �� ���� : " +  totalPrice +"\t��");
			

//			JOptionPane.showMessageDialog(cv, jsp);
			JOptionPane.showMessageDialog(null, jsp);
			
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS�� ������ �߻��߽��ϴ�.");
			Se.printStackTrace();
		} // end catch
		
	} // CalcPCEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == cv.getJbtCalcPC()) {
			CalcPCEvt();
		} // end if
	}
		
	public void setCalcPCList() {
		DefaultTableModel dtm = cv.getDtmCalcPC() ;
		
		dtm.setRowCount(0);
		Object[] rowData = null ;
		
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
			} // end if
			
			CalcPCVO cv = null ;
			
			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i) ;
				
				rowData = new Object[5] ;
				
				rowData[0] = cv.getPcNum() ;
				rowData[1] = cv.getPcCode() ;
				rowData[2] = cv.getId() ;
				rowData[3] = cv.getUseTime() ;
				rowData[4] = cv.getUseFee() ;
				
				dtm.addRow(rowData);
			} // end for
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCList
	
	
//		public void mouseClicked(MouseEvent me) {
//
//			if (me.getSource() == cv.getJtp()) { // �ֹ� ���� ������ �� �̺�Ʈ ó�� => �ֹ���Ȳ ��ȸ ����
//				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
//				if (jtptemp.getSelectedIndex() == 1) {
//					if (ot == null) { // ��ȸ Thread�� �����Ǿ� ���� ����(�ֹ���ȸX)
//						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // ���õ� ���� ��, ���� �ִ� ��
//						ot.start();
//					} // end if
//				} // end if

//			} // end if
		
	// �����׽�Ʈ��
//	public static void main(String[] args) {
//		new CalcPCEvt();
//	} // main

} // class
