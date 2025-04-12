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
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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
            // Serialize using Jackson
            String reservationJson = objectMapper.writeValueAsString(reservation);
            String customerJson = objectMapper.writeValueAsString(customer);

            System.out.println("Submitted Reservation JSON:\n" + reservationJson);
            System.out.println("Submitted Customer JSON:\n" + customerJson);

            // Write JSON to target/ folder
            File targetDir = new File("target");
            if (!targetDir.exists()) {
                targetDir.mkdirs(); // create target directory if not exists
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

        // Save Payment first (embedded in reservation)
        paymentRepository.save(reservation.getPayment());

        // Save Reservation
        reservationRepository.save(reservation);

     
        customer.setReservation(reservation.getId());
        customerRepository.save(customer);

        return "reservationForm"; 
    }

}
