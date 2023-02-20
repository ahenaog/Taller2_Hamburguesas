package uniandes.dpoo.taller2.comportamiento;

public class ProductoMenu implements Producto {
	
	/*
	 * Atributos
	 */
	
	private String nombre;
	private int precioBase;
	
	/*
	 * Constructores
	 */
	
	public ProductoMenu( String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	/*
	 * Métodos
	 */
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public int getPrecio() {
		return precioBase;
	}

	@Override
	public String generarTextoFactura() {
		return nombre + "\t" + precioBase + "\n";
	}
}
