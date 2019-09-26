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
		FileDialog fdOpen=new FileDialog(pav,"도시락이미자선택!",FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		path=fdOpen.getDirectory();
		file=fdOpen.getFile();
		
		if(file!=null) {  //변경할 파일을 선택
			//확장자 확인
			String flagExt="jpg,gif,png,bmp";
			String ext=file.substring(file.lastIndexOf(".")+1);
			
			if(!flagExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(pav,file+
						"은 이미지가 아니거나 사용할 수 없는 이미지 입니다.");
			return;
			}//end if
			
			//이미지를 미리보기 라베엘에 설정
			try {
				uploadImg();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			pav.getJlImgAdd().setIcon(new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/admin/image/"+"rs_"+file));
		}//end if
		
	}//imgAdd
	
private void uploadImg() throws IOException{
	
	//복사할 디렉토리 생성
	File mkDirectory=new File("C:/Users/owner/git/prj2/PcBangProgram/src/admin/image");
	
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
			JOptionPane.showMessageDialog(pav,"가격은 숫자만 넣어주세요");
			return;
		}
		
		ProductAddVO paVO=new ProductAddVO(imgPath, category, name, explain, price);
		
		heeDAO hDAO=heeDAO.getInstance();
		
		try {
			if(hDAO.InsertProduct(paVO)==1) {
			JOptionPane.showMessageDialog(pav,"상품이 추가되었습니다.");
			pe.setDrinkList();
			pe.setFoodList();
			pe.setSnackList();
			}else {
				JOptionPane.showMessageDialog(pav,"상품이 추가실패했습니다.");
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
