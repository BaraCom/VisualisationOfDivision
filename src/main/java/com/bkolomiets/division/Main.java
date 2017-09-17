package com.bkolomiets.division;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the dividend: ");
        int dividend = scanner.nextInt();

        System.out.print("Enter the divisor:  ");
        int divisor = scanner.nextInt();
        scanner.close();

        Divider divider = new Divider();
        System.out.print(divider.divide(dividend, divisor));
    }
}
