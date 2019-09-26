package admin.controller;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
	private String fileName ;
	private StringBuilder msg ;
	private StringBuilder report_file;
	private long date ;
	private Date today ; 
	private DateFormat format ; 
	private String formatted ; 
	private SimpleDateFormat sdf ;
	private Calendar cal ;
	private String todate; 
	private String predate ;

	public CalcItemEvt(CalcView cv) {
		this.cv = cv;
		report_file = new StringBuilder();
		msg = new StringBuilder() ;
//		setCalcItemList();
	} // CalcItemEvt

	private void viewCalcItemRecipt() {
		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		
		// <제품명, 개수>
		Map<String, Integer> itemQMap = new HashMap<String, Integer>() ;
		
		// <제품명, 가격>
		Map<String, Integer> itemPMap = new HashMap<String, Integer>() ;
		
		try {
			List<CalcItemReciptVO> list = ciDAO.selectCalcItemRecipt();

			JTextArea jta = new JTextArea(20, 50);
			JScrollPane jsp = new JScrollPane(jta);
			
			if (list.isEmpty()) {
//				jta.append("정산할 수 있는 거래 목록이 없습니다.");
				JOptionPane.showMessageDialog(null, "당일 거래된 목록이 없습니다.");
				return;
			} // end if

			jta.append(
					"------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("번호\t제품명\t개수\t가격\n");
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
			jta.append("총 판매 수량 : " + totalCnt + "개,\t총 매출 : " +  totalPrice + "원");

			JOptionPane.showMessageDialog(null, jsp);

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS에 문제가 발생했습니다.");
			Se.printStackTrace();
		} // end catch

	} // CalcItmeEvt


	public void setCalcItemList() {
		Object[] rowData = null;

		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		
		try {
			List<CalcItemVO> list = ciDAO.selectCalcItem();

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

				rowData[0] = ciVO.getOrder_code();
				rowData[1] = ciVO.getPCnum();
				rowData[2] = ciVO.getId();
				rowData[3] = ciVO.getItemName();
				rowData[4] = ciVO.getQuantity();
				rowData[5] = ciVO.getPrice();

				dtm.addRow(rowData);
				
			} // end for
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			
			msg.append("상품명\t판매 수량\t가격\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" +  totalPrice +"] 원") ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());

		} catch (SQLException se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			se.printStackTrace();
		} // end catch

	} // setCalcItemList
	
	
	public void setCalcItemList7() {
		Object[] rowData = null ;
		
		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		
		try {
			List<CalcItemVO> list = ciDAO.selectCalcItem7() ;
			
			DefaultTableModel dtm = cv.getDtmCalcItem() ;
			dtm.setRowCount(0);
			
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
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
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.DATE, -7);
			predate = sdf.format(cal.getTime()) ;
			
			msg.append("상품명\t판매 수량\t가격\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" +  totalPrice +"] 원\n")
			.append("조회 기간 : " + predate + " ~ " + todate);
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcItemList7
	
	
	public void setCalcItemLstMonth() {
		Object[] rowData = null ;
		
		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		
		try {
			List<CalcItemVO> list = ciDAO.selectCalcItem7() ;
			
			DefaultTableModel dtm = cv.getDtmCalcItem() ;
			dtm.setRowCount(0);
			
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
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
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.MONTH, -1);
			predate = sdf.format(cal.getTime()) ;
			
			msg.append("상품명\t판매 수량\t가격\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" +  totalPrice +"] 원")
			.append("조회 기간 : " + predate + " ~ " + todate);
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcItemLstMonth

	
	
	public void setCalcItemLstCustom() {
		Object[] rowData = null ;
		CalcItemDAO ciDAO = CalcItemDAO.getInstance();
		
		String startDate = cv.getJtfStartItem().getText().trim() ;
		String endDate = cv.getJtfEndItem().getText().trim() ;
		
		
		try {
			List<CalcItemVO> list = ciDAO.selectCalcItemLstCustom(startDate, endDate) ;
			
			DefaultTableModel dtm = cv.getDtmCalcItem() ;
			dtm.setRowCount(0);
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "매점 이용 내역이 없습니다.");
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
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
			cal = Calendar.getInstance() ;
			
			todate  = cv.getJtfEndPC().getText() ;
			predate= cv.getJtfStartPC().getText() ;
			
			msg.append("상품명\t판매 수량\t가격\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 판매 수량 : [" + totalQuantity + " ] 개,\t 총 매출 : [" +  totalPrice +"] 원")
			.append("조회 기간 : " + predate + " ~ " + todate);
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcItemLstMonth
	
	
	
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
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Checkbox selectChb=cv.getCgItem().getSelectedCheckbox() ;
		// 영수증
		if (ae.getSource() == cv.getJbtCalcItem()) {
			if (selectChb.getLabel()=="오늘") {
				viewCalcItemRecipt();
			} else {
				JOptionPane.showMessageDialog(null, "정산은 당일 내역만 가능합니다.");
			} // end if
		} // end if
			
			
		// 조회버튼
		if (ae.getSource()==cv.getJbtnSearchItem()) {
			
			switch (selectChb.getLabel()) {
			case "오늘":
				setCalcItemList();
				break;
			case "일주일":
				setCalcItemList7();
				break;
			case "한 달":
				setCalcItemLstMonth();
				break;
				
			case "사용자 지정":
				setCalcItemLstCustom();
				System.out.println("떠");
				break;
				
			} // end switch
			
		} // end if
		
		if (ae.getSource()==cv.getJbtnItemSaveFile()) {
			try {
				reportFile() ;
			JOptionPane.showMessageDialog(null, "(C:\\dev\\PCBang_calc_ITEM) 경로에 저장되었습니다.");
				
			} catch (IOException e) {
				e.printStackTrace();
			} // end catch
		} // end if
		
	} // actionPerformed
	
	public void reportFile() throws IOException {
		
		BufferedWriter bw = null;
		date = System.currentTimeMillis() ;
		today = new Date ( date ); 
		format = DateFormat.getDateInstance ( DateFormat.FULL,Locale.US ); 
		formatted = format.format ( today ); 
		
		try {
			File file = new File("c:/dev/PCBang_calc_ITEM");

			if (!file.exists()) {
				file.mkdir();
			} // end if
			
			fileName = file.getAbsolutePath() + "/Item_" + date + ".dat";
			bw = new BufferedWriter(new FileWriter(fileName));
			
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE") ;
			bw.write( "======================================\n"
					+ "[매점 정산 내역] (" + sdf.format(date)+"에 저장됨.)\n"
					+ "======================================\n"
					+ getReport_file()
					+ "\n======================================");
			
			// 스트림의 내용을 목적지로 분출
			bw.flush();

		} finally {
			if (bw != null) { bw.close(); } // end if
		} // end finally
		
	}//reportFile

	public String getReport_file() {
		return report_file.toString();
	}
	
	// 단위테스트용
//	public static void main(String[] args) {
//		new CalcItemEvt();
//	} // main

} // class
