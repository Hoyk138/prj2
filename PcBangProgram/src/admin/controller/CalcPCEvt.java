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
//				jta.append("������ �� �ִ� �ŷ� ����� �����ϴ�.");
//			} // end if
			
			jta.append("------------------------------------------------------------------------------------------------------------------------\n");
			jta.append("��ȣ\t�̿� �ð�\t�̿� �ݾ�\n");
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
			jta.append("�� �� : " + /*totalCnt*/"\t" + ", �� ���� : " +  /*totalPrice*/"\t" + "��");
			

//			JOptionPane.showMessageDialog(cv, jsp);
			JOptionPane.showMessageDialog(null, jsp);
			
			
//		} catch (SQLException Se) {
//			JOptionPane.showMessageDialog(lm, "DBMS�� ������ �߻��߽��ϴ�.");
//			Se.printStackTrace();
//		} // end catch
		
	} // viewCalc
	
//	private void lunchClose() {
//		switch (JOptionPane.showConfirmDialog(lm, "�����ö� ������ ���α׷��� �����Ͻðڽ��ϱ�?")) {
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
//		if (ae.getSource() == lm.getJbtClose()) { // ���� ��ư
//			lunchClose();
//		} // end if
//		
//		public void mouseClicked(MouseEvent me) {
//
//			if (me.getSource() == lm.getJtp()) { // �ֹ� ���� ������ �� �̺�Ʈ ó�� => �ֹ���Ȳ ��ȸ ����
//				JTabbedPane jtptemp = (JTabbedPane) me.getSource();
//				if (jtptemp.getSelectedIndex() == 1) {
//					if (ot == null) { // ��ȸ Thread�� �����Ǿ� ���� ����(�ֹ���ȸX)
//						ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList()); // ���õ� ���� ��, ���� �ִ� ��
//						ot.start();
//					} // end if
//				} // end if
//
//			} // end if
		
	// �����׽�Ʈ��
	public static void main(String[] args) {
		new CalcPCEvt();
	} // main

} // class
