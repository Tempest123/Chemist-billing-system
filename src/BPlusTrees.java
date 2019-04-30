public class BPlusTrees {
	
	btree  b = new btree();	
	
	public void insert(String str,int key) {
		
		String item;
		
		b.insert(key);
		b.display();
		
//				case 2:	System.out.println("Enter the integer to be found: ");
//					key = sc.nextInt();
//					flag=b.search(key);
//					if(flag)
//						System.out.println("Element is present\n");
//					else
//						System.out.println("Element not found\n");
//					break;
//				case 3: b.display();
//					break;
//				default:System.exit(0);
//			
//		}
		
	}
	
	public static void main(String args[]) {
		BPlusTrees obj = new BPlusTrees();
		for(int i = 0;i<10;i++)
			obj.insert("sample", i);
	}
}