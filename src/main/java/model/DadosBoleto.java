/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.lang.Math;
/**
 *
 * @author fabricio
 */
public class DadosBoleto {
    private boolean isValid;
    private String banco;
    private Calendar dataVencimento;
    private Calendar dataPagamento;
    private float valorASerPago;
    private CodigoBarras codigo;
    
    public DadosBoleto(CodigoBarras codigo, String dataPagamento) {
        this.codigo = codigo;
	    this.dataVencimento = GregorianCalendar.getInstance();
	    this.dataVencimento.set(
		    this.getAno(),
		    (Math.abs(13 - Integer.parseInt(this.codigo.getCodVencimento().substring(2,4))) - 1),
		    Math.abs(11 - Integer.parseInt(this.codigo.getCodVencimento().substring(0,2)))
	    );
        this.dataPagamento = GregorianCalendar.getInstance();
        this.dataPagamento.set(
		    Integer.parseInt(dataPagamento.substring(6,10)),
		    Integer.parseInt(dataPagamento.substring(3,5)),
		    Integer.parseInt(dataPagamento.substring(0,2))
	    );
	    this.valorASerPago = Float.parseFloat(this.codigo.getCodValor().substring(0,5)) + 
		    (Float.parseFloat(this.codigo.getCodValor().substring(5,7))/100);
	    this.banco = this.codigo.getBanco();
	    this.isValid = true;
    }
    
    public String getBanco() {
	    return this.banco;
    }

    public int getAno() {
    	return Integer.parseInt(this.codigo.getCodAno()) + 2018;
    }
    
    public String getDataVencimento() {
	    return new SimpleDateFormat("dd/MM/yyyy").format(this.dataVencimento.getTime());
    }

    public String getDataPagamento() {
	    return new SimpleDateFormat("dd/MM/yyyy").format(this.dataPagamento.getTime());
    }

    public float getValor() {
    	int dateDiff = new CompareDate().dateDiff(this.dataVencimento.getTime(),this.dataPagamento.getTime());
        if (dateDiff > 0 && dateDiff <= 90){
            double valorFinal = this.valorASerPago * (1 + 0.02);
            return Float.parseFloat((valorFinal + (valorFinal * (0.003) * dateDiff)) + "");
        } else if (dateDiff > 90){
            return new Float("");
        } else {
            return this.valorASerPago;
        }
    }

    public boolean valid() {
	    return this.isValid;
    }

    public CodigoBarras getCodigo() {
    	return this.codigo;
    }
}
