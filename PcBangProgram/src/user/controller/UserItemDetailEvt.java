package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import user.VO.UserOrderVO;
import user.view.UserItem;
import user.view.UserItemDetail;

public class UserItemDetailEvt implements ActionListener {
	
	private UserItemDetail uid;
	private UserItem ui;
	private UserItemEvt uie;
	
	private int price;
	private List<UserOrderVO> orderList;
	
	public UserItemDetailEvt(UserItemDetail uid,UserItem ui, UserItemEvt uie) {
		this.uid=uid;
		this.ui=ui;
		this.uie=uie;
		
		price=Integer.parseInt(uid.getJtfFoodPrice().getText().trim());
	}//UserOrderDetailEvt
	
	/**
	 * ������ ���� �ѱݾ� �����ϴ� ��
	 */
	private void setTotalPrice() {
		int quan=uid.getJcbQuantity().getSelectedIndex()+1;
		int totalPrice=quan*price;
		uid.getJtfFoodPrice().setText(String.valueOf(totalPrice));
	}//setTotalPrice
	
	
	/**
	 * ����ư�� ������ ���ø���Ʈ�� ���� ��
	 */
	public void setPut() {
		//���ø���Ʈ
		DefaultListModel<String> dlmChoice=ui.getDlmOrderChoiceList();
		String choiceName=uid.getJtfName().getText().trim();
		String choiceQuan=String.valueOf(uid.getJcbQuantity().getSelectedIndex()+1);
		dlmChoice.addElement(choiceName+"-����:"+choiceQuan);

		//���ð���
		List<Integer> priceList=uie.getPriceList();
		int choicePrice=Integer.parseInt(uid.getJtfFoodPrice().getText().trim());
		priceList.add(choicePrice);
		
		int total=0;
		
		for(int i=0;i<uie.getPriceList().size();i++) {
			total+=uie.getPriceList().get(i);
		}//end for
		
		ui.getJtfTotalPrice().setText(String.valueOf(total));
		
		
		//pcuserCode
		String pcCode="P_000005";
		
		//����
		int quan=Integer.parseInt(choiceQuan);

		//itemCode
		String itemCode=uie.getSelectedItemCode();
		
		
		orderList=uie.getitemOrderList();
		orderList.add(new UserOrderVO(itemCode, pcCode, quan));
		
		
	}//setPut
	
	/**
	 * ��â�� �ݴ��� 
	 */
	private void close() {
		uid.dispose();
	}//close

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uid.getJcbQuantity()) { //������ �̺�Ʈ ó��
			setTotalPrice();
		}//end if
		
		if(ae.getSource()==uid.getJbtPut()) { //�ֹ���Ͽ� ��� ��ư
			setPut();
		}//end if
		
		if(ae.getSource()==uid.getJbtClose()) { //�ݱ� ��ư
			close();
		}//end if
		
	}//actionPerformed	


}//class
