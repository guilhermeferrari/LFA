package app;



public class MyAnalisadorSintatico extends AnalisadorSintatico {
	
	/**
	 * @param _nomeArquivoEntrada Nome do arquivo utilizado para fazer a analise lexica/sintatica.
	 */
	
	public MyAnalisadorSintatico(String _nomeArquivoEntrada) {
		super(_nomeArquivoEntrada);
	}

	/**
	 * Inicio do programa
	 */
	
	public void inicio() { // s
		multiplosComandos();
		reconhece(Token.EOF);
	}

	public void _while(){
		reconhece(Token.WHILE);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		bloco();
	}

	public void _do(){
		reconhece(Token.DO);
		bloco();
		reconhece(Token.WHILE);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		reconhece(Token.PV);
	}

	public void _if(){
		reconhece(Token.IF);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		bloco();
	}

	public void _for(){
		reconhece(Token.FOR);
		reconhece(Token.AP);
		exp_for();
		reconhece(Token.PV);
		expressao();
		reconhece(Token.PV);
		exp_for();
		reconhece(Token.FP);
		bloco();
	}

	public void _switch(){
		reconhece(Token.SWITCH);
		reconhece(Token.AP);
		expressao();
		reconhece(Token.FP);
		reconhece(Token.AC);
		loop_case();
		reconhece(Token.FC);

	}

	public void bloco(){
		if(proxTokenIs(Token.AC)){
			reconhece(Token.AC);
			multiplosComandos();
			if(proxTokenIs(Token.FC)){
				reconhece(Token.FC);
			}
			else {
				Token[] tokensEsperados = {Token.FC};
				throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
			}
		}
		else
			comando();
	}

	public void loop_case(){
		if(proxTokenIs(Token.CASE)){
			reconhece(Token.CASE);
			reconhece(Token.AS);
			caso();
			reconhece(Token.AS);
			reconhece(Token.DP);
			
			multiplosComandos();
			
			if(proxTokenIs(Token.CASE)){
				loop_case();
			}
		}
	}

	public void exp_for(){
		if(proxTokenIs(Token.VAR)) {
			reconhece(Token.VAR);
			exp_for_aux();
		}
		else if(proxTokenIs(Token.OP_UN)){
			reconhece(Token.OP_UN);
			reconhece(Token.VAR);
		}
		else {
				Token[] tokensEsperados = {Token.VAR, Token.OP_UN};
				throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
			}
	}

	public void exp_for_aux(){
		if(proxTokenIs(Token.OP_BIN)){
			reconhece(Token.OP_BIN);
			expressao();
		}
		else if(proxTokenIs(Token.OP_UN)){
			reconhece(Token.OP_UN);
		}
		else
			{
				Token[] tokensEsperados = {Token.OP_UN, Token.OP_BIN};
				throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
			}

	}

	public void expressao(){
		if(proxTokenIs(Token.AP)){
			reconhece(Token.AP);
			expressao();
			reconhece(Token.FP);
			expressao_aux();
		}
		else if(proxTokenIs(Token.NEG)){
			reconhece(Token.NEG);
			expressao();
			expressao_aux();
		}
		else{
			variavel();
			expressao_aux();

		}
		//throw
	}

	public void expressao_aux(){
		if(proxTokenIs(Token.OP_BIN)){
			reconhece(Token.OP_BIN);
			expressao();
			expressao_aux();
		}
		else if(proxTokenIs(Token.OP_UN_BIN)){
			reconhece(Token.OP_UN_BIN);
			expressao();
			expressao_aux();
		}
	}

