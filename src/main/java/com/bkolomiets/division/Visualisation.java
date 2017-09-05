package com.bkolomiets.division;

import org.apache.commons.lang3.StringUtils;

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

    private String showDifference(int lineNumber, String difference) {
        if (difference.equals("")) {
            return StringUtils.leftPad(" ",
                    difference.length() + lineNumber,
                    " ");
        } else {
            return StringUtils.leftPad(difference,
                    difference.length() + lineNumber,
                    " ");
        }
    }

    private String showDeduction(int lineNumber, String deduction, String pieceOfDividendLine) {
        int quantityIndent = calcQuantityIndent(Integer.parseInt(pieceOfDividendLine), deduction);

        if (quantityIndent != 0) {
            return "\n" + StringUtils.leftPad(deduction,
                    deduction.length() + lineNumber + quantityIndent,
                    " ");
        } else {
            return "\n" + StringUtils.leftPad(deduction, deduction.length() + lineNumber, " ");
        }
    }

    private String showHorizontalSplitter(int lineNumber, String pieceOfDividendLine) {
        String horizontalSplitter = createQuantityHorizontalSplitter(pieceOfDividendLine, HORIZONTAL_SPLITTER);

        return "\n" + StringUtils.leftPad(horizontalSplitter,
                pieceOfDividendLine.length() + lineNumber,
                " ");
    }

    String showFirstAnswerLine() {
        return dividend + " " + VERTICAL_SPLITTER + " " + divisor;
    }

    String showSecondAnswerLine(String deduction, String pieceOfDividendLine, String resultOfExpression) {
        int quantityIndent = calcQuantityIndent(Integer.parseInt(pieceOfDividendLine), deduction);
        resultOfExpression = comparesWithTheLengthDivisor(resultOfExpression);
        String resultOfTheMethod = "";

        if (quantityIndent != 0) {
            resultOfTheMethod += StringUtils.leftPad(deduction, deduction.length() + quantityIndent, " ");

            if ((deduction.length() + 1) == resultOfExpression.length()) {
                resultOfTheMethod += StringUtils.leftPad(KNOT_SPLITTER,
                        quantityIndent + 1,
                        " ")
                        + " "
                        + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER);
            } else {
                resultOfTheMethod += StringUtils.leftPad(KNOT_SPLITTER,
                        quantityIndent + 1,
                        " ")
                        + " "
                        + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER);
            }
        } else {
            quantityIndent = calcQuantityIndent(dividend, deduction);
            resultOfTheMethod += deduction
                    + StringUtils.leftPad(KNOT_SPLITTER, quantityIndent + 2, " ")
                    + " "
                    + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER);
        }
        return resultOfTheMethod;
    }

    String showThirdAnswerLine(String pieceOfDividendLine, String resultOfExpression) {
        int quantityIndent = calcQuantityIndent(dividend, pieceOfDividendLine);

        return createQuantityHorizontalSplitter(pieceOfDividendLine, HORIZONTAL_SPLITTER)
                + StringUtils.leftPad(VERTICAL_SPLITTER, quantityIndent + 2, " ")
                + " "
                + resultOfExpression;
    }

    String showTheRest(int lineNumber, String deduction, String pieceOfDividendLine) {
        return showDifference(lineNumber, pieceOfDividendLine)
             + showDeduction(lineNumber, deduction, pieceOfDividendLine)
             + showHorizontalSplitter(lineNumber, pieceOfDividendLine);
    }
}
