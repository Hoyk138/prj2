package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import user.DAO.UserDAO;
import user.VO.UserOrderVO;
import user.view.UserItem;
import user.view.UserItemDetail;
import user.view.UserMain;

public class UserItemDetailEvt implements ActionListener {
	
	private UserMain um;
	private UserMainEvt ume;
	private UserItemDetail uid;
	private UserItem ui;
	private UserItemEvt uie;
	
	private int price;
	private List<UserOrderVO> orderList;
	
	public UserItemDetailEvt(UserMain um,UserItemDetail uid,UserItem ui, UserItemEvt uie) {
		this.um=um;
		this.uid=uid;
		this.ui=ui;
		this.uie=uie;
		
		price=Integer.parseInt(uid.getJlDetailPrice().getText().trim()); //가격값을 가져옴
	}//UserOrderDetailEvt
	
	/**
	 * 수량에 의한 총금액 설정하는 일
	 */
	private void setTotalPrice() {
		int quan=uid.getJcbQuantity().getSelectedIndex()+1;
		int totalPrice=quan*price;
		
		uid.getJlDetailPrice().setText(String.valueOf(totalPrice));
	}//setTotalPrice
	
	
	/**
	 * 담기버튼을 누르면 선택리스트에 담기는 일
	 */
	public void setPut() {
		//선택리스트
		DefaultListModel<String> dlmChoice=ui.getDlmOrderChoiceList();
		String choiceName=uid.getJlDetailName().getText().trim();
		String choiceQuan=String.valueOf(uid.getJcbQuantity().getSelectedIndex()+1);
		dlmChoice.addElement(choiceName+"▷ 수량:"+choiceQuan+"개");

//		for(int i=0; i<dlmChoice.size();i++) {
//			String sameName=dlmChoice.get(i);
//			if(sameName.equals(choiceName)) {
//				String temp=sameName.substring(sameName.lastIndexOf(":"));
//				int cnt=Integer.parseInt(temp);
//				cnt=cnt+1;
//				sameName=choiceName+"▷ 수량:"+String.valueOf(cnt);
//			}//end if
//		}//end for

		//선택가격
		List<Integer> priceList=uie.getPriceList();
		int choicePrice=Integer.parseInt(uid.getJlDetailPrice().getText().trim());
		
		priceList.add(choicePrice);
		
		int total=0;
		
		for(int i=0;i<uie.getPriceList().size();i++) {
			total+=uie.getPriceList().get(i);
		}//end for
		
		ui.getJtfTotalPrice().setText(String.valueOf(total));
		
		///////////////////////////////////////////////////////////////////////////////
		//VOList(주문)에도 값을 저장해준다.
		
		//pcuserCode
		InetAddress local; 

		int pcNum=0;
		String pcUseCode="";
		
		UserDAO uDAO=UserDAO.getInstance();
			
		try {
			local = InetAddress.getLocalHost(); 
			String ip = local.getHostAddress(); 
			pcNum=uDAO.selectPcNum(ip);
			pcUseCode=uDAO.selectPcUseCode(pcNum);
		} catch (UnknownHostException e1) {
			e1.printStackTrace(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		//수량
		int quan=Integer.parseInt(choiceQuan);

		//itemCode
		String itemCode=uie.getSelectedItemCode();
		
		orderList=uie.getitemOrderList();
		orderList.add(new UserOrderVO(itemCode, pcUseCode, quan));
		
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
			switch(JOptionPane.showConfirmDialog(uid, "장바구니에 담으시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				JOptionPane.showMessageDialog(uid, "장바구니에 담겨졌습니다.");
				close();
				setPut();
			}//end switch
		}//end if
		
		if(ae.getSource()==uid.getJbtClose()) { //닫기 버튼
			close();
		}//end if
		
	}//actionPerformed	

}//class
