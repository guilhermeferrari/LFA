package app;
public class AnalisadorSintatico extends Analisador implements Constantes {
	protected MyAnalisadorLexico scanner;

	public AnalisadorSintatico(String _nomeArquivoEntrada) {
		this.scanner = new MyAnalisadorLexico(_nomeArquivoEntrada);
		// l� o primeiro token e o coloca no campo tokenReconhecido
		this.leProxToken();
	}

	public AnalisadorSintatico() {
		super();
	}

	// executa 1 vez a m�quina de Moore
	public void leProxToken() {
		this.scanner.q0();
	}

	// verifica se o pr�ximo token � t
	// avan�a o ponteiro para o pr�ximo token
	public void reconhece(Token t){
		if (t == this.scanner.tokenReconhecido)
			this.leProxToken();
		else
			throw new ErroSintatico(this.scanner.tokenReconhecido, t, getLinha(), getColuna());
	}

	// verifica se o pr�ximo token � t
	// N�O avan�a o ponteiro de leitura
	public boolean proxTokenIs(Token t){
		if (t == this.scanner.tokenReconhecido)
			return true;
		else
			return false;
	}
	
	public boolean proxCaractereIs(String s) {
		if (s.indexOf(scanner.proxCaractere) != -1){
			return true;
		}
		else
			return false;
	}
	
	public int getLinha() {
		return scanner.linha;
	}
	
	public int getColuna(){
		return scanner.coluna;
	}
	
}