package com.nareen.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nareen.bookingoperations.ManageBooking;
import com.nareen.entity.Booking;
import com.nareen.entity.Flight;

@Controller
public class ManageBookingController {
	private static final Logger log = LogManager.getLogger(ManageBookingController.class);

	ManageBooking manageBooking = new ManageBooking();
	ModelAndView mv = new ModelAndView();

	@RequestMapping(value = "/getdetails", method = RequestMethod.GET)
	public ModelAndView getDetails(@RequestParam String confirmationNumber) {
		List<Booking> bookingList = manageBooking.BookedList();
		List<Booking> requestedBookList = new ArrayList<Booking>();
		for (Booking booking : bookingList) {
			if (booking.getConfirmation().equals(confirmationNumber)) {
				requestedBookList.add(booking);
			}
		}

		if (!requestedBookList.isEmpty()) {
			mv.addObject("requestedList", requestedBookList);
			mv.setViewName("getdetails");
		} else {
			mv.setViewName("detailsnotfound");
		}
		return mv;
	}

	@RequestMapping(value = "/canceltickets", method = RequestMethod.GET)
	public ModelAndView cancelTickets(HttpServletRequest request, HttpServletResponse response) {
		int ticketCount = 0;
		List<Booking> bookingList = manageBooking.BookedList();
		String confirmationNumber = (String) request.getParameter("confirmationNumber");
		for (Booking booked : bookingList) {
			if (booked.getConfirmation().equals(confirmationNumber)) {
				ticketCount++;
			}

			List<Flight> cancelList = manageBooking.listFlightDetails();
			for (Flight flight : cancelList) {
				if (flight.getFlightNumber().equals(booked.getFlightNumber())) {
					manageBooking.updateSeats(flight.getId(), ticketCount);
					mv.setViewName("cancel");
					manageBooking.deleteBooking(booked.getId());
				}
			}
		}
		if (ticketCount == 0) {
			log.info("Details not found");
			mv.setViewName("detailsnotfound");
		}
		return mv;
	}
}
