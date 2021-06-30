/**
 * @author bruno
 *
 */
public class Conta {

    private final double LIM_MAX = 100;
    private final int conta;
    private double limite = LIM_MAX;
    private double saldo;
    private double[] extrato = new double[10];
    private int operacao;


    public Conta(int numero, double saldo) {
        this.conta = numero;
        this.saldo = saldo;
    }

    
    public int getNumero() {
        return conta;
    }
    
    public double getSaldo() {
        return (saldo + limite);
    }
    
    public double getLimite() {
        return limite;
    }

    private void controleSaldoLimite(double valor){

        if(valor > 0){
            limite += valor;

            if(limite > LIM_MAX){
                saldo += (limite - LIM_MAX);
                limite = LIM_MAX;
            }
        }else{
            saldo += valor;

            if(saldo < 0){
                limite += saldo;
                saldo = 0;
            }
        }

        extrato[operacao] = valor;
        operacao++;
    }
    
    public boolean sacar(double valor) {
        if((valor > 0) && (valor <= getSaldo())){
            controleSaldoLimite(-valor);

            return true;
        }
        return false;
    }

   
    public boolean depositar(double valor) {
        if(valor > 0){
            controleSaldoLimite(valor);

            return true;
        }
        return false;
    }

    
    public boolean transferir(Conta destino, double valor) {
        if((valor > 0) && (valor <= getSaldo())){
            controleSaldoLimite(-valor);
            destino.depositar(valor);

            return true;
        }
        return false;
    }

    public double[] verExtrato() {
        return extrato;
    }
}