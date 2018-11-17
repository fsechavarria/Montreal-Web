package entities;

public class Calificacion {
    
    private Integer id_calificacion;
    private Integer id_alumno;
    private Integer id_curso;
    private String desc_calificacion;
    private double nota;
    private Alumno alumno;
    private Curso curso;

    public Calificacion() {
        this.id_alumno = 0;
        this.id_curso = 0;
        this.id_calificacion = 0;
        this.desc_calificacion = "";
        this.nota = 0;
        this.alumno = new Alumno();
        this.curso = new Curso();
    }

    public Calificacion(Integer id_calificacion, Integer id_alumno, Integer id_curso, String desc_calificacion, double nota, Alumno alumno, Curso curso) {
        this.id_calificacion = id_calificacion;
        this.id_alumno = id_alumno;
        this.id_curso = id_curso;
        this.desc_calificacion = desc_calificacion;
        this.nota = nota;
        this.alumno = alumno;
        this.curso = curso;
    }

    public Integer getId_calificacion() {
        return id_calificacion;
    }

    public void setId_calificacion(Integer id_calificacion) {
        this.id_calificacion = id_calificacion;
    }

    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getDesc_calificacion() {
        return desc_calificacion;
    }

    public void setDesc_calificacion(String desc_calificacion) {
        this.desc_calificacion = desc_calificacion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "id_calificacion=" + id_calificacion + ", id_alumno=" + id_alumno + ", id_curso=" + id_curso + ", desc_calificacion=" + desc_calificacion + ", nota=" + nota + ", alumno=" + alumno + ", curso=" + curso;
    }
    
}
