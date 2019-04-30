import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddMedicine extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfMedName;
	private JTextField tfQuantity;
	private JTextField tfExipryDate;
	private JTextField tfManufacturingCompany;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMedicine frame = new AddMedicine();
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
	public AddMedicine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 514);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfId.setBounds(214, 109, 158, 28);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(44, 108, 101, 28);
		contentPane.add(lblId);
		
		JLabel lblMedicineName = new JLabel("Medicine Name");
		lblMedicineName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedicineName.setBounds(44, 161, 101, 28);
		contentPane.add(lblMedicineName);
		
		tfMedName = new JTextField();
		tfMedName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMedName.setColumns(10);
		tfMedName.setBounds(214, 162, 158, 28);
		contentPane.add(tfMedName);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(44, 213, 101, 28);
		contentPane.add(lblQuantity);
		
		tfQuantity = new JTextField();
		tfQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(214, 214, 158, 28);
		contentPane.add(tfQuantity);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpiryDate.setBounds(44, 270, 101, 28);
		contentPane.add(lblExpiryDate);
		
		tfExipryDate = new JTextField();
		tfExipryDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfExipryDate.setColumns(10);
		tfExipryDate.setBounds(214, 271, 158, 28);
		contentPane.add(tfExipryDate);
		
		JLabel lblManufacturingCompany = new JLabel("Manufacturing Company");
		lblManufacturingCompany.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManufacturingCompany.setBounds(44, 325, 158, 28);
		contentPane.add(lblManufacturingCompany);
		
		tfManufacturingCompany = new JTextField();
		tfManufacturingCompany.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfManufacturingCompany.setColumns(10);
		tfManufacturingCompany.setBounds(214, 326, 158, 28);
		contentPane.add(tfManufacturingCompany);
		
		JLabel lblNewLabel = new JLabel("Enter Medicine Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(44, 25, 312, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnAddRecord = new JButton("Add record");
		btnAddRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnAddRecord.setBackground(new Color(255, 0, 0));
		btnAddRecord.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddRecord.setBounds(44, 403, 333, 36);
		contentPane.add(btnAddRecord);
		
		JTextPane tpHorizontalLine = new JTextPane();
		tpHorizontalLine.setBackground(Color.BLACK);
		tpHorizontalLine.setBounds(20, 73, 357, 2);
		contentPane.add(tpHorizontalLine);
	}
}
