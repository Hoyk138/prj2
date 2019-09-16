package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

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

	private void CalcItemEvt() {
		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		try {
			List<CalcItemReciptVO> list = ciDAO.selectCalcItemRecipt();

			JTextArea jta = new JTextArea(20, 50);
			JScrollPane jsp = new JScrollPane(jta);
			if (list.isEmpty()) {
//				jta.append("정산할 수 있는 거래 목록이 없습니다.");
				JOptionPane.showMessageDialog(null, "정산할 수 있는 거래 목록이 없습니다.");
				return;
			} // end if

			jta.append(
					"------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("번호\t제품명\t개수\t가격\n");
			jta.append("=====================================================================\n");

			CalcItemReciptVO cv = null;
//			int totalCnt = 0 ;
//			int totalPrice = 0 ;
			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i);
				jta.append((i + 1) + "\t" + cv.getItemName() + "\t" + cv.getQuantity() + "\t" + cv.getPrice() + "\n");
//				totalCnt += cv.getCnt() ;
//				totalPrice += cv.getTotalPrice() ;
			} // end for
			jta.append(
					"------------------------------------------------------------------------------------------------------------------------\n");
//			jta.append("총 판매 수량 : " + /*totalCnt*/"\t" + "개, 총 매출 : " +  /*totalPrice*/"\t" + "원");

			JOptionPane.showMessageDialog(null, jsp);

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS에 문제가 발생했습니다.");
			Se.printStackTrace();
		} // end catch

	} // CalcItmeEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == cv.getJbtCalcItem()) {
			CalcItemEvt();
		} // end if

	}

	public void setCalcItemList() {
		DefaultTableModel dtm = cv.getDtmCalcItem();

		dtm.setRowCount(0);
		Object[] rowData = null;

		CalcItemDAO ciDAO = CalcItemDAO.getInstance();

		try {
			List<CalcItemVO> list = ciDAO.selectCalcItem();

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
			} // end if

			CalcItemVO cv = null;

			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i);

				rowData = new Object[6];

				rowData[0] = cv.getNum();
				rowData[1] = cv.getPcCode();
				rowData[2] = cv.getId();
				rowData[3] = cv.getItemName();
				rowData[4] = cv.getQuantity();
				rowData[5] = cv.getPrice();

				dtm.addRow(rowData);

			} // end for

		} catch (SQLException se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			se.printStackTrace();
		} // end catch

	} // setCalcItemList

	public void mouseClicked(MouseEvent me) {

		if (me.getSource() == cv.getJtp()) { // 주문 탭을 눌렀을 때 이벤트 처리 => 주문현황 조회 시작
			JTabbedPane jtptemp = (JTabbedPane) me.getSource();
			if (jtptemp.getSelectedIndex() == 1) {
//					if (cv == null) { // 조회 Thread가 생성되어 있지 않음(주문조회X)
//						ot = new OrderThread(cv.getJtOrderList(), cv.getDtmOrderList()); // 선택된 행을 비교, 값을 넣는 일
				setCalcItemList();
			} // end if
		} // end if

	} // end mouseClicked

	// 단위테스트용
//	public static void main(String[] args) {
//		new CalcItemEvt();
//	} // main

} // class
