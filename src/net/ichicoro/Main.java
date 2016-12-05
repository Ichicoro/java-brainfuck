package net.ichicoro;

import model.BrainfuckCPU;

import static net.ichicoro.BFPrograms.mandelbrot;
import static net.ichicoro.BFPrograms.hanoi;
import static net.ichicoro.BFPrograms.infinitoh;

public class Main {

    public static String helloworld = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
    public static String testcode = "+++>++>+-<--<---";
    public static BrainfuckCPU cpu;



    public static void main(String[] args) {
        cpu = new BrainfuckCPU(10000);
        cpu.load(infinitoh);

        cpu.run();
    }


}
