package ccf;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Mincost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,m;

Scanner scan=new Scanner(System.in);
	n=scan.nextInt();
	m=scan.nextInt();
	int[] w=new int[n+1];
	for(int i=1;i<=n;i++)
		w[i]=scan.nextInt();
	HashMap<Integer, HashMap<Integer,Integer>> e=new HashMap<>();
	for(int i=1;i<=n-1;i++) {
		int s=scan.nextInt();
		int t=scan.nextInt();
		int est=scan.nextInt();
		HashMap<Integer,Integer> tmp;
		if(e.containsKey(s)) {
			e.get(s).put(t, est);
		}else {
			tmp=new HashMap<>();
			tmp.put(t,est);
	    e.put(s,tmp );
		}
		if(e.containsKey(t)) {
			e.get(t).put(s, est);
		}else {
tmp=new HashMap<>();
tmp.put(s,est);
	    e.put(t,tmp );
		}

	}
int[][] input= new int[m][2];
for(int i=0;i<m;i++) {
	input[i][0]=scan.nextInt();
	input[i][1]=scan.nextInt();
}
scan.close();
int[] output=new int[m];
int start;
int end;
ArrayList<Integer> list=new ArrayList<>();
ArrayList<Integer> memory=new ArrayList<>();
int[] book=new int[n+1];
for(int i=0;i<m;i++) {
	start=input[i][0];
	 end=input[i][1];
	for(int b=1;b<=n;b++)
		book[b]=0;
	list.clear();
	memory.clear();
	dfs(start,end,book,e,list);
	for(int j=0;j<list.size();j++)
		memory.add(0);
	output[i]=dp(0,0,0,e,list,w,memory);
	
}
for(int out:output)
	System.out.println(out);
}
public static boolean dfs(int cur,int end,int[] book,HashMap<Integer, HashMap<Integer,Integer>> e,ArrayList<Integer> list) {
	list.add(cur);
	book[cur]=1;
	if(cur==end) {
		
		return true;
	}
	for(Integer neigbor:e.get(cur).keySet()) {
		if(book[neigbor]==0)
			if(dfs(neigbor,end,book,e,list)==true)
				return true;
	}
	list.remove(list.size()-1);
	return false;
}
public static int dp(int pre,int dis,int i,HashMap<Integer, HashMap<Integer,Integer>> e,ArrayList<Integer> list,int[] w,ArrayList<Integer> memory) {
	
	if(i==list.size()-1) {
		int tmp=w[list.get(pre)]*dis;
				
				return tmp;
	}
		
	int v1=w[list.get(pre)]*dis;
	if(memory.get(i)==0)  {
		int tmp=dp(i,e.get(list.get(i)).get(list.get(i+1)),i+1,e,list,w,memory);
		memory.set(i, tmp);
		
	}
	v1+=memory.get(i);
	
	int v2=0;
	v2+=dp(pre,dis+e.get(list.get(i)).get(list.get(i+1)),i+1,e,list,w,memory);
	
	
	return Math.min(v1, v2);
	
}
}
