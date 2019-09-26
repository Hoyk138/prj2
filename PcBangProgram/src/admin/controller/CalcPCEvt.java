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

import admin.DAO.CalcPCDAO;
import admin.VO.CalcPCVO;
import admin.view.CalcView;

public class CalcPCEvt extends MouseAdapter implements ActionListener {
	
	private CalcView cv ;
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
	
	
	
	public CalcPCEvt(CalcView cv) {
		this.cv = cv ;
		report_file = new StringBuilder();
		msg = new StringBuilder() ;
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
				JOptionPane.showMessageDialog(null,"���� �ŷ��� ����� �����ϴ�.");
				return ;
			} // end if				
			
			jta.append("------------------------------------------------------------------------------------------------------------\n");
			jta.append("\tPC��ȣ\t�̿� �ð�(��)\t�̿� �ݾ�(��)\n");
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
				jta.append("\t" + pcNum + "\t" + pcMap.get(pcNum) + "\t" + pcMap.get(pcNum)*20 + "\n") ; // �� PC ���ð� & ita.next():PC��ȣ
			} // end while
			
			jta.append("------------------------------------------------------------------------------------------------------------\n") ;
			jta.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" +  totalPrice +"] ��\n") ;
			

			JOptionPane.showMessageDialog(cv, jsp);
//			JOptionPane.showMessageDialog(null, jsp);
			
			System.out.println(jta.getText());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS�� ������ �߻��߽��ϴ�.");
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
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
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
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			
			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" +  totalPrice +"] ��\n")
			.append("��ȸ �Ⱓ : " + todate) ;
			
//			msg = msg.append(list) ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
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
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
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
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.DATE, -7);
			predate = sdf.format(cal.getTime()) ;
			
			report_file.delete(0, report_file.length()) ;
			
			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" +  totalPrice +"] ��\n")
			.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);

			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
		
						
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
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
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
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
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��") ;
			cal = Calendar.getInstance() ;
			
			todate = sdf.format(cal.getTime()) ;
			cal.add(Calendar.MONTH, -1);
			predate = sdf.format(cal.getTime()) ;
			
			report_file.delete(0, report_file.length()) ;
			
			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" +  totalPrice +"] ��\n")
			.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
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
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
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
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��") ;
			cal = Calendar.getInstance() ;
			
			todate  = cv.getJtfEndPC().getText() ;
			predate= cv.getJtfStartPC().getText() ;
			
			report_file.delete(0, report_file.length()) ;
			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n") ;
			
			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString() ;
				
				totalUseTime += list.get(i).getUseTime() ;
				totalPrice += list.get(i).getUseFee() ;
			} // end for
			
			msg.append("-------------------------------------------------------------\n")
			.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" +  totalPrice +"] ��\n")
			.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);
//			msg = msg.append(list) ;
			report_file = report_file.append(msg) ;
			msg.delete(0, msg.length());
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCLstMonth
	
		public void mouseClicked(MouseEvent me) {

			if (me.getSource() == cv.getJtp()) { // �ֹ� ���� ������ �� �̺�Ʈ ó�� => �ֹ���Ȳ ��ȸ ����
				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
				if (jtptemp.getSelectedIndex() == 0) {
//					if (ot == null) { // ��ȸ Thread�� �����Ǿ� ���� ����(�ֹ���ȸX)
//						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // ���õ� ���� ��, ���� �ִ� ��
//						ot.start();
					setCalcPCList();
					} // end if
				} // end if

			} // end if
		
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			Checkbox selectChb=cv.getCgPC().getSelectedCheckbox() ;
			// ��ȸ��ư
			if (ae.getSource()==cv.getJbtnSearchPC()) {
				
				switch (selectChb.getLabel()) {
				case "����":
					setCalcPCList();
					break;
				case "������":
					setCalcPCList7();
					break;
				case "�� ��":
					setCalcPCLstMonth();
					break;
					
				case "����� ����":
					setCalcPCLstCustom();
					break;
				}
			} // end if
			
			
			// ������	
			if (ae.getSource() == cv.getJbtCalcPC()) {
				if (selectChb.getLabel()=="����") {
					viewCalcPCRecipt();
				} else {
					JOptionPane.showMessageDialog(null, "������ ���� ������ �����մϴ�.");
					return;
				} // end if
			} // end if
			
			if (ae.getSource()==cv.getJbtnPCSaveFile()) {
				try {
					reportFile() ;
				JOptionPane.showMessageDialog(null, "(C:\\dev\\PCBang_calc_PC) ��ο� ����Ǿ����ϴ�.");
					
				} catch (IOException e) {
					e.printStackTrace();
				} // end catch
			} // end if
			
		} // actionPerformed
		

		
		public void reportFile() throws IOException {
			
			BufferedWriter bw = null;
			date = System.currentTimeMillis() ;
			today = new Date ( date ); 
//			format = DateFormat.getInstance(); 
//			formatted = format.format ( today ); 
			sdf = new SimpleDateFormat("yyyy-MM-dd HH/mm/") ;
			
			
			try {
				File file = new File("c:/dev/PCBang_calc_PC");

				if (!file.exists()) {
					file.mkdir();
				} // end if
				
				fileName = file.getAbsolutePath() + "/PC_" + date + ".dat";
				bw = new BufferedWriter(new FileWriter(fileName));
				
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE") ;
				bw.write( "======================================\n"
						+ "[PC ���� ����] (" + sdf.format(date) +"�� �����.)\n"
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
//		new CalcPCEvt();
//	} // main

} // class
