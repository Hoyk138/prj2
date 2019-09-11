package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableModel;

import user.view.UserItem;
import user.view.UserItemDetail;

public class UserItemEvt extends MouseAdapter implements ActionListener{
	
	private UserItem ui;
	public static final int DOUBLE_CLICK=2;

	public UserItemEvt(UserItem ui) {
		this.ui=ui;
	}//UserOrderEvt
	
	/**
	 * ī�װ����� ��ǰ �����ϴ� ��
	 */
	private void setItemList() {
		
//		DefaultTableModel dtm
		
	}//setOrder
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==ui.getJbtOrder()) {
			setItemList();
		}//end if
	}//actionPerformed
	
	/**
	 * ������ ���� item_code�� �����ͼ� �� ���� ��ȸ
	 */
	private void detailUserOrder() {
		
		
	}//detailUserOrder

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount()==DOUBLE_CLICK) { //����Ŭ��
			if(me.getSource()==ui.getJtFood()||me.getSource()==ui.getJtSnack()||me.getSource()==ui.getJtDrink()) {
//				detailUserOrder();
				new UserItemDetail(ui);
			}//end if
		}//end if
		
	}//mouseClicked
	
	

}//class
