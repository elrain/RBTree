package main;

import objects.Student;
import util.RBTree;

import java.util.Random;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RBTree<Student> rbTree = new RBTree<Student>();

		rbTree.add(new Student(10, "0001", "Student 4"));
		rbTree.add(new Student(85, "0103", "Student 2"));
		rbTree.add(new Student(15, "0004", "Student 3"));
		rbTree.add(new Student(70, "0002", "Student 1"));
		rbTree.add(new Student(20, "0004", "Student 3"));
		rbTree.add(new Student(60, "0004", "Student 3"));
		rbTree.add(new Student(30, "0004", "Student 3"));
		rbTree.add(new Student(50, "0004", "Student 3"));
		rbTree.add(new Student(40, "0004", "Student 3"));

//		System.out.println(rbTree.get(4).getStudentId() + "\n\n");

		RBTree<Integer> rbTree1 = new RBTree<Integer>();
		rbTree1.add(13);
		rbTree1.add(27);
		rbTree1.add(1);
		rbTree1.add(15);
		rbTree1.add(17);
		rbTree1.add(25);
		rbTree1.add(8);
		rbTree1.add(6);
		rbTree1.add(27);
		rbTree1.add(11);

//		rbTree.preOrderTravers();
//		System.out.println("\n");
//		rbTree1.inOrderTravers();
		System.out.println("\n" + rbTree.fullSteck());
		
	}
	
	// @see as example http://alenacpp.blogspot.com/2010/07/blog-post_28.html
}
