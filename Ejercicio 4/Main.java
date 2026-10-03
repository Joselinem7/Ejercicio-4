import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    private static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        cargarEjemplos();
        boolean salir = false;
        while (!salir) {
            System.out.println("\nSistema de alquiler de equipos");
            System.out.println("1. Registrar equipo");
            System.out.println("2. Ver inventario");
            System.out.println("3. Cotizar alquiler");
            System.out.println("4. Confirmar alquiler");
            System.out.println("5. Registrar devolución");
            System.out.println("6. Reporte general");
            System.out.println("0. Salir");
            int opcion = leerEntero("Seleccione el número de la opción: ");
            try {
                switch (opcion) {
                    case 1: registrarEquipo(); break;
                    case 2: mostrarInventario(); break;
                    case 3: procesarAlquiler(false); break;
                    case 4: procesarAlquiler(true); break;
                    case 5: System.out.println(inventario.devolver(leerTexto("Código: "))); break;
                    case 6: mostrarReporte(); break;
                    case 0: salir = true; break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar: " + e.getMessage());
            }
        }
        System.out.println("Programa finalizado.");
    }

    private static void cargarEjemplos() {
        inventario.registrar(new Proyector("P101", "Epson", "X1", 200, 3500, true));
        inventario.registrar(new Proyector("P102", "BenQ", "MS560", 180, 4000, false));
        inventario.registrar(new Camara("C201", "Canon", "R50", 250, 2160));
        inventario.registrar(new Camara("C202", "Sony", "ZV1", 190, 1080));
        inventario.registrar(new EquipoSonido("S301", "JBL", "EON", 200, 1.5));
        inventario.registrar(new EquipoSonido("S302", "Yamaha", "Stage", 150, 0.8));
    }

    private static void registrarEquipo() {
        int tipo = leerEntero("Tipo (1 Proyector, 2 Cámara, 3 Sonido): ");
        String codigo = leerTexto("Código: ");
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        double tarifa = leerDecimal("Tarifa diaria: Q");
        Equipo equipo;
        if (tipo == 1) {
            int lumenes = leerEntero("Lúmenes: ");
            boolean inalambrico = leerSiNo("¿Tiene conectividad inalámbrica? (s/n): ");
            equipo = new Proyector(codigo, marca, modelo, tarifa, lumenes, inalambrico);
        } else if (tipo == 2) {
            equipo = new Camara(codigo, marca, modelo, tarifa, leerEntero("Resolución vertical (p): "));
        } else if (tipo == 3) {
            equipo = new EquipoSonido(codigo, marca, modelo, tarifa, leerDecimal("Potencia (kW): "));
        } else {
            System.out.println("Tipo no válido.");
            return;
        }
        if (inventario.registrar(equipo)) System.out.println("Equipo registrado.");
        else System.out.println("Ese código ya existe; no se registró el equipo.");
    }

    private static void mostrarInventario() {
        if (inventario.getEquipos().isEmpty()) System.out.println("No hay equipos registrados.");
        for (Equipo equipo : inventario.getEquipos()) System.out.println(equipo);
    }

    private static void procesarAlquiler(boolean confirmar) {
        String mensajeCodigo = "Código del equipo";
        if (!confirmar) {
            StringBuilder codigos = new StringBuilder();
            for (Equipo equipo : inventario.getEquipos()) {
                if (codigos.length() > 0) codigos.append(", ");
                codigos.append(equipo.getCodigo());
            }
            mensajeCodigo += " (" + codigos + ")";
        }
        String codigo = leerTexto(mensajeCodigo + ": ");
        int dias = leerEntero("Días de alquiler: ");
        if (confirmar) {
            Equipo equipo = inventario.buscar(codigo);
            if (equipo != null && equipo.estaDisponible()) {
                System.out.println(String.format("Total antes de confirmar: Q%.2f", equipo.calcularCosto(dias)));
                if (!leerSiNo("¿Confirma el alquiler? (s/n): ")) {
                    System.out.println("Alquiler cancelado. No se modificó el inventario ni los ingresos.");
                    return;
                }
            }
        }
        System.out.println(inventario.alquilar(codigo, dias, confirmar));
    }

    private static void mostrarReporte() {
        String[] categorias = {"Todos", "Proyector", "Cámara", "Equipo de sonido"};
        for (String categoria : categorias) {
            System.out.println(categoria + ": " + inventario.contar(categoria, 0) + " total, "
                    + inventario.contar(categoria, 1) + " disponible(s), "
                    + inventario.contar(categoria, 2) + " alquilado(s).");
        }
        System.out.println(String.format("Ingresos acumulados: Q%.2f", inventario.getIngresos()));
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try { return Integer.parseInt(leerTexto(mensaje)); }
            catch (NumberFormatException e) { System.out.println("Ingrese un número entero válido."); }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            try { return Double.parseDouble(leerTexto(mensaje).replace(',', '.')); }
            catch (NumberFormatException e) { System.out.println("Ingrese un número válido."); }
        }
    }

    private static boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje);
            if (respuesta.equalsIgnoreCase("s")) return true;
            if (respuesta.equalsIgnoreCase("n")) return false;
            System.out.println("Responda s o n.");
        }
    }
}
