package viajes;

import java.time.LocalDate;
import java.util.LinkedHashMap;


/**
 * Un viaje representa una oferta que hace un propietario para 
 * compartir su vehículo. Las propiedades que caracterizan a un viaje son:
 * 
- Propietario del vehículo (cadena de texto).
- Coche: cadena de texto con el modelo del coche utilizado para el viaje.
- Ruta: cadena que describe el viaje (por ejemplo, “Murcia – Valencia – Barcelona�?).
- Fecha de salida.
- Plazas ofrecidas.
- Reservas: lista con las reservas que se han realizado.
- Número de plazas reservadas (propiedad calculada). 
	Se corresponde con el número de reservas.
- Plazas disponibles (propiedad calculada). Corresponde con la diferencia
 entre las plazas ofrecidas y el número de plazas reservadas.
 * @author laura
 *
 */
public class Viaje {
	
	//Constantes
	private final static int MINIMO_PLAZAS = 1;
	//Atributos
	private String propietario; // Propietario del vehículo (cadena de texto).
	private String coche; // Coche: texto con el modelo del coche utilizado para el viaje.
	private String ruta; // cadena que describe el viaje (por ejemplo, “Murcia – Valencia – Barcelona�?).
	private LocalDate fSalida; //- Fecha de salida.
	private int plazas; //- Plazas ofrecidas.
	private LinkedHashMap<String,Reserva> reservas;//- Reservas: lista con las reservas que se han realizado.
	//- Número de plazas reservadas (propiedad calculada). 
	//Se corresponde con el número de reservas.
	//- Plazas disponibles (propiedad calculada). 
	public int plazasReservadas() {
		return reservas.size();
	}
	public int plazasDisponibles() {
		return plazas - plazasReservadas();
	}
	//Constructores.
	/**
	 * Las propiedades propietario, coche, ruta, fecha de salida y 
	 * número de plazas no se pueden modificar una vez establecidas
	 *  en la construcción. 
	 *  Las reservas serán añadidas a través de un método 
	 *  (se describe más adelante). Inicialmente la lista de
	 *   reservas es vacía. En el constructor se establece 
	 *   el propietario, el coche, la ruta, la fecha de salida 
	 *   y las plazas ofrecidas. 
	 *   
	 *   También se ofrece un segundo constructor en el que 
	 *   se omite el número de plazas tomando como valor por defecto 1 
	 *   (constante).
	 */
	public Viaje(String propietario, String coche, String ruta, LocalDate fSalida, int plazas) {
		this.propietario = propietario;
		this.coche = coche;
		this.ruta = ruta;
		this.fSalida = fSalida;
		this.plazas = plazas;
		this.reservas = new LinkedHashMap<String, Reserva>();
	}
	public Viaje(String propietario, String coche, String ruta, LocalDate fSalida) {
		
		this(propietario, coche, ruta, fSalida, MINIMO_PLAZAS);
	}
	//métodos
	/**
	 * La funcionalidad que ofrece la clase viaje es:
- realizar reserva: el método recibe como parámetro el usuario 
(cadena de texto) que realiza la reserva.
 Para poder formalizar la reserva se tiene que cumplir que: 
 queden plazas disponibles y 
 que el viaje no se haya realizado ya (la fecha en la que se está 
 realizando la reserva tiene que ser anterior a la fecha de salida). 
 Si se cumplen estas restricciones, se generará un objeto reserva
  que se registrará en el viaje y se devuelve como resultado de 
  la ejecución. En el caso de que no se cumplan algunas de las 
  condiciones el método devolverá null.
	 */
	public Reserva reservar(String usuario ) {
		Reserva rv =null;
		if (plazasDisponibles()>0 && //hay plazas, 
			fSalida.isAfter(LocalDate.now()) //no se ha realizado aún.
			) { 
			rv =  new Reserva(usuario);
			this.reservas.put(rv.getCodigo(), rv);
		}
		return rv;
	}
	/**
- Consultar la reserva asociada a un código: el método recibe como 
parámetro un código de reserva y devuelve el objeto reserva 
cuyo código es igual al recibido como parámetro, o null si no 
existe ninguna reserva con ese código.
	 */
	public Reserva consulta(String codigo) {
		Reserva rv = null;
		if ( this.reservas.containsKey(codigo)) {
			rv =  reservas.get(codigo);
		}
		return rv;
	}
	@Override
	public String toString() {
		return "Viaje [propietario=" + propietario + ", coche=" + coche + ", ruta=" + ruta + ", fSalida=" + fSalida
				+ ", plazas=" + plazas + ", reservas=" + reservas + ", plazasReservadas()=" + plazasReservadas()
				+ ", plazasDisponibles()=" + plazasDisponibles() + "]";
	}
	//Getters
	public static int getMinimoPlazas() {
		return MINIMO_PLAZAS;
	}
	public String getPropietario() {
		return propietario;
	}
	public String getCoche() {
		return coche;
	}
	public String getRuta() {
		return ruta;
	}
	public LocalDate getfSalida() {
		return fSalida;
	}
	public int getPlazas() {
		return plazas;
	}
	public LinkedHashMap<String, Reserva> getReservas() {
		return reservas;
	}
	
	//Segunda parte
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Viaje vj = new Viaje(this.propietario,this.coche, this.ruta, this.fSalida, this.plazas);

		return vj;
	}
	protected void copiaReservas(Viaje vj) {
		for (int i = 0; i < this.reservas.size(); i++) {
			vj.reservar(this.reservas.get(i).getUsuario());
		}
	}
}
