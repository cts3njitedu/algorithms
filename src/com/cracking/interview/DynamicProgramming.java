package com.cracking.interview;

public class DynamicProgramming {

	
	
	public static long numberOfSteps(int n,long[]mem) {
		
		if(n<0) {
			return 0;
		}
		if(n==0||n==1) {
			return 1;
		}
		if(mem[n]>0) {
			return mem[n];
		}
		else {
			mem[n] =numberOfSteps(n-1,mem)+numberOfSteps(n-2,mem)+numberOfSteps(n-3,mem);
			return mem[n];
		}
	}
}
