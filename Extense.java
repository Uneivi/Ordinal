
final public class Extense {

    private final static String joinAnd = "%s e %s";
    private final static String join = "%s %s";


    public static <N extends Number> String readNumber(N num){

        String numberReaded;
        if(num.longValue() >= 1_000) numberReaded = milhar(num.longValue());
        else if(num.intValue() >= 100) numberReaded = centenas(num.intValue());
        else if(num.intValue() >= 10) numberReaded = dezenas(num.intValue());
        else numberReaded = unidade(num.intValue());

        return numberReaded;
    }

    /**
     * @return a unidade convertida em texto.
     */
    private static String unidade(Integer number){
        return Readbly.Unidades.from(number);
    }

    /**
     * @param number deve ser menor que 100
     * @return Caso a dezena não seja inteira, faz a quebra dos algarismos e a junção
     * dos dois textos.
     */
    private static String dezenas(Integer number){
        Integer modulo = number % 10;
        Integer dezena = number / 10;

        if(modulo == 0) return Readbly.Dezenas.from(dezena);
        else {
            if(number > 19) return joinAnd.formatted(Readbly.Dezenas.from(dezena), unidade(modulo));
            else return Readbly.DezenasDeUnidades.from(modulo);
        }
    }

    private static String centenas(Integer number){
        Integer modulo = number % 100;
        Integer centena = number / 100;

        if(modulo == 0) {
            return (centena > 1) ? Readbly.Centenas.from(centena + 1): Readbly.Centenas.from(centena);
        }
        else return joinAnd.formatted(Readbly.Centenas.from(centena + 1), readNumber(modulo));
    }

    private static String milhar(Long number){

        //Identificar qual a grandeza do número e dividi-lo por mil.
        /*
        Pode-se fazer chamadas ao próprio método para quebrar valores.
        Sabe-se que a unidade de grandeza muda seu nome;
        O milhão nada mais é do que 'um mil de milhar';
        O bilhão é 'um mil de milhão'; [...]
         */

        String numberReaded = "";
        Long milhao = 1_000_000L;
        Long modulo;

        if(number < milhao){
            modulo = number % 1_000;
            Long milhar = number / 1_000;

            String thousandReaded = join.formatted(readNumber(milhar), Readbly.Milhares.from(1));

            if(modulo == 0) numberReaded = thousandReaded;
            else if(modulo <= 100) numberReaded = joinAnd.formatted(thousandReaded, readNumber(modulo));
            else numberReaded = join.formatted(thousandReaded, readNumber(modulo));
        }
        else {
            numberReaded = illionsGreatness(number);
        }
        return numberReaded;
    }

    /**
     *
     * @param number Valor maior ou igual a milhões.
     */
    private static String illionsGreatness(Long number){
        Long thousand = 1_000L;
        Long million = thousand * thousand;
        Long billion = million * thousand;

        Long modulo;
        Long grandeza = 0L;
        Long unit = number / million;   // Define se o número está na casa do milhão ou acima.
        String numberReaded = "";

        if(unit >= thousand){
            modulo = number % billion;
            unit /= thousand;
            grandeza = 3L;
        } else {
            modulo = number % million;
            grandeza = 2L;
        }

        numberReaded = (unit.equals(1L)) ?
                join.formatted(readNumber(unit), Readbly.Milhares.from(grandeza.intValue()) + "ão"):
                join.formatted(readNumber(unit), Readbly.Milhares.from(grandeza.intValue()) + "ões");

        if (modulo.intValue() > 0) numberReaded = join.formatted(numberReaded, readNumber(modulo));

        return numberReaded;
    }
}
