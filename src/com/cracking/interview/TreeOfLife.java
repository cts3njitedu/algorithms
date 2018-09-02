package com.cracking.interview;

import java.util.LinkedList;
import java.util.Queue;

public class TreeOfLife {

	public static void breadth(Node root) {
		Queue<Node> childQ = new LinkedList<Node>();
		childQ.add(root);
		root.isMarked = true;
		while (!childQ.isEmpty()) {

			Node n = childQ.poll();

			for (Node child : n.children) {

				if(!child.isMarked) {
					childQ.add(child);
					child.isMarked = true;
				}
			}

		}
	}

	public static void depth(Node root) {
		
		root.isMarked = true;
		
		for(Node child: root.children) {
			
			if(!child.isMarked) {
				
				depth(child);
			}
			
		}
		
	}
}
