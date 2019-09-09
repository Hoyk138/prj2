package admin.controller;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import admin.view.CalcView;

public class CalcPCEvt {
	
	private CalcView cv ;
	
	private CalcPCEvt() {
//		AdminDAO aDAO = AdminDAO.getInstance() ;
//		try {
//			List<CalcVO> list = aDAO.selectCalc() ;
//			
			JTextArea jta = new JTextArea(20, 50) ;
			JScrollPane jsp = new JScrollPane(jta) ;
//			if (list.isEmpty()) {
//				jta.append("정산할 수 있는 거래 목록이 없습니다.");
//			} // end if
			
			jta.append("------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("번호\t이용 시간\t이용 금액\n");
			jta.append("=====================================================================\n");
			
//			CalcVO cv = null ;
//			int totalCnt = 0 ;
//			int totalPrice = 0 ;
//			for (int i = 0; i < list.size(); i++) {
//				cv = list.get(i) ;
//				jta.append((i+1) + "\t" + cv.getName() + "\t" + cv.getLunchCode() + "\t" + cv.getCnt() + "\t" + cv.getTotalPrice() + "\n");
//				totalCnt += cv.getCnt() ;
//				totalPrice += cv.getTotalPrice() ;
//			} // end for
			jta.append("------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("총 계 : " + /*totalCnt*/"\t" + ", 총 매출 : " +  /*totalPrice*/"\t" + "원");
			

//			JOptionPane.showMessageDialog(cv, jsp);
			JOptionPane.showMessageDialog(null, jsp);
			
			
//		} catch (SQLException Se) {
//			JOptionPane.showMessageDialog(lm, "DBMS에 문제가 발생했습니다.");
//			Se.printStackTrace();
//		} // end catch
		
	} // viewCalc
	
//	private void lunchClose() {
//		switch (JOptionPane.showConfirmDialog(lm, "원도시락 관리자 프로그램을 종료하시겠습니까?")) {
//		case JOptionPane.OK_OPTION:
//			lm.dispose();
//		} // end switch
//	} // lunchClose
	
//	@Override
//	public void actionPerformed(ActionEvent ae) {
//
//		if (ae.getSource() == lm.getJbtAddForm()) {
//			addForm();
//		} // end if
//
//		if (ae.getSource() == lm.getJbtClose()) { // 종료 버튼
//			lunchClose();
//		} // end if
//		
//		public void mouseClicked(MouseEvent me) {
//
//			if (me.getSource() == lm.getJtp()) { // 주문 탭을 눌렀을 때 이벤트 처리 => 주문현황 조회 시작
//				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
//				if (jtptemp.getSelectedIndex() == 1) {
//					if (ot == null) { // 조회 Thread가 생성되어 있지 않음(주문조회X)
//						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // 선택된 행을 비교, 값을 넣는 일
//						ot.start();
//					} // end if
//				} // end if
//
//			} // end if
		
	// 단위테스트용
	public static void main(String[] args) {
		new CalcPCEvt();
	} // main

} // class
