import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.InputMethodListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.awt.event.InputMethodEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class MainPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	static boolean changed = false;
	static int[] q;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {

		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlDkShadow);
		panel.setBounds(0, 0, 305, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		JFrame fr = this;
		JLabel lblClickHereTo = new JLabel(new ImageIcon("src/ApplicationImages/greencross.png"));
		lblClickHereTo.setForeground(new Color(0, 0, 0));
		lblClickHereTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddMedicine obj = new AddMedicine();
				obj.setVisible(true);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				fr.dispose();
			}
		});

		JLabel lblNewLabel = new JLabel("Click To Add Medicine");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(61, 163, 201, 36);
		panel.add(lblNewLabel);
		lblClickHereTo.setText("CLICK HERE TO ADD MEDICINE");
		lblClickHereTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickHereTo.setBounds(21, 11, 261, 188);
		panel.add(lblClickHereTo);
		File f = new File("medicines.txt");
		JButton btnsearchusingid = new JButton("Check Inventory");
		btnsearchusingid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory inv = new Inventory();
				inv.setVisible(true);
			}
		});

		btnsearchusingid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsearchusingid.setForeground(new Color(143, 188, 143));
		btnsearchusingid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnsearchusingid.setBackground(Color.DARK_GRAY);
		btnsearchusingid.setBounds(21, 231, 261, 36);
		panel.add(btnsearchusingid);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.GRAY);
		lblNewLabel_1.setIcon(new ImageIcon(MainPage.class.getResource("/ApplicationImages/rupee.png")));
		lblNewLabel_1.setBounds(21, 347, 65, 124);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 60));
		label.setOpaque(true);
		label.setBackground(Color.GRAY);
		label.setBounds(96, 369, 186, 77);
		panel.add(label);
		JButton btnbill = new JButton("Pay");
		btnbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
				Payment obj = new Payment(q);
				obj.setVisible(true);
				obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fr.dispose();
				
					
			}
		});
		
		btnbill.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				int input = JOptionPane.showConfirmDialog(null, "Confirm Payment?");
