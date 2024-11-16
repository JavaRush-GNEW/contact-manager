package ua.com.javarush.gnew.m2.repository;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public class RestContactDtoRepository implements ContactDtoRepository {
  //    String url = "http://localhost:8080/contacts";
  String url = "https://194.28.85.113:8585/contacts";

  Client client;

  private final String user;

  String basicAuth;

  public RestContactDtoRepository(String user) {
    this.user = user;
    basicAuth = "Basic " + Base64.getEncoder().encodeToString((user + ":" + "12345").getBytes());
    try {
      sendSettingsInit();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (KeyManagementException e) {
      throw new RuntimeException(e);
    }
  }

  private void sendSettingsInit() throws NoSuchAlgorithmException, KeyManagementException {
    SSLContext sslContext = SSLContext.getInstance("TLS");
    TrustManager[] trustAllCerts =
        new TrustManager[] {
          new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
              return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {}

            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
          }
        };
    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

    client =
        ClientBuilder.newBuilder()
            .sslContext(sslContext)
            .register(JacksonFeature.class) // Реєстрація Jackson для JSON
            .hostnameVerifier((hostname, session) -> true) // Ігнорування перевірки хоста
            .build();
  }

  @Override
  public List<ContactDto> findAll() {
    WebTarget target = client.target(url);

    Invocation.Builder invocationBuilder =
        target.request(MediaType.APPLICATION_JSON).header("Authorization", basicAuth);
    List<ContactDto> devices = invocationBuilder.get(new GenericType<List<ContactDto>>() {});

    return devices;
  }

  @Override
  public Optional<ContactDto> findById(long id) throws IOException {
    WebTarget target = client.target(url).path(String.valueOf(id));

    Invocation.Builder invocationBuilder =
        target.request(MediaType.APPLICATION_JSON).header("Authorization", basicAuth);

    ContactDto contact = invocationBuilder.get(ContactDto.class);

    return Optional.ofNullable(contact);
  }

  @Override
  public void deleteById(long id) throws IOException {
    WebTarget target = client.target(url).path(String.valueOf(id));

    Invocation.Builder invocationBuilder =
        target.request(MediaType.APPLICATION_JSON).header("Authorization", basicAuth);

    ContactDto contact = invocationBuilder.delete(ContactDto.class);
  }

  @Override
  public void saveAll(List<ContactDto> contacts) throws IOException {}

  @Override
  public void save(ContactDto contactDto) throws IOException {

    WebTarget target = client.target(url);

    Invocation.Builder invocationBuilder =
        target.request(MediaType.APPLICATION_JSON).header("Authorization", basicAuth);

    ContactDto contact =
        (contactDto.getId() == 0)
            ? invocationBuilder.post(
                Entity.entity(contactDto, MediaType.APPLICATION_JSON), ContactDto.class)
            : invocationBuilder.put(
                Entity.entity(contactDto, MediaType.APPLICATION_JSON), ContactDto.class);

    contactDto.setId(contact.getId());
    contactDto.setFullName(contact.getFullName());
    contactDto.setPhones(contact.getPhones());
    contactDto.setEmails(contact.getEmails());
    contactDto.setGithubId(contact.getGithubId());
  }

  @Override
  public List<ContactDto> findByKeyword(String keyword) throws IOException {
    WebTarget target = client.target(url + "/search").queryParam("search", keyword);

    Invocation.Builder invocationBuilder =
        target.request(MediaType.APPLICATION_JSON).header("Authorization", basicAuth);
    List<ContactDto> devices = invocationBuilder.get(new GenericType<List<ContactDto>>() {});
    return devices;
  }
}
