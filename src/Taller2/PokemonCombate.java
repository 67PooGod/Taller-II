package Taller2;

import java.io.BufferedReader;
import java.io.FileReader;

public class PokemonCombate {
	public String nombre;
    private String tipo;
	public int puntos;
    public PokemonCombate(String nombre, String tipo, int puntos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.puntos = puntos;
	}
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public static PokemonCombate crearPokemon(String nombres) {
    	int puntos = 0;
		try {
			FileReader arch = new FileReader("Pokedex.txt");
			BufferedReader leyendo = new BufferedReader(arch);
			String linea = leyendo.readLine();
			while ((linea) != null) {
				String[] datos = linea.split(";");
				String nombrePokemon = datos[0];
	            if (nombrePokemon.equals(nombres)) {
	                String tipo = datos[9];
					String vida = datos[3];
					String ataque = datos[4];
					String defensa = datos[5];
					String ataqueEspecial = datos[6];
					String defensaEspecial = datos[7];
					String velocidad = datos[8];
					int vidaInt = Integer.valueOf(vida);
					int ataqueInt = Integer.valueOf(ataque);
					int defensaInt = Integer.valueOf(defensa);
					int ataqueEspecialInt = Integer.valueOf(ataqueEspecial);
					int defensaEspecialInt = Integer.valueOf(defensaEspecial);
					int velocidadInt = Integer.valueOf(velocidad);
					puntos = vidaInt + ataqueInt + defensaInt + ataqueEspecialInt + defensaEspecialInt + velocidadInt;
	                return new PokemonCombate(nombrePokemon, tipo, puntos);
				}
				linea = leyendo.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error batallar crear pokemon " + e);
		}
		return null;
    }
}
