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
		
		price=Integer.parseInt(uid.getJlDetailPrice().getText().trim()); //���ݰ��� ������
	}//UserOrderDetailEvt
	
	/**
	 * ������ ���� �ѱݾ� �����ϴ� ��
	 */
	private void setTotalPrice() {
		int quan=uid.getJcbQuantity().getSelectedIndex()+1;
		int totalPrice=quan*price;
		
		uid.getJlDetailPrice().setText(String.valueOf(totalPrice));
	}//setTotalPrice
	
	
	/**
	 * ����ư�� ������ ���ø���Ʈ�� ���� ��
	 */
	public void setPut() {
		//���ø���Ʈ
		DefaultListModel<String> dlmChoice=ui.getDlmOrderChoiceList();
		String choiceName=uid.getJlDetailName().getText().trim();
		String choiceQuan=String.valueOf(uid.getJcbQuantity().getSelectedIndex()+1);
		dlmChoice.addElement(choiceName+"�� ����:"+choiceQuan+"��");

//		for(int i=0; i<dlmChoice.size();i++) {
//			String sameName=dlmChoice.get(i);
//			if(sameName.equals(choiceName)) {
//				String temp=sameName.substring(sameName.lastIndexOf(":"));
//				int cnt=Integer.parseInt(temp);
//				cnt=cnt+1;
//				sameName=choiceName+"�� ����:"+String.valueOf(cnt);
//			}//end if
//		}//end for

		//���ð���
		List<Integer> priceList=uie.getPriceList();
		int choicePrice=Integer.parseInt(uid.getJlDetailPrice().getText().trim());
		
		priceList.add(choicePrice);
		
		int total=0;
		
		for(int i=0;i<uie.getPriceList().size();i++) {
			total+=uie.getPriceList().get(i);
		}//end for
		
		ui.getJtfTotalPrice().setText(String.valueOf(total));
		
		///////////////////////////////////////////////////////////////////////////////
		//VOList(�ֹ�)���� ���� �������ش�.
		
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
		
		//����
		int quan=Integer.parseInt(choiceQuan);

		//itemCode
		String itemCode=uie.getSelectedItemCode();
		
		orderList=uie.getitemOrderList();
		orderList.add(new UserOrderVO(itemCode, pcUseCode, quan));
		
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
			switch(JOptionPane.showConfirmDialog(uid, "��ٱ��Ͽ� �����ðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				JOptionPane.showMessageDialog(uid, "��ٱ��Ͽ� ��������ϴ�.");
				close();
				setPut();
			}//end switch
		}//end if
		
		if(ae.getSource()==uid.getJbtClose()) { //�ݱ� ��ư
			close();
		}//end if
		
	}//actionPerformed	

}//class
