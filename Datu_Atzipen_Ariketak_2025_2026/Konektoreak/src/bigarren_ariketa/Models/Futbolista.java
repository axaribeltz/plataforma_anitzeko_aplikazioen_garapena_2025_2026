package bigarren_ariketa.Models;

public class Futbolista {
    int dni;
    String nombre;
    String apellido;
    double salario;
    int idTaldea;

    public Futbolista(int dni, String nombre, String apellido, double salario, int idTaldea) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.idTaldea = idTaldea;
    }

    public int getDni() {
        return dni;
    }

        public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getIdTaldea() {
        return idTaldea;
    }

    public void setIdTaldea(int idTaldea) {
        this.idTaldea = idTaldea;
    }

    @Override
    public String toString() {
        return "Futbolista{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", salario=" + salario +
                ", idTaldea=" + idTaldea +
                '}';
    }
}
