package exe03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Controller {
	
	private Object model;
	private Object view;

	public Controller(Object model, Object view) {
		this.model = model;
		this.view = view;
	}
	
	private Object executeMethod(String methodName, Class<?>[] paramTypes, Object[] params){
		Class<?> c = model.getClass();
		Method method;
		try {
			method = c.getMethod(methodName, paramTypes);
			return method.invoke(model, params);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Método inexistente para o modelo.");
		} catch (SecurityException e) {
			throw new IllegalArgumentException("Método inacessível para o modelo.");
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Método inacessível para o modelo.");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Parâmetros inadequados para o método.");
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("Excessão obtida com o método.");
		}
	}

	public void setName(String name) {
		executeMethod("setName", new Class[]{String.class}, new Object[]{name});
	}

	public String getName() {
		return (String)executeMethod("getName", null, null);
	}

	public void setRollNo(String rollNo) {
		executeMethod("setRollNo", new Class[]{String.class}, new Object[]{rollNo});
	}

	public String getRollNo() {
		return (String)executeMethod("getRollNo", null, null);
	}
	
	public void setId(String id){
		executeMethod("setId", new Class[]{String.class}, new Object[]{id});
	}
	
	public String getId(){
		return (String)executeMethod("getId", null, null);
	}

	public void updateView() {
		Class<?> c = view.getClass();
		Method method;
		Object[] params = null;
		try {
			if(c.getName().equals("exe03.StudentView")){
				method = c.getMethod("printStudentDetails", new Class<?>[]{String.class, String.class});
				params = new Object[]{getName(),getRollNo()};
			}
			else if(c.getName().equals("exe03.ProfessorView")){
				method = c.getMethod("printProfessorDetails", new Class<?>[]{String.class, String.class});
				params = new Object[]{getName(),getId()};
			}
			else throw new IllegalArgumentException("View inadequada.");
		} catch (NoSuchMethodException | SecurityException e) {
			throw new IllegalArgumentException("Método indisponível para a view.");
		}
		
		try {
			method.invoke(view, params);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new IllegalArgumentException("Acesso ilegal ou exceção na atualização da "
					+ "view.");
		}
	}
}

