package uniandes.dpoo.taller2.comportamiento;

public class Ingrediente {
	
	/*
	 * Atributos
	 */
	
	private String nombre;
	private int costoAdicional; 
	
	/*
	 * Constructores
	 */
	
	public Ingrediente(String nombre, int costoAdicional) {
		this.costoAdicional = costoAdicional;
		this.nombre = nombre;
	}
	
	/*
	 * Métodos
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	public int getCostoAdicional() {
		return costoAdicional;
	}

}
