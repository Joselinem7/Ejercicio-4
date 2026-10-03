import java.util.ArrayList;

public class Inventario {
    private ArrayList<Equipo> equipos = new ArrayList<Equipo>();
    private double ingresos;

    public boolean registrar(Equipo equipo) {
        if (buscar(equipo.getCodigo()) != null) return false;
        equipos.add(equipo);
        return true;
    }

    public Equipo buscar(String codigo) {
        for (Equipo equipo : equipos) {
            if (equipo.getCodigo().equalsIgnoreCase(codigo.trim())) return equipo;
        }
        return null;
    }

    public ArrayList<Equipo> getEquipos() { return equipos; }
    public double getIngresos() { return ingresos; }

    public String alquilar(String codigo, int dias, boolean confirmar) {
        Equipo equipo = buscar(codigo);
        if (equipo == null) return "No existe un equipo con ese código.";
        if (dias <= 0) return "Los días deben ser un entero positivo.";
        double total = equipo.calcularCosto(dias);
        String mensaje = String.format("Cotización para %s (%s): %d día(s), total Q%.2f. %s",
                equipo.getCodigo(), equipo.getCategoria(), dias, total,
                equipo.estaDisponible() ? "Disponible." : "Actualmente alquilado.");
        if (!confirmar) return mensaje + " No se registró el alquiler.";
        if (!equipo.estaDisponible()) return "El equipo está ocupado. No se registró el alquiler.";
        equipo.alquilar();
        ingresos += total;
        return mensaje + " Alquiler confirmado.";
    }

    public String devolver(String codigo) {
        Equipo equipo = buscar(codigo);
        if (equipo == null) return "No existe un equipo con ese código.";
        if (equipo.estaDisponible()) return "El equipo ya estaba disponible; no se registró devolución.";
        equipo.devolver();
        return "Devolución registrada para " + equipo.getCodigo() + ".";
    }

    public int contar(String categoria, int estado) {
        int cantidad = 0;
        for (Equipo equipo : equipos) {
            boolean coincide = categoria.equals("Todos") || equipo.getCategoria().equals(categoria);
            if (coincide && (estado == 0 || (estado == 1 && equipo.estaDisponible()) || (estado == 2 && !equipo.estaDisponible()))) cantidad++;
        }
        return cantidad;
    }
}
