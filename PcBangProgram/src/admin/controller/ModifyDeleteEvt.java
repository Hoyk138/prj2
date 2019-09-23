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
	}//기본생성자
	
	public void imgModify() {
		FileDialog fdOpen=new FileDialog(mdv,"도시락 수정 이미지",FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		path=fdOpen.getDirectory();
		file=fdOpen.getFile();
		
		if(file!=null) {  //변경할 파일을 선택
			//확장자 확인
			String flagExt="jpg,gif,png,bmp";
			String ext=file.substring(file.lastIndexOf(".")+1);
			
			if(!flagExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(mdv,file+
						"은 이미지가 아니거나 사용할 수 없는 이미지 입니다.");
			return;
			}//end if
			//이미지를 미리보기 라베엘에 설정	

			File writeFile=new File(path+file);
			ImageResize.resizeImage(writeFile.getAbsolutePath(), 303, 321);
			mdv.getJlImgModify().setIcon(new ImageIcon(path+"rs_"+file));
			
			try {
				uploadImg();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//end if
			
	}//imgModify
	
private void uploadImg() throws IOException{
		
		//선택한 이미지를 도시락의 이미지 폴더에 복사.
		File readFile=new File(path+file);
		
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
			ImageResize.resizeImage(writeFile.getAbsolutePath(), 303, 321);
			
		}//end if
		
		}finally {
			if(fos!=null) {fos.close();}
			if(fis!=null) {fis.close();}
		}//end finally
		
	}//uploadImg
	
	
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
			JOptionPane.showMessageDialog(mdv,"상품이 변경되었습니다.");
			pe.setDrinkList();
			pe.setSnackList();
			pe.setFoodList();
		}else {
			JOptionPane.showMessageDialog(mdv,"상품이 변경실패 했습니다.");
		}//end if
	}//productModify
	
	public void productDelete() {
		
		String itemCode=pmdvVO.getItemCode();
		
		ProductDeleteVO pdVO=new ProductDeleteVO(itemCode);
		
		heeDAO hDAO=heeDAO.getInstance();
		try {
			
			if(mdv.getJlState().getText().equals("판매중인 상품입니다.")) {
				if(hDAO.DeleteProduct(pdVO)==1) {
					JOptionPane.showMessageDialog(mdv,"이 상품은 손님에게 보이지 않습니다.");
					mdv.dispose();
				}//end if
			}else if(mdv.getJlState().getText().equals("매진된 상품입니다.")){
				if(hDAO.revive(pdVO)==1) {
					JOptionPane.showMessageDialog(mdv,"이 상품은 손님에게 보여집니다.");
					mdv.dispose();
				}//end if
			}else {
				JOptionPane.showMessageDialog(mdv,"오류");
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
				JOptionPane.showMessageDialog(mdv, "상품이 삭제되었습니다.");
				pe.setDrinkList();
				pe.setSnackList();
				pe.setFoodList();
			}else {
				JOptionPane.showMessageDialog(mdv, "상품이 삭제 실패하였습니다.");
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











