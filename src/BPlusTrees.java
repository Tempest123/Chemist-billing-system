import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class BPlusTrees {

	static btree b = new btree();
	boolean read = false;
	String item;
	File fmedicine = new File("medicines.txt");
	String allrecords="";
	String[] records= {};

	BPlusTrees() {
		if (!read) {
			try {
				FileReader fr = new FileReader(fmedicine);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				String stringcontents[] = {};
				String rest = "";
				while (line != null) {
					stringcontents = line.split("\\|");
					int j;
					for (j = 1; j < stringcontents.length - 1; j++) {
						rest = rest + stringcontents[j] + "|";
					}
					rest = rest + stringcontents[j] + System.lineSeparator();
					b.insert(Integer.parseInt(stringcontents[0]), rest);
					line = br.readLine();
					//System.out.println("rest: " +rest);
				}
			} catch (Exception e) {
				System.out.println("Failed to read to file");
			}
			read = true;
		}
	}

	public void insert(String str, int key) {
		try {
			FileWriter fw = new FileWriter(fmedicine, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str);
			bw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("Failed to write to file");
		}

		b.insert(key, str);
	}
	
	public void show() {
		b.display();
		this.allrecords = new String(b.allrecords);
		records = allrecords.split(System.lineSeparator());
	}
	
	public boolean found(int key) {
		if(b.search(key))
			return true;
		else
			return false;
	}

	public void search(int key) {
		boolean flag;
		flag = b.search(key);
		if (!flag)
			System.out.println("Element not found");
		b.display();

	}

}