package uniandes.dpoo.taller2.comportamiento;

import java.util.ArrayList;

public class ProductoAjustado implements Producto{
	
	/*
	 * Atributos
	 */
	
	private ProductoMenu base;
	
	private ArrayList<Ingrediente> agregados;
	
	private ArrayList<Ingrediente> eliminados;
	
	/*
	 * Constructores
	 */
	
	public ProductoAjustado(Producto base) {
		this.base = (ProductoMenu) base;
		agregados = new ArrayList<Ingrediente>();
		eliminados = new ArrayList<Ingrediente>();
	}
	
	/*
	 * Metodos
	 */
	
	public void agregarIngrediente(Ingrediente ingrediente) {
		agregados.add(ingrediente);
	}
	
	public void eliminarIngrediente(Ingrediente ingrediente) {
		eliminados.add(ingrediente);
	}
	
	@Override
	public int getPrecio() {
		int precioParcial = 0;
		
		for (Ingrediente item : agregados) {
			precioParcial += item.getCostoAdicional();
		}
		return base.getPrecio() + precioParcial;
	}

	@Override
	public String getNombre() {
		String nombreBase = base.getNombre();
		String adiciones = "";
		String sin = "";
		
		if (agregados.size() > 0 ) {
			
			for (Ingrediente item : agregados) {
				adiciones += " adicion de " + item.getNombre();
			}
		}
		if (eliminados.size() > 0 ) {
			
			for (Ingrediente item : eliminados) {
				sin += " sin " + item.getNombre();
			}
		}
		return nombreBase + adiciones + sin;
	}

	@Override
	public String generarTextoFactura() {
		return getNombre() + "\t" + getPrecio() + "\n";
	}
	



}
