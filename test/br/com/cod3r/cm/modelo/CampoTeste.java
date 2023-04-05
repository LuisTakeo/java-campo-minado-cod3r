/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cod3r.cm.modelo;


import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author luist
 */
public class CampoTeste {
    
    Campo campo;
    
    @BeforeEach
    void iniciarCampo() {
    	campo = new Campo(3, 3);
    }
    
    @Test
    void testeVizinhoEsquerda() {
        Campo vizinhoEsquerda = new Campo(3, 2);
        boolean resultadoEsquerda = campo.adicionarVizinho(vizinhoEsquerda);
        
        assertTrue(resultadoEsquerda);
    }
    
    @Test
    void testeVizinhoDireita() {
        Campo vizinhoDireita = new Campo(3, 4);
        boolean resultadoDireita = campo.adicionarVizinho(vizinhoDireita);
        
        assertTrue(resultadoDireita);
    }
    
    @Test
    void testeVizinhoCima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        assertTrue(resultado);
    }
    
    @Test
    void testeVizinhoBaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        assertTrue(resultado);
    }
    
    @Test
    void testeVizinhoDiagonal() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        assertTrue(resultado);
    }
    
    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        
        assertFalse(resultado);
    }
    
    @Test
    void valorMarcacaoPadrao() {
    	
    	assertFalse(campo.isMarcado());
    }
    
    @Test
    void alterarMarcacao() {
    	campo.alternarMarcacao();
    	assertTrue(campo.isMarcado());
    }
    
    @Test
    void alterarMarcacaoDuasCalls() {
    	campo.alternarMarcacao();
    	campo.alternarMarcacao();
    	assertFalse(campo.isMarcado());
    }
    
    @Test
    void abrirNaoMinadoNaoMarcado() {
    	assertTrue(campo.abrir());
    }
    
    @Test
    void abrirNaoMinadoMarcado() {
    	campo.alternarMarcacao();
    	assertFalse(campo.abrir());
    }
    
    @Test
    void abrirMinadoMarcado() {
    	campo.alternarMarcacao();
    	campo.minar();
    	assertFalse(campo.abrir());
    }
    
    @Test
    void abrirMinadoNaoMarcado() {
    	campo.minar();
    	
    	assertThrows(ExplosaoException.class, 
    			() -> campo.abrir());
    }
    
    
    
}
