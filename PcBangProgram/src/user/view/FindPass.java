package user.view;

import javax.swing.DefaultComboBoxModel;
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
	
	public FindPass() {
//		super("PW 찾기");
		
		dcbmQuestion=new DefaultComboBoxModel<String>(new String[] 
				{"자신의 보물 제1호는?","자신의 인생 좌우명은?","유년시절 가장 생각나는 친구 이름은?",
						"다시 태어나면 되고 싶은 것은?","내가 좋아하는 캐릭터는?","인상 깊게 읽은 책 이름은?","출신 초등학교는 어디인가요?"});
		jcbQuestion=new JComboBox<String>(dcbmQuestion);
		
		jlbId=new JLabel("ID");
		jlbQuestion=new JLabel("본인확인질문");
		jlbAnswer=new JLabel("답변");
		
		jtfId=new JTextField();
		jtfAnswer=new JTextField();
		
		jbtnFindPass=new JButton("PW찾기");
		
		setLayout(null);
		
		jlbId.setBounds(50, 40, 76, 30);
		jlbQuestion.setBounds(50, 90, 100, 30);
		jlbAnswer.setBounds(50, 150, 76, 30);
		
		jtfId.setBounds(160, 40, 230, 25);
		jcbQuestion.setBounds(160, 90, 230, 25);
		jtfAnswer.setBounds(160, 150, 230, 25);
		
		jbtnFindPass.setBounds(290, 200, 100, 40);
		
		add(jlbId);
		add(jlbQuestion);
		add(jlbAnswer);
		add(jtfId);
		add(jcbQuestion);
		add(jtfAnswer);
		add(jbtnFindPass);
		
		FindPassEvt fpe=new FindPassEvt(this);
		
		jtfId.addActionListener(fpe);
		jtfAnswer.addActionListener(fpe);
		jbtnFindPass.addActionListener(fpe);
		
		setResizable(false);
		setBounds(700, 370, 450, 300);
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
	

}//class
