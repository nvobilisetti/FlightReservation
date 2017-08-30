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
import org.springframework.web.servlet.ModelAndView;

import com.nareen.bookingoperations.ManageBooking;
import com.nareen.entity.Booking;
import com.nareen.entity.Flight;

@Controller
public class BookingController {
	private static final Logger log = LogManager.getLogger(BookingController.class);

	ManageBooking manageBooking = new ManageBooking();
	ModelAndView mv = new ModelAndView();

	public BookingController() {
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/display", method = RequestMethod.POST)
	public ModelAndView processBooking(HttpServletRequest request, HttpServletResponse response) {

		int noOfTickets = Integer.parseInt(request.getParameter("noOfTickets"));
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		List<Flight> flightList = manageBooking.listFlights(from, to, noOfTickets);
		if (flightList != null) {
			request.getSession().setAttribute("ticketsToBook", noOfTickets);
			mv.addObject("flightList", flightList);
			mv.setViewName("display");
		} else {
			mv.setViewName("flightnotfound");
		}
		return mv;
	}

	@RequestMapping(value = "/bookinginfo", method = RequestMethod.POST)
	public ModelAndView booking(HttpServletRequest request, HttpServletResponse response) {
		int mySelect = Integer.parseInt(request.getParameter("mySelect"));
		Flight flight = null;
		List<Flight> flightList = manageBooking.getFlight(mySelect);
		for (Flight flightobj : flightList) {
			flight = flightobj;
		}
		if (flight != null) {
			request.getSession().setAttribute("flightObj", flight);
			mv.setViewName("bookinginfo");
		} else {
			mv.setViewName("flightnotfound");
		}
		return mv;
	}

	@RequestMapping(value = "/confirmation", method = RequestMethod.POST)
	public ModelAndView confirmBooking(HttpServletRequest request, HttpServletResponse response) {
		List<Booking> passengerList = new ArrayList<Booking>();
		int passengers = (Integer) request.getSession().getAttribute("ticketsToBook");
		for (int i = 1; i <= passengers; i++) {
			Booking bookingDetails = new Booking();
			bookingDetails.setFirstName(request.getParameter("firstName" + i));
			bookingDetails.setLastName(request.getParameter("lastName" + i));
			bookingDetails.setMailId(request.getParameter("mailId" + i));
			bookingDetails.setContact(Long.parseLong((request.getParameter("phNumber" + i))));
			passengerList.add(bookingDetails);
		}
		for (Booking book : passengerList) {
			log.info(book.getFirstName());
		}
		request.getSession().setAttribute("passengerDetails", passengerList);
		mv.setViewName("confirmbooking");
		return mv;
	}

	@RequestMapping(value = "/paymentredirection", method = RequestMethod.GET)
	public String paymentRedirection() {
		return "payment";
	}

}
