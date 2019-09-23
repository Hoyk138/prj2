package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.DAO.heeDAO;
import admin.VO.ProductMDViewVO;
import admin.VO.ProductViewVO;
import admin.view.ModifyDeleteView;
import admin.view.ProductAddView;
import admin.view.ProductView;
import kr.co.sist.util.img.ImageResize;

public class ProductEvt extends MouseAdapter implements ActionListener {

	private ProductView pv;
	private ProductViewVO pvVO;

	public ProductEvt(ProductView pv) {
		this.pv = pv;
		setFoodList();
		setSnackList();
		setDrinkList();
	}// 기본생성자

	public void setFoodList() {
		DefaultTableModel dtmF = pv.getDtmFood();

		dtmF.setRowCount(0);
		Object[] rowData = null;

		heeDAO hDAO = heeDAO.getInstance();

		try {
			List<ProductViewVO> list = hDAO.selectProductView();

			pvVO = null;

			Object cateFlag = new Object();

			for (int i = 0; i < list.size(); i++) {
				pvVO = list.get(i);
				rowData = new Object[6];
				cateFlag = pvVO.getProductCategory();
				if (cateFlag.equals("F")) {
					rowData[0] = pvVO.getProductCode();
					rowData[1] = pvVO.getProductName();
					
					File writeFile=new File(pvVO.getProductImg());
					ImageResize.resizeImage(writeFile.getAbsolutePath(), 200, 150);
					String img=
					writeFile.toString().substring(0,writeFile.toString().lastIndexOf("\\")+1)+
					"rs_"+
					writeFile.toString().substring(writeFile.toString().lastIndexOf("\\")+1);
					
					try {
						uploadImg();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					rowData[2] = new ImageIcon(img);
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

		heeDAO hDAO = heeDAO.getInstance();

		try {
			List<ProductViewVO> list = hDAO.selectProductView();

			pvVO = null;

			Object cateFlag = new Object();

			for (int i = 0; i < list.size(); i++) {
				pvVO = list.get(i);
				rowData = new Object[6];
				cateFlag = pvVO.getProductCategory();
				if (cateFlag.equals("S")) {
					rowData[0] = pvVO.getProductCode();
					rowData[1] = pvVO.getProductName();
					
					File writeFile=new File(pvVO.getProductImg());
					ImageResize.resizeImage(writeFile.getAbsolutePath(), 200, 150);
					String img=
					writeFile.toString().substring(0,writeFile.toString().lastIndexOf("\\")+1)+
					"rs_"+
					writeFile.toString().substring(writeFile.toString().lastIndexOf("\\")+1);
					
					try {
						uploadImg();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					rowData[2] = new ImageIcon(img);
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

		heeDAO hDAO = heeDAO.getInstance();

		try {
			List<ProductViewVO> list = hDAO.selectProductView();

			pvVO = null;

			Object cateFlag = new Object();

			for (int i = 0; i < list.size(); i++) {
				pvVO = list.get(i);
				rowData = new Object[6];
				cateFlag = pvVO.getProductCategory();
				if (cateFlag.equals("D")) {
					rowData[0] = pvVO.getProductCode();
					rowData[1] = pvVO.getProductName();
					
					File writeFile=new File(pvVO.getProductImg());
					ImageResize.resizeImage(writeFile.getAbsolutePath(), 200, 150);
					String img=
					writeFile.toString().substring(0,writeFile.toString().lastIndexOf("\\")+1)+
					"rs_"+
					writeFile.toString().substring(writeFile.toString().lastIndexOf("\\")+1);
					
					try {
						uploadImg();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					rowData[2] = new ImageIcon(img);
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
	
private void uploadImg() throws IOException{
		
		//선택한 이미지를 도시락의 이미지 폴더에 복사.
		File readFile=new File(pvVO.getProductImg());
		
		byte[] readData=new byte[512];
		int len=0;
		
		FileOutputStream fos=null;
		FileInputStream fis=null;
		
		try {
		fis=new FileInputStream(readFile); //파일을 읽어 들여
		
		if(readFile.exists()) {
			
			
			File writeFile=new File("C:/Users/owner/git/prj2/PcBangProgram/src/image/"+readFile.getName());
			fos=new FileOutputStream(writeFile); //관리자 이미지 폴더에 복사
			
			while((len=fis.read(readData))!=-1) {
				fos.write(readData,0,len);  //읽어들인 만큼 출력 스트림에 기록
			}//end while
			fos.flush();
			//이미지를 thumbnail image로 생성
			ImageResize.resizeImage(writeFile.getAbsolutePath(), 200, 150);
			
		}//end if
		
		}finally {
			if(fos!=null) {fos.close();}
			if(fis!=null) {fis.close();}
		}//end finally
		
	}//uploadImg
	

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

		heeDAO hDAO = heeDAO.getInstance();

		try {
			hDAO.selectDetailProduct(pmdvVO);
			new ModifyDeleteView(pmdvVO,this);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// productMDView

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == pv.getJbtProductAdd()) {
			new ProductAddView(this);
		} // end if

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {

		if (me.getClickCount() == 2) {

			JTable temp = pv.getJtSnack();
			if (me.getSource() == temp) {
				productMDView(temp);
			}
			}//end if
		if (me.getClickCount() == 2) {

			JTable temp = pv.getJtDrink();
			if (me.getSource() == temp) {
				productMDView(temp);
			}
		}//end if
		if (me.getClickCount() == 2) {

			JTable temp = pv.getJtFood();
			if (me.getSource() == temp) {
				productMDView(temp);
			}
		}//end if
		
		
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
