import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {
	private String username;
	private String password;
	private JPanel contentPane;
	private final JLabel lblclose = new JLabel("X");
	private JTextField tfusername;
	private JPasswordField tfpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void readValues() {
		boolean flag=false;
		username = tfusername.getText().toString();
		password = String.copyValueOf(tfpassword.getPassword());
		File fuser = new File("usercred.txt");
		
		try {
			FileReader fr = new FileReader(fuser);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int p=0;
			String[] stringcontents= {};
			while (line != null) {
				stringcontents = line.split("\\|");
				//Successful Login
				for (p=0;p<stringcontents.length-1;p+=3) {
					if(stringcontents[p].equals(username) && stringcontents[p+1].equals(password)) {
						JOptionPane.showMessageDialog(this, "Login Successful");
						flag = true;
						break;
					}					
				}

				line = br.readLine();				
			}			
			//Login Failed
			if(!flag)
				JOptionPane.showMessageDialog(this, "Login Failed");
				
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("Failed to read from file");
		}
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		this.setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new MotionPanel(this);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 261, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblgreencross = new JLabel(new ImageIcon("src/ApplicationImages/greencross.png"));
		lblgreencross.setBounds(0, 27, 261, 188);
		panel.add(lblgreencross);
		
		JLabel lblClickHereTo = new JLabel("Click Here to register");
		lblClickHereTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterForm rf = new RegisterForm();
				rf.setVisible(true);
				rf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);			
			}
		});
		lblClickHereTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickHereTo.setForeground(new Color(255, 0, 0));
		lblClickHereTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClickHereTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickHereTo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblClickHereTo.setBounds(0, 264, 261, 25);
		panel.add(lblClickHereTo);
		
			
		
		lblclose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblclose.setBackground(new Color(255, 0, 0));
		lblclose.setHorizontalAlignment(SwingConstants.CENTER);
		lblclose.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblclose.setBounds(568, 0, 26, 28);
		contentPane.add(lblclose);
		JFrame jframe = this;
		JLabel lblminimize = new JLabel("-");
		lblminimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jframe.setState(JFrame.ICONIFIED);
				
			}
		});
		lblminimize.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
		lblminimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblminimize.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblminimize.setBounds(532, -2, 26, 28);
		contentPane.add(lblminimize);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(315, 84, 85, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(315, 128, 85, 22);
		contentPane.add(lblPassword);
		contentPane.setLayout(null);
		contentPane.setBorder(new LineBorder(Color.black));
		
		tfusername = new JTextField();
		tfusername.setBounds(420, 84, 138, 24);
		contentPane.add(tfusername);
		tfusername.setColumns(10);
		
		tfpassword = new JPasswordField();
		tfpassword.setBounds(420, 128, 138, 24);
		contentPane.add(tfpassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readValues();
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(315, 219, 243, 31);
		contentPane.add(btnNewButton);
	}
}
