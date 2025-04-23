
package com.ufu.esof;

public class EstadoPrata implements EstadoConta {
    @Override
    public void depositar(Conta conta, float quantia) {
        conta.setSaldo(conta.getSaldo() + quantia);
    }

    @Override
    public boolean sacar(Conta conta, float quantia) {
        float taxa = 2.0f;
        if (conta.getSaldo() >= quantia + taxa) {
            conta.setSaldo(conta.getSaldo() - quantia - taxa);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PRATA";
    }
}
