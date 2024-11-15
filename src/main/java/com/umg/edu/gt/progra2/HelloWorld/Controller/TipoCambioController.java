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