package entities;


public class Familia {
    
    private int id_familia;
    private int id_usuario;
    private int num_integrantes;
    private char estado;

    public Familia() {
        this.id_familia = 0;
        this.id_usuario = 0;
        this.num_integrantes = 0;
        this.estado = 'I';
    }

    public Familia(int id_familia, int id_usuario, int num_integrantes, char estado) {
        this.id_familia = id_familia;
        this.id_usuario = id_usuario;
        this.num_integrantes = num_integrantes;
        this.estado = estado;
    }

    public int getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getNum_integrantes() {
        return num_integrantes;
    }

    public void setNum_integrantes(int num_integrantes) {
        this.num_integrantes = num_integrantes;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "id_familia=" + id_familia + ", id_usuario=" + id_usuario + ", num_integrantes=" + num_integrantes + ", estado=" + estado;
    }
    
}
