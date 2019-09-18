package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import user.view.UserItem;
import user.view.UserItemDetail;

public class UserItemDetailEvt implements ActionListener {
	
	private UserItemDetail uid;
	private UserItem ui;
	private UserItemEvt uie;
	
	private int price;
	
	public UserItemDetailEvt(UserItemDetail uid,UserItem ui, UserItemEvt uie) {
		this.uid=uid;
		this.ui=ui;
		this.uie=uie;
		
		price=Integer.parseInt(uid.getJtfFoodPrice().getText().trim());
	}//UserOrderDetailEvt
	
	/**
	 * 수량에 의한 총금액 설정하는 일
	 */
	private void setTotalPrice() {
		int quan=uid.getJcbQuantity().getSelectedIndex()+1;
		int totalPrice=quan*price;
		uid.getJtfFoodPrice().setText(String.valueOf(totalPrice));
	}//setTotalPrice
	
	
	/**
	 * 담기버튼을 누르면 선택리스트에 담기는 일
	 */
	public void setPut() {
		//선택리스트
		DefaultListModel<String> dlmChoice=ui.getDlmOrderChoiceList();
		String choiceName=uid.getJtfName().getText().trim();
		String choiceQuan=String.valueOf(uid.getJcbQuantity().getSelectedIndex()+1);
		dlmChoice.addElement(choiceName+"-수량:"+choiceQuan);

		//선택가격
		List<Integer> priceList=uie.getPriceList();
		int choicePrice=new Integer(uid.getJtfFoodPrice().getText().trim());
		priceList.add(choicePrice);

		int total=0;
	
		for(int i=0;i<uie.getPriceList().size();i++) {
			total+=uie.getPriceList().get(i);
		}//end for
		
		ui.getJtfTotalPrice().setText(String.valueOf(total));
		
		
		//선택된 상품코드(itemCode)
		List<String> codeList=uie.getItemCodeList();
		codeList.add(uie.getSelectedItemCode());
//		System.out.println(codeList+choiceQuan);
		
	}//setPut
	
	/**
	 * 상세창을 닫는일 
	 */
	private void close() {
		uid.dispose();
	}//close

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uid.getJcbQuantity()) { //수량의 이벤트 처리
			setTotalPrice();
		}//end if
		
		if(ae.getSource()==uid.getJbtPut()) { //주문목록에 담기 버튼
			setPut();
		}//end if
		
		if(ae.getSource()==uid.getJbtClose()) { //닫기 버튼
			close();
		}//end if
		
	}//actionPerformed	

}//class
