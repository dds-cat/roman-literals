package danilo.distefano;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        RomanLiteralsConverter romanLiteralsConverter = new RomanLiteralsConverter();

        String originalBaseLiteral ="V";
        int number = romanLiteralsConverter.convertBaseLiteralsToNumber(originalBaseLiteral);

        System.out.printf("Converted literal %s to number %d%n", originalBaseLiteral, number);

        int originalBaseNumber = 10;
        String literal = romanLiteralsConverter.convertNumbersToBaseLiterals(originalBaseNumber);

        System.out.printf("Converted number %d to literal%s", originalBaseNumber, literal);

        String twoCharsLiteral = "XX";
        int numberFromTwoCharsLiteral = romanLiteralsConverter.convertLiteralsToNumber(twoCharsLiteral);

        System.out.printf("Converted literal %s to number %d%n", twoCharsLiteral, numberFromTwoCharsLiteral);

    }
}