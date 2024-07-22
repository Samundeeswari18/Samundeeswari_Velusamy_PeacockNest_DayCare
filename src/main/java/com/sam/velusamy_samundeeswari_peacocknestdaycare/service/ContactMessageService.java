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

    public void saveMessage(ContactMessage message)
    {
        contactMessageRepository.save(message);
    }

    public List<ContactMessage> getAllMessages()
    {
        return contactMessageRepository.findAll();
    }
}

