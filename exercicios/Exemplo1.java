package exercicios;

class ManipuladorDeOperacoes {
    private MetodoDeCalculo metodo;

    public void setMetodo(MetodoDeCalculo metodo) {
        this.metodo = metodo;
    }

    public void executarCalculo(PortadorDeNumero portador) {
        metodo.calcular(portador);
    }
}

interface MetodoDeCalculo {
    void calcular(PortadorDeNumero portador);
}

class MetodoDeAdicao implements MetodoDeCalculo {
    @Override
    public void calcular(PortadorDeNumero portador) {
        System.out.println("Adicionando " + portador.getNumero());
    }
}

class MetodoDeMultiplicacao implements MetodoDeCalculo {
    @Override
    public void calcular(PortadorDeNumero portador) {
        System.out.println("Multiplicando " + portador.getNumero());
    }
}

class PortadorDeNumero {
    private int numero;

    public PortadorDeNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}

public class Exemplo1 {
    public static void main(String[] args) {
        PortadorDeNumero numHolder = new PortadorDeNumero(10);

        ManipuladorDeOperacoes handler = new ManipuladorDeOperacoes();

        handler.setMetodo(new MetodoDeAdicao());
        handler.executarCalculo(numHolder);

        handler.setMetodo(new MetodoDeMultiplicacao());
        handler.executarCalculo(numHolder);
    }
}
