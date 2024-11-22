##  Contact API

Цей API забезпечує CRUD-операції для роботи з контактами користувачів.

### Публічні методи

1. **GET /contacts**
    - **Опис:** Повертає список усіх контактів, що належать поточному автентифікованому користувачу.
    - **Вхідні параметри:**
    - **Відповідь:** Повертає об'єкт **Flux<ContactDto>**, що містить контакти користувача.
    - **Приклад запиту:**
      ```http
      GET /contacts HTTP/1.1
      Host: example.com
      Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
      ```
    - **Приклад відповіді:**
      ```json
      [
        {
          "id": 1,
          "fullName": "John Doe",
          "phones": ["+123456789"],
          "emails": ["john.doe@example.com"],
          "githubId": "johndoe"
        }
      ]
      ```
    - **Для розробників:** Використовуйте цей метод для отримання повного списку контактів. Переконайтесь, що користувач автентифікований перед викликом.
    - **Для клієнтів:** Використовуйте цей запит для отримання списку контактів. Переконайтесь, що клієнт правильно автентифікований і має доступ до необхідних токенів.

2. **POST /contacts**
    - **Опис:** Створює новий контакт для поточного користувача.
    - **Вхідні параметри:**
        - **@RequestBody ContactDto contact**: Дані нового контакту.
    - **Відповідь:** Повертає створений контакт у вигляді **Mono<ContactDto>**.
    - **Приклад запиту:**
      ```http
      POST /contacts HTTP/1.1
      Host: example.com
      Content-Type: application/json
      Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
 
      {
        "fullName": "Jane Doe",
        "phones": ["+987654321"],
        "emails": ["jane.doe@example.com"],
        "githubId": "janedoe"
      }
      ```
    - **Приклад відповіді:**
      ```json
      {
        "id": 2,
        "fullName": "Jane Doe",
        "phones": ["+987654321"],
        "emails": ["jane.doe@example.com"],
        "githubId": "janedoe"
      }
      ```
    - **Для розробників:** Перед створенням нового контакту переконайтесь, що всі обов'язкові поля заповнені. Використовуйте **sequenceGeneratorService** для генерації унікального ідентифікатора.
    - **Для клієнтів:** Під час створення контакту переконайтесь, що передаєте всі обов'язкові поля. Використовуйте дані аутентифікації, щоб забезпечити правильний зв'язок контакту з користувачем.

3. **PUT /contacts**
    - **Опис:** Оновлює контакт поточного користувача.
    - **Вхідні параметри:**
        - **@RequestBody ContactDto contact**: Дані контакту для оновлення.
    - **Відповідь:** Повертає оновлений контакт у вигляді **Mono<ContactDto>**.
    - **Приклад запиту:**
      ```http
      PUT /contacts HTTP/1.1
      Host: example.com
      Content-Type: application/json
      Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
 
      {
        "id": 2,
        "fullName": "Jane Doe Updated",
        "phones": ["+987654321"],
        "emails": ["jane.doe@example.com"],
        "githubId": "janedoe"
      }
      ```
    - **Приклад відповіді:**
      ```json
      {
        "id": 2,
        "fullName": "Jane Doe Updated",
        "phones": ["+987654321"],
        "emails": ["jane.doe@example.com"],
        "githubId": "janedoe"
      }
      ```
    - **Для розробників:** Перед оновленням контакту переконайтесь, що контакт належить поточному користувачу. Зверніть увагу на правильність переданих даних.
    - **Для клієнтів:** Використовуйте цей метод для оновлення існуючого контакту. Переконайтесь, що передаєте коректний ідентифікатор та нові дані для оновлення.

4. **GET /contacts/{id}**
    - **Опис:** Повертає контакт за вказаним **id**.
    - **Вхідні параметри:**
        - **@PathVariable Long id**: Ідентифікатор контакту.
    - **Відповідь:** Повертає об'єкт **Mono<ContactDto>** з інформацією про контакт.
    - **Приклад запиту:**
      ```http
      GET /contacts/2 HTTP/1.1
      Host: example.com
      Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
      ```
    - **Приклад відповіді:**
      ```json
      {
        "id": 2,
        "fullName": "Jane Doe",
        "phones": ["+987654321"],
        "emails": ["jane.doe@example.com"],
        "githubId": "janedoe"
      }
      ```
    - **Для розробників:** Перевірте, чи існує контакт із вказаним **id**. Якщо контакт не знайдено, поверніть відповідну помилку.
    - **Для клієнтів:** Використовуйте цей метод для отримання детальної інформації про контакт. Переконайтесь, що вказаний **id** є коректним.

5. **DELETE /contacts/{id}**
    - **Опис:** Видаляє контакт за вказаним **id**.
    - **Вхідні параметри:**
        - **@PathVariable Long id**: Ідентифікатор контакту.
    - **Відповідь:** Повертає об'єкт **Mono<Void>**, що вказує на успішне видалення.
    - **Приклад запиту:**
      ```http
      DELETE /contacts/2 HTTP/1.1
      Host: example.com
      Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
      ```
    - **Приклад відповіді:**
      ```
      (204 No Content)
      ```
    - **Для розробників:** Перед видаленням контакту перевірте, чи існує контакт із вказаним **id**. Переконайтесь, що контакт належить поточному користувачу.
    - **Для клієнтів:** Використовуйте цей метод для видалення контакту. Переконайтесь, що маєте права на видалення вказаного контакту.

