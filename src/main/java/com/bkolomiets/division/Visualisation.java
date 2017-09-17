package com.bkolomiets.division;

import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.leftPad;

class Visualisation {
    private static final String KNOT_SPLITTER = "+";
    private static final String VERTICAL_SPLITTER = "|";
    private static final String HORIZONTAL_SPLITTER = "-";

    private int dividend;
    private int divisor;

    Visualisation(int dividend, int divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    private String createQuantityHorizontalSplitter(String pieceOfDividendLine, String horizontalSplitter) {
        int pieceOfDividendLength = pieceOfDividendLine.length();
        StringBuilder concatHorizontalSplitter = new StringBuilder();

        for (int i = 0; i < pieceOfDividendLength; i++) {
            concatHorizontalSplitter.append(horizontalSplitter);
        }
        return concatHorizontalSplitter.toString();
    }

    private int calcQuantityIndent(int dividend, String deduction) {
        return Integer.toString(dividend).length() - deduction.length();
    }

    private String comparesWithTheLengthDivisor(String resultOfExpression) {
        if (resultOfExpression.length() < Integer.toString(divisor).length()) {
            resultOfExpression = Integer.toString(divisor);
        }
        return resultOfExpression;
    }

    public String showFirstAnswerLine() {
        return dividend + " " + VERTICAL_SPLITTER + " " + divisor;
    }

    public String showSecondAnswerLine(String deduction, String pieceOfDividendLine, String resultOfExpression) {
        int quantityIndent = calcQuantityIndent(Integer.parseInt(pieceOfDividendLine), deduction);
        resultOfExpression = comparesWithTheLengthDivisor(resultOfExpression);
        String resultOfTheMethod = "";

        if (quantityIndent != 0) {
            resultOfTheMethod += leftPad(deduction, deduction.length() + quantityIndent, " ")/*;*/
                + leftPad(KNOT_SPLITTER
                    ,Integer.toString(dividend).length() - (quantityIndent + deduction.length()) + 2
                    , " ")
                + " "
                + leftPad(createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER)
                    , 1
                    , " ");

            /*if ((deduction.length() + 1) == resultOfExpression.length()) {
                resultOfTheMethod += leftPad(KNOT_SPLITTER,
                        quantityIndent + 1,
                        " ")
                        + " "
                        + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER);
            } else {
                resultOfTheMethod += leftPad(KNOT_SPLITTER,
                        quantityIndent + 1,
                        " ")
                        + " "
                        + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER);
            }*/
        } else {
            quantityIndent = calcQuantityIndent(dividend, deduction);
            resultOfTheMethod += deduction
                    + leftPad(KNOT_SPLITTER, quantityIndent + 2, " ")
                    + " "
                    + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER);
        }
        return resultOfTheMethod;
    }

    public String showThirdAnswerLine(String pieceOfDividendLine, String resultOfExpression) {
        int quantityIndent = calcQuantityIndent(dividend, pieceOfDividendLine);

        return createQuantityHorizontalSplitter(pieceOfDividendLine, HORIZONTAL_SPLITTER)
                + leftPad(VERTICAL_SPLITTER, quantityIndent + 2, " ")
                + " "
                + resultOfExpression;
    }

    public String showTheRest(String pieceDividendLine, String deduction, int leftPad) {
        String horizontalSplitter = createQuantityHorizontalSplitter(pieceDividendLine, HORIZONTAL_SPLITTER);

        return "\n"
                + StringUtils.leftPad(pieceDividendLine
                , leftPad
                , " ")
                + "\n"
                + StringUtils.leftPad(deduction
                , leftPad
                , " ")
                + "\n"
                + StringUtils.leftPad(horizontalSplitter
                , leftPad
                , " ");
    }
}
