package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import admin.DAO.AdminDAO;
import admin.VO.CalcPCVO;
import admin.helper.OrderThread;
import admin.view.MainView;

public class MainViewEvt extends WindowAdapter implements ActionListener{

	private MainView mv;
	
	public MainViewEvt(MainView mv) {
		this.mv = mv;
	}//MainViewEvt
	
	@Override
	public void windowClosing(WindowEvent we) {
		String[] options = {"예","아니요"};
		switch (JOptionPane.showOptionDialog(mv, "PC 이용을 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "예")) {
	        case JOptionPane.OK_OPTION:
	        	mv.dispose();
	    }//switch case
	}//windowClosing

	@Override
	public void windowClosed(WindowEvent we) {
			System.exit(0);
	}//windowClosed
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mv.getJbtnmanageShop()) {
			mv.getCard().show(mv.getJpCenter(), "shop");
		}//end if
//		if (ae.getSource() == mv.getJbtnmanagePC()) {
//			mv.getCard().show(mv.getJpCenter(), "pc");
//		}//end if
		if (ae.getSource() == mv.getJbtnmanageMember()) {
			mv.getCard().show(mv.getJpCenter(), "member");
		}//end if
		if (ae.getSource() == mv.getJbtnmanageItem()) {
			mv.getCard().show(mv.getJpCenter(), "item");
		}//end if
		if (ae.getSource() == mv.getJbtnmanageOrder()) {
			mv.getCard().show(mv.getJpCenter(), "order");
			OrderThread ot=new OrderThread(mv.getOv());
			ot.start();
		}//end if
		if (ae.getSource() == mv.getJbtnmanagePayment()) {
			mv.getCard().show(mv.getJpCenter(), "payment");
			List<CalcPCVO> listPC;

//			CalcPCDAO cpcDAO = CalcPCDAO.getInstance();
			AdminDAO aDAO = AdminDAO.getInstance();

			try {
//				listPC = cpcDAO.selectCalcPC();
				listPC = aDAO.selectCalcPC();

				if (listPC.isEmpty()) {
					JOptionPane.showMessageDialog(mv, "PC 결제 내역이 없습니다.");
				} // end if
			} catch (SQLException se) {
				se.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed

}//class
