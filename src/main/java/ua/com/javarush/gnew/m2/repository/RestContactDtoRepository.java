package ua.com.javarush.gnew.m2.repository;

import ua.com.javarush.gnew.m2.dto.ContactDto;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class RestContactDtoRepository implements ContactDtoRepository{
    String url = "http://localhost:8080/contacts";
    Client client = ClientBuilder.newClient();

    private final String user;

    public RestContactDtoRepository(String user) {
        this.user = user;
    }

    @Override
    public List<ContactDto> findAll() {
        WebTarget target = client.target(url);

        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString(( user + ":" + "12345").getBytes());

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", basicAuth);
        List<ContactDto> devices = invocationBuilder.get(new GenericType<List<ContactDto>>() {});

        return devices;
    }

    @Override
    public Optional<ContactDto> findById(long id) throws IOException {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) throws IOException {
        WebTarget target = client.target(url).path(String.valueOf(id));

        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString(( user + ":" + "12345").getBytes());

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", basicAuth);

        ContactDto contact = invocationBuilder.delete(ContactDto.class);

    }

    @Override
    public void saveAll(List<ContactDto> contacts) throws IOException {

    }

    @Override
    public void save(ContactDto contactDto) throws IOException {

        WebTarget target = client.target(url);

        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString(( user + ":" + "12345").getBytes());

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", basicAuth);
       ContactDto contact = invocationBuilder.post(Entity.entity(contactDto,MediaType.APPLICATION_JSON),ContactDto.class);

       contactDto.setId(contact.getId());
       contactDto.setFullName(contact.getFullName());
       contactDto.setPhones(contact.getPhones());
       contactDto.setEmails(contact.getEmails());
       contactDto.setGithubId(contact.getGithubId());

    }

    @Override
    public List<ContactDto> findByKeyword(String keyword) throws IOException {
        WebTarget target = client.target(url+"/search")
                .queryParam("search",keyword);

        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString(( user + ":" + "12345").getBytes());

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", basicAuth);
        List<ContactDto> devices = invocationBuilder.get(new GenericType<List<ContactDto>>() {});
        return devices;
    }
}
