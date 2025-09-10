package maven.demo;

public class Carro {

	private int cod;
	private String modelo;
	private String marca;
	private float preco;
	
	public Carro() {
		this.cod = -1;
		this.modelo = "";
		this.marca = "";
		this.preco = 10;
	}
	
	public Carro(int cod, String modelo, String marca, float preco) {
		this.cod = cod;
		this.modelo = modelo;
		this.marca = marca;
		this.preco = preco;
	}

	public int getCodigo() {
		return cod;
	}

	public void setCodigo(int codigo) {
		this.cod = codigo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(char preco) {
		this.preco	 = preco;
	}

	@Override
	public String toString() {
		return "Carro [codigo=" + cod + ", modelo=" + modelo + ", marca=" + marca + ", preco=" + preco + "]";
	}	
	
	
	
	
}
