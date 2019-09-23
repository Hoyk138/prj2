package admin.controller;

import java.awt.Checkbox;
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

import admin.DAO.CalcPCDAO;
import admin.VO.CalcPCVO;
import admin.view.CalcView;

public class CalcPCEvt extends MouseAdapter implements ActionListener {
	
	private CalcView cv ;
	
	public CalcPCEvt(CalcView cv) {
		this.cv = cv ;
//		setCalcPCList();
	} // CalcPCEvt
	
	private void viewCalcPCRecipt() {
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		
		Map<Integer, Integer> pcMap = new HashMap<Integer, Integer>() ;
		
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			
			JTextArea jta = new JTextArea(20, 50) ;
			JScrollPane jsp = new JScrollPane(jta) ;
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(null,"당일 거래된 목록이 없습니다.");
				return ;
			} // end if				
			
			jta.append("------------------------------------------------------------------------------------------------------------\n");
			jta.append("\tPC번호\t이용 시간(분)\t이용 금액(원)\n");
			jta.append("==============================================================\n");
			
			CalcPCVO cpcVO = null ;
			int totalUseTime = 0 ;
			int totalPrice = 0 ;
			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i) ;
				
				if (pcMap.containsKey(cpcVO.getPcNum())) {
					pcMap.put(cpcVO.getPcNum(), pcMap.get(cpcVO.getPcNum()) + cpcVO.getUseTime()) ;
				} else {
					pcMap.put(cpcVO.getPcNum(), cpcVO.getUseTime()) ;
				} // end if
				
				//useTime, int num, int totalCnt, int price, int totalPrice
//				jta.append((i+1) + "\t" + cv.getPcNum() + "\t" + pcMap.get(cv.getPcNum()) + "\t" + cv.getUseFee()+ "\n");
				totalUseTime += cpcVO.getUseTime() ;
				totalPrice += cpcVO.getUseFee() ;
			} // end for
			
			Set<Integer> keys = pcMap.keySet ( ) ;
			
			Iterator<Integer> ita = keys . iterator ( ) ;
			int pcNum = 0 ;
			while ( ita . hasNext() ) {
				pcNum = ita.next() ;
				jta.append("\t" + pcNum + "\t" + pcMap.get(pcNum) + "\t" + pcMap.get(pcNum)*20 + "\n") ; // 각 PC 사용시간 & ita.next():PC번호
			} // end while
			
			jta.append("------------------------------------------------------------------------------------------------------------\n");
			jta.append("\t총 이용시간 : [" + totalUseTime + " ] 분,\t 총 매출 : [" +  totalPrice +"] 원");
			

//			JOptionPane.showMessageDialog(cv, jsp);
			JOptionPane.showMessageDialog(null, jsp);
			
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS에 문제가 발생했습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // CalcPCEvt

		
	public void setCalcPCList() {
		Object[] rowData = null ;
		
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			
			DefaultTableModel dtm = cv.getDtmCalcPC() ;
			dtm.setRowCount(0);
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC 결제 내역이 없습니다.");
				return;
			} // end if
			
			CalcPCVO cpcVO = null ;
			
			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i) ;
				
				rowData = new Object[5] ;
				
				rowData[0] = cpcVO.getPcCode() ;
				rowData[1] = cpcVO.getPcNum() ;
				rowData[2] = cpcVO.getId() ;
				rowData[3] = cpcVO.getUseTime() ;
				rowData[4] = cpcVO.getUseFee() ;
				
				dtm.addRow(rowData);
			} // end for
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCList
	
	
	public void setCalcPCList7() {
		Object[] rowData = null ;
		
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC7() ;
			
			DefaultTableModel dtm = cv.getDtmCalcPC() ;
			dtm.setRowCount(0);
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC 결제 내역이 없습니다.");
				return;
			} // end if
			
			CalcPCVO cpcVO = null ;
			
			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i) ;
				
				rowData = new Object[5] ;
				
				rowData[0] = cpcVO.getPcCode() ;
				rowData[1] = cpcVO.getPcNum() ;
				rowData[2] = cpcVO.getId() ;
				rowData[3] = cpcVO.getUseTime() ;
				rowData[4] = cpcVO.getUseFee() ;
				
				dtm.addRow(rowData);
			} // end for
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCList
	
	
	public void setCalcPCLstMonth() {
		Object[] rowData = null ;
		
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPCLstMonth() ;
			
			DefaultTableModel dtm = cv.getDtmCalcPC() ;
			dtm.setRowCount(0);
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC 결제 내역이 없습니다.");
				return;
			} // end if
			
			CalcPCVO cpcVO = null ;
			
			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i) ;
				
				rowData = new Object[5] ;
				
				rowData[0] = cpcVO.getPcCode() ;
				rowData[1] = cpcVO.getPcNum() ;
				rowData[2] = cpcVO.getId() ;
				rowData[3] = cpcVO.getUseTime() ;
				rowData[4] = cpcVO.getUseFee() ;
				
				dtm.addRow(rowData);
			} // end for
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCLstMonth
	
	public void setCalcPCLstCustom() {
		Object[] rowData = null ;
		
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		String startDate = cv.getJtfStartPC().getText().trim() ;
		String endDate = cv.getJtfEndPC().getText().trim() ;
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPCLstCustom(startDate, endDate) ;
			
			DefaultTableModel dtm = cv.getDtmCalcPC() ;
			dtm.setRowCount(0);
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC 결제 내역이 없습니다.");
				return;
			} // end if
			
			CalcPCVO cpcVO = null ;
			
			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i) ;
				
				rowData = new Object[5] ;
				
				rowData[0] = cpcVO.getPcCode() ;
				rowData[1] = cpcVO.getPcNum() ;
				rowData[2] = cpcVO.getId() ;
				rowData[3] = cpcVO.getUseTime() ;
				rowData[4] = cpcVO.getUseFee() ;
				
				dtm.addRow(rowData);
			} // end for
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCLstMonth
	
		public void mouseClicked(MouseEvent me) {

			if (me.getSource() == cv.getJtp()) { // 주문 탭을 눌렀을 때 이벤트 처리 => 주문현황 조회 시작
				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
				if (jtptemp.getSelectedIndex() == 0) {
//					if (ot == null) { // 조회 Thread가 생성되어 있지 않음(주문조회X)
//						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // 선택된 행을 비교, 값을 넣는 일
//						ot.start();
					setCalcPCList();
					} // end if
				} // end if

			} // end if
		
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			// 영수증
			if (ae.getSource() == cv.getJbtCalcPC()) {
				viewCalcPCRecipt();
			} // end if
			
			// 조회버튼
			
			if (ae.getSource()==cv.getJbtnSearchPC()) {
				Checkbox selectChb=cv.getCgPC().getSelectedCheckbox() ;
				
				switch (selectChb.getLabel()) {
				case "오늘":
					setCalcPCList();
					break;
				case "일주일":
					setCalcPCList7();
					break;
				case "한 달":
					setCalcPCLstMonth();
					break;
					
				case "사용자 지정":
					setCalcPCLstCustom();
					break;
					
				}
			} // end if
			
		} // actionPerformed
		
	// 단위테스트용
//	public static void main(String[] args) {
//		new CalcPCEvt();
//	} // main

} // class
