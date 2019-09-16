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

import admin.controller.ModifyDeleteEvt;

@SuppressWarnings("serial")
public class ModifyDeleteView extends JDialog{
	
	private JButton jbtProductModify,jbtProductImageModify,jbtProductDelete;
	private JLabel jlImgModify,jlState;
	private JTextField jtfProductNameModify,jtfPriceModify;
	private DefaultComboBoxModel<String> dcbmCategoryModify;
	private JTextArea jtaExplainModify;
	public ModifyDeleteView() {
//		super("메뉴 수정 삭제");
		
		jtaExplainModify=new JTextArea();
		JLabel jlProductName=new JLabel("상품명");
		JLabel jlProductPrice=new JLabel("상품가격");
		JLabel jlExplain=new JLabel("상품설명");
		
		String[] category= {"식사","스낵","음료"};
		dcbmCategoryModify =new DefaultComboBoxModel<String>(category);
		JComboBox<String> jcbCategory=new JComboBox<String>(dcbmCategoryModify);
		
		jbtProductModify=new JButton("수정");
		jbtProductDelete=new JButton("삭제");
		jbtProductImageModify=new JButton("이미지 선택");
		jlImgModify=new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/슈렉.jpg"));
		jtfProductNameModify=new JTextField();
		jtfPriceModify=new JTextField();
		jlState=new JLabel("판매중인 상품입니다.");
		
		jtaExplainModify.setLineWrap(true);
		setLayout(null);
		
		jlImgModify.setBounds(30,30,350,350);
		jbtProductImageModify.setBounds(120,400,150,30);
		jcbCategory.setBounds(400,45,270,30);
		jlProductName.setBounds(400,100,70,30);
		jtfProductNameModify.setBounds(472,100,200,30);
		jlProductPrice.setBounds(400,150,70,30);
		jtfPriceModify.setBounds(472,150,200,30);
		jlExplain.setBounds(400,190,70,30);
		jtaExplainModify.setBounds(400,225,370,300);
		jbtProductModify.setBounds(480,540,100,30);
		jbtProductDelete.setBounds(585,540,100,30);
		jlState.setBounds(125,450,150,100);
		
		add(jlImgModify);
		add(jbtProductImageModify);
		add(jcbCategory);
		add(jlProductName);
		add(jtfProductNameModify);
		add(jlProductPrice);
		add(jtfPriceModify);
		add(jlExplain);
		add(jtaExplainModify);
		add(jbtProductModify);
		add(jbtProductDelete);
		add(jlState);
		
		ModifyDeleteEvt mde=new ModifyDeleteEvt();
		jbtProductImageModify.addActionListener(mde);
		jbtProductModify.addActionListener(mde);
		jbtProductDelete.addActionListener(mde);
		
		setBounds(100,100,800,700);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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



	public DefaultComboBoxModel<String> getDcbmCategoryModify() {
		return dcbmCategoryModify;
	}



	public JTextArea getJtaExplainModify() {
		return jtaExplainModify;
	}



//	public static void main(String[] args) {
//		new ModifyDeleteView();
//	}//main
	
}//class