package io.functional.streams;

import java.util.List;

public class Park {
    private String name;
    private List<String> parkingLots;

  public Park(String name, List<String> parkingLots) {
    this.name = name;
    this.parkingLots = parkingLots;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getParkingLots() {
    return parkingLots;
  }

  public void setParkingLots(List<String> parkingLots) {
    this.parkingLots = parkingLots;
  }
}
