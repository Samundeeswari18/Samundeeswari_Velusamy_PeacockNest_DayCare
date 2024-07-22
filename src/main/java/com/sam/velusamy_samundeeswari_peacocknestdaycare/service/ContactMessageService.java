package com.sam.velusamy_samundeeswari_peacocknestdaycare.service;


import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.ContactMessage;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    /**
     * Saves a contact message to the repository.
     *
     * @param message the ContactMessage entity to save
     */
    public void saveMessage(ContactMessage message)
    {
        contactMessageRepository.save(message);
    }

    /**
     * Retrieves all contact messages from the repository.
     *
     * @return a list of all ContactMessage entities
     */
    public List<ContactMessage> getAllMessages()
    {
        return contactMessageRepository.findAll();
    }
}

