package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import model.Comentario;
import model.Libro;
@Controller
public class LibreriaController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@GetMapping("/")
	public static String index() {
		
		return "index";
	}
        @GetMapping("/nosotros")	
    	public static String nosotros (Model template) {
    	
    		return "nosotros";
        }
        @GetMapping("/libros")	
    	public static String libros (Model template) {
    	
    		return "libros";
        }
        @GetMapping("/autores")	
    	public static String autores (Model template) {
    	
    		return "autores";
        }
        @GetMapping("/sugerencias")	
    	public static String sugerencias (Model template) {
    	
    		return "sugerencias";
        }
        @PostMapping ("/graciasSugerencias")
    	public static String procesarInfoContacto(	@RequestParam String nombre,
    												@RequestParam String email, 
    												@RequestParam String asunto,	
    												@RequestParam String mensaje,
    												Model template) throws SQLException {
    		
    		if (nombre.equals("") || email.equals("") || asunto.equals("")|| mensaje.equals("")) { 
    			template.addAttribute("mensajeError", "No puede haber campos vacios");
    			template.addAttribute("nombreAnterior", nombre);
    			template.addAttribute("emailAnterior", email);
    			template.addAttribute("asuntoAnterior", asunto);
    			template.addAttribute("mensajeAnterior", mensaje);
    			
    			
    			return "sugerencias";
    			} else {		
    			enviarCorreo("no-responder@tempolibreria.com", 
    						"cynthiax984@gmail.com", 
    						"Mensaje de contacto de " + nombre,
    						"nombre: " + nombre + " email " + email + "mensaje " + mensaje);
    			enviarCorreo("no-responder@tempolibreria.com",  email, "Gracias por contactarte!","Recibimos tu sugerencia, nos vamos a contactar con vos en breve");
    			
    	
    				
    				Connection connection;
    		        connection = DriverManager.getConnection(Settings.db_url,Settings.db_user, Settings.db_password);
    		        
    		        PreparedStatement ps = connection.prepareStatement("INSERT INTO mensajes (nombre, email, asunto, mensaje) VALUES(?,?,?,?) ;");
    		        ps.setString(1, nombre);
    		        ps.setString(2, email);
    		        ps.setString(3, asunto);
    		        ps.setString(4, mensaje);
    		       
    		        ps.executeUpdate();
    			
    			return "graciasSugerencias";
    		}
    	}
    	
    	public static void enviarCorreo(String de, String para, String asunto, String contenido){
            Email from = new Email(de);
            String subject = asunto;
            Email to = new Email(para);
            Content content = new Content("text/plain", contenido);
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid("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");
            Request request = new Request();
            try {
              request.method = Method.POST;
              request.endpoint = "mail/send";
              request.body = mail.build();
              Response response = sg.api(request);
              System.out.println(response.statusCode);
              System.out.println(response.body);
              System.out.println(response.headers);
            } catch (IOException ex) {
              System.out.println(ex.getMessage()); ;
            	}
            }
           
        
        @GetMapping("/home")	
    	public static String home (Model template) {
    	
    		return "home";
        }
        
        
        
        
        @GetMapping ("/librosCargados")
    	public static String Libros(Model template) throws SQLException {
    		
    		Connection connection;
            connection = DriverManager.getConnection(Settings.db_url,Settings.db_user, Settings.db_password);
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM libros WHERE id = ? ;");
            ResultSet resultado = ps.executeQuery();
            
            ArrayList<Libro> listaLibros;
            listaLibros = new ArrayList<Libro>();
            
            while( resultado.next() ) {
               
            	Libro miLibro = new Libro( 	resultado.getInt ("id"),
            								resultado.getString("titulo"),
            								resultado.getString("categoria"),
            								resultado.getString("autor"),
            								resultado.getString("url"));
            	
            	listaLibros.add( miLibro );
            }
            template.addAttribute("listaLibros" , listaLibros);
    		return "librosCargados";
    	
        
        
        
        
        
        }
        
        
        
        
}


