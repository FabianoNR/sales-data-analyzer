package com.frapkiewicz.challenge.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.frapkiewicz.challenge.model.Customer;

class CustomerParserTest {

	private CustomerParser parser;
	
	@BeforeEach
	void setUp() throws Exception {
		parser = new CustomerParser();
	}

	@AfterEach
	void tearDown() throws Exception {
		parser = null;
	}

	@Test
	void testWithValidValue() {
		Customer customer = new Customer();
		customer.setId("002");
		customer.setCnpj("2345675434544345");
		customer.setName("Jose da Silva");
		customer.setBusinessArea("Rural");
		
		assertEquals(customer, parser.tryParse("002ç2345675434544345çJose da SilvaçRural").get());
	}
	
	@Test
	void testWithEmptyValue() {
		assertFalse(parser.tryParse("").isPresent());
	}
	
	@Test
	void testWithInvalidLimiter() {
		assertFalse(parser.tryParse("002-2345675434544345çJose da Silva-Rural").isPresent());
		assertFalse(parser.tryParse("002!2345675434544345!Jose da Silva!Rural").isPresent());
		assertFalse(parser.tryParse("002*2345675434544345çJose da SilvaçRural").isPresent());
	}
	
	@Test
	void testWithInvalidId() {
		assertFalse(parser.tryParse("2ç2345675434544345çJose da SilvaçRural").isPresent());
		assertFalse(parser.tryParse("02ç2345675434544345çJose da SilvaçRural").isPresent());
		assertFalse(parser.tryParse("003ç2345675434544345çJose da SilvaçRural").isPresent());
	}
	
	@Test
	void testWithInvalidCnpj() {
		assertFalse(parser.tryParse("002ççJose da SilvaçRural").isPresent());
		assertFalse(parser.tryParse("002ç234567543454434AçJose da SilvaçRural").isPresent());
		assertFalse(parser.tryParse("002ç234567543454434çJose da SilvaçRural").isPresent());
	}

	@Test
	void testWithInvalidBusinessArea() {
		assertFalse(parser.tryParse("002ç2345675434544345çJose da Silvaç").isPresent());
		assertFalse(parser.tryParse("002ç2345675434544345çJose da Silvaç123").isPresent());
		assertFalse(parser.tryParse("002ç2345675434544345çJose da SilvaçArea Rural").isPresent());
	}
}
