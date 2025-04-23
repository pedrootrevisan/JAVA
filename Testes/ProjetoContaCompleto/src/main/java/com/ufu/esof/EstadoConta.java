
package com.ufu.esof;

public interface EstadoConta {
    void depositar(Conta conta, float quantia);
    boolean sacar(Conta conta, float quantia);
}
