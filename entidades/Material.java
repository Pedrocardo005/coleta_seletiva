package entidades;

public class Material {
    private String tipo;
    private float peso, cubagem;
    private int idMaterial;

    // Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getCubagem() {
        return cubagem;
    }

    public void setCubagem(float cubagem) {
        this.cubagem = cubagem;
    }
}
