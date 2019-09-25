package admin.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import admin.VO.ProductMDViewVO;
import admin.controller.ModifyDeleteEvt;
import admin.controller.ProductEvt;

@SuppressWarnings("serial")
public class ModifyDeleteView extends JDialog{
	
	private JButton jbtProductModify,jbtProductImageModify,jbtProductDelete,jbtRealDelete;
	private JLabel jlImgModify,jlState;
	private JTextField jtfProductNameModify,jtfPriceModify;
	private JTextArea jtaExplainModify;
	private String itemCode;
	
	
	public ModifyDeleteView(ProductMDViewVO pmdvVO,ProductEvt pe) {
//		super("메뉴 수정 삭제");
//		super(ProductView pv,"");
		itemCode=pmdvVO.getItemCode();
		jtaExplainModify=new JTextArea(pmdvVO.getExplain());
		ImageIcon bgimg=new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/admin/image/b1g1.png");
		JLabel jlBGimg=new JLabel(bgimg);
		JLabel jlProductName=new JLabel("상품명");
		JLabel jlProductPrice=new JLabel("상품가격");
		JLabel jlExplain=new JLabel("상품설명");		
		JLabel jlCategory=null;
		if(pmdvVO.getCategory().equals("S")){
			jlCategory=new JLabel("스낵입니다.");
		}else if(pmdvVO.getCategory().equals("D")) {
			jlCategory=new JLabel("음료입니다.");
		}else {
			jlCategory=new JLabel("식사입니다.");
		}
		
		jbtProductModify=new JButton("수정");
		jbtProductDelete=new JButton("매진/해제");
		jbtProductImageModify=new JButton("이미지 선택");
		jbtRealDelete=new JButton("삭제(주의)");
		jlImgModify=new JLabel(new ImageIcon(pmdvVO.getImg()));
		jtfProductNameModify=new JTextField(pmdvVO.getName());
		jtfPriceModify=new JTextField(String.valueOf(pmdvVO.getPrice()));
		
		//jlState=new JLabel("판매중인 상품입니다.");
		if(pmdvVO.getState().equals("Y")) {
			jlState=new JLabel("판매중인 상품입니다.");
		}else {
			jlState=new JLabel("매진된 상품입니다.");
		}
		
		
		jtaExplainModify.setLineWrap(true);
		setLayout(null);
		
		jlBGimg.setBounds(0,0,800,700);
		jlImgModify.setBounds(30,30,350,350);
		jbtProductImageModify.setBounds(120,400,150,30);
		jlCategory.setBounds(450,45,270,30);
		jlProductName.setBounds(400,100,70,30);
		jtfProductNameModify.setBounds(472,100,200,30);
		jlProductPrice.setBounds(400,150,70,30);
		jtfPriceModify.setBounds(472,150,200,30);
		jlExplain.setBounds(400,190,70,30);
		jtaExplainModify.setBounds(400,225,370,300);
		jbtProductModify.setBounds(480,540,100,30);
		jbtProductDelete.setBounds(585,540,100,30);
		jlState.setBounds(125,450,180,100);
		jbtRealDelete.setBounds(300,600,200,30);
		
		ModifyDeleteEvt mde=new ModifyDeleteEvt(this,pmdvVO,pe);
		jbtProductImageModify.addActionListener(mde);
		jbtProductModify.addActionListener(mde);
		jbtProductDelete.addActionListener(mde);
		jbtRealDelete.addActionListener(mde);
		
		add(jlImgModify);
		add(jbtProductImageModify);
		add(jbtRealDelete);
		add(jlProductName);
		add(jtfProductNameModify);
		add(jlProductPrice);
		add(jtfPriceModify);
		add(jlExplain);
		add(jtaExplainModify);
		add(jbtProductModify);
		add(jbtProductDelete);
		add(jlState);
		add(jlCategory);
		add(jlBGimg);
		
		setBounds(100,100,800,700);
		
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}//기본생성자
	
	

	public JButton getJbtProductModify() {
		return jbtProductModify;
	}



	public JButton getJbtProductImageModify() {
		return jbtProductImageModify;
	}



	public JButton getJbtProductDelete() {
		return jbtProductDelete;
	}



	public JLabel getJlImgModify() {
		return jlImgModify;
	}



	public JTextField getJtfProductNameModify() {
		return jtfProductNameModify;
	}



	public JTextField getJtfPriceModify() {
		return jtfPriceModify;
	}



	public JLabel getJlState() {
		return jlState;
	}



	public JTextArea getJtaExplainModify() {
		return jtaExplainModify;
	}



	public String getItemCode() {
		return itemCode;
	}



	public JButton getJbtRealDelete() {
		return jbtRealDelete;
	}
	
	

//	public static void main(String[] args) {
//		new ModifyDeleteView();
//	}//main
	
}//class