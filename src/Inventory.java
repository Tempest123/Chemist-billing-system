import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;

public class Inventory extends JFrame {

	private static JPanel contentPane;
	static String[] records;
	String[] columnNames;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory();
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
	public Inventory() {
		
		
		
		setBounds(100, 100, 434, 491);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BPlusTrees obj = new BPlusTrees();
		System.out.println("---------------------------------"+obj.records.length+"---------------------------");
		obj.show();//needed!
		
		records = new String[obj.records.length];
		for(int i = 0;i < obj.records.length;i++) {
			records[i] = obj.records[i];
			System.out.println(records[i]);
		}
		int numberoffields = 5;
		String words[] = new String[numberoffields];
		String[] columnNames = {"ID","Medicine Name","Stock","Date","Company"};
		Object[][] data = new String[records.length][numberoffields];
		
		for(int k = 0; k < records.length;k++) {
			words = records[k].split("\\|");
			for(int j = 0; j < numberoffields; j++) {
				data[k][j] = new String(words[j]);
				System.out.println(data[k][j]+"\t");
			}
		}
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		JTable myTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(myTable);
		scrollPane.setBounds(10, 11, 398, 430);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);

		
	}
}
