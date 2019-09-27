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
	}//기본생성자
	
	public void imgModify() {
		FileDialog fdOpen=new FileDialog(mdv,"상품 수정 이미지",FileDialog.LOAD);
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

			try {
				uploadImg();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mdv.getJlImgModify().setIcon(new ImageIcon("C:/dev/pcbang/admin/img/item/"+"rs_"+file));
			
		}//end if
			
	}//imgModify
	
private void uploadImg() throws IOException{
		
		//복사할 디렉토리 생성
		File mkDirectory=new File("C:/dev/pcbang/admin/img/item");
		
		//1.읽기 스트림 생성
		FileInputStream fis=null;
		//2.쓰기 스트림 생성
		FileOutputStream fos=null;
		
		File originalFile=new File(path+file);
		//복사할 파일명을 생성	
		File copyFile=new File(mkDirectory.getAbsolutePath()+"/"+file);
		
		try {
			fis=new FileInputStream(originalFile);
			fos=new FileOutputStream(copyFile); //입력된 경로에 파일을 생성
			
			//3.원본파일에서 1byte씩 읽어들여
			int temp=0;
			
			byte[] readData=new byte[512]; //HDD에서 한번에 읽어들이는 sector단위로
			//저장 할 것이므로 sector(512byte)와 동일한 크기의 배열을 생성
			while((temp=fis.read(readData))!=-1) { //채워져있는 갯수가 나옴
				//4.읽어들인 1byte를 출력 스트림에 기록
				fos.write(readData,0,temp);  //readData에 담긴걸 0부터 배열의 수까지
			}//end while
			//5.스트림에 남아있는 내용을 목적지로 분출
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
			JOptionPane.showMessageDialog(mdv,"가격은 숫자만 넣어주세요");
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
			
			if(mdv.getJlState().getText().equals("판매중인")) {
				if(hDAO.DeleteProduct(pdVO)==1) {
					JOptionPane.showMessageDialog(mdv,"이 상품은 손님에게 보이지 않습니다.");
					mdv.dispose();
				}//end if
			}else if(mdv.getJlState().getText().equals("재고없는")){
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
		
		switch (JOptionPane.showConfirmDialog(mdv,"한번 삭제한 데이터는 돌아오지 않습니다.")) {
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











