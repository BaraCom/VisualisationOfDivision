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

    private void showDifference(int lineNumber, String difference) {
        if (lineNumber != 0) {
            if (difference.equals("")) {
                System.out.println(StringUtils.leftPad(" ",
                        difference.length() + lineNumber,
                        " "));
            } else {
                System.out.println(StringUtils.leftPad(difference,
                        difference.length() + lineNumber,
                        " "));
            }
        }
    }

    private void showDeduction(int lineNumber, String deduction, String pieceOfDividendLine) {
        int quantityIndent = calcQuantityIndent(Integer.parseInt(pieceOfDividendLine), deduction);

        if (lineNumber != 0) {
            if (quantityIndent != 0) {
                System.out.println(StringUtils.leftPad(deduction,
                        deduction.length() + lineNumber + quantityIndent,
                        " "));
            } else {
                System.out.println(StringUtils.leftPad(deduction, deduction.length() + lineNumber, " "));
            }
        }
    }

    private void showHorizontalSplitter(int lineNumber, String pieceOfDividendLine) {
        String horizontalSplitter = createQuantityHorizontalSplitter(pieceOfDividendLine, HORIZONTAL_SPLITTER);

        if (lineNumber != 0) {
            System.out.println(StringUtils.leftPad(horizontalSplitter,
                    pieceOfDividendLine.length() + lineNumber,
                    " "));
        }
    }

    void showFirstAnswerLine(int lineNumber) {
        if (lineNumber == 0) {
            System.out.println(dividend + " " + VERTICAL_SPLITTER + " " + divisor);
        }
    }

    void showSecondAnswerLine(int lineNumber, String deduction, String pieceOfDividendLine, String resultOfExpression) {
        int quantityIndent = calcQuantityIndent(Integer.parseInt(pieceOfDividendLine), deduction);

        if (lineNumber == 0) {
            if (quantityIndent != 0) {
                System.out.print(StringUtils.leftPad(deduction, deduction.length() + quantityIndent, " "));

                if ((deduction.length() + 1) == resultOfExpression.length()) {
                    System.out.println(StringUtils.leftPad(KNOT_SPLITTER,
                            quantityIndent + 1,
                            " ")
                            + " "
                            + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER));
                } else {
                    System.out.println(StringUtils.leftPad(KNOT_SPLITTER,
                            quantityIndent + 1,
                            " ")
                            + " "
                            + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER));
                }
            } else {
                quantityIndent = calcQuantityIndent(dividend, deduction);
                System.out.println(deduction
                        + StringUtils.leftPad(KNOT_SPLITTER, quantityIndent + 2, " ")
                        + " "
                        + createQuantityHorizontalSplitter(resultOfExpression, HORIZONTAL_SPLITTER));
            }
        }
    }

    void showThirdAnswerLine(int lineNumber, String pieceOfDividendLine, String resultOfExpression) {
        if (lineNumber == 0) {
            int quantityIndent = calcQuantityIndent(dividend, pieceOfDividendLine);

            System.out.println(createQuantityHorizontalSplitter(pieceOfDividendLine, HORIZONTAL_SPLITTER)
                    + StringUtils.leftPad(VERTICAL_SPLITTER, quantityIndent + 2, " ")
                    + " "
                    + resultOfExpression);
        }
    }

    void showTheRest(int lineNumber, String deduction, String pieceOfDividendLine) {
        showDifference(lineNumber, pieceOfDividendLine);
        showDeduction(lineNumber, deduction, pieceOfDividendLine);
        showHorizontalSplitter(lineNumber, pieceOfDividendLine);
    }
}
