package uo.ri.ui.admin.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		// Pedir datos
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		new AdminServiceImpl().newMechanic(nombre, apellidos);

		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
