//Marcelo Nicolás Osandon Nuñez, 22036682-0, Ingienería Civil en Computación e Informática
//Lucas Vicente González cortes, 22336895-6, Ingienería Civil en Computación e Informática

package Taller2;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		String NombreIngresado = null;
		ArrayList<String> Atrapados = new ArrayList<>();
		ArrayList<String> Estado = new ArrayList<>();
		boolean salir = false;
		boolean mostrado = false;
		Pokemon p = new Pokemon(null, false, "0", "Vivo");
		Scanner sc = new Scanner(System.in);
		while (salir == false) {
			System.out.println("1) Continuar");
			System.out.println("2) Nueva Partida");
			System.out.println("3) Salir");
			int opcion = sc.nextInt();
			sc.nextLine();
			while (opcion == 1) {
				try {
					FileReader archivoConteo = new FileReader("Registros.txt");
					BufferedReader leyendo = new BufferedReader(archivoConteo);
					String linea = leyendo.readLine();
					while (linea != null) {
						if (linea.length() > 1) {
							String[] datos = linea.split(";");
							String Nombre = datos[0];
							String Medalla = datos[1];
							int Num_medalla = Integer.valueOf(Medalla);
							if (Nombre != null && Nombre != " ") {
								p.setNombre(Nombre);
								NombreIngresado = Nombre;
								p.setEmpezo(true);
							}
							if (Num_medalla > 0) {
								p.setMedallas(Medalla);
							}
						break;
						}
						linea = leyendo.readLine();
					}
					if (NombreIngresado == null) {
						System.out.println();
						System.out.println("No puedes continuar, no existe guardados, crea una nueva partida");
						System.out.println();
						break;
					}
					leyendo.close();
					if (NombreIngresado != null) {
						opcion = 2;	
					}
				} catch (Exception e) {
					System.out.println("Error de lectura" + e);
				}
			}
			while (opcion == 2) {
				if (p.isEmpezo(false) == false) {
					System.out.print("Ingrese Apodo: ");
					String Nombre = sc.nextLine();
					NombreIngresado = Nombre;
					p.setEmpezo(true);
				}
				if (NombreIngresado != null && NombreIngresado != " ") {
					p.setEmpezo(true);
					if (mostrado == false) {
						mostrado = true;
						System.out.println();
						System.out.println("Bienvenido " + NombreIngresado + "!!");
					}
					System.out.println();
					System.out.println(NombreIngresado + ", que deseas hacer");
					System.out.println();
					System.out.println("1) Revisar equipo.");
					System.out.println("2) Salir a capturar.");
					System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
					System.out.println("4) Retar un gimnasio.");
					System.out.println("5) Desafío al Alto Mando.");
					System.out.println("6) Curar Pokémon.");
					System.out.println("7) Guardar.");
					System.out.println("8) Guardar y Salir.");
					System.out.print("Ingrese Opcion: ");
					int opcion2 = sc.nextInt();
					sc.nextLine();
					switch (opcion2) {
						case 1: 
							//revisar equipo
							//lucas
							break;
						case 2:
							//salir a capturar pokemon
							System.out.println();
							System.out.println("Donde deseas ir a explorar?");
							System.out.println();
							p.ver_zonas("Habitats.txt");
							int opcion3 = sc.nextInt();
							String atrapado = p.ver_pokemon(opcion3);
							sc.nextLine();
							if (atrapado != null) {
								Atrapados.add(atrapado);
								Estado.add("Vivo");
							}
							break;
						case 3:
							p.VerPokemonAtrapados(Atrapados);
							break;
						case 4: 
							//retar a un gimnasio
							//Marcelo
							break;
						case 5: 
							//desafio de alto mando
							//Marcelo
							break;
						case 6:
							//curar
							//Lucas
							break;
						case 7:
							System.out.println();
							System.out.println("Guardado");
							System.out.println();
							Guardar("Registros.txt", NombreIngresado, p.getMedallas(), Atrapados, Estado);
							break;
						case 8:
							System.out.println();
							System.out.println("Guardado y salir");
							System.out.println();
							Guardar("Registros.txt", NombreIngresado, p.getMedallas(), Atrapados, Estado);
							break;
						default:
							System.out.println();
							System.out.println("Opcion invalida, escoje de nuevo");
					}
					if (opcion == 8) {
						break;
					}
				}
			}	
		}
	}
	public static String[] seleccionar(ArrayList<String> PokemonAtrapados, ArrayList<String> Estado) {
		String[] seleccionados = new String[5];
		int LongitudAtrapados = PokemonAtrapados.size();
		for (int i = 0; i < LongitudAtrapados; i++) {
			if (PokemonAtrapados.get(i) != null) {
				
			}
		}
		return seleccionados;
	}
	public static void Guardar(String archivo, String Nombre, String Medallas, ArrayList<String> PokemonAtrapados, ArrayList<String> Estado) {
		ArrayList<String> respaldo = new ArrayList<>();
		try {
			FileReader archivoConteo = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
				respaldo.add(linea);
				linea = leyendo.readLine();
			}
			leyendo.close();
	        for (int i = 0; i < PokemonAtrapados.size(); i++) {
	            respaldo.add(PokemonAtrapados.get(i) + ";" + Estado.get(i));
	        }
			FileWriter archivoUsuarios = new FileWriter(archivo);
			BufferedWriter escritorBuffer = new BufferedWriter(archivoUsuarios);
			for (int i = 0; i < respaldo.size(); i++) {
			    escritorBuffer.write(respaldo.get(i));
	            if (i < respaldo.size() - 1) {
	                escritorBuffer.newLine();
	            }
	        }
			escritorBuffer.close();	
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
}
