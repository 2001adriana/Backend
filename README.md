# intermedioSoapBanguat

------Backend - Tipo de Cambio (Spring Boot)

Este repositorio contiene el componente backend de la aplicación para obtener el tipo de cambio de la moneda a quetzal utilizando un servicio SOAP del Banco de Guatemal


-------Requisitos----------

- JDK 17 o superior
- Maven 3.9.9 
- Base de datos MarianaDB
- IDE recomendad IntelliJ IDEA 

-------Instalación----------

Clona este repositorio:
bash
git clone https://github.com/tu-usuario/backend-tipo-cambio.git
cd backend-tipo-cambio

----Compila y ejecuta la aplicación-----

bash
mvn clean install
mvn spring-boot:run

La aplicación se ejecutará en el puerto 8080 de forma predeterminada

-----Configuración-----
El servicio SOAP del Banco de Guatemala será consumido a través del método TipoCambioDiaString.

La base de datos MarianaDB se utiliza de forma predeterminada. Si deseas configurar una base de datos externa, ajusta las propiedades en application.properties.

Ejemplo de configuración para base de datos MySQL:

properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=usuario
spring.datasource.password=contraseña

------Endpoints-----
Obtener el tipo de cambio actual:
URL: /api/tipo-cambio
GET
Respuesta: JSON con el tipo de cambio actual y la última fecha de actualización.
Registrar solicitud y respuesta:

Cada solicitud al servicio SOAP será registrada en la base de datos junto con el número de solicitud y la respuesta del tipo de cambio.

Puedes ejecutar los tests de la aplicación usando Maven
bash
mvn test

----Instala dependencias----
npm install

-----Ejecuta la aplicación en modo desarrollo-----
bash
npm run dev

http://localhost:8080/tipoCambioDia

package com.umg.edu.gt.progra2.HelloWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.umg.edu.gt.progra2.HelloWorld")
public class HelloWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}


---------------
package com.umg.edu.gt.progra2.HelloWorld.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

		@GetMapping("/hello")
		public String Hello() {
			return "Hola mundo!";
		}
	
}

----------------
package com.umg.edu.gt.progra2.HelloWorld.controller;

import com.umg.edu.gt.progra2.HelloWorld.entity.TipoCambioEntity;
import com.umg.edu.gt.progra2.HelloWorld.service.TipoCambioSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class TipoCambioController {

    @Autowired
    private TipoCambioSoapService tipoCambioSoapService;

    @GetMapping("/tipoCambioDia")
    public ResponseEntity<TipoCambioEntity> obtenerTipoCambioDia() {
        TipoCambioEntity tipoCambio = tipoCambioSoapService.obtenerTipoCambioDia();
        return ResponseEntity.ok(tipoCambio);
    }

    @GetMapping("/tipoCambio")
    public ResponseEntity<List<TipoCambioEntity>> obtenerTiposCambio() {
        List<TipoCambioEntity> tiposCambio = (List<TipoCambioEntity>) tipoCambioSoapService.obtenerTipoCambioDia();
        return ResponseEntity.ok(tiposCambio);
    }
}
------------
package com.umg.edu.gt.progra2.HelloWorld.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umg.edu.gt.progra2.HelloWorld.Dto.UsuariosDto;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@GetMapping("/{id}")
	public ResponseEntity<UsuariosDto> obtenerUsuario(@PathVariable Long id) {
		UsuariosDto usuario = new UsuariosDto(id, "Walter", "Apellido");
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuariosDto> actualizarUsuario(@PathVariable Long id,
			@RequestBody UsuariosDto usuario) {
		usuario.setId(id);
		return ResponseEntity.ok(usuario);
	}
	
}
---------------
package com.umg.edu.gt.progra2.HelloWorld.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoCambio {
    private String fecha;
    private double referencia;
}
-----------
package com.umg.edu.gt.progra2.HelloWorld.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuariosDto {

	private Long id;
	private String nombre;
	private String apellido;
		
}
-----------------
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.edu.gt.progra2.HelloWorld.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
/**
 *
 * @author mejia
 */
@Entity
public class TipoCambioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String noTransaccion;
    private LocalDateTime fecha;
    private double valor;

    // Constructor
    public TipoCambioEntity() {}

    public TipoCambioEntity(String noTransaccion, LocalDateTime fecha, double valor) {
        this.noTransaccion = noTransaccion;
        this.fecha = fecha;
        this.valor = valor;
    }

    // Logica bd
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoTransaccion() {
        return noTransaccion;
    }

    public void setNoTransaccion(String noTransaccion) {
        this.noTransaccion = noTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

-------
package com.umg.edu.gt.progra2.HelloWorld.model;

public class TipoCambio {
    private int moneda;
    private String fecha;
    private float venta;
    private float compra;
}
--------
package com.umg.edu.gt.progra2.HelloWorld.repository;
import com.umg.edu.gt.progra2.HelloWorld.entity.TipoCambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mejia
 */
public interface TipoCambioRepository extends JpaRepository<TipoCambioEntity, Long> {
}

--------
package com.umg.edu.gt.progra2.HelloWorld.service;

import com.umg.edu.gt.progra2.HelloWorld.entity.TipoCambioEntity;
import com.umg.edu.gt.progra2.HelloWorld.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

@Service
public class TipoCambioSoapService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public TipoCambioEntity obtenerTipoCambioDia() {
        String soapEndpoint = "https://banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL";
        String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";

        String soapRequest = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
            + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
            + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<soap:Body>"
            + "<TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />"
            + "</soap:Body>"
            + "</soap:Envelope>";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", soapAction);

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);
        String result;

        try {
            result = restTemplate.exchange(soapEndpoint, HttpMethod.POST, request, String.class).getBody();
            System.out.println(result);

            // Extrae datos (ej. fecha y valor de referencia) y crea un registro en la BD
            // Aquí se pone un ejemplo con valores ficticios para ilustración.
            String fecha = "2023-11-14"; // Deberás extraer esto de `result`
            double referencia = 7.75; // Deberás extraer esto de `result`

            TipoCambioEntity tipoCambio = new TipoCambioEntity("TX-12345", LocalDateTime.now(), referencia);
            tipoCambioRepository.save(tipoCambio);

            return tipoCambio;
        } catch (Exception e) {
            System.out.println("Error al obtener el tipo de cambio: " + e.getMessage());
            return null;
        }
    }
}







