package viajes;

import java.time.LocalDate;
import java.util.UUID;

/**
 * - Código de reserva: una cadena de texto generada automáticamente 
 * en el constructor de forma aleatoria utilizando 
 * UUID.randomUUID().toString();
- Usuario: cadena de texto que identifica al usuario que realiza la 
reserva.
- Fecha en la que se realiza la reserva. Esta propiedad se inicializa
 en la construcción y corresponde a la fecha actual (now).
El constructor de la reserva sólo recibe como parámetro el usuario.
 * @author laura
 *
 */
public class Reserva {
	//Atributos
	private String codigo;
	private String usuario ;
	private LocalDate fecha;
	//Constructor
	public Reserva(String usuario) {
		this.usuario = usuario;
		this.fecha = LocalDate.now();
		this.codigo = UUID.randomUUID().toString();
		 
	}
	@Override
	public String toString() {
		return "Reserva [codigo=" + codigo + ", usuario=" + usuario + ", fecha=" + fecha + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	//Getters
	public String getCodigo() {
		return codigo;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	
}
