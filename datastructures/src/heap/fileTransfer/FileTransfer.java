package heap.fileTransfer;

import java.util.Scanner;

/**
 * 类似于并查集（查询效率高，连接效率低)
 * @author ruiyao.shen
 *
 */
public class FileTransfer {
	public void solve() {
		long begin = System.currentTimeMillis();
		Scanner s = new Scanner(System.in);
		int connected = 0;
		int n = s.nextInt();
		int[] coms = new int[n + 1];
		coms[0] = Integer.MAX_VALUE;
		for(int i=1;i < n;i++){
			coms[i] = i;
		}
		while(true){
			String request = s.next();
			if(request.equals("S")){
				break;
			}
			int c1 = s.nextInt();
			int c2 = s.nextInt();
			if(request.equals("C")){
				if(coms[c1] == coms[c2])
					System.out.println("Yes");
				else
					System.out.println("No");
			} else if(request.equals("I")){
				int index1 = coms[c1];
				int index2 = coms[c2];
				int t = 0;
				for(int i=1;i<=n;i++){
					if(coms[i]==index1 || coms[i]==index2){
						coms[i] = index1;
						t++;
					}
				}
				connected = Math.max(connected, t);
			}
		}
		if(connected == n)
			System.out.println("The network is connected.");
		else
			System.out.println("There are "+ connected +" components.");
		s.close();
		long end = System.currentTimeMillis();
		System.out.println((end-begin)+"ms");
	}
}
