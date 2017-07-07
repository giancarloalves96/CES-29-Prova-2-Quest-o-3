package exe03;

public class ControllerFactory {

	public static Controller getController(Object model) throws IllegalArgumentException {
		if(model==null)
			throw new IllegalArgumentException("Modelo não pode ser nulo.");
		Class<?> c = model.getClass();
		if(c.getName().equals("exe03.Student"))
			return new Controller(model, new StudentView());
		if(c.getName().equals("exe03.Professor"))
			return new Controller(model, new ProfessorView());
		throw new IllegalArgumentException("Tipo do modelo passado deve ser Student ou Professor.");
	}
}
