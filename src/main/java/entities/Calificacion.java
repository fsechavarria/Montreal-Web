package entities;

public class Calificacion {
    
    private int id_curso;
    private int id_programa;
    private String desc_curso;
    private int cupos;

    public Calificacion() {
        this.id_curso = 0;
        this.id_programa = 0;
        this.desc_curso = "";
        this.cupos = 0;
    }
    
    public Calificacion(int id_curso, int id_programa, String desc_curso, int cupos) {
        this.id_curso = id_curso;
        this.id_programa = id_programa;
        this.desc_curso = desc_curso;
        this.cupos = cupos;
    }
    
    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public String getDesc_curso() {
        return desc_curso;
    }

    public void setDesc_curso(String desc_curso) {
        this.desc_curso = desc_curso;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    @Override
    public String toString() {
        return "id_curso=" + id_curso + ", id_programa=" + id_programa + ", desc_curso=" + desc_curso + ", cupos=" + cupos;
    }
    
}