//				if(input == 0) {				
				
				
				//}
			}
		});
		btnbill.setForeground(Color.WHITE);
		btnbill.setBackground(new Color(255, 0, 0));
		btnbill.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnbill.setBounds(21, 482, 261, 53);
		panel.add(btnbill);

		File fmedicines = new File("medicines.txt");

		txtSearch = new JTextField();

		JPanel viewPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 89, 464, 461);
		scrollPane.setViewportView(viewPanel);
		contentPane.add(scrollPane);
		viewPanel.setBackground(SystemColor.activeCaption);
		viewPanel.setLayout(new GridLayout(200, 6));
		
		try {
			int len = 0;
			System.out.println("-----Initialize-----");
			FileReader freader = new FileReader(fmedicines);
			BufferedReader breader = new BufferedReader(freader);
			String linereader = breader.readLine();
			String[] slr;
			while (breader.readLine() != null) {
				len++;
			}
			len++;
			System.out.println(len);
			breader.close();
			freader.close();

			q = new int[len * 3];
			slr = linereader.split("\\|");

			FileReader filer = new FileReader(fmedicines);
			BufferedReader bufr = new BufferedReader(filer);
			String lr = bufr.readLine();
			int k = 0;
			while (lr != null) {
				q[k] = Integer.parseInt(lr.split("\\|")[0]);
				q[k+2] = Integer.parseInt(lr.split("\\|")[4]);
				System.out.println("Value in q: " + q[k]);
				k += 3;
				lr = bufr.readLine();
			}
			System.out.println(q.length);
			breader.close();
			freader.close();

			// System.out.println("Length="+qty.length);
		} catch (Exception e) {
			System.out.println("Couldn't calculate length");
		}

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedSearch();

			}

			@SuppressWarnings("null")
			public void changedSearch() {

				String username = txtSearch.getText().toString();
				boolean flag = false;
				
				try {
					FileReader fr = new FileReader(fmedicines);
					BufferedReader br = new BufferedReader(fr);
					String line = br.readLine();
					int p = -1;
					String[] stringcontents = {};
					viewPanel.removeAll();
					while (line != null) {
						p++;
						stringcontents = line.split("\\|");
						JPanel viewPanel2 = new JPanel();
						if (Pattern.matches(username + ".*", stringcontents[1])) {
							JLabel lblitem = new JLabel();

							lblitem.setOpaque(true);
							// lblitem.setBackground(Color.DARK_GRAY);
							lblitem.setForeground(Color.BLACK);
							lblitem.setHorizontalAlignment(SwingConstants.LEFT);
							lblitem.setFont(new Font("Tahoma", Font.PLAIN, 16));
							lblitem.setText(stringcontents[1]);// modified
							JLabel lblitem11 = new JLabel();
							lblitem11.setOpaque(true);
							// lblitem11.setBackground(Color.DARK_GRAY);
							lblitem11.setForeground(Color.BLACK);
							lblitem11.setHorizontalAlignment(SwingConstants.LEFT);
							lblitem11.setFont(new Font("Tahoma", Font.PLAIN, 16));
							lblitem11.setText(stringcontents[3]); 
							JLabel lblitem12 = new JLabel();
							lblitem12.setOpaque(true);
							// lblitem12.setBackground(Color.DARK_GRAY);
							lblitem12.setForeground(Color.BLACK);
							lblitem12.setHorizontalAlignment(SwingConstants.LEFT);
							lblitem12.setFont(new Font("Tahoma", Font.PLAIN, 16));
							lblitem12.setText(stringcontents[2]);
							viewPanel2.add(lblitem);
							viewPanel2.add(Box.createRigidArea(new Dimension(150 - (stringcontents[1].length() * 9), 10)));
							viewPanel2.add(lblitem11);
							viewPanel2.add(Box.createRigidArea(new Dimension(20, 0)));
							viewPanel2.add(lblitem12);
							viewPanel2.add(Box.createRigidArea(new Dimension(20, 0)));

							int quantity = Integer.parseInt(stringcontents[2]);
							JButton item2 = new JButton("-");
							item2.setFont(new Font("Tahoma", Font.PLAIN, 12));
							// item2.setPreferredSize(new Dimension(25, 25));
							final String cont = stringcontents[0];

							viewPanel2.add(item2);
							viewPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
							int id = Integer.parseInt(stringcontents[0]);
							
							
							JLabel item3 = new JLabel();
							
							for (int l = 0; l < q.length; l += 3) {
								if (q[l] == id) {
									item3.setText(q[l+1]+"");
								}								
							}
							
							
							
							repaint();
							viewPanel2.add(item3);
							
							
							
							item2.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {

									

									int vallbl12 = Integer.parseInt(lblitem12.getText().toString());
									if (vallbl12 < quantity) {
										vallbl12++;

									}

									int vallblitem3 = Integer.parseInt(item3.getText().toString());
									if (vallblitem3 > 0) {
										vallblitem3--;
										for (int l = 0; l < q.length; l += 3) {

											if (q[l] == id) {
												q[l + 1]--;
											}
											for (int i = 0; i < q.length; i+=3)
												System.out.println("Queue[" + (i+1) + "]:" + q[i+1]);
										}

									}
									lblitem12.setText(vallbl12 + "");
									item3.setText(vallblitem3 + "");
									
									calcSum();
								}

								private void calcSum() {
					
									int sum = 0;
									for(int j =0; j<q.length;j+=3) {
										sum = sum + q[j+1]*q[j+2];
										System.out.println(sum);
									}
									
									label.setText(sum+"");
									repaint();
									
								}
							});

							JButton item4 = new JButton("+");
							item4.setFont(new Font("Tahoma", Font.PLAIN, 12));
							// item4.setPreferredSize(new Dimension(25, 25));

							item4.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {

									

									int vallbl12 = Integer.parseInt(lblitem12.getText().toString());
									if (vallbl12 > 0) {
										vallbl12--;
									}

									int vallblitem3 = Integer.parseInt(item3.getText().toString());
									if (vallblitem3 < quantity) {
										vallblitem3++;
										
										for (int l = 0; l < q.length; l += 3) {
											if (q[l] == id) {
												q[l + 1]++;
											}
										}
										for (int i = 0; i < q.length; i+=3)
											System.out.println("Queue[" + i + "]:" + q[i+1]);
									}

									lblitem12.setText(vallbl12 + "");
									item3.setText(vallblitem3 + "");
									revalidate();
									repaint();
									
									calcSum();
								}

								private void calcSum() {
									
									int sum = 0;
									for(int j =0; j<q.length;j+=3) {
										sum = sum + q[j+1]*q[j+2];
										System.out.println(sum);
									}
									
									label.setText(sum+"");
									repaint();
									
								}
							});
							viewPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
							viewPanel2.add(item4);

							revalidate();
							repaint();
							viewPanel.add(viewPanel2);
							revalidate();
							repaint();
						}

						line = br.readLine();
					}

					br.close();
					fr.close();
				} catch (Exception e) {
					System.out.println("Failed to read from file");
				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedSearch();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtSearch.setBounds(394, 22, 464, 35);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		JLabel lblSearch = new JLabel("Search:");

		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setBounds(315, 18, 69, 46);
		contentPane.add(lblSearch);

	}
}