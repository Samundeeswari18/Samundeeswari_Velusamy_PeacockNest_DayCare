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

    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }


    public Child saveChild(Child child) {
        return childRepository.save(child);
    }

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

    public boolean existsById(Long childId) {return childRepository.existsById(childId);
    }

    public void deleteById(Long childId) {
        childRepository.deleteById(childId);
    }
}
