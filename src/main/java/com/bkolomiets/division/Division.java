package com.bkolomiets.division;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Division {
    private boolean checkByNumber(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.hasNextInt();
        } else {
            return scanner.hasNextInt();
        }
    }

    private boolean checkByZero(int dividend) {
        if (dividend == 0) {
            System.out.println("You entered a zero. Try again!");
            return true;
        }
        return false;
    }

    private int returnValidNumber() {
        int resultValidation;

        while (true) {
            Scanner dividendScan = new Scanner(System.in);

            if (checkByNumber(dividendScan)) {
                resultValidation = dividendScan.nextInt();
                dividendScan.reset();
                break;
            } else {
                System.out.println("It's not a number! Try again.");
            }
        }
        return resultValidation;
    }

    private int getValidData() {
        int validNumber;

        while (true) {
            int inputData = returnValidNumber();
            if (!checkByZero(inputData)) {
                validNumber = inputData;
                break;
            }
        }
        return validNumber;
    }

    private char[] addedToCharArray(int dividend) {
        String lineOfNumbers = Integer.toString(dividend);

        return lineOfNumbers.toCharArray();
    }

    private boolean selectPieceOfTheDividend(String pieceOfDividend, int divisor) {
        return divisor <= parseInt(pieceOfDividend);
    }

    private int getQuotient(String number, int divisor) {
        int resultQuotient = parseInt(number) / divisor;
        int resultDeduction = resultQuotient * divisor;
        String resultQuotientLine = Integer.toString(resultQuotient);

        if (resultDeduction != Integer.parseInt(number)) {
            if (resultQuotientLine.charAt(resultQuotientLine.length() - 1) == '0') {
                resultQuotientLine = resultQuotientLine.substring(0, resultQuotientLine.length() - 1);
            }
        }
        return parseInt(resultQuotientLine);
    }

    private String getDeduction(String pieceDividendLine, int divisor) {
        int quotient = getQuotient(pieceDividendLine, divisor);
        int resultQuotient = quotient * divisor;

        return Integer.toString(resultQuotient);
    }

    private String getDifference(int pieceOfDividend, int deduction) {
        int difference = pieceOfDividend - deduction;
        String differenceLine;

        if (difference == 0) {
            differenceLine = "";
        } else {
            differenceLine = Integer.toString(difference);
        }
        return differenceLine;
    }

    public String visualisationAndDivision() {
        System.out.print("Enter a dividend: ");
        int dividend = getValidData();

        System.out.print("Enter a divisor:  ");
        int divisor = getValidData();

        Visualisation visualisation = new Visualisation(dividend, divisor);
        String resultOfTheMethod = "";

        int resultOfExpression = getQuotient(Integer.toString(dividend), divisor);
        String pieceDividendLine = "";
        int lineNumber = 0;

        for (char dividendChar : addedToCharArray(dividend)) {
            pieceDividendLine += Character.toString(dividendChar);

            if (selectPieceOfTheDividend(pieceDividendLine, divisor)) {
                String deduction = getDeduction(pieceDividendLine, divisor);

                if (lineNumber == 0) {
                    resultOfTheMethod += (visualisation.showFirstAnswerLine());
                    resultOfTheMethod += ("\n") + (visualisation.showSecondAnswerLine(deduction,
                            pieceDividendLine,
                            Integer.toString(resultOfExpression)));
                    resultOfTheMethod += ("\n") + (visualisation.showThirdAnswerLine(pieceDividendLine,
                            Integer.toString(resultOfExpression)));
                } else {
                    resultOfTheMethod += ("\n") + (visualisation.showTheRest(lineNumber, deduction, pieceDividendLine));
                }
                pieceDividendLine = getDifference(parseInt(pieceDividendLine), parseInt(deduction));
                lineNumber++;
            }
        }
        return resultOfTheMethod;
    }
}
