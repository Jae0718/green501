
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Input extends JFrame{
	
	private final int NORMAL_EXIT = 0;
	
	private JLabel lbl;
	private JLabel img;
	private JTextField tfInput;
	private JButton btnOk;
	private JButton btnCancel;
	
	
	
	
	public Input(){
		init();
		setDisplay();
		addListener();
		setFrame();
	}
	public void init(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){

			}
		img = new JLabel();
		lbl = new JLabel("���� �Ͻ��� �Է��ϼ���(1~5)", JLabel.CENTER);
		tfInput = new JTextField(20);
		btnOk = new JButton("Ȯ��");
		btnCancel = new JButton("���");
	}
	private void setDisplay(){
		JPanel pnlNorth = new JPanel();
		img.setIcon(new ImageIcon("imgs/icon.png"));
		pnlNorth.setBorder(new EmptyBorder(5,5,5,5));
		pnlNorth.add(img);
		
		JPanel pnlCenter = new JPanel(new GridLayout(2,1));
		pnlCenter.setBorder(new EmptyBorder(10,10,0,10));
		JPanel pnlCenter1 = new JPanel();
		pnlCenter1.add(tfInput);
		pnlCenter.add(lbl);
		pnlCenter.add(pnlCenter1);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnOk);
		pnlSouth.add(btnCancel);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	private void addListener(){
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if(src == btnOk){
					int input = Integer.parseInt(tfInput.getText().trim());
					try{
						if(input == 0){
							showMessage("���ڸ� �Է��ϼ���");
						}else if(input > 11){
							showMessage("����ģ ���Ǳ��Ÿ� �����ϼ���");
						}else{
							//�ζǰ���ȣ��
							new LottoGame(input);
						}
					}catch(NumberFormatException e){
						
					}
				}else if(src == btnCancel){
					System.exit(NORMAL_EXIT);
				}else{
					
				}
				
			}
		};
		
		btnOk.addActionListener(listener);
		btnCancel.addActionListener(listener);
	}
	
	private void showMessage(String message){
		JOptionPane.showConfirmDialog(this, message , "�˸�", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	private void setFrame(){
		setTitle("�Է�");
		pack();
		//setSize(300,130);
		setLocation(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		 new Input();
	}

}
