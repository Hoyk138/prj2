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
import admin.VO.CalcPCVO;
import admin.view.CalcView;

public class CalcPCEvt extends MouseAdapter implements ActionListener {

	private CalcView cv;
	private String fileName;
	private StringBuilder msg;
	private StringBuilder report_file;
	private SimpleDateFormat sdf;
	private Calendar cal;
	private String todate;
	private String predate;

	private Checkbox selectChb;

	public CalcPCEvt(CalcView cv) {
		this.cv = cv;
		report_file = new StringBuilder();
		msg = new StringBuilder();
		setCalcPCList(false);
	} // CalcPCEvt

	private void viewCalcPCRecipt() {
		DefaultTableModel dtm = cv.getDtmCalcPC();
		if (dtm.getRowCount() == 0) {
			JOptionPane.showMessageDialog(cv, "��ȸ �� ����� �����ϴ�.");
			return;
		} // end if

		Map<Integer, Integer> pcMapUseTime = new HashMap<Integer, Integer>(30);
		Map<Integer, Integer> pcMapPrice = new HashMap<Integer, Integer>(30);

		// { "�ڵ�", "PC��ȣ", "�����(ID)", "�̿�ð�(��)", "�̿�ݾ�(��)" };
		Integer pcNum = null;
		Integer useTime = null;
		Integer useFee = null;
		int totalUseTime = 0;
		int totalPrice = 0;
		for (int i = 0; i < dtm.getRowCount(); i++) {
			pcNum = (Integer) dtm.getValueAt(i, 1);// PC��ȣ
			useTime = (Integer) dtm.getValueAt(i, 3);// �̿�ð�(��)
			useFee = (Integer) dtm.getValueAt(i, 4);// �̿�ݾ�(��)

			if (pcMapUseTime.containsKey(pcNum)) {
				pcMapUseTime.put(pcNum, pcMapUseTime.get(pcNum) + useTime);
			} else {
				pcMapUseTime.put(pcNum, useTime);
			} // end if
			if (pcMapPrice.containsKey(pcNum)) {
				pcMapPrice.put(pcNum, pcMapPrice.get(pcNum) + useFee);
			} else {
				pcMapPrice.put(pcNum, useFee);
			} // end if

			totalUseTime += useTime;
			totalPrice += useFee;
		} // end for

		JTextArea jta = new JTextArea(20, 50);
		JScrollPane jsp = new JScrollPane(jta);
		jta.append(
				"------------------------------------------------------------------------------------------------------------\n");
		jta.append("\tPC��ȣ\t�̿� �ð�(��)\t�̿� �ݾ�(��)\n");
		jta.append("==============================================================\n");

		for (int i = 1; i <= 25; i++) {
			if (pcMapUseTime.containsKey(i)) {
				jta.append("\t" + i + "\t" + pcMapUseTime.get(i) + "\t" + pcMapPrice.get(i) + "\n");
			} else {
				jta.append("\t" + i + "\t0\t0\n");
			} // if else
		} // end for

		jta.append(
				"------------------------------------------------------------------------------------------------------------\n");
		jta.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" + totalPrice + "] ��\n");

