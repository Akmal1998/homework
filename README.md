# Техническое задание
Лабораторная работа по дисциплине "Разработка мобильных приложений"

## Основные требования к приложению
Приложение должно включать в себя:

1)	Launch Screen c логотипом МИРЭА
Во время загрузки приложения отображается логотип МИРЭА
![Рисунок 1. Launch Screen.](ImagesForReadme/1.png)

2)	Авторизация и выход в Github, а также отображение списка репозиториев
Авторизация в Github и отображение списка своих публичных и приватных репозиториев. Возможность выхода из аккаунта.
![Рисунок 2. Авторизация и список репозиториев в GitHub.](ImagesForReadme/2.png)

3)	Yandex карта с маршрутом от МГУПИ до МИРЭА
С помощью YandexMapKit приложение прокладывает маршрут от МГУПИ до МИРЭА.
![Рисунок 3. Маршрут в Яндекс Картах.](ImagesForReadme/3.png)

4)	Отображение списка контактов
Отображение всего списка контактов
![Рисунок 3. Список контактов.](ImagesForReadme/4.png)

5)	Отображение основной информации об устройстве
Отображение версии Android, брэнда-производителя смартфона, модели процессора, номер модели, IMEI.
![Рисунок 5. Информация об устройстве.](ImagesForReadme/5.png)

6)	Акселерометр + камера
Отображается на одном экране изменяемые значения акселерометра и возможность открытия камеры и сохранения снимков
![Рисунок 6. Камера + датчики.](ImagesForReadme/6.png)

7)	Место для размещения рекламы спонсора
Реклама спонсора размещается на главном экране. В качестве примера была выбрана один из самых узнаваемых креативов Yota. 
![Рисунок 7. Спонсорский экран.](ImagesForReadme/7.png)

## Описание приложения
При создании приложения использовался шаблон проектирования MVP.
![Рисунок 8. Визуализация принципа работы MVP.](ImagesForReadme/8.png)

MVP( Model - View - Presenter) - шаблон проектирования пользовательского интерфейса, который реализует отделение бизнес логики (Model) приложения от уровня отображения. 
Model хранит в себе всю бизнес-логику, при необходимости получает данные из хранилища. View реализует отображение данных из Model, обращается к Presenter за обновлениями. Presenter, в свою очередь, реализует взаимодействие между Model и View.
![Рисунок 9. Схема работы MVP.](ImagesForReadme/10.png)

## Использование API
При разработке приложения использовались Github API и Yandex MapKit.

### Github API
При реализации авторизации в Github используется OAuth2 token.

Инициализация 2 API клиентов.
![Рисунок 10. Инициализация 2 API клиентов.](ImagesForReadme/11.png)

Обьявление метода для авторизации.
![Рисунок 11. Обьявление метода для авторизации.](ImagesForReadme/12.png)

Обьявление метода для работы с репозиториями.
![Рисунок 12. Обьявление метода для работы с репозиториями.](ImagesForReadme/13.png)

### Yandex MapKit

Подключение библиотек Yandex карт.
![Рисунок 13. Подключение библиотек Yandex карт.](ImagesForReadme/14.png)

Инициализация MapKitFactory.
![Рисунок 14. Инициализация MapKitFactory.](ImagesForReadme/15.png)


## Графический макет приложения

![Рисунок 15. Графический макет приложения.](ImagesForReadme/10.png)

