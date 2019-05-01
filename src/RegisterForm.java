import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfusername;
	private JPasswordField tfpassword;
	private JTextField textField_1;
	private JPasswordField tfrepassword;
	private JTextField tfemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public RegisterForm() {

		JFrame jframe = this;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(new LineBorder(Color.black));

		JLabel lblminimize = new JLabel("-");
		lblminimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jframe.setState(JFrame.ICONIFIED);
			}
		});
		lblminimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblminimize.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblminimize.setBackground(Color.RED);
		lblminimize.setBounds(278, 0, 26, 28);
		contentPane.add(lblminimize);

		JLabel lblclose = new JLabel("X");

		lblclose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jframe.dispose();
			}
		});
		lblclose.setHorizontalAlignment(SwingConstants.CENTER);
		lblclose.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblclose.setBackground(Color.RED);
		lblclose.setBounds(314, 2, 26, 28);
		contentPane.add(lblclose);

		JLabel lblusername = new JLabel("Username");
		lblusername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblusername.setBounds(43, 122, 85, 22);
		contentPane.add(lblusername);

		JLabel lblpassword = new JLabel("Password");
		lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpassword.setBounds(43, 229, 85, 22);
		contentPane.add(lblpassword);

		tfusername = new JTextField();
		tfusername.setColumns(10);
		tfusername.setBounds(148, 122, 174, 24);
		contentPane.add(tfusername);

		tfpassword = new JPasswordField();
		tfpassword.setBounds(148, 229, 174, 24);
		contentPane.add(tfpassword);

		JLabel lblsignup = new JLabel("Sign Up:");
		lblsignup.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblsignup.setBounds(117, 44, 99, 28);
		contentPane.add(lblsignup);

		JTextPane horizontalline = new JTextPane();
		horizontalline.setBackground(SystemColor.desktop);
		horizontalline.setBounds(29, 88, 293, 1);
		contentPane.add(horizontalline);

		JLabel lblretype = new JLabel("Retype Password");
		lblretype.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblretype.setBounds(36, 282, 138, 22);
		contentPane.add(lblretype);

		tfrepassword = new JPasswordField();
		tfrepassword.setBounds(184, 284, 138, 24);
		contentPane.add(tfrepassword);

		JLabel lblemail = new JLabel("e-mail");
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblemail.setBounds(43, 175, 85, 22);
		contentPane.add(lblemail);

		tfemail = new JTextField();
		tfemail.setColumns(10);
		tfemail.setBounds(148, 175, 174, 24);
		contentPane.add(tfemail);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked");
				boolean proceed = true;
				String username = tfusername.getText().toString();
				String password = String.valueOf(tfpassword.getPassword());
				String repass = String.valueOf(tfrepassword.getPassword());
				String email = tfemail.getText().toString();

				if (!password.equals(repass)) {
					JOptionPane.showMessageDialog(null, "Passwords do not match!");
					proceed = false;
				}

				if (username.isEmpty() || email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "One of more fields are empty");
					proceed = false;
				}

				if (proceed) {
					File fuser = new File("usercred.txt");
					boolean flag = true;
					try {
						FileReader fr = new FileReader(fuser);
						BufferedReader br = new BufferedReader(fr);
						String line = br.readLine();
						int p=0;
						String[] stringcontents= {};
						while (line != null) {
							stringcontents = line.split("\\|");
							for (p=0;p<stringcontents.length-1;p+=3) {
								if(stringcontents[p].equals(username)) {
									JOptionPane.showMessageDialog(null, "User already exists!");	
									proceed = false;
									break;
								}					
							}
							if(flag)
								line = br.readLine();
						}					
						br.close();
						fr.close();
						
						if(p>=stringcontents.length-1)
							flag=false;
						if(proceed && !flag) {
							JOptionPane.showMessageDialog(null, "Registration Completed!");
							String str = username+"|"+password+"|"+email+System.lineSeparator();
							try {
								FileWriter fw = new FileWriter(fuser, true);
								BufferedWriter bw = new BufferedWriter(fw);
								bw.write(str);
								jframe.dispose();
								bw.close();
								fw.close();
							} catch (Exception e) {
								System.out.println("Failed to write to file");
							}
						}
					} catch (Exception e) {
						System.out.println("Failed to read or wirte");
					}
				}
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(29, 365, 293, 54);
		contentPane.add(btnNewButton);

	}
}
