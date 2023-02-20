package uniandes.dpoo.taller2.comportamiento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Pedido {
	
	/*
	 * Atributos
	 */

	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	
	private ArrayList<Producto> itemsPedido; 
	
	/*
	 * Constructores
	 */
	
	public Pedido( String nombreCliente, String direccionCliente) {
		this.direccionCliente = direccionCliente;
		this.nombreCliente = nombreCliente;
		numeroPedidos ++;
		idPedido = numeroPedidos;
		itemsPedido = new ArrayList<Producto>();
		
	}
	
	/*
	 * Métodos
	 */
	public void mostrarInformacionPedido() {

		System.out.println("\n");
		System.out.println("Id del pedido: " + getIdPedido());
		System.out.println("Nombre del cliente: " + nombreCliente);
		System.out.println("Direccion del cliente: " + direccionCliente);
		System.out.println("Productos....");
		for(Producto item : itemsPedido) {
			System.out.println(item.getNombre());
		}
	}
	
	public int getIdPedido() {
		return this.idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		
		itemsPedido.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido() {
		int precioNeto = 0;
		for (Producto item : itemsPedido) {
			precioNeto += item.getPrecio();
		}
		
		return precioNeto;
	}
	
	private int getPrecioTotalPedido() {
		int precioNeto = getPrecioNetoPedido();
		int precioIVA = getPrecioIVAPedido();
		return precioNeto + precioIVA;
	}
	
	private int getPrecioIVAPedido() {
		int precioNeto = getPrecioNetoPedido();
		double IVA = 0.19;
		
		int precioIVA = (int) IVA * precioNeto;
		
		return precioIVA;
	}
	
	private String generarTextoFactura() {
		
		String texto = "Nombre: " + nombreCliente + "\n" + "Direccion: " + direccionCliente + "\n";
		
		for (Producto item : itemsPedido) {
			texto += item.generarTextoFactura();
		}
		
		texto += "Precio neto: " + getPrecioNetoPedido() + "\n" + "Precio IVA: " + getPrecioIVAPedido() + "/n" + "Precio total: " + getPrecioTotalPedido() + "\n";
		
		return texto;
	}
	
	public void guardarFactura(File archivo) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
			bw.write(generarTextoFactura());
		}
	}
}
