package com.sc.spring.finalex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	public Reservation add(Reservation reservation) {

		Payment pay = new Payment(reservation.getPayment().getAmount(), reservation.getPayment().getDate());
		pay.setAmount(pay.calculate());
		paymentRepository.save(pay);
		reservation.setPayment(pay);
		return reservationRepository.save(reservation);

	}

	public void updateDetails(String id, String newDetails) {
		Reservation res = reservationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reservation not found."));
		res.update(newDetails);
		reservationRepository.save(res);
	}

}
