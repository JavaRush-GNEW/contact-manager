package ua.com.javarush.gnew.m2.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileGroupContactsRepository implements GroupContactsRepository {
  private final String user;
  ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  public FileGroupContactsRepository(String user) {
    this.user = user;
  }

  @Override
  public String create(String group) {
    Map<String, Set<Long>> savedGroup = readFromFile();
    savedGroup.putIfAbsent(group, new HashSet<>());
    writeToFile(savedGroup);
    return group;
  }

  @Override
  public String delete(String group) {
    Map<String, Set<Long>> savedGroup = readFromFile();
    savedGroup.remove(group);
    writeToFile(savedGroup);
    return group;
  }

  @Override
  public boolean addContact(String group, long id) {
    Map<String, Set<Long>> savedGroup = readFromFile();
    if (savedGroup.containsKey(group)) {
      savedGroup.get(group).add(id);
      writeToFile(savedGroup);
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteContact(String group, long id) {
    Map<String, Set<Long>> savedGroup = readFromFile();
    if (savedGroup.containsKey(group)) {
      savedGroup.get(group).remove(id);
      writeToFile(savedGroup);
      return true;
    }
    return false;
  }

  @Override
  public List<String> getGroups() {
    return readFromFile().keySet().stream().collect(Collectors.toList());
  }

  @Override
  public List<Long> getContactsId(String group) {
    return readFromFile().getOrDefault(group, Collections.emptySet()).stream()
        .collect(Collectors.toList());
  }

  private Map<String, Set<Long>> readFromFile() {
    try {
      return objectMapper.readValue(
          new File(user + "groups.gr"), new TypeReference<Map<String, Set<Long>>>() {});
    } catch (IOException e) {
      return new HashMap<>();
    }
  }

  private void writeToFile(Map<String, Set<Long>> groups) {
    try {
      objectMapper.writeValue(new File(user + "groups.gr"), groups);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
