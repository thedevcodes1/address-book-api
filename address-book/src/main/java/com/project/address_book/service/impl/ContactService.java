package com.project.address_book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.address_book.model.Address;
import com.project.address_book.model.Contact;
import com.project.address_book.repository.ContactRepository;
//import com.project.address_book.service.ContactRequest;
import com.project.address_book.service.IContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact addContact(Contact request) {
		return contactRepository.save(request);
	}

	@Override
	public Contact updateContact(Long id, Contact latestContact) {
		
		Contact existingContact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
		existingContact.setFirstName(latestContact.getFirstName());
		existingContact.setLastName(latestContact.getLastName());
		existingContact.setEmailId(latestContact.getEmailId());
		existingContact.setMobileNumber(latestContact.getMobileNumber());
	    
		Address newAddress = latestContact.getAddress();
		Address existingAddress = existingContact.getAddress();
		
		if(existingAddress != null) {
			existingAddress.setHouseName(newAddress.getHouseName());
			existingAddress.setHouseNumber(newAddress.getHouseNumber());
			existingAddress.setCountry(newAddress.getCountry());
			existingAddress.setState(newAddress.getState());
			existingAddress.setCity(newAddress.getCity());
			existingAddress.setPinCode(newAddress.getPinCode());		
		}
		
		existingContact.setAddress(existingAddress);
		
		return contactRepository.save(existingContact);
	}

	@Override
	public Contact getContact(Long id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
	}

	@Override
	public void deleteContact(Long id) {
		if(contactRepository.existsById(id)){
			contactRepository.deleteById(id);
		}
		
	}

	@Override
	public List<Contact> getContacts() {

		List<Contact> contacts = contactRepository.findAll();
		return contacts;
	}

}
