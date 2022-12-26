package com.example.Materias;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class MateriasApplication {

	public final static String ruta = "./src/main/resources/static/data/ExportacionGRUPOS-MATERIAS.xml";


	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(MateriasApplication.class, args);
	}

}
