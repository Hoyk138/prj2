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
			try {
				uploadImg();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			pav.getJlImgAdd().setIcon(new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/admin/image/"+"rs_"+file));
		}//end if
		
	}//imgAdd
	
private void uploadImg() throws IOException{
	
	//������ ���丮 ����
	File mkDirectory=new File("C:/Users/owner/git/prj2/PcBangProgram/src/admin/image");
	
	//1.�б� ��Ʈ�� ����
	FileInputStream fis=null;
	//2.���� ��Ʈ�� ����
	FileOutputStream fos=null;
	
	File originalFile=new File(path+file);
	//������ ���ϸ��� ����	
	File copyFile=new File(mkDirectory.getAbsolutePath()+"/"+file);
	
	try {
		fis=new FileInputStream(originalFile);
		fos=new FileOutputStream(copyFile); //�Էµ� ��ο� ������ ����
		
		//3.�������Ͽ��� 1byte�� �о�鿩
		int temp=0;
		
		byte[] readData=new byte[512]; //HDD���� �ѹ��� �о���̴� sector������
		//���� �� ���̹Ƿ� sector(512byte)�� ������ ũ���� �迭�� ����
		while((temp=fis.read(readData))!=-1) { //ä�����ִ� ������ ����
			//4.�о���� 1byte�� ��� ��Ʈ���� ���
			fos.write(readData,0,temp);  //readData�� ���� 0���� �迭�� ������
		}//end while
		//5.��Ʈ���� �����ִ� ������ �������� ����
		fos.flush();
		
		ImageResize.resizeImage(copyFile.getAbsolutePath(), 280, 200);
	}finally {
		if(fis!=null) {fis.close();}
		if(fos!=null) {fos.close();}
	}
		
	}//uploadImg

	public void productAdd() {
		String imgPath=file;
		String category = pav.getDcbmCategoryAdd().getSelectedItem().toString(); 
		String name =pav.getJtfProductNameAdd().getText().trim();
		String explain = pav.getJtaExplainAdd().getText().trim();
		int price=0;
		try {
			price=Integer.parseInt((pav.getJtfPriceAdd().getText().trim()));
		}catch ( java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(pav,"������ ���ڸ� �־��ּ���");
			return;
		}
		
		ProductAddVO paVO=new ProductAddVO(imgPath, category, name, explain, price);
		
		heeDAO hDAO=heeDAO.getInstance();
		
		try {
			if(hDAO.InsertProduct(paVO)==1) {
			JOptionPane.showMessageDialog(pav,"��ǰ�� �߰��Ǿ����ϴ�.");
			pe.setDrinkList();
			pe.setFoodList();
			pe.setSnackList();
			}else {
				JOptionPane.showMessageDialog(pav,"��ǰ�� �߰������߽��ϴ�.");
			}//end if
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
