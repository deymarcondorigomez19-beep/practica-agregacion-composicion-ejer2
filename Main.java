import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Pantalla {
    private String tipo;
    private int ancho;
    private int alto;

    public Pantalla(String tipo, int ancho, int alto) {
        this.tipo = tipo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    @Override
    public String toString() {
        return tipo + " " + ancho + " " + alto;
    }
}

class Aplicacion {
    private String nombre;
    private int tamanio;

    public Aplicacion(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTamanio() {
        return tamanio;
    }

    @Override
    public String toString() {
        return nombre + " " + tamanio;
    }
}

class Laptop {
    private String modelo;
    private int cantApps;
    private ArrayList<Aplicacion> apps;
    private Pantalla pantalla;

    public Laptop(String modelo, int cantApps, String tipoPantalla, int anchoPantalla, int altoPantalla) {
        this.modelo = modelo;
        this.cantApps = cantApps;
        this.pantalla = new Pantalla(tipoPantalla, anchoPantalla, altoPantalla);
        this.apps = new ArrayList<>();
        for (int i = 0; i < cantApps; i++) {
            apps.add(new Aplicacion("App" + (i + 1), (int)(Math.random() * 50) + 1));
        }
    }

    public String getModelo() {
        return modelo;
    }

    public int getCantApp() {
        return cantApps;
    }

    public Pantalla getPantalla() {
        return pantalla;
    }

    public ArrayList<Aplicacion> getApps() {
        return apps;
    }

    public void agregaApp(Aplicacion app) {
        this.apps.add(app);
        this.cantApps = this.apps.size();
    }

    public void eliminarTodasApps() {
        this.apps.clear();
        this.cantApps = 0;
    }

    public void ordenarApps() {
        Collections.sort(apps, new Comparator<Aplicacion>() {
            @Override
            public int compare(Aplicacion a1, Aplicacion a2) {
                if (a2.getTamanio() != a1.getTamanio()) {
                    return Integer.compare(a2.getTamanio(), a1.getTamanio());
                } else {
                    return a1.getNombre().compareTo(a2.getNombre());
                }
            }
        });
    }

    @Override
    public String toString() {
        return modelo + " " + cantApps + " " + pantalla + " " + apps;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner te = new Scanner(System.in);
        ArrayList<Laptop> vec = new ArrayList<>();

        System.out.print("Ingrese N: ");
        int n = te.nextInt();
        te.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Modelo: ");
            String modelo = te.nextLine();
            System.out.print("Cantidad apps: ");
            int cant = te.nextInt();
            te.nextLine();
            System.out.print("Tipo pantalla: ");
            String tipoP = te.nextLine();
            System.out.print("Ancho pantalla: ");
            int anchoP = te.nextInt();
            System.out.print("Alto pantalla: ");
            int altoP = te.nextInt();
            te.nextLine();

            Laptop lap = new Laptop(modelo, cant, tipoP, anchoP, altoP);
            vec.add(lap);
        }

        if (!vec.isEmpty()) {
            int may = vec.get(0).getCantApp();
            for (Laptop l : vec) {
                if (l.getCantApp() > may) {
                    may = l.getCantApp();
                }
            }
            System.out.println("Laptop con mas aplicaciones:");
            for (Laptop l : vec) {
                if (l.getCantApp() == may) {
                    System.out.println(l);
                }
            }
        }

        System.out.print("Modelo X: ");
        String modeloX = te.nextLine();
        System.out.print("Nombre app: ");
        String nomApp = te.nextLine();
        System.out.print("Tamano app: ");
        int tamApp = te.nextInt();
        te.nextLine();

        for (Laptop l : vec) {
            if (l.getModelo().equals(modeloX)) {
                l.agregaApp(new Aplicacion(nomApp, tamApp));
            }
        }

        System.out.print("Ancho Y: ");
        int anchoY = te.nextInt();
        te.nextLine();

        for (Laptop l : vec) {
            if (l.getPantalla().getAncho() == anchoY) {
                l.eliminarTodasApps();
            }
        }

        System.out.println("Laptops con apps ordenadas:");
        for (Laptop l : vec) {
            l.ordenarApps();
            System.out.println(l);
        }
        
        te.close();
    }
}