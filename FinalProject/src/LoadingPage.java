import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

public class LoadingPage extends JFrame {

	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static JLabel load;
	JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		
		int x;
					LoadingPage frame = new LoadingPage();
					frame.setVisible(true);
					
					
		try 
		{
			for(x=0;x<=100;x++)
			{
				LoadingPage.progressBar.setValue(x);
			
				Thread.sleep(30);
				LoadingPage.load.setText(Integer.toString(x)+ "%");
				
				if(x==100)
				{
					Music obj=new Music();
					//obj.main(null);
					obj.setVisible(true);
					frame.dispose();	
			    	//dispose();
				}
			} 
		}
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public LoadingPage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 110, 950, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 0, 1230, 750);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/Logo1.png"));
		lblNewLabel_4.setIcon(img2);
		lblNewLabel_4.setBounds(10, 10, 135, 141);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("MelodyMatrix");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.BOLD, 22));
		lblNewLabel_1.setBounds(287, 336, 174, 69);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please Wait...");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(418, 587, 179, 41);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		
		JLabel lblNewLabel_3 = new JLabel("");
		ImageIcon img1 = new ImageIcon(this.getClass().getResource("/Loading.gif"));
	    lblNewLabel_3.setIcon(img1);
		lblNewLabel_3.setBounds(70, 35, 570, 412);
		panel_2.add(lblNewLabel_3);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 128, 128));
		progressBar.setBounds(38, 550, 874, 27);
		panel_2.add(progressBar);
		
		load = new JLabel("");
		load.setBackground(new Color(255, 255, 255));
		load.setFont(new Font("Tahoma", Font.BOLD, 20));
		load.setForeground(new Color(255, 255, 255));
		load.setBounds(455, 513, 64, 27);
		panel_2.add(load);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 715, 1230, 35);
		panel_2.add(panel);
		panel.setLayout(null);
		
	
	}
}

