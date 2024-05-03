package org.sc.data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * Модель объекта "квартира"
 * содержит геттеры/сеттеры
 *
 * @author Kemansu
 * @since 1.0
 */
public class Flat implements Serializable {
    @Serial
    private static final long serialVersionUID = 5760575944040770153L;
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Максимальное значение поля: 637, Значение поля должно быть больше 0
    private Long numberOfRooms; //Значение поля должно быть больше 0
    private Double kitchenArea; //Поле не может быть null, Значение поля должно быть больше 0
    private View view; //Поле не может быть null
    private Transport transport; //Поле может быть null
    private House house; //Поле может быть null

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    public Flat(Long id) {
        this.creationDate = LocalDate.now();
        this.id = id;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public void setNumberOfRooms(long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setKitchenArea(Double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public long getId() {
        return id;
    }

    public Long getArea() {
        return area;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public Transport getTransport() {
        return transport;
    }

    public House getHouse() {
        return house;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getKitchenArea() {
        return kitchenArea;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", kitchenArea=" + kitchenArea +
                ", view=" + view +
                ", transport=" + transport +
                ", house=" + house +
                '}';
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public View getView() {
        return view;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
