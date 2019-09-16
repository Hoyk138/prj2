package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

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
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			
			JTextArea jta = new JTextArea(20, 50) ;
			JScrollPane jsp = new JScrollPane(jta) ;
			if (list.isEmpty()) {
				jta.append("정산할 수 있는 거래 목록이 없습니다.");
			} // end if
			
			jta.append("------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("번호\tPC 번호\t이용 시간\t이용 금액\n");
			jta.append("=====================================================================\n");
			
			CalcPCVO cv = null ;
//			int totalCnt = 0 ;
//			int totalPrice = 0 ;
			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i) ;
				//useTime, int num, int totalCnt, int price, int totalPrice
				jta.append((i+1) + "\t" + cv.getPcNum() + "\t" + cv.getUseTime() + "\t" +Integer.parseInt(cv.getUseTime())*20+ "\n");
//				totalCnt += cv.getTotalCnt() ;
//				totalPrice += cv.getTotalPrice() ;
			} // end for
			jta.append("------------------------------------------------------------------------------------------------------------------------\n");
//			jta.append("총 계 : " + totalCnt + "\t, 총 매출 : " +  totalPrice +"\t원");
			

//			JOptionPane.showMessageDialog(cv, jsp);
			JOptionPane.showMessageDialog(null, jsp);
			
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS에 문제가 발생했습니다.");
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
				JOptionPane.showMessageDialog(cv, "PC 결제 내역이 없습니다.");
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
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCList
	
	
//		public void mouseClicked(MouseEvent me) {
//
//			if (me.getSource() == cv.getJtp()) { // 주문 탭을 눌렀을 때 이벤트 처리 => 주문현황 조회 시작
//				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
//				if (jtptemp.getSelectedIndex() == 1) {
//					if (ot == null) { // 조회 Thread가 생성되어 있지 않음(주문조회X)
//						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // 선택된 행을 비교, 값을 넣는 일
//						ot.start();
//					} // end if
//				} // end if

//			} // end if
		
	// 단위테스트용
//	public static void main(String[] args) {
//		new CalcPCEvt();
//	} // main

} // class
