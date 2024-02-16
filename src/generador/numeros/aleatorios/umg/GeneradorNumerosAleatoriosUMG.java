//7690-21-218 Edrei Girón
package generador.numeros.aleatorios.umg;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GeneradorNumerosAleatoriosUMG {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n---------- GENERADOR NUMEROS ALEATORIOS UMG ----------");
            System.out.println("1. Generar números aleatorios");
            System.out.println("2. Ordenar números aleatorios");
            System.out.println("3. Salir");
            System.out.println("\nSeleccione una opción:");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    generarYGuardarNumerosAleatorios();
                    break;
                case 2:
                    ordenarYGuardarNumerosAleatorios();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpción no válida");
            }
        }
        System.out.println("\n\n\n\n\nPrograma finalizado.");
    }

    private static void generarYGuardarNumerosAleatorios() {
        Random random = new Random();
        ArrayList<Long> numerosAleatorios = new ArrayList<>();

        // Generar un millón de números aleatorios en el rango de -10,000,000 a 10,000,000 y almacenarlos en la lista
        for (int i = 0; i < 1000000; i++) {
            long rango = 10000000L * 2; // Rango total
            long minimo = -10000000L;   // Valor mínimo
            long numeroAleatorio = (long) (random.nextDouble() * rango) + minimo;
            numerosAleatorios.add(numeroAleatorio);
        }

        // Escribir los números aleatorios en un archivo de texto
        escribirNumerosEnArchivo(numerosAleatorios, "Numeros Aleatorios.txt");
        System.out.println("\nNúmeros aleatorios generados y guardados en el archivo 'Numeros Aleatorios.txt'");
    }

    private static void ordenarYGuardarNumerosAleatorios() {
        
        // Leer números aleatorios desde el archivo
        ArrayList<Long> numerosAleatorios = leerNumerosDesdeArchivo("Numeros Aleatorios.txt");
        
        // Ordenar la lista de números usando Quick Sort
        quickSort(numerosAleatorios, 0, numerosAleatorios.size() - 1);
        
        // Escribir los números ordenados en otro archivo de texto
        escribirNumerosEnArchivo(numerosAleatorios, "Numeros Aleatorios Ordenados.txt");
        System.out.println("\nNúmeros aleatorios ordenados y guardados en el archivo 'Numeros Aleatorios Ordenados.txt'");
    }

    //Implementacion para escribir los numeros en el archivo de texto.
    private static void escribirNumerosEnArchivo(ArrayList<Long> numeroAleatorio, String nombreArchivo) {
        try (PrintWriter escribir = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Long number : numeroAleatorio) {
                escribir.println(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Implementacion para leer los numeros del archivo (Nos sirve al momento de ordenar).
    private static ArrayList<Long> leerNumerosDesdeArchivo(String nombreArchivo) {
        ArrayList<Long> numerosAleatorios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numerosAleatorios.add(Long.parseLong(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numerosAleatorios;
    }


    // Implementación de Quick Sort
    private static void quickSort(ArrayList<Long> arr, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particionarArray(arr, bajo, alto);
            quickSort(arr, bajo, pi - 1);
            quickSort(arr, pi + 1, alto);
        }
    }
       
    //Particionamiento del Array
    private static int particionarArray(ArrayList<Long> arr, int bajo, int alto) {
        long pivote = arr.get(alto);
        int i = (bajo - 1); 
        for (int j = bajo; j < alto; j++) {
            if (arr.get(j) <= pivote) {
                i++;
                long temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        long temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(alto));
        arr.set(alto, temp);

        return i + 1;
    }
}
