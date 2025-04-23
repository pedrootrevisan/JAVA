
package com.ufu.esof;

public class Conta {
    private float saldo;
    private EstadoConta estado;

    public Conta() {
        this.saldo = 0;
        this.estado = new EstadoPrata();
    }

    public void depositar(float quantia) {
        estado.depositar(this, quantia);
        atualizarEstado();
    }

    public boolean sacar(float quantia) {
        boolean resultado = estado.sacar(this, quantia);
        atualizarEstado();
        return resultado;
    }

    public void atualizarEstado() {
        if (saldo < 0) {
            estado = new EstadoVermelho();
        } else if (saldo <= 1000) {
            estado = new EstadoPrata();
        } else {
            estado = new EstadoOuro();
        }
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado.toString();
    }
}
