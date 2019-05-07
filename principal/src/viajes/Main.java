package viajes;
/**
 * Documentaci髇 incluida en el eclipse, comprobar cambios
 */
import java.time.LocalDate;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		/*
		 * Implementa el siguiente programa para probar la funcionalidad:
		 * 
- Crea los siguientes viajes cuyo propietario sea 鈥淛os茅 Antonio鈥?
 y coche 鈥淪eat Le贸n鈥?:
- Viaje 鈥淢urcia-Cartagena鈥? con fecha de salida 9/junio/2019
 con el n煤mero de plazas por defecto.
 
  */
		Viaje viajeMCartagena = new Viaje("Jose Antonio", "Seat Le贸n", "Murcia-Cartagena", LocalDate.of(2019,  6, 9));
		
		/*
- Viaje selectivo 鈥淢urcia鈥揅ampus鈥? con fecha de salida 10/junio/2019 y 4 plazas.
- Vetar a 鈥淏eatriz鈥? en el viaje selectivo.
*/
		ViajeSelectivo viajeMCampus = new ViajeSelectivo("Jose Antonio", "Seat Le贸n", "Murcia-Campus", LocalDate.of(2019,  6, 10),4);
		viajeMCampus.vetar("Beatriz");
		/*
- Viaje premium 鈥淢urcia-Barcelona鈥? con fecha de salida 15/junio/2019 y 6 plazas.
	*/
		ViajePremium viajeMBar = new ViajePremium("Jose Antonio", "Seat Le贸n", "Murcia-Barcelona", LocalDate.of(2019,  6, 15),6);
		
		/*
- Realiza las siguientes reservas:
- "Alberto" hace 2 reservas en el viaje 鈥淢urcia-Cartagena鈥?.
- "Enrique" hace 3 reservas en el viaje 鈥淢urcia-Campus鈥?.
- "Beatriz" hace 1 reserva en el viaje 鈥淢urcia-Campus鈥?.
- "Ana" hace 2 reservas en el viaje 鈥淢urcia-Barcelona鈥?.
- 鈥淎na鈥? cancela la 煤ltima reserva que ha hecho.
*/
		Reserva rv;
		rv = viajeMCartagena.reservar("Alberto");
		rv = viajeMCartagena.reservar("Alberto");
		rv = viajeMCampus.reservar("Enrique");
		rv = viajeMCampus.reservar("Enrique");
		rv = viajeMCampus.reservar("Enrique");
		rv = viajeMCampus.reservar("Beatriz");
		rv = viajeMBar.reservar("Ana");
		rv = viajeMBar.reservar("Ana");
		viajeMBar.cancelar(rv.getCodigo());
		
		/*
- Crea una colecci贸n con los tres viajes anteriores.
*/
		Collection conjunto = new HashSet<Viaje>();
		conjunto.add(viajeMCartagena);
		conjunto.add(viajeMCampus);
		conjunto.add(viajeMBar);
		
		/*
- Recorre la colecci贸n de viajes:
- Si el viaje es selectivo, quitar el veto a 鈥淏eatriz鈥?.
- Imprime (toString) la informaci贸n de cada viaje.
*/
		for (Object viaje : conjunto) {
			Viaje v = (Viaje) viaje;
			System.out.println(v.toString());
			if (v instanceof ViajeSelectivo ) {
				((ViajeSelectivo) v).quitarVeto("Beatriz");
			}
		}
		/*
Ejercicio guiado. Copia de viajes.
Implementa el m茅todo clone de la clase Object en las clases
 que implementan los viajes siguiendo las recomendaciones
  de la asignatura y seg煤n la sem谩ntica de los tipos de datos.
   En la implementaci贸n del m茅todo clone se implementar谩 una 
   copia profunda.
Modifica el programa y a帽ade la funcionalidad:
- Declara y construye un segundo conjunto de viajes (copias).

*/
		HashSet<Viaje> conjunto2 = new HashSet<Viaje>();
		/*
- Recorre el conjunto viajes y para cada elemento:
-- Crea una copia del viaje y a帽谩dela al conjunto copias.
-- Si el viaje es Premium, cancela todas las reservas en el objeto copia.

		 */
		try {
			
			for (Object vj : conjunto) {
				Viaje vjOriginal = (Viaje) vj;
				Viaje vjCopia = (Viaje) vjOriginal.clone();
				if (vjCopia instanceof ViajePremium) {
					//LinkedHashMap<String,Reserva>
					for (String codigo : vjCopia.getReservas().keySet()) {
						((ViajePremium) vjCopia).cancelar(codigo);
					}
					
				}
				conjunto2.add(vjCopia);
			}

		} catch (Exception e) {
			System.out.println("Se ha producido un error: "+e.getMessage());
		}
	
		
		/*
		 
- Muestra el contenido del conjunto copias por la consola (toString).

		 */
		System.out.println("Conjunto 2");
		for (Object viaje : conjunto2) {
			Viaje v = (Viaje) viaje;
			System.out.println(v.toString());
			
		}
	}

}
