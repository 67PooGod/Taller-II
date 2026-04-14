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
			System.out.println(z+1 + ") " + zonas("Habitats.txt").get(z));
		}
	}
	
	public String ver_pokemon(int zonaEscojida) {
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
	
	//wip batallar gimnasio

	public static void RetarGimnasio(String archivo, ArrayList<String> PokemonEquipo, ArrayList<String> EstadoActual) {
		boolean batalla = true;
		int contPelear = 0;
		double[][] TipoPelear = new double[17][17];
		ArrayList<String> PokemonEnemigo = new ArrayList<>();
		try {
			while (batalla == true) {
				FileReader arch = new FileReader("Gimnasios.txt");
				BufferedReader leyendo = new BufferedReader(arch);
				String linea = leyendo.readLine();
				while ((linea) != null) {
					String[] datos = linea.split(";");
					String Entrenador = datos[0];
					String EstadoDerrotar = datos[2];
					String cantPokemon = datos[3];
					int EnteroCantPokemon = Integer.valueOf(cantPokemon);
					contPelear = EnteroCantPokemon;
					if (EstadoDerrotar.equals("Sin derrotar")) {
						if (EnteroCantPokemon == 1) {
							String Pokemon1 = datos[3];
							PokemonEnemigo.add(Pokemon1);
							break;
						}
						else if (EnteroCantPokemon == 2) {
							String Pokemon1 = datos[3];
							String Pokemon2 = datos[4];
							PokemonEnemigo.add(Pokemon1 + "+" + Pokemon2);
							break;
						}
						else if (EnteroCantPokemon == 3) {
							String Pokemon1 = datos[3];
							String Pokemon2 = datos[4];
							String Pokemon3 = datos[5];
							PokemonEnemigo.add(Pokemon1 + "+" + Pokemon2 + "+" + Pokemon3);
							break;
						}
						else if (EnteroCantPokemon == 4) {
							String Pokemon1 = datos[3];
							String Pokemon2 = datos[4];
							String Pokemon3 = datos[5];
							String Pokemon4 = datos[6];
							PokemonEnemigo.add(Pokemon1 + "+" + Pokemon2 + "+" + Pokemon3 + "+" + Pokemon4);
							break;
						}
						else if (EnteroCantPokemon == 5) {
							String Pokemon1 = datos[3];
							String Pokemon2 = datos[4];
							String Pokemon3 = datos[5];
							String Pokemon4 = datos[6];
							String Pokemon5 = datos[7];
							PokemonEnemigo.add(Pokemon1 + "+" + Pokemon2 + "+" + Pokemon3 + "+" + Pokemon4 + "+" + Pokemon5);
							break;
						}
						else if (EnteroCantPokemon == 6) {
							String Pokemon1 = datos[3];
							String Pokemon2 = datos[4];
							String Pokemon3 = datos[5];
							String Pokemon4 = datos[6];
							String Pokemon5 = datos[7];
							String Pokemon6 = datos[8];
							PokemonEnemigo.add(Pokemon1 + "+" + Pokemon2 + "+" + Pokemon3 + "+" + Pokemon4 + "+" + Pokemon5 + "+" + Pokemon6);
							break;
						}	
					}
					linea = leyendo.readLine();
				}
				ArrayList<String> tiposJugador = verTipoPokemon(PokemonEquipo);
				ArrayList<String> tiposEnemigo = verTipoPokemon(PokemonEnemigo);
				ArrayList<Integer> PuntosJugador = verStatsPokemon(PokemonEquipo);
				ArrayList<Integer> PuntosEnemigo = verStatsPokemon(PokemonEnemigo);
				System.out.println(PuntosJugador);
				System.out.println(PokemonEquipo);
				break;
			}
		} catch (Exception e) {
			System.out.println("Error batallar gimnasio " + e);
		}
	}
	
	//wip ver tipo del pokemon combatiente
	
	public static ArrayList<String> verTipoPokemon(ArrayList<String> PokemonSeleccionado) {
		ArrayList<String> Tipos = new ArrayList<>();
		try {
			FileReader arch = new FileReader("Pokedex.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				String[] datos = linea.split(";");
				String Pokemon = datos[0];
				String tipo = datos[9];
				for (int p = 0; p < PokemonSeleccionado.size(); p++) {
					if (Pokemon.equals(PokemonSeleccionado.get(p))) {
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
	
	public static ArrayList<Integer> verStatsPokemon(ArrayList<String> PokemonSeleccionado) {
		ArrayList <Integer> Stats = new ArrayList<>();
		int sumador = 0;
		try {
			FileReader arch = new FileReader("Pokedex.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				String[] datos = linea.split(";");
				String Pokemon = datos[0];
				for (int p = 0; p < PokemonSeleccionado.size(); p++) {
					if (Pokemon.equals(PokemonSeleccionado.get(p))) {
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
					}
				}
				sumador = 0;
				linea = leyendo.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error ver tipo Pokemon " + e);
		}
		return Stats;
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
