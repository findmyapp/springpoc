package com.accenture.findmyapp.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accenture.findmyapp.entity.Person;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int arg1) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("ID"));
		person.setName(rs.getString("name"));
		person.setHoyde(rs.getInt("height"));
		
		return person;
	}

}
