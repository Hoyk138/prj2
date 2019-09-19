package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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

import admin.DAO.CalcPCDAO;
import admin.VO.CalcPCVO;
import admin.view.CalcView;

public class CalcPCEvt extends MouseAdapter implements ActionListener {
	
	private CalcView cv ;
	
	public CalcPCEvt(CalcView cv) {
		this.cv = cv ;
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
				JOptionPane.showMessageDialog(null,"������ �� �ִ� �ŷ� ����� �����ϴ�.");
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
			
			jta.append("------------------------------------------------------------------------------------------------------------\n");
			jta.append("\t�� �̿�ð� : [" + totalUseTime + " ] ��,\t �� ���� : [" +  totalPrice +"] ��");
			

//			JOptionPane.showMessageDialog(cv, jsp);
			JOptionPane.showMessageDialog(null, jsp);
			
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "DBMS�� ������ �߻��߽��ϴ�.");
			Se.printStackTrace();
		} // end catch
		
	} // CalcPCEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == cv.getJbtCalcPC()) {
			viewCalcPCRecipt();
		} // end if
	}
		
	public void setCalcPCList() {
		Object[] rowData = null ;
		
		CalcPCDAO cpcDAO = CalcPCDAO.getInstance() ;
		
		try {
			List<CalcPCVO> list = cpcDAO.selectCalcPC() ;
			
			if (list.isEmpty()) {
				JOptionPane.showMessageDialog(cv, "PC ���� ������ �����ϴ�.");
				return;
			} // end if
			DefaultTableModel dtm = cv.getDtmCalcPC() ;
			dtm.setRowCount(0);
			
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
			
		} catch (SQLException Se) {
			JOptionPane.showMessageDialog(cv, "���񽺰� ��Ȱ���� �ʽ��ϴ�.");
			Se.printStackTrace();
		} // end catch
		
	} // setCalcPCList
	
	
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
		
	// �����׽�Ʈ��
//	public static void main(String[] args) {
//		new CalcPCEvt();
//	} // main

} // class
