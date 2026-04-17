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
		String nombre = null;
		ArrayList<String> AtrapadosObtenidos = new ArrayList<>();
		ArrayList<String> EstadoObtenidos = new ArrayList<>();
		ArrayList<String> Atrapados = new ArrayList<>();
		ArrayList<String> Estado = new ArrayList<>();
		ArrayList<String> Equipo = new ArrayList<>();
		ArrayList<String> EstadoEquipo = new ArrayList<>();
		ArrayList<String> Equipador = new ArrayList<>();
		ArrayList<String> ResultadoBatalla = null;
		boolean salir = false;
		boolean mostrado = false;
		boolean NuevoUsuario = true;
		boolean GuardarCaptura = false;
		int AltoMando = 0;
		int cont_lineas = 0;
		Pokemon p = new Pokemon(null, false, "none", "Vivo", null);
		Scanner sc = new Scanner(System.in);
		while (salir == false) {
			System.out.println("1) Continuar");
			System.out.println("2) Nueva Partida");
			System.out.println("3) Salir");
			int opcion = sc.nextInt();
			sc.nextLine();
			while (opcion == 1) {
				nombre = VerRegistrosNombre("Registros.txt");
				if (nombre != null) {
					NuevoUsuario = false;
					opcion = 2;
				}
				if (nombre == null) {
					break;
				}
			}
			while (opcion == 2) {
				if (NuevoUsuario == false) {
					nombre = VerRegistrosNombre("Registros.txt");
					Equipador = ActualizarPokemonEquipo("Registros.txt");
					EstadoEquipo = ActualizarEstadoEquipo("Registros.txt");	
				}
				if (NuevoUsuario == true || nombre == null) {
					System.out.print("Ingrese Apodo: ");
					String Nombre = sc.nextLine();
					Sobreescribir("Registros.txt", Nombre, p.getMedallas(), Atrapados, Estado);
					p.setGuardar(true);
					GuardarCaptura = true;
					NuevoUsuario = false;
				}
				if (nombre != null && nombre != "") {
					if (mostrado == false) {
						mostrado = true;
						System.out.println();
						System.out.println("Bienvenido " + nombre + "!!");
					}
					System.out.println();
					System.out.println(nombre + ", que deseas hacer");
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
								p.setGuardar(true);
								GuardarCaptura = true;
								AtrapadosObtenidos.add(atrapado);
								EstadoObtenidos.add("Vivo");
							}
							break;
						case 3:
							p.VerPokemonAtrapados(Atrapados);
							for (int e = 0; e < 5; e++) {
								p.setPokemonEquipo(Equipo);
							}
							break;
						case 4: 
							p.setPokemonEquipo(Equipador);
							for (int e = 0; e < Equipador.size(); e++) {
								if (EstadoEquipo.get(e).equals("Muerto")) {
									Equipador.remove(e);
								}
							}
							if (Equipador.size() <= 0) {
								System.out.println();
								System.out.println("No tienes Pokemon Vivos para Combatir");
								break;
							}
							for (int e = 0; e <= 5; e++) {
								p.setPokemonEquipo(Equipo);	
							}
							System.out.println();
							System.out.println("A cual Lider deseas retar??");
							System.out.println();
							int opcionBatalla = p.verRivales("Gimnasios.txt");
							if (opcionBatalla >= 1) {
								ResultadoBatalla = p.RetarGimnasio("Gimnasios.txt", Equipador, EstadoEquipo, nombre);
							}
							if (ResultadoBatalla != null) {
							    String actuales = p.getMedallas();
							    guardarMedallas("Registros.txt", ResultadoBatalla);
							}
							else if (opcionBatalla == 0 || opcionBatalla < 0) {
								break;
							}
							break;
						case 5: 
							System.out.println();
							System.out.println("¿Estas Completamente seguro de Luchar Contra el Alto Mando?");
							System.out.println();
							break;
						case 6:
							//curar
							//Lucas
							break;
						case 7:
							System.out.println();
							System.out.println("Guardado");
							System.out.println();
							if (GuardarCaptura == true) {
								GuardarCapturados("Registros.txt", nombre, p.getMedallas(), Atrapados,AtrapadosObtenidos, Estado,EstadoObtenidos, false);	
								for (int s = 0; s < AtrapadosObtenidos.size(); s++) {
									AtrapadosObtenidos.remove(s);	
								}
								GuardarCaptura = false;
							}
							else {
								Guardar("Registros.txt", nombre, p.getMedallas(), Atrapados, Estado, false);				
							}
							break;
						case 8:
							System.out.println();
							System.out.println("Nos vemos entrenador...");
							System.out.println();
							if (GuardarCaptura == true) {
								GuardarCapturados("Registros.txt", nombre, p.getMedallas(), Atrapados,AtrapadosObtenidos, Estado,EstadoObtenidos, false);	
								for (int s = 0; s < AtrapadosObtenidos.size(); s++) {
									AtrapadosObtenidos.remove(s);	
								}
								GuardarCaptura = false;
							}
							else {
								Guardar("Registros.txt", nombre, p.getMedallas(), Atrapados, Estado, false);				
							}
							break;
						default:
							System.out.println();
							System.out.println("Opcion invalida, escoje de nuevo");
					}
					if (opcion2 == 8) {
						nombre = null;
						NuevoUsuario = true;
						break;
					}
				}
			}	
			if (opcion > 2 || opcion <= 0) {
				break;
			}
		}
	}
	
	public static ArrayList<String> ActualizarEstadoEquipo(String Archivo) {
		String NombreIngresado = null;
		int cont_lineas = 0;
		Pokemon p = new Pokemon(null, false, "none", "Vivo", null);
		ArrayList<String> Atrapados = new ArrayList<>();
		ArrayList<String> Estado = new ArrayList<>();
		ArrayList<String> Equipo = new ArrayList<>();
		ArrayList<String> EstadoEquipo = new ArrayList<>();
		try {
			FileReader archivoConteo = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
				if (cont_lineas == 0) {
					String[] datos = linea.split(";");
					String Nombre = datos[0];
					String Medalla = datos[1];
					if (Nombre != null && Nombre != " ") {
						p.setNombre(Nombre);
						NombreIngresado = Nombre;
						p.setGuardar(false);
					}
				}
				if (cont_lineas > 1) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Atrapados.add(Pokemon);
					Estado.add(Estados);
				}
				if (cont_lineas < 7 && cont_lineas > 0) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Equipo.add(Pokemon);
					EstadoEquipo.add(Estados);
					p.setPokemonEquipo(Atrapados);
				}
				else if (cont_lineas > 0) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Atrapados.add(Pokemon);
					Estado.add(Estados);
				}
				linea = leyendo.readLine();
				cont_lineas++;
			}
			leyendo.close();
			if (NombreIngresado != null) {
				return EstadoEquipo;	
			}
		} catch (Exception e) {
			System.out.println("Error de lectura" + e);
		}
		return null;
	}
	
	public static ArrayList<String> ActualizarPokemonEquipo(String Archivo) {
		String NombreIngresado = null;
		int cont_lineas = 0;
		Pokemon p = new Pokemon(null, false, "0", "Vivo", null);
		ArrayList<String> Atrapados = new ArrayList<>();
		ArrayList<String> Estado = new ArrayList<>();
		ArrayList<String> Equipo = new ArrayList<>();
		ArrayList<String> EstadoEquipo = new ArrayList<>();
		try {
			FileReader archivoConteo = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
				if (cont_lineas == 0) {
					String[] datos = linea.split(";");
					String Nombre = datos[0];
					String Medalla = datos[1];
					if (Nombre != null && Nombre != " ") {
						p.setNombre(Nombre);
						NombreIngresado = Nombre;
						p.setGuardar(false);
					}
				}
				if (cont_lineas > 1) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Atrapados.add(Pokemon);
					Estado.add(Estados);
				}
				if (cont_lineas < 7 && cont_lineas > 0) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Equipo.add(Pokemon);
					EstadoEquipo.add(Estados);
					p.setPokemonEquipo(Atrapados);
				}
				else if (cont_lineas > 0) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Atrapados.add(Pokemon);
					Estado.add(Estados);
				}
				linea = leyendo.readLine();
				cont_lineas++;
			}
			leyendo.close();
			if (NombreIngresado != null) {
				return Equipo;	
			}
		} catch (Exception e) {
			System.out.println("Error de lectura" + e);
		}
		return null;
	}
	
	public static String VerRegistrosNombre(String Archivo) {
		String NombreIngresado = null;
		boolean NuevoUsuario = true;
		int cont_lineas = 0;
		Pokemon p = new Pokemon(null, false, "0", "Vivo", null);
		ArrayList<String> Atrapados = new ArrayList<>();
		ArrayList<String> Estado = new ArrayList<>();
		ArrayList<String> Equipo = new ArrayList<>();
		ArrayList<String> EstadoEquipo = new ArrayList<>();
		try {
			FileReader archivoConteo = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
				if (cont_lineas == 0) {
					String[] datos = linea.split(";");
					String Nombre = datos[0];
					String Medalla = datos[1];
					if (Nombre != null && Nombre != " ") {
						NuevoUsuario = false;
						p.setNombre(Nombre);
						NombreIngresado = Nombre;
						p.setGuardar(false);
					}
				}
				if (cont_lineas > 1) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Atrapados.add(Pokemon);
					Estado.add(Estados);
				}
				if (cont_lineas < 7 && cont_lineas > 0) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Equipo.add(Pokemon);
					EstadoEquipo.add(Estados);
					p.setPokemonEquipo(Atrapados);
				}
				else if (cont_lineas > 0) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Atrapados.add(Pokemon);
					Estado.add(Estados);
				}
				linea = leyendo.readLine();
				cont_lineas++;
			}
			if (NombreIngresado == null) {
				System.out.println();
				System.out.println("No puedes continuar, no existe guardados, crea una nueva partida");
				System.out.println();
				return null;
			}
			leyendo.close();
			if (NombreIngresado != null) {
				return NombreIngresado;	
			}
		} catch (Exception e) {
			System.out.println("Error de lectura" + e);
		}
		return null;
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
	public static void Sobreescribir(String archivo, String Nombre, String Medallas, ArrayList<String> PokemonAtrapados, ArrayList<String> Estado) {
		try {
			FileWriter archivoUsuarios = new FileWriter(archivo);
			BufferedWriter escritorBuffer = new BufferedWriter(archivoUsuarios);
			escritorBuffer.write(Nombre + ";" + Medallas);
			escritorBuffer.close();	
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
	public static void GuardarCapturados(String archivo, String Nombre, String Medallas, ArrayList<String> PokemonAtrapados,ArrayList<String> PokemonNuevos, ArrayList<String> Estado, ArrayList<String> EstadoObtenido, boolean yaEscrito) {
		ArrayList<String> respaldo = new ArrayList<>();
		boolean sobreescribir = true;
		if (yaEscrito == false) {
			try {
				FileReader archivoConteo = new FileReader("Registros.txt");
				BufferedReader leyendo = new BufferedReader(archivoConteo);
				String linea = leyendo.readLine();
				while (linea != null) {
					sobreescribir = true;
					respaldo.add(linea);
					linea = leyendo.readLine();
				}
				leyendo.close();
				FileWriter archivoUsuarios = new FileWriter(archivo);
				BufferedWriter escritorBuffer = new BufferedWriter(archivoUsuarios);
				for (int i = 0; i < respaldo.size(); i++) {
					if (respaldo.get(i) != null && sobreescribir == true) {
						escritorBuffer.write(respaldo.get(i));
						if (i < respaldo.size() - 1) {
							escritorBuffer.newLine();
						}	
					}
				}
				escritorBuffer.newLine();
				for (int j = 0; j < PokemonNuevos.size(); j ++) {
					if (PokemonNuevos.get(j) != null && sobreescribir == true) {
						escritorBuffer.write(PokemonNuevos.get(j) + ";" + EstadoObtenido.get(j));
						if (j < PokemonNuevos.size() - 1) {
							escritorBuffer.newLine();
						}	
					}
				}
				escritorBuffer.close();
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
		}
	}
	public static void Guardar(String archivo, String Nombre, String Medallas, ArrayList<String> PokemonAtrapados, ArrayList<String> Estado, boolean yaEscrito) {
		ArrayList<String> respaldo = new ArrayList<>();
		boolean sobreescribir = true;
		if (yaEscrito == false) {
			try {
				FileReader archivoConteo = new FileReader("Registros.txt");
				BufferedReader leyendo = new BufferedReader(archivoConteo);
				String linea = leyendo.readLine();
				while (linea != null) {
					sobreescribir = true;
					respaldo.add(linea);
					linea = leyendo.readLine();
				}
				leyendo.close();
				FileWriter archivoUsuarios = new FileWriter(archivo);
				BufferedWriter escritorBuffer = new BufferedWriter(archivoUsuarios);
				for (int i = 0; i < respaldo.size(); i++) {
					if (respaldo.get(i) != null && sobreescribir == true) {
						escritorBuffer.write(respaldo.get(i));
						if (i < respaldo.size() - 1) {
							escritorBuffer.newLine();
						}	
					}
				}
				escritorBuffer.close();
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
		}
	}
	public static void guardarMedallas(String archivo, ArrayList<String> nuevasMedallas) {
	    ArrayList<String> lineas = new ArrayList<>();
	    boolean existe = false;
	    int sumador = 0;
	    try {
			FileReader archivoConteo = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
	            lineas.add(linea);
	            linea = leyendo.readLine();
	        }
	        leyendo.close();
	        if (lineas.size() > 0) {
	            String[] datos = lineas.get(0).split(";");
	            String nombre = datos[0];
	            ArrayList<String> medallas = new ArrayList<>();
	            if (datos.length > 1 && !datos[1].equals("") && !datos[1].equals("none")) {
	                String[] existentes = datos[1].split(";");
	                for (String m : existentes) {
	                    medallas.add(m);
	                }
	            }
	            for (String m : medallas) {
	                if (m.equals(nuevasMedallas.get(sumador))) {
	                	sumador++;
	                    existe = true;
	                    break;
	                }
	            }

	            if (!existe) {
	                medallas.add(nuevasMedallas.get(sumador));
	                sumador++;
	            }
	            String resultado = "";
	            for (int i = 0; i < medallas.size(); i++) {
	                resultado += medallas.get(i);
	                if (i < medallas.size() - 1) {
	                    resultado += ";";
	                }
	            }
	            lineas.set(0, nombre + ";" + resultado);
	        }
	        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
	        for (int i = 0; i < lineas.size(); i++) {
	            escritor.write(lineas.get(i));
	            if (i < lineas.size() - 1) {
	                escritor.newLine();
	            }
	        }
	        escritor.close();
	    } catch (Exception e) {
	        System.out.println("Error guardar medallas " + e);
	    }
	}
	public static int VerificarMedallas(String Archivo) {
		int Verificar = 0;
	    try {
			FileReader archivoConteo = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
	            Verificar++;
	            linea = leyendo.readLine();
	        }
	    } catch (Exception e) {
	        System.out.println("Error guardar medallas " + e);
	    }
		return Verificar;
	}
}
