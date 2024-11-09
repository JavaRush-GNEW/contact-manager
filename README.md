# contact-manager
[![JavaRush University](https://img.shields.io/badge/java-rush-orange?style=for-the-badge&color=orange&cacheSeconds=3600)](https://javarush.com/ua/university/)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/e34fd4467d234fcaab31765bc99e14a6)](https://app.codacy.com/gh/JavaRush-GNEW/contact-manager/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)


## Usage

**Команди** (add, search, edit, delete, list)

```
Usage: phonebook [-hV] [COMMAND]
CLI для управління контактами в телефонній книзі
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
   --user/-u    Обирає активного користувача
   --add/-a     Додає новий контакт до телефонної книги
   --search/-s  Шукає контакт за ім'ям
   --edit/-e    Редагує існуючий контакт за ім'ям
   --delete/-d  Видаляє контакт за ім'ям
   --list/-ls   Виводить усі контакти
```

* **user**

```
Usage: phonebook --user/-u [-hV] [<name>]
Шукає контакт за ім'ям
      [<name>]    вибір користувача
  -h, --help      Show this help message and exit.
```


#### Приклад:

```
>java -jar phonebook.jar -u alex

Ви обрали користувача: alex

```

* **add**

```
Usage: phonebook --add/-a [-hV] -n=<name> -e[=<emails> [<emails> [<emails>]]] [-e
                     [=<emails> [<emails> [<emails>]]]]... -p[=<phones>
                     [<phones> [<phones>]]] [-p[=<phones> [<phones>
                     [<phones>]]]]... -g=<gitAcaunt>

Додає новий контакт до телефонної книги
  -e, --email[=<emails> [<emails> [<emails>]]]           Електронна пошта
  -h, --help                                           Show this help message and exit.
  -n, --name=<name>                                      Ім'я контакту
  -p, --phone[=<phones> [<phones> [<phones>]]]            Номер телефону
  -g, --git =<gitAcaunt>                                  GitHub акаунт.

```


#### Приклад:

```
>java -jar phonebook.jar -a -n "Jhon Snow" -p 123456789 22233333 -e 123@qq.ua 234@qq.ua -g JhonOnGit 
```


* **search**

```
Usage: phonebook --search/-s [-hV] [<name>]
Шукає контакт за ім'ям
      [<name>]    Ім'я для пошуку
  -h, --help      Show this help message and exit.
```

#### Приклад:

```
>java -jar phonebook.jar -s Chris

| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 6728       | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              |
| 9578       | Chris Pratt          | FoxPartt        | +380673333333, +380674444444   | chris.p@m.ua, chris.p@gmail.com              |

```

* **edit**

```
Usage: phonebook --edit/-e [-hV] [<id>]
Редагує контакт за ID 
      [<id>]    ID контакту для редагуваняя
  -h, --help    Show this help message and exit.
```

#### Приклад:

```

> java -jar phonebook.jar -ls
 
| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 5072       | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              |
| 335        | Chris Pratt          | FoxPartt        | +380673333333, +380674444444   | chris.p@m.ua, chris.p@gmail.com              |
| 3790       | Scarlett Johansson   |                 | +380675555555, +380676666666   | Scarlett.j@m.ua, Scarlett.j@gmail.com        |
| 2222       | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com            |

> java -jar phonebook.jar -e 2222

| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 2222       | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com            |

Телефонна книга - виберiть команду:
1. Редагувати iм'я
2. Редагувати телефони
3. Редагувати email
4. Редагувати GitHub ID
5. Вийти
Ваш вибiр: > 1

1. Введiть нове iм'я: > Jeremy Smit

Контакт оновлено:
| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 2222       | Jeremy Smit          |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com            |

```

* **delete**

```
  Usage: phonebook --delete/-d [-hV] [<id>]
Видаляє контакт за ID
      [<id>]    ID контакту для видалення
  -h, --help    Show this help message and exit.
```

#### Приклад:

```
>java -jar phonebook.jar -ls

| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 12340      | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              | 
| 12341      | Chris Pratt          | FoxPartt        | +380673333333, +380674444444   | chris.p@m.ua, chris.p@gmail.com              | 
| 12342      | Scarlett Johansson   |                 | +380675555555, +380676666666   | Scarlett.j@m.ua, Scarlett.j@gmail.com        | 
| 12343      | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com    

>java -jar phonebook.jar -d 12341 12342

Контакт видалено: 
| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 12340      | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              | 
| 12343      | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com 
```

* **list**

```
  Usage: phonebook --list/-ls [-hV]
Виводить усі контакти
  -h, --help      Show this help message and exit.
```


#### Приклад:

```
> java -jar phonebook.jar -ls

| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        |
| 5072       | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              |
| 335        | Chris Pratt          | FoxPartt        | +380673333333, +380674444444   | chris.p@m.ua, chris.p@gmail.com              |
| 3790       | Scarlett Johansson   |                 | +380675555555, +380676666666   | Scarlett.j@m.ua, Scarlett.j@gmail.com        |
| 2222       | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com            |
```
