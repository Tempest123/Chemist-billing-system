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

public class MainPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	static boolean changed = false;
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

		JLabel lblClickHereTo = new JLabel(new ImageIcon("src/ApplicationImages/greencross.png"));
		lblClickHereTo.setForeground(new Color(0, 0, 0));
		lblClickHereTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddMedicine obj = new AddMedicine();
				obj.setVisible(true);
				obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		
		Inventory inv = new Inventory();
		JButton btnsearchusingid = new JButton("Check Inventory");
		btnsearchusingid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inv.setVisible(true);
			}
		});

		btnsearchusingid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsearchusingid.setForeground(new Color(143, 188, 143));
		btnsearchusingid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnsearchusingid.setBackground(Color.DARK_GRAY);
		btnsearchusingid.setBounds(21, 231, 261, 36);
		panel.add(btnsearchusingid);

		File fmedicines = new File("medicines.txt");

		txtSearch = new JTextField();

		JPanel viewPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 89, 464, 461);
		scrollPane.setViewportView(viewPanel);
		contentPane.add(scrollPane);
		viewPanel.setBackground(SystemColor.activeCaption);
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
		viewPanel.setAlignmentX(LEFT_ALIGNMENT);

		String arr[] = new String[100];

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedSearch();

			}

			public void changedSearch() {

				String username = txtSearch.getText().toString();
				boolean flag = false;
				try {
					FileReader fr = new FileReader(fmedicines);
					BufferedReader br = new BufferedReader(fr);
					String line = br.readLine();
					int p = 0;
					String[] stringcontents = {};

					viewPanel.removeAll();
					while (line != null) {

						stringcontents = line.split("\\|");
						JPanel viewPanel2 = new JPanel();
						viewPanel2.setAlignmentX(LEFT_ALIGNMENT);
						viewPanel2.setAlignmentY(LEFT_ALIGNMENT);
						if (Pattern.matches(username + ".*", stringcontents[0])) {
							JLabel lblitem = new JLabel();
							revalidate();
							repaint();
							lblitem.setText(stringcontents[0]);
							lblitem.setFont(new Font("Tahoma", Font.PLAIN, 14));
							lblitem.setHorizontalAlignment(SwingConstants.LEFT);
							;
							viewPanel2.add(lblitem);
							viewPanel2.add(Box.createRigidArea(new Dimension(200, 0)));
							JButton item2 = new JButton("-");
							item2.setPreferredSize(new Dimension(25, 25));
							viewPanel2.add(item2);
							JTextField item3 = new JTextField("1");
							viewPanel2.add(item3);
							JButton item4 = new JButton("+");
							item4.setPreferredSize(new Dimension(25, 25));
							viewPanel2.add(item4);
							viewPanel.add(viewPanel2);
							revalidate();
							repaint();
							p++;
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
