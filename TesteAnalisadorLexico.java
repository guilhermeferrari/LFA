package app;
public class TesteAnalisadorLexico{
	static public MyAnalisadorLexico scanner;

	public static void main(String[] args){
		try {
			if (args. length != 1)
				throw new RuntimeException( "esqueceu de escrever o nome do arquivode entrada! \n" + "No Eclipse insira em: Run - Open Run Dialog- Arguments" );
			scanner = new MyAnalisadorLexico(args[0]);
			
			// chama a m�quina de Moore v�rias vezes at� encontrar o fim de arquivo
			do{
				scanner.q0();
				System. out .println( scanner.tokenReconhecido );
			}while( scanner.tokenReconhecido != Constantes.Token.EOF );
			
			System. out .println( "An�lise lexica realizada com sucessono arquivo " + scanner . nomeArquivoEntrada );
		}
		catch (ErroLexico e){
			System. out .println( "Erro l�xico: " +e.toString());
		}
		catch (RuntimeException e){
			System. out .println( "Erro: " +e.getMessage());
		}
	}
}