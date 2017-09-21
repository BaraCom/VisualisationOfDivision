package com.bkolomiets.division;

import static java.lang.Integer.parseInt;

public class Divider {

    public String divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Divisor can't be ZERO");
        }
        return buildColumn(dividend, divisor);
    }

    private String buildColumn(int dividend, int divisor) {
        Visualisation visualisation = new Visualisation(dividend, divisor);
        String resultColumn = "";
        String pieceOfDividend = "";
        String prevPieceOfDividend = "";
        String prevDeduction = "";
        int lineNumber = 0;
        int leftPad = 0;

        String resultOfExpression = getQuotient(dividend, divisor);

        for (char dividendChar : addedToCharArray(dividend)) {
            pieceOfDividend += Character.toString(dividendChar);

            if (isPieceOfTheDividend(pieceOfDividend, divisor)) {
                int deduction = getDeduction(pieceOfDividend, divisor);

                if (lineNumber == 0) {
                    resultColumn += visualisation.showFirstLine()
                            + "\n"
                            + visualisation.showSecondLine(Integer.toString(deduction), pieceOfDividend
                            , resultOfExpression)
                            + "\n"
                            + visualisation.showThirdLine(pieceOfDividend, resultOfExpression);
                } else {
                    leftPad += getLeftPad(prevPieceOfDividend, prevDeduction, lineNumber, pieceOfDividend);
                    resultColumn += visualisation.showRestLines(pieceOfDividend, Integer.toString(deduction), leftPad);
                }
                prevPieceOfDividend = pieceOfDividend;
                prevDeduction = Integer.toString(deduction);

                pieceOfDividend = getDifference(Integer.parseInt(pieceOfDividend), deduction);
                lineNumber ++;
            }
        }
        return resultColumn;
    }

    private char[] addedToCharArray(int dividend) {
        String lineNumber = Integer.toString(dividend);

        return lineNumber.toCharArray();
    }

    private boolean isPieceOfTheDividend(String pieceOfDividend, int divisor) {
        return divisor <= parseInt(pieceOfDividend);
    }

    private String getQuotient(int number, int divisor) {
        String pieceOfDividend = "";
        String methodResult = "";

        for (char dividendChar: addedToCharArray(number)) {
            pieceOfDividend += Character.toString(dividendChar);

            if (isPieceOfTheDividend(pieceOfDividend, divisor)) {
                int resultQuotient = Integer.parseInt(pieceOfDividend) / divisor;
                int deduction = resultQuotient * divisor;
                methodResult += Integer.parseInt(pieceOfDividend) / divisor;
                pieceOfDividend = getDifference(Integer.parseInt(pieceOfDividend), deduction);
            }
        }
        return methodResult;
        }

    private int getDeduction(String pieceOfDividend, int divisor) {
        String quotient = getQuotient(parseInt(pieceOfDividend), divisor);

        return parseInt(quotient) * divisor;
    }

    private String getDifference(int pieceOfDividend, int deduction) {
        int difference = pieceOfDividend - deduction;

        if (difference == 0) {
            return "";
        } else {
            return Integer.toString(difference);
        }
    }

    private int getLeftPad(String prevPieceOfDividend, String prevDeduction, int lineNumber, String pieceOfDividend) {
        if (getDifference(prevPieceOfDividend.length(), prevDeduction.length()).equals("")) {
            if (getDifference(parseInt(prevPieceOfDividend), parseInt(prevDeduction)).equals("")) {
                if (lineNumber == 1) {
                    return prevPieceOfDividend.length() + pieceOfDividend.length();
                } else {
                    return pieceOfDividend.length();
                }
            } else {
                int difference = parseInt(prevPieceOfDividend) - parseInt(prevDeduction);

                if (lineNumber == 1) {
                    if (prevPieceOfDividend.length() == Integer.toString(difference).length()) {
                        return pieceOfDividend.length();
                    } else {
                        return pieceOfDividend.length() + 1;
                    }
                } else {
                    return pieceOfDividend.length() - Integer.toString(difference).length();
                }
            }
        } else {
            int difference = parseInt(prevPieceOfDividend) - parseInt(prevDeduction);

            return pieceOfDividend.length() + Integer.toString(difference).length();
        }
    }
}