		JOptionPane.showMessageDialog(cv, jsp);

////		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
//		AdminDAO aDAO = AdminDAO.getInstance() ;
//		
//		try {
////			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
//			List<CalcPCVO> list = aDAO.selectCalcPC();
//
//			JTextArea jta = new JTextArea(20, 50);
//			JScrollPane jsp = new JScrollPane(jta);
//			if (list.isEmpty()) {
//				JOptionPane.showMessageDialog(null, "���� �ŷ��� ����� �����ϴ�.");
//				return;
//			} // end if
//
//			jta.append(
//					"------------------------------------------------------------------------------------------------------------\n");
//			jta.append("\tPC��ȣ\t�̿� �ð�(��)\t�̿� �ݾ�(��)\n");
//			jta.append("==============================================================\n");
//
//			CalcPCVO cpcVO = null;
//			int totalUseTime = 0;
//			int totalPrice = 0;
//			for (int i = 0; i < list.size(); i++) {
//				cpcVO = list.get(i);
//
//				if (pcMapUseTime.containsKey(cpcVO.getPcNum())) {
//					pcMapUseTime.put(cpcVO.getPcNum(), pcMapUseTime.get(cpcVO.getPcNum()) + cpcVO.getUseTime());
//				} else {
//					pcMapUseTime.put(cpcVO.getPcNum(), cpcVO.getUseTime());
//				} // end if
//				if (pcMapPrice.containsKey(cpcVO.getPcNum())) {
//					pcMapPrice.put(cpcVO.getPcNum(), pcMapPrice.get(cpcVO.getPcNum()) + cpcVO.getUseFee());
//				} else {
//					pcMapPrice.put(cpcVO.getPcNum(), cpcVO.getUseFee());
//				} // end if
//
//				// useTime, int num, int totalCnt, int price, int totalPrice
////				jta.append((i+1) + "\t" + cv.getPcNum() + "\t" + pcMap.get(cv.getPcNum()) + "\t" + cv.getUseFee()+ "\n");
//				totalUseTime += cpcVO.getUseTime();
//				totalPrice += cpcVO.getUseFee();
//			} // end for
//
//			Set<Integer> keysUseTime = pcMapUseTime.keySet();
//
//			Iterator<Integer> itaUseTime = keysUseTime.iterator();
//			int pcNum = 0;
//			while (itaUseTime.hasNext()) {
//				pcNum = itaUseTime.next();
//				// �� PC ���ð� & ita.next():PC��ȣ
//				jta.append("\t" + pcNum + "\t" + pcMapUseTime.get(pcNum) + "\t" + pcMapPrice.get(pcNum) + "\n"); 
//			} // end while
//
//			jta.append(
//					"------------------------------------------------------------------------------------------------------------\n");
//			jta.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" + totalPrice + "] ��\n");
//
//			JOptionPane.showMessageDialog(cv, jsp);
////			JOptionPane.showMessageDialog(null, jsp);
//
//			System.out.println(jta.getText());
//
//		} catch (SQLException Se) {
//			JOptionPane.showMessageDialog(cv, "DBMS�� ������ �߻��߽��ϴ�.");
//			Se.printStackTrace();
//		} // end catch

	} // viewCalcPCRecipt

	public void setCalcPCList(boolean flag) {
		Object[] rowData = null;

//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			List<CalcPCVO> list = aDAO.selectCalcPC();

			DefaultTableModel dtm = cv.getDtmCalcPC();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				if (flag) {
					JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
				} // end if
				return;
			} // end if

			CalcPCVO cpcVO = null;

			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i);

				rowData = new Object[5];

				rowData[0] = cpcVO.getPcCode();
				rowData[1] = cpcVO.getPcNum();
				rowData[2] = cpcVO.getId();
				rowData[3] = cpcVO.getUseTime();
				rowData[4] = cpcVO.getUseFee();

				dtm.addRow(rowData);
			} // end for

			int totalUseTime = 0;
			int totalPrice = 0;

			todate = null;
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��");
			cal = Calendar.getInstance();

			todate = sdf.format(cal.getTime());

			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalUseTime += list.get(i).getUseTime();
				totalPrice += list.get(i).getUseFee();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" + totalPrice + "] ��\n")
					.append("��ȸ �Ⱓ : " + todate);

