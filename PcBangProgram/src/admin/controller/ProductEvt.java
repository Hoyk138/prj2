package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import admin.DAO.heeDAO;
import admin.VO.ProductViewVO;
import admin.view.ProductAddView;
import admin.view.ProductView;

public class ProductEvt extends MouseAdapter implements ActionListener{
	
	private ProductView pv;
	
	public ProductEvt(ProductView pv) {
		this.pv=pv;
		setFoodList();
	}//기본생성자
	
	public void setFoodList() {
		DefaultTableModel dtmF=pv.getDtmFood();
		
		dtmF.setRowCount(0);
		Object[] rowData=null;
		
		heeDAO hDAO=heeDAO.getInstance();
		
		try {
			List<ProductViewVO> list=hDAO.productView();
			
			ProductViewVO pvVO=null;
			
			Object cateFlag=new Object();
			
			for(int i=0;i<list.size();i++) {
				pvVO=list.get(i);
				rowData=new Object[6];
				cateFlag=pvVO.getProductCategory();
				if(cateFlag.equals("F")) {
				rowData[0]=pvVO.getProductCode();
				rowData[1]=pvVO.getProductName();
				rowData[2]=new ImageIcon(pvVO.getProductImg());
				rowData[3]=pvVO.getProductExplain();
				rowData[4]=pvVO.getProductPrice();
				rowData[5]=pvVO.getProductInputDate();
				
				dtmF.addRow(rowData);
				}
			}//end for
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}//setFoodList
	
	public void setSnackList() {
		DefaultTableModel dtmS=pv.getDtmSnack();
		
		dtmS.setRowCount(0);
		Object[] rowData=null;
		
		heeDAO hDAO=heeDAO.getInstance();
		
		try {
			List<ProductViewVO> list=hDAO.productView();
			
			ProductViewVO pvVO=null;
			
			Object cateFlag=new Object();
			
			for(int i=0;i<list.size();i++) {
				pvVO=list.get(i);
				rowData=new Object[6];
				cateFlag=pvVO.getProductCategory();
				if(cateFlag.equals("S")) {
				rowData[0]=pvVO.getProductCode();
				rowData[1]=pvVO.getProductName();
				rowData[2]=new ImageIcon(pvVO.getProductImg());
				rowData[3]=pvVO.getProductExplain();
				rowData[4]=pvVO.getProductPrice();
				rowData[5]=pvVO.getProductInputDate();
				
				dtmS.addRow(rowData);
				}
			}//end for
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}//setSnackList
	
	public void setDrinkList() {
		DefaultTableModel dtmD=pv.getDtmDrink();
		
		dtmD.setRowCount(0);
		Object[] rowData=null;
		
		heeDAO hDAO=heeDAO.getInstance();
		
		try {
			List<ProductViewVO> list=hDAO.productView();
			
			ProductViewVO pvVO=null;
			
			Object cateFlag=new Object();
			
			for(int i=0;i<list.size();i++) {
				pvVO=list.get(i);
				rowData=new Object[6];
				cateFlag=pvVO.getProductCategory();
				if(cateFlag.equals("D")) {
				rowData[0]=pvVO.getProductCode();
				rowData[1]=pvVO.getProductName();
				rowData[2]=new ImageIcon(pvVO.getProductImg());
				rowData[3]=pvVO.getProductExplain();
				rowData[4]=pvVO.getProductPrice();
				rowData[5]=pvVO.getProductInputDate();
				
				dtmD.addRow(rowData);
				}
			}//end for
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}//setDrinkList
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==pv.getJbtProductAdd()) {
			new ProductAddView();
		}//end if
	
	}//actionPerformed


	@Override
	public void mouseClicked(MouseEvent me) {
		
		if(me.getSource()==pv.getJtFood()) {
		}//end if
		if(me.getSource()==pv.getJtSnack()) {
		}//end if
		if(me.getSource()==pv.getJtDrink()) {
		}//end if

		if(me.getSource()==pv.getJtp()) {
			if(pv.getJtp().getSelectedIndex()==0) {
				setFoodList();
			}//end if
			if(pv.getJtp().getSelectedIndex()==1) {
				setSnackList();
			}//end if
			if(pv.getJtp().getSelectedIndex()==2) {
				setDrinkList();
			}//end if
			
		}//end if

	}//mouseClicked

}//class

