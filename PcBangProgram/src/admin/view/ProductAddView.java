package admin.view;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import admin.controller.ProductAddViewEvt;
import admin.controller.ProductEvt;

@SuppressWarnings("serial")
public class ProductAddView extends JDialog{
	
	private JButton jbtProductAdd,jbtProductImageAdd;
	private JLabel jlImgAdd;
	private JTextField jtfProductNameAdd,jtfPriceAdd;
	private DefaultComboBoxModel<String> dcbmCategoryAdd;
	private JTextArea jtaExplainAdd;
	
	public ProductAddView(ProductEvt pe, MainView mv) {
		super(mv, "상품 추가");
		
		jtaExplainAdd=new JTextArea();
		JLabel jlProductName=new JLabel("상품명");
		JLabel jlProductPrice=new JLabel("상품가격");
		JLabel jlExplain=new JLabel("상품설명");
		
		String[] category= {"식사","스낵","음료"};
		dcbmCategoryAdd =new DefaultComboBoxModel<String>(category);
		JComboBox<String> jcbCategory=new JComboBox<String>(dcbmCategoryAdd);
		
		jbtProductAdd=new JButton("추가");
		jbtProductImageAdd=new JButton("이미지 선택");
		jlImgAdd=new JLabel(new ImageIcon("C:/dev/pcbang/admin/img/item/rs_선택.jpg"));
		jtfProductNameAdd=new JTextField();
		jtfPriceAdd=new JTextField();
		
		jtaExplainAdd.setLineWrap(true);
		setLayout(null);

		jlImgAdd.setBounds(50,50,280,200);
		jbtProductImageAdd.setBounds(130,280,130,30);
		jcbCategory.setBounds(400,40,100,30);
		jlProductName.setBounds(400,100,70,30);
		jtfProductNameAdd.setBounds(480,100,200,30);
		jlProductPrice.setBounds(400,140,70,30);
		jtfPriceAdd.setBounds(480,140,200,30);
		jlExplain.setBounds(400,190,70,30);
		jtaExplainAdd.setBounds(400,225,300,100);
		jbtProductAdd.setBounds(260,355,200,30);

		ProductAddViewEvt pave=new ProductAddViewEvt(this,pe);
		jbtProductImageAdd.addActionListener(pave);
		jbtProductAdd.addActionListener(pave);
		
		
		add(jlImgAdd);
		add(jbtProductImageAdd);
		add(jcbCategory);
		add(jlProductName);
		add(jtfProductNameAdd);
		add(jlProductPrice);
		add(jtfPriceAdd);
		add(jlExplain);
		add(jtaExplainAdd);
		add(jbtProductAdd);
		
		/////////////////////////////////////////
		jlImgAdd.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jtaExplainAdd.setBorder(new TitledBorder(new  LineBorder(Color.black)));
		jcbCategory.setBackground(Color.white);
		jlImgAdd.setBackground(Color.white);
		jbtProductAdd.setBackground(new Color(0xF5D08A));
		jbtProductImageAdd.setBackground(new Color(0xF5D08A));
		getContentPane().setBackground(new Color(0xE8F2FE));
		/////////////////////////////////////////
		
		setBounds(100,100,770,500);
		
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}//기본생성자
	
	

	public JButton getJbtProductAdd() {
		return jbtProductAdd;
	}



	public JButton getJbtProductImageAdd() {
		return jbtProductImageAdd;
	}



	public JLabel getJlImgAdd() {
		return jlImgAdd;
	}



	public JTextField getJtfProductNameAdd() {
		return jtfProductNameAdd;
	}



	public JTextField getJtfPriceAdd() {
		return jtfPriceAdd;
	}



	public DefaultComboBoxModel<String> getDcbmCategoryAdd() {
		return dcbmCategoryAdd;
	}



	public JTextArea getJtaExplainAdd() {
		return jtaExplainAdd;
	}

}//class