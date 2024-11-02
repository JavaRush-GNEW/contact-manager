package ua.com.javarush.gnew.m2.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDemoRepository {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        public void save(List<User> strings) throws IOException {
            objectMapper.writeValue(new File("demo.st"),strings);
        }
        public List<User> load() throws IOException {
            return objectMapper.readValue(new File("demo.st"),objectMapper.getTypeFactory().constructCollectionType(List.class,User.class));
        }

    public static void main(String[] args) throws IOException {
        JsonDemoRepository jsonDemoRepository=new JsonDemoRepository();

        List<User> savList=new ArrayList<>(){{
            add(new User(123,"Alex","USER"));
            add(new User(124,"Oleg","USER"));
            add(new User(125,"Sergiy","ADMIN"));
        }};

        jsonDemoRepository.save(savList);

        List<User> lodList = jsonDemoRepository.load();
        lodList.forEach(System.out::println);

    }

}
