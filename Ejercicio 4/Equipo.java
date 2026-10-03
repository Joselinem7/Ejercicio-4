public abstract class Equipo {
    private String codigo;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private boolean disponible;

    public Equipo(String codigo, String marca, String modelo, double tarifaDiaria) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        if (marca == null || marca.trim().isEmpty() || modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca y el modelo son obligatorios.");
        }
        if (tarifaDiaria <= 0) {
            throw new IllegalArgumentException("La tarifa debe ser mayor que cero.");
        }
        this.codigo = codigo.trim();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    public String getCodigo() { return codigo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public double getTarifaDiaria() { return tarifaDiaria; }
    public boolean estaDisponible() { return disponible; }
    public void alquilar() { disponible = false; }
    public void devolver() { disponible = true; }

    public abstract double calcularCosto(int dias);
    public abstract String getCategoria();
    public abstract String getDetalle();

    public String toString() {
        return String.format("%s | %s | %s %s | tarifa Q%.2f/día | %s | %s",
                codigo, getCategoria(), marca, modelo, tarifaDiaria,
                disponible ? "Disponible" : "Alquilado", getDetalle());
    }
}
