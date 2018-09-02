package com.cracking.interview;

import java.util.List;

public class MinimumContinousSubsequence {
	
	
	public static void crack(List<String>targetList, List<String>availableList) {
		
		
		int minValue = 0;
		int maxValue = -1;
		int targetListCounter = 0;
		
		for(int i=0; i<availableList.size();i++) {
			
			if(availableList.get(i).equals(targetList.get(targetListCounter))) {
				
				targetListCounter++;
				if(targetListCounter==targetList.size()) {
					maxValue = i;
					break;
				}
			}
			else if(availableList.get(i).equals(targetList.get(0))) {
				
				if(targetListCounter==1) {
					
					minValue = i;
					
				}
				
			}
		}
		System.out.println("["+minValue+((maxValue<minValue)?"]":","+maxValue+"]"));
	}

}
