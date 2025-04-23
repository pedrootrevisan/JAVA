
package com.ufu.esof;

public class EstadoOuro implements EstadoConta {
    @Override
    public void depositar(Conta conta, float quantia) {
        float rendimento = quantia * 0.05f;
        conta.setSaldo(conta.getSaldo() + quantia + rendimento);
    }

    @Override
    public boolean sacar(Conta conta, float quantia) {
        if (conta.getSaldo() >= quantia) {
            conta.setSaldo(conta.getSaldo() - quantia);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "OURO";
    }
}
