package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
	
	private void viewCalcPCRecipt() {
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		
		Map<Integer, Integer> pcMap = new HashMap<Integer, Integer>() ;
		
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			
			JTextArea jta = new JTextArea(20, 50) ;
			JScrollPane jsp = new JScrollPane(jta) ;
			if (list.isEmpty()) {
				jta.append("������ �� �ִ� �ŷ� ����� �����ϴ�.");
			} // end if
			
			jta.append("------------------------------------------------------------------------------------------------------------\n");
			jta.append("\tPC��ȣ\t�̿� �ð�(��)\t�̿� �ݾ�(��)\n");
			jta.append("==============================================================\n");
			
			CalcPCVO cv = null ;
			int totalUseTime = 0 ;
			int totalPrice = 0 ;
			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i) ;
				
				if (pcMap.containsKey(cv.getPcNum())) {
					pcMap.put(cv.getPcNum(), pcMap.get(cv.getPcNum()) + cv.getUseTime()) ;
				} else {
					pcMap.put(cv.getPcNum(), cv.getUseTime()) ;
				} // end if
				
				//useTime, int num, int totalCnt, int price, int totalPrice
//				jta.append((i+1) + "\t" + cv.getPcNum() + "\t" + pcMap.get(cv.getPcNum()) + "\t" + cv.getUseFee()+ "\n");
				totalUseTime += cv.getUseTime() ;
				totalPrice += cv.getUseFee() ;
			} // end for
			
			Set<Integer> keys = pcMap.keySet ( ) ;
			
			Iterator<Integer> ita = keys . iterator ( ) ;
			int pcNum = 0 ;
			while ( ita . hasNext() ) {
				pcNum = ita.next() ;
				jta.append("\t" + pcNum + "\t" + pcMap.get(pcNum) + "\t" + pcMap.get(pcNum)*20 + "\n") ; // �� PC ���ð� & ita.next():PC��ȣ
			} // end while
			
			jta.append("------------------------------------------------------------------------------------------------------------\n");
			jta.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" +  totalPrice +"] ��");
			

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
			viewCalcPCRecipt();
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
				
				rowData[0] = cv.getPcCode() ;
				rowData[1] = cv.getPcNum() ;
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
