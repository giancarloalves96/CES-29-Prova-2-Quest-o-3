package test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exe03.Controller;
import exe03.ControllerFactory;
import exe03.Professor;
import exe03.Student;

public class ControllerTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testModeloNuloParaFactory() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Modelo não pode ser nulo.");
		
		ControllerFactory.getController(null);
	}
	
	@Test
	public void testModeloInvalidoParaFactory() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Tipo do modelo passado deve ser Student ou Professor.");
		
		ControllerFactory.getController(new String("Isso não está certo."));
	}
	
	@Test
	public void testIdInexistenteParaStudent(){
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Método inexistente para o modelo.");
		
		Student student = new Student();
		
		Controller controller = ControllerFactory.getController(student);
		controller.getId();
	}
	
	@Test
	public void testRollNoInexistenteParaProfessor(){
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Método inexistente para o modelo.");
		
		Professor professor = new Professor();
		
		Controller controller = ControllerFactory.getController(professor);
		controller.getRollNo();
	}
	
	@Test
	public void testMetodosStudent(){
		Student student = new Student();
		student.setName("Rodrigo");
		student.setRollNo("89");
		
		Controller controller = ControllerFactory.getController(student);
		assertEquals("89", controller.getRollNo());
		assertEquals("Rodrigo", controller.getName());
		
		controller.setName("Ronaldo");
		assertEquals("Ronaldo", controller.getName());
		
		controller.setRollNo("91");
		assertEquals("91", controller.getRollNo());
	}
	
	@Test
	public void testMetodosProfessor(){
		Professor professor = new Professor();
		professor.setName("Stewart");
		professor.setId("P-67");
		
		Controller controller = ControllerFactory.getController(professor);
		assertEquals("Stewart", controller.getName());
		assertEquals("P-67", controller.getId());
		
		controller.setName("Eduardo");
		assertEquals("Eduardo", controller.getName());
		
		controller.setId("@PCR-8912");
		assertEquals("@PCR-8912", controller.getId());
	}
	
	@Test
	public void testMudarControllerEmTempoDeExecucao(){
		Student student = new Student();
		student.setName("Maria");
		student.setRollNo("67");
		
		Professor professor = new Professor();
		professor.setName("Robertson");
		professor.setId("P-78");
		
		Controller controller;
		controller = ControllerFactory.getController(student);
		controller.setRollNo("12");
		assertEquals("12", controller.getRollNo());
		
		controller = ControllerFactory.getController(professor);
		controller.setName("Steve");
		assertEquals("Steve", controller.getName());
		assertEquals("Maria", student.getName());
	}
}
