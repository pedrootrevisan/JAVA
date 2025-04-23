
package com.ufu.esof;

public class EstadoVermelho implements EstadoConta {
    @Override
    public void depositar(Conta conta, float quantia) {
        conta.setSaldo(conta.getSaldo() + quantia);
    }

    @Override
    public boolean sacar(Conta conta, float quantia) {
        return false;
    }

    @Override
    public String toString() {
        return "VERMELHO";
    }
}
