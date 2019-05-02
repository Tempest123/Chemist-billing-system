import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class btree {
	private static final int MAX = 4;
	private static final int MIN = 2;
	Node root;
	Node found;
	int fkey;
	public String allrecords="";

	btree() {
		root = create_newnode();

	}

	void insert(int key, String str) {

		int i, j, t, max = 0;
		String maxs;
		Node temp = find_leaf(root, key);
		if (temp == null) {
			System.out.println("Error!: Cannot insert: This integer is already present in the tree\n");
			return;
		}
		if (!isFull(temp)) {
			System.out.println("Element inserted successfully\n");
			for (i = 0; i < MAX; i++) {
				if (temp.ele[i] == -1) {
					temp.ele[i] = key;
					System.out.println("str: " + str);
					temp.details[i] = new String(str);
					System.out.println("temp.details: " + temp.details[i]);
					break;
				}
				max = temp.ele[i];
				maxs = new String(temp.details[i]);
				System.out.println("maxs: "+maxs);
			}
			sort_node(temp);
			if (key > max && temp.parent != null)
				update_parent(temp.parent, key, max, str);
			return;
		}
		if (isFull(temp)) {
			split(temp);
			insert(key, str);
		}
	}

	void split(Node x) {
		int i;
		Node temp;
		if (x.parent == null) {
			temp = create_newnode();
			root = create_newnode();

			root.ele[0] = x.ele[MIN - 1];
			root.child[0] = x;
			root.details[0] = new String(x.details[MIN - 1]);
			System.out.println("root.detalis: "+root.details[0]);
			root.ele[1] = x.ele[MAX - 1];
			root.child[1] = temp;
			root.details[1] = new String(x.details[MAX - 1]);
			System.out.println("root.detalis: "+root.details[1]);
			x.parent = root;
			temp.parent = x.parent;
		} else {
			if (isFull(x.parent))
				split(x.parent);

			temp = create_newnode();
			temp.parent = x.parent;
			for (i = 0; i < MAX; i++) {
				if (x.parent.ele[i] == x.ele[MAX - 1])
					x.parent.child[i] = temp;
				if (x.parent.ele[i] == -1) {
					x.parent.ele[i] = x.ele[MIN - 1];
					x.parent.child[i] = x;
					x.parent.details[i] = new String(x.details[MIN - 1]);
					System.out.println("x.parent.details[i]: "+x.parent.details[i]);
					break;
				}

			}
		}
		temp.next_child = x.next_child;
		x.next_child = temp;
		for (i = 0; i < MIN; i++) {
			temp.ele[i] = x.ele[MIN + i];
			temp.details[i] = new String(x.details[MIN + i]);
			x.ele[MIN + i] = -1;
			x.details[MIN + i] = "";
			temp.child[i] = x.child[MIN + i];
			if (temp.child[i] != null)
				temp.child[i].parent = temp;
			x.child[MIN + i] = null;
		}
		sort_node(x.parent);
	}

	@SuppressWarnings("null")
	boolean search(int key) {
		Node temp = find_leaf(root, key);
		if (temp == null) {
			String[] res = found.details[fkey].split(System.lineSeparator());
			System.out.println("Found it!: " + res[res.length-1]);
			return true;
			
		}
			
		return false;
	}

	boolean isFull(Node x) {
		return (x.ele[MAX - 1] == -1) ? false : true;
	}

	Node find_leaf(Node x, int key) {
		int i;
		if (x.child[0] == null) {
			for (i = 0; i < MAX; i++)
				if (key == x.ele[i]) {
					found=x;
					fkey=i;
					return null;
				}
					
			return x;
		}
		for (i = 0; i < MAX; i++) {
			if (x.ele[i] >= key)
				return find_leaf(x.child[i], key);
			if (x.ele[i] == -1)
				return find_leaf(x.child[i - 1], key);
		}
		return find_leaf(x.child[MAX - 1], key);
	}

	Node create_newnode() {
		int i;
		Node temp = new Node();
		if (temp == null) {
			System.out.println("Fatal ERROR: Out of Memory!\n");
			System.exit(0);
		}
		for (i = 0; i < MAX; i++) {
			temp.ele[i] = -1;
			temp.child[i] = null;
			temp.details[i] = "";
		}
		temp.parent = null;
		temp.next_child = null;
		return temp;
	}

	void sort_node(Node x) {
		int i, j;
		int temp_ele;
		String temp_details;
		Node temp_child;
		for (i = 0; i < MAX && x.ele[i] != -1; i++) {
			for (j = i + 1; j < MAX && x.ele[j] != -1; j++) {
				if (x.ele[i] > x.ele[j]) {
					temp_ele = x.ele[i];
					temp_child = x.child[i];
					temp_details = new String(x.details[i]);

					x.ele[i] = x.ele[j];
					x.child[i] = x.child[j];
					x.details[i] = new String(x.details[j]);

					x.ele[j] = temp_ele;
					x.child[j] = temp_child;
					x.details[j] = new String(temp_details);
				}
			}
		}
	}

	void update_parent(Node x, int key, int max, String str) {
		int i, new_max = 0;
		String new_maxs;
		for (i = 0; i < MAX && (x.ele[i] != -1); i++) {
			new_max = x.ele[i];
			new_maxs = new String(x.details[i]);
			if (x.ele[i] == max) {
				x.ele[i] = key;
				x.details[i] = new String(str);
			}
				
		}
		if (key > new_max && x.parent != null)
			update_parent(x.parent, key, max, str);
	}

	void display() {
		Node queue[] = new Node[50];
		int f = 0, r = -1, lvl = -1, i;
		try {
			queue[++r] = null;
			queue[++r] = root;
//			for (int k = 0; k < MAX; k++)
//				for (int l = 0; l < MAX && queue[k]!= null; l++)
//					System.out.print(queue[k].ele[l] + "\t");
			allrecords="";//added now
			while (f < r) {
				if (queue[f] == null) {
					System.out.println("\n\nLevel --> " + (++lvl));
//				if(lvl!=0)
//					br.write(System.lineSeparator());

					if (queue[r] != null)
						queue[++r] = null;
					f++;
					allrecords = "";
				} else {
					
					for (i = 0; i < MAX; i++) {
						if (queue[f].ele[i] != -1) {
							System.out.print(queue[f].ele[i] + ":");
								String[] line = queue[f].details[i].split(System.lineSeparator());
							System.out.print(line[line.length-1]);
							allrecords = allrecords + queue[f].ele[i] + "|" + line[line.length-1] + System.lineSeparator();
							System.out.println("all records: " + allrecords);
							System.out.print("\t");
							

						}
						if (queue[f].child[i] != null)
							queue[++r] = queue[f].child[i];
					}
					System.out.print("       ");
					// br.write("/");
					f++;
				}
			}
		} catch (Exception e) {
			System.out.println("File reader error" + e);
		}

	}
}