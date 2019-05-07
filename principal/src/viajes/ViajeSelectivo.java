package viajes;
/**
 * Viaje selectivo. Es un tipo de viaje que permite vetar a los
 *  usuarios. Por tanto, este tipo de viaje añade una nueva 
 *  propiedad que contiene el conjunto de usuarios vetados. 
 *  Inicialmente la colección estará vacía, pero se pueden 
 *  añadir y eliminar usuarios vetados en cualquier momento.
 *   Por tanto, no se aceptará una reserva si el usuario que 
 *   la realiza está vetado.
 */
import java.time.LocalDate;
import java.util.HashSet;

public class ViajeSelectivo extends Viaje {

	//propiedades
	private HashSet<String> vetados;
	public ViajeSelectivo(String propietario, String coche, String ruta, LocalDate fSalida, int plazas) {
		super(propietario, coche, ruta, fSalida, plazas);
		vetados  = new HashSet<String>();
		
	}

	public ViajeSelectivo(String propietario, String coche, String ruta, LocalDate fSalida) {
		super(propietario, coche, ruta, fSalida);
		vetados  = new HashSet<String>();
	}
	
	public void vetar(String usuario) {
		this.vetados.add(usuario);
	}
	@Override
	public Reserva reservar(String usuario) {
		Reserva rv=null;
		if (!vetados.contains(usuario)) {
			rv =super.reservar(usuario);
		}
		return rv;
	}

	public void quitarVeto(String usuario) {
		if (this.vetados.contains(usuario)) {
			this.vetados.remove(usuario);
		}
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ViajeSelectivo vs = new ViajeSelectivo(this.getPropietario(),
				this.getCoche(), this.getRuta(), this.getfSalida(), this.getPlazas());
		this.copiaReservas(vs);
		return vs;
	}
}
