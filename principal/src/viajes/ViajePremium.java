package viajes;


import java.time.LocalDate;
import java.time.Period;

/**
 * Viaje premium. Es un tipo de viaje se caracteriza 
 * por permitir cancelar las reservas hasta el día antes 
 * de la fecha de salida. Para realizar la cancelación
 *  se proporciona el código de la reserva y devuelve un 
 *  valor booleano indicando si se ha efectuado la cancelación.
 * @author laura
 *
 */
public class ViajePremium extends Viaje {

	public ViajePremium(String propietario, String coche, String ruta, LocalDate fSalida) {
		super(propietario, coche, ruta, fSalida);
	
	}
	public ViajePremium(String propietario, String coche, String ruta, LocalDate fSalida, int plazas) {
		super(propietario, coche, ruta, fSalida, plazas);
		
	}
	public boolean cancelar(String codigo) {
		boolean cancelado = false;
		if (this.getReservas().containsKey(codigo)) {
			Reserva rv = this.getReservas().get(codigo);
			LocalDate fsalida = this.getfSalida();
			LocalDate factual = LocalDate.now();
			Period periodo = Period.between(fsalida, factual);
			int dias = periodo.getDays();
				
			if (dias >=1) {
				//Cancelar reserva
				cancelado = true;
				this.getReservas().remove(codigo);
			}
		}
		return cancelado;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ViajePremium vp = new ViajePremium(this.getPropietario(), this.getCoche(), 
							this.getRuta(), this.getfSalida(), this.getPlazas());
		this.copiaReservas(vp);
		return vp;
	}
	
	
}
