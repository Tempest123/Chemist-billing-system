import java.awt.BorderLayout;
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
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;

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
		
		File fuser = new File("usercred.txt");
			
		txtSearch = new JTextField();

		
		JPanel viewPanel = new JPanel();
    	JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 91, 450, 459);
		scrollPane.setViewportView(viewPanel);
		contentPane.add(scrollPane);
		viewPanel.setBackground(SystemColor.activeCaption);
		viewPanel.setLayout(null);
		
		
		String arr[]=new String[100]; 
		
			
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			
			@Override
		    public void insertUpdate(DocumentEvent e) {
		    	changedSearch();
		    	
		    
		    }
			
			
		    public void changedSearch() {
		    			    	
		    	String username = txtSearch.getText().toString();
				boolean flag = false;
				try {
					FileReader fr = new FileReader(fuser);
					BufferedReader br = new BufferedReader(fr);
					String line = br.readLine();
					int p=0;
					String[] stringcontents= {};
					
					viewPanel.removeAll();
					while (line != null) {
						
						stringcontents = line.split("\\|");		    		
						
							JLabel lblitem = new JLabel();
					    	if(Pattern.matches(username+".*", stringcontents[0])) {
					    			revalidate();	
					    			repaint();
					    			lblitem.setText(stringcontents[0]);
					    			lblitem.setBounds(new Rectangle(0, p*25, 300, 25));
					    			lblitem.setFont(new Font("Tahoma", Font.PLAIN, 18));
					    			viewPanel.add(lblitem);		
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
