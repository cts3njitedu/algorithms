package com.cracking.interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Solution {
	public void solveSudoku(char[][] board) {
		Map<Character, Set<Character>> rows = new HashMap<Character, Set<Character>>();
		Map<Character, Set<Character>> columns = new HashMap<Character, Set<Character>>();
		Map<Character, Map<Character, Set<Character>>> sections = new HashMap<Character, Map<Character, Set<Character>>>();

		int nsqrt = (int) Math.sqrt(board.length);

		for (int i = 0; i < board.length; i++) {
			Map<Character, Set<Character>> section = sections.get((char) ((i / nsqrt) + '0'));
			if (section == null) {
				section = new HashMap<Character, Set<Character>>();
				sections.put((char) ((i / nsqrt) + '0'), section);
			}
			for (int j = 0; j < board.length; j++) {
				Set<Character> row = rows.get((char) (i + '0'));
				Set<Character> subSection = section.get(((char) ((j / nsqrt) + '0')));
				if (subSection == null) {
					subSection = new HashSet<Character>();
					section.put((char) ((j / nsqrt) + '0'), subSection);
				}
				if (row == null) {
					row = new HashSet<Character>();
					rows.put((char) (i + '0'), row);
				}
				if (board[i][j] != '.') {
					row.add(board[i][j]);
					subSection.add(board[i][j]);
				}
				Set<Character> column = columns.get((char) (i + '0'));
				if (column == null) {
					column = new HashSet<Character>();
					columns.put((char) (i + '0'), column);
				}
				if (board[j][i] != '.') {
					column.add(board[j][i]);
				}

			}
		}

		solveSudoku(0, 0, board, rows, columns, sections);
	}

	public boolean solveSudoku(int r, int c, char[][] board, Map<Character, Set<Character>> rows,
			Map<Character, Set<Character>> columns, Map<Character, Map<Character, Set<Character>>> sections) {

		if (r < board.length) {

			if (board[r][c] == '.') {
				int nsqrt = (int) Math.sqrt(board.length);
				int r_old = r;
				int c_old = c;
				for (int i = 1; i <= board.length; i++) {

					if (!rows.get((char) (r + '0')).contains((char) (i + '0'))
							&& !columns.get((char) (c + '0')).contains((char) (i + '0'))
							&& !sections.get((char) ((r / nsqrt) + '0')).get((char) ((c / nsqrt) + '0'))
									.contains((char) (i + '0'))) {
						rows.get((char) (r + '0')).add((char) (i + '0'));
						columns.get((char) (c + '0')).add((char) (i + '0'));
						sections.get((char) ((r / nsqrt) + '0')).get((char) ((c / nsqrt) + '0')).add((char) (i + '0'));
						board[r][c] = (char) (i + '0');

						c++;
						if (c == board.length) {
							c = 0;
							r++;
						}
						boolean isValid = solveSudoku(r, c, board, rows, columns, sections);
						if (isValid) {
							return true;
						}
						r = r_old;
						c = c_old;
						rows.get((char) (r + '0')).remove((char) (i + '0'));
						columns.get((char) (c + '0')).remove((char) (i + '0'));
						sections.get((char) ((r / nsqrt) + '0')).get((char) ((c / nsqrt) + '0'))
								.remove((char) (i + '0'));
						board[r][c] = '.';

					}
				}

				return false;
			} else {
				c++;
				if (c == board.length) {
					c = 0;
					r++;
				}
				return solveSudoku(r, c, board, rows, columns, sections);
			}

		} else {
			System.out.println("skdkd");
			return true;
		}

	}

	public void printStar(int n) {

		StringBuilder b = new StringBuilder("Hi");
		int count = 0;
		do {
			System.out.println(b.toString());
			b.append("!");
			count++;

		} while (count <= n);
	}

	public boolean isSymmetric(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<Integer> queuePos = new LinkedList<>();
		Queue<Integer> queueCount = new LinkedList<>();
		Map<Integer,Integer> row = new HashMap<>();
		if(root==null) {
			return true;
		}
		int count = 0;
	
		int maxCount = 1;
		queue.add(root);
		queuePos.add(count);
		queueCount.add(maxCount);
		row.put(count, root.val);
		while(!queue.isEmpty()) {
			TreeNode n = queue.poll();
			count = queuePos.poll();
			maxCount = queueCount.poll();
			if(count<(maxCount/2)) {
				row.put(count,n.val);
			}
			else {
				Integer value = row.get(maxCount-1-count);
				row.remove(maxCount-1-count);
				if(value==null) {
					return false;
				}
				else if(value!=n.val) {
					return false;
				}
			}
			if(n.left!=null) {
				queue.add(n.left);
				queuePos.add(2*count);
				queueCount.add(2*maxCount);
			}
			if(n.right!=null){
				queue.add(n.right);
				queuePos.add(2*count+1);
				queueCount.add(2*maxCount);
			}
		
		
		}
		
		return (row.isEmpty());
	}

}
