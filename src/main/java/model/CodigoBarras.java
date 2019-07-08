package model;
import java.util.HashMap;
import java.util.Map;
public class CodigoBarras {
	private String banco;
	private String vencimento;
	private String ano;
	private String verificador;
	private String valor;
	protected CodigoBarras(String codigo) {
		this.banco = codigo.substring(0,4);
		this.vencimento = codigo.substring(4,8);
		this.ano = codigo.substring(8,12);
		this.verificador = codigo.substring(12,13);
		this.valor = codigo.substring(13,20);
	}
	protected String getCodBanco() {
		return banco;
	}
	protected String getCodVencimento() {
		return vencimento;
	}
	protected String getCodAno() {
		return ano;
	}
	protected String getCodVerificador() {
		return verificador;
	}
	protected String getCodValor() {
		return valor;
	}
	protected String getBanco() {
		Map<String, String> bancos = new HashMap<>();
    	bancos.put("0001","Banco do Brasil");
    	bancos.put("0104","Caixa Economica Federal");
    	bancos.put("0341","Itau");
    	bancos.put("0237","Bradesco");
    	bancos.put("0033","Santander");
		return bancos.get(this.banco);
	}
}
