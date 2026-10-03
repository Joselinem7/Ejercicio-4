public class EquipoSonido extends Equipo {
    private double potencia;

    public EquipoSonido(String codigo, String marca, String modelo, double tarifaDiaria, double potencia) {
        super(codigo, marca, modelo, tarifaDiaria);
        if (potencia <= 0) throw new IllegalArgumentException("La potencia debe ser mayor que cero.");
        this.potencia = potencia;
    }

    public double calcularCosto(int dias) {
        return (getTarifaDiaria() + 100 * potencia) * dias;
    }
    public String getCategoria() { return "Equipo de sonido"; }
    public String getDetalle() { return String.format("%.2f kW", potencia); }
}
