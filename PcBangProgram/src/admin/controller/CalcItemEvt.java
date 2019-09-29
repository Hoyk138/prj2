package admin.controller;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import admin.DAO.AdminDAO;
import admin.VO.CalcItemVO;
import admin.view.CalcView;

public class CalcItemEvt extends MouseAdapter implements ActionListener {

	private CalcView cv;
	private String fileName;
	private StringBuilder msg;
	private StringBuilder report_file;
	private SimpleDateFormat sdf;
	private Calendar cal;
	private String todate;
	private String predate;

	private Checkbox selectChb;

	public CalcItemEvt(CalcView cv) {
		this.cv = cv;
		report_file = new StringBuilder();
		msg = new StringBuilder();
		setCalcItemList(false);
	} // CalcItemEvt

	private void viewCalcItemRecipt() {
		DefaultTableModel dtm = cv.getDtmCalcItem();
		if (dtm.getRowCount() == 0) {
			JOptionPane.showMessageDialog(cv, "조회 된 목록이 없습니다.");
			return;
		} // end if

		Map<String, Integer> itemMapQuantity = new HashMap<String, Integer>(30);
		Map<String, Integer> itemMapPrice = new HashMap<String, Integer>(30);
		
		// { "코드", "PC번호", "사용자(ID)", "이용시간(분)", "이용금액(원)" };
		String itemName = null;
		Integer orderQuantity = null;
		Integer paymentFee = null;
		int totalOrderQuantity = 0;
		int totalPrice = 0;
		for (int i = 0; i < dtm.getRowCount(); i++) {
			itemName = (String) dtm.getValueAt(i, 3);// 상품명
			orderQuantity = (Integer) dtm.getValueAt(i, 4);// 주문 갯수
			paymentFee = (Integer) dtm.getValueAt(i, 5);// 결제 금액

			if (itemMapQuantity.containsKey(itemName)) {
				itemMapQuantity.put(itemName, itemMapQuantity.get(itemName) + orderQuantity);
			} else {
				itemMapQuantity.put(itemName, orderQuantity);
			} // end if
			if (itemMapPrice.containsKey(itemName)) {
				itemMapPrice.put(itemName, itemMapPrice.get(itemName) + paymentFee);
			} else {
				itemMapPrice.put(itemName, paymentFee);
			} // end if

			totalOrderQuantity += orderQuantity;
			totalPrice += paymentFee;
		} // end for

		JTextArea jta = new JTextArea(20, 50);
		JScrollPane jsp = new JScrollPane(jta);
		jta.append(
				"------------------------------------------------------------------------------------------------------------------------\n");
		jta.append("\t분류\t제품명\t개수\t가격\n");
		jta.append("=====================================================================\n");

		//상품 목록
		AdminDAO aDAO = AdminDAO.getInstance();
		List<String> foodList = null;
		List<String> snackList = null;
		List<String> drinkList = null;
		try {
			foodList = aDAO.selectItem("F");
			snackList = aDAO.selectItem("S");
			drinkList = aDAO.selectItem("D");

		for (int i = 0; i < foodList.size(); i++) {
			itemName = foodList.get(i);
			if (itemMapQuantity.containsKey(itemName)) {
				jta.append("\t식사\t" + itemName + "\t" + itemMapQuantity.get(itemName) + "\t" + itemMapPrice.get(itemName) + "\n");
			} else {
				jta.append("\t식사\t" + itemName + "\t0\t0\n");
			} // if else
		} // end for
		for (int i = 0; i < snackList.size(); i++) {
			itemName = snackList.get(i);
			if (itemMapQuantity.containsKey(itemName)) {
				jta.append("\t스낵\t" + itemName + "\t" + itemMapQuantity.get(itemName) + "\t" + itemMapPrice.get(itemName) + "\n");
			} else {
				jta.append("\t스낵\t" + itemName + "\t0\t0\n");
			} // if else
		} // end for
		for (int i = 0; i < drinkList.size(); i++) {
			itemName = drinkList.get(i);
			if (itemMapQuantity.containsKey(itemName)) {
				jta.append("\t음료\t" + itemName + "\t" + itemMapQuantity.get(itemName) + "\t" + itemMapPrice.get(itemName) + "\n");
			} else {
				jta.append("\t음료\t" + itemName + "\t0\t0\n");
			} // if else
		} // end for

		jta.append(
				"------------------------------------------------------------------------------------------------------------------------\n");
		jta.append("\t총 판매수량 : [" + totalOrderQuantity + "] 개,\t총 매출 : [" + totalPrice + "] 원\n");
		
		JOptionPane.showMessageDialog(cv, jsp);
		
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(cv, "DBMS에 문제가 발생했습니다.");
			sqle.printStackTrace();
		}//end catch

////		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
//		AdminDAO aDAO = AdminDAO.getInstance();
//
//		// <제품명, 개수>
//		Map<String, Integer> itemQMap = new HashMap<String, Integer>();
//
//		// <제품명, 가격>
//		Map<String, Integer> itemPMap = new HashMap<String, Integer>();
//
//		try {
////			List<CalcItemReciptVO> list = ciDAO.selectCalcItemRecipt();
//			List<CalcItemReciptVO> list = aDAO.selectCalcItemRecipt();
//
//			JTextArea jta = new JTextArea(20, 50);
//			JScrollPane jsp = new JScrollPane(jta);
//
//			if (list.isEmpty()) {
////				jta.append("정산할 수 있는 거래 목록이 없습니다.");
//				JOptionPane.showMessageDialog(null, "당일 거래된 목록이 없습니다.");
//				return;
//			} // end if
//
//			jta.append(
//					"------------------------------------------------------------------------------------------------------------------------\n");
//			jta.append("번호\t제품명\t개수\t가격\n");
//			jta.append("=====================================================================\n");
//
//			CalcItemReciptVO cirVO = null;
//			int totalCnt = 0;
//			int totalPrice = 0;
//
//			for (int i = 0; i < list.size(); i++) {
//				cirVO = list.get(i);
//
//				// itemQMap
//				if (itemQMap.containsKey(cirVO.getItemName())) {
//					itemQMap.put(cirVO.getItemName(), itemQMap.get(cirVO.getItemName()) + (cirVO.getQuantity()));
//				} else {
//					itemQMap.put(cirVO.getItemName(), cirVO.getQuantity());
//				} // end if
//
//				itemPMap.put(cirVO.getItemName(), cirVO.getPrice());
//
//				totalCnt += cirVO.getQuantity();
//				totalPrice += cirVO.getOrderedPrice();
//			} // end for
//
//			Set<String> keyQ = itemQMap.keySet();
//
//			Iterator<String> itaQ = keyQ.iterator();
//			String itemName = "";
//			int i = 1;
//			while (itaQ.hasNext()) {
//				itemName = itaQ.next();
//				jta.append(i + "\t" + itemName + "\t" + itemQMap.get(itemName) + "\t"
//						+ itemQMap.get(itemName) * itemPMap.get(itemName) + "\n");
//				i++;
//			} // end while
//
//			jta.append(
//					"------------------------------------------------------------------------------------------------------------------------\n");
//			jta.append("총 판매 수량 : " + totalCnt + "개,\t총 매출 : " + totalPrice + "원");
//
//			JOptionPane.showMessageDialog(null, jsp);
//
//		} catch (SQLException Se) {
//			JOptionPane.showMessageDialog(cv, "DBMS에 문제가 발생했습니다.");
//			Se.printStackTrace();
//		} // end catch

	} // CalcItmeEvt

