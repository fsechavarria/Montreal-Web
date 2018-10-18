package entities;

public class Curso {
    private Integer id_curso;
    private Integer id_programa;
    private String desc_curso;
    private Integer cupos;

    public Curso() {
        this.id_curso = 0;
        this.id_programa = 0;
        this.desc_curso = "";
        this.cupos = 0;
    }

    public Curso(Integer id_curso, Integer id_programa, String desc_curso, Integer cupos) {
        this.id_curso = id_curso;
        this.id_programa = id_programa;
        this.desc_curso = desc_curso;
        this.cupos = cupos;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public Integer getId_programa() {
        return id_programa;
    }

    public void setId_programa(Integer id_programa) {
        this.id_programa = id_programa;
    }

    public String getDesc_curso() {
        return desc_curso;
    }

    public void setDesc_curso(String desc_curso) {
        this.desc_curso = desc_curso;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    @Override
    public String toString() {
        return "id_curso=" + id_curso + ", id_programa=" + id_programa + ", desc_curso=" + desc_curso + ", cupos=" + cupos;
    }
    
}
