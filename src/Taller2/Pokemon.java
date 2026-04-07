package Taller2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Pokemon {
	private String Nombre;
	private boolean Empezo;
	private String Medallas;
	private String Estado;
	public Pokemon(String Nombre, boolean Empezo, String Medallas, String Estado) {
		this.Nombre = Nombre;
		this.Empezo = Empezo;
		this.Medallas = Medallas;
		this.Estado = Estado;
	}

	public String getNombre(String Nombre) {
		return this.Nombre;
	}
	
	public void setNombre(String Nombre) {
	    this.Nombre = Nombre;
	}

	public boolean isEmpezo(boolean Empezo) {
		return this.Empezo;
	}
	
	public void setEmpezo(boolean Empezo) {
	    this.Empezo = Empezo;
	}
	
	public boolean isEstado(String Estado) {
		return this.Empezo;
	}
	
	public void setEstado(String Estado) {
	    this.Estado = Estado;
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
					if (prob <= probabilidad) {
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
}
