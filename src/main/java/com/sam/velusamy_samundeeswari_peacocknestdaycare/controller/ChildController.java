package com.sam.velusamy_samundeeswari_peacocknestdaycare.controller;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.UserDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Child;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.ChildRepository;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.ChildService;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.FileStorageService;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ChildController {

    @Autowired
    private ChildService childService;

    @Autowired
    private UserService userService;


    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/childRegister")
    public String showAddChildForm(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("child", new Child());
        model.addAttribute("parentId"); // Replace with actual parentId
//        model.addAttribute("parents", parents);
        return "childRegistrationForm"; // Assuming "childRegistrationForm" is your template
    }

//    @PostMapping("/childRegister")
//    public String saveChild(@ModelAttribute("child") Child child,
//                            @RequestParam("parentId") Long parentId) {
//        Optional<User> user= userService.getUserById(parentId);
//        child.setUser(user.get());
//        childService.saveChild(child);
//        return "redirect:/childRegister";
//    }




//    @GetMapping("/childRegister")
//    public String showAddChildForm(Model model) {
//        List<UserDto> users = userService.findAllUsers();
//        model.addAttribute("child", new Child());
//        model.addAttribute("parentId",0L); // Initialize parentId attribute
//        model.addAttribute("users", users); // Adding the list of users to the model
//        return "childRegistrationForm"; // Ensure this is the correct template name
//    }
//
    @PostMapping("/childRegister")
    public String saveChild(@Valid @ModelAttribute("child") Child child,
                            BindingResult result,
                            @RequestParam("parentId") Long parentId,
                            @RequestParam("photoUrl") MultipartFile photo,
                            @RequestParam("medicalInfo") MultipartFile medicalInfo) {
        if (result.hasErrors()) {
            return "childRegistrationForm";
        }

        try {
            String photoPath = fileStorageService.storeFile(photo);
            String medicalInfoPath = fileStorageService.storeFile(medicalInfo);

            child.setPhotoUrl(photoPath);
            child.setMedicalInfo(medicalInfoPath);

            Optional<User> user = userService.getUserById(parentId);
            if (user.isPresent()) {
                child.setUser(user.get());
                Child savedChild = childService.saveChild(child);
                System.out.println("Saved child: " + savedChild); // Debug log
                return "redirect:/children/success";
            } else {
                result.rejectValue("parentId", "error.child", "Parent not found");
                return "childRegistrationForm";
            }

        } catch (IOException e) {
            e.printStackTrace();
            result.rejectValue("photo", "error.child", "Failed to store files");
            return "childRegistrationForm";
        }
    }


    @GetMapping("/registeredChildren/list")
    public String listChildren(Model model) {
        List<Child> children = childService.getAllChildren();
        model.addAttribute("children", children); // Use "children" instead of "child"
        return "registeredChildren"; // Ensure this is the correct template name
    }


    @GetMapping("/deleteChild")
    public String showDeleteChildPage() {
        return "deleteChild";
    }



    @PostMapping("/deleteChild")
    public String deleteChild(@RequestParam("childId") Long childId, Model model) {
        if (childService.existsById(childId)) {
            childService.deleteById(childId);
            model.addAttribute("successMessage", "Child deleted successfully.");
        } else {
            model.addAttribute("errorMessage", "Child ID is not valid.");
        }
        return "deleteChild";
    }

    @GetMapping("/childInfo")
    public String showParentInfoForm(Model model) {
        model.addAttribute("email", "");
        return "childInfoByParentEmail";
    }


    @PostMapping("/childInfo")
    public String getParentInfo(@RequestParam("email") String email, Model model) {
        User user = userService.findUserByEmail(email);
        if (user != null) {
            List<Child> children = childService.getChildrenByUser(user);
            model.addAttribute("children", children);
        } else {
            model.addAttribute("error", "No parent found with the provided email.");
        }
        model.addAttribute("email", email);
        return "childInfoByParentEmail";
    }

}
