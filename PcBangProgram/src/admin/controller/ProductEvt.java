package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.DAO.AdminDAO;
import admin.VO.ProductMDViewVO;
import admin.VO.ProductViewVO;
import admin.view.MainView;
import admin.view.ModifyDeleteView;
import admin.view.ProductAddView;
import admin.view.ProductView;

public class ProductEvt extends MouseAdapter implements ActionListener {

	private ProductView pv;
	private ProductViewVO pvVO;
	
	private MainView mv;

	public ProductEvt(ProductView pv, MainView mv) {
		this.pv = pv;
		this.mv = mv;
		setFoodList();
		setSnackList();
		setDrinkList();
	}// 기본생성자

	public void setFoodList() {
		DefaultTableModel dtmF = pv.getDtmFood();

		dtmF.setRowCount(0);
		Object[] rowData = null;

//		heeDAO hDAO = heeDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<ProductViewVO> list = hDAO.selectProductView();
			List<ProductViewVO> list = aDAO.selectProductView();

			pvVO = null;

			Object cateFlag = new Object();

			for (int i = 0; i < list.size(); i++) {
				pvVO = list.get(i);
				rowData = new Object[6];
				cateFlag = pvVO.getProductCategory();
				if (cateFlag.equals("F")) {
					rowData[0] = pvVO.getProductCode();
					rowData[1] = pvVO.getProductName();
					rowData[2] = new ImageIcon("C:/dev/pcbang/admin/img/item/"+"rs_"+pvVO.getProductImg());
					rowData[3] = pvVO.getProductExplain();
					rowData[4] = pvVO.getProductPrice();
					rowData[5] = pvVO.getProductInputDate();
					dtmF.addRow(rowData);
				}
			} // end for

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}// setFoodList

	public void setSnackList() {
		DefaultTableModel dtmS = pv.getDtmSnack();

		dtmS.setRowCount(0);
		Object[] rowData = null;

//		heeDAO hDAO = heeDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<ProductViewVO> list = hDAO.selectProductView();
			List<ProductViewVO> list = aDAO.selectProductView();

			pvVO = null;

			Object cateFlag = new Object();

			for (int i = 0; i < list.size(); i++) {
				pvVO = list.get(i);
				rowData = new Object[6];
				cateFlag = pvVO.getProductCategory();
				if (cateFlag.equals("S")) {
					rowData[0] = pvVO.getProductCode();
					rowData[1] = pvVO.getProductName();
					rowData[2] = new ImageIcon("C:/dev/pcbang/admin/img/item/"+"rs_"+pvVO.getProductImg());
					rowData[3] = pvVO.getProductExplain();
					rowData[4] = pvVO.getProductPrice();
					rowData[5] = pvVO.getProductInputDate();

					dtmS.addRow(rowData);
				}
			} // end for

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}// setSnackList

	public void setDrinkList() {
		DefaultTableModel dtmD = pv.getDtmDrink();

		dtmD.setRowCount(0);
		Object[] rowData = null;

//		heeDAO hDAO = heeDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			List<ProductViewVO> list = hDAO.selectProductView();
			List<ProductViewVO> list = aDAO.selectProductView();

			pvVO = null;

			Object cateFlag = new Object();

			for (int i = 0; i < list.size(); i++) {
				pvVO = list.get(i);
				rowData = new Object[6];
				cateFlag = pvVO.getProductCategory();
				if (cateFlag.equals("D")) {
					rowData[0] = pvVO.getProductCode();
					rowData[1] = pvVO.getProductName();
					rowData[2] = new ImageIcon("C:/dev/pcbang/admin/img/item/"+"rs_"+pvVO.getProductImg());
					rowData[3] = pvVO.getProductExplain();
					rowData[4] = pvVO.getProductPrice();
					rowData[5] = pvVO.getProductInputDate();

					dtmD.addRow(rowData);
				}
			} // end for

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}// setDrinkList

	private void productMDView(JTable temp) {
		ProductMDViewVO pmdvVO = new ProductMDViewVO();

		String explain = (String) temp.getValueAt(temp.getSelectedRow(), 3);
		String name = (String) temp.getValueAt(temp.getSelectedRow(), 1);
		String img = temp.getValueAt(temp.getSelectedRow(), 2).toString();
		Integer price = (Integer) temp.getValueAt(temp.getSelectedRow(), 4);

		pmdvVO.setExplain(explain);
		pmdvVO.setName(name);
		pmdvVO.setImg(img);
		pmdvVO.setPrice(price);

//		heeDAO hDAO = heeDAO.getInstance();
		AdminDAO aDAO = AdminDAO.getInstance();

		try {
//			hDAO.selectDetailProduct(pmdvVO);
			aDAO.selectDetailProduct(pmdvVO);
			new ModifyDeleteView(pmdvVO, this, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// productMDView

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == pv.getJbtProductAdd()) {
			new ProductAddView(this, mv);
		} // end if

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {

		if (me.getClickCount() == 2) {

			JTable temp = pv.getJtSnack();
			if (me.getSource() == temp) {
				productMDView(temp);
			}
		} // end if
		if (me.getClickCount() == 2) {

			JTable temp = pv.getJtDrink();
			if (me.getSource() == temp) {
				productMDView(temp);
			}
		} // end if
		if (me.getClickCount() == 2) {

			JTable temp = pv.getJtFood();
			if (me.getSource() == temp) {
				productMDView(temp);
			}
		} // end if

//		if (me.getSource() == pv.getJtp()) {
//			if (pv.getJtp().getSelectedIndex() == 0) {
//				setFoodList();
//			} // end if
//			if (pv.getJtp().getSelectedIndex() == 1) {
//				setSnackList();
//			} // end if
//			if (pv.getJtp().getSelectedIndex() == 2) {
//				setDrinkList();
//			} // end if
//		} // end if

	}// mouseClicked

}// class
