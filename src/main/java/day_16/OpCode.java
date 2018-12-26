package day_16;

public class OpCode {
    private int a;
    private int b;
    private int c;

    public OpCode(final int a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int[] addr(final int[] register) {
        register[c] = register[a] + register[b];
        return register;
    }

    public int[] addi(final int[] register) {
        register[c] = register[a] + b;
        return register;
    }

    public int[] mulr(final int[] register) {
        register[c] = register[a] * register[b];
        return register;
    }

    public int[] muli(final int[] register) {
        register[c] = register[a] * b;
        return register;
    }

    public int[] banr(final int[] register) {
        register[c] = register[a] & register[b];
        return register;
    }

    public int[] bani(final int[] register) {
        register[c] = register[a] & b;
        return register;
    }

    public int[] borr(final int[] register) {
        register[c] = register[a] | register[b];
        return register;
    }

    public int[] bori(final int[] register) {
        register[c] = register[a] | b;
        return register;
    }

    public int[] setr(final int[] register) {
        register[c] = register[a];
        return register;
    }

    public int[] seti(final int[] register) {
        register[c] = a;
        return register;
    }

    public int[] gtir(final int[] register) {
        if (a > register[b]) {
            register[c] = 1;
        } else {
            register[c] = 0;
        }
        return register;
    }

    public int[] gtri(final int[] register) {
        if (register[a] > b) {
            register[c] = 1;
        } else {
            register[c] = 0;
        }
        return register;
    }

    public int[] gtrr(final int[] register) {
        if (register[a] > register[b]) {
            register[c] = 1;
        } else {
            register[c] = 0;
        }
        return register;
    }

    public int[] eqir(final int[] register) {
        if (a == register[b]) {
            register[c] = 1;
        } else {
            register[c] = 0;
        }
        return register;
    }

    public int[] eqri(final int[] register) {
        if (register[a] == b) {
            register[c] = 1;
        } else {
            register[c] = 0;
        }
        return register;
    }

    public int[] eqrr(final int[] register) {
        if (register[a] == register[b]) {
            register[c] = 1;
        } else {
            register[c] = 0;
        }
        return register;
    }
}
