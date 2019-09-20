package admin.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



import admin.DAO.heeDAO;
import admin.VO.ProductDeleteVO;
import admin.VO.ProductMDViewVO;
import admin.VO.ProductModifyVO;
import admin.VO.ProductRealDeleteVO;
import admin.view.ModifyDeleteView;


public class ModifyDeleteEvt implements ActionListener{
	private ProductMDViewVO pmdvVO;
	private ModifyDeleteView mdv;
	private String path,file;
	private ProductEvt pe;
	public ModifyDeleteEvt(ModifyDeleteView mdv,ProductMDViewVO pmdvVO,ProductEvt pe) {
		this.pmdvVO=pmdvVO;
		this.pe=pe;
		this.mdv=mdv;
	}//�⺻������
	
	public void imgModify() {
		FileDialog fdOpen=new FileDialog(mdv,"���ö� ���� �̹���",FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		path=fdOpen.getDirectory();
		file=fdOpen.getFile();
		
		if(file!=null) {  //������ ������ ����
			//Ȯ���� Ȯ��
			String flagExt="jpg,gif,png,bmp";
			String ext=file.substring(file.lastIndexOf(".")+1);
			
			if(!flagExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(mdv,file+
						"�� �̹����� �ƴϰų� ����� �� ���� �̹��� �Դϴ�.");
			return;
			}//end if
			//�̹����� �̸����� �󺣿��� ����	

		    mdv.getJlImgModify().setIcon(new ImageIcon(path+file));
		}//end if
			
	}//imgModify
	
	public void productModify()  {
		String imgPath=null;
		if(file==null) {
			imgPath=pmdvVO.getImg();
		}else {
			imgPath=path+file;
		}
		String name=mdv.getJtfProductNameModify().getText().trim();
		int price=Integer.parseInt((mdv.getJtfPriceModify().getText().trim()));
		String explain=mdv.getJtaExplainModify().getText().trim();
		String itemCode=mdv.getItemCode();
		
		
		ProductModifyVO pmVO=new ProductModifyVO(imgPath, name, explain, price,itemCode);
		
		
		heeDAO hDAO=heeDAO.getInstance();
		int cnt=0;
		try {
		cnt=hDAO.modifyProduct(pmVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		if(cnt==1) {
			JOptionPane.showMessageDialog(mdv,"��ǰ�� ����Ǿ����ϴ�.");
			pe.setDrinkList();
			pe.setSnackList();
			pe.setFoodList();
		}else {
			JOptionPane.showMessageDialog(mdv,"��ǰ�� ������� �߽��ϴ�.");
		}//end if
	}//productModify
	
	public void productDelete() {
		
		String itemCode=pmdvVO.getItemCode();
		
		ProductDeleteVO pdVO=new ProductDeleteVO(itemCode);
		
		heeDAO hDAO=heeDAO.getInstance();
		try {
			
			if(mdv.getJlState().getText().equals("�Ǹ����� ��ǰ�Դϴ�.")) {
				if(hDAO.DeleteProduct(pdVO)==1) {
					JOptionPane.showMessageDialog(mdv,"�� ��ǰ�� �մԿ��� ������ �ʽ��ϴ�.");
				}//end if
			}else if(mdv.getJlState().getText().equals("������ ��ǰ�Դϴ�.")){
				if(hDAO.revive(pdVO)==1) {
					JOptionPane.showMessageDialog(mdv,"�� ��ǰ�� �մԿ��� �������ϴ�.");
				}//end if
			}else {
				JOptionPane.showMessageDialog(mdv,"����");
			}//end if
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		
	}//productDelete
	
	public void productRealDelete() {
		String itemCode=pmdvVO.getItemCode();
		ProductRealDeleteVO prdVO=new ProductRealDeleteVO(itemCode);
		
		heeDAO hDAO=heeDAO.getInstance();
		
		try {
			if(hDAO.RealDelete(prdVO)==1) {
				JOptionPane.showMessageDialog(mdv, "��ǰ�� �����Ǿ����ϴ�.");
				pe.setDrinkList();
				pe.setSnackList();
				pe.setFoodList();
			}else {
				JOptionPane.showMessageDialog(mdv, "��ǰ�� ���� �����Ͽ����ϴ�.");
			}//end if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		
		
		
	}//productRealDelete
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mdv.getJbtProductImageModify()) {
			imgModify();
		}//end if
		if(ae.getSource()==mdv.getJbtProductModify()) {
			productModify();
		}//end if
		if(ae.getSource()==mdv.getJbtProductDelete()) {
			productDelete();
		}//end if
		if(ae.getSource()==mdv.getJbtRealDelete()) {
			productRealDelete();
		}
	}//actionPerformed
	
}//class











