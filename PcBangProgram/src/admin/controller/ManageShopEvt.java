package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.view.ManagePC;
import admin.view.ManageShop;

public class ManageShopEvt implements ActionListener{

	private ManageShop ms;
	
	public ManageShopEvt(ManageShop ms) {
		this.ms = ms;
		
	}//ManageShopEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==ms.getJbtnPCManage()) {
//			System.out.println("PC°ü¸®");
			new ManagePC(ms.getMv());
		}//end if
//        if (ae.getSource()==ms.getJbtnLogManage()) {
//			
//		}//end if
	}//actionPerformed

}//class
