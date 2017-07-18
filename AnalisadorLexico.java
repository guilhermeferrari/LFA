package app;
import java.io.FileReader;
import java.io.IOException;

public class AnalisadorLexico extends Analisador{
	protected char proxCaractere; // caractere disponível no cabeçote de leitura
	protected int linha = 1, coluna = 0; // linha atual do arquivo fonte
	protected StringBuffer entrada = new StringBuffer(); // armazena o conteúdo do arquivo
	protected int posicao = 0; // posição do caractere a ser lido na entrada
	protected Token tokenReconhecido; // último token lido
	// transfere o arquivo para o buffer ‘entrada’

	public AnalisadorLexico(String _nomeArquivoEntrada){
		super(_nomeArquivoEntrada);
		try {
			FileReader file = new FileReader(_nomeArquivoEntrada);
			int c;
			while ((c = file.read()) != -1) {
				this.entrada.append((char) c); //joga todos os dados do arquivo em entrada
			}
			file.close();
			leProxCaractere();
		} catch (IOException e) {
			throw new RuntimeException("Erro de leitura no arquivo " + _nomeArquivoEntrada);
		}
	}

	// lê o próximo caractere do buffer. Se fim, retorna EOF
	// avança o ponteiro de leitura 1 posição
	public void leProxCaractere() {
		try {
			this.proxCaractere = this.entrada.charAt(this.posicao++); //insere um caracter em proxCaractere
		} catch (IndexOutOfBoundsException e) {
			this.proxCaractere = EOF;
		}
	}

	// verifica se o próximo caractere é um dos que estão em ‘s’
	// NÂO avança o ponteiro de leitura
	public boolean proxCaractereIs(String s) {
		if (s.indexOf(this.proxCaractere) != -1){
			return true;
		}
		else
			return false;
	}
	

}