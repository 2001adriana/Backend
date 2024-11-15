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
