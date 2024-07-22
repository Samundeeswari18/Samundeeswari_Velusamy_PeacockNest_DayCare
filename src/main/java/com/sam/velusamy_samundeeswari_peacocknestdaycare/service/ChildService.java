package com.sam.velusamy_samundeeswari_peacocknestdaycare.service;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Child;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.ChildRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    /**
     * Retrieves all children from the repository.
     *
     * @return a list of all Child entities
     */
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }


    /**
     * Saves a new child or updates an existing child in the repository.
     *
     * @param child the Child entity to save
     * @return the saved Child entity
     */
    public Child saveChild(Child child) {
        return childRepository.save(child);
    }


    /**
     * Retrieves a child by its ID.
     *
     * @param childId the ID of the child to retrieve
     * @return an Optional containing the Child entity if found, or empty if not
     */
    public Optional<Child> getChildById(Long childId) {
        return childRepository.findById(childId);
    }

    // Additional method for deleting a child by id
    public void deleteChildById(Long childId) {
        childRepository.deleteById(childId);
    }

    // Method to retrieve children by parent
    public List<Child> getChildrenByUser(User user) {
        return childRepository.findByUser(user);
    }

    // Checks if a child with a given ID exists in the repository.
    public boolean existsById(Long childId) {return childRepository.existsById(childId);
    }

    // Deletes a child by its ID.
    public void deleteById(Long childId) {
        childRepository.deleteById(childId);
    }
}
