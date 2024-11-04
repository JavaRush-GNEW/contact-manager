package ua.com.javarush.gnew.m2.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.dto.ContactDtoRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonContactDtoRepository implements ContactDtoRepository {

   ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    @Override
    public ContactDto load() throws IOException {
        return objectMapper.readValue(new File("demo.st"),ContactDto.class);
    }
    @Override
    public ContactDto findById(long id) throws IOException {
         ContactDto contact = load();
         return (contact.getId() == id) ? contact : null;
    }
    @Override
    public void save(ContactDto contact) throws IOException {
        objectMapper.writeValue(new File("demo.st"),contact);
    }

    public static void main(String[] args) {
        JsonContactDtoRepository jsonContactDtoRepository=new JsonContactDtoRepository();


        ContactDto contact = ContactDto.builder()
                .id(1L)
                .fullName("Иван Иванов")
                .phones(List.of("+123456789"))
                .emails(List.of("ivan@example.com"))
                .build();


        try {
            jsonContactDtoRepository.save(contact);
            System.out.println("Contact has been saved");

            ContactDto lodList = jsonContactDtoRepository.load();
            if(lodList != null){
                System.out.println("saved contact" + lodList);
            }
            ContactDto foundContact = jsonContactDtoRepository.findById(1L);
            System.out.println("Found contact: " + foundContact);

        }catch (IOException e) {
            System.err.println("Error while working with file: " + e.getMessage());
        }
    }

}
