package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mascaras {
    
    public static String MascaraCnpj(String cnpj){
	Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
	Matcher matcher = pattern.matcher(cnpj);
	if(matcher.find()){
		return matcher.replaceAll("$1.$2.$3/$4-$5");
	}
	return cnpj;
    }  
    
    public static String MascaraRg(String rg){
	Pattern pattern = Pattern.compile("(\\d{1})(\\d{3})(\\d{3})");
	Matcher matcher = pattern.matcher(rg);
	if(matcher.find()){
		return matcher.replaceAll("$1.$2.$3");
	}
	return rg;
    } 
    
    public static String MascaraCpf(String cpf){
	Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
	Matcher matcher = pattern.matcher(cpf);
	if(matcher.find()){
		return matcher.replaceAll("$1.$2.$3-$4");
	}
	return cpf;
    } 
    
    public static String MascaraCep(String cep){
	Pattern pattern = Pattern.compile("(\\d{5})(\\d{3})");
	Matcher matcher = pattern.matcher(cep);
	if(matcher.find()){
		return matcher.replaceAll("$1-$2");
	}
	return cep;
    }  
    
    public static String MascaraTelefone(String telefone){
	Pattern pattern = Pattern.compile("(\\d{2})(\\d{5})(\\d{4})");
	Matcher matcher = pattern.matcher(telefone);
	if(matcher.find()){
		return matcher.replaceAll("($1)$2-$3");
	}
	return telefone;
    }  
    
    public static String MascaraData(String data){
	Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})(\\d{4})");
	Matcher matcher = pattern.matcher(data);
	if(matcher.find()){
		return matcher.replaceAll("$1/$2/$3");
	}
	return data;
    }  
    
}
