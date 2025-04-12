package com.sc.spring.finalex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationServcice;

    @Autowired
    private CustomerService customerService;



    private final ObjectMapper objectMapper = new ObjectMapper(); 
    
    @GetMapping("/form")
    public String showForm(Model model) {
        Reservation reservation = new Reservation();
        reservation.setPayment(new Payment());
        model.addAttribute("reservation", reservation);
        model.addAttribute("customer", new Customer());
        return "reservationForm";
    }

    @PostMapping("/form")
    public String submitForm(
            @ModelAttribute Reservation reservation,
            @ModelAttribute Customer customer) {

        try {
           
            String reservationJson = objectMapper.writeValueAsString(reservation);
            String customerJson = objectMapper.writeValueAsString(customer);

            System.out.println("Submitted Reservation JSON:\n" + reservationJson);
            System.out.println("Submitted Customer JSON:\n" + customerJson);

            
            File targetDir = new File("target");
            if (!targetDir.exists()) {
                targetDir.mkdirs(); 
            }

            FileWriter reservationWriter = new FileWriter("target/reservation.json");
            reservationWriter.write(reservationJson);
            reservationWriter.close();

            FileWriter customerWriter = new FileWriter("target/customer.json");
            customerWriter.write(customerJson);
            customerWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        reservation = reservationServcice.add(reservation);
        
        customer.setReservation(reservation.getId());
        customerService.add(customer);

        return "reservationForm"; 
    }

}
