import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Zirgas {

    //static Stack<Integer> r = new Stack<>(); // R1...
    static int N;  // Šachmatų lentos dydis
    static int n;
    //static int NN = 25;	  // Langelių skaičius šachmatų lentoje 5x5

    static int[][] LENTA;/* = new int[N][N]; */   // Globali duomenų bazė
    static int[] CX = new int[9];            // Produkcijų aibė visada iš 8 prod
    static int[] CY = new int[9];
    static int i;
    static int j;
    static boolean YRA;

    static int counter = 0;
    static int rCounter = 1;
    static int pointCounter = 0;
    static int previousR = 0;

    static BufferedWriter output = null;
    static File file = new File("result.txt");

    public static void inicializuoti() {
        // 1) Suformuojama produkcijų aibė
        CX[1] = 2; CY[1] = 1;
        CX[2] = 1; CY[2] = 2;
        CX[3] = -1; CY[3] = 2;
        CX[4] = -2; CY[4] = 1;
        CX[5] = -2; CY[5] = -1;
        CX[6] = -1; CY[6] = -2;
        CX[7] = 1; CY[7] = -2;
        CX[8] = 2; CY[8] = -1;
        LENTA = new int[N][N];
        // 2) Inicializuojama globali duomenų bazė
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                LENTA[i][j] = 0;
            }
        }
    }

    public static void eiti(int L, int X, int Y) throws IOException {
        int K;
        int U, V;
        K = 0;
        do {
            K = K + 1;
            U = X + CX[K]; V = Y + CY[K];
            System.out.println();
            output.write('\n');
            System.out.print(String.format("%8s", ++counter) + ") ");

            --counter;
            output.write(String.format("%8s", ++counter) + ") ");

            for (int i = 0; i < pointCounter; i++) {
                System.out.print(".");
                output.write(".");
            }
            System.out.print("R" + K + ". U=" + U + ", V=" + V + ". L=" + L + ".");
            output.write("R" + K + ". U=" + U + ", V=" + V + ". L=" + L + ".");
            if ((U >= 1) && (U < N) && (V >= 1) && (V < N)) {
                if (LENTA[U][V] == 0) {
                    System.out.print(" Laisva. ");
                    output.write(" Laisva. ");
                    LENTA[U][V] = L;
                    System.out.print("LENTA [" + U + "," + V + "] := " + L + ".");
                    output.write("LENTA [" + U + "," + V + "] := " + L + ".");
                    previousR = rCounter;
                    rCounter = 1;
                    ++pointCounter;
                    if (L < (n) * (n)) {
                        //r.push(previousR);
                        eiti(L + 1, U, V);
                        if (!YRA) {
                            LENTA[U][V] = 0;
                            output.write(" Backtrack.");
                            System.out.print(" Backtrack.");
                            //rCounter = r.pop();
                            --pointCounter;
                        }
                    }else {
                        YRA = true;
                    }
                } else{
                    System.out.print(" Siūlas.");
                    output.write("  Siūlas.");
                }
            } else {
                System.out.print(" Už krašto.");
                output.write(" Už krašto.");
            }
            ++rCounter;
        }while(!(YRA || (K == 8)));
    }

    public static void main(String[] args) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            output = new BufferedWriter(new FileWriter(file));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Įveskite lentos dydį ");
            n = scanner.nextInt();
            if (n > 0 && n <= 8) {
                N = n + 1;
                System.out.print("Įveskite pradinę žirgo padėtį (x, y) ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if((x > 0 && x <= n) && (y > 0 && y <= n)) {
                    System.out.println("1 DALIS. Duomenys");
                    output.write("1 DALIS. Duomenys ");
                    output.write('\n');
                    System.out.println(String.format("%4s", 1) + ") Lenta " + (n) + "x" + (n) + ".");
                    output.write(String.format("%4s", 1) + ") Lenta " + (n) + "x" + (n) + ".");
                    output.write('\n');
                    System.out.println(String.format("%4s", 2) + ") Pradinė žirgo padėtis " + "X=" + x + ", Y=" + y + ". L=1.");
                    System.out.println();
                    output.write(String.format("%4s", 2) + ") Pradinė žirgo padėtis " + "X=" + x + ", Y=" + y + ". L=1.");
                    output.write('\n');
                    System.out.println("2 DALIS. Vykdymas");
                    output.write('\n');
                    output.write("2 DALIS. Vykdymas");
                    output.write('\n');

                    // Paruošiama globali duomenų bazė ir užpildoma produkcijų aibė
                    inicializuoti();
                    YRA = false;
                    LENTA[x][y] = 1;
                    eiti(2, x, y);
                    System.out.println();
                    System.out.println();
                    output.write('\n');
                    output.write('\n');
                    System.out.println("3 DALIS. Rezultatai");
                    output.write("3 DALIS. Rezultatai");
                    output.write('\n');
                    if (YRA) {
                        System.out.println(String.format("%4s", 1) + ") Apėjimas rastas");
                        output.write(String.format("%4s", 1) + ") Apėjimas rastas");
                        output.write('\n');
                        System.out.println(String.format("%4s", 2) + ") Apėjimas pseudografika");
                        output.write(String.format("%4s", 2) + ") Apėjimas pseudografika");
                        output.write('\n');
                        System.out.println();
                        output.write('\n');
                        System.out.println("   Y, V ^");
                        output.write("   Y, V ^");
                        output.write('\n');
                        for (int i = N - 1; i > 0; i--) {
                            System.out.print("      " + i + " | ");
                            output.write("      " + i + " | ");
                            for (int j = 1; j < N; j++) {
                                System.out.print(String.format("%2s", LENTA[j][i]) + "  ");
                                output.write(String.format("%2s", LENTA[j][i]) + "  ");
                            }
                            System.out.println();
                            output.write('\n');
                        }
                        System.out.println("        -------------------------------------> X, U");
                        output.write("        -------------------------------------> X, U");
                        output.write('\n');
                        System.out.print("          ");
                        output.write("          ");
                        for (int j = 1; j < N; j++) {
                            System.out.print(String.format("%2s", j) + "  ");
                            output.write(String.format("%2s", j) + "  ");
                        }
                    } else {
                        System.out.println("Kelias neegzistuoja");
                        output.write(String.format("%2s", j) + "  ");
                        output.write('\n');
                    }
                }else{
                    System.out.println("X ir Y gali buti 1 - " + n);
                    output.write("X ir Y gali buti 1 - " + n);
                    output.write('\n');
                }
            }else{
                System.out.println("Lentos dytis gali būti 1 - 8");
                output.write("Lentos dytis gali būti 1 - 8");
                output.write('\n');
            }
            long endTime = System.currentTimeMillis();
            long total = endTime - startTime;
            System.out.println();
            System.out.println();
            System.out.println("Vykdymo laikas " + ((total) / 1000) + "s");
            output.write('\n');
            output.write('\n');
            output.write('\n');
            output.write("Vykdymo laikas " + ((total) / 1000) + "s");
            output.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
