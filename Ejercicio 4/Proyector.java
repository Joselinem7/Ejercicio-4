public class Proyector extends Equipo {
    private int lumenes;
    private boolean inalambrico;

    public Proyector(String codigo, String marca, String modelo, double tarifaDiaria,
                     int lumenes, boolean inalambrico) {
        super(codigo, marca, modelo, tarifaDiaria);
        if (lumenes <= 0) throw new IllegalArgumentException("Los lúmenes deben ser mayores que cero.");
        this.lumenes = lumenes;
        this.inalambrico = inalambrico;
    }

    public double calcularCosto(int dias) {
        return (getTarifaDiaria() + (inalambrico ? 50 : 0)) * dias;
    }
    public String getCategoria() { return "Proyector"; }
    public String getDetalle() { return lumenes + " lúmenes, " + (inalambrico ? "inalámbrico" : "sin conectividad inalámbrica"); }
}