	public void variavel(){
		int ponto = 0;
		if(proxTokenIs(Token.OP_UN_BIN)) {
			reconhece(Token.OP_UN_BIN);
			variavel();
		}
		else if(proxTokenIs(Token.VAR)){
			reconhece(Token.VAR);
			var_aux();
		}
		else if(proxTokenIs(Token.P)){
			ponto = 1;
			reconhece(Token.P);
			if(proxTokenIs(Token.NUM)){
				reconhece(Token.NUM);
			}
			else{
				Token[] tokensEsperados = {Token.NUM};
				throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
			}
			if(proxTokenIs(Token.P)){
				System.out.println("Erro de ponto extra.");
				Token[] tokensEsperados = {Token.NUM};
				throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
			}
		}
		else if(proxTokenIs(Token.NUM)){
			reconhece(Token.NUM);			
			if(proxTokenIs(Token.P)){
				if(ponto == 1){
					System.out.println("Erro de ponto extra.");
					Token[] tokensEsperados = {Token.NUM};
					throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
				}
				ponto = 1;
				reconhece(Token.P);
				reconhece(Token.NUM);
				if(proxTokenIs(Token.P)){
					System.out.println("Erro de ponto extra.");
					Token[] tokensEsperados = {Token.NUM};
					throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
				}
				
			}
			else if(proxTokenIs(Token.NUM)){
				reconhece(Token.NUM);
			}
		}
		else if(proxTokenIs(Token.OP_UN)){
			reconhece(Token.OP_UN);
			reconhece(Token.VAR);
		}
		else{
			Token[] tokensEsperados = {Token.VAR, Token.OP_UN, Token.NUM};
			throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
		}
	}
	
	/***
	 * 
	 *  
	 * 
	 */

	public void var_aux(){
		if(proxTokenIs(Token.OP_UN)){
			reconhece(Token.OP_UN);
		}
	}

	public void caso(){
		if(proxTokenIs(Token.VAR)){ /////// CONFIRMAR SE É VAR MESMO, CONFERIR NA GRAMATICA
			reconhece(Token.VAR);
		}
		else if(proxCaractereIs(DIGITOS)){
			reconhece(Token.NUM);
		}
		else{
			Token[] tokensEsperados = {Token.VAR, Token.NUM};
			throw new ErroSintatico(this.scanner.tokenReconhecido, tokensEsperados, getLinha(), getColuna());
		}

	}

	public void comando(){
		if(proxTokenIs(Token.IF)){
			_if();
		}
		else if(proxTokenIs(Token.WHILE)){
			_while();
		}
		else if(proxTokenIs(Token.DO)){
			_do();
		}
		else if(proxTokenIs(Token.FOR)){
			_for();
		}
		else if(proxTokenIs(Token.SWITCH)){
			_switch();
		}
		else if(proxTokenIs(Token.PV)){
			reconhece(Token.PV);
			//leProxToken();
		}
		else{
			expressao();
			reconhece(Token.PV);
		}
	}
	
	
	public boolean isVariavel(){
		boolean flag = false;
		
		if(proxTokenIs(Token.VAR)){
			flag = true;			
		}
		else if(proxTokenIs(Token.NUM)){
			flag = true;
		}
		else if(proxTokenIs(Token.OP_UN)){
			flag = true;
		}
		
		return flag;
	}

	public boolean isExpressao(){
		boolean flag = false;
		
		if(isVariavel()){
			flag = true;
		}
		else if(proxTokenIs(Token.AP)){
			flag = true;
		}
		else if(proxTokenIs(Token.NEG)){
			flag = true;
		}
		
		return flag;
	}
	
	public boolean isComando(){
		boolean flag = false;
		
		if(proxTokenIs(Token.IF)){
			flag = true;
		}
		else if(proxTokenIs(Token.WHILE)){
			flag = true;
		}
		else if(proxTokenIs(Token.DO)){
			flag = true;
		}
		else if(proxTokenIs(Token.FOR)){
			flag = true;
		}
		else if(proxTokenIs(Token.SWITCH)){
			flag = true;
		}
		else if(proxTokenIs(Token.PV)){
			flag = true;
		}
		else if(isExpressao()){
			flag = true;
		}
		
		return flag;
	}
	
	
	/**
	 * Responsável por gerenciar para onde sera dirigido o fluxo do programa de acordo com os tokens de entrada,
	 * sendo eles (WHILE, DO, FOR, SWITCH, IF, VAR, AP, OPUNITARIO, INT e REAL), ele pode executar apenas um comando ou uma
	 * lista de comandos.
	 * @throws ErroSintatico É lançado um objeto avisando caso nenhum dos tokens seja encontrado.
	 * 
	 * Indica o fluxo a ser tomado pelo progama
	 * 
	 */
	
	public void multiplosComandos(){
		if(isComando()){
			comando();
			multiplosComandos();
		}	
		
	}
}