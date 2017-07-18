package app;

public interface Constantes {
	enum Token {
		NUM, ATRIB, IDENT, EOF, PV, VAZIO, AP, FP, WHILE, DO, SWITCH, FOR, IF, VAR, CASE, P, OP_UN, OP_UN_BIN, OP_BIN, DP, AS, NEG, AC, FC, UNL
	};
	
	String DIGITOS = "0123456789", LETRAS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_", ATRIB = "-:", VAZIOS = " \r\n\t", QLI="\n";	
	
	char EOF = 0, HIFEN = '-', PV = ';', DP = ':', AP='(', FP=')', AC='{', FC='}', PONTO='.', NEG='!', IGUAL='=', POSITIVO='+',
			AMPERSAND='&', VBAR='|', PERCNT='%', MENOR='<', MAIOR='>', ASTERISCO='*', BARRA='/', AS='\'', UNDERLINE='_';
	
	String NOME_DEFAULT_ARQUIVO_ENTRADA = "entrada.txt";
}