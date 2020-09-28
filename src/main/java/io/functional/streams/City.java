package io.functional.streams;

import java.util.List;

public class City {
    private String name;
    private List<Park> parkList;

  public City(String name, List<Park> parkList) {
    this.name = name;
    this.parkList = parkList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Park> getParkList() {
    return parkList;
  }

  public void setParkList(List<Park> parkList) {
    this.parkList = parkList;
  }
}
