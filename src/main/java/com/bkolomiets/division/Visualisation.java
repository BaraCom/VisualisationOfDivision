package com.bkolomiets.division;

import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static java.lang.Integer.parseInt;

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

    private String createHorizontalSplitter(String pieceOfDividend) {
        int pieceOfDividendLength = pieceOfDividend.length();
        StringBuilder concatHorizontalSplitter = new StringBuilder();

        for (int i = 0; i < pieceOfDividendLength; i++) {
            concatHorizontalSplitter.append(HORIZONTAL_SPLITTER);
        }
        return concatHorizontalSplitter.toString();
    }

    private int getLeftIndent(int dividend, String deduction) {
        return Integer.toString(dividend).length() - deduction.length();
    }

    private String getDifferenceLength(String resultOfExpression) {
        if (resultOfExpression.length() < Integer.toString(divisor).length()) {
            resultOfExpression = Integer.toString(divisor);
        }
        return resultOfExpression;
    }

    public String showFirstLine() {
        return dividend + " " + VERTICAL_SPLITTER + " " + divisor;
    }

    public String showSecondLine(String deduction, String pieceOfDividend, String resultOfExpression) {
        int leftIndent = getLeftIndent(parseInt(pieceOfDividend), deduction);
        resultOfExpression = getDifferenceLength(resultOfExpression);
        String resultMethod = "";

        if (leftIndent != 0) {
            resultMethod += leftPad(deduction, deduction.length() + leftIndent, " ")
                + leftPad(KNOT_SPLITTER
                    ,Integer.toString(dividend).length() - (leftIndent + deduction.length()) + 2
                    , " ")
                + " "
                + leftPad(createHorizontalSplitter(resultOfExpression)
                    , 1
                    , " ");
        } else {
            leftIndent = getLeftIndent(dividend, deduction);
            resultMethod += deduction
                + leftPad(KNOT_SPLITTER, leftIndent + 2, " ")
                + " "
                + createHorizontalSplitter(resultOfExpression);
        }
        return resultMethod;
    }

    public String showThirdLine(String pieceOfDividend, String resultOfExpression) {
        int leftIndent = getLeftIndent(dividend, pieceOfDividend);

        return createHorizontalSplitter(pieceOfDividend)
                + leftPad(VERTICAL_SPLITTER, leftIndent + 2, " ")
                + " "
                + resultOfExpression;
    }

    public String showRestLines(String pieceDividend, String deduction, int leftPad) {
        String horizontalSplitter = createHorizontalSplitter(pieceDividend);

        return "\n"
                + StringUtils.leftPad(pieceDividend
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
