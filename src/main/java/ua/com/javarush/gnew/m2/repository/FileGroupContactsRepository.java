package ua.com.javarush.gnew.m2.repository;

import java.util.List;

public class FileGroupContactsRepository implements GroupContactsRepository {

  private final String user;

  public FileGroupContactsRepository(String user) {
    this.user = user;
  }

  @Override
  public String create(String group) {
    return null;
  }

  @Override
  public String delete(String group) {
    return null;
  }

  @Override
  public String addContact(String group, long id) {
    return null;
  }

  @Override
  public String deleteContact(String group, long id) {
    return null;
  }

  @Override
  public List<String> getGroups() {
    return null;
  }

  @Override
  public List<String> getContactsId(String group) {
    return null;
  }
}
