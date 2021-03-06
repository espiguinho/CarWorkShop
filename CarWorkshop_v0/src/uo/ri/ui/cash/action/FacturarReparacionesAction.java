package uo.ri.ui.cash.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.business.impl.cash.CreateInvoiceFor;
import uo.ri.business.impl.CashServiceImpl;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import alb.util.menu.Action;

public class FacturarReparacionesAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();

		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while (masAverias());
		Map<String, Object> map = new CashServiceImpl().createInvoiceFor(idsAveria);
		mostrarFactura((long) map.get("num"), (Date) map.get("fecha"), (double) map.get("totalF"),
				(double) map.get("iva"), (double) map.get("importe"));
	}

	private void mostrarFactura(long numeroFactura, Date fechaFactura, double totalFactura, double iva,
			double totalConIva) {

		Console.printf("Factura nº: %d\n", numeroFactura);
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", fechaFactura);
		Console.printf("\tTotal: %.2f €\n", totalFactura);
		Console.printf("\tIva: %.1f %% \n", iva);
		Console.printf("\tTotal con IVA: %.2f €\n", totalConIva);
	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}
