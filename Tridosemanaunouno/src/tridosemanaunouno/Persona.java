package tridosemanaunouno;

public class Persona {
    String nombre;
    int edad;
    String dni;

    Persona(){
        nombre = "";
        edad = -1;
        dni = "";
    }
    Persona(String pNombre, int pEdad, String pDni){
        this.dni = pDni;
        this.edad = pEdad;
        this.dni = pDni;
    }
    void saludar(){
        System.out.println("Hola");
    }
}