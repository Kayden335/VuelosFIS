package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ModeloFactura {

    public String generarFactura(String idVenta, LocalDate fechaVenta, ModeloRegistro usuario, 
                                ModeloPago pago, List<ModeloBoleto> boletos, double total) {
        StringBuilder sb = new StringBuilder();
        LocalDateTime fechaHora = fechaVenta.atTime(LocalDateTime.now().toLocalTime());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Cabecera
        sb.append("====================================================\n");
        sb.append("                  VUELOS FIS                        \n");
        sb.append("====================================================\n");
        sb.append("Ticket Nro: ").append(idVenta).append("\n");
        sb.append("Fecha:      ").append(fechaHora.format(formato)).append("\n");
        sb.append("Cliente:    ").append(usuario.getNombre()).append(" ").append(usuario.getApellido()).append("\n");
        sb.append("Cédula:     ").append(usuario.getCedula()).append("\n");
        sb.append("Pago:       ").append(pago.getMetodo().toUpperCase()).append("\n");
        sb.append("----------------------------------------------------\n");
        
        // Encabezados de columnas
        sb.append(String.format("%-15s %-10s %-10s %-12s %-10s\n", 
            "PASAJERO", "CLASE", "VUELO", "TIPO", "PRECIO")); 
        sb.append("----------------------------------------------------\n");

        // Filas de boletos
        for (ModeloBoleto b : boletos) {
            String tipo = b.getTipoVuelo();
            String vuelo = b.getVueloSeleccionado();
            if (vuelo == null) vuelo = "N/A";
            if (tipo == null) tipo = "N/A";
            
            sb.append(String.format("%-15s %-10s %-10s %-12s $%-10.2f\n",
                b.getNombre().length() > 15 ? b.getNombre().substring(0, 15) : b.getNombre(),
                b.getClaseVuelo(),
                vuelo,
                tipo,
                b.getPrecio()));
        }

        // Totales
        double iva = total * 0.15;
        sb.append("----------------------------------------------------\n");
        sb.append(String.format("SUBTOTAL:                           $%.2f\n", total));
        sb.append(String.format("IVA (15%%):                          $%.2f\n", iva));
        sb.append(String.format("TOTAL A PAGAR:                      $%.2f\n", (total + iva)));
        sb.append("====================================================\n");
        sb.append("             ¡Gracias por su compra!                \n");

        return sb.toString();
    }

    public void guardarTicketEnArchivo(String contenido, String idVenta) {
        String nombreArchivo = "ticket_" + idVenta + ".txt";
        File archivo = new File(nombreArchivo);

        try (FileWriter fw = new FileWriter(archivo); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al guardar el ticket: " + e.getMessage());
        }
    }
}