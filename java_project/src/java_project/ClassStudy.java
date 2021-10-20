package java_project;

public class ClassStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student[] student = new Student[5];
		

		
		for(int i=0; i<5; i++) {
			student[i] = new Student();
			student[i].setnumber(i);
			System.out.println(student[i].getnumber());
		}
		
		
		
			
			
			
	}

}


class Student{
	
	private String number;
	
	public Student() {
		this.number = "미지정번호";
	}
	
	public String getnumber() {
		return this.number;	
	}
	
	public void setnumber(int number) {
		this.number = number+"번";
		
	}
	
	
}