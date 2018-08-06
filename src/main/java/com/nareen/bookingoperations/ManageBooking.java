package com.nareen.bookingoperations;

import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nareen.entity.Booking;
import com.nareen.entity.Flight;

public class ManageBooking {
	private static final Logger log = LogManager.getLogger(ManageBooking.class);
	private static SessionFactory factory;
	{
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			log.error("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/* Methods to establish a connection with Booking table */
	public List<Booking> BookedList() {
		Session session = factory.openSession();
		Transaction tx = null;
		//Hello
		List<Booking> bookingList = null;
		try {
			tx = session.beginTransaction();
			bookingList = session.createQuery("FROM Booking").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookingList;
	}

	public List<Booking> iternaryList(String confirmation) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Booking> bookingList = null;
		try {
			tx = session.beginTransaction();
			bookingList = session.createQuery("FROM Booking b where b.confirmation='" + confirmation + "'").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookingList;
	}

	public Integer addbooking(Booking booking) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer bookingId = null;
		try {
			tx = session.beginTransaction();
			bookingId = (Integer) session.save(booking);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookingId;
	}

	public void deleteBooking(Integer BookingId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Booking bookingDetails = (Booking) session.get(Booking.class, BookingId);
			session.delete(bookingDetails);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Methods to establish a connection with Flights table */
	@SuppressWarnings("unchecked")
	public List<Flight> listFlights(String from, String to, int noOfTickets) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Flight> flightList = null;
		try {
			tx = session.beginTransaction();
			flightList = session.createQuery("FROM Flight f Where f.fromLoc='" + from + "' and f.toLoc='" + to
					+ "' and f.availableTickets>=" + noOfTickets).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flightList;
	}

	public List<Flight> getFlight(int flightID) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Flight> flightDetails = null;
		try {
			tx = session.beginTransaction();
			flightDetails = session.createQuery("FROM Flight f Where f.id='" + flightID + "'").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flightDetails;
	}

	public String updateSeats(int flightID, int noOfSeats) {
		Session session = factory.openSession();
		Transaction tx = null;
		String confirmation = null;
		try {
			tx = session.beginTransaction();
			Flight flight = (Flight) session.get(Flight.class, flightID);
			flight.setAvailableTickets(noOfSeats);
			session.update(flight);
			tx.commit();
			confirmation = getConformation();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return confirmation;
	}

	public List<Flight> listFlightDetails() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Flight> flightList = null;
		try {
			tx = session.beginTransaction();
			flightList = session.createQuery("FROM Flight").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flightList;
	}

	public String atomicTransaction(List<Booking> passengerList, int flightID, int noOfSeats) {

		Session session = factory.openSession();
		Transaction tx = null;
		String confirmation = null;
		try {
			tx = session.beginTransaction();
			Flight flight = (Flight) session.get(Flight.class, flightID);
			flight.setAvailableTickets(noOfSeats);
			session.update(flight);
			confirmation = getConformation();
			for (Booking passenger : passengerList) {
				passenger.setConfirmation(confirmation);
				passenger.setFlightNumber(flight.getFlightNumber());
				session.save(passenger);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return confirmation;

	}

	public String getConformation() {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars[rnd.nextInt(chars.length)]);
		}
		return sb.toString();
	}
}
