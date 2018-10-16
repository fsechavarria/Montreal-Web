package entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Postulacion {
    private int id_postulacion;
    private int id_alumno;
    private int id_familia;
    private int id_seguro;
    private int id_programa;
    private Date fech_postulacion;
    private Date fech_respuesta;
    private char estado;
    private int reserva_dinero_pasajes;

    public Postulacion() {
        this.id_postulacion = 0;
        this.id_alumno = 0;
        this.id_familia = 0;
        this.id_seguro = 0;
        this.id_programa = 0;
        this.fech_postulacion = new Date();
        this.fech_respuesta = new Date();
        this.estado = 'P';
        this.reserva_dinero_pasajes = 0;
    }

    public Postulacion(int id_postulacion, int id_alumno, int id_familia, int id_seguro, int id_programa, String fech_postulacion, String fech_respuesta, char estado, int reserva_dinero_pasajes) throws ParseException {
        this.id_postulacion = id_postulacion;
        this.id_alumno = id_alumno;
        this.id_familia = id_familia;
        this.id_seguro = id_seguro;
        this.id_programa = id_programa;
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.fech_postulacion = format.parse(fech_postulacion);
        this.fech_respuesta = format.parse(fech_respuesta);
        
        this.estado = estado;
        this.reserva_dinero_pasajes = reserva_dinero_pasajes;
    }

    public int getId_postulacion() {
        return id_postulacion;
    }

    public void setId_postulacion(int id_postulacion) {
        this.id_postulacion = id_postulacion;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    public int getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(int id_seguro) {
        this.id_seguro = id_seguro;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public Date getFech_postulacion() {
        return fech_postulacion;
    }

    public void setFech_postulacion(String fech_postulacion) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.fech_postulacion = format.parse(fech_postulacion);
    }

    public Date getFech_respuesta() {
        return fech_respuesta;
    }

    public void setFech_respuesta(String fech_respuesta) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.fech_respuesta = format.parse(fech_respuesta);
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public int getReserva_dinero_pasajes() {
        return reserva_dinero_pasajes;
    }

    public void setReserva_dinero_pasajes(int reserva_dinero_pasajes) {
        this.reserva_dinero_pasajes = reserva_dinero_pasajes;
    }

    @Override
    public String toString() {
        return "id_postulacion=" + id_postulacion + ", id_alumno=" + id_alumno + ", id_familia=" + id_familia + ", id_seguro=" + id_seguro + ", id_programa=" + id_programa + ", fech_postulacion=" + fech_postulacion + ", fech_respuesta=" + fech_respuesta + ", estado=" + estado + ", reserva_dinero_pasajes=" + reserva_dinero_pasajes;
    }
   
}
