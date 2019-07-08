package model;
public class Validador implements ValidadorBoleto {
	public DadosBoleto validarCodigoBarrasBoleto(String codigoDeBarras, String dataPagamento) {
		try {
			CodigoBarras codigo = new CodigoBarras(codigoDeBarras);
			char[] digitos = codigo.getCodValor().toCharArray();
			int soma = 0;
			for( char digito : digitos) {
				soma = soma + Character.getNumericValue(digito);
			}
			if (soma % 9 == Integer.parseInt(codigo.getCodVerificador())) {
				return new DadosBoleto(codigo,dataPagamento);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
