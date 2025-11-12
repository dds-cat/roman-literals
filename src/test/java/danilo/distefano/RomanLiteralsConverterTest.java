package danilo.distefano;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RomanLiteralsConverterTest {
    private RomanLiteralsConverter romanLiteralsConverter = new RomanLiteralsConverter();

    public static Stream<Arguments> mapBaseLiteralsToNumber() {
        return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("V", 5),
                Arguments.of("X", 10),
                Arguments.of("L", 50),
                Arguments.of("C", 100),
                Arguments.of("D", 500),
                Arguments.of("M", 1000)
        );
    }

    public static Stream<Arguments> testTwoCharsLiteralsConversionArgs() {
        return Stream.of(
                Arguments.of("XX", 20), // Two of the same
                Arguments.of("II", 2), // Two of the same
                Arguments.of("CC", 200), // Two of the same
                Arguments.of("VI", 6), // Two summing up
                Arguments.of("CI", 101), // Two summing up
                Arguments.of("IX", 9), // Two subtracting
                Arguments.of("IV", 4), // Two subtracting
                Arguments.of("CM", 900) // Two subtracting
        );
    }

    public static Stream<Arguments> testThreeCharsLiteralsConversionArgs() {
        return Stream.of(
                Arguments.of("XXX", 30), // three of the same
                Arguments.of("III", 3), // three of the same
                Arguments.of("CCC", 300), // three of the same
                Arguments.of("VII", 7), // three summing up
                Arguments.of("CII", 102) // three summing up
        );
    }

    public static Stream<Arguments> testFourCharsLiteralsConversionArgs() {
        return Stream.of(
                Arguments.of("XCIX", 99), // two subtraction
                Arguments.of("XLIV", 44), // two subtraction
                Arguments.of("LXXX", 80), // three addition of the same
                Arguments.of("LXIV", 64) // one sum one subtraction
        );
    }

    public static Stream<Arguments> testFiveCharsLiteralsConversionArgs() {
        return Stream.of(
                Arguments.of("LXXIX", 79),
                Arguments.of("XCVII", 97),
                Arguments.of("DCCCL", 850)
        );
    }

    public static Stream<Arguments> testInvalidLiteralsArgs() {
        return Stream.of(
                Arguments.of("LXXPX"), // invalid literal P
                Arguments.of("XXCIX"), // two subtractions
                Arguments.of("XXXCIX"), // three subtractions
                Arguments.of("RandomString") // three subtractions
        );
    }

    public static Stream<Arguments> testWhiteSpacesArgs() {
        return Stream.of(
                Arguments.of("  DCCCL", 850),
                Arguments.of("DCCCL  ", 850),
                Arguments.of(" DCCCL  ", 850),
                Arguments.of("DC  CC L", 850)
        );
    }

    public static Stream<Arguments> testInvalidFourIdenticalLiteralsInARowArgs() {
        return Stream.of(
                Arguments.of("IIII"),
                Arguments.of("XXXX"),
                Arguments.of("ICCCC"),
                Arguments.of("MMMMI")
        );
    }

    public static Stream<Arguments> testInvalidTwoIdenticalVLDArgs() {
        return Stream.of(
                Arguments.of("VV"),
                Arguments.of("LL"),
                Arguments.of("DD"),
                Arguments.of("VVI"),
                Arguments.of("LLI")
        );
    }

    @ParameterizedTest
    @MethodSource("mapBaseLiteralsToNumber")
    void testConvertBaseLiteralsToNumber (String literal, int number){
        assertEquals(number, romanLiteralsConverter.convertBaseLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("mapBaseLiteralsToNumber")
    void testConvertNumberToBaseLiterals (String literal, int number){
        assertEquals(literal, romanLiteralsConverter.convertNumbersToBaseLiterals(number));
    }

    @Test
    void testInvalidBaseLiteralsToNumber () {
        assertEquals(0, romanLiteralsConverter.convertBaseLiteralsToNumber("XX"));
    }

    @Test
    void testInvalidNumberToBaseLiteral () {
        assertEquals("", romanLiteralsConverter.convertNumbersToBaseLiterals(35));
    }

    @ParameterizedTest
    @MethodSource("testTwoCharsLiteralsConversionArgs")
    void testTwoCharsLiteralsConversion (String literal, int number){
        assertEquals(number, romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("testThreeCharsLiteralsConversionArgs")
    void testThreeCharsLiteralsConversion (String literal, int number){
        assertEquals(number, romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("testFourCharsLiteralsConversionArgs")
    void testFourCharsLiteralsConversion (String literal, int number){
        assertEquals(number, romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("testFiveCharsLiteralsConversionArgs")
    void testFiveCharsLiteralsConversion (String literal, int number){
        assertEquals(number, romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("testInvalidLiteralsArgs")
    void testInvalidLiterals (String literal){
        assertNull(romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("testWhiteSpacesArgs")
    void testWhiteSpaces (String literal, int number){
        assertEquals(number, romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("testInvalidFourIdenticalLiteralsInARowArgs")
    void testInvalidFourIdenticalLiteralsInARow (String literal){
        assertNull(romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @ParameterizedTest
    @MethodSource("testInvalidTwoIdenticalVLDArgs")
    void testInvalidTwoIdenticalVLD (String literal){
        assertNull(romanLiteralsConverter.convertLiteralsToNumber(literal));
    }

    @Test
    void testMaxRomanLiteral() {
        assertEquals(3999, romanLiteralsConverter.convertLiteralsToNumber("MMMCMXCIX"));
    }
}