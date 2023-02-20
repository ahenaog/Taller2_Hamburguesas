package uniandes.dpoo.taller2.comportamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Restaurante {
	
	/*
	* Atributos
	*/
	
	private HashMap<String, Combo> combos;
	
	private HashMap<Integer, Pedido> pedidos;
	
	private Pedido pedidoEnCurso;
	
	private HashMap<String, ProductoMenu> menuBase;
	
	private HashMap<String, Ingrediente> ingredientes;
	
	/*
	* Constructores
    */
	
	public Restaurante() {
	}
	
	/*
	* Métodos
	*/
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		Pedido pedido = new Pedido(nombreCliente, direccionCliente);
		this.pedidoEnCurso = pedido;
		pedidos = new HashMap<Integer, Pedido>();
		
	}
	
	public HashMap<Integer, Pedido> getPedidos() {
		return pedidos;
	}
	
	public void cerrarYGuardarPedido() throws IOException {
		
		int idPedido = pedidoEnCurso.getIdPedido();
		pedidos.put(idPedido, pedidoEnCurso);
		
		String nombreArchivo = "C:\\Users\\Asus\\eclipse-workspace\\Taller2_Hamburguesas\\data\\Pedido" + idPedido + ".txt";
		File archivo = new File(nombreArchivo);
		
		pedidoEnCurso.guardarFactura(archivo);
		
		System.out.println("Su perdido se guardó con éxito, y su id es: " + idPedido);
		
	}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}
	
	public HashMap<String, Combo> getCombos(){
		return combos;
	}
	
	public HashMap<String, ProductoMenu> getMenuBase(){
		
		return menuBase;
		}
	
	public HashMap<String, Ingrediente> getIngredientes(){
		
		return ingredientes;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException {
		
		cargarMenu(archivoMenu);
		cargarIngredientes(archivoIngredientes);
		cargarCombos(archivoCombos);	
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws IOException {
		FileReader fr = new FileReader (archivoIngredientes);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		HashMap<String, Ingrediente> ingredientes = new HashMap<>();
		
		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			String nombre = partes[0];
			int costoAdicional = Integer.parseInt(partes[1]);
			
			Ingrediente ingrediente = new Ingrediente(nombre, costoAdicional);
			
			ingredientes.put(nombre, ingrediente);
			linea = br.readLine();
		}
		this.ingredientes = ingredientes;
		br.close();
	
	}
	
	private void cargarMenu(File archivoMenu) throws IOException {	
		FileReader fr = new FileReader (archivoMenu);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		HashMap<String, ProductoMenu> menuBase = new HashMap<>();
		
		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			String nombre = partes[0];
			int precioBase = Integer.parseInt(partes[1]);
			
			ProductoMenu producto = new ProductoMenu(nombre, precioBase);
			
			menuBase.put(nombre, producto);
			linea = br.readLine();
		}
		this.menuBase = menuBase;
		br.close();
	}
	
	private void cargarCombos(File archivoCombos) throws IOException {
		FileReader fr = new FileReader (archivoCombos);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		HashMap<String, Combo> combos = new HashMap<>();
		
		while (linea != null) {
			
			String[] partes = linea.split(";");
			
			String nombre = partes[0];
			
			String[] porciento = partes[1].split("%");
			double descuento = Integer.parseInt(porciento[0]) / 100;
			
			
			
			ProductoMenu item1 = menuBase.get(partes[2]);
			ProductoMenu item2 = menuBase.get(partes[3]);
			ProductoMenu item3 = menuBase.get(partes[4]);
			
			Combo combo = new Combo(nombre, descuento);
			
			combo.agregarItemACombo(item1);
			combo.agregarItemACombo(item2);
			combo.agregarItemACombo(item3);
			
			combos.put(nombre, combo);
			
			
			
		linea = br.readLine();
		}
		this.combos = combos;
		br.close();
		
	}
	
}
