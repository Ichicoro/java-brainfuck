package model;
import java.util.Scanner;

import static model.Utils.clamp;
import static model.Utils.getNextLoop;

/**
 * Brainfuck CPU!
 */
public class BrainfuckCPU {

    private int memsize;
    private int pointer = 0;
    private char[] memory;
    private int codePointer = 0;
    private Scanner scan = new Scanner(System.in);
    private int cycleState = -1;
    private boolean inaloop = false;

    private int state = 0; // 0 = idle, -1 = left, +1 = right
    private int openBrackets = 0;

    private String srcCode = "";

    private static final int LOG_LEVEL = 0;

    public BrainfuckCPU() {
        this.setMemsize(10000);
        memory = new char[this.getMemsize()];
    }

    public BrainfuckCPU(int memsize) {
        this.setMemsize(memsize);
        memory = new char[memsize];
    }

    private int setMemsize(int memsize) {
        if (memsize < 1000) {
            memsize = 1000;
            return 1;
        } else {
            return 0;
        }
    }

    public int getMemsize() {
        return memsize;
    }

    public int getPointer() {
        return pointer;
    }

    private void setPointer(int pointer) {
        this.pointer = (int) clamp((float) pointer, 0, (float) this.getMemsize());
    }

    private char getMemory() {
        return memory[this.getPointer()];
    }

    private void setMemory(int pointer) {
        this.memory = memory;
    }

    private void setCycleState(int state) {
        this.cycleState = state;
    }

    private int getCycleState() {
        return this.cycleState;
    }

    public void load(String code) {
        this.srcCode = code;
    }

    public String getSrcCode() {
        return this.srcCode;
    }


    private int incPointer() {
        this.pointer++;
        return 0;
    }

    private int decPointer() {
        this.pointer--;
        return 0;
    }

    private int incByte() {
        this.memory[this.getPointer()] = (char) (this.getMemory() + 1);
        return 0;
    }

    private int decByte() {
        this.memory[this.getPointer()] = (char) (this.getMemory() - 1);
        return 0;
    }



    private void runCode(String code) {
        int i = 0;
        while (i < code.length()) {
            if (LOG_LEVEL == 2) {
                System.out.println(this.machineState());
            }
            switch (this.state) {
                case 0:
                    this.executeCommand(code.charAt(i));
                    break;
                case -1:
                    switch (code.charAt(i)) {
                        case ']':
                            this.openBrackets++;
                            break;
                        case '[':
                            if (this.openBrackets > 0)
                                this.openBrackets--;
                            else
                                state = 0;
                            break;
                        default:
                            break;
                    }
                    break;
                case +1:
                    switch (code.charAt(i)) {
                        case '[':
                            this.openBrackets++;
                            break;
                        case ']':
                            if (this.openBrackets > 0)
                                this.openBrackets--;
                            else
                                state = 0;
                            break;
                        default:
                            break;
                    }
                    break;
            }
            switch (state) {
                case 0:
                    i++;
                    break;
                case -1:
                    i--;
                    break;
                case +1:
                    i++;
                    break;
            }
        }
    }

    private void printPtrByte() {
        if (LOG_LEVEL > 0) {
            System.out.print((int) this.getMemory());
        } else {
            System.out.print((char) this.getMemory());
        }
    }

    private void executeCommand(char cmd) {
        switch (cmd) {
            case '>': this.incPointer();
                break;
            case '<': this.decPointer();
                break;
            case '+': this.incByte();
                break;
            case '-': this.decByte();
                break;
            case '.': this.printPtrByte();
                break;
            case ',': //this.getUserInput();
                break;
            case '[':
                if (this.getMemory() == 0) state = +1;
                break;
            case ']':
                if (this.getMemory() != 0) state = -1;
                break;
        }
    }

    public void run() {
        this.runCode(this.getSrcCode());
    }

    private String machineState() {
        String rtn;
        try {
            rtn = (this.getPointer() + "/"   /* + this.getCycleState() + "/"*/   + (int) this.getMemory());
        } catch (Exception e) {
            e.printStackTrace();
            rtn = "";
        }
        return rtn;
    }


}
