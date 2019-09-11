package admin.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import admin.DAO.heeDAO;
import admin.VO.ProductAddVO;
import admin.view.ProductAddView;

public class ProductAddViewEvt implements ActionListener{
	private ProductAddView pav;
	private boolean imgFlag=false;
	String path,file;
	
	public ProductAddViewEvt(ProductAddView pav) {
		this.pav=pav;
	}

	public void imgAdd(){
		FileDialog fdOpen=new FileDialog(pav,"���ö��̹��ڼ���!",FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		path=fdOpen.getDirectory();
		file=fdOpen.getFile();
		
		if(file!=null) {  //������ ������ ����
			//Ȯ���� Ȯ��
			String flagExt="jpg,gif,png,bmp";
			String ext=file.substring(file.lastIndexOf(".")+1);
			
			if(!flagExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(pav,file+
						"�� �̹����� �ƴϰų� ����� �� ���� �̹��� �Դϴ�.");
			return;
			}//end if
			
			//�̹����� �̸����� �󺣿��� ����
			pav.getJlImgAdd().setIcon(new ImageIcon(path+file));
			imgFlag=true;
		}else{
			imgFlag=false;
		}//end if
		
		
	}//imgAdd

	public void productAdd() {
		String imgPath=path+file;
		
		String category = pav.getDcbmCategoryAdd().getSelectedItem().toString(); 
		String name =pav.getJtfProductNameAdd().getText().trim();
		String explain = pav.getJtaExplainAdd().getText().trim();
		int price = Integer.parseInt(pav.getJtfPriceAdd().getText().trim());
		
		ProductAddVO paVO=new ProductAddVO(imgPath, category, name, explain, price);
		
		heeDAO hDAO=heeDAO.getInstance();
		
		try {
			hDAO.AddProduct(paVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(pav,"��ǰ�� �߰��Ǿ����ϴ�.");
		
	}//productAdd
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==pav.getJbtProductImageAdd()) {
			imgAdd();
		}//end if
		if(ae.getSource()==pav.getJbtProductAdd()) {
			productAdd();
		}
		
	}//actionPerformed
	
	
	
}
