package entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Postulacion {
    private Integer id_postulacion;
    private Integer id_alumno;
    private Integer id_familia;
    private Integer id_seguro;
    private Integer id_programa;
    private Date fech_postulacion;
    private Date fech_respuesta;
    private char estado;
    private Integer reserva_dinero_pasajes;

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

    public Postulacion(Integer id_postulacion, Integer id_alumno, Integer id_familia, Integer id_seguro, Integer id_programa, String fech_postulacion, String fech_respuesta, char estado, Integer reserva_dinero_pasajes) throws ParseException {
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

    public Integer getId_postulacion() {
        return id_postulacion;
    }

    public void setId_postulacion(Integer id_postulacion) {
        this.id_postulacion = id_postulacion;
    }

    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Integer getId_familia() {
        return id_familia;
    }

    public void setId_familia(Integer id_familia) {
        this.id_familia = id_familia;
    }

    public Integer getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(Integer id_seguro) {
        this.id_seguro = id_seguro;
    }

    public Integer getId_programa() {
        return id_programa;
    }

    public void setId_programa(Integer id_programa) {
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

    public Integer getReserva_dinero_pasajes() {
        return reserva_dinero_pasajes;
    }

    public void setReserva_dinero_pasajes(Integer reserva_dinero_pasajes) {
        this.reserva_dinero_pasajes = reserva_dinero_pasajes;
    }

    @Override
    public String toString() {
        return "id_postulacion=" + id_postulacion + ", id_alumno=" + id_alumno + ", id_familia=" + id_familia + ", id_seguro=" + id_seguro + ", id_programa=" + id_programa + ", fech_postulacion=" + fech_postulacion + ", fech_respuesta=" + fech_respuesta + ", estado=" + estado + ", reserva_dinero_pasajes=" + reserva_dinero_pasajes;
    }
   
}