	public void setCalcItemList(boolean flag) {
		Object[] rowData = null;

//		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<CalcItemVO> list = ciDAO.selectCalcItem();
			List<CalcItemVO> list = aDAO.selectCalcItem();

			DefaultTableModel dtm = cv.getDtmCalcItem();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				if (flag) {
					JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
				} // end if
				return;
			} // end if

			CalcItemVO ciVO = null;

			for (int i = 0; i < list.size(); i++) {
				ciVO = list.get(i);

				rowData = new Object[6];

				rowData[0] = ciVO.getOrderCode();
				rowData[1] = ciVO.getPCnum();
				rowData[2] = ciVO.getId();
				rowData[3] = ciVO.getItemName();
				rowData[4] = ciVO.getQuantity();
				rowData[5] = ciVO.getPrice();

				dtm.addRow(rowData);
			} // end for

			int totalQuantity = 0;
			int totalPrice = 0;

			msg.append("상품명\t판매 수량\t가격\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalQuantity += list.get(i).getQuantity();
				totalPrice += list.get(i).getPrice();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" + totalPrice + "] 원\n")
					.append("조회 기간 : " + todate);

			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			se.printStackTrace();
		} // end catch

	} // setCalcItemList

	public void setCalcItemList7() {
		Object[] rowData = null;

//		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<CalcItemVO> list = ciDAO.selectCalcItem7() ;
			List<CalcItemVO> list = aDAO.selectCalcItem7();

			DefaultTableModel dtm = cv.getDtmCalcItem();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
				return;
			} // end if

			CalcItemVO ciVO = null;

			for (int i = 0; i < list.size(); i++) {
				ciVO = list.get(i);

				rowData = new Object[6];

				rowData[0] = ciVO.getOrderCode();
				rowData[1] = ciVO.getPCnum();
				rowData[2] = ciVO.getId();
				rowData[3] = ciVO.getItemName();
				rowData[4] = ciVO.getQuantity();
				rowData[5] = ciVO.getPrice();

				dtm.addRow(rowData);

			} // end for

			int totalQuantity = 0;
			int totalPrice = 0;
			todate = null;
			predate = null;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
			cal = Calendar.getInstance();

			todate = sdf.format(cal.getTime());
			cal.add(Calendar.DATE, -7);
			predate = sdf.format(cal.getTime());

			report_file.delete(0, report_file.length());

			msg.append("상품명\t판매 수량\t가격\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalQuantity += list.get(i).getQuantity();
				totalPrice += list.get(i).getPrice();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" + totalPrice + "] 원\n")
					.append("조회 기간 : " + predate + " ~ " + todate);

			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch

	} // setCalcItemList7

	public void setCalcItemLstMonth() {
		Object[] rowData = null;

//		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<CalcItemVO> list = ciDAO.selectCalcItem7() ;
			List<CalcItemVO> list = aDAO.selectCalcItem7();

			DefaultTableModel dtm = cv.getDtmCalcItem();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
				return;
			} // end if

			CalcItemVO ciVO = null;

			for (int i = 0; i < list.size(); i++) {
				ciVO = list.get(i);

				rowData = new Object[6];

				rowData[0] = ciVO.getOrderCode();
				rowData[1] = ciVO.getPCnum();
				rowData[2] = ciVO.getId();
				rowData[3] = ciVO.getItemName();
				rowData[4] = ciVO.getQuantity();
				rowData[5] = ciVO.getPrice();

				dtm.addRow(rowData);

			} // end for

			int totalQuantity = 0;
			int totalPrice = 0;

			todate = null;
			predate = null;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
			cal = Calendar.getInstance();

			todate = sdf.format(cal.getTime());
			cal.add(Calendar.MONTH, -1);
			predate = sdf.format(cal.getTime());

			report_file.delete(0, report_file.length());

			msg.append("상품명\t판매 수량\t가격\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalQuantity += list.get(i).getQuantity();
				totalPrice += list.get(i).getPrice();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" + totalPrice + "] 원\n")
					.append("조회 기간 : " + predate + " ~ " + todate);

			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch

	} // setCalcItemLstMonth

	public void setCalcItemLstCustom() {
		Object[] rowData = null;

//		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		String startDate = cv.getJtfStartItem().getText().trim();
		String endDate = cv.getJtfEndItem().getText().trim();

		try {
//			List<CalcItemVO> list = ciDAO.selectCalcItemLstCustom(startDate, endDate) ;
			List<CalcItemVO> list = aDAO.selectCalcItemLstCustom(startDate, endDate);

			DefaultTableModel dtm = cv.getDtmCalcItem();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
				return;
			} // end if

			CalcItemVO ciVO = null;

			for (int i = 0; i < list.size(); i++) {
				ciVO = list.get(i);

				rowData = new Object[6];

				rowData[0] = ciVO.getOrderCode();
				rowData[1] = ciVO.getPCnum();
				rowData[2] = ciVO.getId();
				rowData[3] = ciVO.getItemName();
				rowData[4] = ciVO.getQuantity();
				rowData[5] = ciVO.getPrice();

				dtm.addRow(rowData);

			} // end for

			int totalQuantity = 0;
			int totalPrice = 0;

			todate = null;
			predate = null;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
			cal = Calendar.getInstance();

			todate = cv.getJtfEndItem().getText();
			predate = cv.getJtfStartItem().getText();

			report_file.delete(0, report_file.length());

			msg.append("상품명\t판매 수량\t가격\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalQuantity += list.get(i).getQuantity();
				totalPrice += list.get(i).getPrice();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" + totalPrice + "] 원\n")
					.append("조회 기간 : " + predate + " ~ " + todate);

			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch

	} // setCalcItemLstMonth

