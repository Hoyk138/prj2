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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.webkit.ContextMenu.ShowContext;

import admin.DAO.heeDAO;
import admin.VO.ProductDeleteVO;
import admin.VO.ProductMDViewVO;
import admin.VO.ProductModifyVO;
import admin.VO.ProductRealDeleteVO;
import admin.view.ModifyDeleteView;
import kr.co.sist.util.img.ImageResize;


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
		FileDialog fdOpen=new FileDialog(mdv,"��ǰ ���� �̹���",FileDialog.LOAD);
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

			try {
				uploadImg();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mdv.getJlImgModify().setIcon(new ImageIcon("C:/dev/pcbang/admin/img/item/"+"rs_"+file));
			
		}//end if
			
	}//imgModify
	
private void uploadImg() throws IOException{
		
		//������ ���丮 ����
		File mkDirectory=new File("C:/dev/pcbang/admin/img/item");
		
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
		}//finally
			
		}//uploadImg
		

	
	
	public void productModify()  {
		String imgPath=null;
		
		if(file==null) {
			imgPath=pmdvVO.getImg().substring(pmdvVO.getImg().lastIndexOf("_")+1);
		}else {
			imgPath=file;
		}
		String name=mdv.getJtfProductNameModify().getText().trim();
		int price=0;
		try {
			price=Integer.parseInt((mdv.getJtfPriceModify().getText().trim()));
		}catch ( java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(mdv,"������ ���ڸ� �־��ּ���");
			return;
		}
		String explain=mdv.getJtaExplainModify().getText().trim();
		String itemCode=mdv.getItemCode();
		
		
		ProductModifyVO pmVO=new ProductModifyVO(imgPath, name, explain, price,itemCode);
		
		
		heeDAO hDAO=heeDAO.getInstance();
		int cnt=0;
		try {
		cnt=hDAO.modifyProduct(pmVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			
			if(mdv.getJlState().getText().equals("�Ǹ�����")) {
				if(hDAO.DeleteProduct(pdVO)==1) {
					JOptionPane.showMessageDialog(mdv,"�� ��ǰ�� �մԿ��� ������ �ʽ��ϴ�.");
					mdv.dispose();
				}//end if
			}else if(mdv.getJlState().getText().equals("������")){
				if(hDAO.revive(pdVO)==1) {
					JOptionPane.showMessageDialog(mdv,"�� ��ǰ�� �մԿ��� �������ϴ�.");
					mdv.dispose();
				}//end if
			}else {
				JOptionPane.showMessageDialog(mdv,"����");
			}//end if
		} catch (SQLException e) {
			e.printStackTrace();
		}//catch
		
	}//productDelete
	
	public void productRealDelete() {
		
		switch (JOptionPane.showConfirmDialog(mdv,"�ѹ� ������ �����ʹ� ���ƿ��� �ʽ��ϴ�.")) {
		case JOptionPane.OK_OPTION:
			
			String itemCode=pmdvVO.getItemCode();
			ProductRealDeleteVO prdVO=new ProductRealDeleteVO(itemCode);
			
			File originalFile=new File(mdv.getJlImgModify().getIcon().toString());
			File reSizeFile=new File(mdv.getJlImgModify().getIcon().toString().substring(0,
					mdv.getJlImgModify().getIcon().toString().lastIndexOf("/")+1)+
					mdv.getJlImgModify().getIcon().toString().substring(
					mdv.getJlImgModify().getIcon().toString().lastIndexOf("/")+1).replace("rs_",""));
			
			originalFile.delete();
			reSizeFile.delete();
			
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
			
			break;
		}//case
		
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











