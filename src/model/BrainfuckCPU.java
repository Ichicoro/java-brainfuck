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

    private String srcCode = "";



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

    public char getMemory() {
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

    /* public void runCode(String code) {
        for (int i = 0; i < code.length(); i++) {
            System.out.println(this.machineState());
            if (!inaloop) {
                this.executeCommand(code.charAt(i));
            } else {
                inaloop = false;
                this.runCode(getNextLoop(code, i-2));
                i = code.indexOf(']', i);
            }
        }
    } */

    public void runCode(String code) {
        for (int i = 0; i < code.length(); i++) {
            //System.out.println(this.machineState());
            if (this.executeCommand(code.charAt(i)) == 1) {
                this.runCode(getNextLoop(code, i));
                i = code.indexOf(']', i)+1;
            }
        }
    }

    public void printPtrByte() {
        System.out.print((int) this.getMemory());
    }

    public int executeCommand(char cmd) {
        if (cmd != '[') {
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
                //case '[': this.setCycleState(this.getPointer());
                //    this.inaloop = true;
                //    break;
                //case ']': this.setCycleState(-1);
                //    this.inaloop = false;
                //    break;
            }
            return 0;
        } else {
            return 1; // 1=[ 2=]
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
