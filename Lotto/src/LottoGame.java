
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LottoGame extends JFrame{
	private final int LOTTO_SIZE = 6;
	
	private int input;
	private StoreLotto stLotto;
	
	private ReadyPanel[] rdPnlArr;
	private JButton btnStart;
	private JButton btnCancel;
	private JButton btnAutoExtract;
	
	public LottoGame(int input){
		setInput(input);
		init();
		addListener();
		setDisplay();
		setFrame();
	}
	private void init(){
		stLotto = new StoreLotto(input);
		rdPnlArr = new ReadyPanel[input];
		//�����гι迭�� �� �г� �ʱ�ȭ
		for(int i=0; i<input; i++){
			rdPnlArr[i] = new ReadyPanel();
			rdPnlArr[i].getPnlMain().setBorder(new TitledBorder((i+1) + "��° �����Դϴ�."));
		}
		btnStart = new JButton("Start");
		btnCancel = new JButton("Cancel");
		btnAutoExtract = new JButton("AutoExtract");

	}
	
	private void setDisplay(){
		//�����г��� �����гο� �ֱ�
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		
		for(int i=0; i<input; i++){
			pnlCenter.add(rdPnlArr[i].getPnlMain());
		}
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnStart);
		pnlSouth.add(btnCancel);
		pnlSouth.add(btnAutoExtract);
		
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	private void addListener(){
		//���⴩���� �ڵ�or���� �Ǵ� -> �ڵ� : ������������ / ���� : pick���� ����
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				int index = -1;
				for(int i=0; i<input; i++){
					if(src == rdPnlArr[i].getBtnExtract()){
						index = i;
					}
				}
				if(rdPnlArr[index].getBtnAuto().isSelected()){
					//�ڵ��� üũ�Ǿ������� stLotto�� �ڵ��޼ҵ带 ȣ�� -> lotto��ȣ�� �޾ƿͼ� tf�� �߰�
					stLotto.autoNumber(index);
					for(int i=0; i<LOTTO_SIZE; i++){
						String text = String.valueOf(stLotto.getLottoList().get(index)[i]);
						rdPnlArr[index].getTfArray()[i].setText(text);
					}
					
				}else if(rdPnlArr[index].getBtnManu().isSelected()){
					//������ üũ�Ǿ������� stLotto�� �����޼ҵ带 ȣ�� -> lotto��ȣ�� �޾ƿͼ� tf�� �߰�
					new ManuPop(LottoGame.this,stLotto,index);
				}else{
					showMessage("�ùٸ� ������ �ƴմϴ�");
				}
			}
				
			
		};
		
		for(int i=0; i<input; i++){
			rdPnlArr[i].getBtnExtract().addActionListener(listener);
		}
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				
				if(src == btnStart){
					new Result(stLotto, input);
				}else if(src == btnCancel){
					dispose();
				}else if(src == btnAutoExtract){
					for(int j=0; j<input; j++){
						stLotto.autoNumber(j);
						for(int k=0; k<LOTTO_SIZE; k++){
							String text = String.valueOf(stLotto.getLottoList().get(j)[k]);
							rdPnlArr[j].getTfArray()[k].setText(text);
						}
					}
				}else{
					showMessage("�߸��� �����Դϴ�.");
				}
				
			}
		};
		
		btnStart.addActionListener(btnListener);
		btnCancel.addActionListener(btnListener);
		btnAutoExtract.addActionListener(btnListener);
		
	}
	private void setFrame(){
		setTitle("LottoGame");
		pack();
		//setSize(300,130);
		setLocation(500,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
	public ReadyPanel[] getPnlArr() {
		return rdPnlArr;
	}
	public void setPnlArr(ReadyPanel[] pnlArr) {
		this.rdPnlArr = pnlArr;
	}
	

}
