/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luist
 */
public class Campo {
    
    private final int linha;
    private final int coluna;
    
    private boolean aberto;
    private boolean minado;
    private boolean marcado;
    
    private List<Campo> vizinhos = new ArrayList<>();
    private List<CampoObservador> observadores = new ArrayList<>();
    
    
    Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }
    
    public void registrarObservador(CampoObservador observador) {
    	observadores.add(observador);
    }
    
    private void notificarObservadores(CampoEvento evento) {
    	observadores.stream()
    		.forEach(observador -> observador.eventoOcorreu(this, evento));
    }
    
    boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = this.linha != vizinho.linha;
        boolean colunaDiferente = this.coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;
        
        int deltaLinha = Math.abs(this.linha - vizinho.linha);
        int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;
        
        boolean vizinhoColunaLinha = deltaGeral == 1 && !diagonal;
        boolean vizinhoDiagonal = deltaGeral == 2 && diagonal;
        
        if(vizinhoColunaLinha){
            vizinhos.add(vizinho);
            return true;
        }else if(vizinhoDiagonal){
            vizinhos.add(vizinho);
            return true;
        }else return false;

    }
    
    public void alternarMarcacao() {
    	if(!aberto) {
    		marcado = !marcado;
    		
    		if(marcado) {
    			notificarObservadores(CampoEvento.MARCAR);
    		}else {
    			notificarObservadores(CampoEvento.DESMARCAR);
    		}
    	}
    }
    
    public boolean abrir() {
    	
    	if(!aberto && !marcado) {
    		aberto = true;
    		
    		if(minado) {
    			notificarObservadores(CampoEvento.EXPLODIR);
    			return true;
    		}
    		
    		setAberto(true);
    		
    		if(vizinhancaSegura()) {
    			vizinhos.forEach(v -> v.abrir());
    		}
    		
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public boolean vizinhancaSegura() {
    	return vizinhos.stream()
    			.noneMatch(v -> v.minado);
    }
    
    
    public boolean isMarcado() {
    	return marcado;
    }
    
    
    
    void setAberto(boolean aberto) {
		this.aberto = aberto;
		
		if(aberto) {
    		notificarObservadores(CampoEvento.ABRIR);

		}
	}

	void minar() {
    	minado = true;
    }

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	
    boolean alcancarObjetivo() {
    	
    	boolean desvendado = !minado && aberto;
    	boolean protegido = minado && marcado;
    	
    	return desvendado || protegido;
    }
    
    public int minasNaVizinhanca() {
    	return (int) vizinhos.stream()
    			.filter(v -> v.minado)
    			.count();
    }
    
    
    void reiniciar() {
    	aberto = false;
    	minado = false;
    	marcado = false;
    	notificarObservadores(CampoEvento.REINICIAR);
    }
    
       
    
    
    
    
}