//	public void mouseClicked(MouseEvent me) {
//
//		if (me.getSource() == cv.getJtp()) { // 주문 탭을 눌렀을 때 이벤트 처리 => 주문현황 조회 시작
//			JTabbedPane jtptemp = (JTabbedPane) me.getSource();
//			if (jtptemp.getSelectedIndex() == 1) {
////					if (cv == null) { // 조회 Thread가 생성되어 있지 않음(주문조회X)
////						ot = new OrderThread(cv.getJtOrderList(), cv.getDtmOrderList()); // 선택된 행을 비교, 값을 넣는 일
//				setCalcItemList(true);
//			} // end if
//		} // end if
//
//	} // end mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {

		selectChb = cv.getCgItem().getSelectedCheckbox();
		// 조회버튼
		if (ae.getSource() == cv.getJbtnSearchItem()) {

			switch (selectChb.getLabel()) {
			case "오늘":
				setCalcItemList(true);
				break;
			case "일주일":
				setCalcItemList7();
				break;
			case "한 달":
				setCalcItemLstMonth();
				break;
			case "사용자 지정":
				setCalcItemLstCustom();
				break;
			} // end switch

		} // end if

		// 영수증
		if (ae.getSource() == cv.getJbtCalcItem()) {
			viewCalcItemRecipt();
//			if (selectChb.getLabel() == "오늘") {
//				viewCalcItemRecipt();
//			} else {
//				JOptionPane.showMessageDialog(null, "정산은 당일 내역만 가능합니다.");
//			} // end if
		} // end if

		if (ae.getSource() == cv.getJbtnItemSaveFile()) {
			String flag = "";
			switch (selectChb.getLabel()) {
			case "오늘":
				flag = "today";
				break;
			case "일주일":
				flag = "week";
				break;
			case "한 달":
				flag = "month";
				break;
			case "사용자 지정":
				flag = "custom";
				break;
			}
			try {
				reportFile(flag);
				JOptionPane.showMessageDialog(cv, fileName + "으로 저장 되었습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			} // end catch
		} // end if

	} // actionPerformed

	public void reportFile(String flag) throws IOException {

		BufferedWriter bw = null;

		long currTime = System.currentTimeMillis();
		Date currDate = new Date(currTime);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");

		String saveTime1 = sdf1.format(currDate);
		String saveTime2 = sdf2.format(currDate);
		try {
			File file = new File("c:/dev/pcbang/admin/calc/item");

			if (!file.exists()) {
				file.mkdir();
			} // end if

			fileName = file.getAbsolutePath() + "\\Item_" + saveTime1 + ".dat";
			bw = new BufferedWriter(new FileWriter(fileName));

			bw.write("======================================\n" + "[매점 정산 내역] (" + saveTime2 + "에 저장됨.)\n"
					+ "======================================\n" + getReport_file()
					+ "\n======================================");

			// 스트림의 내용을 목적지로 분출
			bw.flush();

		} finally {
			if (bw != null) {
				bw.close();
			} // end if
		} // end finally

	}// reportFile

	public String getReport_file() {
		return report_file.toString();
	}

	// 단위테스트용
//	public static void main(String[] args) {
//		new CalcItemEvt();
//	} // main

} // class
