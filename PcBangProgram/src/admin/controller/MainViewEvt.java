package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import admin.DAO.CalcPCDAO;
import admin.VO.CalcPCVO;
import admin.helper.OrderThread;
import admin.view.MainView;

public class MainViewEvt implements ActionListener{

	private MainView mv;
	
	public MainViewEvt(MainView mv) {
		this.mv = mv;
	}//MainViewEvt
	
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

			CalcPCDAO cpcDAO = CalcPCDAO.getInstance();

			try {
				listPC = cpcDAO.selectCalcPC();

				if (listPC.isEmpty()) {
					JOptionPane.showMessageDialog(mv, "PC ���� ������ �����ϴ�.");
				} // end if
			} catch (SQLException se) {
				se.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed

}//class
