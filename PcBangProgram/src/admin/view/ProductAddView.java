package admin.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import admin.controller.ProductAddViewEvt;

@SuppressWarnings("serial")
public class ProductAddView extends JDialog{
	
	private JButton jbtProductAdd,jbtProductImageAdd;
	private JLabel jlImgAdd;
	private JTextField jtfProductNameAdd,jtfPriceAdd;
	private DefaultComboBoxModel<String> dcbmCategoryAdd;
	private JTextArea jtaExplainAdd;
	
	public ProductAddView() {
//		super("메뉴 추가");
		
		jtaExplainAdd=new JTextArea();
		JLabel jlProductName=new JLabel("상품명");
		JLabel jlProductPrice=new JLabel("상품가격");
		JLabel jlExplain=new JLabel("상품설명");
		
		String[] category= {"식사","스낵","음료"};
		dcbmCategoryAdd =new DefaultComboBoxModel<String>(category);
		JComboBox<String> jcbCategory=new JComboBox<String>(dcbmCategoryAdd);
		
		jbtProductAdd=new JButton("추가");
		jbtProductImageAdd=new JButton("이미지 선택");
		jlImgAdd=new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/슈렉.jpg"));
		jtfProductNameAdd=new JTextField();
		jtfPriceAdd=new JTextField();
		
		jtaExplainAdd.setLineWrap(true);
		setLayout(null);

		
		jlImgAdd.setBounds(30,30,350,350);
		jbtProductImageAdd.setBounds(120,400,150,30);
		jcbCategory.setBounds(400,45,270,30);
		jlProductName.setBounds(400,100,70,30);
		jtfProductNameAdd.setBounds(472,100,200,30);
		jlProductPrice.setBounds(400,150,70,30);
		jtfPriceAdd.setBounds(472,150,200,30);
		jlExplain.setBounds(400,190,70,30);
		jtaExplainAdd.setBounds(400,225,370,300);
		jbtProductAdd.setBounds(530,540,100,30);

		ProductAddViewEvt pave=new ProductAddViewEvt(this);
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
		
		
		setBounds(100,100,800,700);
		
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