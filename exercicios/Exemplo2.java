package exercicios;

import java.util.ArrayList;
import java.util.List;

class Documento {
	private List<Conteudo> conteudos = new ArrayList<>();

	public void adicionarConteudo(Conteudo conteudo) {
		conteudos.add(conteudo);
	}

	public void aplicarVisitante(VisitanteDeDocumento visitante) {
		for (Conteudo conteudo : conteudos) {
			conteudo.accept(visitante);
		}
	}
}

abstract class Conteudo {
	public abstract void accept(VisitanteDeDocumento visitante);
}

class ConteudoDeTexto extends Conteudo {
	@Override
	public void accept(VisitanteDeDocumento visitante) {
		visitante.visitar(this);
	}
}

class ConteudoDeImagem extends Conteudo {
	@Override
	public void accept(VisitanteDeDocumento visitante) {
		visitante.visitar(this);
	}
}

interface VisitanteDeDocumento {
	void visitar(ConteudoDeTexto conteudoDeTexto);

	void visitar(ConteudoDeImagem conteudoDeImagem);
}

class VisitanteDeExibicao implements VisitanteDeDocumento {
	@Override
	public void visitar(ConteudoDeTexto conteudoDeTexto) {
		System.out.println("Exibindo conteúdo de texto.");
	}

	@Override
	public void visitar(ConteudoDeImagem conteudoDeImagem) {
		System.out.println("Exibindo conteúdo de imagem.");
	}
}

class VisitanteDeContagem implements VisitanteDeDocumento {
	private int contagemDeTexto = 0;
	private int contagemDeImagem = 0;

	@Override
	public void visitar(ConteudoDeTexto conteudoDeTexto) {
		contagemDeTexto++;
	}

	@Override
	public void visitar(ConteudoDeImagem conteudoDeImagem) {
		contagemDeImagem++;
	}

	public int getContagemDeTexto() {
		return contagemDeTexto;
	}

	public int getContagemDeImagem() {
		return contagemDeImagem;
	}
}

public class Exemplo2 {
	public static void main(String[] args) {
		Documento doc = new Documento();
		doc.adicionarConteudo(new ConteudoDeTexto());
		doc.adicionarConteudo(new ConteudoDeImagem());

		VisitanteDeExibicao visitanteDeExibicao = new VisitanteDeExibicao();
		VisitanteDeContagem visitanteDeContagem = new VisitanteDeContagem();

		doc.aplicarVisitante(visitanteDeExibicao);
		doc.aplicarVisitante(visitanteDeContagem);

		System.out.println("Contagem de conteúdos de texto: " + visitanteDeContagem.getContagemDeTexto());
		System.out.println("Contagem de conteúdos de imagem: " + visitanteDeContagem.getContagemDeImagem());
	}
}
