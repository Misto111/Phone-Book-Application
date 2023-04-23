package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entity.Contact;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContactController {
    Map<String, Contact> contacts;

    public ContactController() {
        this.contacts = new LinkedHashMap<>();
    }

    @GetMapping("/")

    public ModelAndView home(ModelAndView modelAndView) {

        modelAndView.setViewName("index");
        modelAndView.addObject("contacts", this.contacts);

        return modelAndView;


    }
    @PostMapping("/")
    public String add(Contact contact) {
        this.contacts.put(contact.getName(), contact);
        return "redirect:/";
    }
    @GetMapping("/edit{name}")
    public ModelAndView editForm(@PathVariable String name, ModelAndView modelAndView) {
        modelAndView.setViewName("edit");
        modelAndView.addObject(this.contacts.get(name));

        return modelAndView;
    }
    @PutMapping ("/edit{name}")
    public String edit(@PathVariable String name, Contact contact) {
        Contact contact1 = this.contacts.get(name);
        contact1.setName(contact.getName());
        contact1.setNumber(contact.getNumber());

        return "redirect:/";
    }
    @DeleteMapping("/delete{name}")
    public String delete(@PathVariable String name) {
        this.contacts.remove(name);
        return "redirect:/";
    }
}
