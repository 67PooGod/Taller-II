//Marcelo Nicolás Osandon Nuñez, 22036682-0, Ingienería Civil en Computación e Informática
//Lucas Vicente González cortes, 22336895-6, Ingienería Civil en Computación e Informática

package Taller2;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		String nombre = null;
		ArrayList<String> atrapadosObtenidos = new ArrayList<>();
		ArrayList<String> estadoObtenidos = new ArrayList<>();
		ArrayList<String> Atrapados = new ArrayList<>();
		ArrayList<String> Estado = new ArrayList<>();
		ArrayList<String> Equipo = new ArrayList<>();
		ArrayList<String> EstadoEquipo = new ArrayList<>();
		ArrayList<String> Equipador = new ArrayList<>();
		ArrayList<String> ResultadoBatalla = null;
		boolean salir = false;
		boolean mostrado = false;
		boolean nuevoUsuario = true;
		boolean guardarCaptura = false;
		boolean Empezar = true;
		Pokemon p = new Pokemon(null, false, "none", null, null);
		Scanner sc = new Scanner(System.in);
		while (salir == false) {
			System.out.println("1) Continuar");
			System.out.println("2) Nueva Partida");
			System.out.println("3) Salir");
			System.out.print("> ");
			int opcion = sc.nextInt();
			sc.nextLine();
			while (opcion == 1) {
				nombre = VerRegistrosNombre("Registros.txt");
				if (nombre != null) {
					nuevoUsuario = false;
					opcion = 2;
				}
				if (nombre == null) {
					break;
				}
			}
			while (opcion == 2) {
				if (nuevoUsuario == false) {
					nombre = VerRegistrosNombre("Registros.txt");
					Equipador = ActualizarPokemonEquipo("Registros.txt");
					EstadoEquipo = ActualizarEstadoEquipo("Registros.txt");
					cargarTodo("Registros.txt", Atrapados, Estado);
				}
				if (nuevoUsuario == true || nombre == null) {
					System.out.print("Ingrese Apodo: ");
					String Nombre = sc.nextLine();
					Sobreescribir("Registros.txt", Nombre, p.getMedallas(), Atrapados, Estado);
					p.setGuardar(true);
					guardarCaptura = true;
					nuevoUsuario = false;
				}
				if (nombre != null && nombre != "") {
					if (mostrado == false) {
						mostrado = true;
						System.out.println();
						System.out.println("Bienvenido " + nombre + "!!");
					}
					ResultadoBatalla = null;
					System.out.println();
					System.out.println(nombre + ", que deseas hacer?");
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
						// revisar equipo
						// lucas
						revisarEquipo(Equipador, EstadoEquipo);

						break;
					case 2:
						// salir a capturar pokemon
						System.out.println();
						System.out.println("Donde deseas ir a explorar?");
						System.out.println();
						p.ver_zonas("Habitats.txt");
						int opcion3 = sc.nextInt();
						String atrapado = p.ver_pokemon(opcion3);
						sc.nextLine();
						if (atrapado != null) {
							p.setGuardar(true);
							guardarCaptura = true;
							atrapadosObtenidos.add(atrapado);
							estadoObtenidos.add("Vivo");
						}
						break;
					case 3:
						p.VerPokemonAtrapados(Atrapados);
						for (int e = 0; e < 5; e++) {
							p.setPokemonEquipo(Equipo);
							p.setEstadoEquipo(EstadoEquipo);
						}
						break;
					case 4:
						p.setPokemonEquipo(Equipador);
						for (int e = Equipador.size() - 1; e >= 0; e--) {
							if (EstadoEquipo.get(e).equalsIgnoreCase("Muerto")) {
								Equipador.remove(e);
								EstadoEquipo.remove(e);
							}
						}
						if (Equipador.size() <= 0) {
							System.out.println();
							System.out.println("No tienes Pokemon Vivos para Combatir");
							break;
						}
						p.setPokemonEquipo(Equipo);
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
						} else if (opcionBatalla == 0 || opcionBatalla < 0) {
							break;
						}
						break;
					case 5:
						boolean puedeEntrar = VerificarMedallas("Registros.txt");
						p.setPokemonEquipo(Equipador);
						for (int e = Equipador.size() - 1; e >= 0; e--) {
							if (EstadoEquipo.get(e).equalsIgnoreCase("Muerto")) {
								Equipador.remove(e);
								EstadoEquipo.remove(e);
							}
						}
						if (Equipador.size() <= 0) {
							System.out.println();
							System.out.println("No tienes Pokemon Vivos para Combatir");
							break;
						}
						p.setPokemonEquipo(Equipo);
						System.out.println();
						if (puedeEntrar == true) {
							boolean ResultadoAltoMando = p.batallarAltoMando("Alto Mando.txt", Equipador, EstadoEquipo,
									nombre);
							if (ResultadoAltoMando == true) {
								System.out.println();
								System.out.println("Completaste el juego " + nombre);
							}
						} else {
							System.out.println();
							System.out.println("No puedes ingresar al Alto Mando, no tienes medallas suficientes");
						}
						break;
					case 6:
						// curar
						// Lucas
						System.out.println();
						System.out.println("Tu equipo se ha recuperado!");

						Atrapados.clear();
						Estado.clear();
						cargarTodo("Registros.txt", Atrapados, Estado);

						for (int i = 0; i < Estado.size(); i++) {
							Estado.set(i, "Vivo");
						}

						for (int i = 0; i < EstadoEquipo.size(); i++) {
							EstadoEquipo.set(i, "Vivo");
						}

						try {
							FileWriter archivoUsuarios = new FileWriter("Registros.txt");
							BufferedWriter escritorBuffer = new BufferedWriter(archivoUsuarios);

							escritorBuffer.write(nombre + ";" + p.getMedallas());
							escritorBuffer.newLine();

							for (int i = 0; i < Atrapados.size(); i++) {
								escritorBuffer.write(Atrapados.get(i) + ";" + Estado.get(i));
								if (i < Atrapados.size() - 1) {
									escritorBuffer.newLine();
								}
							}
							escritorBuffer.close();
						} catch (Exception e) {
							System.out.println("error " + e);
						}
						break;
					case 7:
						System.out.println();
						System.out.println("Guardado");
						System.out.println();
						if (guardarCaptura == true) {
							guardarCapturados("Registros.txt", nombre, p.getMedallas(), Atrapados, atrapadosObtenidos,
									Estado, estadoObtenidos, false);
							for (int s = 0; s < atrapadosObtenidos.size(); s++) {
								atrapadosObtenidos.remove(s);
							}
							guardarCaptura = false;
						} else {
							Guardar("Registros.txt", nombre, p.getMedallas(), Atrapados, Estado, false);
						}
						break;
					case 8:
						System.out.println();
						System.out.println("Nos vemos entrenador...");
						System.out.println();
						if (guardarCaptura == true) {
							guardarCapturados("Registros.txt", nombre, p.getMedallas(), Atrapados, atrapadosObtenidos,
									Estado, estadoObtenidos, false);
							for (int s = 0; s < atrapadosObtenidos.size(); s++) {
								atrapadosObtenidos.remove(s);
							}
							guardarCaptura = false;
						} else {
							Guardar("Registros.txt", nombre, p.getMedallas(), Atrapados, Estado, false);
						}
						break;
					default:
						System.out.println();
						System.out.println("Opcion invalida, escoje de nuevo");
					}
					if (opcion2 == 8) {
						nombre = null;
						nuevoUsuario = true;
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
		int cont_lineas = 0;
		Pokemon p = new Pokemon(null, false, "none", null, null);
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
				}
				if (cont_lineas >= 1) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Atrapados.add(Pokemon);
					Estado.add(Estados);
				}
				if (cont_lineas < 7 && cont_lineas >= 1) {
					String[] partes = linea.split(";");
					String Pokemon = partes[0];
					String Estados = partes[1];
					Equipo.add(Pokemon);
					EstadoEquipo.add(Estados);
					p.setPokemonEquipo(Atrapados);
					p.setEstadoEquipo(EstadoEquipo);
				}
				linea = leyendo.readLine();
				cont_lineas++;
			}
			leyendo.close();
			return EstadoEquipo;
		} catch (Exception e) {
			System.out.println("Error de lectura" + e);
		}
		return null;
	}

	public static ArrayList<String> ActualizarPokemonEquipo(String Archivo) {
		int cont_lineas = 0;
		Pokemon p = new Pokemon(null, false, "0", null, null);
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
				} else if (cont_lineas > 0) {
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
			return Equipo;
		} catch (Exception e) {
			System.out.println("Error de lectura" + e);
		}
		return null;
	}

	public static String VerRegistrosNombre(String Archivo) {
		String NombreIngresado = null;
		boolean nuevoUsuario = true;
		int cont_lineas = 0;
		Pokemon p = new Pokemon(null, false, "0", null, null);
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
						nuevoUsuario = false;
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
				} else if (cont_lineas > 0) {
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

	public static void Sobreescribir(String archivo, String Nombre, String Medallas, ArrayList<String> PokemonAtrapados,
			ArrayList<String> Estado) {
		try {
			FileWriter archivoUsuarios = new FileWriter(archivo);
			BufferedWriter escritorBuffer = new BufferedWriter(archivoUsuarios);
			escritorBuffer.write(Nombre + ";" + Medallas);
			escritorBuffer.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public static void guardarCapturados(String archivo, String Nombre, String Medallas,
			ArrayList<String> PokemonAtrapados, ArrayList<String> PokemonNuevos, ArrayList<String> Estado,
			ArrayList<String> EstadoObtenido, boolean yaEscrito) {
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
				for (int j = 0; j < PokemonNuevos.size(); j++) {
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

	public static void Guardar(String archivo, String Nombre, String Medallas, ArrayList<String> PokemonAtrapados,
			ArrayList<String> Estado, boolean yaEscrito) {
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
					for (int i = 1; i < datos.length; i++) {
						medallas.add(datos[i]);
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

	public static int cantLineasRival(String Archivo) {
		int Verificar = 0;
		try {
			FileReader archivoConteo = new FileReader("Gimnasios.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
				linea = leyendo.readLine();
				Verificar++;
			}
		} catch (Exception e) {
			System.out.println("Error guardar medallas " + e);
		}
		return Verificar;
	}

	public static boolean VerificarMedallas(String Archivo) {
		boolean resultado = false;
		boolean Verificado = false;
		int Verificar = 0;
		try {
			FileReader archivoConteo = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
				String datos[] = linea.split(";");
				if (datos[0] == null || datos[1].equals("none")) {
					Verificar = 0;
					return resultado;
				}
				int Maximo = datos.length;
				if (Verificado == false) {
					while (Verificar < Maximo) {
						Verificar++;
					}
					Verificado = true;
				}
				linea = leyendo.readLine();
			}
			if (Verificar - 1 == cantLineasRival("Registros.txt")) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error guardar medallas " + e);
		}
		return resultado;
	}

	// wip verificar y actualizar las medallas
	public static void ActualizarMedallas(String archivo1, String archivo2, String Texto) {
		ArrayList<String> N = new ArrayList<>();
		ArrayList<String> MedallaN = new ArrayList<>();
		ArrayList<String> Estado = new ArrayList<>();
		ArrayList<String> CantPokemon = new ArrayList<>();
		ArrayList<String> Pokemons = new ArrayList<>();
		ArrayList<String> Resultado = new ArrayList<>();
		boolean Arreglar = false;
		int numero = 0;
		int contLineas = 0;
		int medallas = 0;
		try {
			FileReader archivoConteo = new FileReader(archivo1);
			BufferedReader leyendo = new BufferedReader(archivoConteo);
			String linea = leyendo.readLine();
			while (linea != null) {
				String datos[] = linea.split(";");
				if (contLineas <= 0) {
					int Maximo = datos.length - 1;
					numero = Maximo;
				}
				contLineas++;
				linea = leyendo.readLine();
			}
			if (numero != cantLineasRival(archivo2)) {
				Arreglar = true;
			}
			if (Arreglar = true) {
				FileReader archivoConteo2 = new FileReader(archivo2);
				BufferedReader leyendo2 = new BufferedReader(archivoConteo2);
				String linea2 = leyendo2.readLine();
				while (linea2 != null) {
					String datos2[] = linea2.split(";");
					if (datos2[2].equalsIgnoreCase("Derrotado")) {
						datos2[2] = Texto;
					}
					String lineaNueva = "";
					for (int i = 0; i < datos2.length; i++) {
						lineaNueva += datos2[i];
						if (i < datos2.length - 1) {
							lineaNueva += ";";
						}
					}
					Resultado.add(lineaNueva);
					linea2 = leyendo2.readLine();
				}
			}
			leyendo.close();
			BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo2));
			for (int i = 0; i < Resultado.size(); i++) {
				escritor.write(Resultado.get(i));
				if (i < Resultado.size() - 1) {
					escritor.newLine();
				}
			}
			escritor.close();
		} catch (Exception e) {
			System.out.println("Error guardar medallas " + e);
		}
	}

	public static void revisarEquipo(ArrayList<String> equipo, ArrayList<String> estadoEquipo) {
		System.out.println();
		System.out.println("Estado actual:");
		System.out.println();

		if (equipo == null || equipo.isEmpty()) {

			System.out.println("No hay pokemones en tu equipo");
			return;
		}

		for (int i = 0; i < equipo.size(); i++) {
			String nombrePoke = equipo.get(i);
			String estado = estadoEquipo.get(i);

			PokemonCombate infoPoke = PokemonCombate.crearPokemon(nombrePoke);

			if (infoPoke != null) {
				System.out.println((i + 1) + ") " + infoPoke.getNombre() + "|" + infoPoke.getTipo()
						+ " | Stats totales: " + infoPoke.getPuntos());
			}
		}

	}

	public static void cargarTodo(String archivo, ArrayList<String> atrapados, ArrayList<String> estados) {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			linea = lector.readLine();
			while (linea != null) {
				String[] partes = linea.split(";");
				if (partes.length >= 2) {
					atrapados.add(partes[0]);
					estados.add(partes[1]);
				}
				linea = lector.readLine();
			}
			lector.close();
		} catch (Exception e) {
			System.out.println("error" + e);
		}
	}
}
