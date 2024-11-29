# Contact Manager

[![JavaRush University](https://img.shields.io/badge/java-rush-orange?style=for-the-badge&color=orange&cacheSeconds=3600)](https://javarush.com/ua/university/)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/e34fd4467d234fcaab31765bc99e14a6)](https://app.codacy.com/gh/JavaRush-GNEW/contact-manager/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

[**GUIDE**: How to Contribute to the Contact Manager project](https://github.com/JavaRush-GNEW/contact-manager/wiki/How-to-Contribute-%E2%80%90-Guide)

## About the project

> Contact Manager is a CLI application for managing contacts in your phone book. It allows you to add, search, edit,
> delete, and view contacts using simple commands.

## Commands

### General information

```
-h, --help        Show this help message and exit.
-V, --version     Print version information and exit.
```

### Available commands

```
-u, --user          Select active user.
-a, --add           Add new contact.
-s, --search        Find a contact by name.
-e, --edit          Edit existing contact by ID.
-d, --delete        Delete contact by ID.
-ls, --list         Display all contacts.
-gr, --group        Create,delete group. Add, remove contact from group.
```

#### Usage

```
phonebook [-hV] [COMMAND]
```

## Using commands

### User

---

#### Usage

```
phonebook --user/-u [-hV] [<name>]
      [<name>]    вибір користувача
  -h, --help      Show this help message and exit.
```

#### Example

```shell
java -jar phonebook.jar -u alex
```

```
Ви обрали користувача: alex
```

### List

---

#### Usage

```
phonebook --list/-ls [-hV]
  -h, --help      Show this help message and exit.
```

#### Example

```shell
java -jar phonebook.jar -ls
```

```
| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        |
| 5072       | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              |
| 335        | Chris Pratt          | FoxPartt        | +380673333333, +380674444444   | chris.p@m.ua, chris.p@gmail.com              |
| 3790       | Scarlett Johansson   |                 | +380675555555, +380676666666   | Scarlett.j@m.ua, Scarlett.j@gmail.com        |
| 2222       | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com            |
```

### Add

---

#### Usage

```
phonebook --add/-a [-hV] -n=<name> -e[=<emails> [<emails> [<emails>]]] [-e
                     [=<emails> [<emails> [<emails>]]]]... -p[=<phones>
                     [<phones> [<phones>]]] [-p[=<phones> [<phones>
                     [<phones>]]]]... -g=<gitAcaunt>

  -e, --email[=<emails> [<emails> [<emails>]]]      E-mail
  -h, --help                                        Show this help message and exit
  -n, --name=<name>                                 Contact name
  -p, --phone[=<phones> [<phones> [<phones>]]]      Phone number
  -g, --git=<gitAccount>                            GitHub account
```

#### Example

```shell
java -jar phonebook.jar -a -n "Jhon Snow" -p 123456789 22233333 -e 123@qq.ua 234@qq.ua -g JhonOnGit 
```

### Search

---

#### Usage

```
phonebook --search/-s [-hV] [<name>]
      [<name>]    Ім'я для пошуку
  -h, --help      Show this help message and exit.
```

#### Example

```shell
java -jar phonebook.jar -s Chris
```

```
| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 6728       | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              |
| 9578       | Chris Pratt          | FoxPartt        | +380673333333, +380674444444   | chris.p@m.ua, chris.p@gmail.com              |
```

### Edit

---

#### Usage

```
phonebook --edit/-e [-hV] [<id>]
      [<id>]    ID контакту для редагуваняя
  -h, --help    Show this help message and exit.
```

#### Example

```shell
java -jar phonebook.jar -e 2222
```

```
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

### Delete

---

#### Usage

```
phonebook --delete/-d [-hV] [<id>]
      [<id>]    ID контакту для видалення
  -h, --help    Show this help message and exit.
```

#### Example

```shell
java -jar phonebook.jar -d 12341 12342
```

```
Контакт видалено:
| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 12340      | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              | 
| 12343      | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com 
```

### Group

---

#### Usage

```
phonebook --group/-gr [-hV] 
  -c, --create=<group>                           Create new grop
  -d, --delete=<group>                           Delete group     
  -a, --add=<group> [=<id> [<id> [<id>]]]        Add contacts to group  
  -rm --remove=<group> [=<id> [<id> [<id>]]]
  -h, --help                                    Show this help message and exit.
```

#### Example

```shell
java -jar phonebook.jar -gr -c "Marvel"
java -jar phonebook.jar -gr -a "Marvel" 12340 12343 

```

```
Контакт видалено:
| ID         | FULL NAME            | GITHUB ID       | PHONE                          | EMAIL                                        | 
| 12340      | Chris Hemsworth      | Hemsworth-C     | +380671111111, +380672222222   | chris.h@m.ua, chris.h@gmail.com              | 
| 12343      | Jeremy Renner        |                 | +380677777777, +380678888888   | Jeremy.r@m.ua, Jeremy.r@gmail.com 
```
