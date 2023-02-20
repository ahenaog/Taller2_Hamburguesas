package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import uniandes.dpoo.taller2.comportamiento.Combo;
import uniandes.dpoo.taller2.comportamiento.Ingrediente;
import uniandes.dpoo.taller2.comportamiento.Pedido;
import uniandes.dpoo.taller2.comportamiento.Producto;
import uniandes.dpoo.taller2.comportamiento.ProductoAjustado;
import uniandes.dpoo.taller2.comportamiento.ProductoMenu;
import uniandes.dpoo.taller2.comportamiento.Restaurante;

public class Aplicacion {
	
	//---------------------------------------------------------------------------------------
	// Atributos
	
	private Restaurante restaurante;
	
	private HashMap<Integer, String> printCombos; 
	
	private HashMap<Integer, String> printMenuBase; 
	
	private HashMap<Integer, String> printMenuIngredientes; 
	
	
	//----------------------------------------------------------------------------
	// Métodos
	
	
	public static void main(String[] args) throws IOException {
		
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarOpcion();
		
	}
	
		
	public void ejecutarOpcion() throws IOException {
			
		System.out.println("Restaurante Las Hamburguesas");	
		
		cargarInformacion();
		
		
		boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenu();
				int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opción "));
				
				if (opcionSeleccionada == 1) {
					
					System.out.println("Bienvenido...");	
					String nombreCliente = input("Por favor ingrese su nombre");
					String Direccion = input("Por favor ingrese su dirección");
					System.out.println("\n");
					
					mostrarMenuProductosCombos();
					
					restaurante.iniciarPedido(nombreCliente, Direccion);
					
					
					int opcionProductos = Integer.parseInt(input("\nIngrese la opción que quiere agregar a su pedido"));
					
					seleccionarProducto(opcionProductos);
					
				}
				else if (opcionSeleccionada == 2) {
					int op = Integer.parseInt(input("\nIngrese el id del pedido que desea buscar"));
					buscarPedidoPorId(op);
				}
				
				
				else if (opcionSeleccionada == 3){
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
				
			}
			catch (NumberFormatException e){
				System.out.println("Debe seleccionar uno de los números de las opciones.");	
				
			}
		}
	}
	
	
	
	public Producto agregarIngrediente(int opcion1Ingredientes, Producto producto) {
		HashMap<String, Ingrediente> ingredientes = restaurante.getIngredientes();
		
		String nombre = printMenuIngredientes.get(opcion1Ingredientes);
		Ingrediente ingrediente =  ingredientes.get(nombre);
		
		ProductoAjustado productoAjustado = new ProductoAjustado(producto);
		
		productoAjustado.agregarIngrediente(ingrediente);
		return productoAjustado;
		
		
	}
	
	public Producto eliminarIngrediente(int opcion1Ingredientes, Producto producto) {
		HashMap<String, Ingrediente> ingredientes = restaurante.getIngredientes();
		
		String nombre = printMenuIngredientes.get(opcion1Ingredientes);
		Ingrediente ingrediente =  ingredientes.get(nombre);
		
		ProductoAjustado productoAjustado = new ProductoAjustado(producto);
		
		productoAjustado.eliminarIngrediente(ingrediente);
		
		return productoAjustado;
		
	}

	
	public void agregarCombo(int opcionSeleccionada) {
		HashMap<String, Combo> combos = restaurante.getCombos();
		
		String nombre = printCombos.get(opcionSeleccionada);
		Combo combo =  combos.get(nombre);
		
		Pedido pedido = restaurante.getPedidoEnCurso();
		pedido.agregarProducto(combo);
	}
	
	public void buscarPedidoPorId(int id) {
		HashMap<Integer, Pedido> pedidos = restaurante.getPedidos();
		Pedido pedido = pedidos.get(id);
		pedido.mostrarInformacionPedido();
	}
	
	public void opcionesMenuCuatro(int opcionProductos) throws IOException {
		if (opcionProductos == 1) {
			mostrarMenuProductosCombos();
			int opcion = Integer.parseInt(input("\nIngrese la opción que quiere agregar a su pedido"));
			seleccionarProducto(opcion);
		}
		
		else if (opcionProductos == 2) {
			restaurante.cerrarYGuardarPedido();
		}
	}
	
	
	public void seleccionarProducto(int opcionSeleccionada) throws IOException {
		
		HashMap<String, ProductoMenu> menuBase = restaurante.getMenuBase();
		
		
		int sizeMenuBase = menuBase.size();
		
		if (opcionSeleccionada > sizeMenuBase) {
			agregarCombo(opcionSeleccionada);
			mostrarMenuCuatro();
			
			int opcionProductos = Integer.parseInt(input("\nSeleccione una opcion"));
			
			opcionesMenuCuatro(opcionProductos);
			
		}
		
		else if (opcionSeleccionada > 0 && opcionSeleccionada < sizeMenuBase) {
			String nombre = printMenuBase.get(opcionSeleccionada);
			ProductoMenu producto =  menuBase.get(nombre);
			
			mostrarMenuTres();
			
			int opcionItem = Integer.parseInt(input("\nSeleccione una opcion"));
			
			seleccionarAgregarOQuitar(opcionItem, producto);
			
			
		}	
	}
	
	public void opcionesMenuCinco(int opcion, Producto producto) throws IOException {
		
		if (opcion == 1) {
			mostrarMenuTres();
			int opcionItem = Integer.parseInt(input("\nSeleccione una opcion"));
			seleccionarAgregarOQuitar(opcionItem, producto);
		}
		else if (opcion == 2) {
			mostrarMenuProductosCombos();
			int opc = Integer.parseInt(input("\nIngrese la opción que quiere agregar a su pedido"));
			seleccionarProducto(opc);
		}
		else if (opcion == 3) {
			Pedido pedido = restaurante.getPedidoEnCurso();
			pedido.agregarProducto(producto);
			mostrarMenuCuatro();
			int opc3 = Integer.parseInt(input("\nSeleccione una opcion"));
			opcionesMenuCuatro(opc3);
		}
		else if (opcion == 4) {
			Pedido pedido = restaurante.getPedidoEnCurso();
			pedido.agregarProducto(producto);
			mostrarMenuCuatro();
			int opc3 = Integer.parseInt(input("\nSeleccione una opcion"));
			opcionesMenuCuatro(opc3);
		}
		
	}
	
	public void seleccionarAgregarOQuitar(int opcion,Producto producto) throws IOException {
		
		//agregar
		if (opcion ==1) {
			mostrarMenuIngredientes();
			int opcion1Ingredientes = Integer.parseInt(input("\nSeleccione una opcion"));
			Producto adjs = agregarIngrediente(opcion1Ingredientes, producto);
			mostrarMenuCinco();
			int opc1 = Integer.parseInt(input("\nSeleccione una opcion"));
			opcionesMenuCinco(opc1, adjs);
		}
		//quitar
		else if (opcion == 2) {
			
			mostrarMenuIngredientes();
			int opcion2Ingredientes = Integer.parseInt(input("\nSeleccione una opcion"));
			Producto adj =  eliminarIngrediente(opcion2Ingredientes, producto);
			mostrarMenuCinco();
			int opc2 = Integer.parseInt(input("\nSeleccione una opcion"));
			opcionesMenuCinco(opc2, adj);
		}
		//guardar
		else {
			Pedido pedido = restaurante.getPedidoEnCurso();
			pedido.agregarProducto(producto);
			mostrarMenuCuatro();
			int opc3 = Integer.parseInt(input("\nSeleccione una opcion"));
			opcionesMenuCuatro(opc3);
		}
	}
	
	
	public void mostrarMenu(){
		System.out.println("\n");
		System.out.println("1. Iniciar nuevo pedido. ");
		System.out.println("2. Consultar la información de un pedido dado su id. ");
		System.out.println("3. Cerrar aplicación. ");
	}
	
	public void mostrarMenuTres() {
		System.out.println("\n");
		System.out.println("1. Adicionar un Ingrediente al producto seleccionado");
		System.out.println("2. Eliminar un Ingrediente del producto seleccionado");
		System.out.println("3. Guardar producto");
		System.out.println("\n");
	}
	
	public void mostrarMenuCinco() {
		System.out.println("\n");
		System.out.println("1. Adicionar o eliminar un Ingrediente al producto seleccionado");
		System.out.println("2. Agregar algo más al pedido");
		System.out.println("3. Cerrar y guardar pedido");
		System.out.println("4. Guardar Producto");
		System.out.println("\n");
	}
	
	public void mostrarMenuCuatro() {
		System.out.println("\n");
		System.out.println("1. ¿Desea agregar algo más al pedido?");
		System.out.println("2. Cerrar y guardar pedido");
		;
	}
	
	public void mostrarMenuIngredientes() {
		HashMap<String, Ingrediente> menuIngredientes = restaurante.getIngredientes();
		HashMap< Integer, String > printMenuIngredientes = new HashMap<>();
		Set<String> nombre = menuIngredientes.keySet();
		int contador = 0;
		
		for (String nombrep : nombre) {
			contador ++;
			printMenuIngredientes.put(contador, nombrep);
		}
		
		System.out.println("\nINGREDIENTES");
		System.out.println("-----------------------------------");
		
		for (Integer clave : printMenuIngredientes.keySet()) {
		    String valor = printMenuIngredientes.get(clave);
		    System.out.println(clave + ". " + valor);
		}
		
		this.printMenuIngredientes = printMenuIngredientes;
	}
	
	public void mostrarMenuProductosCombos() {
		
		HashMap<String, ProductoMenu> menuBase = restaurante.getMenuBase();
		HashMap< Integer, String > printMenuBase = new HashMap<>();
		Set<String> nombreProducto = menuBase.keySet();
		int contador = 0;
		
		for (String nombre : nombreProducto) {
			contador ++;
			printMenuBase.put(contador, nombre);
		}
		
		HashMap<String, Combo> combos = restaurante.getCombos();
		HashMap< Integer, String > printCombos = new HashMap<>();
		Set<String> nombreCombo = combos.keySet();
		int contadorCombos = 22;
		
		for (String nombre : nombreCombo) {
			contadorCombos ++;
			printCombos.put(contadorCombos, nombre);
		}
		
		System.out.println("\nPRODUCTOS");
		System.out.println("-----------------------------------");
		
		for (Integer clave : printMenuBase.keySet()) {
		    String valor = printMenuBase.get(clave);
		    System.out.println(clave + ". " + valor);
		}
		
		System.out.println("\nCOMBOS");
		System.out.println("-----------------------------------");
		
		for (Integer clave : printCombos.keySet()) {
		    String valor = printCombos.get(clave);
		    System.out.println(clave + ". " + valor);
		}
		
		this.printMenuBase = printMenuBase;
		this.printCombos = printCombos;
		
	}
	
	
	public void cargarInformacion() throws IOException {
		
		Restaurante restaurante = new Restaurante();
		
		File archivoIngredientes = new File("C:\\Users\\Asus\\eclipse-workspace\\Taller2_Hamburguesas\\data\\ingredientes.txt");
		File archivoMenu = new File("C:\\Users\\Asus\\eclipse-workspace\\Taller2_Hamburguesas\\data\\menu.txt");
		File archivoCombos = new File("C:\\Users\\Asus\\eclipse-workspace\\Taller2_Hamburguesas\\data\\combos.txt");
		
		restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
		this.restaurante = restaurante;
	}
	

	public String input(String mensaje){
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}






















