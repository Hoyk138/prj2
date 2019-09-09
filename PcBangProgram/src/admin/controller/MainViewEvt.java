package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		}
	}//actionPerformed

}
