package com.project.address_book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.address_book.model.Contact;
import com.project.address_book.service.IContactService;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

	@Autowired
	private IContactService contactService;

	@GetMapping("/getAllContacts")
	public ResponseEntity<List<Contact>> getAlLContacts() {
		return ResponseEntity.ok(contactService.getContacts());
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Contact> getById(@PathVariable Long id) {
		return ResponseEntity.ok(contactService.getContact(id));
	}

	@PostMapping("/addContact")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.addContact(contact));
	}

	@PutMapping("/updateContact/{id}")
	public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
		return ResponseEntity.ok(contactService.updateContact(id, contact));
	}
	
	@DeleteMapping("/deleteContact/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
		return ResponseEntity.ok().body("Contact deleted successfully");	
	}
}
