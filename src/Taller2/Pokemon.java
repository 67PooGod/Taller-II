package Taller2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Pokemon {
	private String Nombre;
	private boolean Guardar;
	private String Medallas;
	private String Estado;
	private ArrayList<String> PokemonEquipo;
	public Pokemon(String Nombre, boolean Guardar, String Medallas, String Estado, ArrayList<String> PokemonEquipo) {
		this.Nombre = Nombre;
		this.Guardar = Guardar;
		this.Medallas = Medallas;
		this.Estado = Estado;
		this.PokemonEquipo = PokemonEquipo;
	}

	public String getNombre(String Nombre) {
		return this.Nombre;
	}
	
	public void setNombre(String Nombre) {
	    this.Nombre = Nombre;
	}
	
	
	public boolean isGuardar() {
		return Guardar;
	}

	public void setGuardar(boolean guardar) {
		this.Guardar = guardar;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getEstado() {
		return Estado;
	}

	public String isEstado(String Estado) {
		return this.Estado;
	}
	
	public void setEstado(String Estado) {
	    this.Estado = Estado;
	}

	public ArrayList<String> getPokemonEquipo() {
		return PokemonEquipo;
	}

	public void setPokemonEquipo(ArrayList<String> pokemonEquipo) {
		PokemonEquipo = pokemonEquipo;
	}

	public String getMedallas() {
		try {
			FileReader arch = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String[] lineas = new String[300];
			int totalLineas = 0;
			String linea;
			while ((linea = leyendo.readLine()) != null) {
				String[] datos = linea.split(";");
				String Medalla = datos[1];
				this.Medallas = Medalla;
				break;
			}
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return this.Medallas;
	}
	
	public void setMedallas(String Medallas) {
	    this.Medallas = Medallas;
	}
	
	public int cant_zonas(String Archivo) {
		int cantidad = 0;
		try {
			FileReader arch = new FileReader(Archivo);
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				cantidad++;
				linea = leyendo.readLine();
			}
			leyendo.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return cantidad;
	}
	
	public ArrayList<String> zonas(String Archivo) {
		ArrayList<String> zonas = new ArrayList<>();
		try {
			FileReader arch = new FileReader(Archivo);
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while (linea != null) {
				zonas.add(linea);
				linea = leyendo.readLine();
			}
			leyendo.close();
		} catch (Exception e) {
			System.out.println("Error de zonas" + e);
		}
		return zonas;
	}
	
	public void ver_zonas(String Archivo) {
		int cantidad = cant_zonas("Habitats.txt");
		for (int z = 0; z < cantidad; z++) {
			if (z == 0) {
				System.out.println(z + ") " + "Volver");
			}
			System.out.println(z+1 + ") " + zonas("Habitats.txt").get(z));
		}
	}
	
	public String ver_pokemon(int zonaEscojida) {
		if (zonaEscojida < 0 && zonaEscojida < cant_zonas("Habitats.txt")) {
			System.out.println("");
			System.out.println("Opcion Invalida");
			return null;
		}
		else if (zonaEscojida > cant_zonas("Habitats.txt")) {
			System.out.println("");
			System.out.println("Opcion Invalida");
			return null;
		}
		else if (zonaEscojida >= 1 && zonaEscojida <= cant_zonas("Habitats.txt")) {
			Scanner sc = new Scanner(System.in);
			String pokemon = null;
			String lugar_escojido = zonas("Habitats.txt").get(zonaEscojida-1);
			Random azar = new Random();
			double acumulado = 0;
			boolean capturado = false;
			double prob = azar.nextDouble();
			try {
				FileReader arch = new FileReader("Pokedex.txt");
				BufferedReader leyendo = new BufferedReader(arch);
				String linea = leyendo.readLine();
				while ((linea) != null) {
					String[] datos = linea.split(";");
					pokemon = datos[0];
					String zona_pokemon = datos[1];
					String probabilidad_pokemon = datos[2];
					double probabilidad = Double.valueOf(probabilidad_pokemon);
					if (lugar_escojido.equals(zona_pokemon)) {
						acumulado += probabilidad;
						if (prob <= acumulado) {
							System.out.println();
							System.out.println("Oh!! Ha aparecido un increible " + pokemon);
							System.out.println();
							System.out.println("Que deseas hacer?");
							System.out.println();
							System.out.println("1) Capturar");
							System.out.println("2) Huir");
							int opcion = sc.nextInt();
							sc.nextLine();
							while (opcion == 1) {
								if (opcion == 1) {
									Random azar2 = new Random();
									double prob2 = azar2.nextDouble();
									if (prob2 < 0.50) {
										System.out.println();
										System.out.println(pokemon + " capturado con exito!!");
										System.out.println();
										System.out.println(pokemon + " ha sido agregado a tu equipo!");
										capturado = true;
										return pokemon;
									}
									else {
										System.out.println();
										System.out.println("oh no el pokemon " + pokemon + " a escapado de la pokeball");
										System.out.println();
										System.out.println("Que deseas hacer?");
										System.out.println();
										System.out.println("1) Capturar");
										System.out.println("2) Huir");
										opcion = sc.nextInt();
										sc.nextLine();
									}
								}
							}
							if (opcion == 2) {
								break;
							}
						}
					}
					linea = leyendo.readLine();
				}
			} catch (Exception e) {
				System.out.println("Error ver pokemon " + e);
			}
			if (capturado == true) {
				return pokemon;
			}
			else {
				return null;	
			}	
		}
		else {
			return null;
		}
	}
	
	public ArrayList<String> VerPokemonAtrapados(ArrayList<String> PokemonAtrapados) {
		ArrayList<String> Resultado = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int cont_lineas = 0;
		try {
			FileReader arch = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				cont_lineas ++;
				if (cont_lineas > 1) {
					Resultado.add(linea);
				}
				linea = leyendo.readLine();
			}
			leyendo.close();
			System.out.println();
			System.out.println("Lista de Pokemon que atrapaste escoje para mover hacia el primero, 0 para regresar");
			System.out.println();
			System.out.println("0) Regresar");
			for (int r = 0; r < Resultado.size(); r++) {
				System.out.println(r+1 + ") " + Resultado.get(r));
			}
			int opcion = sc.nextInt();
			sc.nextLine();
			if (opcion > 0) {
				EscojerPokemonEquipo(opcion);	
			}
			else {
				System.out.println("Opcion Invalida");
			}
		} catch (Exception e) {
			System.out.println("Error ver pokemon " + e);
		}
		return Resultado;
	}
	
	public static ArrayList<String> EscojerPokemonEquipo(int indicePokemon) {
		ArrayList<String> ResultadoFinal = new ArrayList<>();
		int cont_lineas = 0;
		try {
			FileReader arch = new FileReader("Registros.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				String[] datos = linea.split(";");
				String Pokemon = datos[0];
				String Estado = datos[1];
				cont_lineas ++;
				if (cont_lineas == 1) {
					ResultadoFinal.add(Pokemon + ";" + Estado);
				}
				if (cont_lineas > 1) {
	                ResultadoFinal.add(Pokemon + ";" + Estado);
				}
				linea = leyendo.readLine();
			}
			leyendo.close();
			cont_lineas = 0;
			FileWriter archivoUsuarios = new FileWriter("Registros.txt");
			BufferedWriter escritorBuffer = new BufferedWriter(archivoUsuarios);
	        if (indicePokemon > 1 && indicePokemon < ResultadoFinal.size()) {
	            String anteriorPrimero = ResultadoFinal.get(1);
	            ResultadoFinal.set(1, ResultadoFinal.get(indicePokemon));
	            ResultadoFinal.set(indicePokemon, anteriorPrimero);
	        }
			for (int i = 0; i < ResultadoFinal.size(); i++) {
			    escritorBuffer.write(ResultadoFinal.get(i));
	            if (i < ResultadoFinal.size() - 1) {
	                escritorBuffer.newLine();
	            }
	        }
			escritorBuffer.close();	
		} catch (Exception e) {
			System.out.println("Error ver pokemon " + e);
		}
		return ResultadoFinal;
	}
	
	public static int verRivales(String NombreArchivo) {
		ArrayList<String> ListaRivales = new ArrayList<>();
		ArrayList<String> ListaRivalesEstado = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int Desicion = 0;
		int ContVictoria = 0;
		int VariableMaxima = 0;
		try {
			FileReader arch = new FileReader(NombreArchivo);
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				String[] datos = linea.split(";");
				String Nombre = datos[1];
				String Estado = datos[2];
				if (Estado.equals("Derrotado")) {
					ContVictoria++;
				}
				ListaRivales.add(Nombre);
				ListaRivalesEstado.add(Estado);
				linea = leyendo.readLine();
			}
			leyendo.close();
			for (int i = 0; i <= ListaRivales.size(); i++) {
				if (i < ListaRivales.size()) {
					System.out.println(i+1 + ") " + ListaRivales.get(i) + " - Estado: " + ListaRivalesEstado.get(i));
				}
				else {
					VariableMaxima = i;
					System.out.println(i+1 + ") " + "Volver al menu.");
				}
			}
			System.out.println();
			System.out.println("Ingrese una Opcion");
			System.out.println();
			Desicion = sc.nextInt();
			sc.nextLine();
			if (Desicion < VariableMaxima && Desicion <= ContVictoria + 1) {
				System.out.println();
				System.out.println("Desafiando a " + ListaRivales.get(Desicion - 1) + "!!");
				return Desicion;
			}
			else if (Desicion > ContVictoria + 1 && Desicion < VariableMaxima) {
				System.out.println();
				System.out.println("Calmado Entrenador!!! No puedes retar a " + ListaRivales.get(Desicion - 1) + " sin haber derrotado a los lideres anteriores!!");
				return 0;
			}
			else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Error ver pokemon " + e);
		}
		return 0;
	}
	
	//wip batallar gimnasio

	public static void RetarGimnasio(String archivo, ArrayList<String> PokemonEquipo, ArrayList<String> EstadoActual, String NombreUsuario) {
		boolean batalla = true;
		boolean MostrarEnemigo = true;
		boolean MostrarJugador = true;
		int actualJugador = 0;
		int actualEnemigo = 0;
		String EntrenadorRival = "";
		double[][] TipoPelear = new double[17][17];
		PokemonCombate C = new PokemonCombate(null, null, 0);
		ArrayList<String> PokemonEnemigo = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		try {
			while (batalla == true) {
				FileReader arch = new FileReader("Gimnasios.txt");
				BufferedReader leyendo = new BufferedReader(arch);
				String linea = leyendo.readLine();
				while ((linea) != null) {
					String[] datos = linea.split(";");
					String Entrenador = datos[1];
					String EstadoDerrotar = datos[2];
					String cantPokemon = datos[3];
					int EnteroCantPokemon = Integer.valueOf(cantPokemon);
					if (EstadoDerrotar.equals("Sin derrotar")) {
						if (EnteroCantPokemon == 1) {
							EntrenadorRival = Entrenador;
							String Pokemon1 = datos[4];
							PokemonEnemigo.add(Pokemon1);
							break;
						}
						else if (EnteroCantPokemon == 2) {
							EntrenadorRival = Entrenador;
							String Pokemon1 = datos[4];
							String Pokemon2 = datos[5];
							PokemonEnemigo.add(Pokemon1);
							PokemonEnemigo.add(Pokemon2);
							break;
						}
						else if (EnteroCantPokemon == 3) {
							EntrenadorRival = Entrenador;
							String Pokemon1 = datos[4];
							String Pokemon2 = datos[5];
							String Pokemon3 = datos[6];
							PokemonEnemigo.add(Pokemon1);
							PokemonEnemigo.add(Pokemon2);
							PokemonEnemigo.add(Pokemon3);
							break;
						}
						else if (EnteroCantPokemon == 4) {
							EntrenadorRival = Entrenador;
							String Pokemon1 = datos[4];
							String Pokemon2 = datos[5];
							String Pokemon3 = datos[6];
							String Pokemon4 = datos[7];
							PokemonEnemigo.add(Pokemon1);
							PokemonEnemigo.add(Pokemon2);
							PokemonEnemigo.add(Pokemon3);
							PokemonEnemigo.add(Pokemon4);
							break;
						}
						else if (EnteroCantPokemon == 5) {
							EntrenadorRival = Entrenador;
							String Pokemon1 = datos[4];
							String Pokemon2 = datos[5];
							String Pokemon3 = datos[6];
							String Pokemon4 = datos[7];
							String Pokemon5 = datos[8];
							PokemonEnemigo.add(Pokemon1);
							PokemonEnemigo.add(Pokemon2);
							PokemonEnemigo.add(Pokemon3);
							PokemonEnemigo.add(Pokemon4);
							PokemonEnemigo.add(Pokemon5);
							break;
						}
						else if (EnteroCantPokemon == 6) {
							EntrenadorRival = Entrenador;
							String Pokemon1 = datos[4];
							String Pokemon2 = datos[5];
							String Pokemon3 = datos[6];
							String Pokemon4 = datos[7];
							String Pokemon5 = datos[8];
							String Pokemon6 = datos[9];
							PokemonEnemigo.add(Pokemon1);
							PokemonEnemigo.add(Pokemon2);
							PokemonEnemigo.add(Pokemon3);
							PokemonEnemigo.add(Pokemon4);
							PokemonEnemigo.add(Pokemon5);
							PokemonEnemigo.add(Pokemon6);
							break;
						}	
					}
					linea = leyendo.readLine();
				}
				ArrayList<String> tiposJugador = verTipoPokemon(PokemonEquipo);
				ArrayList<String> tiposEnemigo = verTipoPokemon(PokemonEnemigo);
				ArrayList<Integer> PuntosJugador = verStatsPokemon(PokemonEquipo);
				ArrayList<Integer> PuntosEnemigo = verStatsPokemon(PokemonEnemigo);
				PokemonCombate jugador = C.crearPokemon(PokemonEquipo.get(actualJugador));
				PokemonCombate enemigo = C.crearPokemon(PokemonEnemigo.get(actualEnemigo));
				if (MostrarEnemigo == true) {
					MostrarEnemigo = false;
					System.out.println();
					System.out.println(EntrenadorRival + " saca a " + PokemonEnemigo.get(actualEnemigo));
				}
				System.out.println();
				if (MostrarJugador == true) {
					MostrarJugador = false;
					System.out.println(NombreUsuario + " saca a " + PokemonEquipo.get(actualJugador));
					System.out.println();
				}
				System.out.println("Que deseas hacer?");
				System.out.println("1) Atacar");
				System.out.println("2) Cambiar de pokemon");
				System.out.println("3) Rendirse");
				int OpcionCombate = sc.nextInt();
				sc.nextLine();
				while (OpcionCombate == 1) {
					while (actualJugador < PokemonEquipo.size() && actualEnemigo < PokemonEnemigo.size()) {
					    combatir(jugador, enemigo);
					    if (jugador.puntos <= 0) {
					    	MostrarJugador = true;
					        actualJugador++;
					        if (actualJugador < PokemonEquipo.size()) {
					            jugador = C.crearPokemon(PokemonEquipo.get(actualJugador));
					        }
					    }
					    if (enemigo.puntos <= 0) {
					    	MostrarEnemigo = true;
					        actualEnemigo++;
					        if (actualEnemigo < PokemonEnemigo.size()) {
					            enemigo = C.crearPokemon(PokemonEnemigo.get(actualEnemigo));
					        }
					    }
					    break;
					}	
					break;
				}
				if (OpcionCombate == 2) {
					System.out.println();
					System.out.println("Escoje un Pokemon de su equipo a cambiar");
					System.out.println();
					for (int f = 0; f < PokemonEquipo.size(); f++) {
						if (f == 0) {
							System.out.println(f +") " + "volver");	
						}
						System.out.println(f+1 +") " + PokemonEquipo.get(f));	
					}
					int OpcionCambio = sc.nextInt();
					sc.nextLine();
					if (OpcionCambio >= 0 && OpcionCambio <= 6) {
						
					}
				}
				if (OpcionCombate == 3) {
					batalla = false;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error batallar gimnasio " + e);
		}
	}
	
	//wip ver tipo del pokemon combatiente
	
	public static ArrayList<String> verTipoPokemon(ArrayList<String> PokemonEquipo) {
		ArrayList<String> Tipos = new ArrayList<>();
		try {
			FileReader arch = new FileReader("Pokedex.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				String[] datos = linea.split(";");
				String Pokemon = datos[0];
				String tipo = datos[9];
				for (int p = 0; p < PokemonEquipo.size(); p++) {
					if (Pokemon.equals(PokemonEquipo.get(p))) {
						Tipos.add(tipo);
					}
				}
				linea = leyendo.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error ver tipo Pokemon " + e);
		}
		return Tipos;
	}
	
	//wip ver stats totales del pokemon
	
	public static ArrayList<Integer> verStatsPokemon(ArrayList<String> PokemonEquipo) {
		ArrayList <Integer> Stats = new ArrayList<>();
		int sumador = 0;
		try {
			FileReader arch = new FileReader("Pokedex.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				String[] datos = linea.split(";");
				String Pokemon = datos[0];
				for (int p = 0; p < PokemonEquipo.size(); p++) {
					if (Pokemon.equals(PokemonEquipo.get(p))) {
						String vida = datos[3];
						String ataque = datos[4];
						String defensa = datos[5];
						String ataqueEspecial = datos[6];
						String defensaEspecial = datos[7];
						String velocidad = datos[8];
						int vida_int = Integer.valueOf(vida);
						int ataque_int = Integer.valueOf(ataque);
						int defensa_int = Integer.valueOf(defensa);
						int ataqueEspecial_int = Integer.valueOf(ataqueEspecial);
						int defensaEspecial_int = Integer.valueOf(defensaEspecial);
						int velocidad_int = Integer.valueOf(velocidad);
						sumador = vida_int + ataque_int + defensa_int + ataqueEspecial_int + defensaEspecial_int + velocidad_int;
						Stats.add(sumador);
						sumador = 0;
					}
				}
				linea = leyendo.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error ver tipo Pokemon " + e);
		}
		return Stats;
	}
	
	public static int tipoIndex(String tipo) {
	    switch (tipo) {
	        case "Normal": return 0;
	        case "Fuego": return 1;
	        case "Agua": return 2;
	        case "Planta": return 3;
	        case "Electrico": return 4;
	        case "Hielo": return 5;
	        case "Lucha": return 6;
	        case "Veneno": return 7;
	        case "Tierra": return 8;
	        case "Volador": return 9;
	        case "Psiquico": return 10;
	        case "Bicho": return 11;
	        case "Roca": return 12;
	        case "Fantasma": return 13;
	        case "Dragon": return 14;
	        case "Acero": return 15;
	        case "Siniestro": return 16;
	        case "Hada": return 17;
	    }
	    return 0;
	}
	
	public static double getEfectividad(String tipoAtaque, String tipoDefensa) {
	    int atk = tipoIndex(tipoAtaque);
	    int def = tipoIndex(tipoDefensa);
	    return EFECTIVIDAD[atk][def];
	}
	
	public static void atacar(PokemonCombate atacante, PokemonCombate defensor) {
	    double efectividad = getEfectividad(atacante.getTipo(), defensor.getTipo());
	    int dañoBase = atacante.getPuntos();
	    int dañoFinal = (int)(dañoBase * efectividad);
	    if (dañoFinal < 1 && efectividad > 0) {
	        dañoFinal = 1;
	    }
	    defensor.setPuntos(defensor.getPuntos() - dañoFinal);
	    if (efectividad == 0) {
	        System.out.println(atacante.getNombre() + " no afecta a " + defensor.getNombre());
	    } else if (efectividad > 1) {
	        System.out.println(atacante.getNombre() + " es supereficaz contra " + defensor.getNombre());
	    } else if (efectividad < 1) {
	        System.out.println(atacante.getNombre() + " no es muy efectivo contra" + defensor.getNombre());
	    }
	}
	
	public static void combatir(PokemonCombate p1, PokemonCombate p2) {
		boolean MostradoNormal = true;
		boolean MostradoDespues = false;
	    if (p1.puntos > 0 && p2.puntos > 0) {
	    	if (MostradoNormal == true) {
	    		System.out.println(p1.nombre + " -> " + p1.puntos + " Puntos");
	        	System.out.println(p2.nombre + " -> " + p2.puntos + " Puntos");
	    	}
	    }
	    if (p1.getPuntos() > 0) {
		    atacar(p1, p2);	
	    }
	    if (p2.getPuntos() > 0) {
	        atacar(p2, p1);
	    }
	    if (p1.puntos > 0) {
	        System.out.println(p1.nombre + " gana!");
	    } else {
	        System.out.println(p2.nombre + " gana!");
	    }
	}
	
	public static double[][] getEFECTIVIDAD() {
		return EFECTIVIDAD;
	}

	public static void setEFECTIVIDAD(double[][] eFECTIVIDAD) {
		EFECTIVIDAD = eFECTIVIDAD;
	}
	private static double[][] EFECTIVIDAD = {
		// NOR  FUE  AGU  PLA  ELE  HIE  LUC  VEN  TIE  VOL  PSI  BIC  ROC  FAN  DRA  ACE  SIN  HAD
		{  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 0.5, 1.0, 1.0 }, // NORMAL
		{  1.0, 0.5, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0 }, // FUEGO
		{  1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // AGUA
		{  1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 0.5, 1.0, 1.0 }, // PLANTA
		{  1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0 }, // ELECTRICO
		{  1.0, 0.5, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0 }, // HIELO
		{  2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5 }, // LUCHA
		{  1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.0, 1.0, 2.0 }, // VENENO
		{  1.0, 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0 }, // TIERRA
		{  1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0 }, // VOLADOR
		{  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0 }, // PSIQUICO
		{  1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 0.5, 2.0, 0.5 }, // BICHO
	   	{  1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0 }, // ROCA
	   	{  0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0 }, // FANTASMA
	   	{  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.0 }, // DRAGON
	    {  1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0 }, // ACERO
	   	{  1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5 }, // SINIESTRO
	   	{  1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 2.0, 1.0 }  // HADA
	};
}
