/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.edu.gt.progra2.HelloWorld.JPA;
import com.umg.edu.gt.progra2.HelloWorld.entity.TipoCambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author mejia
 */

public interface JPARepository extends JpaRepository<TipoCambioEntity, Long> {
}
