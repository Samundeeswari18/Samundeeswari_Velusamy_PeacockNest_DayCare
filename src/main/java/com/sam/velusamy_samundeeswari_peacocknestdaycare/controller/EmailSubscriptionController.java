package com.sam.velusamy_samundeeswari_peacocknestdaycare.controller;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.EmailSubscription;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.EmailSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailSubscriptionController {
    private final EmailSubscriptionService emailSubscriptionService;

    @Autowired
    public EmailSubscriptionController(EmailSubscriptionService emailSubscriptionService) {
        this.emailSubscriptionService = emailSubscriptionService;
    }

    // Displays the email subscription form on the index page.
    @GetMapping("/index")
    public String showEmailSubscriptionForm(Model model) {
        model.addAttribute("emailSubscription", new EmailSubscription());
        return "index"; // This assumes your template is named index.html
    }

    //Handles the submission of the email subscription form.
    //Saves the email subscription using the EmailSubscriptionService.
    @PostMapping("/emailSubscription")
    public String submitEmailSubscriptionForm(@ModelAttribute EmailSubscription emailSubscription) {
        emailSubscriptionService.saveEmailSubscription(emailSubscription);
        return "redirect:/index?success"; // Redirect to the form page with a success parameter
    }

    //Displays a list of all email subscriptions.
    @GetMapping("/emailSubscription/list")
    public String showAllEmailSubscription(Model model) {
        model.addAttribute("emailSubscription", emailSubscriptionService.findAllEmailSubscription());
        return "emailSubscriptionList";
    }
}
