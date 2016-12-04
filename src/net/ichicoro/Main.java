package net.ichicoro;

import model.BrainfuckCPU;

public class Main {

    public static String helloworld = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
    public static BrainfuckCPU cpu;

    public static void main(String[] args) {
        cpu = new BrainfuckCPU(10000);
        cpu.load("+++>++>+-<--<---");

        cpu.run();
    }


}
