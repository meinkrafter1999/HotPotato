/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;


public enum GameStatus {

	LOBBY(1),
	SCHUTZ(2),
	GAME(3),
	END(4);
	
	private int i;
	
	private GameStatus(int i){
		
		if(i > 0 && i < 5){
			
			this.i = i;
			
		}
		
	}
	
	
	public int getGameStatus(){
		
		return this.i;
		
	}
	
	public void setGameStatus(){
		
		HotPotato.status = this;
		
	}
	
}
