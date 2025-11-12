package danilo.distefano;

import java.util.*;


public class RomanLiteralsConverter {
    private static final HashMap<String, Integer> baseLiteralsMap = new HashMap<>();
    private static final Map<String, ArrayList<String>> possibleSubtractionsMap = new HashMap<>();
    private static final String NOT_A_VALID_LITERAL = "Not a valid literal";
    private static final List<String> cannotBeRepeated = Arrays.asList("V", "L", "D");

    public int convertBaseLiteralsToNumber(String literal) {
        if (baseLiteralsMap.get(literal) == null){
            return 0;
        }
        return baseLiteralsMap.get(literal);
    }

    public String convertNumbersToBaseLiterals(int i) {
        if (getBaseLiteral(baseLiteralsMap, i) == null) {
            return "";
        }
        return getBaseLiteral(baseLiteralsMap, i);
    }

    public RomanLiteralsConverter() {
        baseLiteralsMap.put("I", 1);
        baseLiteralsMap.put("V", 5);
        baseLiteralsMap.put("X", 10);
        baseLiteralsMap.put("L", 50);
        baseLiteralsMap.put("C", 100);
        baseLiteralsMap.put("D", 500);
        baseLiteralsMap.put("M", 1000);

        possibleSubtractionsMap.put("I", new ArrayList<>());
        possibleSubtractionsMap.get("I").add("V");
        possibleSubtractionsMap.get("I").add("X");
        possibleSubtractionsMap.put("X", new ArrayList<>());
        possibleSubtractionsMap.get("X").add("L");
        possibleSubtractionsMap.get("X").add("C");
        possibleSubtractionsMap.put("C", new ArrayList<>());
        possibleSubtractionsMap.get("C").add("D");
        possibleSubtractionsMap.get("C").add("M");
    }

    private <K, V> K getBaseLiteral(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Integer convertLiteralsToNumber(String literal) {
        literal = cleanUpLiteral(literal);

        // Inititalize counters and basic variables
        int result=0;
        int mappedInt;
        int previousInt=0;
        String previousChar = "";
        int subtractionCounter = 0;
        int sameLiteralCounter = 0;

        // Loop on literal
        for (int j=literal.length()-1; j >=0; j--) {
            String c = String.valueOf(literal.charAt(j));
            mappedInt = convertBaseLiteralsToNumber(c);

            if (mappedInt == 0) {
                // Means the character is not a Roman Literal (I,V,X,L,C,D,M)
                System.out.println(NOT_A_VALID_LITERAL);
                return null;
            }

            if (mappedInt > previousInt) {
                // Means we can sum the literal
                result += mappedInt;
                subtractionCounter = 0;
                sameLiteralCounter = 1;
            } else if (mappedInt < previousInt) {
                sameLiteralCounter = 1;
                // Means we should subtract the literal
                if (possibleSubtractionsMap.get(c).contains(previousChar) && subtractionCounter <= 1) {
                    result -= mappedInt;
                    subtractionCounter ++;
                } else {
                    /*
                     Cannot have two subtractions in a row
                     Can only subtract
                     - I from V and X
                     - X from L and C,
                     - C from D and M
                    */
                    System.out.println(NOT_A_VALID_LITERAL);
                    return null;
                }
            } else {
                // Literal is the same as previous
                sameLiteralCounter++;
                if ((sameLiteralCounter == 2 && cannotBeRepeated.contains(c)) || (sameLiteralCounter > 3) || (subtractionCounter > 0)) {
                    System.out.println(NOT_A_VALID_LITERAL);
                    return null;
                }
                result += mappedInt;
            }

            // Store previous literal and number
            previousInt = mappedInt;
            previousChar = c;
        }
        return result;
    }

    private static String cleanUpLiteral(String literal) {
        return literal.replaceAll("\\s", "").toUpperCase();
    }
}
