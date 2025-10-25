package com.project.address_book.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.address_book.model.Contact;

public interface IContactService {
	
	Contact addContact(Contact request);

	Contact updateContact(Long id, Contact contact);

	Contact getContact(Long id);

	void deleteContact(Long id);

	List<Contact> getContacts();

}
