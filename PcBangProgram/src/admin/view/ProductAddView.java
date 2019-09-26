package admin.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import admin.controller.ProductAddViewEvt;
import admin.controller.ProductEvt;

@SuppressWarnings("serial")
public class ProductAddView extends JDialog{
	
	private JButton jbtProductAdd,jbtProductImageAdd;
	private JLabel jlImgAdd;
	private JTextField jtfProductNameAdd,jtfPriceAdd;
	private DefaultComboBoxModel<String> dcbmCategoryAdd;
	private JTextArea jtaExplainAdd;
	
	public ProductAddView(ProductEvt pe) {
//		super("�޴� �߰�");
		
		ImageIcon bgimg=new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/admin/image/b1g1.png");
		JLabel jlBGimg=new JLabel(bgimg);
		jtaExplainAdd=new JTextArea();
		JLabel jlProductName=new JLabel("��ǰ��");
		JLabel jlProductPrice=new JLabel("��ǰ����");
		JLabel jlExplain=new JLabel("��ǰ����");
		
		String[] category= {"�Ļ�","����","����"};
		dcbmCategoryAdd =new DefaultComboBoxModel<String>(category);
		JComboBox<String> jcbCategory=new JComboBox<String>(dcbmCategoryAdd);
		
		jbtProductAdd=new JButton("�߰�");
		jbtProductImageAdd=new JButton("�̹��� ����");
		jlImgAdd=new JLabel(new ImageIcon("C:/Users/owner/git/prj2/PcBangProgram/src/admin/image/rs_����.jpg"));
		jtfProductNameAdd=new JTextField();
		jtfPriceAdd=new JTextField();
		
		jtaExplainAdd.setLineWrap(true);
		setLayout(null);

		jlBGimg.setBounds(0,0,770,500);
		jlImgAdd.setBounds(50,50,280,200);
		jbtProductImageAdd.setBounds(130,280,130,30);
		jcbCategory.setBounds(460,40,200,30);
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
		add(jlBGimg);
		
		setBounds(100,100,770,500);
		
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}//�⺻������
	
	

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