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
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import admin.DAO.AdminDAO;
import admin.VO.CalcPCVO;
import admin.view.CalcView;

public class CalcPCEvt extends MouseAdapter implements ActionListener {
	
	private CalcView cv ;
	private String fileName ;
	private StringBuilder msg ;
	private StringBuilder report_file;
	private SimpleDateFormat sdf ;
	private Calendar cal ;
	private String todate; 
	private String predate ;
	
	private Checkbox selectChb;
	
	public CalcPCEvt(CalcView cv) {
		this.cv = cv ;
		report_file = new StringBuilder();
		msg = new StringBuilder() ;
		setCalcPCList(false);
	} // CalcPCEvt
	
	private void viewCalcPCRecipt() {
//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance() ;
		
		Map<Integer, Integer> pcMapUseTime = new HashMap<Integer, Integer>() ;
		Map<Integer, Integer> pcMapPrice = new HashMap<Integer, Integer>() ;
		
		
		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			List<CalcPCVO> list = aDAO.selectCalcPC() ;
			
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
				
				if (pcMapUseTime.containsKey(cpcVO.getPcNum())) {
					pcMapUseTime.put(cpcVO.getPcNum(), pcMapUseTime.get(cpcVO.getPcNum()) + cpcVO.getUseTime()) ;
				} else {
					pcMapUseTime.put(cpcVO.getPcNum(), cpcVO.getUseTime()) ;
				} // end if
				if (pcMapPrice.containsKey(cpcVO.getPcNum())) {
					pcMapPrice.put(cpcVO.getPcNum(), pcMapPrice.get(cpcVO.getPcNum()) + cpcVO.getUseFee()) ;
				} else {
					pcMapPrice.put(cpcVO.getPcNum(), cpcVO.getUseFee()) ;
				} // end if
				
				//useTime, int num, int totalCnt, int price, int totalPrice
//				jta.append((i+1) + "\t" + cv.getPcNum() + "\t" + pcMap.get(cv.getPcNum()) + "\t" + cv.getUseFee()+ "\n");
				totalUseTime += cpcVO.getUseTime() ;
				totalPrice += cpcVO.getUseFee() ;
			} // end for
			
			Set<Integer> keysUseTime = pcMapUseTime.keySet ( ) ;
			
			Iterator<Integer> itaUseTime = keysUseTime . iterator ( ) ;
			int pcNum = 0 ;
			while ( itaUseTime . hasNext() ) {
				pcNum = itaUseTime.next() ;
				jta.append("\t" + pcNum + "\t" + pcMapUseTime.get(pcNum) + "\t" + pcMapPrice.get(pcNum) + "\n") ; // 각 PC 사용시간 & ita.next():PC번호
			} // end while
			
			jta.append("------------------------------------------------------------------------------------------------------------\n") ;
			jta.append("\t총 이용시간 : [" + totalUseTime + " ] 분,\t 총 매출 : [" +  totalPrice +"] 원\n") ;
			

			JOptionPane.showMessageDialog(cv, jsp);
//			JOptionPane.showMessageDialog(null, jsp);
			
