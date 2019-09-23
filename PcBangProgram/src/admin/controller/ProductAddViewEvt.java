package admin.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import admin.DAO.heeDAO;
import admin.VO.ProductAddVO;
import admin.view.ProductAddView;
import kr.co.sist.util.img.ImageResize;

public class ProductAddViewEvt implements ActionListener{
	private ProductAddView pav;
	private ProductEvt pe;
//	private boolean imgFlag=false;
	private String path,file;
	
	public ProductAddViewEvt(ProductAddView pav,ProductEvt pe) {
		this.pav=pav;
		this.pe=pe;
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
			File writeFile=new File(path+file);
			ImageResize.resizeImage(writeFile.getAbsolutePath(), 303, 321);
			pav.getJlImgAdd().setIcon(new ImageIcon(path+"rs_"+file));
			
			try {
				uploadImg();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			imgFlag=true;
		}else{
//			imgFlag=false;
		}//end if
		
		
	}//imgAdd
	
private void uploadImg() throws IOException{
		
		//������ �̹����� ���ö��� �̹��� ������ ����.
		File readFile=new File(path+file);
		
		byte[] readData=new byte[512];
		int len=0;
		
		FileOutputStream fos=null;
		FileInputStream fis=null;
		
		try {
		fis=new FileInputStream(readFile); //������ �о� �鿩
		
		if(readFile.exists()) {
			
			File writeFile=new File("C:/Users/owner/git/prj2/PcBangProgram/src/image/"+readFile.getName());
			fos=new FileOutputStream(writeFile); //������ �̹��� ������ ����
			
			while((len=fis.read(readData))!=-1) {
				fos.write(readData,0,len);  //�о���� ��ŭ ��� ��Ʈ���� ���
			}//end while
			fos.flush();
			//�̹����� thumbnail image�� ����
			ImageResize.resizeImage(writeFile.getAbsolutePath(), 303, 321);
			
		}//end if
		
		}finally {
			if(fos!=null) {fos.close();}
			if(fis!=null) {fis.close();}
		}//end finally
		
	}//uploadImg

	public void productAdd() {
		String imgPath=path+file;
		String category = pav.getDcbmCategoryAdd().getSelectedItem().toString(); 
		String name =pav.getJtfProductNameAdd().getText().trim();
		String explain = pav.getJtaExplainAdd().getText().trim();
		int price = Integer.parseInt(pav.getJtfPriceAdd().getText().trim());
		
		ProductAddVO paVO=new ProductAddVO(imgPath, category, name, explain, price);
		
		heeDAO hDAO=heeDAO.getInstance();
		try {
			hDAO.InsertProduct(paVO);
			JOptionPane.showMessageDialog(pav,"��ǰ�� �߰��Ǿ����ϴ�.");
			pe.setDrinkList();
			pe.setFoodList();
			pe.setSnackList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
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
