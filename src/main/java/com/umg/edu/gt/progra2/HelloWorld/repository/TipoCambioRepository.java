package com.umg.edu.gt.progra2.HelloWorld.repository;
import com.umg.edu.gt.progra2.HelloWorld.entity.TipoCambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mejia
 */
@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambioEntity, Long> {
}