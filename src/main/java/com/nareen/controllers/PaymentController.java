package com.nareen.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nareen.bookingoperations.ManageBooking;
import com.nareen.entity.Booking;
import com.nareen.entity.Flight;
import com.nareen.services.ExternalServiceCallable;


@Controller
public class PaymentController {
	private static final Logger log = LogManager.getLogger(PaymentController.class);
	
	ManageBooking manageBooking = new ManageBooking();
	ModelAndView mv = new ModelAndView();
	ExecutorService executorService = Executors.newFixedThreadPool(3);
	public PaymentController() {
	}


	@RequestMapping(value = "/paymentexecutor", method = RequestMethod.POST)
	public ModelAndView validation(HttpServletRequest request, HttpServletResponse response) {
		String userName=request.getParameter("userName");
		String cardDetails= request.getParameter("cardDetails");
		String cardCvv =request.getParameter("cardCvv");
		String cardExp =request.getParameter("cardExp");
		Flight flight=(Flight) request.getSession().getAttribute("flightObj");
		int bookedTickets = (Integer) request.getSession().getAttribute("ticketsToBook");
		//Calling External service //
		long totalPrice=flight.getTicketPrice()*bookedTickets;
		ExternalServiceCallable externalService=new ExternalServiceCallable(userName, cardDetails, cardCvv, cardExp,totalPrice);
		Future<String> future = executorService.submit(externalService);
		request.getSession().setAttribute("future", future);
		request.getSession().setAttribute("flag", false);
		//mv.addObject("future", future);
		mv.setViewName("processing"); 
		return mv;
	}
	
	
	@RequestMapping(value = "/processconfirmation", method = RequestMethod.GET)
	public ModelAndView processingConfirmation(HttpServletRequest request,HttpServletResponse response) throws IOException, InterruptedException, ExecutionException {
		
		Future<String> future=(Future<String>)request.getSession().getAttribute("future");
		Boolean flag=(Boolean)request.getSession().getAttribute("flag");
		if(!flag&&future.isDone()) {
			response.sendRedirect("paymentconfirmation.do");
		}else if(flag) {
			response.sendRedirect("bookingconfirmation.do");
		}else {
			mv.setViewName("processing");
		}
		return mv;
		
	}
	
	@RequestMapping(value = "/paymentconfirmation", method = RequestMethod.GET)
	public ModelAndView paymentConfirmation(HttpServletRequest request,HttpServletResponse response) throws InterruptedException, ExecutionException {
		Flight flight=(Flight) request.getSession().getAttribute("flightObj");
		int bookedTickets = (Integer) request.getSession().getAttribute("ticketsToBook");
		Future<String> future=(Future<String>)request.getSession().getAttribute("future");
		String result=future.get();
		if (!result.equals("fail")) {
			List<Booking> bookingDetails = (ArrayList<Booking>)request.getSession().getAttribute("passengerDetails");
			String confirmation=manageBooking.atomicTransaction(bookingDetails, flight.getId(),
												(flight.getAvailableTickets() - bookedTickets));
				mv.addObject("confirmation", confirmation);
				request.getSession().setAttribute("flag", true);
				mv.setViewName("processing");
			}else {
				log.info("card details are wrong");
				mv.setViewName("payment");
			}
		return mv;
}

	@RequestMapping(value = "/bookingconfirmation", method = RequestMethod.GET)
	public ModelAndView bookingConfirmation(HttpServletRequest request,SessionStatus status) {
		
		String confirmation=(String) request.getAttribute("confirmation");
		List<Booking> bookingList=manageBooking.iternaryList(confirmation);
		status.setComplete();
		mv.addObject("bookingList", bookingList);
		mv.setViewName("bookingconfirmation");
		status.setComplete();
		return mv;	
	}
}
		
	
	

