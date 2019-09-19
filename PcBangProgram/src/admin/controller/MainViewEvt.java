package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import admin.DAO.CalcPCDAO;
import admin.VO.CalcPCVO;
import admin.view.MainView;

public class MainViewEvt implements ActionListener {

	private MainView mv;

	public MainViewEvt(MainView mv) {
		this.mv = mv;
	}// MainViewEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mv.getJbtnmanageShop()) {
			mv.getCard().show(mv.getJpCenter(), "shop");
		}
		if (ae.getSource() == mv.getJbtnmanagePC()) {
			mv.getCard().show(mv.getJpCenter(), "pc");
		}
		if (ae.getSource() == mv.getJbtnmanageMember()) {
			mv.getCard().show(mv.getJpCenter(), "member");
		}
		if (ae.getSource() == mv.getJbtnmanageItem()) {
			mv.getCard().show(mv.getJpCenter(), "item");
		}
		if (ae.getSource() == mv.getJbtnmanageOrder()) {
			mv.getCard().show(mv.getJpCenter(), "order");
		}
		if (ae.getSource() == mv.getJbtnmanagePayment()) {
			mv.getCard().show(mv.getJpCenter(), "payment");
			List<CalcPCVO> list;
			CalcPCDAO cpcDAO = CalcPCDAO.getInstance();
			try {
				
				list = cpcDAO.selectCalcPC();

				if (list.isEmpty()) {
					JOptionPane.showMessageDialog(null, "PC 결제 내역이 없습니다.");
				} // end if
				
			} catch (SQLException se) {
				se.printStackTrace();
			} // end catch
		} // end if
		
	}// actionPerformed

}
