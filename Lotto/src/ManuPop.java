
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ManuPop extends JFrame {
	private final int CHECK_SIZE = 45;
	private final int LOTTO_SIZE = 6;
	
	private JCheckBox[] checkList;
	private JButton btnOk;
	private JButton btnReset;
	private JButton btnCancel;
	private LottoGame owner;
	private StoreLotto stLotto;
	private int index;
	
	public ManuPop(LottoGame owner,StoreLotto stLotto,int index){
		setOwner(owner);
		setStLotto(stLotto);
		setIndex(index);
		init();
		addListener();
		setDisplay();
		setFrame();
	}
	
	private void init(){
		checkList = new JCheckBox[CHECK_SIZE];
		btnOk = new JButton("Ȯ��");
		btnReset = new JButton("����");
		btnCancel = new JButton("���");
	}
	
	private void addListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				
				if(src == btnOk){
					Vector<Integer> temp = checkCheckBox();
					Integer[] lotto = new Integer[LOTTO_SIZE];
					
					if(temp.size() == 6){
						lotto = temp.toArray(new Integer[0]);
						stLotto.getLottoList().set(index,lotto);

						for(int i=0; i<LOTTO_SIZE; i++){
							String text = String.valueOf(stLotto.getLottoList().get(index)[i]);
							owner.getPnlArr()[index].getTfArray()[i].setText(text);
						}
						dispose();
					}else{
						showMessage("6���� �����մϴ�");
					}
				}else if(src == btnReset){
					for(int i=0; i<CHECK_SIZE; i++){
							checkList[i].setSelected(false);
					}
				}else if(src == btnCancel){
					dispose();
				}else{
					showMessage("�߸��� �����Դϴ�");
				}
			}
		};
		
		btnOk.addActionListener(listener);
		btnReset.addActionListener(listener);
		btnCancel.addActionListener(listener);
	}

	private Vector<Integer> checkCheckBox(){
		Vector<Integer> lotto = new Vector<Integer>();
		
		for(int i=0; i<CHECK_SIZE; i++){
			if(checkList[i].isSelected()){
				lotto.add(Integer.parseInt(checkList[i].getText()));
			}
		}
		return lotto;
		
	}
	private void setDisplay(){
		JPanel pnlCenter = new JPanel(new GridLayout(9,5));
		JPanel pnlSouth = new JPanel();
		
		String str;
		for(int i=0;i<CHECK_SIZE;i++){
			str = String.valueOf(i+1);
			checkList[i] = new JCheckBox(str);
			pnlCenter.add(checkList[i]);
		}
		
		pnlSouth.add(btnOk);
		pnlSouth.add(btnReset);
		pnlSouth.add(btnCancel);
		
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
	}
	
	private void setFrame(){
		setTitle("Select Numbers");
		//pack();
		setSize(400,400);
		setLocation(800,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void showMessage(String message){
		JOptionPane.showConfirmDialog(this, message , "�˸�" ,JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	
	//setter&&getter
	
	public LottoGame getOwner() {
		return owner;
	}

	public void setOwner(LottoGame owner) {
		this.owner = owner;
	}

	public StoreLotto getStLotto() {
		return stLotto;
	}

	public void setStLotto(StoreLotto stLotto) {
		this.stLotto = stLotto;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}





	

}
