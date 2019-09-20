package user.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale.Category;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import user.DAO.UserDAO;
import user.VO.UserItemDetailVO;
import user.VO.UserItemVO;
import user.VO.UserOrderVO;
import user.view.UserItem;
import user.view.UserItemDetail;

public class UserItemEvt extends MouseAdapter implements ActionListener{
	
	private UserItem ui;
	private UserItemDetailEvt uide;
	
	private List<UserItemVO> listItem;
	private List<Integer> priceList;
	private List<UserOrderVO> itemOrderList;
	
	private String selectedItemCode;
	
	public static final int DOUBLE_CLICK=2;

	public UserItemEvt(UserItem ui,UserItemDetailEvt uide) {
		this.ui=ui;
		this.uide=uide;
		setItem("F");
		
		priceList = new ArrayList<Integer>();
		itemOrderList = new ArrayList<UserOrderVO>();
	}//UserOrderEvt
	
	/**
	 * 상품 데이터를 테이블에 조회하는 일
	 */
	public void setItem(String category) {
		DefaultTableModel dtm=null;
		
		switch(category) {
		case "F":
			dtm=ui.getDtmFood();
			break;
			
		case "S":
			dtm=ui.getDtmSnack();
			break;
			
		case "D":
			dtm=ui.getDtmDrink();
			break;
		}//end switch

		dtm.setRowCount(0); ////////////////뭐야?
		
		Object[] rowData=null;
		UserDAO uDAO=UserDAO.getInstance();
		
		
		try {
			listItem=uDAO.selectUserItemList(category);
			
			UserItemVO uiv=null;
			
			for(int i=0;i<listItem.size();i++) {
				uiv=listItem.get(i);
				rowData=new Object[3];
				rowData[0] = "["+uiv.getCategoryName()+"]" +uiv.getItemName();
				rowData[1]=new ImageIcon(uiv.getItemImg());
				rowData[2]=new Integer(uiv.getPrice());
				dtm.addRow(rowData);
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//setFoodItem
	
	/**
	 *선택목록의 상품을 취소하는 일
	 */
	private void choiceCancel() {
		JList<String> choice=ui.getJltOrderChoiceList();
		DefaultListModel<String> choiceData=ui.getDlmOrderChoiceList();
		int selectIndex=choice.getSelectedIndex(); //0행
		
		int cancelPrice=priceList.get(selectIndex); //0행
		int total=Integer.parseInt(ui.getJtfTotalPrice().getText());
		int setTotal=total-cancelPrice;
		
		if(selectIndex==-1) {
			JOptionPane.showMessageDialog(ui, "취소할 상품을 선택해주세요.");
			return;
		}//end if
		
		String choiceItem=choice.getSelectedValue();
		String choiceName=choiceItem.substring(0,choiceItem.lastIndexOf("-"));
		
		switch(JOptionPane.showConfirmDialog(ui, choiceName+"상품을 취소하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			JOptionPane.showMessageDialog(ui, choiceName+"상품이 취소되었습니다.");
			choiceData.removeElementAt(selectIndex);
			priceList.remove(selectIndex);
			ui.getJtfTotalPrice().setText(String.valueOf(setTotal));
			itemOrderList.remove(selectIndex);

		}//end switch
		
		
	}//choiceCancel
	
	/**
	 *	선택한 상품 리스트를 주문하는 일  //////
	 */
	private void setOrder() {
		
//		for (int i = 0; i < itemOrderList.size(); i++) {
//			System.out.println(itemOrderList.get(i).toString());
//		}
	
		//DB에 추가
		UserDAO uDAO=UserDAO.getInstance();
		
		for(int i=0;i<itemOrderList.size();i++) {
			try {
				uDAO.insertOrder(itemOrderList);
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end for
		
		JOptionPane.showMessageDialog(ui, "주문이 완료되었습니다.");
		
	}//setOrder
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==ui.getJmCancel()) {
			choiceCancel();
		}//end if
		
		if(ae.getSource()==ui.getJbtOrder()) {
			setOrder();
		}//end if
		
	}//actionPerformed
	
	 
	/**
	 * 테이블의 상세 정보를 조회하는 일
	 */
	private void detailItem(String item) {
		
		JTable jtItem=null;
		
		switch(item) {
		case "Food":
			jtItem=ui.getJtFood();
			break;
		
		case "Snack":
			jtItem=ui.getJtSnack();
			break;
		
		case "Drink":
			jtItem=ui.getJtDrink();
			break;
		}//end switch

		
		UserItemVO uiv=null;
		
		UserDAO uDAO=UserDAO.getInstance();
		
		try {
			int selectRow=jtItem.getSelectedRow();
			selectedItemCode=listItem.get(selectRow).getItemCode();
			UserItemDetailVO uidVO=uDAO.selectUserItemDetail(selectedItemCode);
			new UserItemDetail(ui, this, uidVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//detailUserOrder
	
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getSource()==ui.getJtpOrder()) {
			JTabbedPane jtpTemp=(JTabbedPane)me.getSource();
			switch (jtpTemp.getSelectedIndex()) {
			case 0:
				setItem("F");
				break;
			case 1:
				setItem("S");
				break;
			case 2:
				setItem("D");
				break;
			}//end switch
			
		}//end if
		
		if(me.getClickCount()==DOUBLE_CLICK) { //더블클릭
			
			if(me.getSource()==ui.getJtFood()) {
				detailItem("Food");
			}//end if
			
			if(me.getSource()==ui.getJtSnack()) {
				detailItem("Snack");
			}//end if
			
			if(me.getSource()==ui.getJtDrink()) {
				detailItem("Drink");
			}//end if
			
		}//end if
		

	}//mouseClicked

	public List<Integer> getPriceList() {
		return priceList;
	}

//	public List<String> getitemOrderList() {
//		return itemOrderList;
//	}

	public String getSelectedItemCode() {
		return selectedItemCode;
	}

	public List<UserOrderVO> getitemOrderList() {
		return itemOrderList;
	}
	
	
	
}//class
