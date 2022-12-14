# Книга рецептов

## Описание проекта
Целью проекта является десктопное приложение, которое подразумевает возможность хранения рецептов, добавления их пользователем и просмотр. 
Все рецепты содержат название, описание и ингредиенты. При добавлении нового рецепта, все данные сохраняются локально в формате JSON и при новом запуске программы оттуда восстанавливаются.

В главном окне пользователь может просматривать список названий всех рецептов. Если выбрать одно из них и нажать на кпопку "Посмотреть рецепт", то появится новое окно со всей соответсвующей информаций. При нажатии на конопку "Добавить рецепт", открывается новое окно, с возможностью сохранения добавленной информации о рецепте.

## Технологии
1. Java AWT
2. Jackson
3. Jupiter Test API

## Разделение обязанностей
Помимо совместного обсуждения идеи и структуры проекта, некоторые оязанности были соответсвенно разделены:

#### Кристина:

1. Добавление основных структур проекта
2. Реализация сериализации/десериализации JSON файлов с помощью библиотеки Jackson
3. Добавление unit тестирования

#### Ирина:

1. Реализация основной логики приложения
2. Реализация окон с использованием Java AWT
