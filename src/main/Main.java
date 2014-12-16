package main;

import objects.Student;
import util.RBTree;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RBTree<Student> rbTree = new RBTree<Student>(Student.comparatorById);

		rbTree.add(10, new Student(4, "0001", "Student 4"));
		rbTree.add(85, new Student(44, "0103", "Student 2"));
		rbTree.add(15, new Student(8, "0004", "Student 3"));
		rbTree.add(70, new Student(22, "0002", "Student 1"));
		rbTree.add(20, new Student(8, "0004", "Student 3"));
		rbTree.add(60, new Student(8, "0004", "Student 3"));
		rbTree.add(30, new Student(8, "0004", "Student 3"));
		rbTree.add(50, new Student(8, "0004", "Student 3"));
		rbTree.add(40, new Student(8, "0004", "Student 3"));
//
////		for (int index = 0; index < rbTree.size() ; ++index) {
////			System.out.println(rbTree.get(index).getStudentId() + " "
////					+ rbTree.get(index).getStudentGroup());
////		}
//
//		 TreeSet<Integer> treeSet = new TreeSet<Integer>();
//		
//		 treeSet.add(0);
//		 treeSet.add(2);
//		 treeSet.add(5);
//		 treeSet.add(6);
//		 treeSet.add(22);
		int i=0;
		++i;
		
//		rbTree.preOrderTravers();
//		System.out.println("\n");
		rbTree.inOrderTravers();
		
//		rbTree.fullSteck();
		
	}
	
	// @see as example http://alenacpp.blogspot.com/2010/07/blog-post_28.html
}
