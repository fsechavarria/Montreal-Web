package entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Programa_Estudio {
    private int id_programa;
    private int id_cem;
    private int id_cel;
    private String nomb_programa;
    private String desc_programa;
    private Date fech_inicio;
    private Date fech_termino;
    private int cant_min_alumnos;
    private int cant_max_alumnos;

    public Programa_Estudio() {
        this.id_programa = 0;
        this.id_cem = 0;
        this.id_cel = 0;
        this.nomb_programa = "";
        this.desc_programa = "";
        this.fech_inicio = new Date();
        this.fech_termino = new Date();
        this.cant_min_alumnos = 0;
        this.cant_max_alumnos = 0;
    }

    public Programa_Estudio(int id_programa, int id_cem, int id_cel, String nomb_programa, String desc_programa, String fech_inicio, String fech_termino, int cant_min_alumnos, int cant_max_alumnos) throws ParseException {
        this.id_programa = id_programa;
        this.id_cem = id_cem;
        this.id_cel = id_cel;
        this.nomb_programa = nomb_programa;
        this.desc_programa = desc_programa;
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.fech_inicio = format.parse(fech_inicio);
        this.fech_termino = format.parse(fech_termino);
        
        this.cant_min_alumnos = cant_min_alumnos;
        this.cant_max_alumnos = cant_max_alumnos;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public int getId_cem() {
        return id_cem;
    }

    public void setId_cem(int id_cem) {
        this.id_cem = id_cem;
    }

    public int getId_cel() {
        return id_cel;
    }

    public void setId_cel(int id_cel) {
        this.id_cel = id_cel;
    }

    public String getNomb_programa() {
        return nomb_programa;
    }

    public void setNomb_programa(String nomb_programa) {
        this.nomb_programa = nomb_programa;
    }

    public String getDesc_programa() {
        return desc_programa;
    }

    public void setDesc_programa(String desc_programa) {
        this.desc_programa = desc_programa;
    }

    public Date getFech_inicio() {
        return fech_inicio;
    }

    public void setFech_inicio(String fech_inicio) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.fech_inicio = format.parse(fech_inicio);
    }

    public Date getFech_termino() {
        return fech_termino;
    }

    public void setFech_termino(String fech_termino) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.fech_termino = format.parse(fech_termino);
    }

    public int getCant_min_alumnos() {
        return cant_min_alumnos;
    }

    public void setCant_min_alumnos(int cant_min_alumnos) {
        this.cant_min_alumnos = cant_min_alumnos;
    }

    public int getCant_max_alumnos() {
        return cant_max_alumnos;
    }

    public void setCant_max_alumnos(int cant_max_alumnos) {
        this.cant_max_alumnos = cant_max_alumnos;
    }

    @Override
    public String toString() {
        return "id_programa=" + id_programa + ", id_cem=" + id_cem + ", id_cel=" + id_cel + ", nomb_programa=" + nomb_programa + ", desc_programa=" + desc_programa + ", fech_inicio=" + fech_inicio + ", fech_termino=" + fech_termino + ", cant_min_alumnos=" + cant_min_alumnos + ", cant_max_alumnos=" + cant_max_alumnos;
    }
    
    
}
