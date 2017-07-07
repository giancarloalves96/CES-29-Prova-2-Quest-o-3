package exe03;

public class MVCPatternDemo {
	public static void main(String[] args) {

		// fetch student record based on his roll no from the database
		Student model = retriveStudentFromDatabase();

		Controller controller = ControllerFactory.getController(model);

		controller.updateView();

		// update model data
		controller.setName("John");

		controller.updateView();
		
		Professor model2 = retrieveProfessorFromDatabase();
		
		controller = ControllerFactory.getController(model2);
		
		controller.updateView();
		
		controller.setName("Steve");
		
		controller.setId("@82");
		
		controller.updateView();
	}

	private static Student retriveStudentFromDatabase() {
		Student student = new Student();
		student.setName("Robert");
		student.setRollNo("10");
		return student;
	}
	
	private static Professor retrieveProfessorFromDatabase() {
		Professor professor = new Professor();
		professor.setName("Stewart");
		professor.setId("90");
		return professor;
	}
}
