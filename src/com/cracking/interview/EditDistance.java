package com.cracking.interview;

public class EditDistance {

	
	public static int editDistance(String source, String target) {
		
		int [][] d = new int[source.length()+1][target.length()+1];
		for(int i=0; i<=source.length();i++) {
			d[i][0]=i;
		}
		for(int j=0; j<=target.length();j++) {
			d[0][j]=j;
		}
		
		for(int i=0; i<source.length();i++) {
			for(int j=0; j<target.length();j++) {
				if(source.charAt(i)==target.charAt(j)) {
					d[i+1][j+1]=d[i][j];
				}
				else {
					d[i+1][j+1] = 1 + Math.min(d[i][j],Math.min(d[i+1][j], d[i][j+1]));
				}
			}
		}
		for(int i=0; i<d.length;i++) {
			for(int j=0; j<d[0].length; j++) {
				System.out.print(d[i][j]+" ");
			}
			System.out.println();
		}
		return d[source.length()][target.length()];
	}
}
