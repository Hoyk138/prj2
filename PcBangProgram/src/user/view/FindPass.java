package user.view;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import user.controller.FindPassEvt;

@SuppressWarnings("serial")
public class FindPass extends JDialog {
	
	private JComboBox<String> jcbQuestion;
	private DefaultComboBoxModel<String> dcbmQuestion ;
	private JLabel jlbId, jlbQuestion, jlbAnswer;
	private JTextField jtfId, jtfAnswer ;
	private JButton jbtnFindPass;
	
	public FindPass(UserLogin ul) {
		super(ul,"PW 찾기",true);
		
		ImageIcon findPassBackground=new ImageIcon("C:/dev/workspace/Team2_prj2/src/user/Design/find_pw_backimg.png");
		ImageIcon findPassButton1=new ImageIcon("C/dev/workspace/Team2_prj2/src/user/Design/find_id_button1.png");
		ImageIcon findPassButton2=new ImageIcon("C/dev/workspace/Team2_prj2/src/user/Design/find_id_button2.png");
		
		JLabel fpBack=new JLabel(findPassBackground);
		
		dcbmQuestion=new DefaultComboBoxModel<String>(new String[] 
				{"자신의 보물 제1호는?","자신의 인생 좌우명은?","유년시절 가장 생각나는 친구 이름은?",
						"다시 태어나면 되고 싶은 것은?","내가 좋아하는 캐릭터는?","인상 깊게 읽은 책 이름은?","출신 초등학교는 어디인가요?"});
		jcbQuestion=new JComboBox<String>(dcbmQuestion);
		
		jlbId=new JLabel("ID");
		jlbQuestion=new JLabel("본인확인질문");
		jlbAnswer=new JLabel("답변");
		
		jtfId=new JTextField();
		jtfAnswer=new JTextField();
		
		jbtnFindPass=new JButton(findPassButton1);
		
		jbtnFindPass.setRolloverIcon(findPassButton2);
		
		setLayout(null);
		
		fpBack.setBounds(0, 0, 567, 378);
		
		jlbId.setBounds(50, 40, 76, 30);
		jlbQuestion.setBounds(50, 90, 100, 30);
		jlbAnswer.setBounds(50, 150, 76, 30);
		
		jtfId.setBounds(178, 98, 320, 30);
		jcbQuestion.setBounds(172, 147, 330, 35);
		jtfAnswer.setBounds(178, 203, 320, 30);
		
		jbtnFindPass.setBounds(190, 280, 168, 35);
		
		jtfId.setCaretColor(new Color(0xC2C2C2)); //커서컬러바꾸기
		jtfAnswer.setCaretColor(new Color(0xC2C2C2));
		
		jtfId.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //jtf 테두리 없애기
		jtfAnswer.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		jbtnFindPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());//버튼테두리 없애기
		
		
		add(jtfId);
		add(jcbQuestion);
		add(jtfAnswer);
		add(jbtnFindPass);
		add(fpBack);
		add(jlbId);
		add(jlbQuestion);
		add(jlbAnswer);
		add(jbtnFindPass);
		
		FindPassEvt fpe=new FindPassEvt(this);
		
		jtfId.addActionListener(fpe);
		jtfAnswer.addActionListener(fpe);
		jbtnFindPass.addActionListener(fpe);
		
		setResizable(false);
		setBounds(700, 370, 567, 378);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//FindPass

	public JComboBox<String> getJcbQuestion() {
		return jcbQuestion;
	}

	public DefaultComboBoxModel<String> getDcbmQuestion() {
		return dcbmQuestion;
	}

	public JLabel getJlbId() {
		return jlbId;
	}

	public JLabel getJlbQuestion() {
		return jlbQuestion;
	}

	public JLabel getJlbAnswer() {
		return jlbAnswer;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfAnswer() {
		return jtfAnswer;
	}

	public JButton getJbtnFindPass() {
		return jbtnFindPass;
	}
	
//	public static void main(String[] args) {
//		new FindPass(null);
//	}//main
	

}//class
