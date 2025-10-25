package com.project.address_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.address_book.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
