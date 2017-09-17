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
        String pieceDividendLine = "";
        String prevPieceDivid = "";
        String prevDeduction = "";
        int lineNumber = 0;
        int leftPad = 0;

        String resultOfExpression = getQuotient(dividend, divisor);

        for (char dividendChar : addedToCharArray(dividend)) {
            pieceDividendLine += Character.toString(dividendChar);

            if (isSelectPieceOfTheDividend(pieceDividendLine, divisor)) {
                int deduction = getDeduction(pieceDividendLine, divisor);

                if (lineNumber == 0) {
                    resultColumn += visualisation.showFirstAnswerLine()
                            + "\n"
                            + visualisation.showSecondAnswerLine(Integer.toString(deduction), pieceDividendLine
                            , resultOfExpression)
                            + "\n"
                            + visualisation.showThirdAnswerLine(pieceDividendLine, resultOfExpression);
                } else {
                    leftPad += getLeftPad(prevPieceDivid, prevDeduction, lineNumber, pieceDividendLine);
                    resultColumn += visualisation.showTheRest(pieceDividendLine, Integer.toString(deduction), leftPad);
                }
                prevPieceDivid = pieceDividendLine;
                prevDeduction = Integer.toString(deduction);

                pieceDividendLine = getDifference(Integer.parseInt(pieceDividendLine), deduction);
                lineNumber ++;
            }
        }
        return resultColumn;
    }

    private char[] addedToCharArray(int dividend) {
        String lineOfNumbers = Integer.toString(dividend);

        return lineOfNumbers.toCharArray();
    }

    private boolean isSelectPieceOfTheDividend(String pieceOfDividend, int divisor) {
        return divisor <= parseInt(pieceOfDividend);
    }

    private String getQuotient(int someNumber, int divisor) {
        String pieceDividendLine = "";
        String methodResult = "";

        for (char dividendChar: addedToCharArray(someNumber)) {
            pieceDividendLine += Character.toString(dividendChar);

            if (isSelectPieceOfTheDividend(pieceDividendLine, divisor)) {
                int resultQuotient = Integer.parseInt(pieceDividendLine) / divisor;
                int deduction = resultQuotient * divisor;
                methodResult += Integer.parseInt(pieceDividendLine) / divisor;
                pieceDividendLine = getDifference(Integer.parseInt(pieceDividendLine), deduction);
            }
        }
        return methodResult;
        }

    private int getDeduction(String pieceDividendLine, int divisor) {
        String quotient = getQuotient(Integer.parseInt(pieceDividendLine), divisor);

        return Integer.parseInt(quotient) * divisor;
    }

    private String getDifference(int pieceOfDividend, int deduction) {
        int difference = pieceOfDividend - deduction;

        if (difference == 0) {
            return "";
        } else {
            return Integer.toString(difference);
        }
    }

    private int getLeftPad(String prevPieceDivid, String prevDeduction, int lineNumber, String pieceDividendLine) {
        if (getDifference(prevPieceDivid.length(), prevDeduction.length()).equals("")) {
            if (getDifference(Integer.parseInt(prevPieceDivid), Integer.parseInt(prevDeduction)).equals("")) {
                if (lineNumber == 1) {
                    return prevPieceDivid.length() + pieceDividendLine.length();
                } else {
                    return pieceDividendLine.length();
                }
            } else {
                int difference = Integer.parseInt(prevPieceDivid) - Integer.parseInt(prevDeduction);

                if (lineNumber == 1) {
                    if (prevPieceDivid.length() == Integer.toString(difference).length()) {
                        return pieceDividendLine.length();
                    } else {
                        return pieceDividendLine.length() + 1;
                    }
                } else {
                    return pieceDividendLine.length() - Integer.toString(difference).length();
                }
            }
        } else {
            int difference = Integer.parseInt(prevPieceDivid) - Integer.parseInt(prevDeduction);

            return pieceDividendLine.length() + Integer.toString(difference).length();
        }
    }
}
