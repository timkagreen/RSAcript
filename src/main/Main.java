package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static int p, q, En, n, e, d;
    private static ArrayList<Character> alphabet;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        alphabet = new ArrayList<>();
        alphabet.add(0, ' ');
        for (char c = 'А'; c <= 'я'; c++) {
            alphabet.add(c);
        }
        for (char c = '0'; c <= '9'; c++) {
            alphabet.add(c);
        }

        System.out.println("Введите взаимнопростые числа p и q");
        p = scanner.nextInt();
        q = scanner.nextInt();


        int s;
        if (p > q) {
            s = p;
            p = q;
            q = s;
        }
        isPrime(p, q);

        n = p * q;
        System.out.println("n = " + n);


        if (n < alphabet.size()){
            System.out.println("n не может быть меньше длины алфавита");
            System.exit(-2);
        }

        En = (p - 1) * (q - 1);
        System.out.println("Функция эйлера En = " + En);
        System.out.println("Выберете взаимнопростое с En = " + En + " из промежутака от 0 до " + n);

        e = scanner.nextInt();
        isPrime(e, En);
        d = -1;
        for (int i = 1; i < n; i++) {

            if ((i * En + 1) % e == 0 && d < 0) {
                d = (i * En + 1) / e;
                break;

            }
        }
        System.out.println("d = " + d);
        System.out.println("*******************************************");
        System.out.println("Открытый ключ (e, n) = (" + e + "," + n + ")");
        System.out.println("Закрытый ключ (d, n) = (" + d + "," + n + ")");
        System.out.println("*******************************************");

        System.out.println("Введите сообщение которое хотите закодировать ");
        String word = br.readLine();
        ArrayList<Integer> criptWord = new ArrayList<>();
        criptWord = cript(word);
        System.out.println(criptWord);
        word = deCript(criptWord);
        System.out.println(word);

    }

    public static int NOK(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            return NOK(q, (p % q));
        }
    }

    public static void isPrime(int p, int q) {
        int s = NOK(p, q);
        if (s != 1) {
            System.out.println("Числа не являются взаимнопростыми");
            System.exit(-1);
        } else {
            System.out.println("Числа являются взаимнопростыми");
        }
    }

    public static ArrayList<Integer> cript(String word) {
        ArrayList<Integer> criptWord = new ArrayList<>();
        int number;

        for (char c : word.toCharArray()) {
            number = power(alphabet.indexOf(c), e);
            criptWord.add(number);
        }

        return criptWord;
    }

    public static String deCript(ArrayList<Integer> criptWord) {
        String deCriptWord = "";
        int number;
        for (int i : criptWord) {
            number = power(i, d);
            deCriptWord += alphabet.get(number);
        }

        return deCriptWord;
    }

    public static int power(int a, int b) {
        int power = 1;
        for (int i = 1; i <= b; i++){
            power = (power * a) % n;
        }

        return power;
    }
}
