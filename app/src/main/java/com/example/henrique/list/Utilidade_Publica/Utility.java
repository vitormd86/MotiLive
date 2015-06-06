package com.example.henrique.list.Utilidade_Publica;

/**
 * Created by htamashiro on 3/20/15.
 */
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.motiserver.constants.Gender;

/**
 * Class which has Utility methods
 *
 */
public class Utility {

    private static String errorString;

    public static boolean isValid(String value) {
        if (value != null && !value.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidName(String nome) {

        if (nome.equals("")) {

            return false;
        }
        else{
            if(nome.length() > 60){
                return false;
            }
            else{
                String NAME_PATTERN = "^[_A-Za-z\\+]{3,15}+((\\s[_A-Za-z]+)*)$";

                Pattern pattern = Pattern.compile(NAME_PATTERN);
                Matcher matcher = pattern.matcher(nome);
                return matcher.matches();
            }
        }
    }
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPrefix(String prefix) {
        if (prefix.length() != 2) {
            return false;
        }else {
            return true;
        }
    }
    public static boolean isValidCelular(String celular) {
        if (celular.length() != 9) {
            return false;
        }else  {
            return true;
        }

    }
    public static boolean isValidCEP(String cep) {
        if (cep.length() != 8 ) {
            return false;
        }else {
            return true;
        }
    }
    public static boolean isValidNumero(String numero) {
        if (numero.equals("") || numero.length() > 9) {
            return false;
        }
        else{
            return true;
        }
    }
    public static boolean isValidEstado(String estado) {
        if (estado.equals("")) {
            return false;
        }
        else{
            if(estado.length() > 2){
                return false;
            }
            else{
                String STATE_PATTERN = "^[a-zA-Z]{1,2}$";

                Pattern pattern = Pattern.compile(STATE_PATTERN);
                Matcher matcher = pattern.matcher(estado);
                return matcher.matches();
            }
        }
    }
    public static boolean isValidCidade(String cidade) {
        if (cidade.equals("")) {
            return false;
        }
        else{
            if(cidade.length() > 40){
                return false;
            }
            else{
                String CITY_NAME = "^[_A-Za-z0-9\\+]{3,15}+((\\s[_A-Za-z0-9]+)*)$";

                Pattern pattern = Pattern.compile(CITY_NAME);
                Matcher matcher = pattern.matcher(cidade);
                return matcher.matches();
            }
        }
    }
    public static boolean isValidBairro(String bairro) {
        if (bairro.equals("")) {
            return false;
        }
        else{
            if(bairro.length() > 60){
                return false;
            }
            else{
                String DISTRICT_NAME = "^[_A-Za-z0-9\\+]{3,15}+((\\s[_A-Za-z0-9]+)*)$";

                Pattern pattern = Pattern.compile(DISTRICT_NAME);
                Matcher matcher = pattern.matcher(bairro);
                return matcher.matches();            }
        }
    }
    public static boolean isValidTextWithSpace(String text) {
        if (text.equals("")) {
            return false;
        }
        else{
            if(text.length() > 60){
                return false;
            }
            else{

                String TEXT = "^[_A-Za-z�-��-�0-9\\+]{3,15}+((\\s[_A-Za-z0-9�-��-�]+)*)$";
                Pattern pattern = Pattern.compile(TEXT);
                Matcher matcher = pattern.matcher(text);
                return matcher.matches();             }
        }

    }
    public static boolean isValidprofissao(String profissao) {
        if (profissao.equals("")) {
            return false;
        }
        else{
            if(profissao.length() > 40){
                return false;
            }
            else{
                String PROFESSION = "^[_A-Za-z\\+]{3,15}+((\\s[_A-Za-z]+)*)$";

                Pattern pattern = Pattern.compile(PROFESSION);
                Matcher matcher = pattern.matcher(profissao);
                return matcher.matches();
            }
        }
    }

    public static boolean isValidBigDecimal(String text) {
        if (text.equals("")) {
            return false;
        }
        else{
            if(text.length() > 10){
                return false;
            }
            else {
                BigDecimal bd = new BigDecimal(text);
                return bd.toPlainString().matches("\\d+(\\.\\d*)?");
            }

        }

    }

    public static boolean isValidNascimento(Calendar chosenDate){
        if(chosenDate==null){
            return false;
        }
        else{
            return true;
        }
    }
    public static Boolean isValidSexo(Gender opcaoEscolhidaGenero){
        if(opcaoEscolhidaGenero==null){
            return false;
        }
        else{
            return true;
        }
    }

}