import java.util.*;

public class Building {
    private int numOfFloors;
    List<Customer> customerList = new ArrayList<Customer>();

    public void run() {
        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("input number of floors : ");
                numOfFloors = s.nextInt();
                if (numOfFloors < 0) {
                    System.out.println("Wrong input!!!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong input!!!");
            }
        }

        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("input number of customers : ");
                int customers = s.nextInt();
                if (customers < 0) {
                    System.out.println("Wrong input!!!");
                } else {
                    for (int i = 0; i < customers; i++) {
                        customerList.add(new Customer());
                    }
                    for (Customer cus : customerList) {
                        cus.setFloor(numOfFloors);
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println("Wrong input!!!");
            }
        }
    }

    public Elevator elevator() {
        // 빌딩의 정보를 엘레베이터에 옮기는 작업
        Elevator elevator = new Elevator();
        elevator.setNumOfFloors(numOfFloors);
        elevator.registerList = customerList;

        return elevator;
    }


    public void output() {
        Elevator elevator = this.elevator();
        elevator.move();
    }
}
