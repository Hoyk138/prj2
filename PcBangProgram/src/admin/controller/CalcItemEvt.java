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
				JOptionPane.showMessageDialog(null, "���� �ŷ��� ����� �����ϴ�.");
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
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			
			msg.append("��ǰ��\t�Ǹ� ����\t����\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �Ǹ� ���� : [" + totalQuantity + " ] ��,\t �� ���� : [" +  totalPrice +"] ��") ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());

		} catch (SQLException se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
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
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.DATE, -7);
			predate = sdf.format(cal.getTime()) ;
			
			msg.append("��ǰ��\t�Ǹ� ����\t����\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �Ǹ� ���� : [" + totalQuantity + " ] ��,\t �� ���� : [" +  totalPrice +"] ��\n")
			.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
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
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.MONTH, -1);
			predate = sdf.format(cal.getTime()) ;
			
			msg.append("��ǰ��\t�Ǹ� ����\t����\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �Ǹ� ���� : [" + totalQuantity + " ] ��,\t �� ���� : [" +  totalPrice +"] ��")
			.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
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
			
			int totalQuantity=0 ;
			int totalPrice=0 ;
			
			todate= null; 
			predate= null ;
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��") ;
			cal = Calendar.getInstance() ;
			
			todate  = cv.getJtfEndPC().getText() ;
			predate= cv.getJtfStartPC().getText() ;
			
			msg.append("��ǰ��\t�Ǹ� ����\t����\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalQuantity += list.get(i).getQuantity() ;
				totalPrice += list.get(i).getPrice() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �Ǹ� ���� : [" + totalQuantity + " ] ��,\t �� ���� : [" +  totalPrice +"] ��")
			.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcItemLstMonth
	
	
	
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
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Checkbox selectChb=cv.getCgItem().getSelectedCheckbox() ;
		// ������
		if (ae.getSource() == cv.getJbtCalcItem()) {
			if (selectChb.getLabel()=="����") {
				viewCalcItemRecipt();
			} else {
				JOptionPane.showMessageDialog(null, "������ ���� ������ �����մϴ�.");
			} // end if
		} // end if
			
			
		// ��ȸ��ư
		if (ae.getSource()==cv.getJbtnSearchItem()) {
			
			switch (selectChb.getLabel()) {
			case "����":
				setCalcItemList();
				break;
			case "������":
				setCalcItemList7();
				break;
			case "�� ��":
				setCalcItemLstMonth();
				break;
				
			case "����� ����":
				setCalcItemLstCustom();
				System.out.println("��");
				break;
				
			} // end switch
			
		} // end if
		
		if (ae.getSource()==cv.getJbtnItemSaveFile()) {
			try {
				reportFile() ;
			JOptionPane.showMessageDialog(null, "(C:\\dev\\PCBang_calc_ITEM) ��ο� ����Ǿ����ϴ�.");
				
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
					+ "[���� ���� ����] (" + sdf.format(date)+"�� �����.)\n"
					+ "======================================\n"
					+ getReport_file()
					+ "\n======================================");
			
			// ��Ʈ���� ������ �������� ����
			bw.flush();

		} finally {
			if (bw != null) { bw.close(); } // end if
		} // end finally
		
	}//reportFile

	public String getReport_file() {
		return report_file.toString();
	}
	
	// �����׽�Ʈ��
//	public static void main(String[] args) {
//		new CalcItemEvt();
//	} // main

} // class