			System.out.println(jta.getText());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS에 문제가 발생했습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // viewCalcPCRecipt

		
	public void setCalcPCList(boolean flag) {
		Object[] rowData = null ;
		
//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance() ;
		
		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			List<CalcPCVO> list = aDAO.selectCalcPC() ;
			
			DefaultTableModel dtm = cv.getDtmCalcPC() ;
			dtm.setRowCount(0);
			
			if (list.isEmpty()) {
				if (flag) {
					JOptionPane.showMessageDialog(cv, "PC 결제 내역이 없습니다.");
				}//end if
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
			
			int totalUseTime=0 ;
			int totalPrice=0 ;
			
			todate= null; 
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			
			msg.append("PC코드\tid\tPC번호\t이용금액\t이용시간\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 이용시간 : [" + totalUseTime + " ] 분,\t 총 매출 : [" +  totalPrice +"] 원\n")
			.append("조회 기간 : " + todate) ;
			
//			msg = msg.append(list) ;
			report_file.delete(0, report_file.length()) ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCList
	
	
	public void setCalcPCList7() {
		Object[] rowData = null ;
		
//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance() ;
		
		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPC7() ;
			List<CalcPCVO> list = aDAO.selectCalcPC7() ;
			
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
			
	
			int totalUseTime=0 ;
			int totalPrice=0 ;
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.DATE, -7);
			predate = sdf.format(cal.getTime()) ;
			
			
			msg.append("PC코드\tid\tPC번호\t이용금액\t이용시간\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 이용시간 : [" + totalUseTime + " ] 분,\t 총 매출 : [" +  totalPrice +"] 원\n")
			.append("조회 기간 : " + predate + " ~ " + todate);

			report_file.delete(0, report_file.length()) ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
		
						
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCList
	
	
	public void setCalcPCLstMonth() {
		Object[] rowData = null ;
		
//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance() ;
		
		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPCLstMonth() ;
			List<CalcPCVO> list = aDAO.selectCalcPCLstMonth() ;
			
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
			
			int totalUseTime=0 ;
			int totalPrice=0 ;
			
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.MONTH, -1);
			predate = sdf.format(cal.getTime()) ;
			
			
			msg.append("PC코드\tid\tPC번호\t이용금액\t이용시간\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 이용시간 : [" + totalUseTime + " ] 분,\t 총 매출 : [" +  totalPrice +"] 원\n")
			.append("조회 기간 : " + predate + " ~ " + todate);
			report_file.delete(0, report_file.length()) ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCLstMonth
	
	public void setCalcPCLstCustom() {
		Object[] rowData = null ;
		
//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance() ;
		
		String startDate = cv.getJtfStartPC().getText().trim() ;
		String endDate = cv.getJtfEndPC().getText().trim() ;
		
		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPCLstCustom(startDate, endDate) ;
			List<CalcPCVO> list = aDAO.selectCalcPCLstCustom(startDate, endDate) ;
			
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
			
			int totalUseTime=0 ;
			int totalPrice=0 ;
			
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
			cal = Calendar.getInstance() ;
			
			todate  = cv.getJtfEndPC().getText() ;
			predate= cv.getJtfStartPC().getText() ;
			
			
			msg.append("PC코드\tid\tPC번호\t이용금액\t이용시간\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t총 이용시간 : [" + totalUseTime + " ] 분,\t 총 매출 : [" +  totalPrice +"] 원\n")
			.append("조회 기간 : " + predate + " ~ " + todate);
			
			report_file.delete(0, report_file.length()) ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
		
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "서비스가 원활하지 않습니다.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCLstMonth
	
//		public void mouseClicked(MouseEvent me) {
//
//			if (me.getSource() == cv.getJtp()) { // 주문 탭을 눌렀을 때 이벤트 처리 => 주문현황 조회 시작
//				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
//				if (jtptemp.getSelectedIndex() == 0) {
////					if (ot == null) { // 조회 Thread가 생성되어 있지 않음(주문조회X)
////						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // 선택된 행을 비교, 값을 넣는 일
////						ot.start();
//					setCalcPCList(true);
//					} // end if
//				} // end if
//
//			} // end if
		
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			selectChb=cv.getCgPC().getSelectedCheckbox() ;
			// 조회버튼
			if (ae.getSource()==cv.getJbtnSearchPC()) {
				
				switch (selectChb.getLabel()) {
				case "오늘":
					setCalcPCList(true);
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
			
			
			// 영수증	
			if (ae.getSource() == cv.getJbtCalcPC()) {
				if (selectChb.getLabel()=="오늘") {
					viewCalcPCRecipt();
				} else {
					JOptionPane.showMessageDialog(null, "정산은 당일 내역만 가능합니다.");
					return;
				} // end if
			} // end if
			
			if (ae.getSource()==cv.getJbtnPCSaveFile()) {
				String flag = "";
				switch (selectChb.getLabel()) {
				case "오늘": flag = "today"; break;
				case "일주일": flag = "week"; break;
				case "한 달": flag = "month"; break;
				case "사용자 지정": flag = "custom"; break;
				}
				try {
					reportFile(flag) ;
				JOptionPane.showMessageDialog(cv, fileName+"으로 저장 되었습니다.");
				} catch (IOException e) {
					e.printStackTrace();
				} // end catch
			} // end if
			
		} // actionPerformed
		

		
		public void reportFile(String flag) throws IOException {
			BufferedWriter bw = null;

			
			long currTime = System.currentTimeMillis() ;
			Date currDate = new Date(currTime) ;
			SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd HH-mm-ss") ;
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE") ;
			
			String saveTime1 = sdf1.format(currDate);
			String saveTime2 = sdf2.format(currDate);
			
			
			try {
				File file = new File("c:/dev/pcbang/calc/pc");

				if (!file.exists()) {
				    System.out.println(file.mkdir());
				} // end if
				
				fileName = file.getAbsolutePath() + "/PC_" + saveTime1 + ".dat";
				bw = new BufferedWriter(new FileWriter(fileName));
				
				bw.write( "======================================\n"
						+ "[PC 정산 내역] (" + saveTime2 +"에 저장됨.)\n"
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
//		new CalcPCEvt();
//	} // main

} // class
