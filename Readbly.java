public enum Readbly {

    Unidades(new String[]{"um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez"}),
    DezenasDeUnidades(new String[]{"onze", "doze", "treze",
            "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"}),
    Dezenas(new String[]{"dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"}),
    Centenas(new String[]{"cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos",
            "setecentos", "oitocentos", "novecentos"}),
    Milhares(new String[]{"mil", "milh", "bilh", "trilh", "quatrilh", "quitilh", "sextilh",
            "septilh", "octilh", "nonilh"});

    private final String[] nomes;

    Readbly(String[] nomes){
        this.nomes = nomes;
    }

    /**
     * Converte o número em uma String para sua forma por extensa.
     * @return O valor escrito do número fornecido.
     */
    public String from(Integer num) {
        return nomes[num - 1];
    }
}
