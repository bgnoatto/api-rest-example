package com.pruebas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebas.model.Persona;

public interface IPersonaDAO extends JpaRepository<Persona, Integer> {

}
