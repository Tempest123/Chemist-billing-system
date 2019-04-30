import java.util.Scanner;

public class BPlusTrees {

	public void insert(String str) {
		btree  b = new btree();	
		String key;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the integer to be inserted: ");
		int k = sc.nextInt();
		key = String.valueOf(k);
		key=key+str;
		b.insert(k);
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
}