# EnEscena - Ejercicio 4

Programa de consola en Java para administrar proyectores, cámaras y equipos de sonido.

## Requisitos

- Java JDK 8 o superior.

## Compilar y ejecutar

Desde la carpeta del proyecto:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

El programa inicia con dos equipos de cada categoría. La información vive mientras el programa está abierto y se reinicia al cerrarlo.

## Archivos

- `src/`: clases Java.
- `documentacion/`: análisis, diagrama de clases y evidencia de pruebas.
- `outputs/ejercicio-4-entrega.pdf`: documento listo para revisar y entregar.

## Reglas de cobro

- Proyector inalámbrico: Q50 adicionales por día.
- Cámara con resolución mayor a 1080p: Q75 por alquiler.
- Sonido: Q100 por kW y por día.
- La tarifa diaria se cobra por cada día solicitado.

Las cotizaciones no registran ingresos. Solo una confirmación exitosa suma el total; la devolución vuelve a habilitar el equipo y no cambia los ingresos.