6. **GET /contacts/search**
    - **Опис:** Шукає контакти за вказаним рядком запиту.
    - **Вхідні параметри:**
        - **@RequestParam String search**: Пошуковий запит.
    - **Відповідь:** Повертає список контактів, які відповідають пошуковому запиту, у вигляді **Flux<ContactDto>**.
    - **Приклад запиту:**
      ```http
      GET /contacts/search?search=Jane HTTP/1.1
      Host: example.com
      Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
      ```
    - **Приклад відповіді:**
      ```json
      [
        {
          "id": 2,
          "fullName": "Jane Doe",
          "phones": ["+987654321"],
          "emails": ["jane.doe@example.com"],
          "githubId": "janedoe"
        }
      ]
      ```
    - **Для розробників:** Використовуйте **ContactService** для виконання логіки пошуку. Забезпечте ефективну обробку великих обсягів даних.
    - **Для клієнтів:** Використовуйте цей метод для пошуку контактів за ім'ям, телефоном або іншими параметрами. Переконайтесь, що ваш запит коректний та не перевантажує систему.

### Приватні методи

1. **convertToContactDto(ContactEntity deviceEntity)**
    - **Опис:** Конвертує об'єкт **ContactEntity** у **ContactDto** за допомогою **ModelMapper**.
    - **Для розробників:** Використовуйте цей метод для зручної конвертації даних між рівнями сервісів та DTO.

2. **convertToContactEntity(ContactDto deviceEntity)**
    - **Опис:** Конвертує об'єкт **ContactDto** у **ContactEntity** за допомогою **ModelMapper**.
    - **Для розробників:** Забезпечте коректну конвертацію даних, особливо для обов'язкових полів.

*SequenceGeneratorService**: Для генерації унікальних ідентифікаторів контактів.

### Примітки
- Використовується реактивний підхід із використанням **Mono** та **Flux** для асинхронної обробки запитів.
- Авторизація користувача здійснюється через **@AuthenticationPrincipal**, який надає поточного автентифікованого користувача.
- Використовується базова автентифікація **httpBasic** та перенаправлення з HTTP на HTTPS для забезпечення безпеки.

### Рекомендації для клієнтів
- Використовуйте відповідні токени автентифікації для доступу до всіх методів API.
- Перед відправкою даних переконайтесь, що всі обов'язкові поля заповнені коректно.
- Використовуйте методи для створення, оновлення та видалення контактів з дотриманням правил авторизації.
- Для забезпечення коректності запитів, відстежуйте відповіді API та обробляйте можливі помилки (наприклад, відсутні контакти або помилки автентифікації).
- Оптимізуйте запити для пошуку, щоб уникнути перевантаження серверу та забезпечити швидке отримання результатів.


# Contact API Documentation

This API provides CRUD operations for managing user contacts. The controller uses **@RestController** and handles requests using the **@RequestMapping("/contacts")** annotation.

## Public Methods

### 1. GET /contacts
- **Description:** Returns a list of all contacts belonging to the current authenticated user.
- **Parameters:**
    - **@AuthenticationPrincipal Mono<UserDetails> currentUser**: Authenticated user details.
- **Response:** Returns a **Flux<ContactDto>** object containing user contacts.
- **Example Request:**
  ```http
  GET /contacts HTTP/1.1
  Host: example.com
  Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
  ```
- **Example Response:**
  ```json
  [
    {
      "id": 1,
      "fullName": "John Doe",
      "phones": ["+123456789"],
      "emails": ["john.doe@example.com"],
      "githubId": "johndoe"
    }
  ]
  ```

### 2. POST /contacts
- **Description:** Creates a new contact for the current user.
- **Parameters:**
    - **@AuthenticationPrincipal Mono<UserDetails> currentUser**: Authenticated user details.
    - **@RequestBody ContactDto contact**: New contact data.
- **Response:** Returns the created contact as a **Mono<ContactDto>**.
- **Example Request:**
  ```http
  POST /contacts HTTP/1.1
  Host: example.com
  Content-Type: application/json
  Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=

  {
    "fullName": "Jane Doe",
    "phones": ["+987654321"],
    "emails": ["jane.doe@example.com"],
    "githubId": "janedoe"
  }
  ```
- **Example Response:**
  ```json
  {
    "id": 2,
    "fullName": "Jane Doe",
    "phones": ["+987654321"],
    "emails": ["jane.doe@example.com"],
    "githubId": "janedoe"
  }
  ```

### 3. PUT /contacts
- **Description:** Updates the contact of the current user.
- **Parameters:**
    - **@AuthenticationPrincipal Mono<UserDetails> currentUser**: Authenticated user details.
    - **@RequestBody ContactDto contact**: Contact data for update.
- **Response:** Returns the updated contact as a **Mono<ContactDto>**.
- **Example Request:**
  ```http
  PUT /contacts HTTP/1.1
  Host: example.com
  Content-Type: application/json
  Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=

  {
    "id": 2,
    "fullName": "Jane Doe Updated",
    "phones": ["+987654321"],
    "emails": ["jane.doe@example.com"],
    "githubId": "janedoe"
  }
  ```
- **Example Response:**
  ```json
  {
    "id": 2,
    "fullName": "Jane Doe Updated",
    "phones": ["+987654321"],
    "emails": ["jane.doe@example.com"],
    "githubId": "janedoe"
  }
  ```

### 4. GET /contacts/{id}
- **Description:** Returns a contact by the specified **id**.
- **Parameters:**
    - **@PathVariable Long id**: Contact identifier.
- **Response:** Returns a **Mono<ContactDto>** object with contact information.
- **Example Request
