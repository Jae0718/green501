
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Result extends JFrame{
	private final int LOTTO_SIZE = 6;
	
	private int input;
	private StoreLotto stLotto;

	private Integer[] winNum;
	private int bonusNum;
	
	private ShowPanel[] showPnlArr;
	private JTextField[] tfWinNum;
	private JTextField tfBonusNum;
	private JButton btnClose;
	private JButton btnTrace;
	
	public Result(StoreLotto stLotto, int input){
		setInput(input);
		setStLotto(stLotto);
		init();
		makeWinNum();
		addListener();
		setDisplay();
		setFrame();
	}
	private void init(){
		winNum = new Integer[LOTTO_SIZE];
		
		tfWinNum = new JTextField[6];
		tfBonusNum = new JTextField();
		showPnlArr = new ShowPanel[input];
		btnClose = new JButton("Close");
		btnTrace = new JButton("Trace");
	}
	
	private void makeWinNum(){
		winNum = stLotto.makeNumber();
		
		Random r = new Random();
		bonusNum = r.nextInt(45)+1;
		
		for(int i=0; i<LOTTO_SIZE; i++){
			while(winNum[i] == bonusNum){
				bonusNum = r.nextInt(45)+1;
				i=0;
			}
		}
	}
	
	private void addListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent qe) {
				Object src = qe.getSource();
				if(src == btnTrace){
					System.out.println("��ư������");
					traceSecond();
				}else if(src == btnClose){
					dispose();
				}else{
					showMessage("�߸��� �����Դϴ�");
				}
				
			}
		};
		
		btnTrace.addActionListener(listener);
		btnClose.addActionListener(listener);
		
	}
	private void setDisplay(){
		JPanel pnlNorth = new JPanel();
		JLabel lblNorth = new JLabel("Result", JLabel.CENTER);
		lblNorth.setFont(new Font(Font.DIALOG, Font.BOLD, 77));
		pnlNorth.add(lblNorth);

		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		//pnlInfo -> ��÷��ȣ �г�
		JPanel pnlInfo = new JPanel();
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setBorder(new TitledBorder("��÷��ȣ"));
		for(int i=0; i<6; i++){
			tfWinNum[i] = new JTextField();
			tfWinNum[i].setEditable(false);
			tfWinNum[i].setHorizontalAlignment(JTextField.CENTER);
			tfWinNum[i].setOpaque(true);
			tfWinNum[i].setBackground(Color.YELLOW);
			tfWinNum[i].setPreferredSize(new Dimension(60,55));
			tfWinNum[i].setFont(new Font(Font.DIALOG, Font.BOLD, 45));
			tfWinNum[i].setText(String.valueOf(winNum[i]));
			pnlInfo1.add(tfWinNum[i]);
		}
		
		JPanel pnlInfo2 = new JPanel();
		tfBonusNum.setHorizontalAlignment(JTextField.CENTER);
		tfBonusNum.setOpaque(true);
		tfBonusNum.setBackground(Color.RED);
		tfBonusNum.setPreferredSize(new Dimension(60,55));
		tfBonusNum.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
		tfBonusNum.setText(String.valueOf(bonusNum));
		pnlInfo2.add(tfBonusNum);
		pnlInfo2.setBorder(new TitledBorder("���ʽ�"));
		pnlInfo.add(pnlInfo1);
		pnlInfo.add(pnlInfo2);
		pnlCenter.add(pnlInfo);
		
		
		for(int i=0; i<input; i++){
			Vector<Integer> correctNumList = new Vector<Integer>();
			correctNumList = compareNum(i);
			//input�� ��ŭ ���� ��ȣ�� �˸��� �г� �����
			showPnlArr[i] = new ShowPanel(correctNumList.size(), i);
			
			//stLotto�� ����Ǿ��ִ� �ζ� ��ȣ�� ������� �ؽ�Ʈ�ʵ忡 �ֱ�
			for(int j=0; j<LOTTO_SIZE; j++){
				String text = String.valueOf(stLotto.getLottoList().get(i)[j]);
				showPnlArr[i].getTfArr()[j].setText(text);
			}
			pnlCenter.add(showPnlArr[i].getPnlMain());
			
			//���̺� �����ŭ ������ ���̺� ���ؽ�Ʈ
			for(int j=0;j<correctNumList.size(); j++){
				String text = String.valueOf(correctNumList.get(j));
				showPnlArr[i].getLblVector().get(j).setHorizontalAlignment(JLabel.CENTER);
				showPnlArr[i].getLblVector().get(j).setText(text);
			}
			
			showResult(i,correctNumList);
		}
		
		//�гο� ��ġ�ϴ� ���ڸ� �Ѱ��ֱ�
		add(pnlCenter, BorderLayout.CENTER);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnClose);
		pnlSouth.add(btnTrace);
		
		JScrollPane scroll = new JScrollPane(pnlCenter);
		add(pnlNorth, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	
	private void showResult(int index, Vector<Integer> correctNumList){
		boolean isBonus = false;
		//���� ���ʽ� �˻�
		for(int i=0; i<LOTTO_SIZE;i++){
			if(bonusNum == stLotto.getLottoList().get(index)[i]){
				isBonus = true;
			}
		}
		
		if(isBonus){
			if(correctNumList.size()==5){
				showPnlArr[index].getLblResult().setText("2��");
			}else if(correctNumList.size()==4){
				showPnlArr[index].getLblResult().setText("4��");
			}else if(correctNumList.size()==3){
				showPnlArr[index].getLblResult().setText("5��");
			}else{
				showPnlArr[index].getLblResult().setText("��");
			}
		}else{
			if(correctNumList.size()==6){
				showPnlArr[index].getLblResult().setText("1��");
			}else if(correctNumList.size()==5){
				showPnlArr[index].getLblResult().setText("3��");
			}else if(correctNumList.size()==4){
				showPnlArr[index].getLblResult().setText("4��");
			}else if(correctNumList.size()==3){
				showPnlArr[index].getLblResult().setText("5��");
			}else{
				showPnlArr[index].getLblResult().setText("��");
			}
		}
	}
	
	private Vector<Integer> compareNum(int index){
		//��÷��ȣ�� ���ؼ� ��ġ�ϸ� lblArr�� ���
		Vector<Integer> correctNumList = new Vector<Integer>();

		for(int i=0; i<LOTTO_SIZE;i++){
			if(winNum[i] == stLotto.getLottoList().get(index)[i]){
				correctNumList.add(winNum[i]);
			}
		}
		return correctNumList;
	}
	
	private void traceSecond(){
		//�������� ��÷��ȣ�� �����ؼ� 
		//�� ��ȣ�� �ϳ��� 2���̻��� �ɶ����� ����
		
		Integer[] winLotto = new Integer[LOTTO_SIZE];
		
		boolean isBonus = false;
		boolean flag = false;
		int countTemp = 0;
		
		

		
		while(!flag){
			//��÷��ȣ �����
			winLotto = stLotto.makeNumber();
			Random r = new Random();
			int winBonus = r.nextInt(45)+1;
			for(int i=0; i<LOTTO_SIZE; i++){
				while(winLotto[i] == winBonus){
					winBonus = r.nextInt(45)+1;
					i=0;
				}
			}
			
			//input��ȣ ���� ��ŭ 
			for(int i=0; i<input; i++){
				int countCorrect = 0;
				//���ζ� ��ȣ�� ���鼭 ��÷��ȣ�� ���ʽ���ȣ�� ��ġ�ϴ��� üũ
				for(int j=0; j<LOTTO_SIZE; j++){
					if(stLotto.getLottoList().get(i)[j] == winBonus){
						isBonus = true;
					}
				}
				
				for(int j=0; j<LOTTO_SIZE; j++){
					//��ġ�ϴ� ���� ī��Ʈ
					for(int k=0; k<LOTTO_SIZE; k++){
						if(stLotto.getLottoList().get(i)[j] == winLotto[k]){
							countCorrect ++;
						}
					}
					countTemp++;	
				}
				
	
				if(countCorrect == 6){
					flag = true;
					String str = String.format("%,d" , countTemp );
					showMessage(str + "�� �õ�����" + (i+1) +"��° ������1�� ��÷�Դϴ�.");
					isBonus = false;
					
				}else if(isBonus){
					if(countCorrect == 5){
						flag = true;
						String str = String.format("%,d" , countTemp );
						showMessage(str + "�� �õ�����" + (i+1) +"��° ������2�� ��÷�Դϴ�.");
					}
				}
				countCorrect = 0;
					
				

			
								
			}//input��ŭ

		
		}//while���� ��
		
	}

	private void setFrame(){
		setTitle("Result");
		setSize(550,850);
		setLocation(500,100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void showMessage(String message){
		JOptionPane.showConfirmDialog(this, message , "�˸�" ,JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//getter&&setter
	
	public int getInput() {
		return input;
	}
	public void setInput(int input) {
		this.input = input;
	}
	public StoreLotto getStLotto() {
		return stLotto;
	}
	public void setStLotto(StoreLotto stLotto) {
		this.stLotto = stLotto;
	}
	
	
}
