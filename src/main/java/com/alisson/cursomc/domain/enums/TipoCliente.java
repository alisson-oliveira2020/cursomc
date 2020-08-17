package com.alisson.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa física"),
	PESSOAJURIDICA(2, "Pessoa jurídica");
	
	private int codigo;
	private String descricao;
	
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null)
			return null;
		
		for (TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod); 
	}
}
