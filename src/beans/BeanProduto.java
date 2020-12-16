package beans;


public class BeanProduto {
	
	
	private Long id;
	private String nome;
	private String quantidade;
	private double valor;
	private Long categoria_id;
	
	
	
	public Long getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	public String getValorEmText() {
		
		return Double.toString(valor).replace('.', ',');
	}
	
}
