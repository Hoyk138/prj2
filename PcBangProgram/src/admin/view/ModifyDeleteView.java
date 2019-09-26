package admin.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
//		super("�޴� ���� ����");
//		super(ProductView pv,"");
		itemCode=pmdvVO.getItemCode();
		jtaExplainModify=new JTextArea(pmdvVO.getExplain());
		JLabel jlProductName=new JLabel("��ǰ��");
		JLabel jlProductPrice=new JLabel("��ǰ����");
		JLabel jlExplain=new JLabel("��ǰ����");		
		JLabel jlCategory=null;
		if(pmdvVO.getCategory().equals("S")){
			jlCategory=new JLabel("�����Դϴ�.");
		}else if(pmdvVO.getCategory().equals("D")) {
			jlCategory=new JLabel("�����Դϴ�.");
		}else {
			jlCategory=new JLabel("�Ļ��Դϴ�.");
		}
		
		jbtProductModify=new JButton("����");
		jbtProductDelete=new JButton("����/����");
		jbtProductImageModify=new JButton("�̹��� ����");
		jbtRealDelete=new JButton("����(����)");
		jlImgModify=new JLabel(new ImageIcon(pmdvVO.getImg()));
		jtfProductNameModify=new JTextField(pmdvVO.getName());
		jtfPriceModify=new JTextField(String.valueOf(pmdvVO.getPrice()));
		
		//jlState=new JLabel("�Ǹ����� ��ǰ�Դϴ�.");
		if(pmdvVO.getState().equals("Y")) {
			jlState=new JLabel("�Ǹ�����");
		}else {
			jlState=new JLabel("������");
		}
		
		
		jtaExplainModify.setLineWrap(true);
		setLayout(null);
		
		jlImgModify.setBounds(50,50,280,200);
		jbtProductImageModify.setBounds(130,280,130,30);
		jlState.setBounds(400,40,60,30);
		jlCategory.setBounds(460,40,100,30);
		jlProductName.setBounds(400,100,70,30);
		jtfProductNameModify.setBounds(480,100,200,30);
		jlProductPrice.setBounds(400,140,70,30);
		jtfPriceModify.setBounds(480,140,200,30);
		jlExplain.setBounds(400,190,70,30);
		jtaExplainModify.setBounds(400,225,300,100);
		jbtProductModify.setBounds(100,355,100,30);
		jbtProductDelete.setBounds(210,355,100,30);
		jbtRealDelete.setBounds(465,355,180,30);
		
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
		
		/////////////////////////////////////////
		jlImgModify.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jtaExplainModify.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jlImgModify.setBackground(Color.white);
		jbtProductImageModify.setBackground(new Color(0xF5D08A));
		jbtRealDelete.setBackground(new Color(0xF5D08A));
		jbtProductModify.setBackground(new Color(0xF5D08A));
		jbtProductDelete.setBackground(new Color(0xF5D08A));
		getContentPane().setBackground(new Color(0xE8F2FE));
		/////////////////////////////////////////
		
		setBounds(100,100,770,500);
		
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}//�⺻������
	
	

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