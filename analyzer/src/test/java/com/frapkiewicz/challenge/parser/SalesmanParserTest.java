package com.frapkiewicz.challenge.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.frapkiewicz.challenge.model.Salesman;

class SalesmanParserTest {

	SalesmanParser parser;
	
	@BeforeEach
	void setUp() throws Exception {
		parser = new SalesmanParser();
	}

	@AfterEach
	void tearDown() throws Exception {
		parser = null;
	}

	@Test
	void testWithValidValue() {
		Salesman salesman = new Salesman();
		salesman.setId("001");
		salesman.setCpf("1234567891234");
		salesman.setName("Pedro");
		salesman.setSalary(Double.valueOf("50000"));
		
		assertEquals(salesman, parser.tryParse("001ç1234567891234çPedroç50000").get());
	}
	
	@Test
	void testWithEmptyValue() {
		assertFalse(parser.tryParse("").isPresent());
	}
	
	@Test
	void testWithInvalidLimiter() {
		assertFalse(parser.tryParse("001ç1234567891234-Pedroç50000").isPresent());
		assertFalse(parser.tryParse("001-1234567891234-Pedro-50000").isPresent());
		assertFalse(parser.tryParse("001;1234567891234;Pedro;50000").isPresent());
	}
	
	@Test
	void testWithInvalidId() {
		assertFalse(parser.tryParse("1ç1234567891234çPedroç50000").isPresent());
		assertFalse(parser.tryParse("01ç1234567891234çPedroç50000").isPresent());
		assertFalse(parser.tryParse("002ç1234567891234çPedroç50000").isPresent());
	}
	
	@Test
	void testWithInvalidCpf() {
		assertFalse(parser.tryParse("001ççPedroç50000").isPresent());
		assertFalse(parser.tryParse("001ç123456789123AçPedroç50000").isPresent());
		assertFalse(parser.tryParse("001ç123456789123çPedroç50000").isPresent());
	}
	
	@Test
	void testWithInvalidName() {
		assertFalse(parser.tryParse("001ç123456789123çç50000").isPresent());
		assertFalse(parser.tryParse("001ç123456789123ç1234ç50000").isPresent());
		assertFalse(parser.tryParse("001ç123456789123çPedro Andreç50000").isPresent());
	}
	
	@Test
	void testWithInvalidSalary() {
		assertFalse(parser.tryParse("001ç1234567891234çPedroç").isPresent());
		assertFalse(parser.tryParse("001ç1234567891234çPedroç120,23").isPresent());
		assertFalse(parser.tryParse("001ç1234567891234çPedroçSalary").isPresent());
	}

}
