import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Payment extends JFrame {
	static int q[];

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment(q);
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
	public Payment(int q[]) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		int m = 1, n = 0;
		int arr[] = new int[(int) Math.ceil(q.length / 3)];
		int brr[] = new int[(int) Math.ceil(q.length / 3)];
		int crr[] = new int[(int) Math.ceil(q.length / 3)];
		for (int i = 0; i < q.length; i++) {
			if ((i + 1) % 3 == 0) {
				System.out.println(q[i]);
			}

			else {
				System.out.print(q[i] + "\t");

			}
			if (i == m) {
				brr[n] = q[i];
				crr[n++] = q[i + 1];
				m += 3;
			}

		}

		for (int z = 0; z < brr.length; z++)
			arr[z] = brr[z] * crr[z];

		Object[] columnNames = { "ID", "Quantity", "Price", "Sub-total" };
		Object[][] data = new Object[(int) Math.ceil(q.length / 3)][4];

		int row = 0;
		for (int i = 0; i < q.length; i += 3) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) {
					if (q[1] == 0)
						break;
					data[0][j] = q[i + j];
					data[0][3] = arr[0];

				}

				else {
					if (q[i + 1] == 0)
						break;
					data[row][j] = q[i + j];
					data[row][3] = arr[row];
				}

			}
			row++;

		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);

		for (int i = table.getRowCount() - 1; i >= 0; i--) {
			if (table.getModel().getValueAt(i, 0) == null)
				model.removeRow(i);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVisible(true);
		scrollPane.setBounds(10, 61, 381, 307);
		contentPane.add(scrollPane);

		JFrame jframe = this;
		btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			File fmedicines = new File("medicines.txt");

			public void actionPerformed(ActionEvent arg0) {
				int r = 0;
				try {
					FileReader fr = new FileReader(fmedicines);
					BufferedReader br = new BufferedReader(fr);
					String line = br.readLine();
					int p = 0;
					// Too lazy to find length of it
					String[][] stringcontents = new String[100][100];
					while (line != null) {
						stringcontents[p] = line.split("\\|");
						System.out.println("String contents: " + stringcontents[p][2] + " inside brr thing :" + brr[p]);
						stringcontents[p][2]=(Integer.parseInt(stringcontents[p][2])-brr[p])+"";
						System.out.println("String contents after modi: " + stringcontents[p][2]);
						p++;
						line = br.readLine();
					}

					br.close();
					fr.close();
					
					
					File fmedicines = new File("medicines.txt");
					FileWriter fw = new FileWriter(fmedicines);
					BufferedWriter bw = new BufferedWriter(fw);
//					for(int l =0;l<p;l++) {
//						System.out.println(stringcontents[l][0]+"|"+stringcontents[l][1]+"|"+stringcontents[l][2]+"|"+stringcontents[l][3]+"|"+stringcontents[l][4]);
//					}
//					
					for(int l =0;l<p;l++) {
						bw.write(stringcontents[l][0]+"|"+stringcontents[l][1]+"|"+stringcontents[l][2]+"|"+stringcontents[l][3]+"|"+stringcontents[l][4]+System.lineSeparator());
					}
					bw.close();
					fw.close();
					
					}
				 catch (Exception e) {
					System.out.println("Failed to read from file");
				}
				JOptionPane.showMessageDialog(null, "Payment successful!");
				MainPage mp = new MainPage();
				mp.setVisible(true);
				mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jframe.dispose();
				
					
				

			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(10, 379, 381, 58);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Bill:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(10, 11, 381, 37);
		contentPane.add(lblNewLabel);

	}
}
