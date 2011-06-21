package com.accenture.findmyapp.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.accenture.findmyapp.entity.Person;
import com.accenture.findmyapp.persistence.PersonRowMapper;

@Repository
public class HomeDataHandler {

	@Autowired
	private DataSource ds;

	public Person getPerson(String name) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		Person person = jdbcTemplate.queryForObject(
				"SELECT ID, name, height FROM person WHERE name=?",
				new PersonRowMapper(), name);
		return person;
	}

	public int insertPerson(String name) {
		JdbcTemplate jt = new JdbcTemplate(ds);
		int status = jt.update("INSERT into person(name) values(?)", name);
		return status;
	}

	public int insertPerson(String name, int height) {
		JdbcTemplate jt = new JdbcTemplate(ds);
		int status = jt.update("INSERT into person(name, height) values(?,?)",
				new Object[] { name, height });
		return status;
	}

}