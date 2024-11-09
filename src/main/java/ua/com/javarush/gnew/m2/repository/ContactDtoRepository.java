package ua.com.javarush.gnew.m2.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public interface ContactDtoRepository {

   List<ContactDto> findAll() throws IOException ;


   Optional<ContactDto> findById(long id) throws IOException ;

   void deleteById(long id) throws IOException;

   void saveAll(List<ContactDto> contacts) throws IOException;

   void save(ContactDto contactDto) throws IOException;

   List<ContactDto> findByKeyword(String keyword) throws IOException ;

}
