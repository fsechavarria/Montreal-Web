package entities;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Curso implements Serializable{
    private Integer id_curso;
    private Integer id_programa;
    @Size(min=5, max=100, message="La descripción debe tener entre 5 y 100 caracteres.")
    private String desc_curso;
    @Min(value=1, message="Debe poseer al menos 1 cupo")
    private Integer cupos;
    private Programa_Estudio programa;

    public Curso() {
        this.id_curso = 0;
        this.id_programa = 0;
        this.desc_curso = "";
        this.cupos = 0;
    }

    public Curso(Integer id_curso, Integer id_programa, String desc_curso, Integer cupos, Programa_Estudio programa) {
        this.id_curso = id_curso;
        this.id_programa = id_programa;
        this.desc_curso = desc_curso;
        this.cupos = cupos;
        this.programa = programa;
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

    public Programa_Estudio getPrograma() {
        return programa;
    }

    public void setPrograma(Programa_Estudio programa) {
        this.programa = programa;
    }

    @Override
    public String toString() {
        return "id_curso=" + id_curso + ", id_programa=" + id_programa + ", desc_curso=" + desc_curso + ", cupos=" + cupos;
    }
    
}
