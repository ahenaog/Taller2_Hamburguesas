package uniandes.dpoo.taller2.comportamiento;

import java.util.ArrayList;

public class Combo implements Producto{
	
	/*
	 * Atributos
	 */
	
	private double descuento;
	private String nombreCombo;
	
	private ArrayList<ProductoMenu> itemsCombo;
	
	/*
	 * Constructores
	 */
	
	public Combo(String nombre, double descuento) {
		this.descuento = descuento;
		this.nombreCombo = nombre;
		itemsCombo = new ArrayList<ProductoMenu>();
	}
	
	/*
	 * Métodos
	 */
	
	public void agregarItemACombo(ProductoMenu itemCombo) {
		
		itemsCombo.add(itemCombo);
		
	}

	@Override
	public int getPrecio() {
		
		int precio = 0;
		for (Producto item : itemsCombo) {
			precio += item.getPrecio();
		}
		
		int total = (int) (precio - (precio*descuento));
		
		return total;
	}

	@Override
	public String getNombre() {
		return nombreCombo;
	}

	@Override
	public String generarTextoFactura() {
		return nombreCombo + "\t" + getPrecio() + "/n";
	}
	
	

}
