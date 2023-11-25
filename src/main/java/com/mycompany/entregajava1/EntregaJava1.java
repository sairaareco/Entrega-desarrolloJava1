package com.mycompany.entregajava1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EntregaJava1 {

    private static final List<Persona> personasRegistradas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarPersona();
                    break;
                case 2:
                    listarPersonas();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuveo.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("1. Agregar persona");
        System.out.println("2. Ver personas");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void agregarPersona() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Ingrese fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNacimientoStr = scanner.nextLine();

        // Validación:
        
        try {
            validarDatos(nombre, apellido, dni, fechaNacimientoStr);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);

            Persona persona = new Persona(nombre, apellido, dni, fechaNacimiento);
            personasRegistradas.add(persona);

            System.out.println("Persona agregada correctamente.");
        } catch (IllegalArgumentException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void validarDatos(String nombre, String apellido, String dni, String fechaNacimientoStr) {
        
        if (nombre.isEmpty() || apellido.isEmpty()) {
            throw new IllegalArgumentException("El nombre y el apellido no pueden estar en blanco.");
        }
        
        if (!dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe ser un número válido de 8 dígitos.");
        }

        SimpleDateFormat parserFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaNacimiento = parserFecha.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("La fecha de nacimiento debe ser una fecha válida en el formato dd/mm/yyyy.");
        }
    }

    private static void listarPersonas() {
        if (personasRegistradas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            System.out.println("Personas registradas:");
            for (Persona persona : personasRegistradas) {
                System.out.println(persona);
            }
        }
    }

    private static class Persona {
        private String nombre;
        private String apellido;
        private String dni;
        private Date fechaNacimiento;

        public Persona(String nombre, String apellido, String dni, Date fechaNacimiento) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.dni = dni;
            this.fechaNacimiento = fechaNacimiento;
        }

        @Override
        public String toString() {
            return "Persona { " +
                    "nombre ='" + nombre + '\'' +
                    ", apellido ='" + apellido + '\'' +
                    ", dni ='" + dni + '\'' +
                    ", fechaNacimiento =" + fechaNacimiento +
                    " }";
        }
    }
}
