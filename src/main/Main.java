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

		Random r = new Random();

//		rbTree.add(10, new Student(4, "0001", "Student 4"));
//		rbTree.add(85, new Student(44, "0103", "Student 2"));
//		rbTree.add(15, new Student(8, "0004", "Student 3"));
//		rbTree.add(70, new Student(22, "0002", "Student 1"));
//		rbTree.add(20, new Student(8, "0004", "Student 3"));
//		rbTree.add(60, new Student(8, "0004", "Student 3"));
//		rbTree.add(30, new Student(8, "0004", "Student 3"));
//		rbTree.add(50, new Student(8, "0004", "Student 3"));
//		rbTree.add(40, new Student(8, "0004", "Student 3"));

		rbTree.add(new Student(10, "0001", "Student 4"));
		rbTree.add(new Student(85, "0103", "Student 2"));
		rbTree.add(new Student(15, "0004", "Student 3"));
		rbTree.add(new Student(70, "0002", "Student 1"));
		rbTree.add(new Student(20, "0004", "Student 3"));
		rbTree.add(new Student(60, "0004", "Student 3"));
		rbTree.add(new Student(30, "0004", "Student 3"));
		rbTree.add(new Student(50, "0004", "Student 3"));
		rbTree.add(new Student(40, "0004", "Student 3"));

//		System.out.println(rbTree.get(5).getStudentId() + "\n\n");

//		rbTree.preOrderTravers();
//		System.out.println("\n");
		rbTree.inOrderTravers();
		
//		rbTree.fullSteck();
		
	}
	
	// @see as example http://alenacpp.blogspot.com/2010/07/blog-post_28.html
}
