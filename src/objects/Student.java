package objects;

import java.util.Comparator;

public class Student{
	private int studentId;
	private String studentGroup;
	private String studentName;
	
	public Student(int studentId, String studentGroup, String studentName)
	  {
	    this.studentId = studentId;
	    this.studentGroup = studentGroup;
	    this.studentName = studentName;  
	  }
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentGroup() {
		return studentGroup;
	}
	public void setStudentGroup(String studentGroup) {
		this.studentGroup = studentGroup;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public static Comparator<Object> comparatorById = new Comparator<Object>() {
		
		@Override
		public int compare(Object o1, Object o2) {
			int id1 = ((Student)o1).getStudentId();
			int id2 = ((Student)o2).getStudentId();
			return id1 - id2;
		}
	};
	
	public static Comparator<Object> comparatorByGroup = new Comparator<Object>() {
		
		@Override
		public int compare(Object o1, Object o2) {
			String group1 = ((Student)o1).getStudentGroup();
			String group2 = ((Student)o2).getStudentGroup();
			return group1.compareTo(group2);
		}
	};
	
}
