package entities;


public class Inscripcion_Alumno {
 
    private Integer id_inscripcion;
    private Integer id_programa;
    private Integer id_alumno;
    private Integer id_curso;

    public Inscripcion_Alumno() {
        this.id_inscripcion = 0;
        this.id_programa = 0;
        this.id_alumno = 0;
        this.id_curso = 0;
    }

    public Inscripcion_Alumno(Integer id_inscripcion, Integer id_programa, Integer id_alumno, Integer id_curso) {
        this.id_inscripcion = id_inscripcion;
        this.id_programa = id_programa;
        this.id_alumno = id_alumno;
        this.id_curso = id_curso;
    }

    public Integer getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(Integer id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public Integer getId_programa() {
        return id_programa;
    }

    public void setId_programa(Integer id_programa) {
        this.id_programa = id_programa;
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

    @Override
    public String toString() {
        return "id_inscripcion=" + id_inscripcion + ", id_programa=" + id_programa + ", id_alumno=" + id_alumno + ", id_curso=" + id_curso;
    }
    
    
}
