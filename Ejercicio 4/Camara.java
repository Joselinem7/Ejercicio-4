public class Camara extends Equipo {
    private int resolucion;

    public Camara(String codigo, String marca, String modelo, double tarifaDiaria, int resolucion) {
        super(codigo, marca, modelo, tarifaDiaria);
        if (resolucion <= 0) throw new IllegalArgumentException("La resolución debe ser mayor que cero.");
        this.resolucion = resolucion;
    }

    public double calcularCosto(int dias) {
        return getTarifaDiaria() * dias + (resolucion > 1080 ? 75 : 0);
    }
    public String getCategoria() { return "Cámara"; }
    public String getDetalle() { return resolucion + "p"; }
}
