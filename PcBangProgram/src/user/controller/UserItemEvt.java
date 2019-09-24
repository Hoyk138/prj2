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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import user.DAO.UserDAO;
import user.VO.UserItemDetailVO;
import user.VO.UserItemVO;
import user.VO.UserOrderVO;
import user.VO.selectItemVO;
import user.VO.selectSearchVO;
import user.view.UserItem;
import user.view.UserItemDetail;
import user.view.UserMain;

public class UserItemEvt extends MouseAdapter implements ActionListener{
	
	private UserMain um;
	private UserItem ui;
	private UserItemDetailEvt uide;
	
	private List<UserItemVO> listItem;
	private List<Integer> priceList;
	private List<UserOrderVO> itemOrderList;
	private List<selectSearchVO> selectList;
	
	private String selectedItemCode;
	
	public static final int DOUBLE_CLICK=2;

	public UserItemEvt(UserItem ui,UserItemDetailEvt uide) {
		this.ui=ui;
		this.uide=uide;
		setItem("F",0);
		
		priceList = new ArrayList<Integer>();
		itemOrderList = new ArrayList<UserOrderVO>();
	}//UserOrderEvt
	
	/**
	 * ��ǰ �����͸� ���̺� ��ȸ�ϴ� ��
	 */
	public void setItem(String category, int combo) {
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

		dtm.setRowCount(0); 
		
		Object[] rowData=null;
		
		UserDAO uDAO=UserDAO.getInstance();
		
		try {
			selectItemVO siVO=new selectItemVO(category, combo);
			
			switch(combo) {
			case 0:
				listItem=uDAO.selectOrderBy(siVO);
				break;
			case 1:
				listItem=uDAO.selectOrderBy(siVO);
				break;
			case 2:
				listItem=uDAO.selectOrderBy(siVO);
				break;
			}//end switch
			
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
	 * ��ȸ������ �߻��ϴ� ��ȸ
	 */
	public void setOrderBy() {
		
		int comoboIndex=ui.getJcbOrderBy().getSelectedIndex();
		
		DefaultTableModel dtm=null;
		
		int tabIndex=ui.getJtpOrder().getSelectedIndex();

		String category=null;
		switch(tabIndex) {
		case 0:
			category="F";
			dtm=ui.getDtmFood();
			break;
		case 1:
			category="S";
			dtm=ui.getDtmSnack();
			break;
		case 2:
			category="D";
			dtm=ui.getDtmDrink();
			break;
		}//end switch
		
		dtm.setRowCount(0); 
		
		Object[] rowData=null;
		
		UserDAO uDAO=UserDAO.getInstance();
		selectItemVO siVO=new selectItemVO(category, comoboIndex);
		
		try {
			listItem=uDAO.selectOrderBy(siVO);
			
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
		
		
	}//setOrderBy
	
	/**
	 * �˻��� ��ǰ�� ��ȸ�ϴ� ��
	 */
	public void ItemSearch() {
		
		DefaultListModel<String> listSearch=ui.getDlmSearchList();
		selectSearchVO ssVO=null;
		
		UserDAO uDAO=UserDAO.getInstance();
		
		listSearch.setSize(0); 
		
		String searchText=ui.getJtfSearch().getText();
		try {
			selectList=uDAO.selectSearch(searchText);
			if(selectList.isEmpty()) {
				JOptionPane.showMessageDialog(ui, "������ �˻�� �����ϴ�.");
				ui.getJtfSearch().setText("");
				ui.getJtfSearch().requestFocus();
				return;
			}//end if
			for(int i=0; i<selectList.size();i++) {
				ssVO=selectList.get(i);
				listSearch.addElement(ssVO.getItemName()+"("+ssVO.getPrice()+"��)");
				ui.getJtfSearch().requestFocus();
			}//end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ui, "������ �˻�� �����ϴ�.");
			e.printStackTrace();
		}//end catch
		
	}//ItemSearch
	
	/**
	 * �˻��� ��ǰ �󼼺���
	 */
	public void searchDetail() {
		
		JList<String> list=ui.getJlSearchList();
		int selectIndex=list.getSelectedIndex();
		
		UserDAO uDAO=UserDAO.getInstance();
		
		selectSearchVO ssVO=null;
		String searchItemCode=selectList.get(selectIndex).getItemCode();
		
		try {
			UserItemDetailVO uidVO=uDAO.selectUserItemDetail(searchItemCode);
			new UserItemDetail(ui, this, uidVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException ne) {
			ne.printStackTrace();
		}
		
		
	}//searchDetail
	
	/**
	 *���ø���� ��ǰ�� ����ϴ� ��
	 */
	private void choiceCancel() {
		JList<String> choice=ui.getJltOrderChoiceList();
		DefaultListModel<String> choiceData=ui.getDlmOrderChoiceList();
		int selectIndex=choice.getSelectedIndex(); //0��
		
		if(selectIndex==-1) {
			JOptionPane.showMessageDialog(ui, "����� ��ǰ�� �������ּ���.");
			return;
		}//end if
		
		int cancelPrice=priceList.get(selectIndex); //0��
		int total=Integer.parseInt(ui.getJtfTotalPrice().getText());
		int setTotal=total-cancelPrice;
		
		String choiceItem=choice.getSelectedValue();
		String choiceName=choiceItem.substring(0,choiceItem.lastIndexOf("��"));
		
		switch(JOptionPane.showConfirmDialog(ui, choiceName+"�� ����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			JOptionPane.showMessageDialog(ui, choiceName+"�� ��ҵǾ����ϴ�.");
			choiceData.removeElementAt(selectIndex);
			priceList.remove(selectIndex);
			ui.getJtfTotalPrice().setText(String.valueOf(setTotal));
			itemOrderList.remove(selectIndex);

		}//end switch
		
	}//choiceCancel
	
	/**
	 *	������ ��ǰ ����Ʈ�� �ֹ��ϴ� ��
	 */
	private void setOrder() {
		
		//DB�� �߰�
		UserDAO uDAO=UserDAO.getInstance();
		
			try {
				uDAO.insertOrder(itemOrderList);
				JOptionPane.showMessageDialog(ui, "�ֹ��� �Ϸ�Ǿ����ϴ�.");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ui, "�ֹ��� ó������ �ʾҽ��ϴ�.");
				e.printStackTrace();
			}//end catch
			
		
	}//setOrder
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(ae.getSource()==ui.getJcbOrderBy()) { //��ȸ�� �̺�Ʈ ó��
			setOrderBy();
		}//end if
		
		if(ae.getSource()==ui.getJbtSearch() || ae.getSource()==ui.getJtfSearch()) { //��ǰ�˻� ��ư
			JTextField jtfSearch=ui.getJtfSearch();
			if((jtfSearch.getText()).isEmpty()) {
				JOptionPane.showMessageDialog(ui, "�˻��� ��ǰ�� �Է����ּ���!");
				ui.getJtfSearch().requestFocus();
				return;
			}//end if
				ItemSearch();
		}//end if
		
		if(ae.getSource()==ui.getJbtSearchDetail()) { //�˻��� ��ǰ �󼼺��� ��ư
			JList<String> listDetail=ui.getJlSearchList();
			int detailIndex=listDetail.getSelectedIndex();
			String search=ui.getJtfSearch().getText();
			if(search.isEmpty()) {
				JOptionPane.showMessageDialog(ui, "�˻��Ͻ� ��ǰ�� �����ϴ�.\n��ǰ�� �������ּ���.");
				return;
			}//end if
			if(detailIndex==-1) {
				JOptionPane.showMessageDialog(ui, "�˻��Ͻ� ��ǰ�� �����ϴ�.\n(��ǰ�� �˻��Ͻðų� �������ּ���)");
				return;
			}//end if
			searchDetail();
		}//end if
		
		if(ae.getSource()==ui.getJmCancel()) { //���ø�� ���
			choiceCancel();
		}//end if
		
		if(ae.getSource()==ui.getJbtOrder()) { //�ֹ� ��ư
			if(!ui.getDlmOrderChoiceList().isEmpty()) {
				switch(JOptionPane.showConfirmDialog(ui, "�����Ͻ� ��ǰ�� �ֹ��Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:
					setOrder();
				}//end switch
			}else {
				JOptionPane.showMessageDialog(ui, "�ֹ��� ��ǰ�� �����ϴ�.");
			}//end else
		}//end if
		
		
	}//actionPerformed
	
	 
	/**
	 * ���̺��� �� ������ ��ȸ�ϴ� ��
	 */
	private void detailItem(String item) {
		
		JTable jtItem=null;
		
		switch(item) {
		case "F":
			jtItem=ui.getJtFood();
			break;
		
		case "S":
			jtItem=ui.getJtSnack();
			break;
		
		case "D":
			jtItem=ui.getJtDrink();
			break;
		}//end switch

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
			int combo=ui.getJcbOrderBy().getSelectedIndex();
			JTabbedPane jtpTemp=(JTabbedPane)me.getSource();
			switch (jtpTemp.getSelectedIndex()) {
			case 0:
				setItem("F",combo);
				break;
			case 1:
				setItem("S",combo);
				break;
			case 2:
				setItem("D",combo);
				break;
			}//end switch
			
		}//end if
		
		if(me.getClickCount()==DOUBLE_CLICK) { //����Ŭ��
			
			if(me.getSource()==ui.getJtFood()) {
				
				detailItem("F");
			}//end if
			
			if(me.getSource()==ui.getJtSnack()) {
				detailItem("S");
			}//end if
			
			if(me.getSource()==ui.getJtDrink()) {
				detailItem("D");
			}//end if
			
		}//end if
		

	}//mouseClicked

	public List<Integer> getPriceList() {
		return priceList;
	}

	public String getSelectedItemCode() {
		return selectedItemCode;
	}

	public List<UserOrderVO> getitemOrderList() {
		return itemOrderList;
	}
	
	
	
}//class