//			msg = msg.append(list) ;
			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch

	} // setCalcPCList

	public void setCalcPCList7() {
		Object[] rowData = null;

//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPC7() ;
			List<CalcPCVO> list = aDAO.selectCalcPC7();

			DefaultTableModel dtm = cv.getDtmCalcPC();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
				return;
			} // end if

			CalcPCVO cpcVO = null;

			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i);

				rowData = new Object[5];

				rowData[0] = cpcVO.getPcCode();
				rowData[1] = cpcVO.getPcNum();
				rowData[2] = cpcVO.getId();
				rowData[3] = cpcVO.getUseTime();
				rowData[4] = cpcVO.getUseFee();

				dtm.addRow(rowData);
			} // end for

			int totalUseTime = 0;
			int totalPrice = 0;
			todate = null;
			predate = null;
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��");
			cal = Calendar.getInstance();

			todate = sdf.format(cal.getTime());
			cal.add(Calendar.DATE, -7);
			predate = sdf.format(cal.getTime());

			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalUseTime += list.get(i).getUseTime();
				totalPrice += list.get(i).getUseFee();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" + totalPrice + "] ��\n")
					.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);

			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch

	} // setCalcPCList

	public void setCalcPCLstMonth() {
		Object[] rowData = null;

//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPCLstMonth() ;
			List<CalcPCVO> list = aDAO.selectCalcPCLstMonth();

			DefaultTableModel dtm = cv.getDtmCalcPC();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
				return;
			} // end if

			CalcPCVO cpcVO = null;

			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i);

				rowData = new Object[5];

				rowData[0] = cpcVO.getPcCode();
				rowData[1] = cpcVO.getPcNum();
				rowData[2] = cpcVO.getId();
				rowData[3] = cpcVO.getUseTime();
				rowData[4] = cpcVO.getUseFee();

				dtm.addRow(rowData);
			} // end for

			int totalUseTime = 0;
			int totalPrice = 0;

			todate = null;
			predate = null;
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��");
			cal = Calendar.getInstance();

			todate = sdf.format(cal.getTime());
			cal.add(Calendar.MONTH, -1);
			predate = sdf.format(cal.getTime());

			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalUseTime += list.get(i).getUseTime();
				totalPrice += list.get(i).getUseFee();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" + totalPrice + "] ��\n")
					.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);
			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch

	} // setCalcPCLstMonth

	public void setCalcPCLstCustom() {
		Object[] rowData = null;

//		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		AdminDAO aDAO = AdminDAO.getInstance();

		String startDate = cv.getJtfStartPC().getText().trim();
		String endDate = cv.getJtfEndPC().getText().trim();

		try {
//			List<CalcPCVO> list = cpcDAO.selectCalcPCLstCustom(startDate, endDate) ;
			List<CalcPCVO> list = aDAO.selectCalcPCLstCustom(startDate, endDate);

			DefaultTableModel dtm = cv.getDtmCalcPC();
			dtm.setRowCount(0);

			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
				return;
			} // end if

			CalcPCVO cpcVO = null;

			for (int i = 0; i < list.size(); i++) {
				cpcVO = list.get(i);

				rowData = new Object[5];

				rowData[0] = cpcVO.getPcCode();
				rowData[1] = cpcVO.getPcNum();
				rowData[2] = cpcVO.getId();
				rowData[3] = cpcVO.getUseTime();
				rowData[4] = cpcVO.getUseFee();

				dtm.addRow(rowData);
			} // end for

			int totalUseTime = 0;
			int totalPrice = 0;

			todate = null;
			predate = null;
			sdf = new SimpleDateFormat("yyyy�� MM�� dd��");
			cal = Calendar.getInstance();

			todate = cv.getJtfEndPC().getText();
			predate = cv.getJtfStartPC().getText();

			msg.append("PC�ڵ�\tid\tPC��ȣ\t�̿�ݾ�\t�̿�ð�\n");

			for (int i = 0; i < list.size(); i++) {
				msg.append(list.get(i)).toString();

				totalUseTime += list.get(i).getUseTime();
				totalPrice += list.get(i).getUseFee();
			} // end for

			msg.append("-------------------------------------------------------------\n")
					.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" + totalPrice + "] ��\n")
					.append("��ȸ �Ⱓ : " + predate + " ~ " + todate);

			report_file.delete(0, report_file.length());
			report_file = report_file.append(msg);
			msg.delete(0, msg.length());

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch

	} // setCalcPCLstMonth

//		public void mouseClicked(MouseEvent me) {
//
//			if (me.getSource() == cv.getJtp()) { // �ֹ� ���� ������ �� �̺�Ʈ ó�� => �ֹ���Ȳ ��ȸ ����
//				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
//				if (jtptemp.getSelectedIndex() == 0) {
////					if (ot == null) { // ��ȸ Thread�� �����Ǿ� ���� ����(�ֹ���ȸX)
////						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // ���õ� ���� ��, ���� �ִ� ��
////						ot.start();
//					setCalcPCList(true);
//					} // end if
//				} // end if
//
//			} // end if

	@Override
	public void actionPerformed(ActionEvent ae) {

		selectChb = cv.getCgPC().getSelectedCheckbox();
		// ��ȸ��ư
		if (ae.getSource() == cv.getJbtnSearchPC()) {

			switch (selectChb.getLabel()) {
			case "����":
				setCalcPCList(true);
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
			viewCalcPCRecipt();
//				if (selectChb.getLabel()=="����") {
//					viewCalcPCRecipt();
//				} else {
//					JOptionPane.showMessageDialog(null, "������ ���� ������ �����մϴ�.");
//					return;
//				} // end if
		} // end if

		if (ae.getSource() == cv.getJbtnPCSaveFile()) {
			String flag = "";
			switch (selectChb.getLabel()) {
			case "����":
				flag = "today";
				break;
			case "������":
				flag = "week";
				break;
			case "�� ��":
				flag = "month";
				break;
			case "����� ����":
				flag = "custom";
				break;
			}
			try {
				reportFile(flag);
				JOptionPane.showMessageDialog(cv, fileName + "���� ���� �Ǿ����ϴ�.");
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
			File file = new File("c:/dev/pcbang/admin/calc/pc");

			if (!file.exists()) {
				System.out.println(file.mkdir());
			} // end if

			fileName = file.getAbsolutePath() + "\\PC_" + saveTime1 + ".dat";
			bw = new BufferedWriter(new FileWriter(fileName));

			bw.write("======================================\n" + "[PC ���� ����] (" + saveTime2 + "�� �����.)\n"
					+ "======================================\n" + getReport_file()
					+ "\n======================================");

			// ��Ʈ���� ������ �������� ����
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

	// �����׽�Ʈ��
//	public static void main(String[] args) {
//		new CalcPCEvt();
//	} // main

} // class
