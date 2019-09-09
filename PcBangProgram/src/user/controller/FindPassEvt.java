package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user.view.FindPass;


public class FindPassEvt implements ActionListener {

	private FindPass fp;
	
	public FindPassEvt(FindPass fp) {
		this.fp=fp;
	}//FindPassEvt
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}//actionPerformed

}//class
