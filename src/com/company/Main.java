package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String[] palabras = {"\\*<]:-DOo", ">:o\\)", "[^\\*]<]:-D"};
    private static String[] palabras2 = {"*<]:-DOo", ">:o)", "<]:-D"};
    private static int[] vecesEncontrado = new int[3];
    private static String[] nombres = {"Pare Noel", "Ren", "Follets"};
    private static File file = new File(System.getProperty("user.home") + "/Escritorio/santako.txt");

    public static void main(String[] args) throws IOException {
        regularExpression();
        noRegularExpression();
    }

    private static void regularExpression() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Pattern pattern;
        Matcher matcher;

        String linea = bufferedReader.readLine();

        while (linea != null) {
            int x = 0;
            resetArray();
            for (String palabra : palabras) {
                pattern = Pattern.compile(palabra);
                matcher = pattern.matcher(linea);
                while (matcher.find()) {
                    vecesEncontrado[x]++;
                }
                x++;
            }
            enseñarResultados(vecesEncontrado);
            linea = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    private static void noRegularExpression() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String linea = bufferedReader.readLine();
        while (linea != null) {
            resetArray();
            int x = 0;
            for (String palabra : palabras2) {
                for (int i = 0; i < linea.length(); i++) {
                    if (i < linea.length() - 1) {
                        for (int j = 0; j < palabra.length(); j++) {
                            if (linea.charAt(i + j) != palabra.charAt(j)) {
                                break;
                            }
                            if (j == palabra.length() - 1) {
                                vecesEncontrado[x]++;
                            }
                        }
                    }
                }
                x++;
            }
            vecesEncontrado[2] -= vecesEncontrado[0];
            enseñarResultados(vecesEncontrado);
            linea = bufferedReader.readLine();
        }

        bufferedReader.close();
    }

    private static void resetArray() {
        for (int i = 0; i < vecesEncontrado.length; i++) {
            vecesEncontrado[i] = 0;
        }
    }

    private static void enseñarResultados(int[] vecesEncontrado) {
        for (int i = 0; i < vecesEncontrado.length; i++) {
            // Siempre que haya un resultado
            if (vecesEncontrado[i] > 0) {
                System.out.print(nombres[i] + "(" + vecesEncontrado[i] + ") ");
            }
        }
    }
}
