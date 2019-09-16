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
//				jta.append("������ �� �ִ� �ŷ� ����� �����ϴ�.");
				JOptionPane.showMessageDialog(null, "������ �� �ִ� �ŷ� ����� �����ϴ�.");
				return;
			} // end if

			jta.append(
					"------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("��ȣ\t��ǰ��\t����\t����\n");
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
//			jta.append("�� �Ǹ� ���� : " + /*totalCnt*/"\t" + "��, �� ���� : " +  /*totalPrice*/"\t" + "��");

			JOptionPane.showMessageDialog(null, jsp);

		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS�� ������ �߻��߽��ϴ�.");
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
				JOptionPane.showMessageDialog(cv, "���� �̿� ������ �����ϴ�.");
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
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			se.printStackTrace();
		} // end catch

	} // setCalcItemList

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

	// �����׽�Ʈ��
//	public static void main(String[] args) {
//		new CalcItemEvt();
//	} // main

} // class
