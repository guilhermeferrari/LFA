package app;
public class ErroSintatico extends RuntimeException implements Constantes {
	private Token tokenEncontrado;
	private Token[] tokensEsperados;
	private int linha, coluna;

	public ErroSintatico(Token _tokenEncontrado, Token[] _tokensEsperados, int linha, int coluna) {
		this.tokenEncontrado = _tokenEncontrado;
		this.tokensEsperados = _tokensEsperados;
		this.linha = linha;
		this.coluna = coluna;
	}

	public ErroSintatico(Token _tokenEncontrado, Token _tokenEsperado, int linha, int coluna) {
		this.tokenEncontrado = _tokenEncontrado;
		this.tokensEsperados = new Token[1];
		tokensEsperados[0] = _tokenEsperado;
		this.linha = linha;
		this.coluna = coluna;
	}

	public String toString() {
		String listaDeTokensEsperados = "";
		for (int i = 0; i < this.tokensEsperados.length; i++)
			listaDeTokensEsperados += this.tokensEsperados[i] + " ";
		return "token encontrado: " + this.tokenEncontrado + "\nera(m) esperado(s): " + listaDeTokensEsperados+ "\nLinha: " + this.linha + "\nColuna: "+ (this.coluna-1);
	}
}