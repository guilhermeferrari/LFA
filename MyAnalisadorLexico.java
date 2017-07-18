package app;

public class MyAnalisadorLexico extends AnalisadorLexico {
	
	public MyAnalisadorLexico(String _nomeArquivoEntrada){
		super(_nomeArquivoEntrada);
	}

	public void q0(){
		if(this.proxCaractereIs(VAZIOS)){//espaco, quebra linha, tab
			if(this.proxCaractereIs(QLI)){
				//System.out.println(this.linha);
				this.linha++;
				this.coluna=0;
			}			
			leProxCaractere();
			this.coluna++;
			q0();
		}		
		//comeco do reconhecimento do comando while
		else if(this.proxCaractere == 'w'){ //comeco do while
			leProxCaractere();
			this.coluna++;
			q2();
		}
		else if(this.proxCaractere == 's'){	//comeco do switch
			leProxCaractere();
			this.coluna++;
			q7();
		}
		else if(this.proxCaractere == 'c'){ //comeco do case
			leProxCaractere();
			this.coluna++;
			q13(); 
		}
		else if(this.proxCaractere == 'f'){ //comeco do for
			leProxCaractere();
			this.coluna++;
			q17(); 
		}
		else if(this.proxCaractere == 'i'){ //comeco do if
			leProxCaractere();
			this.coluna++;
			q20(); 
		}
		else if(this.proxCaractere == 'd'){ //comeco do do
			leProxCaractere();
			this.coluna++;
			q22(); 
		}
		else if(this.proxCaractereIs(LETRAS)){//DEVE FICAR APOS TODAS AS CONDICOES DOS COMANDOS ACIMA
			leProxCaractere();
			this.coluna++;
			q1();
		}
		else if(this.proxCaractere == PONTO){
			leProxCaractere();
			this.coluna++;
			q24();
		}
		else if(this.proxCaractere == PV){
			leProxCaractere();
			this.coluna++;
			q25();
		}
		else if(this.proxCaractere == DP){
			leProxCaractere();
			this.coluna++;
			q26();
		}
		else if(this.proxCaractere == AS){
			leProxCaractere();
			this.coluna++;
			q27();
		}
		else if(this.proxCaractere == AP){	//abre parenteses
			leProxCaractere();
			this.coluna++;
			q28();
		}
		else if(this.proxCaractere == AC){	//abre parenteses
			leProxCaractere();
			this.coluna++;
			q30();
		}else if(this.proxCaractere == FC){	//abre parenteses
			leProxCaractere();
			this.coluna++;
			q31();
		}
		else if(this.proxCaractere == FP){	//fecha parenteses
			leProxCaractere();
			this.coluna++;
			q29();
		}
		else if(this.proxCaractere == NEG){
			leProxCaractere();
			this.coluna++;
			q32();
		}
		else if(this.proxCaractereIs(DIGITOS)){
			leProxCaractere();
			this.coluna++;
			q33();
		}
		else if(this.proxCaractere == POSITIVO){
			leProxCaractere();
			this.coluna++;
			q34();
		}
		else if(this.proxCaractere == HIFEN){
			leProxCaractere();
			this.coluna++;
			q37();
		}
		else if(this.proxCaractere == ASTERISCO){
			leProxCaractere();
			this.coluna++;
			q38();
		}
		else if(this.proxCaractere == BARRA){
			leProxCaractere();
			this.coluna++;
			q39();
		}
		else if(this.proxCaractere == AMPERSAND){
			leProxCaractere();
			this.coluna++;
			q40();
		}		
		else if(this.proxCaractere == VBAR){
			leProxCaractere();
			this.coluna++;
			q41();
		}
		else if(this.proxCaractere == PERCNT){
			leProxCaractere();
			this.coluna++;
			q42();
		}
		else if(this.proxCaractere == MENOR){
			leProxCaractere();
			this.coluna++;
			q43();
		}
		else if(this.proxCaractere == MAIOR){
			leProxCaractere();
			this.coluna++;
			q44();
		}
		else if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q45();
		}		
		else if(this.proxCaractere == UNDERLINE){
			leProxCaractere();
			this.coluna++;
			q47();
		}	
		else if(this.proxCaractere == EOF){	//fim do arquivo
			leProxCaractere();
			this.coluna++;
			estadoFinalArquivo();
		}				
		else{
			throw new ErroLexico(this.proxCaractere, DIGITOS + LETRAS + VAZIOS + PV + HIFEN);
		}
	}
		
	//estado que gera uma variavel
	public void q1(){
		if(this.proxCaractereIs(LETRAS) || this.proxCaractereIs(DIGITOS)){
			leProxCaractere();
			this.coluna++;
			q1();
		}
		else{
			this.tokenReconhecido = Token.VAR;
		}		
	}
	
	/*--------continuacao do reconhecimendo do comando WHILE------*/
	public void q2(){ 
		if(this.proxCaractere == 'h'){
			leProxCaractere();
			this.coluna++;
			q3();
		}
		else{
			q1();
		}
	}
	
	public void q3(){
		if(this.proxCaractere == 'i'){
			leProxCaractere();
			this.coluna++;
			q4();
		}
		else{
			q1();
		}
	}

	public void q4(){
		if(this.proxCaractere == 'l'){
			leProxCaractere();
			this.coluna++;
			q5();
		}
		else{
			q1();
		}
	}
	
	public void q5(){
		if(this.proxCaractere == 'e'){
			leProxCaractere();
			this.coluna++;
			q6();
		}
		else{
			q1();
		}
	}
	
	public void q6(){
		if(!this.proxCaractereIs(LETRAS)){
			this.tokenReconhecido = Token.WHILE;
		}
		else{
			q1();
		}
	}
	/* ------- fim do reconhecimento do WHILE*/
	
	/*continuacao do reconhecimento do comando SWITCH*/
	public void q7(){
		if(this.proxCaractere == 'w'){
			leProxCaractere();
			this.coluna++;
			q8();
		}
		else{
			q1();
		}
	}
	
	public void q8(){
		if(this.proxCaractere == 'i'){
			leProxCaractere();
			this.coluna++;
			q9();
		}
		else{
			q1();
		}
	}
	public void q9(){
		if(this.proxCaractere == 't'){
			leProxCaractere();
			this.coluna++;
			q10();
		}
		else{
			q1();
		}
	}
	public void q10(){
		if(this.proxCaractere == 'c'){
			leProxCaractere();
			this.coluna++;
			q11();
		}
		else{
			q1();
		}
	}
	public void q11(){
		if(this.proxCaractere == 'h'){
			leProxCaractere();
			this.coluna++;
			q12();
		}
		else{
			q1();
		}
	}
	public void q12(){
		if(!this.proxCaractereIs(LETRAS)){
			this.tokenReconhecido = Token.SWITCH;
		}
		else{
			q1();
		}
	}
	/* fim do reconhecimento do comando SWITCH*/

	/*--------continuacao do reconhecimendo do comando CASE------*/
	public void q13(){ 
		if(this.proxCaractere == 'a'){
			leProxCaractere();
			this.coluna++;
			q14();
		}
		else{
			q1();
		}
	}
	
	public void q14(){
		if(this.proxCaractere == 's'){
			leProxCaractere();
			this.coluna++;
			q15();
		}
		else{
			q1();
		}
	}

	public void q15(){
		if(this.proxCaractere == 'e'){
			leProxCaractere();
			this.coluna++;
			q16();
		}
		else{
			q1();
		}
	}
	
	public void q16(){
		if(!this.proxCaractereIs(LETRAS)){
			this.tokenReconhecido = Token.CASE;
		}
		else{
			q1();
		}
	}
	/* ------- fim do reconhecimento do CASE*/
	
	/*--------continuacao do reconhecimendo do comando FOR------*/
	public void q17(){
		
		if(this.proxCaractere == 'o'){
			leProxCaractere();
			this.coluna++;
			q18();
		}
		else{
			q1();
		}
	}
	
	public void q18(){
		
		if(this.proxCaractere == 'r'){
			leProxCaractere();
			this.coluna++;
			q19();
		}
		else{
			q1();
		}
	}
	
	public void q19(){
		
		if(!this.proxCaractereIs(LETRAS)){
			this.tokenReconhecido = Token.FOR;
		}
		else{
			q1();
		}
	}
	/* ------- fim do reconhecimento do FOR*/
	
	/*--------continuacao do reconhecimendo do comando IF------*/
	public void q20(){ 
		if(this.proxCaractere == 'f'){
			leProxCaractere();
			this.coluna++;
			q21();
		}
		else{
			q1();
		}
	}
	
	public void q21(){
		if(!this.proxCaractereIs(LETRAS)){
			this.tokenReconhecido = Token.IF;
		}
		else{
			q1();
		}
	}
	/* ------- fim do reconhecimento do IF*/
	
	/*--------continuacao do reconhecimendo do comando DO------*/
	public void q22(){ 
		if(this.proxCaractere == 'o'){
			leProxCaractere();
			this.coluna++;
			q23();
		}
		else{
			q1();
		}
	}
	
	public void q23(){
		if(!this.proxCaractereIs(LETRAS) || this.proxCaractereIs(VAZIOS)){
			this.tokenReconhecido = Token.DO;
		}
		else{
			q1();
		}
	}
	/* ------- fim do reconhecimento do DO*/
	
	/*Inicio do reconhecimento dos numeros reais*/
	public void q24(){		
		this.tokenReconhecido = Token.P;
	}
	
	public void q25(){
		this.tokenReconhecido = Token.PV;
	}
	public void q26(){
		this.tokenReconhecido = Token.DP;
	}
	public void q27(){
		this.tokenReconhecido = Token.AS;
	}
	public void q28(){
		this.tokenReconhecido = Token.AP;
	}
	
	public void q29(){
		this.tokenReconhecido = Token.FP;
	}
	
	public void q30(){
		this.tokenReconhecido = Token.AC;
	}
	
	public void q31(){
		this.tokenReconhecido = Token.FC;
	}
	
	public void q32(){
		if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.NEG;
		}
	}
	
	public void q33(){
		if(this.proxCaractereIs(DIGITOS)){
			leProxCaractere();
			this.coluna++;
			q33();
		}
		else if(this.proxCaractereIs(LETRAS)){
			throw new ErroLexico(this.proxCaractere, DIGITOS + PONTO + POSITIVO);
			
		}
		else{
			this.tokenReconhecido = Token.NUM;
		}
	}
	
	public void q34(){
		if(this.proxCaractere == POSITIVO){
			leProxCaractere();
			this.coluna++;
			q35();
		}
		else if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_UN_BIN;
		}
	}
	
	public void q35(){
		this.tokenReconhecido = Token.OP_UN;
	}
	
	public void q37(){
		if(this.proxCaractere == HIFEN){
			leProxCaractere();
			this.coluna++;
			q35();
		}
		else if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_UN_BIN;
		}
	}
	
	public void q38(){
		if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q39(){
		if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q40(){
		if((this.proxCaractere == AMPERSAND) || (this.proxCaractere == IGUAL)){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q41(){
		if((this.proxCaractere == IGUAL) || this.proxCaractere == VBAR){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q42(){
		if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q43(){
		if((this.proxCaractere == IGUAL) || this.proxCaractere == MENOR){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q44(){
		if((this.proxCaractere == IGUAL) || this.proxCaractere == MAIOR){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q45(){
		if(this.proxCaractere == IGUAL){
			leProxCaractere();
			this.coluna++;
			q46();
		}
		else{
			this.tokenReconhecido = Token.OP_BIN;
		}
	}
	
	public void q46(){
		this.tokenReconhecido = Token.OP_BIN;
	}
	
	public void q47(){
		this.tokenReconhecido = Token.UNL;
	}
	/*Fim do reconhecimento dos numeros reais*/
	
	
	public void estadoFinalArquivo() {
		this.tokenReconhecido = Token.EOF;
	}

}